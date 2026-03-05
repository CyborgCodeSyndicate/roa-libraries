package io.cyborgcode.roa.ui.components.table.insertion;

import io.cyborgcode.roa.ui.components.base.BaseUiElement;
import java.util.function.BiConsumer;

/**
 * Functional interface for inserting values into a table cell.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@FunctionalInterface
public interface CellInsertionFunction<E extends BaseUiElement> extends BiConsumer<E, String[]> {

   /**
    * Performs the insertion of values into the specified table cell.
    *
    * @param cellElement The {@link E} representing the target cell.
    * @param values      The values to be inserted into the cell.
    */
   void cellInsertionFunction(E cellElement, String... values);

   @Override
   default void accept(E element, String[] objects) {
      cellInsertionFunction(element, objects);
   }
}
