package io.cyborgcode.roa.ui.components.factory;

import io.cyborgcode.roa.ui.annotations.ImplementationOfType;
import io.cyborgcode.roa.ui.components.accordion.Accordion;
import io.cyborgcode.roa.ui.components.accordion.AccordionComponentType;
import io.cyborgcode.roa.ui.components.alert.Alert;
import io.cyborgcode.roa.ui.components.alert.AlertComponentType;
import io.cyborgcode.roa.ui.components.base.ComponentType;
import io.cyborgcode.roa.ui.components.button.Button;
import io.cyborgcode.roa.ui.components.button.ButtonComponentType;
import io.cyborgcode.roa.ui.components.checkbox.Checkbox;
import io.cyborgcode.roa.ui.components.checkbox.CheckboxComponentType;
import io.cyborgcode.roa.ui.components.input.Input;
import io.cyborgcode.roa.ui.components.input.InputComponentType;
import io.cyborgcode.roa.ui.components.link.Link;
import io.cyborgcode.roa.ui.components.link.LinkComponentType;
import io.cyborgcode.roa.ui.components.list.ItemList;
import io.cyborgcode.roa.ui.components.list.ItemListComponentType;
import io.cyborgcode.roa.ui.components.loader.Loader;
import io.cyborgcode.roa.ui.components.loader.LoaderComponentType;
import io.cyborgcode.roa.ui.components.modal.Modal;
import io.cyborgcode.roa.ui.components.modal.ModalComponentType;
import io.cyborgcode.roa.ui.components.radio.Radio;
import io.cyborgcode.roa.ui.components.radio.RadioComponentType;
import io.cyborgcode.roa.ui.components.select.Select;
import io.cyborgcode.roa.ui.components.select.SelectComponentType;
import io.cyborgcode.roa.ui.components.tab.Tab;
import io.cyborgcode.roa.ui.components.tab.TabComponentType;
import io.cyborgcode.roa.ui.components.table.base.TableComponentType;
import io.cyborgcode.roa.ui.components.table.service.Table;
import io.cyborgcode.roa.ui.components.toggle.Toggle;
import io.cyborgcode.roa.ui.components.toggle.ToggleComponentType;
import io.cyborgcode.roa.ui.log.LogUi;
import io.cyborgcode.roa.ui.selenium.smart.SmartWebDriver;
import io.cyborgcode.utilities.reflections.ReflectionUtil;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;

import static io.cyborgcode.roa.ui.config.UiConfigHolder.getUiConfig;

