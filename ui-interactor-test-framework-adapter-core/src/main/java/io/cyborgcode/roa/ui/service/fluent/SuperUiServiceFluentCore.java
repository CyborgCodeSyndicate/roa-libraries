package io.cyborgcode.roa.ui.service.fluent;

import io.cyborgcode.roa.framework.quest.SuperQuest;
import io.cyborgcode.roa.ui.components.accordion.AccordionCore;
import io.cyborgcode.roa.ui.components.accordion.AccordionServiceImplCore;
import io.cyborgcode.roa.ui.components.alert.AlertCore;
import io.cyborgcode.roa.ui.components.alert.AlertServiceImplCore;
import io.cyborgcode.roa.ui.components.base.BaseUiElement;
import io.cyborgcode.roa.ui.components.button.ButtonCore;
import io.cyborgcode.roa.ui.components.button.ButtonServiceImplCore;
import io.cyborgcode.roa.ui.components.checkbox.CheckboxCore;
import io.cyborgcode.roa.ui.components.checkbox.CheckboxServiceImplCore;
import io.cyborgcode.roa.ui.components.input.InputCore;
import io.cyborgcode.roa.ui.components.input.InputServiceImplCore;
import io.cyborgcode.roa.ui.components.link.LinkCore;
import io.cyborgcode.roa.ui.components.link.LinkServiceImplCore;
import io.cyborgcode.roa.ui.components.list.ItemListCore;
import io.cyborgcode.roa.ui.components.list.ItemListServiceImplCore;
import io.cyborgcode.roa.ui.components.loader.LoaderCore;
import io.cyborgcode.roa.ui.components.loader.LoaderServiceImplCore;
import io.cyborgcode.roa.ui.components.modal.ModalCore;
import io.cyborgcode.roa.ui.components.modal.ModalServiceImplCore;
import io.cyborgcode.roa.ui.components.radio.RadioCore;
import io.cyborgcode.roa.ui.components.radio.RadioServiceImplCore;
import io.cyborgcode.roa.ui.components.select.SelectCore;
import io.cyborgcode.roa.ui.components.select.SelectServiceImplCore;
import io.cyborgcode.roa.ui.components.tab.TabCore;
import io.cyborgcode.roa.ui.components.tab.TabServiceImplCore;
import io.cyborgcode.roa.ui.components.table.registry.TableServiceRegistry;
import io.cyborgcode.roa.ui.components.table.service.TableServiceImplCore;
import io.cyborgcode.roa.ui.components.toggle.ToggleCore;
import io.cyborgcode.roa.ui.components.toggle.ToggleServiceImplCore;
import io.cyborgcode.roa.ui.insertion.InsertionServiceRegistry;
import io.cyborgcode.roa.ui.service.InsertionServiceElementImplCore;
import io.cyborgcode.roa.ui.validator.UiTableValidator;
import io.cyborgcode.roa.validator.core.AssertionResult;
import java.util.List;

/**
 * A specialized UI service fluent class that extends {@link UiServiceFluentCore}
 * and delegates its functionalities to an existing instance of {@code UIServiceFluent}.
 *
 * <p>The generic type {@code ORIG} represents a concrete implementation of {@link UiServiceFluentCore},
 * ensuring fluent interface compatibility and enabling seamless method chaining.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public abstract class SuperUiServiceFluentCore<
      L,
      D,
      E extends BaseUiElement,
      ORIG extends UiServiceFluentCore<L, D, E, ORIG>
      > extends UiServiceFluentCore<L, D, E, ORIG> {

   protected final ORIG original;

   protected SuperUiServiceFluentCore(ORIG uiServiceFluent) {
      super(uiServiceFluent.getDriver());
      this.original = uiServiceFluent;
   }

   public ORIG original() {
      return original;
   }

   @Override
   public D getDriver() {
      return original.getDriver();
   }

   @Override
   protected void setQuest(SuperQuest quest) {
      super.setQuest(quest);
      original.setQuest(quest);
   }

   @Override
   public void validation(List<AssertionResult<Object>> assertionResults) {
      original.validation(assertionResults);
   }

   @Override
   protected void postQuestSetupInitialization() {
      original.postQuestSetupInitialization();
   }

   @Override
   protected <T extends InputServiceImplCore<E, C, D, L>, C extends InputCore<E, L>> T inputService() {
      return original.inputService();
   }

   @Override
   protected <T extends ButtonServiceImplCore<E, C, D, L>, C extends ButtonCore<E, L>> T buttonService() {
      return original.buttonService();
   }

   @Override
   protected <T extends RadioServiceImplCore<E, C, D, L>, C extends RadioCore<E, L>> T radioService() {
      return original.radioService();
   }

   @Override
   protected <T extends CheckboxServiceImplCore<E, C, D, L>, C extends CheckboxCore<E, L>> T checkboxService() {
      return original.checkboxService();
   }

   @Override
   protected <T extends SelectServiceImplCore<E, C, D, L>, C extends SelectCore<E, L>> T selectService() {
      return original.selectService();
   }

   @Override
   protected <T extends ItemListServiceImplCore<E, C, D, L>, C extends ItemListCore<E, L>> T listService() {
      return original.listService();
   }

   @Override
   protected <T extends LoaderServiceImplCore<E, C, D, L>, C extends LoaderCore<E, L>> T loaderService() {
      return original.loaderService();
   }

   @Override
   protected <T extends LinkServiceImplCore<E, C, D, L>, C extends LinkCore<E, L>> T linkService() {
      return original.linkService();
   }

   @Override
   protected <T extends AlertServiceImplCore<E, C, D, L>, C extends AlertCore<E, L>> T alertService() {
      return original.alertService();
   }

   @Override
   protected <T extends TabServiceImplCore<E, C, D, L>, C extends TabCore<E, L>> T tabService() {
      return original.tabService();
   }

   @Override
   protected <T extends ToggleServiceImplCore<E, C, D, L>, C extends ToggleCore<E, L>> T toggleService() {
      return original.toggleService();
   }

   @Override
   protected <T extends ModalServiceImplCore<E, C, D, L>, C extends ModalCore<E, L>> T modalService() {
      return original.modalService();
   }

   @Override
   protected <T extends AccordionServiceImplCore<E, C, D, L>, C extends AccordionCore<E, L>> T accordionService() {
      return original.accordionService();
   }

   @Override
   protected TableServiceImplCore<D, E> tableServiceImpl(TableServiceRegistry<E> tableServiceRegistry,
                                                          UiTableValidator uiTableValidator) {
      return original.tableServiceImpl(tableServiceRegistry, uiTableValidator);
   }

   @Override
   protected InsertionServiceElementImplCore<D, L> insertionServiceElement(
         InsertionServiceRegistry<L> serviceRegistry) {
      return original.insertionServiceElement(serviceRegistry);
   }
}
