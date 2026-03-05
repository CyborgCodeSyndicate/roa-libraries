package io.cyborgcode.roa.ui.extensions;

import io.cyborgcode.roa.framework.allure.CustomAllureListener;
import io.cyborgcode.roa.framework.decorators.DecoratorsFactory;
import io.cyborgcode.roa.framework.quest.SuperQuest;
import io.cyborgcode.roa.framework.storage.Storage;
import io.cyborgcode.roa.framework.storage.StoreKeys;
import io.cyborgcode.roa.ui.annotations.AuthenticateViaUi;
import io.cyborgcode.roa.ui.annotations.InterceptRequests;
import io.cyborgcode.roa.ui.authentication.BaseLoginClientCore;
import io.cyborgcode.roa.ui.authentication.LoginCredentials;
import io.cyborgcode.roa.ui.components.base.BaseUiElement;
import io.cyborgcode.roa.ui.components.interceptor.ApiResponse;
import io.cyborgcode.roa.ui.exceptions.AuthenticationUiException;
import io.cyborgcode.roa.ui.log.LogUi;
import io.cyborgcode.roa.ui.parameters.DataIntercept;
import io.cyborgcode.roa.ui.util.ResponseFormatter;
import io.cyborgcode.utilities.reflections.ReflectionUtil;
import io.qameta.allure.Allure;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;
import org.junit.platform.launcher.LauncherSession;
import org.junit.platform.launcher.LauncherSessionListener;
import org.springframework.context.ApplicationContext;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static io.cyborgcode.roa.framework.allure.StepType.TEAR_DOWN;
import static io.cyborgcode.roa.framework.config.FrameworkConfigHolder.getFrameworkConfig;
import static io.cyborgcode.roa.framework.util.TestContextManager.getSuperQuest;
import static io.cyborgcode.roa.ui.config.UiFrameworkConfigHolder.getUiFrameworkConfig;
import static io.cyborgcode.roa.ui.storage.StorageKeysUi.RESPONSES;
import static io.cyborgcode.roa.ui.storage.StorageKeysUi.UI;

