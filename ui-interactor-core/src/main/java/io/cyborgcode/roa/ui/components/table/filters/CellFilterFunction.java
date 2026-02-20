package io.cyborgcode.roa.ui.components.table.filters;

import io.cyborgcode.roa.ui.util.TriConsumer;

/**
 * Functional interface for applying filtering logic to table cells.
 * Implementations of this interface define how filtering is performed
 * on specific table cells using a provided {@link FilterStrategy}.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@FunctionalInterface
public interface CellFilterFunction<C> extends TriConsumer<C, FilterStrategy, String[]> {

   /**
    * Applies a filtering function to a table cell.
    *
    * @param cellElement    The {@link C} representing the table cell.
    * @param filterStrategy The filtering strategy to be applied.
    * @param values         The values used for filtering.
    */
   void cellFilterFunction(C cellElement, FilterStrategy filterStrategy, String... values);

   @Override
   default void accept(C locator, FilterStrategy filterStrategy, String[] objects) {
      cellFilterFunction(locator, filterStrategy, objects);
   }
}
