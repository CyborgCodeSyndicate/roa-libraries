package io.cyborgcode.roa.ui.components.toggle;

import io.cyborgcode.roa.ui.components.base.BaseUiElement;

/**
 * Defines operations for interacting with toggle UI elements.
 *
 * @param <E> The container/element type (e.g., Playwright's {@code Locator} or Selenium's {@code WebElement}).
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface ToggleCore<E extends BaseUiElement, L> {

   void activate(E container, String toggleText);

   void activate(String toggleText);

   void deactivate(E container, String toggleText);

   void deactivate(String toggleText);

   boolean isEnabled(E container, String toggleText);

   boolean isEnabled(String toggleText);

   boolean isActivated(E container, String toggleText);

   boolean isActivated(String toggleText);

   void activate(L toggleLocator);

   void deactivate(L toggleLocator);

   boolean isEnabled(L toggleLocator);

   boolean isActivated(L toggleLocator);

}
