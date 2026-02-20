package io.cyborgcode.roa.ui.components.table.model;

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
public class TableLocators<V> {

   /**
    * Selector for the table container element.
    */
   private V tableContainerLocator;

   /**
    * Selector for identifying all rows within the table.
    */
   private V tableRowsLocator;

   /**
    * Selector for the table's header row.
    */
   private V headerRowLocator;
}
