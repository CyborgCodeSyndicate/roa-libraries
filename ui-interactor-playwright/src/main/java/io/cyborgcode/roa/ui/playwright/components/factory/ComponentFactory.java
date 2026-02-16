package io.cyborgcode.roa.ui.playwright.components.factory;

import io.cyborgcode.roa.ui.playwright.annotations.ImplementationOfType;
import io.cyborgcode.roa.ui.playwright.components.accordion.Accordion;
import io.cyborgcode.roa.ui.playwright.components.accordion.AccordionComponentType;
import io.cyborgcode.roa.ui.playwright.components.alert.Alert;
import io.cyborgcode.roa.ui.playwright.components.alert.AlertComponentType;
import io.cyborgcode.roa.ui.playwright.components.base.ComponentType;
import io.cyborgcode.roa.ui.playwright.components.button.Button;
import io.cyborgcode.roa.ui.playwright.components.button.ButtonComponentType;
import io.cyborgcode.roa.ui.playwright.components.checkbox.Checkbox;
import io.cyborgcode.roa.ui.playwright.components.checkbox.CheckboxComponentType;
import io.cyborgcode.roa.ui.playwright.components.input.Input;
import io.cyborgcode.roa.ui.playwright.components.input.InputComponentType;
import io.cyborgcode.roa.ui.playwright.components.link.Link;
import io.cyborgcode.roa.ui.playwright.components.link.LinkComponentType;
import io.cyborgcode.roa.ui.playwright.components.list.ItemList;
import io.cyborgcode.roa.ui.playwright.components.list.ItemListComponentType;
import io.cyborgcode.roa.ui.playwright.components.loader.Loader;
import io.cyborgcode.roa.ui.playwright.components.loader.LoaderComponentType;
import io.cyborgcode.roa.ui.playwright.components.modal.Modal;
import io.cyborgcode.roa.ui.playwright.components.modal.ModalComponentType;
import io.cyborgcode.roa.ui.playwright.components.radio.Radio;
import io.cyborgcode.roa.ui.playwright.components.radio.RadioComponentType;
import io.cyborgcode.roa.ui.playwright.components.select.Select;
import io.cyborgcode.roa.ui.playwright.components.select.SelectComponentType;
import io.cyborgcode.roa.ui.playwright.components.tab.Tab;
import io.cyborgcode.roa.ui.playwright.components.tab.TabComponentType;
import io.cyborgcode.roa.ui.playwright.components.toggle.Toggle;
import io.cyborgcode.roa.ui.playwright.components.toggle.ToggleComponentType;
import io.cyborgcode.roa.ui.playwright.log.LogUi;
import com.microsoft.playwright.Page;
import io.cyborgcode.utilities.reflections.ReflectionUtil;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;

