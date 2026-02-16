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
@ConfigSource("playwright-config")
@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({"system:properties", "classpath:${playwright.config.file}.properties"})
public interface UiConfig extends PropertyConfig {

   /**
    * Retrieves the browser type used for UI testing.
    *
    * <p>Default: "CHROMIUM"
    *
    * @return The browser type (e.g., CHROMIUM, FIREFOX, WEBKIT).
    */
   @DefaultValue("CHROMIUM")
   @Key("browser.type")
   String browserType();

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
    * Determines whether the browser should run in headless mode.
    *
    * <p>Default: false
    *
    * @return {@code true} if headless mode is enabled, otherwise {@code false}.
    */
   @DefaultValue("false")
   @Key("headless")
   boolean headless();

   /**
    * Retrieves the URL for a remote browser instance via Playwright's connect method.
    *
    * @return The remote browser WebSocket URL.
    */
   @DefaultValue("")
   @Key("remote.browser.url")
   String remoteBrowserUrl();

   /**
    * Retrieves the default wait duration for element interactions in seconds.
    *
    * @return The wait duration in seconds.
    */
   @Key("wait.duration.in.seconds")
   int waitDuration();

   /**
    * Retrieves the base package of the project for class scanning.
    *
    * @return The project's root package(s).
    */
   @Key("project.packages")
   @Separator(";")
   String[] projectPackages();

   /**
    * Retrieves the default type for input fields.
    *
    * @return The default input component type.
    */
   @Key("input.default.type")
   String inputDefaultType();

   /**
    * Retrieves the default type for buttons.
    *
    * @return The default button component type.
    */
   @Key("button.default.type")
   String buttonDefaultType();

   /**
    * Retrieves the default type for checkboxes.
    *
    * @return The default checkbox component type.
    */
   @Key("checkbox.default.type")
   String checkboxDefaultType();

   /**
    * Retrieves the default type for toggle buttons.
    *
    * @return The default toggle component type.
    */
   @Key("toggle.default.type")
   String toggleDefaultType();

   /**
    * Retrieves the default type for radio buttons.
    *
    * @return The default radio button component type.
    */
   @Key("radio.default.type")
   String radioDefaultType();

   /**
    * Retrieves the default type for select (dropdown) components.
    *
    * @return The default select component type.
    */
   @Key("select.default.type")
   String selectDefaultType();

   /**
    * Retrieves the default type for list components.
    *
    * @return The default list component type.
    */
   @Key("list.default.type")
   String listDefaultType();

   /**
    * Retrieves the default type for loader/spinner components.
    *
    * @return The default loader component type.
    */
   @Key("loader.default.type")
   String loaderDefaultType();

   /**
    * Retrieves the default type for link components.
    *
    * @return The default link component type.
    */
   @Key("link.default.type")
   String linkDefaultType();

   /**
    * Retrieves the default type for alert components.
    *
    * @return The default alert component type.
    */
   @Key("alert.default.type")
   String alertDefaultType();

   /**
    * Retrieves the default type for tab components.
    *
    * @return The default tab component type.
    */
   @Key("tab.default.type")
   String tabDefaultType();

   /**
    * Retrieves the default type for modal components.
    *
    * @return The default modal component type.
    */
   @Key("modal.default.type")
   String modalDefaultType();

   /**
    * Retrieves the default type for accordion components.
    *
    * @return The default accordion component type.
    */
   @Key("accordion.default.type")
   String accordionDefaultType();

   /**
    * Retrieves the base URL for the UI application.
    *
    * <p>This URL is used as the root for all page navigation and resource loading
    * within the UI automation framework.
    *
    * @return The configured base URL.
    */
   @Key("ui.base.url")
   String baseUrl();

   /**
    * Retrieves the default type for table components.
    *
    * @return The default table component type.
    */
   @Key("table.default.type")
   String tableDefaultType();

   /**
    * Determines whether wrapped Playwright functions should be used.
    *
    * <p>Default: true
    *
    * @return {@code true} if wrapped Playwright functions should be used, otherwise {@code false}.
    */
   @DefaultValue("true")
   @Key("use.wrap.playwright.function")
   boolean useWrappedPlaywrightFunctions();

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
