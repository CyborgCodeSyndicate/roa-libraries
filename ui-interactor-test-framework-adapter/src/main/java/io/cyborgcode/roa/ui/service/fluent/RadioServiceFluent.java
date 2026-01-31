package io.cyborgcode.roa.ui.service.fluent;

import io.cyborgcode.pandora.annotation.Pandora;
import io.cyborgcode.pandora.annotation.PandoraOptions;
import io.cyborgcode.pandora.model.CreationKind;
import io.cyborgcode.roa.framework.storage.Storage;
import io.cyborgcode.roa.ui.components.base.ComponentType;
import io.cyborgcode.roa.ui.components.radio.RadioService;
import io.cyborgcode.roa.ui.insertion.Insertion;
import io.cyborgcode.roa.ui.selenium.RadioUiElement;
import io.cyborgcode.roa.ui.selenium.smart.SmartWebDriver;
import io.qameta.allure.Allure;
import java.util.Arrays;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;

import static io.cyborgcode.roa.ui.storage.StorageKeysUi.UI;

/**
 * A fluent service class for interacting with radio button components in a UI automation framework.
 *
 * <p>Provides methods for selecting, validating, enabling, and retrieving radio button values.
 *
 * <p>The generic type {@code T} represents the UI service fluent implementation that extends {@link UiServiceFluent},
 * enabling method chaining for seamless interaction.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@SuppressWarnings({"java:S5960", "unchecked"})
@Pandora(
      description = "Fluent UI service for interacting with radio inputs: select, "
            + "visibility/enablement checks, retrievals and validations.",
      tags = {"ui", "fluent", "radio"},
      creation = CreationKind.PROVIDED
)
@PandoraOptions(
      exampleFilesPath = "docs/usage/roa/ui-usage.json",
      meta = {
         @PandoraOptions.Meta(key = "type", value = "fluent-service")
      }
)
public class RadioServiceFluent<T extends UiServiceFluent<?>> implements Insertion {

   private static final String UI_RADIO_VALIDATING_IF_RADIO_BUTTON_IS = "[UI - Radio] Validating if radio button is ";

   private final RadioService radioService;
   private final T uiServiceFluent;
   private final Storage storage;
   private final SmartWebDriver driver;

   /**
    * Constructs a new {@code RadioServiceFluent} instance.
    *
    * @param uiServiceFluent The parent fluent UI service instance.
    * @param storage         The storage instance for saving UI-related values.
    * @param radioService    The service responsible for handling radio button interactions.
    * @param webDriver       The smart web driver instance.
    */
   public RadioServiceFluent(T uiServiceFluent, Storage storage, RadioService radioService,
                             SmartWebDriver webDriver) {
      this.radioService = radioService;
      this.uiServiceFluent = uiServiceFluent;
      this.storage = storage;
      driver = webDriver;
   }

   /**
    * Selects the given radio button element.
    *
    * @param element The {@link RadioUiElement} representing the radio button.
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Select the given radio UI element and continue the fluent UI flow.",
         tags = {"ui", "radio"}
   )
   @PandoraOptions(
         exampleFilesPath = "docs/usage/roa/ui-usage.json"
   )
   public T select(final RadioUiElement element) {
      Allure.step("[UI - Radio] Selecting radio button: " + element.enumImpl());
      element.before().accept(driver);
      radioService.select(element.componentType(), element.locator());
      element.after().accept(driver);
      return uiServiceFluent;
   }

   /**
    * Checks if the specified radio button is enabled.
    *
    * @param element The {@link RadioUiElement} to check.
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Evaluate whether the radio input is enabled and store the result in quest storage.",
         tags = {"ui", "radio"}
   )
   @PandoraOptions(
         exampleFilesPath = "docs/usage/roa/ui-usage.json"
   )
   public T isEnabled(final RadioUiElement element) {
      Allure.step("[UI - Radio] Checking if radio button is enabled: " + element.enumImpl());
      element.before().accept(driver);
      boolean enabled = radioService.isEnabled(element.componentType(), element.locator());
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), enabled);
      return uiServiceFluent;
   }

   /**
    * Validates whether the specified radio button element is enabled.
    *
    * @param element The {@link RadioUiElement} representing the radio button.
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Validate that the radio input is enabled (hard assertion).",
         tags = {"ui", "radio"}
   )
   @PandoraOptions(
         exampleFilesPath = "docs/usage/roa/ui-usage.json"
   )
   public T validateIsEnabled(final RadioUiElement element) {
      return validateIsEnabled(element, true, false);
   }

   /**
    * Validates whether the radio button is enabled.
    *
    * @param element The {@link RadioUiElement} to validate.
    * @param soft    Whether to perform a soft assertion.
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Validate that the radio input is enabled, optionally using a soft assertion.",
         tags = {"ui", "radio"}
   )
   @PandoraOptions(
         exampleFilesPath = "docs/usage/roa/ui-usage.json"
   )
   public T validateIsEnabled(final RadioUiElement element,
                              @Pandora(
                                    description = "When true, use soft assertions (don't fail immediately)."
                              ) boolean soft) {
      return validateIsEnabled(element, true, soft);
   }

   private T validateIsEnabled(final RadioUiElement element, boolean shouldBeEnabled, boolean soft) {
      Allure.step(UI_RADIO_VALIDATING_IF_RADIO_BUTTON_IS + (shouldBeEnabled ? "enabled" : "disabled") + ": "
            + element.enumImpl());
      element.before().accept(driver);
      boolean enabled = radioService.isEnabled(element.componentType(), element.locator());
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), enabled);

      String assertionMessage = shouldBeEnabled
            ? "Validating radio input is enabled"
            : "Validating radio input is disabled";

      if (soft) {
         return (T) uiServiceFluent.validate(
               softAssertions -> {
                  if (shouldBeEnabled) {
                     softAssertions.assertThat(enabled).as(assertionMessage).isTrue();
                  } else {
                     softAssertions.assertThat(enabled).as(assertionMessage).isFalse();
                  }
               }
         );
      } else {
         return (T) uiServiceFluent.validate(
               () -> {
                  if (shouldBeEnabled) {
                     Assertions.assertThat(enabled).as(assertionMessage).isTrue();
                  } else {
                     Assertions.assertThat(enabled).as(assertionMessage).isFalse();
                  }
               }
         );
      }
   }

   /**
    * Validates whether the specified radio button element is disabled.
    *
    * @param element The {@link RadioUiElement} representing the radio button.
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Validate that the radio input is disabled (hard assertion).",
         tags = {"ui", "radio"}
   )
   @PandoraOptions(
         exampleFilesPath = "docs/usage/roa/ui-usage.json"
   )
   public T validateIsDisabled(final RadioUiElement element) {
      return validateIsEnabled(element, false, false);
   }

   /**
    * Validates whether the radio button is disabled.
    *
    * @param element The {@link RadioUiElement} to validate.
    * @param soft    Whether to perform a soft assertion.
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Validate that the radio input is disabled, optionally using a soft assertion.",
         tags = {"ui", "radio"}
   )
   @PandoraOptions(
         exampleFilesPath = "docs/usage/roa/ui-usage.json"
   )
   public T validateIsDisabled(final RadioUiElement element,
                               @Pandora(
                                     description = "When true, use soft assertions (don't fail immediately)."
                               ) boolean soft) {
      return validateIsEnabled(element, false, soft);
   }

   /**
    * Checks if the specified radio button is selected.
    *
    * @param element The {@link RadioUiElement} to check.
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Evaluate whether the radio input is selected and store the result in quest storage.",
         tags = {"ui", "radio"}
   )
   @PandoraOptions(
         exampleFilesPath = "docs/usage/roa/ui-usage.json"
   )
   public T isSelected(final RadioUiElement element) {
      Allure.step("[UI - Radio] Checking if radio button is selected: " + element.enumImpl());
      element.before().accept(driver);
      boolean selected = radioService.isSelected(element.componentType(), element.locator());
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), selected);
      return uiServiceFluent;
   }

   /**
    * Validates whether the specified radio button element is selected.
    *
    * @param element The {@link RadioUiElement} representing the radio button.
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Validate that the radio input is selected (hard assertion).",
         tags = {"ui", "radio"}
   )
   @PandoraOptions(
         exampleFilesPath = "ai/roa/ui-usage.json"
   )
   public T validateIsSelected(final RadioUiElement element) {
      return validateIsSelected(element, true, false);
   }

   /**
    * Validates whether the specified radio button element is selected, with an option for soft validation.
    *
    * @param element The {@link RadioUiElement} representing the radio button.
    * @param soft    A boolean indicating whether the validation should be performed softly.
    *                If {@code true}, failures will be collected rather than throwing an exception immediately.
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Validate that the radio input is selected, optionally using a soft assertion.",
         tags = {"ui", "radio"}
   )
   @PandoraOptions(
         exampleFilesPath = "ai/roa/ui-usage.json"
   )
   public T validateIsSelected(final RadioUiElement element,
                               @Pandora(
                                     description = "When true, use soft assertions (don't fail immediately)."
                               ) boolean soft) {
      return validateIsSelected(element, true, soft);
   }

   private T validateIsSelected(final RadioUiElement element, boolean shouldBeSelected, boolean soft) {
      Allure.step(UI_RADIO_VALIDATING_IF_RADIO_BUTTON_IS + (shouldBeSelected ? "selected" : "not selected") + ": "
            + element.enumImpl());
      element.before().accept(driver);
      boolean selected = radioService.isSelected(element.componentType(), element.locator());
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), selected);

      String assertionMessage = shouldBeSelected
            ? "Validating radio input is selected"
            : "Validating radio input is not selected";

      if (soft) {
         return (T) uiServiceFluent.validate(
               softAssertions -> {
                  if (shouldBeSelected) {
                     softAssertions.assertThat(selected).as(assertionMessage).isTrue();
                  } else {
                     softAssertions.assertThat(selected).as(assertionMessage).isFalse();
                  }
               }
         );
      } else {
         return (T) uiServiceFluent.validate(
               () -> {
                  if (shouldBeSelected) {
                     Assertions.assertThat(selected).as(assertionMessage).isTrue();
                  } else {
                     Assertions.assertThat(selected).as(assertionMessage).isFalse();
                  }
               }
         );
      }
   }

   /**
    * Validates whether the specified radio button element is not selected.
    *
    * @param element The {@link RadioUiElement} representing the radio button.
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Validate that the radio input is not selected (hard assertion).",
         tags = {"ui", "radio"}
   )
   @PandoraOptions(
         exampleFilesPath = "ai/roa/ui-usage.json"
   )
   public T validateIsNotSelected(final RadioUiElement element) {
      return validateIsSelected(element, false, false);
   }

   /**
    * Validates whether the specified radio button element is not selected, with an option for soft validation.
    *
    * @param element The {@link RadioUiElement} representing the radio button.
    * @param soft    A boolean indicating whether the validation should be performed softly.
    *                If {@code true}, failures will be collected rather than throwing an exception immediately.
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Validate that the radio input is not selected, optionally using a soft assertion.",
         tags = {"ui", "radio"}
   )
   @PandoraOptions(
         exampleFilesPath = "ai/roa/ui-usage.json"
   )
   public T validateIsNotSelected(final RadioUiElement element,
                                  @Pandora(
                                        description = "When true, use soft assertions (don't fail immediately)."
                                  ) boolean soft) {
      return validateIsSelected(element, false, soft);
   }

   /**
    * Checks whether the specified radio button element is visible.
    *
    * @param element The {@link RadioUiElement} representing the radio button.
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Evaluate whether the radio input is visible and store the result in quest storage.",
         tags = {"ui", "radio"}
   )
   @PandoraOptions(
         exampleFilesPath = "ai/roa/ui-usage.json"
   )
   public T isVisible(final RadioUiElement element) {
      Allure.step("[UI - Radio] Checking if radio button is visible: " + element.enumImpl());
      element.before().accept(driver);
      boolean visible = radioService.isVisible(element.componentType(), element.locator());
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), visible);
      return uiServiceFluent;
   }

   /**
    * Validates whether the specified radio button element is visible.
    *
    * @param element The {@link RadioUiElement} representing the radio button.
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Validate that the radio input is visible (hard assertion).",
         tags = {"ui", "radio"}
   )
   @PandoraOptions(
         exampleFilesPath = "ai/roa/ui-usage.json"
   )
   public T validateIsVisible(final RadioUiElement element) {
      return validateIsVisible(element, true, false);
   }

   /**
    * Validates whether the specified radio button element is visible, with an option for soft validation.
    *
    * @param element The {@link RadioUiElement} representing the radio button.
    * @param soft    A boolean indicating whether the validation should be performed softly.
    *                If {@code true}, failures will be collected rather than throwing an exception immediately.
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Validate that the radio input is visible, optionally using a soft assertion.",
         tags = {"ui", "radio"}
   )
   @PandoraOptions(
         exampleFilesPath = "ai/roa/ui-usage.json"
   )
   public T validateIsVisible(final RadioUiElement element,
                              @Pandora(
                                    description = "When true, use soft assertions (don't fail immediately)."
                              ) boolean soft) {
      return validateIsVisible(element, true, soft);
   }

   private T validateIsVisible(final RadioUiElement element, boolean shouldBeVisible, boolean soft) {
      Allure.step(UI_RADIO_VALIDATING_IF_RADIO_BUTTON_IS + (shouldBeVisible ? "visible" : "hidden") + ": "
            + element.enumImpl());
      element.before().accept(driver);
      boolean visible = radioService.isVisible(element.componentType(), element.locator());
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), visible);

      String assertionMessage = shouldBeVisible
            ? "Validating radio input is visible"
            : "Validating radio input is hidden";

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
    * Validates whether the specified radio button element is hidden.
    *
    * @param element The {@link RadioUiElement} representing the radio button.
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Validate that the radio input is hidden (hard assertion).",
         tags = {"ui", "radio"}
   )
   @PandoraOptions(
         exampleFilesPath = "ai/roa/ui-usage.json"
   )
   public T validateIsHidden(final RadioUiElement element) {
      return validateIsVisible(element, false, false);
   }

   /**
    * Validates whether the specified radio button element is hidden, with an option for soft validation.
    *
    * @param element The {@link RadioUiElement} representing the radio button.
    * @param soft    A boolean indicating whether the validation should be performed softly.
    *                If {@code true}, failures will be collected rather than throwing an exception immediately.
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Validate that the radio input is hidden, optionally using a soft assertion.",
         tags = {"ui", "radio"}
   )
   @PandoraOptions(
         exampleFilesPath = "ai/roa/ui-usage.json"
   )
   public T validateIsHidden(final RadioUiElement element,
                             @Pandora(
                                   description = "When true, use soft assertions (don't fail immediately)."
                             ) boolean soft) {
      return validateIsVisible(element, false, soft);
   }

   /**
    * Retrieves the selected radio button's value.
    *
    * @param element The {@link RadioUiElement} to check.
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Retrieve the selected radio value and store it in quest storage.",
         tags = {"ui", "radio"}
   )
   @PandoraOptions(
         exampleFilesPath = "ai/roa/ui-usage.json"
   )
   public T getSelected(final RadioUiElement element) {
      Allure.step("[UI - Radio] Retrieving selected value for radio button: " + element.enumImpl());
      element.before().accept(driver);
      String selectedValue = radioService.getSelected(element.componentType(), element.locator());
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), selectedValue);
      return uiServiceFluent;
   }

   /**
    * Validates whether the specified radio button element has the expected selected value.
    *
    * @param element  The {@link RadioUiElement} representing the radio button.
    * @param expected The expected value of the selected radio button.
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Validate that the selected radio value equals the expected value (hard assertion).",
         tags = {"ui", "radio"}
   )
   @PandoraOptions(
         exampleFilesPath = "ai/roa/ui-usage.json"
   )
   public T validateSelected(final RadioUiElement element,
                             @Pandora(
                                   description = "Expected selected radio value."
                             ) String expected) {
      Allure.step("[UI - Radio] Validating selected value for radio button: " + element.enumImpl()
            + " (Expected: " + expected + ")");
      return validateSelected(element, expected, false);
   }

   /**
    * Validates that the selected radio button matches the expected value.
    *
    * @param element  The {@link RadioUiElement} to validate.
    * @param expected The expected value.
    * @param soft     Whether to perform a soft assertion.
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Validate that the selected radio value equals the expected value, "
               + "optionally using a soft assertion.",
         tags = {"ui", "radio"}
   )
   @PandoraOptions(
         exampleFilesPath = "ai/roa/ui-usage.json"
   )
   public T validateSelected(final RadioUiElement element,
                             @Pandora(
                                   description = "Expected selected radio value."
                             ) String expected,
                             @Pandora(
                                   description = "When true, use soft assertions (don't fail immediately)."
                             ) boolean soft) {
      Allure.step("[UI - Radio] Validating selected value for radio button: " + element.enumImpl()
            + " (Expected: " + expected + ", Soft: " + soft + ")");
      element.before().accept(driver);
      String selectedRadioInput = radioService.getSelected(element.componentType(), element.locator());
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), selectedRadioInput);

      if (soft) {
         return (T) uiServiceFluent.validate(
               softAssertions -> softAssertions.assertThat(selectedRadioInput)
                     .as("Validating selected radio Input").isEqualTo(expected)
         );
      } else {
         return (T) uiServiceFluent.validate(
               () -> Assertions.assertThat(selectedRadioInput)
                     .as("Validating selected radio Input").isEqualTo(expected)
         );
      }
   }

   /**
    * Retrieves all available radio button options.
    *
    * @param element The {@link RadioUiElement} representing the radio button group.
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Retrieve all radio options and store them in quest storage.",
         tags = {"ui", "radio"}
   )
   @PandoraOptions(
         exampleFilesPath = "ai/roa/ui-usage.json"
   )
   public T getAll(final RadioUiElement element) {
      Allure.step("[UI - Radio] Retrieving all radio button options for: " + element.enumImpl());
      element.before().accept(driver);
      List<String> allItems = radioService.getAll(element.componentType(), element.locator());
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), allItems);
      return uiServiceFluent;
   }

   /**
    * Validates that all available radio button inputs within the specified element match the expected values.
    *
    * @param element        The {@link RadioUiElement} representing the radio button group.
    * @param expectedValues The expected values of all selectable radio inputs.
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Validate that all available radio inputs match the expected values (hard assertion).",
         tags = {"ui", "radio"}
   )
   @PandoraOptions(
         exampleFilesPath = "ai/roa/ui-usage.json"
   )
   public T validateAllRadioInputs(final RadioUiElement element,
                                   @Pandora(
                                         description = "Expected radio inputs."
                                   ) final String... expectedValues) {
      Allure.step("[UI - Radio] Validating all radio inputs for: " + element.enumImpl() + " (Expected Values: "
            + Arrays.toString(expectedValues) + ")");
      return validateAllRadioInputs(element, false, expectedValues);
   }

   /**
    * Validates that the available radio button options match the expected values.
    *
    * @param element        The {@link RadioUiElement} to validate.
    * @param expectedValues The expected options.
    * @param soft           Whether to perform a soft assertion.
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Validate that all available radio inputs match the expected values, "
               + "optionally using a soft assertion.",
         tags = {"ui", "radio"}
   )
   @PandoraOptions(
         exampleFilesPath = "ai/roa/ui-usage.json"
   )
   public T validateAllRadioInputs(final RadioUiElement element,
                                   @Pandora(
                                         description = "When true, use soft assertions (don't fail immediately)."
                                   ) boolean soft,
                                   @Pandora(
                                         description = "Expected radio inputs."
                                   ) final String... expectedValues) {
      Allure.step("[UI - Radio] Validating all radio inputs for: " + element.enumImpl() + " (Soft: " + soft
            + ", Expected Values: " + Arrays.toString(expectedValues) + ")");
      element.before().accept(driver);
      List<String> selectedRadioInputs = radioService.getAll(element.componentType(), element.locator());
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), selectedRadioInputs);

      if (soft) {
         return (T) uiServiceFluent.validate(
               softAssertions -> softAssertions.assertThat(selectedRadioInputs)
                     .as("Validating radio inputs").containsAll(Arrays.asList(expectedValues))
         );
      } else {
         return (T) uiServiceFluent.validate(
               () -> Assertions.assertThat(selectedRadioInputs)
                     .as("Validating radio inputs").containsAll(Arrays.asList(expectedValues))
         );
      }
   }

   /**
    * Performs an insertion operation on the specified radio button element.
    *
    * @param componentType The type of the component.
    * @param locator       The locator of the radio button.
    * @param values        The values to insert.
    */
   @Override
   @Pandora(
         description = "Insertion hook for radio values using component type and locator.",
         tags = {"ui", "radio", "insertion"}
   )
   @PandoraOptions(
         exampleFilesPath = "ai/roa/ui-usage.json"
   )
   public void insertion(final ComponentType componentType,
                         @Pandora(
                               description = "Locator identifying the radio element."
                         ) final By locator,
                         @Pandora(
                               description = "Values to insert into the radio group."
                         ) final Object... values) {
      Allure.step("[UI - Radio] Performing insertion on radio button (ComponentType: " + componentType
            + ", Locator: " + locator + ")");
      radioService.insertion(componentType, locator, values);
   }

}
