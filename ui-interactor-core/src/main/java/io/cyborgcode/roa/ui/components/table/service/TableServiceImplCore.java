package io.cyborgcode.roa.ui.components.table.service;

import io.cyborgcode.roa.ui.components.base.AbstractComponentServiceCore;
import io.cyborgcode.roa.ui.components.base.BaseUiElement;
import io.cyborgcode.roa.ui.components.table.base.TableComponentType;
import io.cyborgcode.roa.ui.components.table.base.TableField;
import io.cyborgcode.roa.ui.components.table.filters.FilterStrategy;
import io.cyborgcode.roa.ui.components.table.registry.TableServiceRegistry;
import io.cyborgcode.roa.ui.components.table.sort.SortingStrategy;
import io.cyborgcode.roa.ui.validator.UiTableValidator;
import io.cyborgcode.roa.validator.core.Assertion;
import io.cyborgcode.roa.validator.core.AssertionResult;
import java.util.List;
import lombok.Getter;

/**
 * Implementation of the {@link TableService} interface, providing concrete logic
 * for interacting with table components.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public abstract class TableServiceImplCore<D, E extends BaseUiElement> extends AbstractComponentServiceCore<TableComponentType, Table, D> implements TableService {

   @Getter
   private final TableServiceRegistry<E> tableServiceRegistry;
   private final UiTableValidator uiTableValidator;

   /**
    * Constructs a new AbstractComponentService with the given driver.
    *
    * @param driver The driver/page instance for UI interactions.
    */
   protected TableServiceImplCore(D driver, TableServiceRegistry<E> tableServiceRegistry, UiTableValidator uiTableValidator) {
      super(driver);
      this.tableServiceRegistry = tableServiceRegistry;
      this.uiTableValidator = uiTableValidator;
   }

   protected Table tableComponent(final TableComponentType componentType) {
      return getOrCreateComponent(componentType);
   }


   @Override
   public final <T> List<T> readTable(final TableComponentType tableComponentType, final Class<T> clazz) {
      return tableComponent(tableComponentType).readTable(clazz);
   }

   @Override
   @SafeVarargs
   public final <T> List<T> readTable(final TableComponentType tableComponentType, final Class<T> clazz,
                                      final TableField<T>... fields) {
      return tableComponent(tableComponentType).readTable(clazz, fields);
   }

   @Override
   public final <T> List<T> readTable(final TableComponentType tableComponentType, final int start, final int end,
                                      final Class<T> clazz) {
      return tableComponent(tableComponentType).readTable(start, end, clazz);
   }

   @Override
   @SafeVarargs
   public final <T> List<T> readTable(final TableComponentType tableComponentType, final int start, final int end,
                                      final Class<T> clazz, final TableField<T>... fields) {
      return tableComponent(tableComponentType).readTable(start, end, clazz, fields);
   }

   @Override
   public final <T> T readRow(final TableComponentType tableComponentType, final int row, final Class<T> clazz) {
      return tableComponent(tableComponentType).readRow(row, clazz);
   }

   @Override
   public final <T> T readRow(final TableComponentType tableComponentType, final List<String> searchCriteria,
                              final Class<T> clazz) {
      return tableComponent(tableComponentType).readRow(searchCriteria, clazz);
   }

   @Override
   @SafeVarargs
   public final <T> T readRow(final TableComponentType tableComponentType, final int row, final Class<T> clazz,
                              final TableField<T>... fields) {
      return tableComponent(tableComponentType).readRow(row, clazz, fields);
   }

   @Override
   @SafeVarargs
   public final <T> T readRow(final TableComponentType tableComponentType, final List<String> searchCriteria,
                              final Class<T> clazz, final TableField<T>... fields) {
      return tableComponent(tableComponentType).readRow(searchCriteria, clazz, fields);
   }

   @Override
   public final <T> void insertCellValue(final TableComponentType tableComponentType, final int row,
                                         final Class<T> clazz, final T data) {
      tableComponent(tableComponentType).insertCellValue(row, clazz, data);
   }

   @Override
   public final <T> void insertCellValue(final TableComponentType tableComponentType, final int row,
                                         final Class<T> clazz, final TableField<T> field, final int index,
                                         final String... value) {
      tableComponent(tableComponentType).insertCellValue(row, clazz, field, index, value);
   }

   @Override
   public final <T> void insertCellValue(final TableComponentType tableComponentType,
                                         final List<String> searchCriteria, final Class<T> clazz,
                                         final TableField<T> field, final int index, final String... values) {
      tableComponent(tableComponentType).insertCellValue(searchCriteria, clazz, field, index, values);
   }

   @Override
   public final <T> void insertCellValue(final TableComponentType tableComponentType,
                                         final List<String> searchCriteria, final Class<T> clazz, final T data) {
      tableComponent(tableComponentType).insertCellValue(searchCriteria, clazz, data);
   }

   @Override
   public final <T> void filterTable(final TableComponentType tableComponentType, final Class<T> tclass,
                                     final TableField<T> column, final FilterStrategy filterStrategy,
                                     final String... values) {
      tableComponent(tableComponentType).filterTable(tclass, column, filterStrategy, values);
   }

   @Override
   public final <T> void sortTable(final TableComponentType tableComponentType, final Class<T> tclass,
                                   final TableField<T> column, final SortingStrategy sortingStrategy) {
      tableComponent(tableComponentType).sortTable(tclass, column, sortingStrategy);
   }

   /**
    * Validates a table against the specified assertions.
    *
    * @param table      The table object to validate.
    * @param assertions The assertions to apply for validation.
    * @param <T>        The row type.
    * @return A list of assertion results indicating the validation outcome.
    */
   @Override
   public <T> List<AssertionResult<T>> validate(final Object table, final Assertion... assertions) {
      if (table == null) {
         throw new IllegalArgumentException("Table cannot be null for validation.");
      }
      if (assertions == null || assertions.length == 0) {
         throw new IllegalArgumentException("At least one assertion must be provided.");
      }
      return uiTableValidator.validateTable(table, assertions);
   }

}
