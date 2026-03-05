package io.cyborgcode.roa.ui.playwright.extensions;

import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Route;
import com.microsoft.playwright.Tracing;
import com.microsoft.playwright.Video;
import io.cyborgcode.roa.framework.assertion.CustomSoftAssertion;
import io.cyborgcode.roa.framework.decorators.DecoratorsFactory;
import io.cyborgcode.roa.framework.exceptions.ServiceInitializationException;
import io.cyborgcode.roa.framework.log.LogQuest;
import io.cyborgcode.roa.framework.quest.Quest;
import io.cyborgcode.roa.framework.quest.SuperQuest;
import io.cyborgcode.roa.framework.storage.StoreKeys;
import io.cyborgcode.roa.ui.authentication.BaseLoginClientCore;
import io.cyborgcode.roa.ui.components.interceptor.ApiResponse;
import io.cyborgcode.roa.ui.exceptions.AuthenticationUiException;
import io.cyborgcode.roa.ui.extensions.UiTestExtensionCore;
import io.cyborgcode.roa.ui.log.LogUi;
import io.cyborgcode.roa.ui.playwright.base.PwBy;
import io.cyborgcode.roa.ui.playwright.base.PwElement;
import io.cyborgcode.roa.ui.playwright.config.UiConfig;
import io.cyborgcode.roa.ui.playwright.service.fluent.SuperUiServiceFluent;
import io.cyborgcode.roa.ui.playwright.service.fluent.UiServiceFluent;
import io.cyborgcode.roa.ui.playwright.session.UiSession;
import io.cyborgcode.utilities.reflections.ReflectionUtil;
import io.qameta.allure.Allure;
import java.io.ByteArrayInputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.CodeSource;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.platform.launcher.LauncherSession;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static io.cyborgcode.roa.ui.playwright.config.UiConfigHolder.getPlaywrightConfig;
import static io.cyborgcode.roa.ui.storage.StorageKeysUi.PASSWORD;
import static io.cyborgcode.roa.ui.storage.StorageKeysUi.UI;
import static io.cyborgcode.roa.ui.storage.StorageKeysUi.USERNAME;

