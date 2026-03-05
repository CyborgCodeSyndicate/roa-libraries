package io.cyborgcode.roa.ui.components.table.registry;

import io.cyborgcode.roa.ui.components.base.BaseUiElement;
import io.cyborgcode.roa.ui.components.base.ComponentType;
import io.cyborgcode.roa.ui.components.table.filters.TableFilter;
import io.cyborgcode.roa.ui.components.table.insertion.TableInsertion;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Registry for managing table-related services.
 *
 * <p>This class provides a centralized registry for storing and retrieving
 * {@link TableInsertion} and {@link TableFilter} services based on their
 * associated {@link ComponentType}.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public class TableServiceRegistry<E extends BaseUiElement> {

   private final Map<Class<? extends ComponentType>, TableInsertion<E>> tableInsertionRegistry = new ConcurrentHashMap<>();
   private final Map<Class<? extends ComponentType>, TableFilter<E>> tableFilterRegistry = new ConcurrentHashMap<>();

   public void registerService(Class<? extends ComponentType> type, TableInsertion<E> service) {
      tableInsertionRegistry.put(type, service);
   }

   public void registerService(Class<? extends ComponentType> type, TableFilter<E> service) {
      tableFilterRegistry.put(type, service);
   }

   public TableInsertion<E> getTableService(Class<? extends ComponentType> type) {
      return tableInsertionRegistry.get(type);
   }

   public TableFilter<E> getFilterService(Class<? extends ComponentType> type) {
      return tableFilterRegistry.get(type);
   }
}
