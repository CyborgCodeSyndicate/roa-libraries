package io.cyborgcode.roa.ui.playwright.components.table.registry;

import io.cyborgcode.roa.ui.playwright.components.base.ComponentType;
import io.cyborgcode.roa.ui.playwright.components.table.filters.TableFilter;
import io.cyborgcode.roa.ui.playwright.components.table.insertion.TableInsertion;
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
public class TableServiceRegistry {

   private final Map<Class<? extends ComponentType>, TableInsertion> tableInsertionRegistry = new ConcurrentHashMap<>();
   private final Map<Class<? extends ComponentType>, TableFilter> tableFilterRegistry = new ConcurrentHashMap<>();

   public void registerService(Class<? extends ComponentType> type, TableInsertion service) {
      tableInsertionRegistry.put(type, service);
   }

   public void registerService(Class<? extends ComponentType> type, TableFilter service) {
      tableFilterRegistry.put(type, service);
   }

   public TableInsertion getTableService(Class<? extends ComponentType> type) {
      return tableInsertionRegistry.get(type);
   }

   public TableFilter getFilterService(Class<? extends ComponentType> type) {
      return tableFilterRegistry.get(type);
   }
}
