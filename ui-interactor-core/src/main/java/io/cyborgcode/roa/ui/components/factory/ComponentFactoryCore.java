package io.cyborgcode.roa.ui.components.factory;

import io.cyborgcode.roa.ui.annotations.ImplementationOfType;
import io.cyborgcode.roa.ui.components.base.ComponentType;
import io.cyborgcode.roa.ui.log.LogUi;
import io.cyborgcode.utilities.reflections.ReflectionUtil;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;

/**
 * Factory class responsible for dynamically creating Playwright UI component instances.
 *
 * <p>This class retrieves component instances based on their type using reflection.
 * It scans the project package and framework package for implementations of UI components
 * that are annotated with {@link ImplementationOfType}.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public class ComponentFactoryCore {

   /**
    * The framework package where core components are defined.
    */
   private static final String FRAMEWORK_PACKAGE = "io.cyborgcode.roa";

   /**
    * Private constructor to prevent instantiation.
    */
   private ComponentFactoryCore() {
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
    * @param driver          The Page used for UI interactions.
    * @param <T>             The generic type of the component interface.
    * @return A newly instantiated component matching the desired type.
    * @throws ComponentNotFoundException If no matching implementation class is found.
    */
   public static <T, D> T getComponent(Class<T> interfaceType, ComponentType componentType, String[] projectPackages,
                                       D driver) {
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

      return implementation.map(implClass -> createInstance(implClass, driver))
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
   public static <T> boolean isMatchingImplementation(Class<? extends T> implClass, ComponentType componentType) {
      return Optional.ofNullable(implClass.getAnnotation(ImplementationOfType.class))
            .map(ImplementationOfType::value)
            .filter(value -> value.equals(componentType.getType().name()))
            .isPresent();
   }

   /**
    * Instantiates a component class by invoking its constructor that accepts a {@link D}.
    *
    * @param implClass The concrete implementation class.
    * @param driver    The Page for UI interactions.
    * @param <T>       The component interface type.
    * @return A new instance of the specified class.
    * @throws ComponentCreationException If reflection-based instantiation fails.
    */
   public static <T, D> T createInstance(Class<? extends T> implClass, D driver) {
      try {
         return implClass.getDeclaredConstructor(driver.getClass()).newInstance(driver);
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
