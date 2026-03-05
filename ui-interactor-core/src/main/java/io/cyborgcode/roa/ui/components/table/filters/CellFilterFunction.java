package io.cyborgcode.roa.ui.components.table.filters;

import io.cyborgcode.roa.ui.components.base.BaseUiElement;
import io.cyborgcode.roa.ui.util.TriConsumer;

/**
 * Functional interface for applying filtering logic to table cells.
 * Implementations of this interface define how filtering is performed
 * on specific table cells using a provided {@link FilterStrategy}.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@FunctionalInterface
public interface CellFilterFunction<E extends BaseUiElement> extends TriConsumer<E, FilterStrategy, String[]> {

   /**
    * Applies a filtering function to a table cell.
    *
    * @param cellElement    The {@link E} representing the table cell.
    * @param filterStrategy The filtering strategy to be applied.
    * @param values         The values used for filtering.
    */
   void cellFilterFunction(E cellElement, FilterStrategy filterStrategy, String... values);

   @Override
   default void accept(E element, FilterStrategy filterStrategy, String[] objects) {
      cellFilterFunction(element, filterStrategy, objects);
   }
}
