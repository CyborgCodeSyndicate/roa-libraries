package io.cyborgcode.roa.ui.playwright.config;

import io.cyborgcode.utilities.config.ConfigSource;
import io.cyborgcode.utilities.config.PropertyConfig;
import org.aeonbits.owner.Config;

/**
 * Configuration interface for Playwright-based UI automation settings.
 *
 * <p>This interface is used to define and retrieve configuration properties
 * for the Playwright UI automation framework. It uses the {@link Config} library
 * to load values from system properties or a properties file.
 *
 * <p>Properties are loaded from:
 * <ul>
 *     <li>System properties (e.g., {@code -Dbrowser.type=CHROMIUM})</li>
 *     <li>Classpath properties file, dynamically determined by {@code ${playwright.config.file}.properties}</li>
 * </ul>
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@ConfigSource("ui-config")
@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({"system:properties", "classpath:${playwright.config.file}.properties"})
public interface UiConfig extends PropertyConfig {


   /**
    * Retrieves the browser channel to use.
    *
    * <p>Channels allow launching branded browsers such as "chrome", "msedge", "chrome-beta", etc.
    * When empty, the default Playwright-bundled browser is used.
    *
    * <p>Default: "" (empty string, meaning Playwright-bundled browser)
    *
    * @return The browser channel name.
    */
   @DefaultValue("")
   @Key("browser.channel")
   String browserChannel();



   /**
    * Determines whether tracing should be enabled for debugging.
    *
    * <p>When enabled, Playwright records a trace that can be inspected using the Trace Viewer.
    *
    * <p>Default: false
    *
    * @return {@code true} if tracing is enabled, otherwise {@code false}.
    */
   @DefaultValue("false")
   @Key("enable.tracing")
   boolean enableTracing();

   /**
    * Retrieves the directory path where trace files should be stored.
    *
    * <p>Default: "traces"
    *
    * @return The trace output directory.
    */
   @DefaultValue("traces")
   @Key("trace.dir")
   String traceDir();

   /**
    * Determines whether Playwright traces should be saved and attached to Allure reports
    * when a test fails.
    *
    * <p>When enabled, tracing is automatically activated and trace files are captured
    * on test failure for debugging purposes.
    *
    * <p>Default: false
    *
    * @return {@code true} if traces should be attached on failed tests, otherwise {@code false}.
    */
   @DefaultValue("false")
   @Key("traces.on.failed.test")
   boolean tracesOnFailedTest();

   /**
    * Determines whether video recording should be enabled for browser sessions.
    *
    * <p>Default: false
    *
    * @return {@code true} if video recording is enabled, otherwise {@code false}.
    */
   @DefaultValue("false")
   @Key("record.video")
   boolean recordVideo();

   /**
    * Retrieves the directory path where video recordings should be stored.
    *
    * <p>Default: "videos"
    *
    * @return The video output directory.
    */
   @DefaultValue("videos")
   @Key("video.dir")
   String videoDir();

   /**
    * Determines whether video recordings should be kept and attached to Allure reports
    * for successful tests.
    *
    * <p>When disabled, video recordings are only kept for failed tests.
    * Requires {@link #recordVideo()} to be enabled.
    *
    * <p>Default: false
    *
    * @return {@code true} if video should be attached on successful tests, otherwise {@code false}.
    */
   @DefaultValue("false")
   @Key("video.on.successful.test")
   boolean videoOnSuccessfulTest();

   /**
    * Retrieves the viewport width for the browser context.
    *
    * <p>Default: 1920
    *
    * @return The viewport width in pixels.
    */
   @DefaultValue("1920")
   @Key("viewport.width")
   int viewportWidth();

   /**
    * Retrieves the viewport height for the browser context.
    *
    * <p>Default: 1080
    *
    * @return The viewport height in pixels.
    */
   @DefaultValue("1080")
   @Key("viewport.height")
   int viewportHeight();

   /**
    * Retrieves the slow motion delay in milliseconds.
    *
    * <p>This slows down Playwright operations by the specified amount,
    * useful for debugging and demonstrations.
    *
    * <p>Default: 0 (no slow motion)
    *
    * @return The slow motion delay in milliseconds.
    */
   @DefaultValue("0")
   @Key("slow.mo")
   double slowMo();

   /**
    * Determines whether HTTPS errors should be ignored.
    *
    * <p>Default: false
    *
    * @return {@code true} if HTTPS errors should be ignored, otherwise {@code false}.
    */
   @DefaultValue("false")
   @Key("ignore.https.errors")
   boolean ignoreHttpsErrors();

   /**
    * Retrieves the default timeout for Playwright actions in milliseconds.
    *
    * <p>Default: 30000 (30 seconds)
    *
    * @return The default timeout in milliseconds.
    */
   @DefaultValue("30000")
   @Key("default.timeout")
   double defaultTimeout();

   /**
    * Retrieves the default navigation timeout in milliseconds.
    *
    * <p>Default: 30000 (30 seconds)
    *
    * @return The navigation timeout in milliseconds.
    */
   @DefaultValue("30000")
   @Key("navigation.timeout")
   double navigationTimeout();

}
