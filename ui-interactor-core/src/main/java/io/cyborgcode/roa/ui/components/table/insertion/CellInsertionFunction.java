package io.cyborgcode.roa.ui.components.table.insertion;

import java.util.function.BiConsumer;

/**
 * Functional interface for inserting values into a table cell.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@FunctionalInterface
public interface CellInsertionFunction<C> extends BiConsumer<C, String[]> {

   /**
    * Performs the insertion of values into the specified table cell.
    *
    * @param cellElement The {@link C} representing the target cell.
    * @param values      The values to be inserted into the cell.
    */
   void cellInsertionFunction(C cellElement, String... values);

   @Override
   default void accept(C locator, String[] objects) {
      cellInsertionFunction(locator, objects);
   }
}