/**
 * Factory class responsible for dynamically creating UI component instances.
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
    * @param type           The type of input component.
    * @param smartWebDriver The {@code SmartWebDriver} instance used for UI interactions.
    * @return The corresponding {@code Input} component instance.
    */
   public static Input getInputComponent(InputComponentType type, SmartWebDriver smartWebDriver) {
      return getComponent(Input.class, type, getUiConfig().projectPackage(), smartWebDriver);
   }

   /**
    * Retrieves a {@code Button} component of the specified type.
    *
    * @param type           The type of button component.
    * @param smartWebDriver The {@code SmartWebDriver} instance used for UI interactions.
    * @return The corresponding {@code Button} component instance.
    */
   public static Button getButtonComponent(ButtonComponentType type, SmartWebDriver smartWebDriver) {
      return getComponent(Button.class, type, getUiConfig().projectPackage(), smartWebDriver);
   }

   /**
    * Retrieves a {@code Radio} component of the specified type.
    *
    * @param type           The type of radio component.
    * @param smartWebDriver The {@code SmartWebDriver} instance used for UI interactions.
    * @return The corresponding {@code Radio} component instance.
    */
   public static Radio getRadioComponent(RadioComponentType type, SmartWebDriver smartWebDriver) {
      return getComponent(Radio.class, type, getUiConfig().projectPackage(), smartWebDriver);
   }

   /**
    * Retrieves a {@code Select} component of the specified type.
    *
    * @param type           The type of select component.
    * @param smartWebDriver The {@code SmartWebDriver} instance used for UI interactions.
    * @return The corresponding {@code Select} component instance.
    */
   public static Select getSelectComponent(SelectComponentType type, SmartWebDriver smartWebDriver) {
      return getComponent(Select.class, type, getUiConfig().projectPackage(), smartWebDriver);
   }

   /**
    * Retrieves a {@code ItemList} component of the specified type.
    *
    * @param type           The type of select component.
    * @param smartWebDriver The {@code SmartWebDriver} instance used for UI interactions.
    * @return The corresponding {@code ItemList} component instance.
    */
   public static ItemList getListComponent(ItemListComponentType type, SmartWebDriver smartWebDriver) {
      return getComponent(ItemList.class, type, getUiConfig().projectPackage(), smartWebDriver);
   }

   /**
    * Retrieves a {@code Loader} component of the specified type.
    *
    * @param type           The type of select component.
    * @param smartWebDriver The {@code SmartWebDriver} instance used for UI interactions.
    * @return The corresponding {@code Loader} component instance.
    */
   public static Loader getLoaderComponent(LoaderComponentType type, SmartWebDriver smartWebDriver) {
      return getComponent(Loader.class, type, getUiConfig().projectPackage(), smartWebDriver);
   }

   /**
    * Retrieves a {@code Link} component of the specified type.
    *
    * @param type           The type of select component.
    * @param smartWebDriver The {@code SmartWebDriver} instance used for UI interactions.
    * @return The corresponding {@code Link} component instance.
    */
   public static Link getLinkComponent(LinkComponentType type, SmartWebDriver smartWebDriver) {
      return getComponent(Link.class, type, getUiConfig().projectPackage(), smartWebDriver);
   }

   /**
    * Retrieves a {@code Alert} component of the specified type.
    *
    * @param type           The type of select component.
    * @param smartWebDriver The {@code SmartWebDriver} instance used for UI interactions.
    * @return The corresponding {@code Alert} component instance.
    */
   public static Alert getAlertComponent(AlertComponentType type, SmartWebDriver smartWebDriver) {
      return getComponent(Alert.class, type, getUiConfig().projectPackage(), smartWebDriver);
   }

   /**
    * Retrieves a {@code Tab} component of the specified type.
    *
    * @param type           The type of select component.
    * @param smartWebDriver The {@code SmartWebDriver} instance used for UI interactions.
    * @return The corresponding {@code Tab} component instance.
    */
   public static Tab getTabComponent(TabComponentType type, SmartWebDriver smartWebDriver) {
      return getComponent(Tab.class, type, getUiConfig().projectPackage(), smartWebDriver);
   }

   /**
    * Retrieves a {@code Checkbox} component of the specified type.
    *
    * @param type           The type of select component.
    * @param smartWebDriver The {@code SmartWebDriver} instance used for UI interactions.
    * @return The corresponding {@code Checkbox} component instance.
    */
   public static Checkbox getCheckBoxComponent(CheckboxComponentType type, SmartWebDriver smartWebDriver) {
      return getComponent(Checkbox.class, type, getUiConfig().projectPackage(), smartWebDriver);
   }

   /**
    * Retrieves a {@code Toggle} component of the specified type.
    *
    * @param type           The type of select component.
    * @param smartWebDriver The {@code SmartWebDriver} instance used for UI interactions.
    * @return The corresponding {@code Toggle} component instance.
    */
   public static Toggle getToggleComponent(ToggleComponentType type, SmartWebDriver smartWebDriver) {
      return getComponent(Toggle.class, type, getUiConfig().projectPackage(), smartWebDriver);
   }

   /**
    * Retrieves a {@code Modal} component of the specified type.
    *
    * @param type           The type of select component.
    * @param smartWebDriver The {@code SmartWebDriver} instance used for UI interactions.
    * @return The corresponding {@code Modal} component instance.
    */
   public static Modal getModalComponent(ModalComponentType type, SmartWebDriver smartWebDriver) {
      return getComponent(Modal.class, type, getUiConfig().projectPackage(), smartWebDriver);
   }

   /**
    * Retrieves a {@code Accordion} component of the specified type.
    *
    * @param type           The type of select component.
    * @param smartWebDriver The {@code SmartWebDriver} instance used for UI interactions.
    * @return The corresponding {@code Accordion} component instance.
    */
   public static Accordion getAccordionComponent(AccordionComponentType type, SmartWebDriver smartWebDriver) {
      return getComponent(Accordion.class, type, getUiConfig().projectPackage(), smartWebDriver);
   }

   /**
    * Retrieves a {@code Table} component of the specified type.
    *
    * @param type           The type of select component.
    * @param smartWebDriver The {@code SmartWebDriver} instance used for UI interactions.
    * @return The corresponding {@code Table} component instance.
    */
   public static Table getTableComponent(TableComponentType type, SmartWebDriver smartWebDriver) {
      return getComponent(Table.class, type, getUiConfig().projectPackage(), smartWebDriver);
   }

   /**
    * Discovers and retrieves a component implementation matching the specified interface and type.
    *
    * <p>The method scans both the user's project package and the framework package
    * for classes that implement the given interface and are annotated
    * with {@link ImplementationOfType} matching the provided {@code componentType}.
    *
    * @param interfaceType  The class object representing the component interface (e.g. {@code Input.class}).
    * @param componentType  The enum-based type identifying the component variant.
    * @param projectPackage The user's project package to scan for implementations.
    * @param smartWebDriver The WebDriver used for UI interactions.
    * @param <T>            The generic type of the component interface.
    * @return A newly instantiated component matching the desired type.
    * @throws ComponentNotFoundException If no matching implementation class is found.
    */
   private static <T> T getComponent(Class<T> interfaceType, ComponentType componentType, String projectPackage,
                                     SmartWebDriver smartWebDriver) {
      List<Class<? extends T>> implementations = ReflectionUtil.findImplementationsOfInterface(interfaceType,
            projectPackage);
      LogUi.debug("Found {} classes implementing {} in package {}.",
            implementations.size(),
            interfaceType.getSimpleName(),
            projectPackage);
      implementations.addAll(ReflectionUtil.findImplementationsOfInterface(interfaceType,
            FRAMEWORK_PACKAGE));


      Optional<Class<? extends T>> implementation = implementations.stream()
            .filter(
                  implClass -> isMatchingImplementation(implClass, componentType))
            .findFirst();

      return implementation.map(implClass -> createInstance(implClass, smartWebDriver))
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
            .filter(value -> value.equals(componentType.getType().name()))
            .isPresent();
   }

   /**
    * Instantiates a component class by invoking its constructor that accepts a {@link SmartWebDriver}.
    *
    * @param implClass      The concrete implementation class.
    * @param smartWebDriver The WebDriver for UI interactions.
    * @param <T>            The component interface type.
    * @return A new instance of the specified class.
    * @throws ComponentCreationException If reflection-based instantiation fails.
    */
   private static <T> T createInstance(Class<? extends T> implClass, SmartWebDriver smartWebDriver) {
      try {
         return implClass.getDeclaredConstructor(SmartWebDriver.class).newInstance(smartWebDriver);
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
