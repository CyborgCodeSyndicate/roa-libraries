package io.cyborgcode.roa.ui.playwright.components.toggle;

import com.microsoft.playwright.Locator;

/**
 * Defines operations for interacting with toggle UI elements.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface Toggle {

   void toggle(Locator container, String toggleLabel);

   void toggle(Locator container);

   void toggle(String toggleLabel);

   void toggleBySelector(String toggleSelector);

   boolean isOn(Locator container, String toggleLabel);

   boolean isOn(Locator container);

   boolean isOn(String toggleLabel);

   boolean isOnBySelector(String toggleSelector);

   boolean isEnabled(Locator container, String toggleLabel);

   boolean isEnabled(Locator container);

   boolean isEnabled(String toggleLabel);

   boolean isEnabledBySelector(String toggleSelector);

   default void tableInsertion(Locator cell, String... values) {
   }
}
