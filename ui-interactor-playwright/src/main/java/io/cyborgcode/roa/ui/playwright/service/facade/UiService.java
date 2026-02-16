package io.cyborgcode.roa.ui.playwright.service.facade;

import io.cyborgcode.roa.ui.playwright.components.accordion.AccordionService;
import io.cyborgcode.roa.ui.playwright.components.accordion.AccordionServiceImpl;
import io.cyborgcode.roa.ui.playwright.components.alert.AlertService;
import io.cyborgcode.roa.ui.playwright.components.alert.AlertServiceImpl;
import io.cyborgcode.roa.ui.playwright.components.button.ButtonServiceImpl;
import io.cyborgcode.roa.ui.playwright.components.button.ButtonService;
import io.cyborgcode.roa.ui.playwright.components.checkbox.CheckboxService;
import io.cyborgcode.roa.ui.playwright.components.checkbox.CheckboxServiceImpl;
import io.cyborgcode.roa.ui.playwright.components.input.InputService;
import io.cyborgcode.roa.ui.playwright.components.input.InputServiceImpl;
import io.cyborgcode.roa.ui.playwright.components.link.LinkService;
import io.cyborgcode.roa.ui.playwright.components.link.LinkServiceImpl;
import io.cyborgcode.roa.ui.playwright.components.list.ItemListService;
import io.cyborgcode.roa.ui.playwright.components.list.ItemListServiceImpl;
import io.cyborgcode.roa.ui.playwright.components.loader.LoaderService;
import io.cyborgcode.roa.ui.playwright.components.loader.LoaderServiceImpl;
import io.cyborgcode.roa.ui.playwright.components.modal.ModalService;
import io.cyborgcode.roa.ui.playwright.components.modal.ModalServiceImpl;
import io.cyborgcode.roa.ui.playwright.components.radio.RadioService;
import io.cyborgcode.roa.ui.playwright.components.radio.RadioServiceImpl;
import io.cyborgcode.roa.ui.playwright.components.select.SelectService;
import io.cyborgcode.roa.ui.playwright.components.select.SelectServiceImpl;
import io.cyborgcode.roa.ui.playwright.components.tab.TabService;
import io.cyborgcode.roa.ui.playwright.components.tab.TabServiceImpl;
import io.cyborgcode.roa.ui.playwright.components.toggle.ToggleService;
import io.cyborgcode.roa.ui.playwright.components.toggle.ToggleServiceImpl;
import io.cyborgcode.roa.ui.playwright.components.table.filters.TableFilter;
import io.cyborgcode.roa.ui.playwright.components.table.insertion.TableInsertion;
import io.cyborgcode.roa.ui.playwright.components.table.registry.TableServiceRegistry;
import io.cyborgcode.roa.ui.playwright.components.table.service.TableService;
import io.cyborgcode.roa.ui.playwright.components.table.service.TableServiceImpl;
import io.cyborgcode.roa.ui.playwright.insertion.InsertionService;
import io.cyborgcode.roa.ui.playwright.insertion.InsertionServiceFieldImpl;
import io.cyborgcode.roa.ui.playwright.insertion.InsertionServiceRegistry;
import io.cyborgcode.roa.ui.playwright.session.UISession;
import com.microsoft.playwright.Page;
import lombok.Getter;

/**
 * Centralized UI service facade that provides access to various Playwright UI component services.
 *
 * <p>This class serves as a single entry point for interacting with different UI components
 * such as input fields, buttons, checkboxes, toggles, and more.
 * It initializes and manages instances of these services, allowing simplified access
 * throughout the application.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@Getter
public class UiService {

   private final UISession session;
   private final Page page;
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
   private final AccordionService accordionField;
   private final ModalService modalField;
   private final InsertionServiceRegistry serviceRegistry;
   private final InsertionService insertionService;
   private final TableServiceRegistry tableServiceRegistry;
   private final TableService tableService;

   /**
    * Constructs the UI service, initializing various UI component services.
    *
    * @param session The {@link UISession} instance containing the Playwright session.
    */
   public UiService(final UISession session) {
      this.session = session;
      this.page = session.getPage();
      inputField = new InputServiceImpl(page);
      buttonField = new ButtonServiceImpl(page);
      radioField = new RadioServiceImpl(page);
      selectField = new SelectServiceImpl(page);
      listField = new ItemListServiceImpl(page);
      loaderField = new LoaderServiceImpl(page);
      linkField = new LinkServiceImpl(page);
      alertField = new AlertServiceImpl(page);
      tabField = new TabServiceImpl(page);
      accordionField = new AccordionServiceImpl(page);
      modalField = new ModalServiceImpl(page);
      checkboxField = new CheckboxServiceImpl(page);
      toggleField = new ToggleServiceImpl(page);
      serviceRegistry = new InsertionServiceRegistry();
      tableServiceRegistry = new TableServiceRegistry();
      registerInsertionServices();
      insertionService = new InsertionServiceFieldImpl(serviceRegistry);
      tableService = new TableServiceImpl(page, tableServiceRegistry);
   }

   /**
    * Constructs the UI service with a Page directly.
    *
    * @param Page The {@link Page} instance for UI interactions.
    */
   public UiService(final Page page) {
      this.session = null;
      this.page = page;
      inputField = new InputServiceImpl(page);
      buttonField = new ButtonServiceImpl(page);
      radioField = new RadioServiceImpl(page);
      selectField = new SelectServiceImpl(page);
      listField = new ItemListServiceImpl(page);
      loaderField = new LoaderServiceImpl(page);
      linkField = new LinkServiceImpl(page);
      alertField = new AlertServiceImpl(page);
      tabField = new TabServiceImpl(page);
      accordionField = new AccordionServiceImpl(page);
      modalField = new ModalServiceImpl(page);
      checkboxField = new CheckboxServiceImpl(page);
      toggleField = new ToggleServiceImpl(page);
      serviceRegistry = new InsertionServiceRegistry();
      tableServiceRegistry = new TableServiceRegistry();
      registerInsertionServices();
      insertionService = new InsertionServiceFieldImpl(serviceRegistry);
      tableService = new TableServiceImpl(page, tableServiceRegistry);
   }

   /**
    * Registers various UI component services to the respective registries.
    */
   private void registerInsertionServices() {
      serviceRegistry.registerService(
            io.cyborgcode.roa.ui.playwright.components.input.InputComponentType.class, inputField);
      serviceRegistry.registerService(
            io.cyborgcode.roa.ui.playwright.components.radio.RadioComponentType.class, radioField);
      serviceRegistry.registerService(
            io.cyborgcode.roa.ui.playwright.components.checkbox.CheckboxComponentType.class, checkboxField);
      serviceRegistry.registerService(
            io.cyborgcode.roa.ui.playwright.components.select.SelectComponentType.class, selectField);
      serviceRegistry.registerService(
            io.cyborgcode.roa.ui.playwright.components.list.ItemListComponentType.class, listField);
      tableServiceRegistry.registerService(
            io.cyborgcode.roa.ui.playwright.components.button.ButtonComponentType.class, buttonField);
      tableServiceRegistry.registerService(
            io.cyborgcode.roa.ui.playwright.components.link.LinkComponentType.class, linkField);
      tableServiceRegistry.registerService(
            io.cyborgcode.roa.ui.playwright.components.input.InputComponentType.class, (TableFilter) inputField);
      tableServiceRegistry.registerService(
            io.cyborgcode.roa.ui.playwright.components.input.InputComponentType.class, (TableInsertion) inputField);
   }

}
