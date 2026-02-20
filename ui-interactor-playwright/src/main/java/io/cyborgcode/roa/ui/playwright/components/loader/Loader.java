package io.cyborgcode.roa.ui.playwright.components.loader;

import com.microsoft.playwright.Locator;
import io.cyborgcode.roa.ui.components.loader.LoaderCore;
import io.cyborgcode.roa.ui.playwright.base.PwBy;

/**
 * Playwright-specific loader component interface.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface Loader extends LoaderCore<Locator> {

   boolean isVisible(PwBy loaderLocator);

   void waitToBeShown(PwBy loaderLocator, int secondsShown);

   void waitToBeRemoved(PwBy loaderLocator, int secondsRemoved);

}
