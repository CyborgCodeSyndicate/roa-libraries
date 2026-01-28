package io.cyborgcode.roa.ui.components.table.filters;

import io.cyborgcode.roa.ui.components.table.model.CellLocator;
import io.cyborgcode.roa.ui.components.table.service.TableImpl;
import io.cyborgcode.roa.ui.selenium.smart.SmartWebElement;
import io.cyborgcode.roa.ui.util.TriConsumer;

/**
 * Functional interface for applying filtering logic to table cells.
 * Implementations of this interface define how filtering is performed
 * on specific table cells using a provided {@link FilterStrategy}.
 *
 * <p>This interface extends {@code TriConsumer} to allow functional-style
 * execution of filtering operations.
 *
 * <p>Used in {@link TableImpl} and
 * {@link CellLocator} for dynamic
 * table filtering.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@FunctionalInterface
public interface CellFilterFunction extends TriConsumer<SmartWebElement, FilterStrategy, String[]> {

   /**
    * Applies a filtering function to a table cell.
    *
    * @param cellElement    The {@link SmartWebElement} representing the table cell.
    * @param filterStrategy The filtering strategy to be applied.
    * @param values         The values used for filtering.
    */
   void cellFilterFunction(SmartWebElement cellElement, FilterStrategy filterStrategy, String... values);

   /**
    * Default implementation of {@code accept} that delegates to {@link #cellFilterFunction}.
    *
    * @param smartWebElement The table cell element.
    * @param filterStrategy  The filtering strategy.
    * @param objects         The filtering values.
    */
   @Override
   default void accept(SmartWebElement smartWebElement, FilterStrategy filterStrategy, String[] objects) {
      cellFilterFunction(smartWebElement, filterStrategy, objects);
   }
}
