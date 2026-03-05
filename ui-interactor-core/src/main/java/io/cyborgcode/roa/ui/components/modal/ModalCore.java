package io.cyborgcode.roa.ui.components.modal;

import io.cyborgcode.roa.ui.components.base.BaseUiElement;

/**
 * Defines operations for interacting with modal UI elements.
 *
 * @param <E> The container/element type (e.g., Playwright's {@code Locator} or Selenium's {@code WebElement}).
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface ModalCore<E extends BaseUiElement, L> {

   boolean isOpened();

   void clickButton(E container, String buttonText);

   void clickButton(String buttonText);

   String getTitle();

   String getBodyText();

   String getContentTitle();

   void close();

   void clickButton(L buttonLocator);

}
