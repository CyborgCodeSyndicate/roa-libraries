package io.cyborgcode.roa.ui.components.alert;

import io.cyborgcode.roa.ui.components.base.BaseUiElement;

/**
 * Defines operations for interacting with alert UI elements.
 *
 * @param <E> The container/element type (e.g., Playwright's {@code Locator} or Selenium's {@code WebElement}).
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface AlertCore<E extends BaseUiElement, L> {

   String getValue(E container);

   boolean isVisible(E container);

   String getValue(L containerLocator);

   boolean isVisible(L containerLocator);

}
