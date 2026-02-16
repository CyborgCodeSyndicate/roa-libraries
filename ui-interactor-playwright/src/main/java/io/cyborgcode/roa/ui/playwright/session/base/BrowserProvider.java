package io.cyborgcode.roa.ui.playwright.session.base;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;

/**
 * Defines the contract for Playwright browser providers.
 *
 * <p>Each implementation is responsible for creating a specific browser type
 * (e.g., Chromium, Firefox, WebKit) using Playwright's API.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface BrowserProvider {

   /**
    * Retrieves the browser type identifier (e.g., "CHROMIUM", "FIREFOX", "WEBKIT").
    *
    * @return A string representing the browser type.
    */
   String getBrowserType();

   /**
    * Retrieves the Playwright {@link BrowserType} instance for this provider.
    *
    * @param playwright The Playwright instance.
    * @return The {@link BrowserType} for launching the browser.
    */
   BrowserType getBrowserTypeInstance(Playwright playwright);

   /**
    * Creates default launch options for this browser type.
    *
    * @return A new {@link BrowserType.LaunchOptions} instance with default settings.
    */
   BrowserType.LaunchOptions createDefaultLaunchOptions();

   /**
    * Applies headless-specific arguments to the launch options.
    *
    * @param options The launch options to modify.
    */
   void applyHeadlessArguments(BrowserType.LaunchOptions options);

   /**
    * Applies default arguments to the launch options.
    *
    * @param options The launch options to modify.
    */
   void applyDefaultArguments(BrowserType.LaunchOptions options);

   /**
    * Launches the browser using the provided launch options.
    *
    * @param playwright The Playwright instance.
    * @param options    The launch options.
    * @return The launched {@link Browser} instance.
    */
   Browser launch(Playwright playwright, BrowserType.LaunchOptions options);

}
