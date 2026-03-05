package io.cyborgcode.roa.ui.service.tables;


import io.cyborgcode.roa.framework.decorators.DecoratorsFactory;
import io.cyborgcode.roa.framework.storage.Storage;
import io.cyborgcode.roa.ui.components.base.BaseUiElement;
import io.cyborgcode.roa.ui.components.table.base.TableField;
import io.cyborgcode.roa.ui.components.table.filters.FilterStrategy;
import io.cyborgcode.roa.ui.components.table.service.TableService;
import io.cyborgcode.roa.ui.components.table.sort.SortingStrategy;
import io.cyborgcode.roa.ui.service.fluent.SuperUiServiceFluentCore;
import io.cyborgcode.roa.ui.service.fluent.UiServiceFluentCore;
import io.cyborgcode.roa.validator.core.Assertion;
import io.qameta.allure.Allure;
import java.util.Arrays;
import java.util.List;

import static io.cyborgcode.roa.ui.storage.StorageKeysUi.UI;

/**
 * Provides fluent methods for interacting with table components in the UI.
 *
 * <p>This class offers functionality for reading, inserting, filtering, and sorting table data,
 * as well as validating table contents.
 *
 * @param <T> The type of {@link UiServiceFluentCore} for chaining fluent methods.
 * @author Cyborg Code Syndicate 💍👨💻
 */
public class TableServiceFluentCore<T extends UiServiceFluentCore<L, D, E, ?>, L, D, E extends BaseUiElement> {

   private static final String UI_TABLE_CLICKING_ELEMENT_IN_CELL_AT_ROW =
         "[UI - Table] Clicking element in cell at row: ";
   private static final String FIELD = ", field: ";
   private static final String INDEX = ", index: ";
   private static final String USING_DATA = " using data: ";
   private static final String UI_TABLE_CLICKING_ELEMENT_IN_CELL_FOR_SEARCH_CRITERIA =
         "[UI - Table] Clicking element in cell for search criteria: ";

   private final TableService tableService;
   private final T uiServiceFluent;
   private final Storage storage;
   private final D driver;
   private final DecoratorsFactory decoratorsFactory = new DecoratorsFactory();

   /**
    * Constructs a new {@code TableServiceFluent} instance.
    *
    * @param uiServiceFluent The UI service instance for fluent chaining.
    * @param storage         The storage instance for persisting table data.
    * @param tableService    The table service handling table interactions.
    * @param driver          The smart WebDriver instance.
    */
   public TableServiceFluentCore(T uiServiceFluent, Storage storage, TableService tableService,
                                 D driver) {
      this.tableService = tableService;
      this.uiServiceFluent = uiServiceFluent;
      this.storage = storage;
      this.driver = driver;
   }

   /**
    * Reads the entire table and stores the result in storage.
    *
    * @param <K>              The type representing table rows.
    * @param tableElementCore The table element to be read.
    * @return The fluent UI service instance.
    */
   public final <K> T readTable(TableElementCore<?, D> tableElementCore) {
      Allure.step("[UI - Table] Reading the table: " + tableElementCore);
      tableElementCore.before().accept(driver);
      List<K> rows = tableService.readTable(tableElementCore.tableType(), tableElementCore.rowsRepresentationClass());
      tableElementCore.after().accept(driver);
      storage.sub(UI).put(tableElementCore.enumImpl(), rows);
      return uiServiceFluent;
   }

   /**
    * Reads specific fields from the table and stores the result in storage.
    *
    * @param <K>              The type representing table rows.
    * @param tableElementCore The table element to be read.
    * @param fields           The specific fields to read from the table.
    * @return The fluent UI service instance.
    */
   @SafeVarargs
   public final <K> T readTable(TableElementCore<?, D> tableElementCore, TableField<K>... fields) {
      Allure.step("[UI - Table] Reading the table with specific fields: " + tableElementCore);
      validateArguments(fields[0], tableElementCore.rowsRepresentationClass());
      tableElementCore.before().accept(driver);
      tableElementCore.after().accept(driver);
      storage.sub(UI).put(tableElementCore.enumImpl(),
            tableService.readTable(tableElementCore.tableType(), tableElementCore.rowsRepresentationClass(), fields));
      return uiServiceFluent;
   }

