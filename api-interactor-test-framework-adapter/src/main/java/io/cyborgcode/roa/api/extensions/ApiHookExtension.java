package io.cyborgcode.roa.api.extensions;

import io.cyborgcode.roa.api.allure.RestClientAllureImpl;
import io.cyborgcode.roa.api.allure.RestResponseValidatorAllureImpl;
import io.cyborgcode.roa.api.annotations.ApiHook;
import io.cyborgcode.roa.api.hooks.ApiHookFlow;
import io.cyborgcode.roa.api.service.RestService;
import io.cyborgcode.roa.framework.exceptions.HookExecutionException;
import io.cyborgcode.roa.framework.hooks.HookExecution;
import io.cyborgcode.roa.framework.storage.StoreKeys;
import io.cyborgcode.utilities.reflections.ReflectionUtil;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import static io.cyborgcode.roa.api.config.ApiConfigHolder.getApiConfig;
import static io.cyborgcode.roa.framework.util.PropertiesUtil.addSystemProperties;

/**
 * A JUnit 5 extension that processes {@link ApiHook} annotations on a test class
 *
 * <p>During {@code beforeAll()} it executes any {@code @ApiHook(..., when=HookExecution.BEFORE, ...)} hooks,
 * and during {@code afterAll()} it executes any {@code @ApiHook(..., when=HookExecution.AFTER, ...)} hooks.
 * All hook outputs are collected into a map and stored under the key {@link StoreKeys#HOOKS_PARAMS}
 * in the JUnit extension context’s GLOBAL namespace.
 *
 *
 * <p>Hooks are looked up reflectively by type via {@link ReflectionUtil#findEnumImplementationsOfInterface},
 * then applied via their {@link ApiHookFlow#flow()} consumer.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public class ApiHookExtension implements BeforeAllCallback, AfterAllCallback {

   static {
      synchronized (ApiHookExtension.class) {
         addSystemProperties();
      }
   }

   private RestService restService;

   /**
    * Called once before all tests in the class.
    *
    * <p>Gathers all {@link ApiHook} annotations on the test class with
    * {@link HookExecution#BEFORE}, orders them by {@linkplain ApiHook#order() order},
    * and invokes {@link #executeHook} on each. Finally, stores the resulting
    * parameters map under {@link StoreKeys#HOOKS_PARAMS}.
    *
    * @param context the current JUnit extension context
    * @throws Exception if any reflection or hook‐flow error occurs; wrapped in {@link RuntimeException}
    */
   @Override
   public void beforeAll(final ExtensionContext context) throws Exception {
      Map<Object, Object> hooksStorage = new HashMap<>();
      ApiHook[] apiHooks = context.getRequiredTestClass().getAnnotationsByType(ApiHook.class);
      Arrays.stream(apiHooks)
            .filter(apiHook -> apiHook.when() == HookExecution.BEFORE)
            .sorted(Comparator.comparingInt(ApiHook::order))
            .forEach(apiHook -> executeHook(apiHook, hooksStorage));
      context.getStore(ExtensionContext.Namespace.GLOBAL).put(StoreKeys.HOOKS_PARAMS, hooksStorage);
   }

   /**
    * Called once after all tests in the class.
    *
    * <p>Gathers all {@link ApiHook} annotations on the test class with
    * {@link HookExecution#AFTER}, orders them by {@linkplain ApiHook#order() order},
    * and invokes {@link #executeHook} on each. Finally, stores the resulting
    * parameters map under {@link StoreKeys#HOOKS_PARAMS}.
    *
    * @param context the current JUnit extension context
    * @throws Exception if any reflection or hook‐flow error occurs; wrapped in {@link RuntimeException}
    */
   @Override
   public void afterAll(final ExtensionContext context) throws Exception {
      Map<Object, Object> hooksStorage = new HashMap<>();
      ApiHook[] apiHooks = context.getRequiredTestClass().getAnnotationsByType(ApiHook.class);
      Arrays.stream(apiHooks)
            .filter(apiHook -> apiHook.when() == HookExecution.AFTER)
            .sorted(Comparator.comparingInt(ApiHook::order))
            .forEach(apiHook -> executeHook(apiHook, hooksStorage));
      context.getStore(ExtensionContext.Namespace.GLOBAL).put(StoreKeys.HOOKS_PARAMS, hooksStorage);
   }

   /**
    * Executes a single {@link ApiHook} by looking up its {@link ApiHookFlow},
    * then invoking its {@code flow()} consumer with the shared storage map.
    *
    * <p>Any exceptions during lookup or execution are caught and rethrown as
    * {@link RuntimeException} with a standardized message.
    *
    * @param apiHook      the hook annotation instance
    * @param storageHooks the map into which the hook may write parameters
    */
   private void executeHook(ApiHook apiHook, Map<Object, Object> storageHooks) {
      try {
         ApiHookFlow<?> hookFlow = ReflectionUtil.findEnumImplementationsOfInterface(
               ApiHookFlow.class, apiHook.type(), getApiConfig().projectPackages());
         hookFlow.flow().accept(restService(), storageHooks, apiHook.arguments());
      } catch (Exception e) {
         throw new HookExecutionException("Error executing ApiHook: " + apiHook.type(), e);
      }
   }

   /**
    * Lazily instantiates (and then caches) the {@link RestService} used by hooks.
    * On first call, creates a new {@link RestService} with Allure‐enabled
    * implementations; on subsequent calls, returns the same instance.
    *
    * @return the singleton RestService for this extension
    */
   @SuppressWarnings("checkstyle:JavadocParagraph")
   private RestService restService() {
      if (restService == null) {
         restService = new RestService(new RestClientAllureImpl(), new RestResponseValidatorAllureImpl());
      }
      return restService;
   }

}
