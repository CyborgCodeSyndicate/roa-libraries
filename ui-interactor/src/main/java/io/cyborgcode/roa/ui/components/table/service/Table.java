package io.cyborgcode.roa.ui.components.table.service;

import io.cyborgcode.pandora.annotation.Pandora;
import io.cyborgcode.pandora.annotation.PandoraOptions;
import io.cyborgcode.pandora.model.CreationKind;
import io.cyborgcode.roa.ui.components.table.base.TableField;
import io.cyborgcode.roa.ui.components.table.filters.FilterStrategy;
import io.cyborgcode.roa.ui.components.table.sort.SortingStrategy;
import java.util.List;

/**
 * Defines the core operations for interacting with table components.
 *
 * <p>This interface provides methods for reading table data, retrieving rows based on different criteria,
 * inserting values into specific table cells, filtering table contents, and sorting table data.
 * It abstracts table interactions for different table structures.
 *
 * <p>Implementations of this interface are expected to handle the underlying table processing.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@Pandora(
      description = "Interface contract for table components, covering reading, filtering, "
            + "inserting cell values, and sorting operations.",
      tags = {"ui", "component-contract", "table"},
      creation = CreationKind.AUTO
)
@PandoraOptions(
      exampleFilesPath = "docs/usage/roa/ui-usage.json",
      meta = {
         @PandoraOptions.Meta(key = "type", value = "ui-component")
      }
)
public interface Table {

   /**
    * Reads the entire table and maps it to a list of objects of the specified type.
    *
    * @param clazz The class type representing a table row.
    * @param <T>   The type of the row representation.
    * @return A list of objects representing the table rows.
    */
   @Pandora(
         description = "Reads the entire table and maps it to a list of the specified row type.",
         tags = {"component-contract", "table"}
   )
   <T> List<T> readTable(Class<T> clazz);

   /**
    * Reads the table while considering specific fields for mapping.
    *
    * @param clazz  The class type representing a table row.
    * @param fields The specific table fields to be read.
    * @param <T>    The type of the row representation.
    * @return A list of objects containing only the specified fields.
    */
   @Pandora(
         description = "Reads the table selecting only the specified fields for mapping.",
         tags = {"component-contract", "table"}
   )
   <T> List<T> readTable(Class<T> clazz, TableField<T>... fields);

   /**
    * Reads a specific range of rows from the table.
    *
    * @param start The starting row index (inclusive).
    * @param end   The ending row index (exclusive).
    * @param clazz The class type representing a table row.
    * @param <T>   The type of the row representation.
    * @return A list of objects representing the specified range of table rows.
    */
   @Pandora(
         description = "Reads a range of rows from the table and maps them to the specified type.",
         tags = {"component-contract", "table"}
   )
   <T> List<T> readTable(int start, int end, Class<T> clazz);

   /**
    * Reads a specific range of rows from the table while considering specified fields.
    *
    * @param start  The starting row index (inclusive).
    * @param end    The ending row index (exclusive).
    * @param clazz  The class type representing a table row.
    * @param fields The specific table fields to be read.
    * @param <T>    The type of the row representation.
    * @return A list of objects representing the specified table rows with the chosen fields.
    */
   @Pandora(
         description = "Reads a range of rows from the table selecting only the specified fields.",
         tags = {"component-contract", "table"}
   )
   <T> List<T> readTable(int start, int end, Class<T> clazz, TableField<T>... fields);

   /**
    * Reads a single row from the table based on its index.
    *
    * @param row   The row index (1-based index).
    * @param clazz The class type representing a table row.
    * @param <T>   The type of the row representation.
    * @return An object representing the table row.
    */
   @Pandora(
         description = "Reads a single row from the table by index and maps it to the specified type.",
         tags = {"component-contract", "table"}
   )
   <T> T readRow(int row, Class<T> clazz);

   /**
    * Reads a single row from the table that matches the given search criteria.
    *
    * @param searchCriteria A list of string values to match in the row.
    * @param clazz          The class type representing a table row.
    * @param <T>            The type of the row representation.
    * @return The first matching row as an object of type {@code T}.
    */
   @Pandora(
         description = "Reads the first row matching the given search criteria and maps it to the specified type.",
         tags = {"component-contract", "table"}
   )
   <T> T readRow(List<String> searchCriteria, Class<T> clazz);

   /**
    * Reads a specific row from the table while considering specific fields.
    *
    * @param row    The row index (1-based index).
    * @param clazz  The class type representing a table row.
    * @param fields The specific table fields to be read.
    * @param <T>    The type of the row representation.
    * @return An object representing the specified row with only the chosen fields.
    */
   @Pandora(
         description = "Reads a single row by index selecting only the specified fields.",
         tags = {"component-contract", "table"}
   )
   <T> T readRow(int row, Class<T> clazz, TableField<T>... fields);

   /**
    * Reads a row that matches the search criteria while considering specific fields.
    *
    * @param searchCriteria A list of string values to match in the row.
    * @param clazz          The class type representing a table row.
    * @param fields         The specific table fields to be read.
    * @param <T>            The type of the row representation.
    * @return The first matching row as an object of type {@code T}, with only the chosen fields.
    */
   @Pandora(
         description = "Reads the first matching row selecting only the specified fields.",
         tags = {"component-contract", "table"}
   )
   <T> T readRow(List<String> searchCriteria, Class<T> clazz, TableField<T>... fields);

   /**
    * Inserts a full object as a row into the table at the specified row index.
    *
    * @param row       The row index (1-based index).
    * @param classType The class type representing a table row.
    * @param data      The object containing the data to be inserted.
    * @param <T>       The type of the row representation.
    */
   @Pandora(
         description = "Inserts an entire object as a row into the table at the specified index.",
         tags = {"component-contract", "table"}
   )
   <T> void insertCellValue(int row, Class<T> classType, T data);

   /**
    * Inserts a value into a specific cell within a row.
    * If not specified, the value is inserted at index 1.
    *
    * @param row       The row index (1-based index).
    * @param classType The class type representing a table row.
    * @param field     The table field to insert the value into.
    * @param values    The values to be inserted.
    * @param <T>       The type of the row representation.
    */
   @Pandora(
         description = "Inserts values into a specific cell of a row (defaults to index 1).",
         tags = {"component-contract", "table"}
   )
   default <T> void insertCellValue(int row, Class<T> classType, TableField<T> field, String... values) {
      insertCellValue(row, classType, field, 1, values);
   }

   /**
    * Inserts a value into a specific cell within a row at the given cell index.
    *
    * @param row       The row index (1-based index).
    * @param classType The class type representing a table row.
    * @param field     The table field to insert the value into.
    * @param index     The cell index within the row (1-based index).
    * @param values    The values to be inserted.
    * @param <T>       The type of the row representation.
    */
   @Pandora(
         description = "Inserts values into a specific cell of a row at the given cell index.",
         tags = {"component-contract", "table"}
   )
   <T> void insertCellValue(int row, Class<T> classType, TableField<T> field, int index, String... values);

   /**
    * Inserts values into a cell within a row that matches the given search criteria.
    *
    * @param searchCriteria A list of string values to match in the row.
    * @param classType      The class type representing a table row.
    * @param field          The table field to insert the value into.
    * @param values         The values to be inserted.
    * @param <T>            The type of the row representation.
    */
   @Pandora(
         description = "Inserts values into a specific cell for the row matching the search criteria "
               + "(defaults to index 1).",
         tags = {"component-contract", "table"}
   )
   default <T> void insertCellValue(List<String> searchCriteria, Class<T> classType, TableField<T> field,
                                    String... values) {
      insertCellValue(searchCriteria, classType, field, 1, values);
   }

   /**
    * Inserts values into a cell within a row that matches the search criteria at the given cell index.
    *
    * @param searchCriteria A list of string values to match in the row.
    * @param classType      The class type representing a table row.
    * @param field          The table field to insert the value into.
    * @param index          The cell index within the row (1-based index).
    * @param values         The values to be inserted.
    * @param <T>            The type of the row representation.
    */
   @Pandora(
         description = "Inserts values into a specific cell for the row matching the search criteria "
               + "at the given cell index.",
         tags = {"component-contract", "table"}
   )
   <T> void insertCellValue(List<String> searchCriteria, Class<T> classType, TableField<T> field, int index,
                            String... values);

   /**
    * Inserts an object as a row into the table based on matching search criteria.
    *
    * @param searchCriteria A list of string values to match in the row.
    * @param classType      The class type representing a table row.
    * @param data           The object containing the data to be inserted.
    * @param <T>            The type of the row representation.
    */
   @Pandora(
         description = "Inserts an entire object as a row into the table matching the given search criteria.",
         tags = {"component-contract", "table"}
   )
   <T> void insertCellValue(List<String> searchCriteria, Class<T> classType, T data);

   /**
    * Applies a filter to a column in the table.
    *
    * @param tclass         The class type representing a table row.
    * @param column         The table field representing the column to be filtered.
    * @param filterStrategy The filter strategy to be applied.
    * @param values         The values to be used for filtering.
    * @param <T>            The type of the row representation.
    */
   @Pandora(
         description = "Applies a filter strategy to a table column with the provided values.",
         tags = {"component-contract", "table"}
   )
   <T> void filterTable(Class<T> tclass, TableField<T> column, FilterStrategy filterStrategy, String... values);

   /**
    * Sorts the table based on the specified column.
    *
    * @param tclass          The class type representing a table row.
    * @param column          The table field representing the column to be sorted.
    * @param sortingStrategy The sorting strategy to be applied.
    * @param <T>             The type of the row representation.
    */
   @Pandora(
         description = "Sorts the table by the specified column using the given sorting strategy.",
         tags = {"component-contract", "table"}
   )
   <T> void sortTable(Class<T> tclass, TableField<T> column, SortingStrategy sortingStrategy);

}
