package io.cyborgcode.roa.ui.components.table.insertion;

import io.cyborgcode.roa.ui.components.base.BaseUiElement;
import io.cyborgcode.roa.ui.components.base.ComponentType;

/**
 * Defines the contract for inserting values into table cells.
 *
 * @param <E> The container/element type (e.g., Playwright's {@code Locator} or Selenium's {@code WebElement}).
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface TableInsertion<E extends BaseUiElement> {

   /**
    * Inserts values into the specified table cell.
    *
    * @param cellElement   The element representing the target cell.
    * @param componentType The {@link ComponentType} defining the type of UI component used for insertion.
    * @param values        The values to be inserted into the cell.
    */
   void tableInsertion(E cellElement, ComponentType componentType, String... values);
}
