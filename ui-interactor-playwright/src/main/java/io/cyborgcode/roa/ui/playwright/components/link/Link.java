package io.cyborgcode.roa.ui.playwright.components.link;

import com.microsoft.playwright.Locator;

/**
 * Defines operations for interacting with link UI elements.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface Link {

   void click(Locator container, String linkText);

   void click(Locator container);

   void click(String linkText);

   void clickBySelector(String linkSelector);

   String getHref(Locator container, String linkText);

   String getHref(Locator container);

   String getHref(String linkText);

   String getHrefBySelector(String linkSelector);

   boolean isVisible(Locator container, String linkText);

   boolean isVisible(Locator container);

   boolean isVisible(String linkText);

   boolean isVisibleBySelector(String linkSelector);

   boolean isEnabled(Locator container, String linkText);

   boolean isEnabled(Locator container);

   boolean isEnabled(String linkText);

   boolean isEnabledBySelector(String linkSelector);
}
