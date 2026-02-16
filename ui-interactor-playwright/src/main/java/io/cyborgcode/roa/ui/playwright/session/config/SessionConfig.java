package io.cyborgcode.roa.ui.playwright.session.config;

import java.util.function.Consumer;
import lombok.Builder;
import lombok.Getter;

/**
 * Immutable configuration class for Playwright browser sessions.
 *
 * <p>This class encapsulates all the settings needed to create and configure
 * a Playwright {@code Browser}, {@code BrowserContext}, and {@code Page}.
 *
 * <p>Instances are created using the builder pattern provided by Lombok's {@link Builder}.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@Getter
@Builder
public class SessionConfig {

   /**
    * The browser channel to use (e.g., "chrome", "msedge").
    * When empty, the default Playwright-bundled browser is used.
    */
   @Builder.Default
   private final String browserChannel = "";

   /**
    * Whether to run the browser in headless mode.
    */
   @Builder.Default
   private final boolean headless = false;

   /**
    * Whether to connect to a remote browser instance.
    */
   @Builder.Default
   private final boolean remote = false;

   /**
    * The WebSocket URL for connecting to a remote browser.
    */
   @Builder.Default
   private final String remoteBrowserUrl = "";

   /**
    * Whether to enable tracing for the browser context.
    */
   @Builder.Default
   private final boolean enableTracing = false;

   /**
    * The directory where trace files are stored.
    */
   @Builder.Default
   private final String traceDir = "traces";

   /**
    * Whether to record video of the browser session.
    */
   @Builder.Default
   private final boolean recordVideo = false;

   /**
    * The directory where video recordings are stored.
    */
   @Builder.Default
   private final String videoDir = "videos";

   /**
    * The viewport width in pixels.
    */
   @Builder.Default
   private final int viewportWidth = 1920;

   /**
    * The viewport height in pixels.
    */
   @Builder.Default
   private final int viewportHeight = 1080;

   /**
    * The slow motion delay in milliseconds.
    */
   @Builder.Default
   private final double slowMo = 0;

   /**
    * Whether to ignore HTTPS errors.
    */
   @Builder.Default
   private final boolean ignoreHttpsErrors = false;

   /**
    * The default timeout for Playwright actions in milliseconds.
    */
   @Builder.Default
   private final double defaultTimeout = 30000;

   /**
    * The navigation timeout in milliseconds.
    */
   @Builder.Default
   private final double navigationTimeout = 30000;

   /**
    * Optional customizer for the browser launch options.
    * Allows users to apply additional configuration beyond what is provided here.
    */
   @Builder.Default
   private final Consumer<Object> launchOptionsCustomizer = options -> { };

}
