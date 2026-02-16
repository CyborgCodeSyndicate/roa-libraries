package io.cyborgcode.roa.ui.playwright.components.table.filters;

import io.cyborgcode.roa.ui.playwright.components.base.ComponentType;
import com.microsoft.playwright.Locator;

/**
 * Defines a contract for filtering table cells using various filter strategies.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface TableFilter {

   /**
    * Applies a filter to a specific table cell using a defined component type and strategy.
    *
    * @param cellElement    The table cell element to filter.
    * @param componentType  The component type associated with the filtering process.
    * @param filterStrategy The strategy to apply for filtering.
    * @param values         The values to use for filtering.
    */
   void tableFilter(Locator cellElement, ComponentType componentType, FilterStrategy filterStrategy,
                    String... values);
}
