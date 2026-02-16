package io.cyborgcode.roa.ui.playwright.validator;

import io.cyborgcode.roa.validator.core.AssertionType;
import java.util.List;

/**
 * Enum representing different types of table assertions.
 *
 * <p>Each entry corresponds to a specific validation check that can be performed on a table.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public enum TableAssertionTypes implements AssertionType<TableAssertionTypes> {

   /**
    * Asserts that the table is not empty.
    */
   TABLE_NOT_EMPTY(List.class),

   /**
    * Asserts that the table contains the expected number of rows.
    */
   TABLE_ROW_COUNT(List.class),

   /**
    * Asserts that every row in the table contains specific expected values.
    */
   EVERY_ROW_CONTAINS_VALUES(List.class),

   /**
    * Asserts that the table does not contain a specific row.
    */
   TABLE_DOES_NOT_CONTAIN_ROW(List.class),

   /**
    * Asserts that all rows in the table are unique.
    */
   ALL_ROWS_ARE_UNIQUE(List.class),

   /**
    * Asserts that there are no empty cells in the table.
    */
   NO_EMPTY_CELLS(List.class),

   /**
    * Asserts that all values in a given column are unique.
    */
   COLUMN_VALUES_ARE_UNIQUE(List.class),

   /**
    * Asserts that the entire table data matches the expected data.
    */
   TABLE_DATA_MATCHES_EXPECTED(List.class),

   /**
    * Asserts that a given row is not empty.
    */
   ROW_NOT_EMPTY(List.class),

   /**
    * Asserts that a given row contains specific expected values.
    */
   ROW_CONTAINS_VALUES(List.class),

   /**
    * Asserts that all cells in the table are enabled.
    */
   ALL_CELLS_ENABLED(List.class),

   /**
    * Asserts that all cells in the table are clickable.
    */
   ALL_CELLS_CLICKABLE(List.class);

   private final Class<?> supportedType;

   <T> TableAssertionTypes(Class<T> supportedType) {
      this.supportedType = supportedType;
   }

   @Override
   public TableAssertionTypes type() {
      return this;
   }

   @Override
   public Class<?> getSupportedType() {
      return supportedType;
   }
}
