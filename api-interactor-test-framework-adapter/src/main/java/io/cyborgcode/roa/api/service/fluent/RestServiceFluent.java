package io.cyborgcode.roa.api.service.fluent;

import io.cyborgcode.pandora.annotation.AiCompass;
import io.cyborgcode.pandora.annotation.AiCompassOptions;
import io.cyborgcode.pandora.model.CreationKind;
import io.cyborgcode.roa.api.authentication.BaseAuthenticationClient;
import io.cyborgcode.roa.api.core.Endpoint;
import io.cyborgcode.roa.api.service.RestService;
import io.cyborgcode.roa.framework.annotation.Ring;
import io.cyborgcode.roa.framework.base.ClassLevelHook;
import io.cyborgcode.roa.framework.chain.FluentService;
import io.cyborgcode.roa.framework.retry.RetryCondition;
import io.cyborgcode.roa.validator.core.Assertion;
import io.cyborgcode.roa.validator.core.AssertionResult;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import java.time.Duration;
import java.util.List;
import java.util.function.Consumer;
import org.assertj.core.api.SoftAssertions;
import org.springframework.beans.factory.annotation.Autowired;

import static io.cyborgcode.roa.api.storage.StorageKeysApi.API;

/**
 * Provides fluent interactions for API requests and validations.
 *
 * <p>This class extends {@link FluentService} and enables streamlined API calls,
 * authentication, validations, and retry mechanisms.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@Ring("API")
@SuppressWarnings("java:S6832")
@AiCompass(
      description = "High-level API DSL used by tests to send HTTP "
            + "requests, validate responses, perform authentication and retries.",
      tags = {"api"},
      creation = CreationKind.PROVIDED
)
@AiCompassOptions(
      exampleFilesPath = "docs/usage/roa/api-usage.json",
      meta = {
         @AiCompassOptions.Meta(key = "type", value = "fluent-service")
      }
)
public class RestServiceFluent extends FluentService implements ClassLevelHook {

   private final RestService restService;

   /**
    * Constructs a fluent API service instance.
    *
    * @param restService The underlying {@link RestService} instance.
    */
   @Autowired
   public RestServiceFluent(final RestService restService) {
      this.restService = restService;
   }

   /**
    * Executes a request to the specified endpoint.
    *
    * @param endpoint The API endpoint.
    * @return The current {@code RestServiceFluent} instance for method chaining.
    */
   @AiCompass(
         description = "Send a request to the given endpoint and "
               + "store the HTTP response in quest storage for later use.",
         tags = {"api"}
   )
   @AiCompassOptions(
         exampleFilesPath = "docs/usage/roa/api-usage.json"
   )
   public RestServiceFluent request(final Endpoint<?> endpoint) {
      final Response response = restService.request(endpoint);
      quest.getStorage().sub(API).put(endpoint.enumImpl(), response);
      return this;
   }

   /**
    * Executes a request with a request body.
    *
    * @param endpoint The API endpoint.
    * @param body     The request body.
    * @return The current {@code RestServiceFluent} instance for method chaining.
    */
   @AiCompass(
         description = "Send a request to the given endpoint with a "
               + "request payload and store the HTTP response in quest storage.",
         tags = {"api"}
   )
   @AiCompassOptions(
         exampleFilesPath = "docs/usage/roa/api-usage.json"
   )
   public RestServiceFluent request(
         final Endpoint<?> endpoint,
         @AiCompass(
               description = "Request payload that will be serialized and sent as the HTTP body."
         ) final Object body) {

      final Response response = restService.request(endpoint, body);
      quest.getStorage().sub(API).put(endpoint.enumImpl(), response);
      return this;
   }

   /**
    * Validates an API response against provided assertions.
    *
    * @param response   The API response.
    * @param assertions The assertions to validate.
    * @return The current {@code RestServiceFluent} instance for method chaining.
    */
   @AiCompass(
         description = "Validate a previously obtained HTTP response against the given assertions.",
         tags = {"api"}
   )
   @AiCompassOptions(
         exampleFilesPath = "docs/usage/roa/api-usage.json"
   )
   public RestServiceFluent validateResponse(
         @AiCompass(
               description = "HTTP response returned from a prior API request."
         ) final Response response,
         final Assertion... assertions) {

      final List<AssertionResult<Object>> assertionResults = restService.validate(response, assertions);
      validation(assertionResults);
      return this;
   }


   /**
    * Executes a request and validates the response.
    *
    * @param endpoint   The API endpoint.
    * @param assertions The assertions to validate.
    * @return The current {@code RestServiceFluent} instance for method chaining.
    */
   @AiCompass(
         description = "Send a request to the given endpoint, store the HTTP "
               + "response in quest storage, and validate it using the provided assertions.",
         tags = {"api"}
   )
   @AiCompassOptions(
         exampleFilesPath = "docs/usage/roa/api-usage.json"
   )
   public RestServiceFluent requestAndValidate(final Endpoint<?> endpoint,
                                               final Assertion... assertions) {
      final Response response = restService.request(endpoint);
      quest.getStorage().sub(API).put(endpoint.enumImpl(), response);
      return validateResponse(response, assertions);
   }

   /**
    * Executes a request with a request body and validates the response.
    *
    * @param endpoint   The API endpoint.
    * @param body       The request body.
    * @param assertions The assertions to validate.
    * @return The current {@code RestServiceFluent} instance for method chaining.
    */
   @AiCompass(
         description = "Send a request with a request payload to the given endpoint, "
               + "store the HTTP response in quest storage, and validate it using the provided assertions.",
         tags = {"api"}
   )
   @AiCompassOptions(
         exampleFilesPath = "docs/usage/roa/api-usage.json"
   )
   @Step("Request and validations for endpoint: {endpoint}")
   public RestServiceFluent requestAndValidate(final Endpoint<?> endpoint,
                                               @AiCompass(
                                                     description = "Request payload that will be "
                                                           + "serialized and sent as the HTTP body."
                                               ) final Object body,
                                               final Assertion... assertions) {
      final Response response = restService.request(endpoint, body);
      quest.getStorage().sub(API).put(endpoint.enumImpl(), response);
      return validateResponse(response, assertions);
   }

   /**
    * Performs authentication using the specified credentials and authentication client.
    *
    * @param username             The username for authentication.
    * @param password             The password for authentication.
    * @param authenticationClient The authentication client class.
    * @return The current {@code RestServiceFluent} instance for method chaining.
    */
   @AiCompass(
         description = "Perform API authentication using the given "
               + "username, password and authentication client implementation.",
         tags = {"api"}
   )
   @AiCompassOptions(
         exampleFilesPath = "docs/usage/roa/api-usage.json"
   )
   public RestServiceFluent authenticate(
         @AiCompass(
               description = "Username used for API authentication."
         ) final String username,
         @AiCompass(
               description = "Password used for API authentication."
         ) final String password,
         @AiCompass(
               description = "Authentication client implementation that "
                     + "defines how to perform the login and apply credentials."
         ) final Class<? extends BaseAuthenticationClient> authenticationClient) {

      restService.authenticate(username, password, authenticationClient);
      return this;
   }


   /**
    * Performs validation using a provided assertion.
    *
    * @param assertion The assertion to validate.
    * @return The current {@code RestServiceFluent} instance for method chaining.
    */
   @AiCompass(
         description = "Execute custom validation logic implemented as a Runnable, "
               + "typically using plain JUnit assertions after previous API steps.",
         tags = {"api"}
   )
   @AiCompassOptions(
         exampleFilesPath = "docs/usage/roa/api-usage.json"
   )
   @Override
   public RestServiceFluent validate(
         @AiCompass(
               description = "Custom validation block that will be executed in the context of the current quest."
         ) final Runnable assertion) {
      return (RestServiceFluent) super.validate(assertion);
   }

   /**
    * Performs validation using a provided assertion with {@link SoftAssertions}.
    *
    * @param assertion The assertion to validate.
    * @return The current {@code RestServiceFluent} instance for method chaining.
    */
   @AiCompass(
         description = "Execute custom validation logic using AssertJ "
               + "SoftAssertions for grouped (soft) checks after previous API steps.",
         tags = {"api"}
   )
   @AiCompassOptions(
         exampleFilesPath = "docs/usage/roa/api-usage.json"
   )
   @Override
   public RestServiceFluent validate(
         @AiCompass(
               description = "Consumer that receives a SoftAssertions "
                     + "instance for building grouped validation checks."
         ) final Consumer<SoftAssertions> assertion) {
      return (RestServiceFluent) super.validate(assertion);
   }


   /**
    * Retrieves the underlying {@link RestService} instance.
    *
    * @return The {@code RestService} instance.
    */
   protected RestService getRestService() {
      return restService;
   }

   /**
    * Executes a retry mechanism until a specified condition is met.
    *
    * @param retryCondition The retry condition to be checked.
    * @param maxWait        The maximum duration to wait before giving up.
    * @param retryInterval  The interval between retries.
    * @param <T>            The type used in the retry condition function.
    * @return The current {@code RestServiceFluent} instance for method chaining.
    */
   @AiCompass(
         description = "Poll a retry condition until it is satisfied or "
               + "the maximum wait time is reached, then continue the API flow.",
         tags = {"api"}
   )
   @AiCompassOptions(
         exampleFilesPath = "docs/usage/roa/api-usage.json"
   )
   public <T> RestServiceFluent retryUntil(
         final RetryCondition<T> retryCondition,
         @AiCompass(
               description = "Maximum total duration to keep retrying before giving up."
         ) final Duration maxWait,
         @AiCompass(
               description = "Delay between individual retry attempts."
         ) final Duration retryInterval) {

      return (RestServiceFluent) super.retryUntil(retryCondition, maxWait, retryInterval, restService);
   }

}
