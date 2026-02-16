package io.cyborgcode.roa.ui.playwright.components.modal;

import com.microsoft.playwright.Locator;

/**
 * Defines operations for interacting with modal UI elements.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface Modal {

   boolean isOpen(Locator container);

   boolean isOpen(String modalLabel);

   boolean isOpenBySelector(String modalSelector);

   void close(Locator container);

   void close(String modalLabel);

   void closeBySelector(String modalSelector);

   String getTitle(Locator container);

   String getTitle(String modalLabel);

   String getTitleBySelector(String modalSelector);

   String getContent(Locator container);

   String getContent(String modalLabel);

   String getContentBySelector(String modalSelector);

   void confirm(Locator container);

   void confirm(String modalLabel);

   void confirmBySelector(String modalSelector);
}
