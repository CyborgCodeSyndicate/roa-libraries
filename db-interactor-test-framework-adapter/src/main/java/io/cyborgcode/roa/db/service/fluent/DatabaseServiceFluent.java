package io.cyborgcode.roa.db.service.fluent;

import io.cyborgcode.pandora.annotation.Pandora;
import io.cyborgcode.pandora.annotation.PandoraOptions;
import io.cyborgcode.pandora.model.CreationKind;
import io.cyborgcode.roa.db.query.DbQuery;
import io.cyborgcode.roa.db.query.QueryResponse;
import io.cyborgcode.roa.db.service.DatabaseService;
import io.cyborgcode.roa.framework.annotation.Ring;
import io.cyborgcode.roa.framework.base.ClassLevelHook;
import io.cyborgcode.roa.framework.chain.FluentService;
import io.cyborgcode.roa.framework.retry.RetryCondition;
import io.cyborgcode.roa.validator.core.Assertion;
import io.cyborgcode.roa.validator.core.AssertionResult;
import java.time.Duration;
import java.util.List;
import java.util.function.Consumer;
import org.assertj.core.api.SoftAssertions;
import org.springframework.beans.factory.annotation.Autowired;

import static io.cyborgcode.roa.db.storage.StorageKeysDb.DB;

/**
 * Provides a fluent API for database interactions.
 */
@Ring("DB")
@Pandora(
      description = "High-level DB DSL used by tests to execute SQL queries, "
            + "extract values, validate results, and handle retries.",
      tags = {"db"},
      creation = CreationKind.PROVIDED
)
@PandoraOptions(
      exampleFilesPath = "ai/roa/db-usage.json",
      meta = {
         @PandoraOptions.Meta(key = "type", value = "fluent-service"),
      }
)
public class DatabaseServiceFluent extends FluentService implements ClassLevelHook {

   private final DatabaseService databaseService;

   /**
    * Constructs a new {@code DatabaseServiceFluent} with the given database service.
    *
    * @param databaseService The service responsible for executing database queries.
    */
   @Autowired
   public DatabaseServiceFluent(final DatabaseService databaseService) {
      this.databaseService = databaseService;
   }

   /**
    * Executes a database query and stores the response in the test storage.
    *
    * @param query The database query to execute.
    * @return The current {@code DatabaseServiceFluent} instance for method chaining.
    */
   @Pandora(
         description = "Execute a database query and store the full query response "
               + "in quest storage for later validations.",
         tags = {"db"}
   )
   @PandoraOptions(
         exampleFilesPath = "ai/roa/db-usage.json"
   )
   public DatabaseServiceFluent query(final DbQuery<?> query) {
      final QueryResponse queryResponse = databaseService.query(query);
      quest.getStorage().sub(DB).put(query.enumImpl(), queryResponse);
      return this;
   }

   /**
    * Executes a database query, extracts a value using JSONPath, and stores it in the test storage.
    *
    * @param query      The database query to execute.
    * @param jsonPath   The JSONPath expression to extract data from the query result.
    * @param resultType The expected type of the extracted value.
    * @return The current {@code DatabaseServiceFluent} instance for method chaining.
    */
   @Pandora(
         description = "Execute a database query and extract a specific value "
               + "from the result using JSONPath.",
         tags = {"db"}
   )
   @PandoraOptions(
         exampleFilesPath = "ai/roa/db-usage.json"
   )
   public <T> DatabaseServiceFluent query(
         final DbQuery<?> query,
         @Pandora(
               description = "JSONPath expression used to extract a value from the query result."
         ) final String jsonPath,
         @Pandora(
               description = "Expected Java type of the extracted value."
         ) final Class<T> resultType) {

      final T queryResult = databaseService.query(query, jsonPath, resultType);
      quest.getStorage().sub(DB).put(query.enumImpl(), queryResult);
      return this;
   }

