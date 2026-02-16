package io.cyborgcode.roa.ui.playwright.components.table.insertion;

import io.cyborgcode.roa.ui.playwright.components.base.ComponentType;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Represents a configuration for inserting values into a table cell.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@AllArgsConstructor
@Getter
public class CellInsertionComponent {

   /**
    * The type of component used for insertion.
    */
   private Class<? extends ComponentType> type;

   /**
    * The specific component type identifier.
    */
   private String componentType;

   /**
    * The execution order for insertion, determining priority when multiple insertions occur.
    */
   int order;
}
