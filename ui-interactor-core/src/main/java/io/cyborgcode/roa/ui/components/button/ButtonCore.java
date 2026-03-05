package io.cyborgcode.roa.ui.components.button;

import io.cyborgcode.roa.ui.components.base.BaseUiElement;

/**
 * Represents a UI button component with various ways to locate and interact
 * with buttons in a web interface.
 *
 * @param <E> The container/element type (e.g., Playwright's {@code Locator} or Selenium's {@code WebElement}).
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface ButtonCore<E extends BaseUiElement, L> {

   void click(E container, String buttonText);

   void click(E container);

   void click(L buttonSelector);

   void click(String buttonText);


   boolean isEnabled(E container, String buttonText);

   boolean isEnabled(E container);

   boolean isEnabled(L buttonSelector);

   boolean isEnabled(String buttonText);


   boolean isVisible(E container, String buttonText);

   boolean isVisible(E container);

   boolean isVisible(L buttonSelector);

   boolean isVisible(String buttonText);

   default void clickElementInCell(E cell) {
   }
}
