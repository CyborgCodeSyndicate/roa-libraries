package ${package}.db_module.db.hooks;

import io.cyborgcode.roa.db.hooks.DbHookFlow;
import io.cyborgcode.roa.db.service.DatabaseService;
import java.util.Map;
import org.apache.logging.log4j.util.TriConsumer;

/**
 * Example registry of database hook flows.
 *
 * <p>This file provides placeholder examples demonstrating how to implement
 * reusable database hooks using the ROA framework. Replace or extend these
 * entries to match your application's database lifecycle needs.</p>
 */
public enum DbHookFlows implements DbHookFlow<DbHookFlows> {

   /**
    * Example hook executed BEFORE tests.
    */
   EXAMPLE_INITIALIZE((service, storage, args) ->
         DbHookFunctions.initializeExample(service)),

   /**
    * Example hook that performs a query and saves something in storage.
    */
   EXAMPLE_QUERY_AND_STORE(DbHookFunctions::queryAndStoreExample);

   public static final class Data {

      public static final String EXAMPLE_INITIALIZE = "EXAMPLE_INITIALIZE";
      public static final String EXAMPLE_QUERY_AND_STORE = "EXAMPLE_QUERY_AND_STORE";

      private Data() {}
   }

   private final TriConsumer<DatabaseService, Map<Object,Object>, String[]> flow;

   DbHookFlows(TriConsumer<DatabaseService, Map<Object,Object>, String[]> flow) {
      this.flow = flow;
   }

   @Override
   public TriConsumer<DatabaseService, Map<Object, Object>, String[]> flow() {
      return flow;
   }

   @Override
   public DbHookFlows enumImpl() {
      return this;
   }
}
