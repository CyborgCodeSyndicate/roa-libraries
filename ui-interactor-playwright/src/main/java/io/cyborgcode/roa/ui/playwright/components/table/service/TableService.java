package io.cyborgcode.roa.ui.playwright.components.table.service;

import io.cyborgcode.roa.ui.playwright.components.table.base.TableComponentType;
import io.cyborgcode.roa.ui.playwright.components.table.base.TableField;
import io.cyborgcode.roa.ui.playwright.components.table.filters.FilterStrategy;
import io.cyborgcode.roa.ui.playwright.components.table.sort.SortingStrategy;
import io.cyborgcode.utilities.reflections.ReflectionUtil;
import io.cyborgcode.utilities.reflections.exceptions.ReflectionException;
import java.util.List;

import static io.cyborgcode.roa.ui.playwright.config.UiConfigHolder.getPlaywrightConfig;

/**
 * Service interface defining operations for interacting with table components.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface TableService {

   TableComponentType DEFAULT_TYPE = getDefaultType();

   private static TableComponentType getDefaultType() {
      try {
         return ReflectionUtil.findEnumImplementationsOfInterface(TableComponentType.class,
               getPlaywrightConfig().tableDefaultType(),
               getPlaywrightConfig().projectPackages());
      } catch (ReflectionException e) {
         return ReflectionUtil.findEnumImplementationsOfInterface(TableComponentType.class,
               getPlaywrightConfig().tableDefaultType(),
               "io.cyborgcode.roa");
      }
   }

   default <T> List<T> readTable(Class<T> clazz) { return readTable(DEFAULT_TYPE, clazz); }
   <T> List<T> readTable(TableComponentType tableComponentType, Class<T> clazz);

   default <T> List<T> readTable(Class<T> clazz, TableField<T>... fields) { return readTable(DEFAULT_TYPE, clazz, fields); }
   <T> List<T> readTable(TableComponentType tableComponentType, Class<T> clazz, TableField<T>... fields);

   default <T> List<T> readTable(int start, int end, Class<T> clazz) { return readTable(DEFAULT_TYPE, start, end, clazz); }
   <T> List<T> readTable(TableComponentType tableComponentType, int start, int end, Class<T> clazz);

   default <T> List<T> readTable(int start, int end, Class<T> clazz, TableField<T>... fields) { return readTable(DEFAULT_TYPE, start, end, clazz, fields); }
   <T> List<T> readTable(TableComponentType tableComponentType, int start, int end, Class<T> clazz, TableField<T>... fields);

   default <T> T readRow(int row, Class<T> clazz) { return readRow(DEFAULT_TYPE, row, clazz); }
   <T> T readRow(TableComponentType tableComponentType, int row, Class<T> clazz);

   default <T> T readRow(List<String> searchCriteria, Class<T> clazz) { return readRow(DEFAULT_TYPE, searchCriteria, clazz); }
   <T> T readRow(TableComponentType tableComponentType, List<String> searchCriteria, Class<T> clazz);

   default <T> T readRow(int row, Class<T> clazz, TableField<T>... fields) { return readRow(DEFAULT_TYPE, row, clazz, fields); }
   <T> T readRow(TableComponentType tableComponentType, int row, Class<T> clazz, TableField<T>... fields);

   default <T> T readRow(List<String> searchCriteria, Class<T> clazz, TableField<T>... fields) { return readRow(DEFAULT_TYPE, searchCriteria, clazz, fields); }
   <T> T readRow(TableComponentType tableComponentType, List<String> searchCriteria, Class<T> clazz, TableField<T>... fields);

   default <T> void insertCellValue(int row, Class<T> classType, T data) { insertCellValue(DEFAULT_TYPE, row, classType, data); }
   <T> void insertCellValue(TableComponentType tableComponentType, int row, Class<T> classType, T data);

   default <T> void insertCellValue(int row, Class<T> classType, TableField<T> field, String... values) { insertCellValue(DEFAULT_TYPE, row, classType, field, 1, values); }
   default <T> void insertCellValue(TableComponentType tableComponentType, int row, Class<T> classType, TableField<T> field, String... values) { insertCellValue(tableComponentType, row, classType, field, 1, values); }
   default <T> void insertCellValue(int row, Class<T> classType, TableField<T> field, int index, String... value) { insertCellValue(DEFAULT_TYPE, row, classType, field, index, value); }
   <T> void insertCellValue(TableComponentType tableComponentType, int row, Class<T> classType, TableField<T> field, int index, String... value);

   default <T> void insertCellValue(List<String> searchCriteria, Class<T> classType, TableField<T> field, String... values) { insertCellValue(DEFAULT_TYPE, searchCriteria, classType, field, 1, values); }
   default <T> void insertCellValue(TableComponentType tableComponentType, List<String> searchCriteria, Class<T> classType, TableField<T> field, String... values) { insertCellValue(tableComponentType, searchCriteria, classType, field, 1, values); }
   default <T> void insertCellValue(List<String> searchCriteria, Class<T> classType, TableField<T> field, int index, String... values) { insertCellValue(DEFAULT_TYPE, searchCriteria, classType, field, index, values); }
   <T> void insertCellValue(TableComponentType tableComponentType, List<String> searchCriteria, Class<T> classType, TableField<T> field, int index, String... values);

   default <T> void insertCellValue(List<String> searchCriteria, Class<T> classType, T data) { insertCellValue(DEFAULT_TYPE, searchCriteria, classType, data); }
   <T> void insertCellValue(TableComponentType tableComponentType, List<String> searchCriteria, Class<T> classType, T data);

   default <T> void filterTable(Class<T> tclass, TableField<T> column, FilterStrategy filterStrategy, String... values) { filterTable(DEFAULT_TYPE, tclass, column, filterStrategy, values); }
   <T> void filterTable(TableComponentType tableComponentType, Class<T> tclass, TableField<T> column, FilterStrategy filterStrategy, String... values);

   default <T> void sortTable(Class<T> tclass, TableField<T> column, SortingStrategy sortingStrategy) { sortTable(DEFAULT_TYPE, tclass, column, sortingStrategy); }
   <T> void sortTable(TableComponentType tableComponentType, Class<T> tclass, TableField<T> column, SortingStrategy sortingStrategy);
}
