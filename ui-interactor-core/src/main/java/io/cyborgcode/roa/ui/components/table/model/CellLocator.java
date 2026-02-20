package io.cyborgcode.roa.ui.components.table.model;

import io.cyborgcode.roa.ui.components.table.filters.CellFilterComponent;
import io.cyborgcode.roa.ui.components.table.filters.CellFilterFunction;
import io.cyborgcode.roa.ui.components.table.insertion.CellInsertionComponent;
import io.cyborgcode.roa.ui.components.table.insertion.CellInsertionFunction;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents the location and behavior of a table cell within a UI table structure.
 *
 * <p>In the Playwright module, locators are CSS/XPath selector strings
 * instead of Selenium's {@code By} objects.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CellLocator<T, V> {

   /**
    * The field name associated with this cell.
    */
   private String fieldName;

   /**
    * The selector used to find the table cell.
    */
   private T locator;

   /**
    * The selector used to extract the text content of the table cell.
    */
   private T cellTextLocator;

   /**
    * The selector used to identify the corresponding header cell.
    */
   private T headerCellLocator;

   /**
    * Indicates whether this cell represents a collection of elements.
    */
   private boolean collection;

   /**
    * The table section where this cell is located.
    */
   private String tableSection;

   /**
    * The component responsible for inserting values into this cell.
    */
   private CellInsertionComponent cellInsertionComponent;

   /**
    * The custom function used for inserting values into the cell.
    */
   private Class<? extends CellInsertionFunction<V>> customCellInsertion;

   /**
    * The component responsible for filtering values in this cell.
    */
   private CellFilterComponent cellFilterComponent;

   /**
    * The custom function used for filtering values in this cell.
    */
   private Class<? extends CellFilterFunction<V>> customCellFilter;
}
