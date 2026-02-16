package io.cyborgcode.roa.ui.playwright.components.table.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Stores locator information for identifying key table elements in the UI.
 *
 * <p>In the Playwright module, locators are CSS/XPath selector strings
 * instead of Selenium's {@code By} objects.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@AllArgsConstructor
@Getter
public class TableLocators {

   /**
    * Selector for the table container element.
    */
   private String tableContainerLocator;

   /**
    * Selector for identifying all rows within the table.
    */
   private String tableRowsLocator;

   /**
    * Selector for the table's header row.
    */
   private String headerRowLocator;
}
