package io.cyborgcode.roa.ui.playwright.validator;

//import com.microsoft.playwright.Locator;

import com.microsoft.playwright.Locator;
import io.cyborgcode.roa.ui.validator.TableAssertionFunctionsServiceCore;

/**
 * Provides a set of validation functions for asserting table data integrity.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
//todo we can remove this class and only work with TableAssertionFunctionsServiceCore
public class TableAssertionFunctions {

   private static final TableAssertionFunctionsServiceCore<Locator> instance = initialInstance();

   private static TableAssertionFunctionsServiceCore<Locator> initialInstance() {
      return new TableAssertionFunctionsServiceCore<>(Locator.class) {
         @Override
         protected boolean cellIsVisible(Locator cell) {
            return cell.isVisible();
         }

         @Override
         protected boolean cellIsEnabled(Locator cell) {
            return cell.isEnabled();
         }
      };
   }

   private TableAssertionFunctions() {
   }

   /**
    * Validates if a table is not empty.
    *
    * @param actual   The table data (expected to be a {@code List}).
    * @param expected The expected result (a {@code Boolean}).
    * @return {@code true} if the table is not empty and matches the expected state; otherwise, {@code false}.
    */
   public static boolean validateTableNotEmpty(Object actual, Object expected) {
      return instance.validateTableNotEmpty(actual, expected);
   }

   /**
    * Validates the row count of a table.
    *
    * @param actual   The table data (expected to be a {@code List}).
    * @param expected The expected row count (an {@code Integer}).
    * @return {@code true} if the table has the expected number of rows; otherwise, {@code false}.
    */
   public static boolean validateTableRowCount(Object actual, Object expected) {
      return instance.validateTableRowCount(actual, expected);
   }

   /**
    * Validates if every row in the table contains the expected values.
    *
    * @param actual   The table data (expected to be a {@code List} of rows).
    * @param expected The expected values (a {@code List} of items that should be present in each row).
    * @return {@code true} if every row contains all the expected values; otherwise, {@code false}.
    */
   public static boolean validateEveryRowContainsValues(Object actual, Object expected) {
      return instance.validateEveryRowContainsValues(actual, expected);
   }

   /**
    * Validates that a specific row is not present in the table.
    *
    * @param actual   The table data (expected to be a {@code List} of rows).
    * @param expected The row that should not be present (a {@code List} representing the row).
    * @return {@code true} if the row is absent; otherwise, {@code false}.
    */
   public static boolean validateTableDoesNotContainRow(Object actual, Object expected) {
      return instance.validateTableDoesNotContainRow(actual, expected);
   }

   /**
    * Validates that all rows in the table are unique.
    *
    * @param actual   The table data (expected to be a {@code List} of rows).
    * @param expected The expected uniqueness state (a {@code Boolean}).
    * @return {@code true} if all rows are unique and match the expected uniqueness state; otherwise, {@code false}.
    */
   public static boolean validateAllRowsAreUnique(Object actual, Object expected) {
      return instance.validateAllRowsAreUnique(actual, expected);
   }

   /**
    * Validates that there are no empty cells in the table.
    *
    * @param actual   The table data (expected to be a {@code List} of rows).
    * @param expected The expected state (a {@code Boolean}).
    * @return {@code true} if there are no empty cells and matches the expected state; otherwise, {@code false}.
    */
   public static boolean validateNoEmptyCells(Object actual, Object expected) {
      return instance.validateNoEmptyCells(actual, expected);
   }

   /**
    * Validates that a column contains only unique values.
    *
    * @param actual   The table data (expected to be a {@code List} of rows).
    * @param expected The column index (an {@code Integer}, 1-based index).
    * @return {@code true} if all values in the specified column are unique; otherwise, {@code false}.
    */
   public static boolean validateColumnValuesAreUnique(Object actual, Object expected) {
      return instance.validateColumnValuesAreUnique(actual, expected);
   }

   /**
    * Validates that the actual table data matches the expected table data.
    *
    * @param actual   The table data (expected to be a {@code List} of rows).
    * @param expected The expected table data (a {@code List} of rows).
    * @return {@code true} if both tables match exactly; otherwise, {@code false}.
    */
   public static boolean validateTableDataMatchesExpected(Object actual, Object expected) {
      return instance.validateTableDataMatchesExpected(actual, expected);
   }

   /**
    * Validates whether a row is not empty.
    *
    * @param actual   The row data (expected to be a {@code List}).
    * @param expected The expected state (a {@code Boolean}).
    * @return {@code true} if the row is non-empty and matches the expected state; otherwise, {@code false}.
    */
   public static boolean validateRowNotEmpty(Object actual, Object expected) {
      return instance.validateRowNotEmpty(actual, expected);
   }

   /**
    * Validates whether a row contains specific expected values.
    *
    * @param actual   The row data (expected to be a {@code List}).
    * @param expected The expected values (a {@code List}).
    * @return {@code true} if the row contains all the expected values; otherwise, {@code false}.
    */
   public static boolean validateRowContainsValues(Object actual, Object expected) {
      return instance.validateRowContainsValues(actual, expected);
   }

   /**
    * Validates that all table cells are enabled.
    *
    * @param actual   The table data (expected to be a {@code List} of rows containing {@link Locator}).
    * @param expected The expected enabled state (a {@code Boolean}).
    * @return {@code true} if all cells are enabled and match the expected state; otherwise, {@code false}.
    */
   public static boolean validateAllCellsEnabled(Object actual, Object expected) {
      return instance.validateAllCellsEnabled(actual, expected);
   }

   /**
    * Validates whether all cells in the table are clickable.
    *
    * @param actual   The table data (expected to be a {@code List} of lists).
    * @param expected The expected state (a {@code Boolean}).
    * @return {@code true} if all cells in the table are clickable and match the expected state; otherwise,
    * {@code false}.
    */
   public static boolean validateAllCellsClickable(Object actual, Object expected) {
      return instance.validateAllCellsClickable(actual, expected);
   }

}
