package io.cyborgcode.roa.ui.playwright.session.providers;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;
import io.cyborgcode.roa.ui.playwright.session.base.BrowserProvider;

/**
 * Provides a Playwright browser implementation for Chromium-based browsers.
 *
 * <p>This class implements {@link BrowserProvider} and provides methods to configure
 * and launch Chromium-based browsers, including Chrome and Edge channels.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public class ChromiumBrowserProvider implements BrowserProvider {

   /**
    * {@inheritDoc}
    */
   @Override
   public String getBrowserType() {
      return "CHROMIUM";
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public BrowserType getBrowserTypeInstance(final Playwright playwright) {
      return playwright.chromium();
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public BrowserType.LaunchOptions createDefaultLaunchOptions() {
      return new BrowserType.LaunchOptions();
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public void applyHeadlessArguments(final BrowserType.LaunchOptions options) {
      options.setHeadless(true);
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public void applyDefaultArguments(final BrowserType.LaunchOptions options) {
      options.setArgs(java.util.List.of(
            "--disable-gpu",
            "--no-sandbox",
            "--disable-dev-shm-usage"
      ));
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public Browser launch(final Playwright playwright, final BrowserType.LaunchOptions options) {
      return getBrowserTypeInstance(playwright).launch(options);
   }

}
