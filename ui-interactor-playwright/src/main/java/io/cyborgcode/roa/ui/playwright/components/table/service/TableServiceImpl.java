package io.cyborgcode.roa.ui.playwright.components.table.service;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.cyborgcode.roa.ui.components.table.base.TableComponentType;
import io.cyborgcode.roa.ui.components.table.registry.TableServiceRegistry;
import io.cyborgcode.roa.ui.components.table.service.Table;
import io.cyborgcode.roa.ui.components.table.service.TableService;
import io.cyborgcode.roa.ui.components.table.service.TableServiceImplCore;
import io.cyborgcode.roa.ui.playwright.components.factory.ComponentFactory;

/**
 * Implementation of the {@link TableService} interface, providing concrete logic
 * for interacting with table components.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public class TableServiceImpl extends TableServiceImplCore<Page, Locator> {

   /**
    * Constructs a new AbstractComponentService with the given driver.
    *
    * @param page The driver/page instance for UI interactions.
    */
   public TableServiceImpl(Page page, TableServiceRegistry<Locator> tableServiceRegistry) {
      super(page, tableServiceRegistry);
   }

   @Override
   protected Table createComponent(TableComponentType componentType) {
      Table tableComponent = ComponentFactory.getTableComponent(componentType, driver);
      TableImpl table = (TableImpl) tableComponent;
      table.setServiceRegistry(getTableServiceRegistry());
      return tableComponent;
   }

}
