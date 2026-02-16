package io.cyborgcode.roa.ui.playwright.components.radio;

import com.microsoft.playwright.Locator;

/**
 * Defines operations for interacting with radio button UI elements.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface Radio {

   void select(Locator container, String radioLabel);

   void select(Locator container);

   void select(String radioLabel);

   void selectBySelector(String radioSelector);

   boolean isSelected(Locator container, String radioLabel);

   boolean isSelected(Locator container);

   boolean isSelected(String radioLabel);

   boolean isSelectedBySelector(String radioSelector);

   boolean isEnabled(Locator container, String radioLabel);

   boolean isEnabled(Locator container);

   boolean isEnabled(String radioLabel);

   boolean isEnabledBySelector(String radioSelector);

   default void tableInsertion(Locator cell, String... values) {
   }
}
