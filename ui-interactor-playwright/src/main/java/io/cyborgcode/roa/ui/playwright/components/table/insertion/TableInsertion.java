package io.cyborgcode.roa.ui.playwright.components.table.insertion;

import io.cyborgcode.roa.ui.playwright.components.base.ComponentType;
import com.microsoft.playwright.Locator;

/**
 * Defines the contract for inserting values into table cells.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface TableInsertion {

   /**
    * Inserts values into the specified table cell.
    *
    * @param cellElement   The {@link Locator} representing the target cell.
    * @param componentType The {@link ComponentType} defining the type of UI component used for insertion.
    * @param values        The values to be inserted into the cell.
    */
   void tableInsertion(Locator cellElement, ComponentType componentType, String... values);
}
