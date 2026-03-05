package io.cyborgcode.roa.ui.service.fluent;

import io.cyborgcode.roa.framework.annotation.Ring;
import io.cyborgcode.roa.framework.chain.FluentService;
import io.cyborgcode.roa.framework.quest.SuperQuest;
import io.cyborgcode.roa.ui.components.accordion.AccordionCore;
import io.cyborgcode.roa.ui.components.accordion.AccordionServiceImplCore;
import io.cyborgcode.roa.ui.components.alert.AlertCore;
import io.cyborgcode.roa.ui.components.alert.AlertServiceImplCore;
import io.cyborgcode.roa.ui.components.base.BaseUiElement;
import io.cyborgcode.roa.ui.components.button.ButtonComponentType;
import io.cyborgcode.roa.ui.components.button.ButtonCore;
import io.cyborgcode.roa.ui.components.button.ButtonServiceCore;
import io.cyborgcode.roa.ui.components.button.ButtonServiceImplCore;
import io.cyborgcode.roa.ui.components.checkbox.CheckboxComponentType;
import io.cyborgcode.roa.ui.components.checkbox.CheckboxCore;
import io.cyborgcode.roa.ui.components.checkbox.CheckboxServiceCore;
import io.cyborgcode.roa.ui.components.checkbox.CheckboxServiceImplCore;
import io.cyborgcode.roa.ui.components.input.InputComponentType;
import io.cyborgcode.roa.ui.components.input.InputCore;
import io.cyborgcode.roa.ui.components.input.InputServiceCore;
import io.cyborgcode.roa.ui.components.input.InputServiceImplCore;
import io.cyborgcode.roa.ui.components.link.LinkComponentType;
import io.cyborgcode.roa.ui.components.link.LinkCore;
import io.cyborgcode.roa.ui.components.link.LinkServiceCore;
import io.cyborgcode.roa.ui.components.link.LinkServiceImplCore;
import io.cyborgcode.roa.ui.components.list.ItemListComponentType;
import io.cyborgcode.roa.ui.components.list.ItemListCore;
import io.cyborgcode.roa.ui.components.list.ItemListServiceCore;
import io.cyborgcode.roa.ui.components.list.ItemListServiceImplCore;
import io.cyborgcode.roa.ui.components.loader.LoaderCore;
import io.cyborgcode.roa.ui.components.loader.LoaderServiceImplCore;
import io.cyborgcode.roa.ui.components.modal.ModalCore;
import io.cyborgcode.roa.ui.components.modal.ModalServiceImplCore;
import io.cyborgcode.roa.ui.components.radio.RadioComponentType;
import io.cyborgcode.roa.ui.components.radio.RadioCore;
import io.cyborgcode.roa.ui.components.radio.RadioServiceCore;
import io.cyborgcode.roa.ui.components.radio.RadioServiceImplCore;
import io.cyborgcode.roa.ui.components.select.SelectComponentType;
import io.cyborgcode.roa.ui.components.select.SelectCore;
import io.cyborgcode.roa.ui.components.select.SelectServiceCore;
import io.cyborgcode.roa.ui.components.select.SelectServiceImplCore;
import io.cyborgcode.roa.ui.components.tab.TabCore;
import io.cyborgcode.roa.ui.components.tab.TabServiceImplCore;
import io.cyborgcode.roa.ui.components.table.filters.TableFilter;
import io.cyborgcode.roa.ui.components.table.insertion.TableInsertion;
import io.cyborgcode.roa.ui.components.table.registry.TableServiceRegistry;
import io.cyborgcode.roa.ui.components.table.service.TableServiceImplCore;
import io.cyborgcode.roa.ui.components.toggle.ToggleCore;
import io.cyborgcode.roa.ui.components.toggle.ToggleServiceImplCore;
import io.cyborgcode.roa.ui.insertion.InsertionServiceRegistry;
import io.cyborgcode.roa.ui.service.InsertionServiceElementImplCore;
import io.cyborgcode.roa.ui.service.tables.TableServiceFluentCore;
import io.cyborgcode.roa.ui.validator.UiTableValidator;
import io.cyborgcode.roa.ui.validator.UiTableValidatorImplCore;
import io.cyborgcode.roa.validator.core.AssertionResult;
import java.util.List;
import java.util.function.Consumer;
import lombok.Getter;
import org.assertj.core.api.SoftAssertions;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Provides a fluent interface for UI interactions, encapsulating various UI services
 * such as buttons, inputs, checkboxes, tables, and more.
 *
 * <p>This class serves as a core service for UI automation and validation, allowing
 * seamless interaction with UI components while maintaining fluent method chaining.
 * It extends {@link FluentService}, integrating common UI operations.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@Ring("UI")
