package io.cyborgcode.roa.ui.playwright.components.table.insertion;

import com.microsoft.playwright.Locator;
import java.util.function.BiConsumer;

/**
 * Functional interface for inserting values into a table cell.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@FunctionalInterface
public interface CellInsertionFunction extends BiConsumer<Locator, String[]> {

   /**
    * Performs the insertion of values into the specified table cell.
    *
    * @param cellElement The {@link Locator} representing the target cell.
    * @param values      The values to be inserted into the cell.
    */
   void cellInsertionFunction(Locator cellElement, String... values);

   @Override
   default void accept(Locator locator, String[] objects) {
      cellInsertionFunction(locator, objects);
   }
}
