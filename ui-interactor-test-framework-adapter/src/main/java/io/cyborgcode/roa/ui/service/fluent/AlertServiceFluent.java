package io.cyborgcode.roa.ui.service.fluent;

import io.cyborgcode.pandora.annotation.Pandora;
import io.cyborgcode.pandora.annotation.PandoraOptions;
import io.cyborgcode.pandora.model.CreationKind;
import io.cyborgcode.roa.framework.storage.Storage;
import io.cyborgcode.roa.ui.components.alert.AlertService;
import io.cyborgcode.roa.ui.selenium.AlertUiElement;
import io.cyborgcode.roa.ui.selenium.smart.SmartWebDriver;
import io.qameta.allure.Allure;
import org.assertj.core.api.Assertions;

import static io.cyborgcode.roa.ui.storage.StorageKeysUi.UI;

/**
 * Provides fluent API methods for interacting with Alert UI components.
 *
 * <p>This class encapsulates interactions with Alert elements, allowing retrieval,
 * validation, and visibility checks. It integrates with {@link AlertService} to
 * perform operations in a structured manner.
 *
 * <p>The generic type {@code T} represents the main UI service fluent class that this service extends,
 * allowing method chaining within the fluent API structure.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@SuppressWarnings({"java:S5960", "unchecked"})
@Pandora(
      description = "Fluent UI service for interacting with alerts: get value, visibility checks and validations.",
      tags = {"ui", "fluent", "alert"},
      creation = CreationKind.PROVIDED
)
@PandoraOptions(
      exampleFilesPath = "ai/roa/ui-usage.json",
      meta = {
         @PandoraOptions.Meta(key = "type", value = "fluent-service")
      }
)
public class AlertServiceFluent<T extends UiServiceFluent<?>> {

   private final AlertService alertService;
   private final T uiServiceFluent;
   private final Storage storage;
   private final SmartWebDriver driver;

   /**
    * Constructs an instance of {@link AlertServiceFluent}.
    *
    * @param uiServiceFluent The main UI service fluent instance.
    * @param storage         The storage object for maintaining test state.
    * @param alertService    The service handling alert interactions.
    * @param webDriver       The instance of {@link SmartWebDriver}.
    */
   public AlertServiceFluent(T uiServiceFluent, Storage storage, AlertService alertService,
                             SmartWebDriver webDriver) {
      this.alertService = alertService;
      this.uiServiceFluent = uiServiceFluent;
      this.storage = storage;
      this.driver = webDriver;
   }

   /**
    * Retrieves the value of the specified alert element.
    *
    * @param element The alert UI element.
    * @return The current fluent service instance for method chaining.
    */
   @Pandora(
        description = "Retrieve the current value from the alert element and store it in quest storage.",
        tags = {"ui", "alert"}
   )
   @PandoraOptions(
        exampleFilesPath = "ai/roa/ui-usage.json"
   )
   public T getValue(final AlertUiElement element) {
      Allure.step(String.format("[UI - Alert] Retrieving value for alert with componentType: %s, locator: %s",
            element.componentType(), element.locator()));
      element.before().accept(driver);
      String value = alertService.getValue(element.componentType(), element.locator());
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), value);
      return uiServiceFluent;
   }

   /**
    * Validates that the alert element's value matches the expected value.
    *
    * @param element       The alert UI element.
    * @param expectedValue The expected value of the alert.
    * @return The current fluent service instance for method chaining.
    */
   @Pandora(
        description = "Validate that the alert value equals the expected value (hard assertion).",
        tags = {"ui", "alert"}
   )
   @PandoraOptions(
        exampleFilesPath = "ai/roa/ui-usage.json"
   )
   public T validateValue(final AlertUiElement element,
          @Pandora(
                description = "Expected alert value."
          ) String expectedValue) {
      return validateValue(element, expectedValue, false);
   }

   /**
    * Validates that the alert element's value matches the expected value with an option for soft assertions.
    *
    * @param element       The alert UI element.
    * @param expectedValue The expected value of the alert.
    * @param soft          Whether to perform a soft assertion.
    * @return The current fluent service instance for method chaining.
    */
   @Pandora(
        description = "Validate that the alert value equals the expected value, optionally using a soft assertion.",
        tags = {"ui", "alert"}
   )
   @PandoraOptions(
        exampleFilesPath = "ai/roa/ui-usage.json"
   )
   public T validateValue(final AlertUiElement element,
         @Pandora(
               description = "Expected alert value."
         ) String expectedValue,
         @Pandora(
               description = "When true, use soft assertions (don't fail immediately)."
         ) boolean soft) {
      Allure.step(String.format("[UI - Alert] Validating value for alert with componentType: %s, locator: %s",
            element.componentType(), element.locator()));
      element.before().accept(driver);
      String value = alertService.getValue(element.componentType(), element.locator());
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), value);
      if (soft) {
         return (T) uiServiceFluent.validate(
               softAssertions -> softAssertions.assertThat(value).as("Validating Alert value")
                     .isEqualTo(expectedValue));
      } else {
         return (T) uiServiceFluent.validate(
               () -> Assertions.assertThat(value).as("Validating Alert value")
                     .isEqualTo(expectedValue));
      }
   }

   /**
    * Checks if the specified alert element is visible.
    *
    * @param element The alert UI element.
    * @return The current fluent service instance for method chaining.
    */
   @Pandora(
        description = "Evaluate whether the alert is visible and store the result in quest storage.",
        tags = {"ui", "alert"}
   )
   @PandoraOptions(
        exampleFilesPath = "ai/roa/ui-usage.json"
   )
   public T isVisible(final AlertUiElement element) {
      Allure.step(String.format("[UI - Alert] Checking visibility for alert with componentType: %s, locator: %s",
            element.componentType(), element.locator()));
      element.before().accept(driver);
      boolean enabled = alertService.isVisible(element.componentType(), element.locator());
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), enabled);
      return uiServiceFluent;
   }

   /**
    * Validates if the specified alert element is visible.
    *
    * @param element The alert UI element.
    * @return The current fluent service instance for method chaining.
    */
   @Pandora(
        description = "Validate that the alert is visible (hard assertion).",
        tags = {"ui", "alert"}
   )
   @PandoraOptions(
        exampleFilesPath = "ai/roa/ui-usage.json"
   )
   public T validateIsVisible(final AlertUiElement element) {
      return validateIsVisible(element, true, false);
   }

   /**
    * Validates if the specified alert element is visible with an option for soft assertions.
    *
    * @param element The alert UI element.
    * @param soft    Whether to perform a soft assertion.
    * @return The current fluent service instance for method chaining.
    */
   @Pandora(
        description = "Validate that the alert is visible, optionally using a soft assertion.",
        tags = {"ui", "alert"}
   )
   @PandoraOptions(
        exampleFilesPath = "ai/roa/ui-usage.json"
   )
   public T validateIsVisible(final AlertUiElement element,
          @Pandora(
                description = "When true, use soft assertions (don't fail immediately)."
          ) boolean soft) {
      return validateIsVisible(element, true, soft);
   }

   /**
    * Validates if the specified alert element is visible or hidden based on the provided flag.
    *
    * @param element         The alert UI element.
    * @param shouldBeVisible Whether the element should be visible.
    * @param soft            Whether to perform a soft assertion.
    * @return The current fluent service instance for method chaining.
    */
   private T validateIsVisible(final AlertUiElement element, boolean shouldBeVisible, boolean soft) {
      Allure.step(String.format("[UI - Alert] Validating visibility for alert with componentType: %s, locator: %s",
            element.componentType(), element.locator()));
      element.before().accept(driver);
      boolean visible = alertService.isVisible(element.componentType(), element.locator());
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), visible);

      String assertionMessage = shouldBeVisible
            ? "Validating Alert is visible"
            : "Validating Alert is hidden";

      if (soft) {
         return (T) uiServiceFluent.validate(
               softAssertions -> {
                  if (shouldBeVisible) {
                     softAssertions.assertThat(visible).as(assertionMessage).isTrue();
                  } else {
                     softAssertions.assertThat(visible).as(assertionMessage).isFalse();
                  }
               }
         );
      } else {
         return (T) uiServiceFluent.validate(
               () -> {
                  if (shouldBeVisible) {
                     Assertions.assertThat(visible).as(assertionMessage).isTrue();
                  } else {
                     Assertions.assertThat(visible).as(assertionMessage).isFalse();
                  }
               }
         );
      }
   }

   /**
    * Validates if the specified alert element is hidden.
    *
    * @param element The alert UI element.
    * @return The current fluent service instance for method chaining.
    */
   @Pandora(
        description = "Validate that the alert is hidden (hard assertion).",
        tags = {"ui", "alert"}
   )
   @PandoraOptions(
        exampleFilesPath = "ai/roa/ui-usage.json"
   )
   public T validateIsHidden(final AlertUiElement element) {
      return validateIsVisible(element, false, false);
   }

   /**
    * Validates if the specified alert element is hidden with an option for soft assertions.
    *
    * @param element The alert UI element.
    * @param soft    Whether to perform a soft assertion.
    * @return The current fluent service instance for method chaining.
    */
   @Pandora(
        description = "Validate that the alert is hidden, optionally using a soft assertion.",
        tags = {"ui", "alert"}
   )
   @PandoraOptions(
        exampleFilesPath = "ai/roa/ui-usage.json"
   )
   public T validateIsHidden(final AlertUiElement element,
         @Pandora(
               description = "When true, use soft assertions (don't fail immediately)."
         ) boolean soft) {
      return validateIsVisible(element, false, soft);
   }
}
