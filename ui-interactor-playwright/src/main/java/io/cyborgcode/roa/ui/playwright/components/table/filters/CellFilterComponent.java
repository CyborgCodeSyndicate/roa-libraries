package io.cyborgcode.roa.ui.playwright.components.table.filters;

import io.cyborgcode.roa.ui.playwright.components.base.ComponentType;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Represents a filtering component associated with a table cell.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@AllArgsConstructor
@Getter
public class CellFilterComponent {

   /**
    * The type of UI component used for filtering.
    */
   private Class<? extends ComponentType> type;

   /**
    * The specific component type as a string representation.
    */
   private String componentType;
}
