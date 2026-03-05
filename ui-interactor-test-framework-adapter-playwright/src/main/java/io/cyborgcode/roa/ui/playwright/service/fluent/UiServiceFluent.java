package io.cyborgcode.roa.ui.playwright.service.fluent;

import com.microsoft.playwright.Page;
import io.cyborgcode.roa.framework.annotation.Ring;
import io.cyborgcode.roa.ui.components.table.registry.TableServiceRegistry;
import io.cyborgcode.roa.ui.components.table.service.TableServiceImplCore;
import io.cyborgcode.roa.ui.insertion.InsertionServiceRegistry;
import io.cyborgcode.roa.ui.playwright.base.PwBy;
import io.cyborgcode.roa.ui.playwright.base.PwElement;
import io.cyborgcode.roa.ui.playwright.components.accordion.AccordionServiceImpl;
import io.cyborgcode.roa.ui.playwright.components.alert.AlertServiceImpl;
import io.cyborgcode.roa.ui.playwright.components.button.ButtonServiceImpl;
import io.cyborgcode.roa.ui.playwright.components.checkbox.CheckboxServiceImpl;
import io.cyborgcode.roa.ui.playwright.components.input.InputServiceImpl;
import io.cyborgcode.roa.ui.playwright.components.link.LinkServiceImpl;
import io.cyborgcode.roa.ui.playwright.components.list.ItemListServiceImpl;
import io.cyborgcode.roa.ui.playwright.components.loader.LoaderServiceImpl;
import io.cyborgcode.roa.ui.playwright.components.modal.ModalServiceImpl;
import io.cyborgcode.roa.ui.playwright.components.radio.RadioServiceImpl;
import io.cyborgcode.roa.ui.playwright.components.select.SelectServiceImpl;
import io.cyborgcode.roa.ui.playwright.components.tab.TabServiceImpl;
import io.cyborgcode.roa.ui.playwright.components.table.service.TableServiceImpl;
import io.cyborgcode.roa.ui.playwright.components.toggle.ToggleServiceImpl;
import io.cyborgcode.roa.ui.playwright.service.InsertionServiceElementImpl;
import io.cyborgcode.roa.ui.playwright.session.UiSession;
import io.cyborgcode.roa.ui.service.InsertionServiceElementImplCore;
import io.cyborgcode.roa.ui.service.fluent.UiServiceFluentCore;
import io.cyborgcode.roa.ui.validator.UiTableValidator;
import io.cyborgcode.roa.validator.core.AssertionResult;
import java.util.List;
import java.util.function.Consumer;
import lombok.Getter;
import org.assertj.core.api.SoftAssertions;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Provides a fluent interface for Playwright-based UI interactions, encapsulating various UI services
 * such as buttons, inputs, checkboxes, tables, and more.
 *
 * <p>This class binds the core {@link UiServiceFluentCore} to Playwright-specific types:
 * {@link PwBy} as the locator, {@link Page} as the driver, and {@link PwElement} as the element type.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@Ring("UI")
@Getter
public class UiServiceFluent<T extends UiServiceFluent<T>> extends UiServiceFluentCore<PwBy, Page, PwElement, T> {


   private final UiSession uiSession;
   /**
    * Constructs a new {@code UiServiceFluent} instance with the specified Playwright Page.
    *
    * @param uiSession The Playwright {@link UiSession} instance used for UI interactions.
    */
   @Autowired
   public UiServiceFluent(UiSession uiSession) {
      super(uiSession.getPage());
      this.uiSession = uiSession;
   }

   /**
    * Executes a validation assertion.
    *
    * @param assertion The assertion to validate.
    * @return The current instance for method chaining.
    */
   @Override
   public T validate(Runnable assertion) {
      return (T) super.validate(assertion);
   }

