package io.cyborgcode.roa.ui.insertion;

import io.cyborgcode.roa.ui.components.base.ComponentType;
import io.cyborgcode.roa.ui.insertion.Insertion;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Registry for managing {@link Insertion} services for various UI components.
 *
 * <p>Provides a centralized way to register and retrieve insertion services
 * based on specific {@link ComponentType} implementations.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public class InsertionServiceRegistry<T> {

   private final Map<Class<? extends ComponentType>, Insertion<T>> registry = new ConcurrentHashMap<>();

   /**
    * Registers an {@link Insertion} service for a specific component type.
    *
    * @param type    The class representing the {@link ComponentType}.
    * @param service The {@link Insertion} service to associate with the component type.
    */
   public void registerService(final Class<? extends ComponentType> type, final Insertion<T> service) {
      registry.put(type, service);
   }

   /**
    * Retrieves the registered {@link Insertion} service for a given component type.
    *
    * @param type The class representing the {@link ComponentType}.
    * @return The corresponding {@link Insertion} service, or {@code null} if none is registered.
    */
   public Insertion<T> getService(final Class<? extends ComponentType> type) {
      return registry.get(type);
   }

}