   /**
    * Reads a range of rows from the table and stores the result in storage.
    *
    * @param tableElementCore The table element to be read.
    * @param start            The starting row index (inclusive).
    * @param end              The ending row index (exclusive).
    * @return The fluent UI service instance.
    */
   public final T readTable(TableElementCore<?, D> tableElementCore, int start, int end) {
      Allure.step("[UI - Table] Reading a range of rows from the table: " + tableElementCore + " from "
            + start + " to " + end);
      tableElementCore.before().accept(driver);
      tableElementCore.after().accept(driver);
      storage.sub(UI).put(tableElementCore.enumImpl(), tableService.readTable(tableElementCore.tableType(), start, end,
            tableElementCore.rowsRepresentationClass()));
      return uiServiceFluent;
   }

   /**
    * Reads a specific range of rows from the table with only the specified fields and stores the result in storage.
    *
    * @param <K>              The type representing table rows.
    * @param tableElementCore The table element to be read.
    * @param start            The starting row index (inclusive).
    * @param end              The ending row index (exclusive).
    * @param fields           The fields to be extracted from the table. If not provided, all fields are retrieved.
    * @return The fluent UI service instance for method chaining.
    */
   @SafeVarargs
   public final <K> T readTable(TableElementCore<?, D> tableElementCore, int start, int end,
                                TableField<K>... fields) {
      Allure.step("[UI - Table] Reading a range of rows with specific fields from the table: " + tableElementCore
            + " from " + start + " to " + end);
      validateArguments(fields[0], tableElementCore.rowsRepresentationClass());
      tableElementCore.before().accept(driver);
      tableElementCore.after().accept(driver);
      storage.sub(UI).put(tableElementCore.enumImpl(), tableService.readTable(tableElementCore.tableType(), start, end,
            tableElementCore.rowsRepresentationClass(), fields));
      return uiServiceFluent;
   }

   /**
    * Reads a specific row from the table and stores the result in storage.
    *
    * @param tableElementCore The table element to be read.
    * @param row              The index of the row to read.
    * @return The fluent UI service instance.
    */
   public final T readRow(TableElementCore<?, D> tableElementCore, int row) {
      Allure.step("[UI - Table] Reading a specific row from the table: " + tableElementCore + " at row index: " + row);
      tableElementCore.before().accept(driver);
      tableElementCore.after().accept(driver);
      storage.sub(UI).put(tableElementCore.enumImpl(), tableService.readRow(tableElementCore.tableType(), row,
            tableElementCore.rowsRepresentationClass()));
      return uiServiceFluent;
   }

   /**
    * Reads a single row from the table based on the provided search criteria and stores the result in storage.
    *
    * @param tableElementCore The table element to be read.
    * @param searchCriteria   A list of values that must be matched within the row.
    * @return The fluent UI service instance for method chaining.
    */
   public final T readRow(TableElementCore<?, D> tableElementCore, List<String> searchCriteria) {
      Allure.step("[UI - Table] Reading a specific row from the table by search criteria: " + tableElementCore
            + " with criteria: " + searchCriteria);
      tableElementCore.before().accept(driver);
      tableElementCore.after().accept(driver);
      storage.sub(UI).put(tableElementCore.enumImpl(), tableService.readRow(tableElementCore.tableType(), searchCriteria,
            tableElementCore.rowsRepresentationClass()));
      return uiServiceFluent;
   }

