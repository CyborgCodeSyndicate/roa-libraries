package io.cyborgcode.roa.api.service;

import io.cyborgcode.pandora.annotation.Pandora;
import io.cyborgcode.pandora.annotation.PandoraOptions;
import io.cyborgcode.pandora.model.CreationKind;
import io.cyborgcode.roa.api.authentication.AuthenticationKey;
import io.cyborgcode.roa.api.authentication.BaseAuthenticationClient;
import io.cyborgcode.roa.api.client.RestClient;
import io.cyborgcode.roa.api.core.Endpoint;
import io.cyborgcode.roa.api.exceptions.RestServiceException;
import io.cyborgcode.roa.api.validator.RestResponseValidator;
import io.cyborgcode.roa.validator.core.Assertion;
import io.cyborgcode.roa.validator.core.AssertionResult;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Objects;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * Handles API request execution and response validation.
 *
 * <p>This service provides methods to execute API requests, validate responses, and manage authentication.
 * It integrates with {@link RestClient} for request execution and {@link RestResponseValidator} for validation.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@Service
@Scope("prototype")
@Pandora(
      description = "Internal Spring-managed service that executes API requests (via RestClient), "
            + "applies authentication headers and validates responses (via RestResponseValidator). "
            + "Not obtained from Quest directly.",
      tags = {"api"},
      creation = CreationKind.PROVIDED
)
@PandoraOptions(
      exampleFilesPath = "docs/usage/roa/api-usage.json",
      meta = {
         @PandoraOptions.Meta(key = "type", value = "rest-service"),
         @PandoraOptions.Meta(key = "scope", value = "spring-prototype")
      }
)
public class RestService {

   private final RestClient restClient;
   private final RestResponseValidator restResponseValidator;
   private BaseAuthenticationClient baseAuthenticationClient;

   private AuthenticationKey authenticationKey;
   @Setter
   private boolean cacheAuthentication;

   /**
    * Constructs a new {@code RestService} instance.
    *
    * @param restClient            The client responsible for executing API requests.
    * @param restResponseValidator The validator for API responses.
    */
   @Autowired
   public RestService(RestClient restClient, final RestResponseValidator restResponseValidator) {
      this.restClient = restClient;
      this.restResponseValidator = restResponseValidator;
   }

   /**
    * Constructs a new {@code RestService} instance with caching configuration.
    *
    * @param restClient            The client responsible for executing API requests.
    * @param restResponseValidator The validator for API responses.
    * @param cacheAuthentication   Whether authentication should be cached.
    */
   public RestService(RestClient restClient, final RestResponseValidator restResponseValidator,
                      boolean cacheAuthentication) {
      this.restClient = restClient;
      this.restResponseValidator = restResponseValidator;
      this.cacheAuthentication = cacheAuthentication;
   }

   /**
    * Executes a request without a body.
    *
    * @param endpoint The API endpoint to call.
    * @return The response from the API.
    */
   @Pandora(
         description = "Execute an API request without a request body using the provided Endpoint definition."
   )
   @PandoraOptions(
         exampleFilesPath = "docs/usage/roa/api-usage.json"
   )
   public Response request(Endpoint<?> endpoint) {
      return executeRequest(endpoint, null);
   }

   /**
    * Executes a request with a specified body.
    *
    * @param endpoint The API endpoint to call.
    * @param body     The request body.
    * @return The response from the API.
    */
   @Pandora(
         description = "Execute an API request with a request payload. "
               + "The body is serialized by the endpoint's RequestSpecification."
   )
   @PandoraOptions(
         exampleFilesPath = "docs/usage/roa/api-usage.json"
   )
   public Response request(Endpoint<?> endpoint,
                           @Pandora(
                                 description = "Request payload to send as "
                                       + "body (will be serialized by RestAssured/spec)."
                           )
                           Object body) {
      return executeRequest(endpoint, body);
   }

   /**
    * Validates a response against the provided assertions.
    *
    * @param response   The response to validate.
    * @param assertions The assertions to apply.
    * @param <T>        The type of the assertion results.
    * @return A list of assertion results.
    * @throws IllegalArgumentException if the response or assertions are null.
    */
   @Pandora(
         description = "Validate an existing Response using one or more "
               + "Assertion rules and return the collected AssertionResult entries."
   )
   @PandoraOptions(
         exampleFilesPath = "docs/usage/roa/api-usage.json"
   )
   public <T> List<AssertionResult<T>> validate(@Pandora(
                                                      description = "Response instance to validate."
                                                )
                                                Response response,
                                                Assertion... assertions) {
      if (response == null) {
         throw new IllegalArgumentException("Response cannot be null for validation.");
      }
      if (assertions == null || assertions.length == 0) {
         throw new IllegalArgumentException("At least one assertion must be provided.");
      }
      return restResponseValidator.validateResponse(response, assertions);
   }

