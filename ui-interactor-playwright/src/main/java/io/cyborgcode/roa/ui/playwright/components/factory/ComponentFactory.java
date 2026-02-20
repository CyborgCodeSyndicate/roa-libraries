package io.cyborgcode.roa.ui.playwright.components.factory;

import com.microsoft.playwright.Page;
import io.cyborgcode.roa.ui.annotations.ImplementationOfType;
import io.cyborgcode.roa.ui.components.accordion.AccordionComponentType;
import io.cyborgcode.roa.ui.components.alert.AlertComponentType;
import io.cyborgcode.roa.ui.components.button.ButtonComponentType;
import io.cyborgcode.roa.ui.components.checkbox.CheckboxComponentType;
import io.cyborgcode.roa.ui.components.input.InputComponentType;
import io.cyborgcode.roa.ui.components.link.LinkComponentType;
import io.cyborgcode.roa.ui.components.list.ItemListComponentType;
import io.cyborgcode.roa.ui.components.loader.LoaderComponentType;
import io.cyborgcode.roa.ui.components.modal.ModalComponentType;
import io.cyborgcode.roa.ui.components.radio.RadioComponentType;
import io.cyborgcode.roa.ui.components.select.SelectComponentType;
import io.cyborgcode.roa.ui.components.tab.TabComponentType;
import io.cyborgcode.roa.ui.components.table.base.TableComponentType;
import io.cyborgcode.roa.ui.components.table.service.Table;
import io.cyborgcode.roa.ui.components.toggle.ToggleComponentType;
import io.cyborgcode.roa.ui.playwright.components.accordion.Accordion;
import io.cyborgcode.roa.ui.playwright.components.alert.Alert;
import io.cyborgcode.roa.ui.playwright.components.button.Button;
import io.cyborgcode.roa.ui.playwright.components.checkbox.Checkbox;
import io.cyborgcode.roa.ui.playwright.components.input.Input;
import io.cyborgcode.roa.ui.playwright.components.link.Link;
import io.cyborgcode.roa.ui.playwright.components.list.ItemList;
import io.cyborgcode.roa.ui.playwright.components.loader.Loader;
import io.cyborgcode.roa.ui.playwright.components.modal.Modal;
import io.cyborgcode.roa.ui.playwright.components.radio.Radio;
import io.cyborgcode.roa.ui.playwright.components.select.Select;
import io.cyborgcode.roa.ui.playwright.components.tab.Tab;
import io.cyborgcode.roa.ui.playwright.components.toggle.Toggle;

import static io.cyborgcode.roa.ui.components.factory.ComponentFactoryCore.getComponent;
import static io.cyborgcode.roa.ui.config.UiConfigHolderCore.getUiConfig;

