package io.cyborgcode.roa.ui.playwright.config;

import io.cyborgcode.roa.ui.playwright.session.UiSession;
import io.cyborgcode.roa.ui.playwright.session.config.SessionConfig;
import io.cyborgcode.roa.ui.playwright.session.factory.UiSessionFactory;
import io.cyborgcode.roa.ui.playwright.validator.UiTableValidatorImpl;
import io.cyborgcode.roa.ui.validator.UiTableValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

import static io.cyborgcode.roa.ui.config.UiConfigHolderCore.getUiConfig;
import static io.cyborgcode.roa.ui.playwright.config.UiConfigHolder.getPlaywrightConfig;

/**
 * Spring configuration class for managing Playwright UI-related beans.
 *
 * <p>This class defines beans for Playwright session management and UI table validation,
 * enabling dependency injection for Playwright-based UI interactions and validation functionalities.
 *
 * <p>The configuration follows a lazy initialization strategy to optimize resource usage.
 * {@link UiSession} instances are created with a "prototype" scope, ensuring each test receives a fresh instance.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@Configuration
public class UiInteractionAutoConfiguration {

   /**
    * Provides a lazily initialized and prototype-scoped {@link UiSession} bean.
    *
    * <p>This method creates a Playwright session based on the core and Playwright-specific
    * UI configuration. It supports remote and local execution and applies all configured
    * browser options including viewport, tracing, video recording, and timeouts.
    *
    * @return A new instance of {@link UiSession}.
    */
   @Bean
   @Lazy
   @Scope("prototype")
   public UiSession uiSession() {
      return UiSessionFactory.createSession(
            getUiConfig().browserType(),
            SessionConfig.builder()
                  .headless(getUiConfig().headless())
                  .remote(getUiConfig().remoteBrowserUrl() != null && !getUiConfig().remoteBrowserUrl().isEmpty())
                  .remoteBrowserUrl(getUiConfig().remoteBrowserUrl())
                  .browserChannel(getPlaywrightConfig().browserChannel())
                  .enableTracing(getPlaywrightConfig().enableTracing() || getPlaywrightConfig().tracesOnFailedTest())
                  .traceDir(getPlaywrightConfig().traceDir())
                  .recordVideo(getPlaywrightConfig().recordVideo())
                  .videoDir(getPlaywrightConfig().videoDir())
                  .viewportWidth(getPlaywrightConfig().viewportWidth())
                  .viewportHeight(getPlaywrightConfig().viewportHeight())
                  .slowMo(getPlaywrightConfig().slowMo())
                  .ignoreHttpsErrors(getPlaywrightConfig().ignoreHttpsErrors())
                  .defaultTimeout(getPlaywrightConfig().defaultTimeout())
                  .navigationTimeout(getPlaywrightConfig().navigationTimeout())
                  .build()
      );
   }

   /**
    * Provides a lazily initialized {@link UiTableValidator} bean.
    *
    * <p>This validator facilitates automated assertions on table-based UI components.
    * It ensures consistency in table data validation across tests.
    *
    * @return A new instance of {@link UiTableValidatorImpl}.
    */
   @Bean
   @Lazy
   public UiTableValidator uiTableValidator() {
      return new UiTableValidatorImpl();
   }

}