@Getter
public abstract class UiServiceFluentCore<
      L,
      D,
      E extends BaseUiElement,
      SELF extends UiServiceFluentCore<L, D, E, SELF>
      > extends FluentService {

   protected InputServiceFluentCore<SELF, L, D, E> inputField;
   protected ButtonServiceFluentCore<SELF, L, D, E> buttonField;
   protected RadioServiceFluentCore<SELF, L, D, E> radioField;
   protected CheckboxServiceFluentCore<SELF, L, D, E> checkboxField;
   protected SelectServiceFluentCore<SELF, L, D, E> selectField;
   protected ListServiceFluentCore<SELF, L, D, E> listField;
   protected LoaderServiceFluentCore<SELF, L, D, E> loaderField;
   protected LinkServiceFluentCore<SELF, L, D, E> linkField;
   protected AlertServiceFluentCore<SELF, L, D, E> alertField;
   protected TabServiceFluentCore<SELF, L, D, E> tabField;
   protected ToggleServiceFluentCore<SELF, L, D, E> toggleField;
   protected ModalServiceFluentCore<SELF, L, D, E> modalField;
   protected AccordionServiceFluentCore<SELF, L, D, E> accordionField;
   protected TableServiceFluentCore<SELF, L, D, E> table;
   protected InterceptorServiceFluentCore<SELF> interceptor;
   protected InsertionServiceRegistry<L> serviceRegistry;
   protected TableServiceRegistry<E> tableServiceRegistry;
   protected InsertionServiceFluentCore<SELF> insertionService;
   @Getter
   private final D driver;

   /**
    * Constructs a new {@code UiServiceFluent} instance with the specified WebDriver.
    *
    * @param driver The {@link D} instance used for UI interactions.
    */
   @Autowired
   public UiServiceFluentCore(D driver) {
      this.driver = driver;
   }

   @Override
   @SuppressWarnings("java:S1185")
   protected void setQuest(SuperQuest quest) {
      super.setQuest(quest);
   }

   /**
    * Executes a validation assertion.
    *
    * @param assertion The assertion to validate.
    * @return The current instance of {@code UiServiceFluent} for method chaining.
    */
   @Override
   public SELF validate(Runnable assertion) {
      return (SELF) super.validate(assertion);
   }

   /**
    * Executes a validation assertion using a soft assertion approach.
    *
    * @param assertion The assertion to validate with soft assertions.
    * @return The current instance of {@code UiServiceFluent} for method chaining.
    */
   @Override
   public SELF validate(Consumer<SoftAssertions> assertion) {
      return (SELF) super.validate(assertion);
   }


   protected abstract <T extends InputServiceImplCore<E, C, D, L>, C extends InputCore<E, L>> T inputService();

   protected abstract <T extends ButtonServiceImplCore<E, C, D, L>, C extends ButtonCore<E, L>> T buttonService();

   protected abstract <T extends RadioServiceImplCore<E, C, D, L>, C extends RadioCore<E, L>> T radioService();

   protected abstract <T extends CheckboxServiceImplCore<E, C, D, L>, C extends CheckboxCore<E, L>> T checkboxService();

   protected abstract <T extends SelectServiceImplCore<E, C, D, L>, C extends SelectCore<E, L>> T selectService();

   protected abstract <T extends ItemListServiceImplCore<E, C, D, L>, C extends ItemListCore<E, L>> T listService();

   protected abstract <T extends LoaderServiceImplCore<E, C, D, L>, C extends LoaderCore<E, L>> T loaderService();

   protected abstract <T extends LinkServiceImplCore<E, C, D, L>, C extends LinkCore<E, L>> T linkService();

   protected abstract <T extends AlertServiceImplCore<E, C, D, L>, C extends AlertCore<E, L>> T alertService();

   protected abstract <T extends TabServiceImplCore<E, C, D, L>, C extends TabCore<E, L>> T tabService();

   protected abstract <T extends ToggleServiceImplCore<E, C, D, L>, C extends ToggleCore<E, L>> T toggleService();

   protected abstract <T extends ModalServiceImplCore<E, C, D, L>, C extends ModalCore<E, L>> T modalService();

   protected abstract <T extends AccordionServiceImplCore<E, C, D, L>, C extends AccordionCore<E, L>> T accordionService();

   /**
    * Creates a {@link TableServiceImplCore} for the given table service registry.
    * Subclasses must provide a framework-specific implementation.
    *
    * @param tableServiceRegistry The table service registry.
    * @return A framework-specific table service implementation.
    */
   protected abstract TableServiceImplCore<D, E> tableServiceImpl(TableServiceRegistry<E> tableServiceRegistry,
                                                                  UiTableValidator uiTableValidator);

   /**
    * Creates an {@link InsertionServiceElementImplCore} for the given insertion service registry.
    * Subclasses must provide a framework-specific implementation.
    *
    * @param serviceRegistry The insertion service registry.
    * @return A framework-specific insertion service element implementation.
    */
   protected abstract InsertionServiceElementImplCore<D, L> insertionServiceElement(
         InsertionServiceRegistry<L> serviceRegistry);

   /**
    * Initializes the necessary UI services and registers them for UI interactions.
    * This method is automatically called after setup.
    */
   @Override
   @SuppressWarnings("java:S3740")
   protected void postQuestSetupInitialization() {
      InputServiceCore<E, L> inputServiceImpl = inputService();
      ButtonServiceCore<E, L> buttonServiceImpl = buttonService();
      LinkServiceCore<E, L> linkServiceImpl = linkService();
      RadioServiceCore<E, L> radioServiceImpl = radioService();
      CheckboxServiceCore<E, L> checkboxServiceImpl = checkboxService();
      SelectServiceCore<E, L> selectServiceImpl = selectService();
      ItemListServiceCore<E, L> listServiceImpl = listService();

      inputField = new InputServiceFluentCore<>(self(), quest.getStorage(), inputServiceImpl, driver);
      buttonField = new ButtonServiceFluentCore<>(self(), quest.getStorage(), buttonServiceImpl, driver);
      radioField = new RadioServiceFluentCore<>(self(), quest.getStorage(), radioServiceImpl, driver);
      checkboxField = new CheckboxServiceFluentCore<>(self(), quest.getStorage(), checkboxServiceImpl, driver);
      selectField = new SelectServiceFluentCore<>(self(), quest.getStorage(), selectServiceImpl, driver);
      listField = new ListServiceFluentCore<>(self(), quest.getStorage(), listServiceImpl, driver);
      loaderField = new LoaderServiceFluentCore<>(self(), quest.getStorage(), loaderService(), driver);
      linkField = new LinkServiceFluentCore<>(self(), quest.getStorage(), linkServiceImpl, driver);
      alertField = new AlertServiceFluentCore<>(self(), quest.getStorage(), alertService(), driver);
      tabField = new TabServiceFluentCore<>(self(), quest.getStorage(), tabService(), driver);
      toggleField = new ToggleServiceFluentCore<>(self(), quest.getStorage(), toggleService(), driver);
      modalField = new ModalServiceFluentCore<>(self(), quest.getStorage(), modalService(), driver);
      accordionField = new AccordionServiceFluentCore<>(self(), quest.getStorage(), accordionService(), driver);
      interceptor = new InterceptorServiceFluentCore<>(self(), quest.getStorage());

      UiTableValidator uiTableValidator = new UiTableValidatorImplCore<E>();

      serviceRegistry = new InsertionServiceRegistry<>();
      registerInsertionServices(inputServiceImpl, radioServiceImpl, checkboxServiceImpl,
            selectServiceImpl, listServiceImpl);

      tableServiceRegistry = new TableServiceRegistry<>();
      registerTableServices(inputServiceImpl, buttonServiceImpl, linkServiceImpl);

      table = new TableServiceFluentCore<>(self(), quest.getStorage(),
            tableServiceImpl(tableServiceRegistry, uiTableValidator), driver);

      insertionService = new InsertionServiceFluentCore<>(
            insertionServiceElement(serviceRegistry), self(), quest.getStorage());
   }

   /**
    * Registers insertion services for different UI components.
    *
    * @param inputService    The input service to register.
    * @param radioService    The radio service to register.
    * @param checkboxService The checkbox service to register.
    * @param selectService   The select service to register.
    * @param listService     The list service to register.
    */
   @SuppressWarnings("unchecked")
   private void registerInsertionServices(InputServiceCore<E, L> inputService,
                                          RadioServiceCore<E, L> radioService,
                                          CheckboxServiceCore<E, L> checkboxService,
                                          SelectServiceCore<E, L> selectService,
                                          ItemListServiceCore<E, L> listService) {
      serviceRegistry.registerService(RadioComponentType.class, radioService);
      serviceRegistry.registerService(CheckboxComponentType.class, checkboxService);
      serviceRegistry.registerService(SelectComponentType.class, selectService);
      serviceRegistry.registerService(ItemListComponentType.class, listService);
      serviceRegistry.registerService(InputComponentType.class, inputService);
   }

   /**
    * Registers table services for different UI components.
    *
    * @param inputService  The input service to register.
    * @param buttonService The button service to register.
    * @param linkService   The link service to register.
    */
   @SuppressWarnings("unchecked")
   private void registerTableServices(InputServiceCore<E, L> inputService,
                                      ButtonServiceCore<E, L> buttonService,
                                      LinkServiceCore<E, L> linkService) {
      tableServiceRegistry.registerService(InputComponentType.class, (TableFilter<E>) inputService);
      tableServiceRegistry.registerService(InputComponentType.class, (TableInsertion<E>) inputService);
      tableServiceRegistry.registerService(ButtonComponentType.class, (TableInsertion<E>) buttonService);
      tableServiceRegistry.registerService(LinkComponentType.class, (TableInsertion<E>) linkService);
   }

   /**
    * Retrieves the {@link D} instance used for UI interactions.
    *
    * @return The {@link D} instance.
    */
   protected D getDriver() {
      return driver;
   }

   /**
    * Executes validation assertions on a list of results.
    *
    * @param assertionResults The list of assertion results.
    */
   @Override
   @SuppressWarnings("java:S1185")
   protected void validation(List<AssertionResult<Object>> assertionResults) {
      super.validation(assertionResults);
   }

   @SuppressWarnings("unchecked")
   protected final SELF self() {
      return (SELF) this;
   }

}
