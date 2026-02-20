package io.cyborgcode.roa.ui.components.table.filters;

import io.cyborgcode.roa.ui.components.base.ComponentType;
import io.cyborgcode.roa.ui.components.table.filters.FilterStrategy;

/**
 * Defines a contract for filtering table cells using various filter strategies.
 *
 * @param <C> The container/element type (e.g., Playwright's {@code Locator} or Selenium's {@code WebElement}).
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface TableFilter<C> {

   /**
    * Applies a filter to a specific table cell using a defined component type and strategy.
    *
    * @param cellElement    The table cell element to filter.
    * @param componentType  The component type associated with the filtering process.
    * @param filterStrategy The strategy to apply for filtering.
    * @param values         The values to use for filtering.
    */
   void tableFilter(C cellElement, ComponentType componentType, FilterStrategy filterStrategy,
                    String... values);
}