/**
 * Playwright-specific JUnit 5 test extension for managing UI-related test execution lifecycle.
 *
 * <p>This extension provides Playwright-specific implementations for:
 * <ul>
 *     <li>Capturing screenshots via {@link Page#screenshot()}.</li>
 *     <li>Intercepting network requests via {@link Page#route(String, java.util.function.Consumer)}.</li>
 *     <li>Automating login via {@link io.cyborgcode.roa.ui.playwright.authentication.BaseLoginClient}.</li>
 *     <li>Closing Playwright sessions after tests.</li>
 * </ul>
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public class UiTestExtension extends UiTestExtensionCore<PwBy, Page, PwElement> {

   private static final String PLAYWRIGHT_PACKAGE = "com.microsoft.playwright";


   /**
    * Captures a screenshot from the Playwright {@link Page} and attaches it to the Allure report.
    *
    * @param context  The current test execution context.
    * @param testName The name to use for the screenshot attachment.
    */
   @Override
   protected void captureScreenshot(final ExtensionContext context, final String testName) {
      try {
         Page page = getPage(context);
         byte[] screenshotBytes = page.screenshot();
         Allure.addAttachment(testName, new ByteArrayInputStream(screenshotBytes));
         LogQuest.info("Screenshot taken and stored for: " + testName);
      } catch (Exception e) {
         LogUi.error("Failed to take screenshot for test '{}': {}", testName, e.getMessage());
      }
   }


   /**
    * Closes the Playwright page/session unless it has been marked as "kept" for credential caching.
    *
    * <p>Before closing, this method:
    * <ul>
    *     <li>Saves Playwright traces on test failure (if {@link UiConfig#tracesOnFailedTest()} is enabled).</li>
    *     <li>Captures video recordings and attaches them to Allure (if {@link UiConfig#recordVideo()} is enabled).</li>
    * </ul>
    *
    * @param context The current test execution context.
    */
   @Override
   protected void closeDriverIfNotKept(final ExtensionContext context) {
      try {
         UiSession session = getSession(context);
         Page page = session.getPage();
         if (BaseLoginClientCore.getKeptDrivers().contains(page)) {
            return;
         }

         boolean testFailed = context.getExecutionException().isPresent();
         UiConfig config = getPlaywrightConfig();
         BrowserContext browserContext = page.context();
         String testName = context.getDisplayName();

         Path tracePath = saveTraceIfNeeded(browserContext, config, testFailed, testName);
         Path videoPath = getVideoPath(page);

         browserContext.close();
         LogUi.info("Playwright session closed successfully.");

         attachVideoIfNeeded(videoPath, config, testFailed, testName);
         attachTraceIfNeeded(tracePath, testName);
      } catch (Exception e) {
         LogUi.error("Failed to close Playwright session: {}", e.getMessage());
      }
   }


   /**
    * Sets up request interception for the given URL substrings using Playwright's routing API.
    *
    * <p>Intercepts all requests matching {@code "**&#47;*"} and captures response data for URLs
    * that contain any of the specified substrings.
    *
    * @param quest               The current quest instance.
    * @param urlsForIntercepting The URL substrings to intercept.
    */
   @Override
   protected void setupRequestInterception(final SuperQuest quest, final String[] urlsForIntercepting) {

      Page page = quest.artifact(UiServiceFluent.class, UiSession.class).getPage();

      page.route("**/*", route -> {
         APIResponse response = route.fetch();
         String url = response.url();
         String method = route.request().method();
         int statusCode = response.status();
         ApiResponse apiResponse = new ApiResponse(url, method, statusCode);

         if (checkUrl(urlsForIntercepting, url)) {
            try {
               apiResponse.setBody(response.text());
            } catch (Exception e) {
               apiResponse.setBody("Error retrieving response body: " + e.getMessage());
            }
         }
         addResponseInStorage(quest.getStorage(), apiResponse);
         route.fulfill(new Route.FulfillOptions()
               .setStatus(response.status())
               .setHeaders(response.headers())
               .setBody(response.text()));
      });
   }


   /**
    * Executes the login flow using the Playwright-specific UI service.
    *
    * @param quest             The current quest instance.
    * @param decoratorsFactory The factory for creating decorators.
    * @param username          The username for authentication.
    * @param password          The password for authentication.
    * @param type              The login client class to instantiate.
    * @param cache             Whether to cache credentials for session reuse.
    */
   @Override
   protected void postQuestCreationLogin(final SuperQuest quest, final DecoratorsFactory decoratorsFactory,
                                         final String username, final String password,
                                         final Class<? extends BaseLoginClientCore<?, PwBy, Page, PwElement>> type, final boolean cache) {
      quest.getStorage().sub(UI).put(USERNAME, username);
      quest.getStorage().sub(UI).put(PASSWORD, password);
      UiServiceFluent<?> uiServiceFluent = quest.use(UiServiceFluent.class);

      try {
         BaseLoginClientCore<?, PwBy, Page, PwElement> baseLoginClient =
               type.getDeclaredConstructor().newInstance();
         SuperUiServiceFluent<?> superService =
               (SuperUiServiceFluent<?>) decoratorsFactory.decorate(
                     uiServiceFluent, SuperUiServiceFluent.class);
         baseLoginClient.login(superService, username, password, cache);
      } catch (InstantiationException | IllegalAccessException | InvocationTargetException
               | NoSuchMethodException e) {
         throw new AuthenticationUiException("Failed to instantiate or execute login client", e);
      }
   }


   /**
    * Registers a soft assertion error handler for the Playwright {@link Page} type.
    *
    * <p>On assertion failure, a screenshot is taken from the Playwright page and attached to
    * the Allure report.
    *
    * @param quest    The current quest instance.
    * @param testName The display name of the test.
    */
   @Override
   protected void postQuestCreationAssertion(final SuperQuest quest, final String testName) {
      Page page = quest.artifact(UiServiceFluent.class, UiSession.class).getPage();
      quest.getSoftAssertions().registerObjectForPostErrorHandling(Page.class, page);

      CustomSoftAssertion.registerCustomAssertion(
            Page.class,
            (assertionError, pwPage) -> capturePageScreenshot(pwPage, "soft_assert_failure_" + testName),
            stackTrace -> Arrays.stream(stackTrace)
                  .anyMatch(element -> element.getClassName().contains(PLAYWRIGHT_PACKAGE)
                        || element.getClassName().contains(UI_MODULE_PACKAGE))
      );
   }


   /**
    * Registers custom UI service implementations found in the project packages.
    *
    * <p>Scans for implementations of {@link UiServiceFluent} and registers them into the quest,
    * replacing the default service.
    *
    * @param quest The current quest instance.
    */
   @Override
   protected void postQuestCreationRegisterCustomServices(final SuperQuest quest) {
      String[] projectPackages = io.cyborgcode.roa.ui.config.UiConfigHolderCore.getUiConfig().projectPackages();

      List<Class<? extends UiServiceFluent>> customUiServices =
            ReflectionUtil.findImplementationsOfInterface(
                  UiServiceFluent.class,
                  projectPackages
            );

      if (customUiServices.size() > 1) {
         if (haveMultipleCodeSources(customUiServices)) {
            customUiServices =
                  ReflectionUtil.findImplementationsOfInterface(
                        UiServiceFluent.class,
                        projectPackages[0]
                  );
         }

         if (customUiServices.size() > 1) {
            throw new IllegalStateException(
                  "There is more than one UI service that extends UiServiceFluent. Only 1 is allowed. Found: "
                        + customUiServices
            );
         }
      }
      if (!customUiServices.isEmpty()) {
         Class<? extends UiServiceFluent> customUiServiceFluentClass = customUiServices.get(0);
         try {
            Page page = quest.artifact(UiServiceFluent.class, UiSession.class).getPage();
            quest.registerRing(customUiServiceFluentClass, customUiServiceFluentClass.getDeclaredConstructor(
                  Page.class, SuperQuest.class).newInstance(page, quest));
            quest.removeRing(UiServiceFluent.class);
         } catch (InstantiationException | IllegalAccessException | InvocationTargetException
                  | NoSuchMethodException e) {
            throw new ServiceInitializationException("Failed to register custom UI service", e);
         }
      }
   }


   // ── Helpers ───────────────────────────────────────────────────────────────


   /**
    * Retrieves the Playwright {@link UiSession} from the current test execution context.
    *
    * @param context The current test execution context.
    * @return The {@link UiSession} instance.
    */
   private UiSession getSession(final ExtensionContext context) {
      ApplicationContext appCtx = SpringExtension.getApplicationContext(context);
      DecoratorsFactory decoratorsFactory = appCtx.getBean(DecoratorsFactory.class);
      Quest quest = (Quest) context.getStore(ExtensionContext.Namespace.GLOBAL).get(StoreKeys.QUEST);
      SuperQuest superQuest = decoratorsFactory.decorate(quest, SuperQuest.class);
      return superQuest.artifact(UiServiceFluent.class, UiSession.class);
   }

   /**
    * Retrieves the Playwright {@link Page} from the current test execution context.
    *
    * @param context The current test execution context.
    * @return The Playwright {@link Page} instance.
    */
   private Page getPage(final ExtensionContext context) {
      return getSession(context).getPage();
   }

   /**
    * Saves the Playwright trace to a temporary file if tracing is active and the test failed.
    *
    * @param browserContext The browser context with active tracing.
    * @param config         The Playwright configuration.
    * @param testFailed     Whether the test failed.
    * @param testName       The display name of the test.
    * @return The path to the saved trace file, or {@code null} if not saved.
    */
   private static Path saveTraceIfNeeded(final BrowserContext browserContext, final UiConfig config,
                                         final boolean testFailed, final String testName) {
      if (!testFailed || !config.tracesOnFailedTest()) {
         return null;
      }
      try {
         Path tracePath = Files.createTempFile("trace-" + sanitizeFileName(testName) + "-", ".zip");
         browserContext.tracing().stop(new Tracing.StopOptions().setPath(tracePath));
         LogUi.info("Trace saved for failed test: {}", testName);
         return tracePath;
      } catch (Exception e) {
         LogUi.error("Failed to save trace for test '{}': {}", testName, e.getMessage());
         return null;
      }
   }

   /**
    * Retrieves the video file path from the Playwright page, if video recording is active.
    *
    * @param page The Playwright page.
    * @return The video file path, or {@code null} if no video is being recorded.
    */
   private static Path getVideoPath(final Page page) {
      Video video = page.video();
      return video != null ? video.path() : null;
   }

   /**
    * Attaches the recorded video to the Allure report if applicable.
    *
    * <p>Videos are attached for failed tests by default. For successful tests, videos are
    * only attached if {@link UiConfig#videoOnSuccessfulTest()} is enabled.
    * Video files are always cleaned up after processing.
    *
    * @param videoPath  The path to the video file.
    * @param config     The Playwright configuration.
    * @param testFailed Whether the test failed.
    * @param testName   The display name of the test.
    */
   private static void attachVideoIfNeeded(final Path videoPath, final UiConfig config,
                                           final boolean testFailed, final String testName) {
      if (videoPath == null || !config.recordVideo()) {
         return;
      }
      try {
         if (!Files.exists(videoPath)) {
            return;
         }
         boolean shouldAttach = testFailed || config.videoOnSuccessfulTest();
         if (shouldAttach) {
            attachFileToAllure("Video - " + testName, videoPath, "video/webm");
         }
         Files.deleteIfExists(videoPath);
      } catch (Exception e) {
         LogUi.error("Failed to process video for test '{}': {}", testName, e.getMessage());
      }
   }

   /**
    * Attaches the saved trace file to the Allure report.
    *
    * @param tracePath The path to the trace file.
    * @param testName  The display name of the test.
    */
   private static void attachTraceIfNeeded(final Path tracePath, final String testName) {
      if (tracePath == null) {
         return;
      }
      try {
         if (Files.exists(tracePath)) {
            attachFileToAllure("Trace - " + testName, tracePath, "application/zip");
            Files.deleteIfExists(tracePath);
         }
      } catch (Exception e) {
         LogUi.error("Failed to attach trace for test '{}': {}", testName, e.getMessage());
      }
   }

   /**
    * Sanitizes a test name for use in file names by replacing non-alphanumeric characters.
    *
    * @param name The original name.
    * @return A sanitized file-name-safe string.
    */
   private static String sanitizeFileName(final String name) {
      return name.replaceAll("[^a-zA-Z0-9_-]", "_");
   }


   /**
    * Takes a screenshot from a Playwright page and attaches it to Allure.
    *
    * @param page     The Playwright page to capture.
    * @param testName The attachment name.
    */
   private static void capturePageScreenshot(final Page page, final String testName) {
      try {
         byte[] screenshotBytes = page.screenshot();
         Allure.addAttachment(testName, new ByteArrayInputStream(screenshotBytes));
         LogQuest.info("Screenshot taken and stored for: " + testName);
      } catch (Exception e) {
         LogUi.error("Failed to take screenshot for test '{}': {}", testName, e.getMessage());
      }
   }


   private static boolean haveMultipleCodeSources(final List<Class<? extends UiServiceFluent>> classes) {
      return classes.stream()
            .map(UiTestExtension::getCodeSourceLocationSafe)
            .distinct()
            .limit(2)
            .count() > 1;
   }

   private static URL getCodeSourceLocationSafe(final Class<?> clazz) {
      CodeSource codeSource = clazz.getProtectionDomain().getCodeSource();
      return codeSource != null ? codeSource.getLocation() : null;
   }

   @Override
   public void launcherSessionOpened(LauncherSession session) {
      super.launcherSessionOpened(session);
   }
}