   /**
    * Executes a validation assertion using a soft assertion approach.
    *
    * @param assertion The assertion to validate with soft assertions.
    * @return The current instance for method chaining.
    */
   @Override
   public T validate(Consumer<SoftAssertions> assertion) {
      return (T) super.validate(assertion);
   }


   @Override
   protected InputServiceImpl inputService() {
      return new InputServiceImpl(getDriver());
   }

   @Override
   protected ButtonServiceImpl buttonService() {
      return new ButtonServiceImpl(getDriver());
   }

   @Override
   protected RadioServiceImpl radioService() {
      return new RadioServiceImpl(getDriver());
   }

   @Override
   protected CheckboxServiceImpl checkboxService() {
      return new CheckboxServiceImpl(getDriver());
   }

   @Override
   protected SelectServiceImpl selectService() {
      return new SelectServiceImpl(getDriver());
   }

   @Override
   protected ItemListServiceImpl listService() {
      return new ItemListServiceImpl(getDriver());
   }

   @Override
   protected LoaderServiceImpl loaderService() {
      return new LoaderServiceImpl(getDriver());
   }

   @Override
   protected LinkServiceImpl linkService() {
      return new LinkServiceImpl(getDriver());
   }

   @Override
   protected AlertServiceImpl alertService() {
      return new AlertServiceImpl(getDriver());
   }

   @Override
   protected TabServiceImpl tabService() {
      return new TabServiceImpl(getDriver());
   }

   @Override
   protected ToggleServiceImpl toggleService() {
      return new ToggleServiceImpl(getDriver());
   }

   @Override
   protected ModalServiceImpl modalService() {
      return new ModalServiceImpl(getDriver());
   }

   @Override
   protected AccordionServiceImpl accordionService() {
      return new AccordionServiceImpl(getDriver());
   }

   @Override
   protected TableServiceImplCore<Page, PwElement> tableServiceImpl(
         TableServiceRegistry<PwElement> tableServiceRegistry, UiTableValidator uiTableValidator) {
      return new TableServiceImpl(getDriver(), tableServiceRegistry, uiTableValidator);
   }

   @Override
   protected InsertionServiceElementImplCore<Page, PwBy> insertionServiceElement(
         InsertionServiceRegistry<PwBy> serviceRegistry) {
      return new InsertionServiceElementImpl(serviceRegistry, getDriver());
   }

   /**
    * Initializes the necessary Playwright UI services and registers them for UI interactions.
    * This method is automatically called after setup.
    */
   @Override
   @SuppressWarnings("java:S3740")
   protected void postQuestSetupInitialization() {
      super.postQuestSetupInitialization();
      inputField = new InputServiceFluent<>(self(), quest.getStorage(), inputService(), getDriver());
      buttonField = new ButtonServiceFluent<>(self(), quest.getStorage(), buttonService(), getDriver());
      radioField = new RadioServiceFluent<>(self(), quest.getStorage(), radioService(), getDriver());
      checkboxField = new CheckboxServiceFluent<>(self(), quest.getStorage(), checkboxService(), getDriver());
      selectField = new SelectServiceFluent<>(self(), quest.getStorage(), selectService(), getDriver());
      listField = new ListServiceFluent<>(self(), quest.getStorage(), listService(), getDriver());
      loaderField = new LoaderServiceFluent<>(self(), quest.getStorage(), loaderService(), getDriver());
      linkField = new LinkServiceFluent<>(self(), quest.getStorage(), linkService(), getDriver());
      alertField = new AlertServiceFluent<>(self(), quest.getStorage(), alertService(), getDriver());
      tabField = new TabServiceFluent<>(self(), quest.getStorage(), tabService(), getDriver());
      toggleField = new ToggleServiceFluent<>(self(), quest.getStorage(), toggleService(), getDriver());
      modalField = new ModalServiceFluent<>(self(), quest.getStorage(), modalService(), getDriver());
      accordionField = new AccordionServiceFluent<>(self(), quest.getStorage(), accordionService(), getDriver());
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

}