   /**
    * Reads a single row from the table based on the row index and stores the result in storage.
    *
    * @param <K>              The type representing the table row.
    * @param tableElementCore The table element to be read.
    * @param row              The row index (1-based index).
    * @param fields           The fields to be extracted from the row. If not provided, all fields are retrieved.
    * @return The fluent UI service instance for method chaining.
    */
   @SafeVarargs
   public final <K> T readRow(TableElementCore<?, D> tableElementCore, int row,
                              TableField<K>... fields) {
      Allure.step("[UI - Table] Reading a specific row from the table with specific fields: " + tableElementCore
            + " at row index: " + row);
      tableElementCore.before().accept(driver);
      tableElementCore.after().accept(driver);
      storage.sub(UI).put(tableElementCore.enumImpl(), tableService.readRow(tableElementCore.tableType(), row,
            tableElementCore.rowsRepresentationClass(), fields));
      return uiServiceFluent;
   }

   /**
    * Reads a row from the table that matches the provided search criteria, retrieving only the specified fields.
    *
    * @param <K>              The type representing the table row.
    * @param tableElementCore The table element to be read.
    * @param searchCriteria   A list of values that must be matched within the row.
    * @param fields           The specific fields to be extracted from the matched row.
    * @return The fluent UI service instance for method chaining.
    */
   @SafeVarargs
   public final <K> T readRow(TableElementCore<?, D> tableElementCore, List<String> searchCriteria,
                              TableField<K>... fields) {
      Allure.step("[UI - Table] Reading a row with search criteria and specific fields: " + tableElementCore
            + " with criteria: " + searchCriteria);
      validateArguments(fields[0], tableElementCore.rowsRepresentationClass());
      tableElementCore.before().accept(driver);
      tableElementCore.after().accept(driver);
      storage.sub(UI).put(tableElementCore.enumImpl(), tableService.readRow(tableElementCore.tableType(), searchCriteria,
            tableElementCore.rowsRepresentationClass(), fields));
      return uiServiceFluent;
   }

   /**
    * Inserts a value into a table cell based on the row index.
    *
    * @param <K>              The type representing table rows.
    * @param tableElementCore The table element to insert data into.
    * @param row              The row index.
    * @param field            The table field to insert data into.
    * @param values           The values to insert.
    * @return The fluent UI service instance.
    */
   public final <K> T insertCellValue(TableElementCore<?, D> tableElementCore, int row,
                                      TableField<K> field, String... values) {
      Allure.step("[UI - Table] Inserting value into cell in row: " + row + " for field: " + field);
      validateArguments(field, tableElementCore.rowsRepresentationClass());
      tableElementCore.before().accept(driver);
      tableService.insertCellValue(tableElementCore.tableType(), row, tableElementCore.rowsRepresentationClass(), field, 1,
            values);
      tableElementCore.after().accept(driver);
      return uiServiceFluent;
   }

   /**
    * Inserts values into a specific cell within a row of the table at the given index.
    *
    * @param <K>              The type representing the table row.
    * @param tableElementCore The table element where the value will be inserted.
    * @param row              The row index (1-based index).
    * @param field            The field within the row where the value should be inserted.
    * @param index            The index of the cell within the row (1-based index).
    * @param value            The values to be inserted into the specified cell.
    * @return The fluent UI service instance for method chaining.
    */
   public final <K> T insertCellValue(TableElementCore<?, D> tableElementCore, int row,
                                      TableField<K> field,
                                      int index, String... value) {
      Allure.step("[UI - Table] Inserting cell value into row: " + row + FIELD + field + INDEX + index);
      validateArguments(field, tableElementCore.rowsRepresentationClass());
      tableElementCore.before().accept(driver);
      tableService.insertCellValue(tableElementCore.tableType(), row, tableElementCore.rowsRepresentationClass(), field,
            index, value);
      tableElementCore.after().accept(driver);
      Allure.step("[UI - Table] Successfully inserted value into cell");
      return uiServiceFluent;
   }

