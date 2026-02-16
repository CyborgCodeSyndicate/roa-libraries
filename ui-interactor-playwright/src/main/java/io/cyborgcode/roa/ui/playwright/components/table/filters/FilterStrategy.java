package io.cyborgcode.roa.ui.playwright.components.table.filters;

/**
 * Defines filtering strategies for table elements.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public enum FilterStrategy {

   /**
    * Selects only the specified elements, deselecting others.
    */
   SELECT_ONLY,

   /**
    * Selects the specified elements without affecting others.
    */
   SELECT,

   /**
    * Selects all available elements.
    */
   SELECT_ALL,

   /**
    * Unselects the specified elements without affecting others.
    */
   UNSELECT,

   /**
    * Unselects all elements.
    */
   UNSELECT_ALL
}
