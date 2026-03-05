package io.cyborgcode.roa.ui.components.tab;

import io.cyborgcode.roa.ui.components.base.BaseUiElement;
import io.cyborgcode.roa.ui.components.button.ButtonCore;

/**
 * Defines operations for interacting with tab UI elements.
 *
 * @param <E> The container/element type (e.g., Playwright's {@code Locator} or Selenium's {@code WebElement}).
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface TabCore<E extends BaseUiElement, L> extends ButtonCore<E, L> {

   boolean isSelected(E container, String buttonText);

   boolean isSelected(E container);

   boolean isSelected(String buttonText);

   boolean isSelected(L tabSelector);

}
