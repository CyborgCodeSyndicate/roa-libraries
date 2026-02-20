package io.cyborgcode.roa.ui.components.table.registry;

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
public class TableServiceRegistry<C> {

   private final Map<Class<? extends ComponentType>, TableInsertion<C>> tableInsertionRegistry = new ConcurrentHashMap<>();
   private final Map<Class<? extends ComponentType>, TableFilter<C>> tableFilterRegistry = new ConcurrentHashMap<>();

   public void registerService(Class<? extends ComponentType> type, TableInsertion<C> service) {
      tableInsertionRegistry.put(type, service);
   }

   public void registerService(Class<? extends ComponentType> type, TableFilter<C> service) {
      tableFilterRegistry.put(type, service);
   }

   public TableInsertion<C> getTableService(Class<? extends ComponentType> type) {
      return tableInsertionRegistry.get(type);
   }

   public TableFilter<C> getFilterService(Class<? extends ComponentType> type) {
      return tableFilterRegistry.get(type);
   }
}