   /**
    * Inserts values into a specific cell in the row that matches the given search criteria.
    *
    * @param <K>              The type representing the table row.
    * @param tableElementCore The table element where the value will be inserted.
    * @param searchCriteria   A list of values used to locate the row.
    * @param field            The field within the row where the values should be inserted.
    * @param values           The values to be inserted into the specified field.
    * @return The fluent UI service instance for method chaining.
    */
   public final <K> T insertCellValue(TableElementCore<?, D> tableElementCore, List<String> searchCriteria,
                                      TableField<K> field,
                                      String... values) {
      Allure.step("[UI - Table] Inserting cell value for search criteria: " + searchCriteria + FIELD + field);
      validateArguments(field, tableElementCore.rowsRepresentationClass());
      tableElementCore.before().accept(driver);
      tableService.insertCellValue(tableElementCore.tableType(), searchCriteria, tableElementCore.rowsRepresentationClass(),
            field, 1, values);
      tableElementCore.after().accept(driver);
      Allure.step("[UI - Table] Successfully inserted value for search criteria");
      return uiServiceFluent;
   }

   /**
    * Inserts values into a specific cell in the row that matches the given search criteria, at the given index.
    *
    * @param <K>              The type representing the table row.
    * @param tableElementCore The table element where the value will be inserted.
    * @param searchCriteria   A list of values used to locate the row.
    * @param field            The field within the row where the values should be inserted.
    * @param index            The index of the cell within the row (1-based index).
    * @param values           The values to be inserted into the specified field.
    * @return The fluent UI service instance for method chaining.
    */
   public final <K> T insertCellValue(TableElementCore<?, D> tableElementCore, List<String> searchCriteria,
                                      TableField<K> field,
                                      int index, String... values) {
      Allure.step("[UI - Table] Inserting cell value at index: " + index + " for search criteria: "
            + searchCriteria + FIELD + field);
      validateArguments(field, tableElementCore.rowsRepresentationClass());
      tableElementCore.before().accept(driver);
      tableService.insertCellValue(tableElementCore.tableType(), searchCriteria, tableElementCore.rowsRepresentationClass(),
            field, index, values);
      tableElementCore.after().accept(driver);
      Allure.step("[UI - Table] Successfully inserted value at index");
      return uiServiceFluent;
   }

   /**
    * Inserts a full data object into a specific row in the table at the given index.
    *
    * @param <K>              The type representing the table row.
    * @param tableElementCore The table element where the data will be inserted.
    * @param row              The row index (1-based index).
    * @param data             The object containing values to be inserted into the row.
    * @return The fluent UI service instance for method chaining.
    */
   public final <K> T insertCellValueAsData(TableElementCore<?, D> tableElementCore, int row, K data) {
      Allure.step("[UI - Table] Inserting data into row: " + row + USING_DATA + data);
      if (!tableElementCore.rowsRepresentationClass().equals(data.getClass())) {
         throw new IllegalArgumentException(
               "The Data object must be from class: " + tableElementCore.rowsRepresentationClass());
      }
      tableElementCore.before().accept(driver);
      tableService.insertCellValue(tableElementCore.tableType(), row, tableElementCore.rowsRepresentationClass(), data);
      tableElementCore.after().accept(driver);
      Allure.step("[UI - Table] Successfully inserted data into row");
      return uiServiceFluent;
   }

   /**
    * Inserts a full data object into a row that matches the given search criteria.
    *
    * <p>This method finds a row in the table that matches the search criteria and inserts values
    * into multiple fields based on the provided data object.
    *
    * @param <K>              The type representing the table row.
    * @param tableElementCore The table element where the data will be inserted.
    * @param searchCriteria   A list of values used to locate the row.
    * @param data             The object containing values to be inserted into the row.
    * @return The fluent UI service instance for method chaining.
    */
   public final <K> T insertCellValueAsData(TableElementCore<?, D> tableElementCore, List<String> searchCriteria, K data) {
      Allure.step("[UI - Table] Inserting data for search criteria: " + searchCriteria + USING_DATA + data);
      if (!tableElementCore.rowsRepresentationClass().equals(data.getClass())) {
         throw new IllegalArgumentException(
               "The Data object must be from class: " + tableElementCore.rowsRepresentationClass());
      }
      tableElementCore.before().accept(driver);
      tableService.insertCellValue(tableElementCore.tableType(), searchCriteria, tableElementCore.rowsRepresentationClass(),
            data);
      tableElementCore.after().accept(driver);
      Allure.step("[UI - Table] Successfully inserted data for search criteria");
      return uiServiceFluent;
   }

