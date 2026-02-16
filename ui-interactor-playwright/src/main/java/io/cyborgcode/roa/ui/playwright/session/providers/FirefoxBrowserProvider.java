package io.cyborgcode.roa.ui.playwright.session.providers;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;
import io.cyborgcode.roa.ui.playwright.session.base.BrowserProvider;

/**
 * Provides a Playwright browser implementation for Firefox.
 *
 * <p>This class implements {@link BrowserProvider} and provides methods to configure
 * and launch Firefox browsers using Playwright.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public class FirefoxBrowserProvider implements BrowserProvider {

   /**
    * {@inheritDoc}
    */
   @Override
   public String getBrowserType() {
      return "FIREFOX";
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public BrowserType getBrowserTypeInstance(final Playwright playwright) {
      return playwright.firefox();
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
      // Firefox does not require additional default arguments
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public Browser launch(final Playwright playwright, final BrowserType.LaunchOptions options) {
      return getBrowserTypeInstance(playwright).launch(options);
   }

}
