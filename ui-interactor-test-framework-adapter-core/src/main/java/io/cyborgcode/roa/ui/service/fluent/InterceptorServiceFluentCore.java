package io.cyborgcode.roa.ui.service.fluent;

import io.cyborgcode.roa.framework.storage.Storage;
import io.cyborgcode.roa.ui.components.interceptor.ApiResponse;
import io.cyborgcode.roa.ui.storage.StorageKeysUi;
import io.cyborgcode.roa.validator.core.AssertionResult;
import io.qameta.allure.Allure;
import java.util.List;

/**
 * A fluent service for handling API response validation and interception.
 *
 * <p>This service provides methods to validate API responses stored in the UI storage,
 * ensuring that requests contain expected status codes.
 *
 * @param <T> The type of {@link UiServiceFluentCore} for chaining fluent methods.
 * @author Cyborg Code Syndicate 💍👨💻
 */
public class InterceptorServiceFluentCore<T extends UiServiceFluentCore<?, ?, ?, ?>> {

   private final T uiServiceFluent;
   private final Storage storage;

   /**
    * Constructs an {@code InterceptorServiceFluentCore} instance.
    *
    * @param uiServiceFluent The UI service fluent instance to maintain fluent method chaining.
    * @param storage         The storage instance where API responses are stored for validation.
    */
   public InterceptorServiceFluentCore(T uiServiceFluent, Storage storage) {
      this.uiServiceFluent = uiServiceFluent;
      this.storage = storage;
   }

   /**
    * Validates that all API responses matching a given URL substring have a status code
    * starting with the specified prefix.
    *
    * @param requestUrlSubString The substring to match against API response URLs.
    * @param statusPrefix        The expected status code prefix (e.g., 2 for 200-level responses).
    * @return The current {@link UiServiceFluentCore} instance for method chaining.
    */
   public T validateResponseHaveStatus(final String requestUrlSubString, int statusPrefix) {
      Allure.step(
            "Validate that all API responses containing URL substring: " + requestUrlSubString + " have status prefix: "
                  + statusPrefix);
      return validateResponseHaveStatus(requestUrlSubString, statusPrefix, false);
   }

   /**
    * Validates that all API responses matching a given URL substring have a status code
    * starting with the specified prefix, with an option for soft assertions.
    *
    * @param requestUrlSubString The substring to match against API response URLs.
    * @param statusPrefix        The expected status code prefix (e.g., 2 for 200-level responses).
    * @param soft                {@code true} if the assertion should be a soft assertion,
    *                            allowing the test to continue even if it fails.
    * @return The current {@link UiServiceFluentCore} instance for method chaining.
    */
   @SuppressWarnings("unchecked")
   public T validateResponseHaveStatus(final String requestUrlSubString, int statusPrefix,
                                       boolean soft) {
      List<ApiResponse> apiResponses = (List<ApiResponse>) storage.sub(StorageKeysUi.UI)
            .getByClass(StorageKeysUi.RESPONSES, Object.class);

      List<ApiResponse> filteredResponses = apiResponses.stream()
            .filter(apiResponse -> apiResponse.getUrl().contains(requestUrlSubString))
            .toList();

      boolean isPassed = filteredResponses.stream()
            .allMatch(apiResponse -> validateStatus(statusPrefix, apiResponse.getStatus()));

      AssertionResult<?> result = new AssertionResult<>(isPassed,
            String.format("Validating API requests containing: %s have correct status: %d",
                  requestUrlSubString, statusPrefix),
            true, isPassed, soft);

      List<AssertionResult<Object>> validationResults = List.of((AssertionResult<Object>) result);
      uiServiceFluent.validation(validationResults);
      return uiServiceFluent;
   }

   /**
    * Checks whether a given status code starts with the specified prefix.
    *
    * @param prefix The expected status code prefix.
    * @param status The actual status code.
    * @return {@code true} if the status code starts with the prefix, otherwise {@code false}.
    */
   private boolean validateStatus(int prefix, int status) {
      String prefixStr = String.valueOf(prefix);
      String statusStr = String.valueOf(status);
      return statusStr.startsWith(prefixStr);
   }

}