   /**
    * Filters a table column based on the specified filtering strategy.
    *
    * @param <K>              The type representing table rows.
    * @param tableElementCore The table element to filter.
    * @param column           The table column field to filter.
    * @param filterStrategy   The filtering strategy to apply.
    * @param values           The values to filter by.
    * @return The fluent UI service instance.
    */
   public final <K> T filterTable(TableElementCore<?, D> tableElementCore,
                                  TableField<K> column,
                                  FilterStrategy filterStrategy, String... values) {
      Allure.step("[UI - Table] Filtering table using column: " + column + " with strategy: " + filterStrategy
            + " and values: " + Arrays.toString(values));
      validateArguments(column, tableElementCore.rowsRepresentationClass());
      tableElementCore.before().accept(driver);
      tableService.filterTable(tableElementCore.tableType(), tableElementCore.rowsRepresentationClass(), column,
            filterStrategy, values);
      tableElementCore.after().accept(driver);
      Allure.step("[UI - Table] Successfully filtered table");
      return uiServiceFluent;
   }

   /**
    * Sorts a table column based on the specified sorting strategy.
    *
    * @param <K>              The type representing table rows.
    * @param tableElementCore The table element to sort.
    * @param column           The table column field to sort.
    * @param sortingStrategy  The sorting strategy to apply.
    * @return The fluent UI service instance.
    */
   public final <K> T sortTable(TableElementCore<?, D> tableElementCore, TableField<K> column,
                                SortingStrategy sortingStrategy) {
      Allure.step("[UI - Table] Sorting table using column: " + column + " with strategy: " + sortingStrategy);
      validateArguments(column, tableElementCore.rowsRepresentationClass());
      tableElementCore.before().accept(driver);
      tableService.sortTable(tableElementCore.tableType(), tableElementCore.rowsRepresentationClass(), column,
            sortingStrategy);
      tableElementCore.after().accept(driver);
      Allure.step("[UI - Table] Successfully sorted table");
      return uiServiceFluent;
   }

   /**
    * Clicks an element inside a specific cell within a row of the table at the given index.
    *
    * @param <K>              The type representing the table row.
    * @param tableElementCore The table element where the action will be performed.
    * @param row              The row index (1-based index).
    * @param field            The field within the row that should be clicked.
    * @return The fluent UI service instance for method chaining.
    */
   public final <K> T clickElementInCell(TableElementCore<?, D> tableElementCore, int row,
                                         TableField<K> field) {
      Allure.step(UI_TABLE_CLICKING_ELEMENT_IN_CELL_AT_ROW + row + FIELD + field);
      return insertCellValue(tableElementCore, row, field);
   }

   /**
    * Clicks an element inside a specific cell within a row of the table at the given cell index.
    *
    * @param <K>              The type representing the table row.
    * @param tableElementCore The table element where the action will be performed.
    * @param row              The row index (1-based index).
    * @param field            The field within the row that should be clicked.
    * @param index            The index of the cell within the row (1-based index).
    * @return The fluent UI service instance for method chaining.
    */
   public final <K> T clickElementInCell(TableElementCore<?, D> tableElementCore, int row, TableField<K> field, int index) {
      Allure.step(UI_TABLE_CLICKING_ELEMENT_IN_CELL_AT_ROW + row + FIELD + field + INDEX + index);
      return insertCellValue(tableElementCore, row, field, index);
   }