/**
 * Factory class responsible for dynamically creating Playwright UI component instances.
 *
 * <p>This class retrieves component instances based on their type using reflection.
 * It scans the project package and framework package for implementations of UI components
 * that are annotated with {@link ImplementationOfType}.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public class ComponentFactory {

   /**
    * Private constructor to prevent instantiation.
    */
   private ComponentFactory() {
   }

   /**
    * Retrieves an {@code Input} component of the specified type.
    *
    * @param type The type of input component.
    * @param page The {@code Page} instance used for UI interactions.
    * @return The corresponding {@code Input} component instance.
    */
   public static Input getInputComponent(InputComponentType type, Page page) {
      return getComponent(Input.class, type, getUiConfig().projectPackages(), page);
   }

   /**
    * Retrieves a {@code Button} component of the specified type.
    *
    * @param type The type of button component.
    * @param page The {@code Page} instance used for UI interactions.
    * @return The corresponding {@code Button} component instance.
    */
   public static Button getButtonComponent(ButtonComponentType type, Page page) {
      return getComponent(Button.class, type, getUiConfig().projectPackages(), page);
   }

   /**
    * Retrieves a {@code Radio} component of the specified type.
    *
    * @param type The type of radio component.
    * @param page The {@code Page} instance used for UI interactions.
    * @return The corresponding {@code Radio} component instance.
    */
   public static Radio getRadioComponent(RadioComponentType type, Page page) {
      return getComponent(Radio.class, type, getUiConfig().projectPackages(), page);
   }

   /**
    * Retrieves a {@code Select} component of the specified type.
    *
    * @param type The type of select component.
    * @param page The {@code Page} instance used for UI interactions.
    * @return The corresponding {@code Select} component instance.
    */
   public static Select getSelectComponent(SelectComponentType type, Page page) {
      return getComponent(Select.class, type, getUiConfig().projectPackages(), page);
   }

   /**
    * Retrieves a {@code ItemList} component of the specified type.
    *
    * @param type The type of list component.
    * @param page The {@code Page} instance used for UI interactions.
    * @return The corresponding {@code ItemList} component instance.
    */
   public static ItemList getListComponent(ItemListComponentType type, Page page) {
      return getComponent(ItemList.class, type, getUiConfig().projectPackages(), page);
   }

   /**
    * Retrieves a {@code Loader} component of the specified type.
    *
    * @param type The type of loader component.
    * @param page The {@code Page} instance used for UI interactions.
    * @return The corresponding {@code Loader} component instance.
    */
   public static Loader getLoaderComponent(LoaderComponentType type, Page page) {
      return getComponent(Loader.class, type, getUiConfig().projectPackages(), page);
   }

   /**
    * Retrieves a {@code Link} component of the specified type.
    *
    * @param type The type of link component.
    * @param page The {@code Page} instance used for UI interactions.
    * @return The corresponding {@code Link} component instance.
    */
   public static Link getLinkComponent(LinkComponentType type, Page page) {
      return getComponent(Link.class, type, getUiConfig().projectPackages(), page);
   }

   /**
    * Retrieves an {@code Alert} component of the specified type.
    *
    * @param type The type of alert component.
    * @param page The {@code Page} instance used for UI interactions.
    * @return The corresponding {@code Alert} component instance.
    */
   public static Alert getAlertComponent(AlertComponentType type, Page page) {
      return getComponent(Alert.class, type, getUiConfig().projectPackages(), page);
   }

   /**
    * Retrieves a {@code Tab} component of the specified type.
    *
    * @param type The type of tab component.
    * @param page The {@code Page} instance used for UI interactions.
    * @return The corresponding {@code Tab} component instance.
    */
   public static Tab getTabComponent(TabComponentType type, Page page) {
      return getComponent(Tab.class, type, getUiConfig().projectPackages(), page);
   }

   /**
    * Retrieves a {@code Checkbox} component of the specified type.
    *
    * @param type The type of checkbox component.
    * @param page The {@code Page} instance used for UI interactions.
    * @return The corresponding {@code Checkbox} component instance.
    */
   public static Checkbox getCheckBoxComponent(CheckboxComponentType type, Page page) {
      return getComponent(Checkbox.class, type, getUiConfig().projectPackages(), page);
   }

   /**
    * Retrieves a {@code Toggle} component of the specified type.
    *
    * @param type The type of toggle component.
    * @param page The {@code Page} instance used for UI interactions.
    * @return The corresponding {@code Toggle} component instance.
    */
   public static Toggle getToggleComponent(ToggleComponentType type, Page page) {
      return getComponent(Toggle.class, type, getUiConfig().projectPackages(), page);
   }

   /**
    * Retrieves a {@code Modal} component of the specified type.
    *
    * @param type The type of modal component.
    * @param page The {@code Page} instance used for UI interactions.
    * @return The corresponding {@code Modal} component instance.
    */
   public static Modal getModalComponent(ModalComponentType type, Page page) {
      return getComponent(Modal.class, type, getUiConfig().projectPackages(), page);
   }

   /**
    * Retrieves an {@code Accordion} component of the specified type.
    *
    * @param type The type of accordion component.
    * @param page The {@code Page} instance used for UI interactions.
    * @return The corresponding {@code Accordion} component instance.
    */
   public static Accordion getAccordionComponent(AccordionComponentType type, Page page) {
      return getComponent(Accordion.class, type, getUiConfig().projectPackages(), page);
   }

   /**
    * Retrieves a {@code Table} component of the specified type.
    *
    * @param type The type of table component.
    * @param page The {@code Page} instance used for UI interactions.
    * @return The corresponding {@code Table} component instance.
    */
   public static Table getTableComponent(
         TableComponentType type, Page page) {
      return getComponent(Table.class, type,
            getUiConfig().projectPackages(), page);
   }


}
