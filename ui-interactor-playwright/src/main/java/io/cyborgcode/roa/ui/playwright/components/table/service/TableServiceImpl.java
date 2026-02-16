package io.cyborgcode.roa.ui.playwright.components.table.service;

import io.cyborgcode.roa.ui.playwright.components.base.AbstractComponentService;
import io.cyborgcode.roa.ui.playwright.components.factory.ComponentFactory;
import io.cyborgcode.roa.ui.playwright.components.table.base.TableComponentType;
import io.cyborgcode.roa.ui.playwright.components.table.base.TableField;
import io.cyborgcode.roa.ui.playwright.components.table.filters.FilterStrategy;
import io.cyborgcode.roa.ui.playwright.components.table.registry.TableServiceRegistry;
import io.cyborgcode.roa.ui.playwright.components.table.sort.SortingStrategy;
import com.microsoft.playwright.Page;
import java.util.List;

/**
 * Implementation of the {@link TableService} interface, providing concrete logic
 * for interacting with table components.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public class TableServiceImpl extends AbstractComponentService<TableComponentType, Table> implements TableService {

   private final TableServiceRegistry tableServiceRegistry;

   /**
    * Constructs a {@code TableServiceImpl} with the specified dependencies.
    *
    * @param Page            The {@link Page} instance for browser interactions.
    * @param tableServiceRegistry The registry managing table services.
    */
   public TableServiceImpl(final Page page, final TableServiceRegistry tableServiceRegistry) {
      super(page);
      this.tableServiceRegistry = tableServiceRegistry;
   }

   @Override
   protected Table createComponent(final TableComponentType componentType) {
      Table tableComponent = ComponentFactory.getTableComponent(componentType, page);
      TableImpl table = (TableImpl) tableComponent;
      table.setServiceRegistry(tableServiceRegistry);
      return tableComponent;
   }

   @Override
   public final <T> List<T> readTable(final TableComponentType tableComponentType, final Class<T> clazz) {
      return getOrCreateComponent(tableComponentType).readTable(clazz);
   }

   @Override
   @SafeVarargs
   public final <T> List<T> readTable(final TableComponentType tableComponentType, final Class<T> clazz,
                                      final TableField<T>... fields) {
      return getOrCreateComponent(tableComponentType).readTable(clazz, fields);
   }

   @Override
   public final <T> List<T> readTable(final TableComponentType tableComponentType, final int start, final int end,
                                      final Class<T> clazz) {
      return getOrCreateComponent(tableComponentType).readTable(start, end, clazz);
   }

   @Override
   @SafeVarargs
   public final <T> List<T> readTable(final TableComponentType tableComponentType, final int start, final int end,
                                      final Class<T> clazz, final TableField<T>... fields) {
      return getOrCreateComponent(tableComponentType).readTable(start, end, clazz, fields);
   }

   @Override
   public final <T> T readRow(final TableComponentType tableComponentType, final int row, final Class<T> clazz) {
      return getOrCreateComponent(tableComponentType).readRow(row, clazz);
   }

   @Override
   public final <T> T readRow(final TableComponentType tableComponentType, final List<String> searchCriteria,
                              final Class<T> clazz) {
      return getOrCreateComponent(tableComponentType).readRow(searchCriteria, clazz);
   }

   @Override
   @SafeVarargs
   public final <T> T readRow(final TableComponentType tableComponentType, final int row, final Class<T> clazz,
                              final TableField<T>... fields) {
      return getOrCreateComponent(tableComponentType).readRow(row, clazz, fields);
   }

   @Override
   @SafeVarargs
   public final <T> T readRow(final TableComponentType tableComponentType, final List<String> searchCriteria,
                              final Class<T> clazz, final TableField<T>... fields) {
      return getOrCreateComponent(tableComponentType).readRow(searchCriteria, clazz, fields);
   }

   @Override
   public final <T> void insertCellValue(final TableComponentType tableComponentType, final int row,
                                         final Class<T> clazz, final T data) {
      getOrCreateComponent(tableComponentType).insertCellValue(row, clazz, data);
   }

   @Override
   public final <T> void insertCellValue(final TableComponentType tableComponentType, final int row,
                                         final Class<T> clazz, final TableField<T> field, final int index,
                                         final String... value) {
      getOrCreateComponent(tableComponentType).insertCellValue(row, clazz, field, index, value);
   }

   @Override
   public final <T> void insertCellValue(final TableComponentType tableComponentType,
                                         final List<String> searchCriteria, final Class<T> clazz,
                                         final TableField<T> field, final int index, final String... values) {
      getOrCreateComponent(tableComponentType).insertCellValue(searchCriteria, clazz, field, index, values);
   }

   @Override
   public final <T> void insertCellValue(final TableComponentType tableComponentType,
                                         final List<String> searchCriteria, final Class<T> clazz, final T data) {
      getOrCreateComponent(tableComponentType).insertCellValue(searchCriteria, clazz, data);
   }

   @Override
   public final <T> void filterTable(final TableComponentType tableComponentType, final Class<T> tclass,
                                     final TableField<T> column, final FilterStrategy filterStrategy,
                                     final String... values) {
      getOrCreateComponent(tableComponentType).filterTable(tclass, column, filterStrategy, values);
   }

   @Override
   public final <T> void sortTable(final TableComponentType tableComponentType, final Class<T> tclass,
                                   final TableField<T> column, final SortingStrategy sortingStrategy) {
      getOrCreateComponent(tableComponentType).sortTable(tclass, column, sortingStrategy);
   }

}
