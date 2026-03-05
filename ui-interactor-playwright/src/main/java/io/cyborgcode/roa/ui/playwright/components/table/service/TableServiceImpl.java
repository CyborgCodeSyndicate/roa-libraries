package io.cyborgcode.roa.ui.playwright.components.table.service;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.cyborgcode.roa.ui.components.table.base.TableComponentType;
import io.cyborgcode.roa.ui.components.table.registry.TableServiceRegistry;
import io.cyborgcode.roa.ui.components.table.service.Table;
import io.cyborgcode.roa.ui.components.table.service.TableService;
import io.cyborgcode.roa.ui.components.table.service.TableServiceImplCore;
import io.cyborgcode.roa.ui.playwright.base.PwElement;
import io.cyborgcode.roa.ui.playwright.components.factory.ComponentFactory;
import io.cyborgcode.roa.ui.validator.UiTableValidator;
import io.cyborgcode.roa.validator.core.Assertion;
import io.cyborgcode.roa.validator.core.AssertionResult;
import java.util.List;

/**
 * Implementation of the {@link TableService} interface, providing concrete logic
 * for interacting with table components.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public class TableServiceImpl extends TableServiceImplCore<Page, PwElement> {

   /**
    * Constructs a new AbstractComponentService with the given driver.
    *
    * @param page The driver/page instance for UI interactions.
    */
   public TableServiceImpl(Page page, TableServiceRegistry<PwElement> tableServiceRegistry, UiTableValidator uiTableValidator) {
      super(page, tableServiceRegistry, uiTableValidator);
   }

   @Override
   protected Table createComponent(TableComponentType componentType) {
      Table tableComponent = ComponentFactory.getTableComponent(componentType, driver);
      TableImpl table = (TableImpl) tableComponent;
      table.setServiceRegistry(getTableServiceRegistry());
      return tableComponent;
   }

}
