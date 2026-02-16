package io.cyborgcode.roa.ui.playwright.components.loader;

import com.microsoft.playwright.Locator;

/**
 * Defines operations for interacting with loader/spinner UI elements.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface Loader {

   void waitUntilLoaded(Locator container);

   void waitUntilLoaded(Locator container, int seconds);

   void waitUntilLoaded(String loaderLabel);

   void waitUntilLoaded(String loaderLabel, int seconds);

   void waitUntilLoadedBySelector(String loaderSelector);

   void waitUntilLoadedBySelector(String loaderSelector, int seconds);

   boolean isLoading(Locator container);

   boolean isLoading(String loaderLabel);

   boolean isLoadingBySelector(String loaderSelector);
}
