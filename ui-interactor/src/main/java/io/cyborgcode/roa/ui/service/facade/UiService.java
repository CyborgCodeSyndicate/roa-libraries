package io.cyborgcode.roa.ui.service.facade;

import io.cyborgcode.roa.ui.components.alert.AlertService;
import io.cyborgcode.roa.ui.components.alert.AlertServiceImpl;
import io.cyborgcode.roa.ui.components.button.ButtonComponentType;
import io.cyborgcode.roa.ui.components.button.ButtonService;
import io.cyborgcode.roa.ui.components.button.ButtonServiceImpl;
import io.cyborgcode.roa.ui.components.checkbox.CheckboxComponentType;
import io.cyborgcode.roa.ui.components.checkbox.CheckboxService;
import io.cyborgcode.roa.ui.components.checkbox.CheckboxServiceImpl;
import io.cyborgcode.roa.ui.components.input.InputComponentType;
import io.cyborgcode.roa.ui.components.input.InputService;
import io.cyborgcode.roa.ui.components.input.InputServiceImpl;
import io.cyborgcode.roa.ui.components.link.LinkComponentType;
import io.cyborgcode.roa.ui.components.link.LinkService;
import io.cyborgcode.roa.ui.components.link.LinkServiceImpl;
import io.cyborgcode.roa.ui.components.list.ItemListComponentType;
import io.cyborgcode.roa.ui.components.list.ItemListService;
import io.cyborgcode.roa.ui.components.list.ItemListServiceImpl;
import io.cyborgcode.roa.ui.components.loader.LoaderService;
import io.cyborgcode.roa.ui.components.loader.LoaderServiceImpl;
import io.cyborgcode.roa.ui.components.radio.RadioComponentType;
import io.cyborgcode.roa.ui.components.radio.RadioService;
import io.cyborgcode.roa.ui.components.radio.RadioServiceImpl;
import io.cyborgcode.roa.ui.components.select.SelectComponentType;
import io.cyborgcode.roa.ui.components.select.SelectService;
import io.cyborgcode.roa.ui.components.select.SelectServiceImpl;
import io.cyborgcode.roa.ui.components.tab.TabService;
import io.cyborgcode.roa.ui.components.tab.TabServiceImpl;
import io.cyborgcode.roa.ui.components.table.filters.TableFilter;
import io.cyborgcode.roa.ui.components.table.insertion.TableInsertion;
import io.cyborgcode.roa.ui.components.table.registry.TableServiceRegistry;
import io.cyborgcode.roa.ui.components.table.service.TableService;
import io.cyborgcode.roa.ui.components.table.service.TableServiceImpl;
import io.cyborgcode.roa.ui.components.toggle.ToggleService;
import io.cyborgcode.roa.ui.components.toggle.ToggleServiceImpl;
import io.cyborgcode.roa.ui.insertion.InsertionService;
import io.cyborgcode.roa.ui.insertion.InsertionServiceFieldImpl;
import io.cyborgcode.roa.ui.insertion.InsertionServiceRegistry;
import io.cyborgcode.roa.ui.selenium.smart.SmartWebDriver;
import io.cyborgcode.roa.ui.validator.UiTableValidator;
import io.cyborgcode.roa.ui.validator.UiTableValidatorImpl;
import lombok.Getter;

/**
 * Centralized UI service facade that provides access to various UI component services.
 *
 * <p>This class serves as a single entry point for interacting with different UI components
 * such as input fields, buttons, checkboxes, toggles, tables, and more.
 * It initializes and manages instances of these services, allowing simplified access
 * throughout the application.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@Getter
public class UiService {

   private final SmartWebDriver driver;
   private final InputService inputField;
   private final ButtonService buttonField;
   private final RadioService radioField;
   private final CheckboxService checkboxField;
   private final ToggleService toggleField;
   private final SelectService selectField;
   private final ItemListService listField;
   private final LoaderService loaderField;
   private final LinkService linkField;
   private final AlertService alertField;
   private final TabService tabField;
   private final InsertionServiceRegistry serviceRegistry;
   private final InsertionService insertionService;
   private final TableServiceRegistry tableServiceRegistry;
   private final TableService tableService;
   private final UiTableValidator uiTableValidator;

   /**
    * Constructs the UI service, initializing various UI component services.
    *
    * @param driver The {@link SmartWebDriver} instance used for UI interactions.
    */
   public UiService(SmartWebDriver driver) {
      this.driver = driver;
      inputField = new InputServiceImpl(driver);
      buttonField = new ButtonServiceImpl(driver);
      radioField = new RadioServiceImpl(driver);
      selectField = new SelectServiceImpl(driver);
      listField = new ItemListServiceImpl(driver);
      loaderField = new LoaderServiceImpl(driver);
      linkField = new LinkServiceImpl(driver);
      alertField = new AlertServiceImpl(driver);
      tabField = new TabServiceImpl(driver);
      checkboxField = new CheckboxServiceImpl(driver);
      toggleField = new ToggleServiceImpl(driver);
      serviceRegistry = new InsertionServiceRegistry();
      tableServiceRegistry = new TableServiceRegistry();
      registerInsertionServices();
      insertionService = new InsertionServiceFieldImpl(serviceRegistry);
      uiTableValidator = new UiTableValidatorImpl();
      tableService = new TableServiceImpl(driver, tableServiceRegistry, uiTableValidator);
   }

   /**
    * Registers various UI component services to the respective registries.
    *
    * <p>This method ensures that UI components such as input fields, radio buttons,
    * checkboxes, select fields, item lists, and table components are registered
    * so they can be properly managed and accessed throughout the application.
    */
   private void registerInsertionServices() {
      serviceRegistry.registerService(InputComponentType.class, inputField);
      serviceRegistry.registerService(RadioComponentType.class, radioField);
      serviceRegistry.registerService(CheckboxComponentType.class, checkboxField);
      serviceRegistry.registerService(SelectComponentType.class, selectField);
      serviceRegistry.registerService(ItemListComponentType.class, listField);
      tableServiceRegistry.registerService(ButtonComponentType.class, buttonField);
      tableServiceRegistry.registerService(LinkComponentType.class, linkField);
      tableServiceRegistry.registerService(InputComponentType.class, (TableFilter) inputField);
      tableServiceRegistry.registerService(InputComponentType.class, (TableInsertion) inputField);
   }

}
