package io.cyborgcode.roa.ui.components.table.filters;

import io.cyborgcode.roa.ui.components.base.ComponentType;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Represents a filtering component associated with a table cell.
 * This class defines the type of UI component used for filtering
 * and the specific filter type to be applied.
 *
 * <p>It is used in conjunction with {@code CellLocator} to specify
 * filtering behaviors within a table structure.
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
