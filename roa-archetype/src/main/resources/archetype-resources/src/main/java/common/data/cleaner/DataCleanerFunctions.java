package ${package}.common.data.cleaner;

import io.cyborgcode.roa.framework.quest.SuperQuest;
import io.cyborgcode.roa.validator.core.Assertion;
#if( $modules.contains("API") )
import static io.cyborgcode.roa.validator.core.AssertionTypes.IS;
import static io.cyborgcode.roa.api.config.ApiRings.RING_OF_API;
import static io.cyborgcode.roa.api.validator.RestAssertionTarget.STATUS;
import static org.apache.http.HttpStatus.SC_NO_CONTENT;
#end

/**
 * Provides reusable cleanup routines invoked by {@link DataCleaner}.
 *
 * <p>This class is intentionally generic. It demonstrates how to structure
 * cleanup methods without relying on any application-specific API endpoints,
 * IDs, database schemas or UI interactions.</p>
 *
 * <p>Replace the placeholder implementation with real teardown logic
 * (such as deleting data, resetting test data, removing created entities, etc.).</p>
 */
public final class DataCleanerFunctions {

   private DataCleanerFunctions() {}

   /**
    * Example cleanup operation.
    *
    * <p>This method demonstrates how to implement a cleanup function.
    * Replace the request URL, path params, and validation rules according
    * to your application's needs.</p>
    *
    * <p>Example usage:
    * <pre>
    *     @Ripper(DataCleaner.Data.EXAMPLE_CLEANUP)
    *     void myTest() { ... }
    * </pre>
    * </p>
    */
   public static void exampleCleanup(SuperQuest quest) {
#if( $modules.contains("API") )
      quest.use(RING_OF_API)
            .requestAndValidate(
                  "/example/{id}".withPathParam("id", "123"),
                  Assertion.builder()
                        .target(STATUS)
                        .type(IS)
                        .expected(SC_NO_CONTENT)
                        .build()
            );
#else
      System.out.println("Example cleanup function");
#end
   }
}
