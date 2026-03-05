package io.cyborgcode.roa.ui.components.loader;

import io.cyborgcode.roa.ui.components.base.BaseUiElement;

/**
 * Defines operations for interacting with loader/spinner UI elements.
 *
 * @param <E> The container/element type (e.g., Playwright's {@code Locator} or Selenium's {@code WebElement}).
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface LoaderCore<E extends BaseUiElement, L> {

   boolean isVisible(E container);

   void waitToBeShown(E container, int secondsShown);

   void waitToBeShown(int secondsShown);

   void waitToBeRemoved(E container, int secondsRemoved);

   void waitToBeRemoved(int secondsRemoved);

   boolean isVisible(L loaderLocator);

   void waitToBeShown(L loaderLocator, int secondsShown);

   void waitToBeRemoved(L loaderLocator, int secondsRemoved);

}