/**
 * Abstract base JUnit 5 test extension for managing UI-related test execution lifecycle.
 *
 * <p>This extension provides framework-agnostic support for:
 * <ul>
 *     <li>Intercepting UI-related HTTP requests using {@link InterceptRequests}.</li>
 *     <li>Automating login via UI authentication using {@link AuthenticateViaUi}.</li>
 *     <li>Capturing screenshots on test failures and optionally on passed tests.</li>
 *     <li>Registering UI assertions and handling driver/page session cleanup.</li>
 * </ul>
 *
 * <p>Subclasses must implement framework-specific template methods for screenshot capture,
 * driver cleanup, request interception setup, login execution, assertion registration,
 * and custom service registration.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public abstract class UiTestExtensionCore<L, D, E extends BaseUiElement> implements
      BeforeTestExecutionCallback, AfterTestExecutionCallback,
      TestExecutionExceptionHandler, LauncherSessionListener {

   /**
    * Package substring used to identify UI module stack trace elements.
    */
   protected static final String UI_MODULE_PACKAGE = "cyborgcode.roa.ui";


   /**
    * Executes actions before the test runs, such as:
    * <ul>
    *     <li>Intercepting UI-related requests.</li>
    *     <li>Setting up authentication via UI.</li>
    *     <li>Registering custom assertions.</li>
    *     <li>Registering custom UI services.</li>
    * </ul>
    *
    * @param context The current test execution context.
    */
   @Override
   public void beforeTestExecution(final ExtensionContext context) {
      context.getTestMethod().ifPresent(method -> {
         processInterceptRequestsAnnotation(context, method);
         registerAssertionConsumer(context);
         processAuthenticateViaUiAnnotation(context, method);
         registerCustomServices(context);
      });
   }


   /**
    * Executes actions after the test completes, such as:
    * <ul>
    *     <li>Taking screenshots if enabled.</li>
    *     <li>Attaching intercepted responses to Allure.</li>
    *     <li>Cleaning up the driver/page session.</li>
    * </ul>
    *
    * @param context The current test execution context.
    */
   @Override
   public void afterTestExecution(final ExtensionContext context) {
      if (context.getExecutionException().isEmpty() && getUiFrameworkConfig().makeScreenshotOnPassedTest()) {
         takeScreenshot(context, context.getDisplayName());
      }
      attachInterceptedResponses(context);
      closeDriverIfNotKept(context);
   }


   /**
    * Handles test execution exceptions by capturing a screenshot and rethrowing the error.
    *
    * @param context   The current test execution context.
    * @param throwable The exception that occurred during test execution.
    * @throws Throwable The rethrown exception.
    */
   @Override
   public void handleTestExecutionException(final ExtensionContext context, final Throwable throwable)
         throws Throwable {
      LogUi.error("Unhandled exception during UI test execution ({}).",
            context.getDisplayName(), throwable);
      takeScreenshot(context, context.getDisplayName());
      throw throwable;
   }


   /**
    * Cleans up all sessions that were kept alive for credential caching.
    *
    * <p>Runs all cleanup actions registered by
    * {@link BaseLoginClientCore#getSessionCleanupActions()}.
    *
    * @param session The launcher session that is being closed.
    */
   @Override
   public void launcherSessionClosed(final LauncherSession session) {
      BaseLoginClientCore.getSessionCleanupActions().forEach(Runnable::run);
   }


   // ── Annotation processing (concrete) ────────────────────────────────────


   /**
    * Processes the {@link InterceptRequests} annotation on the test method.
    *
    * <p>Resolves URL substrings (potentially via {@link DataIntercept} enum implementations)
    * and adds a quest consumer that delegates to
    * {@link #setupRequestInterception(SuperQuest, String[])}.
    */
   private void processInterceptRequestsAnnotation(final ExtensionContext context, final Method method) {
      Optional.ofNullable(method.getAnnotation(InterceptRequests.class))
            .ifPresent(intercept -> {
               String[] urlsForIntercepting;
               try {
                  List<Class<? extends Enum>> enumClassImplementations =
                        ReflectionUtil.findEnumClassImplementationsOfInterface(
                              DataIntercept.class, getFrameworkConfig().projectPackages());

                  if (enumClassImplementations.isEmpty()) {
                     urlsForIntercepting = intercept.requestUrlSubStrings();
                  } else {
                     List<String> resolvedEndpoints = Arrays.stream(intercept.requestUrlSubStrings())
                           .map(target -> {
                              for (Class<? extends Enum> enumClass : enumClassImplementations) {
                                 try {
                                    @SuppressWarnings("rawtypes")
                                    Enum enumValue = Enum.valueOf((Class) enumClass, target);
                                    return ((DataIntercept<?>) enumValue).getEndpointSubString();
                                 } catch (IllegalArgumentException ignore) {
                                    //ignore
                                 }
                              }
                              return target;
                           })
                           .toList();

                     urlsForIntercepting = resolvedEndpoints.toArray(new String[0]);
                  }
               } catch (Exception e) {
                  urlsForIntercepting = intercept.requestUrlSubStrings();
               }

               final String[] finalUrlsForIntercepting = urlsForIntercepting;
               Consumer<SuperQuest> questConsumer =
                     quest -> setupRequestInterception(quest, finalUrlsForIntercepting);
               addQuestConsumer(context, questConsumer);
            });
   }


   /**
    * Processes the {@link AuthenticateViaUi} annotation on the test method.
    *
    * <p>Instantiates the {@link LoginCredentials} and adds a quest consumer that delegates to
    * {@link #postQuestCreationLogin(SuperQuest, DecoratorsFactory, String, String, Class, boolean)}.
    */
   private void processAuthenticateViaUiAnnotation(final ExtensionContext context, final Method method) {
      Optional.ofNullable(method.getAnnotation(AuthenticateViaUi.class))
            .ifPresent(login -> {
               try {
                  ApplicationContext appCtx = SpringExtension.getApplicationContext(context);
                  DecoratorsFactory decoratorsFactory = appCtx.getBean(DecoratorsFactory.class);
                  LoginCredentials credentials = login.credentials().getDeclaredConstructor().newInstance();
                  @SuppressWarnings("unchecked")
                  Class<? extends BaseLoginClientCore<?, L, D, E>> loginType =
                        (Class<? extends BaseLoginClientCore<?, L, D, E>>) (Class<?>) login.type();
                  Consumer<SuperQuest> questConsumer =
                        quest -> postQuestCreationLogin(quest, decoratorsFactory, credentials.username(),
                              credentials.password(), loginType, login.cacheCredentials());
                  addQuestConsumer(context, questConsumer);
               } catch (InstantiationException | IllegalAccessException | InvocationTargetException
                        | NoSuchMethodException e) {
                  throw new AuthenticationUiException("Failed to instantiate login credentials", e);
               }
            });
   }


   /**
    * Registers an assertion consumer that delegates to
    * {@link #postQuestCreationAssertion(SuperQuest, String)}.
    */
   private void registerAssertionConsumer(final ExtensionContext context) {
      Consumer<SuperQuest> questConsumer = quest -> postQuestCreationAssertion(quest, context.getDisplayName());
      addQuestConsumer(context, questConsumer);
   }


   /**
    * Registers a custom services consumer that delegates to
    * {@link #postQuestCreationRegisterCustomServices(SuperQuest)}.
    */
   private void registerCustomServices(final ExtensionContext context) {
      Consumer<SuperQuest> questConsumer = this::postQuestCreationRegisterCustomServices;
      addQuestConsumer(context, questConsumer);
   }


   // ── Quest consumer management ────────────────────────────────────────────


   /**
    * Adds a quest consumer to the extension context store.
    *
    * @param context       The current test execution context.
    * @param questConsumer The consumer to add.
    */
   protected void addQuestConsumer(final ExtensionContext context, final Consumer<SuperQuest> questConsumer) {
      var consumers = getOrCreateQuestConsumers(context);
      consumers.add(questConsumer);
   }


   /**
    * Retrieves the quest consumer list from the extension store, creating it if absent.
    *
    * @param context The current test execution context.
    * @return A mutable list of quest consumers.
    */
   @SuppressWarnings("unchecked")
   protected List<Consumer<SuperQuest>> getOrCreateQuestConsumers(final ExtensionContext context) {
      return (List<Consumer<SuperQuest>>) context.getStore(ExtensionContext.Namespace.GLOBAL)
            .computeIfAbsent(StoreKeys.QUEST_CONSUMERS,
                  key -> new ArrayList<>());
   }


   // ── Intercepted responses (concrete) ─────────────────────────────────────


   /**
    * Attaches intercepted API responses to the Allure report.
    *
    * @param context The current test execution context.
    */
   private void attachInterceptedResponses(final ExtensionContext context) {
      List<ApiResponse> responses = getSuperQuest(context).getStorage().sub(UI).getAllByClass(RESPONSES,
            ApiResponse.class);
      if (!responses.isEmpty()) {
         String formattedResponses = ResponseFormatter.formatResponses(responses);
         Allure.addAttachment("Intercepted Requests", "text/html",
               new ByteArrayInputStream(formattedResponses.getBytes(StandardCharsets.UTF_8)), ".html");
      }
   }


   /**
    * Adds an intercepted API response to the quest storage.
    *
    * @param storage     The quest storage instance.
    * @param apiResponse The intercepted API response.
    */
   protected static void addResponseInStorage(final Storage storage, final ApiResponse apiResponse) {
      List<ApiResponse> responses = storage.sub(UI).get(RESPONSES, new ParameterizedTypeReference<>() {
      });
      if (responses == null) {
         responses = new ArrayList<>();
      }
      responses.add(apiResponse);
      storage.sub(UI).put(RESPONSES, responses);

      LogUi.extended("Response added to storage: URL={}, Status={}", apiResponse.getUrl(), apiResponse.getStatus());
   }


   /**
    * Checks whether any of the interception URL substrings match the given URL.
    *
    * @param urlsForIntercepting The URL substrings to check against.
    * @param url                 The actual URL.
    * @return {@code true} if any substring matches.
    */
   protected static boolean checkUrl(final String[] urlsForIntercepting, final String url) {
      return Arrays.stream(urlsForIntercepting).anyMatch(url::contains);
   }


   // ── Screenshot utility (concrete) ────────────────────────────────────────


   /**
    * Ensures the Allure step is set to {@code TEAR_DOWN} before taking a screenshot.
    *
    * <p>This method manages the Allure step lifecycle and then delegates to
    * {@link #captureScreenshot(ExtensionContext, String)} for the actual framework-specific
    * screenshot capture.
    *
    * @param context  The current test execution context.
    * @param testName The name of the test (used as the screenshot attachment name).
    */
   protected void takeScreenshot(final ExtensionContext context, final String testName) {
      if (!Objects.equals(CustomAllureListener.getActiveStepName(), TEAR_DOWN.getDisplayName())) {
         CustomAllureListener.stopStep();
         CustomAllureListener.startStep(TEAR_DOWN);
      }
      captureScreenshot(context, testName);
   }


   // ── File attachment utility (concrete) ────────────────────────────────────


   /**
    * Attaches a file to the Allure report.
    *
    * @param name     The name for the Allure attachment.
    * @param filePath The path to the file to attach.
    * @param mimeType The MIME type of the file (e.g., "video/webm", "application/zip").
    */
   protected static void attachFileToAllure(final String name, final Path filePath, final String mimeType) {
      try (InputStream is = Files.newInputStream(filePath)) {
         Allure.addAttachment(name, mimeType, is, getFileExtension(filePath));
         LogUi.info("Attached '{}' to Allure report.", name);
      } catch (Exception e) {
         LogUi.error("Failed to attach '{}' to Allure report: {}", name, e.getMessage());
      }
   }

   private static String getFileExtension(final Path path) {
      String fileName = path.getFileName().toString();
      int dotIndex = fileName.lastIndexOf('.');
      return dotIndex >= 0 ? fileName.substring(dotIndex) : "";
   }


   // ── Abstract template methods (framework-specific) ───────────────────────


   /**
    * Captures a screenshot and attaches it to the Allure report.
    *
    * <p>Selenium implementations use {@code TakesScreenshot}, while Playwright
    * implementations use {@code Page.screenshot()}.
    *
    * @param context  The current test execution context.
    * @param testName The name to use for the screenshot attachment.
    */
   protected abstract void captureScreenshot(ExtensionContext context, String testName);

   /**
    * Closes the driver/page session unless it has been marked as "kept" for credential caching.
    *
    * <p>Implementations should check whether the driver/page is in
    * {@link BaseLoginClientCore#getKeptDrivers()} and skip closing if so.
    *
    * @param context The current test execution context.
    */
   protected abstract void closeDriverIfNotKept(ExtensionContext context);

   /**
    * Sets up request interception for the given URLs.
    *
    * <p>Selenium implementations use Chrome DevTools Protocol, while Playwright
    * implementations use {@code BrowserContext.route()}.
    *
    * @param quest               The current quest instance.
    * @param urlsForIntercepting The URL substrings to intercept.
    */
   protected abstract void setupRequestInterception(SuperQuest quest, String[] urlsForIntercepting);

   /**
    * Executes the login flow using the framework-specific UI service.
    *
    * <p>Implementations should:
    * <ol>
    *     <li>Store username/password in quest storage.</li>
    *     <li>Retrieve the framework-specific fluent UI service from the quest.</li>
    *     <li>Instantiate the login client and invoke {@code login()}.</li>
    * </ol>
    *
    * @param quest             The current quest instance.
    * @param decoratorsFactory The factory for creating decorators.
    * @param username          The username for authentication.
    * @param password          The password for authentication.
    * @param type              The login client class to instantiate.
    * @param cache             Whether to cache credentials for session reuse.
    */
   protected abstract void postQuestCreationLogin(SuperQuest quest, DecoratorsFactory decoratorsFactory,
                                                  String username, String password,
                                                  Class<? extends BaseLoginClientCore<?, L, D, E>> type, boolean cache);

   /**
    * Registers a soft assertion error handler for the framework-specific driver type.
    *
    * <p>Selenium implementations register {@code SmartWebDriver} for screenshot-on-assertion-failure,
    * while Playwright implementations register {@code Page}.
    *
    * @param quest    The current quest instance.
    * @param testName The display name of the test.
    */
   protected abstract void postQuestCreationAssertion(SuperQuest quest, String testName);

   /**
    * Registers custom UI service implementations found in the project packages.
    *
    * <p>Each framework adapter scans for its own {@code UiServiceFluent} subclasses
    * and registers them into the quest.
    *
    * @param quest The current quest instance.
    */
   protected abstract void postQuestCreationRegisterCustomServices(SuperQuest quest);

}
