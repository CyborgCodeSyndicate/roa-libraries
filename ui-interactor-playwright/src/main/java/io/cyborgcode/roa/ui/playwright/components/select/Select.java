package io.cyborgcode.roa.ui.playwright.components.select;

import com.microsoft.playwright.Locator;

/**
 * Defines operations for interacting with select (dropdown) UI elements.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface Select {

   void select(Locator container, String optionText);

   void select(Locator container, String selectLabel, String optionText);

   void select(String selectLabel, String optionText);

   void selectBySelector(String selectSelector, String optionText);

   String getSelectedValue(Locator container);

   String getSelectedValue(Locator container, String selectLabel);

   String getSelectedValue(String selectLabel);

   String getSelectedValueBySelector(String selectSelector);

   boolean isEnabled(Locator container, String selectLabel);

   boolean isEnabled(Locator container);

   boolean isEnabled(String selectLabel);

   boolean isEnabledBySelector(String selectSelector);

   default void tableInsertion(Locator cell, String... values) {
   }
}
