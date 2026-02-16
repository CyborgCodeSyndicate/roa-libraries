package io.cyborgcode.roa.ui.playwright.components.tab;

import com.microsoft.playwright.Locator;

/**
 * Defines operations for interacting with tab UI elements.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface Tab {

   void select(Locator container, String tabLabel);

   void select(Locator container);

   void select(String tabLabel);

   void selectBySelector(String tabSelector);

   boolean isSelected(Locator container, String tabLabel);

   boolean isSelected(Locator container);

   boolean isSelected(String tabLabel);

   boolean isSelectedBySelector(String tabSelector);

   boolean isEnabled(Locator container, String tabLabel);

   boolean isEnabled(Locator container);

   boolean isEnabled(String tabLabel);

   boolean isEnabledBySelector(String tabSelector);
}
