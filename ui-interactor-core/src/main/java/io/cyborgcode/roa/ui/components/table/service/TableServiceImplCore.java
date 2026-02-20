package io.cyborgcode.roa.ui.components.table.service;

import io.cyborgcode.roa.ui.components.base.AbstractComponentServiceCore;
import io.cyborgcode.roa.ui.components.table.base.TableComponentType;
import io.cyborgcode.roa.ui.components.table.base.TableField;
import io.cyborgcode.roa.ui.components.table.filters.FilterStrategy;
import io.cyborgcode.roa.ui.components.table.registry.TableServiceRegistry;
import io.cyborgcode.roa.ui.components.table.service.Table;
import io.cyborgcode.roa.ui.components.table.service.TableService;
import io.cyborgcode.roa.ui.components.table.sort.SortingStrategy;
import java.util.List;
import lombok.Getter;

/**
 * Implementation of the {@link TableService} interface, providing concrete logic
 * for interacting with table components.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public abstract class TableServiceImplCore<D, C> extends AbstractComponentServiceCore<TableComponentType, Table, D> implements TableService {

   @Getter
   private final TableServiceRegistry<C> tableServiceRegistry;

   /**
    * Constructs a new AbstractComponentService with the given driver.
    *
    * @param driver The driver/page instance for UI interactions.
    */
   protected TableServiceImplCore(D driver, TableServiceRegistry<C> tableServiceRegistry) {
      super(driver);
      this.tableServiceRegistry = tableServiceRegistry;
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

}