   /**
    * Clicks an element inside a specific cell in the row that matches the given search criteria.
    *
    * @param <K>              The type representing the table row.
    * @param tableElementCore The table element where the action will be performed.
    * @param searchCriteria   A list of values used to locate the row.
    * @param field            The field within the row that should be clicked.
    * @return The fluent UI service instance for method chaining.
    */
   public final <K> T clickElementInCell(TableElementCore<?, D> tableElementCore, List<String> searchCriteria,
                                         TableField<K> field) {
      Allure.step(
            UI_TABLE_CLICKING_ELEMENT_IN_CELL_FOR_SEARCH_CRITERIA + searchCriteria + FIELD + field);
      return insertCellValue(tableElementCore, searchCriteria, field);
   }

   /**
    * Clicks an element inside a specific cell in the row that matches the given search criteria at the given index.
    *
    * @param <K>              The type representing the table row.
    * @param tableElementCore The table element where the action will be performed.
    * @param searchCriteria   A list of values used to locate the row.
    * @param field            The field within the row that should be clicked.
    * @param index            The index of the cell within the row (1-based index).
    * @return The fluent UI service instance for method chaining.
    */
   public final <K> T clickElementInCell(TableElementCore<?, D> tableElementCore, List<String> searchCriteria,
                                         TableField<K> field, int index) {
      Allure.step(UI_TABLE_CLICKING_ELEMENT_IN_CELL_FOR_SEARCH_CRITERIA + searchCriteria + FIELD + field
            + INDEX + index);
      return insertCellValue(tableElementCore, searchCriteria, field, index);
   }

   /**
    * Clicks an element inside a specific row in the table based on the given data object.
    *
    * @param <K>              The type representing the table row.
    * @param tableElementCore The table element where the action will be performed.
    * @param row              The row index (1-based index).
    * @param data             The object containing values to locate and interact with elements inside the row.
    * @return The fluent UI service instance for method chaining.
    */
   public final <K> T clickElementInCell(TableElementCore<?, D> tableElementCore, int row, K data) {
      Allure.step(UI_TABLE_CLICKING_ELEMENT_IN_CELL_AT_ROW + row + USING_DATA + data);
      return insertCellValueAsData(tableElementCore, row, data);
   }

   /**
    * Clicks an element inside a specific row in the table based on the given search criteria and data object.
    *
    * @param <K>              The type representing the table row.
    * @param tableElementCore The table element where the action will be performed.
    * @param searchCriteria   A list of values used to locate the row.
    * @param data             The object containing values to locate and interact with elements inside the row.
    * @return The fluent UI service instance for method chaining.
    */
   public final <K> T clickElementInCell(TableElementCore<?, D> tableElementCore, List<String> searchCriteria, K data) {
      Allure.step(UI_TABLE_CLICKING_ELEMENT_IN_CELL_FOR_SEARCH_CRITERIA + searchCriteria + USING_DATA + data);
      return insertCellValueAsData(tableElementCore, searchCriteria, data);
   }

   private <K> void validateArguments(TableField<K> field, Class<K> expectedClass) {
      try {
         K instance = expectedClass.getDeclaredConstructor().newInstance();
         field.invoke(instance, null);
      } catch (Exception e) {
         throw new IllegalArgumentException("The TableField objects should be from class: " + expectedClass);
      }
   }

   /**
    * Validates the table contents against the given assertions.
    *
    * @param tableElementCore The table element to validate.
    * @param assertions       The assertions to verify.
    * @return The fluent UI service instance.
    */
   @SuppressWarnings("java:S1854")
   public T validate(TableElementCore<?, D> tableElementCore, Assertion... assertions) {
      Allure.step("[UI - Table] Validating table element: " + tableElementCore + " with assertions: "
            + Arrays.toString(assertions));
      Object tableData = storage.sub(UI).get(tableElementCore.enumImpl(), Object.class);
      if (tableData == null) {
         throw new IllegalArgumentException("No table data found for key: " + tableElementCore.enumImpl());
      }

      var validationResults = tableService.validate(tableData, assertions);
      decoratorsFactory.decorate(uiServiceFluent, SuperUiServiceFluentCore.class)
            .validation(validationResults);

      Allure.step("[UI - Table] Validation completed for table element");
      return uiServiceFluent;
   }

}


