package io.cyborgcode.roa.ui.components.link;

import io.cyborgcode.roa.ui.components.base.BaseUiElement;
import io.cyborgcode.roa.ui.components.button.ButtonCore;

/**
 * Defines operations for interacting with link UI elements.
 *
 * @param <E> The container/element type (e.g., Playwright's {@code Locator} or Selenium's {@code WebElement}).
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface LinkCore<E extends BaseUiElement, L> extends ButtonCore<E, L> {

   void doubleClick(E container, String linkText);

   void doubleClick(E container);

   void doubleClick(String linkText);

   void doubleClick(L linkSelector);

}
