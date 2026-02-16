package io.cyborgcode.roa.ui.playwright.session;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import lombok.Builder;
import lombok.Getter;

/**
 * Encapsulates a complete Playwright browser session.
 *
 * <p>This model class holds references to the core Playwright objects:
 * {@link Playwright}, {@link Browser}, {@link BrowserContext}, and {@link Page}.
 * It serves as the central object for managing a browser session lifecycle.
 *
 * <p>The session is typically created by the {@code UISessionFactory} and provided
 * to services that need to interact with the browser.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@Getter
@Builder
public class UISession {

   /**
    * The Playwright instance managing the browser engine.
    */
   private final Playwright playwright;

   /**
    * The browser instance (e.g., Chromium, Firefox, WebKit).
    */
   private final Browser browser;

   /**
    * The browser context providing an isolated session (cookies, storage, etc.).
    */
   private final BrowserContext browserContext;

   /**
    * The page instance representing a single browser tab.
    */
   private final Page page;

   /**
    * Closes the entire session by closing the browser context, browser, and Playwright instance.
    *
    * <p>Resources are closed in reverse order of creation to ensure proper cleanup.
    */
   public void close() {
      if (browserContext != null) {
         browserContext.close();
      }
      if (browser != null) {
         browser.close();
      }
      if (playwright != null) {
         playwright.close();
      }
   }

}