   /**
    * Executes a database query and validates the response.
    *
    * @param query      The database query to execute.
    * @param assertions The assertions to apply.
    * @return The current {@code DatabaseServiceFluent} instance for method chaining.
    */
   @Pandora(
         description = "Execute a database query, store the response, "
               + "and validate it using the provided assertions.",
         tags = {"db"}
   )
   @PandoraOptions(
         exampleFilesPath = "ai/roa/db-usage.json"
   )
   public DatabaseServiceFluent queryAndValidate(final DbQuery<?> query, final Assertion... assertions) {
      final QueryResponse queryResponse = databaseService.query(query);
      quest.getStorage().sub(DB).put(query.enumImpl(), queryResponse);
      return validate(queryResponse, assertions);
   }

   /**
    * Validates a database query response against the specified assertions.
    *
    * @param queryResponse The query response to validate.
    * @param assertions    The assertions to apply.
    * @return The current {@code DatabaseServiceFluent} instance for method chaining.
    */
   @Pandora(
         description = "Validate a previously executed database query response "
               + "using assertion rules.",
         tags = {"db"}
   )
   public DatabaseServiceFluent validate(final QueryResponse queryResponse, final Assertion... assertions) {
      final List<AssertionResult<Object>> assertionResults = databaseService.validate(queryResponse, assertions);
      validation(assertionResults);
      return this;
   }

   /**
    * Performs validation with a custom assertion.
    *
    * @param assertion The assertion to execute.
    * @return The current {@code DatabaseServiceFluent} instance for method chaining.
    */
   @Pandora(
         description = "Execute custom validation logic after database steps "
               + "using plain assertions.",
         tags = {"db"}
   )
   @Override
   public DatabaseServiceFluent validate(
         @Pandora(
               description = "Custom validation block executed in the current quest context."
         ) final Runnable assertion) {
      return (DatabaseServiceFluent) super.validate(assertion);
   }

   /**
    * Performs validation with a consumer of {@link SoftAssertions}.
    *
    * @param assertion The assertion to execute.
    * @return The current {@code DatabaseServiceFluent} instance for method chaining.
    */
   @Pandora(
         description = "Execute grouped (soft) validation checks using AssertJ SoftAssertions "
               + "after database operations.",
         tags = {"db"}
   )
   @Override
   public DatabaseServiceFluent validate(
         @Pandora(
               description = "Consumer that receives a SoftAssertions instance "
                     + "to build grouped validation checks."
         ) final Consumer<SoftAssertions> assertion) {

      return (DatabaseServiceFluent) super.validate(assertion);
   }

   /**
    * Retries a database operation until the specified condition is met or the timeout expires.
    *
    * @param retryCondition The retry condition to evaluate.
    * @param maxWait        The maximum duration to wait.
    * @param retryInterval  The interval between retries.
    * @param <T>            The type used in the retry condition.
    * @return The current {@code DatabaseServiceFluent} instance for method chaining.
    */
   @Pandora(
         description = "Retry a database-related condition until it is satisfied "
               + "or the maximum wait time is reached.",
         tags = {"db"}
   )
   @PandoraOptions(
         exampleFilesPath = "ai/roa/db-usage.json"
   )
   public <T> DatabaseServiceFluent retryUntil(
         @Pandora(
               description = "Condition that will be evaluated repeatedly until it returns true."
         ) final RetryCondition<T> retryCondition,
         @Pandora(
               description = "Maximum total duration to keep retrying."
         ) final Duration maxWait,
         @Pandora(
               description = "Delay between retry attempts."
         ) final Duration retryInterval) {

      return (DatabaseServiceFluent)
            super.retryUntil(retryCondition, maxWait, retryInterval, databaseService);
   }

   /**
    * Retrieves the underlying database service.
    *
    * @return The {@code DatabaseService} instance.
    */
   protected DatabaseService getDatabaseService() {
      return databaseService;
   }

}
