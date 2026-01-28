package io.cyborgcode.roa.ui.service.fluent;

import io.cyborgcode.pandora.annotation.Pandora;
import io.cyborgcode.pandora.annotation.PandoraOptions;
import io.cyborgcode.pandora.model.CreationKind;
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
 * <p>The generic type {@code T} represents the UI service fluent implementation that extends {@link UiServiceFluent},
 * allowing method chaining for seamless interaction.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@Pandora(
      description = "Fluent UI service for validating intercepted API responses from UI flows.",
      tags = {"ui", "fluent", "interceptor"},
      creation = CreationKind.PROVIDED
)
@PandoraOptions(
      exampleFilesPath = "ai/roa/ui-usage.json",
      meta = {
         @PandoraOptions.Meta(key = "type", value = "fluent-service")
      }
)
public class InterceptorServiceFluent<T extends UiServiceFluent<?>> {

   private final T uiServiceFluent;
   private final Storage storage;

   /**
    * Constructs an {@code InterceptorServiceFluent} instance.
    *
    * @param uiServiceFluent The UI service fluent instance to maintain fluent method chaining.
    * @param storage         The storage instance where API responses are stored for validation.
    */
   public InterceptorServiceFluent(T uiServiceFluent, Storage storage) {
      this.uiServiceFluent = uiServiceFluent;
      this.storage = storage;
   }

   /**
    * Validates that all API responses matching a given URL substring have a status code
    * starting with the specified prefix.
    *
    * @param requestUrlSubString The substring to match against API response URLs.
    * @param statusPrefix        The expected status code prefix (e.g., 2 for 200-level responses).
    * @return The current {@link UiServiceFluent} instance for method chaining.
    */
   @Pandora(
         description = "Validate that all intercepted API responses matching the URL substring have the "
               + "given status prefix (hard assertion).",
         tags = {"ui", "interceptor"}
   )
   @PandoraOptions(
         exampleFilesPath = "ai/roa/ui-usage.json"
   )
   public T validateResponseHaveStatus(
         @Pandora(
               description = "Substring to match against API response URLs."
         ) final String requestUrlSubString,
         @Pandora(
               description = "Expected HTTP status code prefix, e.g. 2 for 2xx."
         ) int statusPrefix) {
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
    * @return The current {@link UiServiceFluent} instance for method chaining.
    */
   @Pandora(
         description = "Validate that all intercepted API responses matching the URL substring have "
               + "the given status prefix, optionally using a soft assertion.",
         tags = {"ui", "interceptor"}
   )
   @PandoraOptions(
         exampleFilesPath = "ai/roa/ui-usage.json"
   )
   public T validateResponseHaveStatus(
         @Pandora(
               description = "Substring to match against API response URLs."
         ) final String requestUrlSubString,
         @Pandora(
               description = "Expected HTTP status code prefix, e.g. 2 for 2xx."
         ) int statusPrefix,
         @Pandora(
               description = "When true, use soft assertions (don't fail immediately)."
         ) boolean soft) {
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
