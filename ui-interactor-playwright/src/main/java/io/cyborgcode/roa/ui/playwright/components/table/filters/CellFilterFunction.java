package io.cyborgcode.roa.ui.playwright.components.table.filters;

import com.microsoft.playwright.Locator;
import io.cyborgcode.roa.ui.playwright.util.TriConsumer;

/**
 * Functional interface for applying filtering logic to table cells.
 * Implementations of this interface define how filtering is performed
 * on specific table cells using a provided {@link FilterStrategy}.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@FunctionalInterface
public interface CellFilterFunction extends TriConsumer<Locator, FilterStrategy, String[]> {

   /**
    * Applies a filtering function to a table cell.
    *
    * @param cellElement    The {@link Locator} representing the table cell.
    * @param filterStrategy The filtering strategy to be applied.
    * @param values         The values used for filtering.
    */
   void cellFilterFunction(Locator cellElement, FilterStrategy filterStrategy, String... values);

   @Override
   default void accept(Locator locator, FilterStrategy filterStrategy, String[] objects) {
      cellFilterFunction(locator, filterStrategy, objects);
   }
}
