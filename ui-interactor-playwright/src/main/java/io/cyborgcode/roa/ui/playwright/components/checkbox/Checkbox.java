package io.cyborgcode.roa.ui.playwright.components.checkbox;

import com.microsoft.playwright.Locator;

/**
 * Defines operations for interacting with checkbox UI elements.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface Checkbox {

   void check(Locator container, String checkboxLabel);

   void check(Locator container);

   void check(String checkboxLabel);

   void checkBySelector(String checkboxSelector);

   void uncheck(Locator container, String checkboxLabel);

   void uncheck(Locator container);

   void uncheck(String checkboxLabel);

   void uncheckBySelector(String checkboxSelector);

   boolean isChecked(Locator container, String checkboxLabel);

   boolean isChecked(Locator container);

   boolean isChecked(String checkboxLabel);

   boolean isCheckedBySelector(String checkboxSelector);

   boolean isEnabled(Locator container, String checkboxLabel);

   boolean isEnabled(Locator container);

   boolean isEnabled(String checkboxLabel);

   boolean isEnabledBySelector(String checkboxSelector);

   default void tableInsertion(Locator cell, String... values) {
   }
}
