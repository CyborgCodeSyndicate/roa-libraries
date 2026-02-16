package io.cyborgcode.roa.ui.playwright.components.alert;

import com.microsoft.playwright.Locator;

/**
 * Defines operations for interacting with alert UI elements.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface Alert {

   String getMessage(Locator container);

   String getMessage(String alertLabel);

   String getMessageBySelector(String alertSelector);

   boolean isVisible(Locator container);

   boolean isVisible(String alertLabel);

   boolean isVisibleBySelector(String alertSelector);

   void dismiss(Locator container);

   void dismiss(String alertLabel);

   void dismissBySelector(String alertSelector);

   String getType(Locator container);

   String getType(String alertLabel);

   String getTypeBySelector(String alertSelector);
}