import static io.cyborgcode.roa.ui.playwright.config.UiConfigHolder.getPlaywrightConfig;

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
    * The framework package where core components are defined.
    */
   private static final String FRAMEWORK_PACKAGE = "io.cyborgcode.roa";

   /**
    * Private constructor to prevent instantiation.
    */
   private ComponentFactory() {
   }

   /**
    * Retrieves an {@code Input} component of the specified type.
    *
    * @param type      The type of input component.
    * @param Page The {@code Page} instance used for UI interactions.
    * @return The corresponding {@code Input} component instance.
    */
   public static Input getInputComponent(InputComponentType type, Page page) {
      return getComponent(Input.class, type, getPlaywrightConfig().projectPackages(), page);
   }

   /**
    * Retrieves a {@code Button} component of the specified type.
    *
    * @param type      The type of button component.
    * @param Page The {@code Page} instance used for UI interactions.
    * @return The corresponding {@code Button} component instance.
    */
   public static Button getButtonComponent(ButtonComponentType type, Page page) {
      return getComponent(Button.class, type, getPlaywrightConfig().projectPackages(), page);
   }

   /**
    * Retrieves a {@code Radio} component of the specified type.
    *
    * @param type      The type of radio component.
    * @param Page The {@code Page} instance used for UI interactions.
    * @return The corresponding {@code Radio} component instance.
    */
   public static Radio getRadioComponent(RadioComponentType type, Page page) {
      return getComponent(Radio.class, type, getPlaywrightConfig().projectPackages(), page);
   }

   /**
    * Retrieves a {@code Select} component of the specified type.
    *
    * @param type      The type of select component.
    * @param Page The {@code Page} instance used for UI interactions.
    * @return The corresponding {@code Select} component instance.
    */
   public static Select getSelectComponent(SelectComponentType type, Page page) {
      return getComponent(Select.class, type, getPlaywrightConfig().projectPackages(), page);
   }

   /**
    * Retrieves a {@code ItemList} component of the specified type.
    *
    * @param type      The type of list component.
    * @param Page The {@code Page} instance used for UI interactions.
    * @return The corresponding {@code ItemList} component instance.
    */
   public static ItemList getListComponent(ItemListComponentType type, Page page) {
      return getComponent(ItemList.class, type, getPlaywrightConfig().projectPackages(), page);
   }

   /**
    * Retrieves a {@code Loader} component of the specified type.
    *
    * @param type      The type of loader component.
    * @param Page The {@code Page} instance used for UI interactions.
    * @return The corresponding {@code Loader} component instance.
    */
   public static Loader getLoaderComponent(LoaderComponentType type, Page page) {
      return getComponent(Loader.class, type, getPlaywrightConfig().projectPackages(), page);
   }

   /**
    * Retrieves a {@code Link} component of the specified type.
    *
    * @param type      The type of link component.
    * @param Page The {@code Page} instance used for UI interactions.
    * @return The corresponding {@code Link} component instance.
    */
   public static Link getLinkComponent(LinkComponentType type, Page page) {
      return getComponent(Link.class, type, getPlaywrightConfig().projectPackages(), page);
   }

   /**
    * Retrieves an {@code Alert} component of the specified type.
    *
    * @param type      The type of alert component.
    * @param Page The {@code Page} instance used for UI interactions.
    * @return The corresponding {@code Alert} component instance.
    */
   public static Alert getAlertComponent(AlertComponentType type, Page page) {
      return getComponent(Alert.class, type, getPlaywrightConfig().projectPackages(), page);
   }

   /**
    * Retrieves a {@code Tab} component of the specified type.
    *
    * @param type      The type of tab component.
    * @param Page The {@code Page} instance used for UI interactions.
    * @return The corresponding {@code Tab} component instance.
    */
   public static Tab getTabComponent(TabComponentType type, Page page) {
      return getComponent(Tab.class, type, getPlaywrightConfig().projectPackages(), page);
   }

   /**
    * Retrieves a {@code Checkbox} component of the specified type.
    *
    * @param type      The type of checkbox component.
    * @param Page The {@code Page} instance used for UI interactions.
    * @return The corresponding {@code Checkbox} component instance.
    */
   public static Checkbox getCheckBoxComponent(CheckboxComponentType type, Page page) {
      return getComponent(Checkbox.class, type, getPlaywrightConfig().projectPackages(), page);
   }

   /**
    * Retrieves a {@code Toggle} component of the specified type.
    *
    * @param type      The type of toggle component.
    * @param Page The {@code Page} instance used for UI interactions.
    * @return The corresponding {@code Toggle} component instance.
    */
   public static Toggle getToggleComponent(ToggleComponentType type, Page page) {
      return getComponent(Toggle.class, type, getPlaywrightConfig().projectPackages(), page);
   }

   /**
    * Retrieves a {@code Modal} component of the specified type.
    *
    * @param type      The type of modal component.
    * @param Page The {@code Page} instance used for UI interactions.
    * @return The corresponding {@code Modal} component instance.
    */
   public static Modal getModalComponent(ModalComponentType type, Page page) {
      return getComponent(Modal.class, type, getPlaywrightConfig().projectPackages(), page);
   }

   /**
    * Retrieves an {@code Accordion} component of the specified type.
    *
    * @param type      The type of accordion component.
    * @param Page The {@code Page} instance used for UI interactions.
    * @return The corresponding {@code Accordion} component instance.
    */
   public static Accordion getAccordionComponent(AccordionComponentType type, Page page) {
      return getComponent(Accordion.class, type, getPlaywrightConfig().projectPackages(), page);
   }

   /**
    * Retrieves a {@code Table} component of the specified type.
    *
    * @param type      The type of table component.
    * @param Page The {@code Page} instance used for UI interactions.
    * @return The corresponding {@code Table} component instance.
    */
   public static io.cyborgcode.roa.ui.playwright.components.table.service.Table getTableComponent(
         io.cyborgcode.roa.ui.playwright.components.table.base.TableComponentType type, Page page) {
      return getComponent(io.cyborgcode.roa.ui.playwright.components.table.service.Table.class, type,
            getPlaywrightConfig().projectPackages(), page);
   }

   /**
    * Discovers and retrieves a component implementation matching the specified interface and type.
    *
    * <p>The method scans both the user's project package and the framework package
    * for classes that implement the given interface and are annotated
    * with {@link ImplementationOfType} matching the provided {@code componentType}.
    *
    * @param interfaceType   The class object representing the component interface (e.g. {@code Input.class}).
    * @param componentType   The enum-based type identifying the component variant.
    * @param projectPackages The user's project packages to scan for implementations.
    * @param Page       The Page used for UI interactions.
    * @param <T>             The generic type of the component interface.
    * @return A newly instantiated component matching the desired type.
    * @throws ComponentNotFoundException If no matching implementation class is found.
    */
   private static <T> T getComponent(Class<T> interfaceType, ComponentType componentType, String[] projectPackages,
                                     Page page) {
      List<Class<? extends T>> implementations = ReflectionUtil.findImplementationsOfInterface(interfaceType,
            projectPackages);
      LogUi.debug("Found {} classes implementing {} in package {}.",
            implementations.size(),
            interfaceType.getSimpleName(),
            projectPackages);
      implementations.addAll(ReflectionUtil.findImplementationsOfInterface(interfaceType,
            FRAMEWORK_PACKAGE));

      Optional<Class<? extends T>> implementation = implementations.stream()
            .filter(implClass -> isMatchingImplementation(implClass, componentType))
            .findFirst();

      return implementation.map(implClass -> createInstance(implClass, page))
            .orElseThrow(() -> new ComponentNotFoundException(
                  "No implementation found for type: " + componentType.getType().name()));
   }

   /**
    * Checks whether the provided class is annotated with {@link ImplementationOfType}
    * matching the given component type enum name.
    *
    * @param implClass     The class to check.
    * @param componentType The component type (enum value).
    * @param <T>           The component interface type.
    * @return {@code true} if the class annotation value matches the enum's name; {@code false} otherwise.
    */
   private static <T> boolean isMatchingImplementation(Class<? extends T> implClass, ComponentType componentType) {
      return Optional.ofNullable(implClass.getAnnotation(ImplementationOfType.class))
            .map(ImplementationOfType::value)
            .filter(values -> {
               for (ComponentType val : values) {
                  if (val.getType().name().equals(componentType.getType().name())) {
                     return true;
                  }
               }
               return false;
            })
            .isPresent();
   }

   /**
    * Instantiates a component class by invoking its constructor that accepts a {@link Page}.
    *
    * @param implClass The concrete implementation class.
    * @param Page The Page for UI interactions.
    * @param <T>       The component interface type.
    * @return A new instance of the specified class.
    * @throws ComponentCreationException If reflection-based instantiation fails.
    */
   private static <T> T createInstance(Class<? extends T> implClass, Page page) {
      try {
         return implClass.getDeclaredConstructor(Page.class).newInstance(page);
      } catch (InstantiationException | IllegalAccessException | InvocationTargetException
               | NoSuchMethodException e) {
         LogUi.error("Failed to create instance of " + implClass.getName(), e);
         throw new ComponentCreationException("Failed to create instance of " + implClass.getName(), e);
      }
   }

   /**
    * Exception thrown when no matching component implementation is found for a given type.
    *
    * @author Cyborg Code Syndicate 💍👨💻
    */
   public static class ComponentNotFoundException extends RuntimeException {

      /**
       * Constructs a {@code ComponentNotFoundException} with the specified message.
       *
       * @param message The detail message explaining why the component was not found.
       */
      public ComponentNotFoundException(String message) {
         super(message);
      }

   }

   /**
    * Exception thrown when a component implementation fails to instantiate.
    *
    * @author Cyborg Code Syndicate 💍👨💻
    */
   public static class ComponentCreationException extends RuntimeException {

      /**
       * Constructs a {@code ComponentCreationException} with the specified message and cause.
       *
       * @param message The detail message.
       * @param cause   The underlying cause of the creation failure.
       */
      public ComponentCreationException(String message, Throwable cause) {
         super(message, cause);
      }

   }

}