   /**
    * Executes a request and validates the response.
    *
    * @param endpoint   The API endpoint to call.
    * @param assertions The assertions to apply.
    * @param <T>        The type of the assertion results.
    * @return A list of assertion results.
    */
   @Pandora(
         description = "Execute a request (no body) and immediately "
               + "validate the response using the provided Assertion rules."
   )
   @PandoraOptions(
         exampleFilesPath = "docs/usage/roa/api-usage.json"
   )
   public <T> List<AssertionResult<T>> requestAndValidate(Endpoint<?> endpoint, Assertion... assertions) {
      return requestAndValidate(endpoint, null, assertions);
   }

   /**
    * Executes a request with a body and validates the response.
    *
    * @param endpoint   The API endpoint to call.
    * @param body       The request body.
    * @param assertions The assertions to apply.
    * @param <T>        The type of the assertion results.
    * @return A list of assertion results.
    */
   @Pandora(
         description = "Execute a request with a body and immediately "
               + "validate the response using the provided Assertion rules."
   )
   @PandoraOptions(
         exampleFilesPath = "docs/usage/roa/api-usage.json"
   )
   public <T> List<AssertionResult<T>> requestAndValidate(Endpoint<?> endpoint,
                                                          @Pandora(description = "Request payload to send as body "
                                                                + "(will be serialized by RestAssured/spec).")
                                                          Object body,
                                                          Assertion... assertions) {
      return validate(request(endpoint, body), assertions);
   }

   /**
    * Authenticates a user using a specified authentication client.
    *
    * @param username                  The username for authentication.
    * @param password                  The password for authentication.
    * @param authenticationClientClass The authentication client class to use.
    * @throws RestServiceException if authentication fails.
    */
   @Pandora(
         description = "Authenticate using a BaseAuthenticationClient implementation. "
               + "Stores an AuthenticationKey internally so subsequent requests can attach the cached auth header."
   )
   @PandoraOptions(
         exampleFilesPath = "docs/usage/roa/api-usage.json"
   )
   public void authenticate(@Pandora(
                                  description = "Username for authentication."
                            )
                            String username,
                            @Pandora(
                                  description = "Password for authentication."
                            )
                            String password,
                            @Pandora(
                                  description = "Concrete BaseAuthenticationClient class "
                                        + "that performs the login and provides the auth header."
                            )
                            Class<? extends BaseAuthenticationClient> authenticationClientClass) {
      Objects.requireNonNull(username, "Username must not be null");
      Objects.requireNonNull(password, "Password must not be null");
      Objects.requireNonNull(authenticationClientClass, "Authentication client class must not be null");
      try {
         baseAuthenticationClient = authenticationClientClass.getDeclaredConstructor().newInstance();
         authenticationKey = baseAuthenticationClient.authenticate(this, username, password, cacheAuthentication);

      } catch (InstantiationException | IllegalAccessException | InvocationTargetException
               | NoSuchMethodException e) {
         throw new RestServiceException("Error instantiating or authenticating with BaseAuthenticationClient.", e);
      }
   }

   /**
    * Executes a request to the specified endpoint.
    *
    * @param endpoint The API endpoint.
    * @param body     The request body (optional).
    * @return The API response.
    * @throws RestServiceException if an error occurs during request execution.
    */
   private Response executeRequest(Endpoint<?> endpoint, Object body) {
      if (endpoint == null) {
         throw new RestServiceException("Endpoint cannot be null.");
      }
      try {
         RequestSpecification spec = endpoint.prepareRequestSpec(body);

         if (baseAuthenticationClient != null && authenticationKey != null) {
            Header header = baseAuthenticationClient.getAuthentication(authenticationKey);
            if (header != null) {
               spec.header(header);
            }
         }

         return restClient.execute(spec, endpoint.method());
      } catch (Exception e) {
         throw new RestServiceException("Error executing request for endpoint: " + endpoint.url(), e);
      }
   }
}
