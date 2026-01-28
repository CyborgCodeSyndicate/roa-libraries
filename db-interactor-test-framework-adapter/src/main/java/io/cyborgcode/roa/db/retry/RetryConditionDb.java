package io.cyborgcode.roa.db.retry;

import io.cyborgcode.pandora.annotation.Pandora;
import io.cyborgcode.pandora.annotation.PandoraOptions;
import io.cyborgcode.pandora.model.CreationKind;
import io.cyborgcode.roa.db.query.DbQuery;
import io.cyborgcode.roa.db.query.QueryResponse;
import io.cyborgcode.roa.db.service.DatabaseService;
import io.cyborgcode.roa.framework.retry.RetryCondition;
import io.cyborgcode.roa.framework.retry.RetryConditionImpl;
import java.util.function.Predicate;

/**
 * Provides retry conditions for database queries.
 *
 * <p>This class defines conditions for retrying operations based on database query results,
 * allowing tests to wait until expected data is available.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@Pandora(
      description = "Factory class providing database-specific retry conditions "
            + "that can be used to wait for query results or expected values.",
      tags = {"db", "retry"},
      creation = CreationKind.PROVIDED
)
@PandoraOptions(
      meta = {
         @PandoraOptions.Meta(key = "type", value = "db-retry-condition-factory")
      }
)
public class RetryConditionDb {

   private RetryConditionDb() {
   }

   /**
    * Creates a retry condition that waits until a query returns at least one row.
    *
    * <p>This condition is useful for ensuring that data has been inserted before proceeding
    * with further test steps.
    *
    * @param query The database query to execute.
    * @return A {@code RetryCondition} that evaluates to {@code true} when the query returns rows.
    */
   @Pandora(
         description = "Creates a retry condition that waits until the given database "
               + "query returns at least one row."
   )
   public static RetryCondition<Boolean> queryReturnsRows(DbQuery<?> query) {
      return new RetryConditionImpl<>(
            service -> {
               DatabaseService databaseService = (DatabaseService) service;
               QueryResponse queryResponse = databaseService.query(query);
               return !queryResponse.getRows().isEmpty();
            }, Predicate.isEqual(true)
      );
   }

   /**
    * Creates a retry condition that waits until a specific field in the query result matches a value.
    *
    * <p>This condition is useful for waiting until a database update or insertion has propagated.
    *
    * @param query    The database query to execute.
    * @param jsonPath The JSONPath expression to extract the value from the query result.
    * @param value    The expected value to match.
    * @return A {@code RetryCondition} that evaluates to {@code true} when the field matches the expected value.
    */
   @Pandora(
         description = "Creates a retry condition that waits until a specific field "
               + "in the query result matches the expected value."
   )
   public static RetryCondition<Object> queryReturnsValueForField(DbQuery<?> query, String jsonPath, Object value) {
      return new RetryConditionImpl<>(
            service -> {
               DatabaseService databaseService = (DatabaseService) service;
               return databaseService.query(query, jsonPath, value.getClass());
            }, object -> object.equals(value)
      );
   }

}
