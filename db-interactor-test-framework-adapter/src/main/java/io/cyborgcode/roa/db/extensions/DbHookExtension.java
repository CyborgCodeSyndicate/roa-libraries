package io.cyborgcode.roa.db.extensions;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.cyborgcode.roa.db.allure.QueryResponseValidatorAllureImpl;
import io.cyborgcode.roa.db.annotations.DbHook;
import io.cyborgcode.roa.db.client.DbClientManager;
import io.cyborgcode.roa.db.connector.BaseDbConnectorService;
import io.cyborgcode.roa.db.hooks.DbHookFlow;
import io.cyborgcode.roa.db.json.JsonPathExtractor;
import io.cyborgcode.roa.db.service.DatabaseService;
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

import static io.cyborgcode.roa.db.config.DbConfigHolder.getDbConfig;
import static io.cyborgcode.roa.framework.util.PropertiesUtil.addSystemProperties;

/**
 * JUnit 5 extension that processes {@link DbHook} annotations on the test class.
 *
 * <p>Executes database-related hooks before and after all tests, ordered by the
 * {@code order} attribute on the {@code @DbHook} annotation, and stores the hook
 * parameters in the global ExtensionContext store under {@link StoreKeys#HOOKS_PARAMS}.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public class DbHookExtension implements BeforeAllCallback, AfterAllCallback {

   static {
      synchronized (DbHookExtension.class) {
         addSystemProperties();
      }
   }

   /**
    * Cached instance of {@link DatabaseService} used to execute hooks.
    */
   private DatabaseService databaseService;

   /**
    * Executes all {@link DbHook} annotations on the test class with {@link HookExecution#BEFORE}.
    * Hooks are sorted by {@code order}, executed, and their parameters stored.
    *
    * @param context the JUnit extension context for the test class
    * @throws Exception if any hook execution fails
    */
   @Override
   public void beforeAll(final ExtensionContext context) throws Exception {
      Map<Object, Object> hooksStorage = new HashMap<>();
      DbHook[] dbHooks = context.getRequiredTestClass().getAnnotationsByType(DbHook.class);
      Arrays.stream(dbHooks)
            .filter(dbHook -> dbHook.when() == HookExecution.BEFORE)
            .sorted(Comparator.comparingInt(DbHook::order))
            .forEach(dbHook -> executeHook(dbHook, hooksStorage));
      context.getStore(ExtensionContext.Namespace.GLOBAL).put(StoreKeys.HOOKS_PARAMS, hooksStorage);
   }

   /**
    * Executes all {@link DbHook} annotations on the test class with {@link HookExecution#AFTER}.
    * Hooks are sorted by {@code order}, executed, and their parameters stored.
    *
    * @param context the JUnit extension context for the test class
    * @throws Exception if any hook execution fails
    */
   @Override
   public void afterAll(final ExtensionContext context) throws Exception {
      Map<Object, Object> hooksStorage = new HashMap<>();
      DbHook[] dbHooks = context.getRequiredTestClass().getAnnotationsByType(DbHook.class);
      Arrays.stream(dbHooks)
            .filter(dbHook -> dbHook.when() == HookExecution.AFTER)
            .sorted(Comparator.comparingInt(DbHook::order))
            .forEach(dbHook -> executeHook(dbHook, hooksStorage));
      context.getStore(ExtensionContext.Namespace.GLOBAL).put(StoreKeys.HOOKS_PARAMS, hooksStorage);
   }

   /**
    * Resolves and executes a single {@link DbHook} by locating the corresponding {@link DbHookFlow}
    * implementation and invoking its flow.
    *
    * @param dbHook       the annotation instance containing hook metadata
    * @param storageHooks the map used to accumulate hook results and arguments
    */
   private void executeHook(DbHook dbHook, Map<Object, Object> storageHooks) {
      try {
         DbHookFlow<?> hookFlow = ReflectionUtil.findEnumImplementationsOfInterface(
               DbHookFlow.class, dbHook.type(), getDbConfig().projectPackages());
         hookFlow.flow().accept(dbService(), storageHooks, dbHook.arguments());
      } catch (Exception e) {
         throw new HookExecutionException("Error executing DbHook: " + dbHook.type(), e);
      }
   }

   /**
    * Lazily initializes and returns the singleton {@link DatabaseService}.
    *
    * <p>Configures JSON path extraction, a database connector manager, and an Allure result validator.
    *
    * @return the shared {@code DatabaseService} instance
    */
   private DatabaseService dbService() {
      if (databaseService == null) {
         JsonPathExtractor jsonPathExtractor = new JsonPathExtractor(new ObjectMapper());
         databaseService =
               new DatabaseService(jsonPathExtractor,
                     new DbClientManager(new BaseDbConnectorService()),
                     new QueryResponseValidatorAllureImpl(jsonPathExtractor));
      }
      return databaseService;
   }

}
