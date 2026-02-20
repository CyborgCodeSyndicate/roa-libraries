package io.cyborgcode.roa.ui.components.base;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Abstract base class for UI component services.
 *
 * <p>This class manages component instances by their {@link ComponentType},
 * caching them to avoid redundant creation. Subclasses must implement
 * the {@link #createComponent(ComponentType)} method to define how
 * specific components are instantiated.
 *
 * @param <T> The component type enum.
 * @param <C> The component interface type.
 * @param <D> The driver or page type.
 * @author Cyborg Code Syndicate 💍👨💻
 */
public abstract class AbstractComponentServiceCore<T extends ComponentType, C, D> {

   /**
    * The driver/page instance used for UI interactions.
    */
   protected final D driver;

   /**
    * Cache of created component instances, keyed by component type.
    */
   private final Map<T, C> components = new ConcurrentHashMap<>();

   /**
    * Constructs a new AbstractComponentService with the given driver.
    *
    * @param driver The driver/page instance for UI interactions.
    */
   protected AbstractComponentServiceCore(final D driver) {
      this.driver = driver;
   }

   /**
    * Returns the driver/page instance.
    *
    * @return The driver/page instance.
    */
   protected D getDriver() {
      return driver;
   }

   /**
    * Creates a component instance for the given component type.
    *
    * <p>Subclasses must implement this method to define how specific
    * component implementations are created.
    *
    * @param componentType The component type to create an instance for.
    * @return A new component instance.
    */
   protected abstract C createComponent(T componentType);

   /**
    * Retrieves an existing component for the given type, or creates and caches a new one.
    *
    * @param componentType The component type.
    * @return The component instance.
    */
   protected C getOrCreateComponent(final T componentType) {
      return components.computeIfAbsent(componentType, this::createComponent);
   }
}
