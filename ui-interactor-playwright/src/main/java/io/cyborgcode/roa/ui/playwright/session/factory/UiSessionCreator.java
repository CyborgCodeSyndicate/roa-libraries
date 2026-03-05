package io.cyborgcode.roa.ui.playwright.session.factory;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Tracing;
import io.cyborgcode.roa.ui.log.LogUi;
import io.cyborgcode.roa.ui.playwright.session.UiSession;
import io.cyborgcode.roa.ui.playwright.session.base.BrowserProvider;
import io.cyborgcode.roa.ui.playwright.session.config.SessionConfig;
import java.nio.file.Paths;

/**
 * Responsible for creating and configuring {@link UiSession} instances.
 *
 * <p>This class orchestrates the creation of the full Playwright session lifecycle:
 * {@link Playwright} → {@link Browser} → {@link BrowserContext} → {@link Page}.
 *
 * <p>It applies configuration from {@link SessionConfig} including headless mode,
 * browser channel, viewport, tracing, video recording, and timeouts.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public class UiSessionCreator {

   /**
    * Creates a new {@link UiSession} using the specified configuration and browser provider.
    *
    * @param config   The session configuration.
    * @param provider The browser provider for the desired browser type.
    * @return A fully initialized {@link UiSession}.
    */
   public UiSession createSession(final SessionConfig config, final BrowserProvider provider) {
      LogUi.info("Creating Playwright session for browser type: '{}'", provider.getBrowserType());

      final Playwright playwright = Playwright.create();
      final Browser browser = createBrowser(playwright, config, provider);
      final BrowserContext browserContext = createBrowserContext(browser, config);
      final Page page = createPage(browserContext, config);

      if (config.isEnableTracing()) {
         startTracing(browserContext);
      }

      LogUi.info("Playwright session created successfully for browser type: '{}'", provider.getBrowserType());

      return UiSession.builder()
            .playwright(playwright)
            .browser(browser)
            .browserContext(browserContext)
            .page(page)
            .build();
   }

   /**
    * Creates and configures the browser instance.
    *
    * @param playwright The Playwright instance.
    * @param config     The session configuration.
    * @param provider   The browser provider.
    * @return The launched {@link Browser} instance.
    */
   private Browser createBrowser(final Playwright playwright, final SessionConfig config,
                                 final BrowserProvider provider) {
      if (config.isRemote() && config.getRemoteBrowserUrl() != null && !config.getRemoteBrowserUrl().isEmpty()) {
         LogUi.info("Connecting to remote browser at: '{}'", config.getRemoteBrowserUrl());
         return provider.getBrowserTypeInstance(playwright)
               .connect(config.getRemoteBrowserUrl());
      }

      final BrowserType.LaunchOptions options = provider.createDefaultLaunchOptions();
      provider.applyDefaultArguments(options);

      if (config.isHeadless()) {
         provider.applyHeadlessArguments(options);
      } else {
         options.setHeadless(false);
      }

      if (config.getBrowserChannel() != null && !config.getBrowserChannel().isEmpty()) {
         options.setChannel(config.getBrowserChannel());
         LogUi.info("Using browser channel: '{}'", config.getBrowserChannel());
      }

      if (config.getSlowMo() > 0) {
         options.setSlowMo(config.getSlowMo());
         LogUi.info("Slow motion enabled: {} ms", config.getSlowMo());
      }

      config.getLaunchOptionsCustomizer().accept(options);

      return provider.launch(playwright, options);
   }

   /**
    * Creates and configures a browser context.
    *
    * @param browser The browser instance.
    * @param config  The session configuration.
    * @return The created {@link BrowserContext}.
    */
   private BrowserContext createBrowserContext(final Browser browser, final SessionConfig config) {
      final Browser.NewContextOptions contextOptions = new Browser.NewContextOptions()
            .setViewportSize(config.getViewportWidth(), config.getViewportHeight())
            .setIgnoreHTTPSErrors(config.isIgnoreHttpsErrors());

      if (config.isRecordVideo()) {
         contextOptions.setRecordVideoDir(Paths.get(config.getVideoDir()));
         LogUi.info("Video recording enabled. Output directory: '{}'", config.getVideoDir());
      }

      return browser.newContext(contextOptions);
   }

   /**
    * Creates a new page within the given browser context and applies timeout settings.
    *
    * @param browserContext The browser context.
    * @param config         The session configuration.
    * @return The created {@link Page}.
    */
   private Page createPage(final BrowserContext browserContext, final SessionConfig config) {
      final Page page = browserContext.newPage();
      page.setDefaultTimeout(config.getDefaultTimeout());
      page.setDefaultNavigationTimeout(config.getNavigationTimeout());
      return page;
   }

   /**
    * Starts tracing on the browser context for debugging purposes.
    *
    * @param browserContext The browser context on which to start tracing.
    */
   private void startTracing(final BrowserContext browserContext) {
      browserContext.tracing().start(new Tracing.StartOptions()
            .setScreenshots(true)
            .setSnapshots(true)
            .setSources(true));
      LogUi.info("Tracing enabled for browser context.");
   }

}
