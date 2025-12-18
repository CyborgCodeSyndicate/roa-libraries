package io.cyborgcode.roa.framework.base;

import io.cyborgcode.pandora.annotation.Pandora;
import io.cyborgcode.pandora.annotation.PandoraOptions;
import io.cyborgcode.pandora.model.CreationKind;
import io.cyborgcode.roa.framework.log.LogQuest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import static io.cyborgcode.utilities.reflections.ReflectionUtil.getFieldValues;

/**
 * Manages and provides access to fluent service instances.
 *
 * <p>This class is responsible for retrieving and caching service instances,
 * ensuring efficient access to dynamically managed components.
 *
 * <p>Services are lazily initialized and stored in an internal cache to optimize performance.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@Component
@Lazy
@Pandora(
      description = "Spring-managed service container used by the framework to access underlying service beans "
            + "from a specific Ring (fluent service). It resolves the fluent service from the Spring context, "
            + "extracts a matching field by type, and caches the result for reuse.",
      tags = {"framework"},
      creation = CreationKind.PROVIDED
)
@PandoraOptions(
      meta = {
         @PandoraOptions.Meta(key = "type", value = "services-container"),
         @PandoraOptions.Meta(key = "scope", value = "spring-context")
      }
)
public class Services {

   /**
    * The application context for retrieving service beans.
    */
   private final ApplicationContext applicationContext;

   /**
    * Cache for storing retrieved service instances.
    */
   private final Map<Class<?>, Object> serviceCache = new HashMap<>();

   /**
    * Constructs a new {@code Services} instance with the provided application context.
    *
    * @param applicationContext The application context used for retrieving service beans.
    */
   @Autowired
   public Services(ApplicationContext applicationContext) {
      this.applicationContext = applicationContext;
   }

   /**
    * Retrieves a service instance for the specified fluent service class and service type.
    *
    * <p>If the requested service is not already cached, it is retrieved from the application context
    * and stored for future access.
    *
    * @param fluentServiceClass The class representing the fluent service.
    * @param serviceClass       The type of service to retrieve.
    * @param <T>                The type of the fluent service.
    * @param <K>                The type of the requested service.
    * @return The requested service instance.
    * @throws IllegalStateException if no matching service bean is found.
    */
   @Pandora(
         description = "Retrieve an underlying service from a specific Ring (fluent service) by type. "
               + "The service is resolved from the Ring instance via reflection and then cached."
   )
   public <T extends ClassLevelHook, K> K service(@Pandora(description = "Ring (fluent service) class that "
                                                        + "contains the desired service as a field, "
                                                        + "e.g. RestServiceFluent.class.")
                                                  Class<T> fluentServiceClass,
                                                  @Pandora(description = "Type of the service to "
                                                        + "extract from the Ring, e.g. RestService.class.")
                                                  Class<K> serviceClass) {
      return serviceClass.cast(serviceCache.computeIfAbsent(serviceClass, key -> {
         ClassLevelHook fluentService = applicationContext.getBeansOfType(ClassLevelHook.class)
               .values().stream()
               .filter(fluent -> fluent.getClass().equals(fluentServiceClass))
               .findFirst()
               .orElseThrow(() -> new IllegalStateException(
                     "No bean found for the specified fluentServiceClass: " + fluentServiceClass.getName()));

         List<K> fieldValues = getFieldValues(fluentService, serviceClass);
         if (fieldValues.size() > 1) {
            LogQuest.warn(
                  "There is more than one service from type: {} inside class: {}. The first one will be taken: {}",
                  serviceClass, fluentServiceClass, fieldValues.get(0));
         }
         return fieldValues.get(0);
      }));
   }

}
