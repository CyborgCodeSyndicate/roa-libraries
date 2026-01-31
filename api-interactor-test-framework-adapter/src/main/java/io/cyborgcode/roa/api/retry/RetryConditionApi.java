package io.cyborgcode.roa.api.retry;

import io.cyborgcode.pandora.annotation.Pandora;
import io.cyborgcode.pandora.annotation.PandoraOptions;
import io.cyborgcode.pandora.model.CreationKind;
import io.cyborgcode.roa.api.core.Endpoint;
import io.cyborgcode.roa.api.service.RestService;
import io.cyborgcode.roa.framework.retry.RetryCondition;
import io.cyborgcode.roa.framework.retry.RetryConditionImpl;
import io.restassured.response.Response;
import java.util.Objects;

/**
 * Provides predefined retry conditions for API requests.
 *
 * <p>This utility class defines common conditions for retrying requests,
 * including status code checks and JSON response field validations.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@Pandora(
      description = "Predefined retry conditions for API requests (status code and JSON field checks). "
            + "Used together with RestServiceFluent.retryUntil(...) to handle eventual consistency.",
      tags = {"api", "retry"},
      creation = CreationKind.PROVIDED
)
@PandoraOptions(
      exampleFilesPath = "ai/roa/api-usage.json",
      meta = {
         @PandoraOptions.Meta(key = "type", value = "api-retry-conditions")
      }
)
public class RetryConditionApi {

   private RetryConditionApi() {
   }

   /**
    * Creates a retry condition that checks if the response status matches the expected value.
    *
    * @param endpoint The API endpoint.
    * @param status   The expected status code.
    * @return A {@link RetryCondition} that retries until the expected status code is received.
    */
   @Pandora(
         description = "Create a retry condition that repeatedly calls the endpoint (without body) "
               + "until the HTTP status equals the expected value."
   )
   @PandoraOptions(
         exampleFilesPath = "ai/roa/api-usage.json"
   )
   public static RetryCondition<Integer> statusEquals(Endpoint<?> endpoint,
                                                      @Pandora(description = "Expected HTTP status code "
                                                            + "that will stop the retry loop when reached.")
                                                      int status) {
      return new RetryConditionImpl<>(
            service -> {
               RestService restService = (RestService) service;
               Response response = restService.request(endpoint);
               return response.getStatusCode();
            }, responseStatus -> responseStatus == status
      );
   }

   /**
    * Creates a retry condition that checks if the response status matches the expected value.
    *
    * @param endpoint The API endpoint.
    * @param body     The request body.
    * @param status   The expected status code.
    * @return A {@link RetryCondition} that retries until the expected status code is received.
    */
   @Pandora(
         description = "Create a retry condition that repeatedly calls the endpoint with a body "
               + "until the HTTP status equals the expected value."
   )
   @PandoraOptions(
         exampleFilesPath = "ai/roa/api-usage.json"
   )
   public static RetryCondition<Integer> statusEquals(Endpoint<?> endpoint,
                                                      @Pandora(description = "Request payload sent "
                                                            + "with each retry attempt.")
                                                      Object body,
                                                      @Pandora(description = "Expected HTTP status code "
                                                            + "that will stop the retry loop when reached.")
                                                      int status) {
      return new RetryConditionImpl<>(
            service -> {
               RestService restService = (RestService) service;
               Response response = restService.request(endpoint, body);
               return response.getStatusCode();
            }, responseStatus -> responseStatus == status
      );
   }

   /**
    * Creates a retry condition that checks if a specific JSON response field equals the expected value.
    *
    * @param endpoint The API endpoint.
    * @param jsonPath The JSON path to extract the field.
    * @param obj      The expected value.
    * @return A {@link RetryCondition} that retries until the field value matches the expected value.
    */
   @Pandora(
         description = "Create a retry condition that repeatedly calls the endpoint (without body) "
               + "until a JSON field at the given path equals the expected value."
   )
   @PandoraOptions(
         exampleFilesPath = "ai/roa/api-usage.json"
   )
   public static RetryCondition<Object> responseFieldEqualsTo(Endpoint<?> endpoint,
                                                              @Pandora(description = "JSON path expression used to "
                                                                    + "extract the value from the response body.")
                                                              String jsonPath,
                                                              @Pandora(description = "Expected value that the JSON "
                                                                    + "field must equal to stop the retry loop.")
                                                              Object obj) {
      return new RetryConditionImpl<>(
            service -> {
               RestService restService = (RestService) service;
               Response response = restService.request(endpoint);
               return response.getBody().jsonPath().get(jsonPath);
            }, field -> field.equals(obj)
      );
   }

   /**
    * Creates a retry condition that checks if a specific JSON response field equals the expected value.
    *
    * @param endpoint The API endpoint.
    * @param body     The request body.
    * @param jsonPath The JSON path to extract the field.
    * @param obj      The expected value.
    * @return A {@link RetryCondition} that retries until the field value matches the expected value.
    */
   @Pandora(
         description = "Create a retry condition that repeatedly calls the endpoint with a body "
               + "until a JSON field at the given path equals the expected value."
   )
   @PandoraOptions(
         exampleFilesPath = "ai/roa/api-usage.json"
   )
   public static RetryCondition<Object> responseFieldEqualsTo(Endpoint<?> endpoint,
                                                              @Pandora(description = "Request payload sent "
                                                                    + "with each retry attempt.")
                                                              Object body,
                                                              @Pandora(description = "JSON path expression used to "
                                                                    + "extract the value from the response body.")
                                                                 String jsonPath,
                                                              @Pandora(description = "Expected value that the JSON "
                                                                    + "field must equal to stop the retry loop.")
                                                              Object obj) {
      return new RetryConditionImpl<>(
            service -> {
               RestService restService = (RestService) service;
               Response response = restService.request(endpoint, body);
               return response.getBody().jsonPath().get(jsonPath);
            }, field -> field.equals(obj)
      );
   }

   /**
    * Creates a retry condition that checks if a specific JSON response field is non-null.
    *
    * @param endpoint The API endpoint.
    * @param jsonPath The JSON path to extract the field.
    * @return A {@link RetryCondition} that retries until the field contains a non-null value.
    */
   @Pandora(
         description = "Create a retry condition that repeatedly calls the endpoint (without body) "
               + "until the JSON field at the given path becomes non-null."
   )
   @PandoraOptions(
         exampleFilesPath = "ai/roa/api-usage.json"
   )
   public static RetryCondition<Object> responseFieldNonNull(Endpoint<?> endpoint,
                                                             @Pandora(description = "JSON path expression used "
                                                                   + "to extract the value from the response body.")
                                                             String jsonPath) {
      return new RetryConditionImpl<>(
            service -> {
               RestService restService = (RestService) service;
               Response response = restService.request(endpoint);
               return response.getBody().jsonPath().get(jsonPath);
            }, Objects::nonNull
      );
   }

   /**
    * Creates a retry condition that checks if a specific JSON response field is non-null.
    *
    * @param endpoint The API endpoint.
    * @param body     The request body.
    * @param jsonPath The JSON path to extract the field.
    * @return A {@link RetryCondition} that retries until the field contains a non-null value.
    */
   @Pandora(
         description = "Create a retry condition that repeatedly calls the endpoint with a body "
               + "until the JSON field at the given path becomes non-null."
   )
   @PandoraOptions(
         exampleFilesPath = "ai/roa/api-usage.json"
   )
   public static RetryCondition<Object> responseFieldNonNull(Endpoint<?> endpoint,
                                                             @Pandora(description = "Request payload sent "
                                                                   + "with each retry attempt.")
                                                             Object body,
                                                             @Pandora(description = "JSON path expression used to "
                                                                   + "extract the value from the response body.")
                                                                String jsonPath) {
      return new RetryConditionImpl<>(
            service -> {
               RestService restService = (RestService) service;
               Response response = restService.request(endpoint, body);
               return response.getBody().jsonPath().get(jsonPath);
            }, Objects::nonNull
      );
   }

}
