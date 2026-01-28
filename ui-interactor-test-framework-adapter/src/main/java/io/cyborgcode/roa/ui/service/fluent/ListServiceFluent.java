package io.cyborgcode.roa.ui.service.fluent;

import io.cyborgcode.pandora.annotation.Pandora;
import io.cyborgcode.pandora.annotation.PandoraOptions;
import io.cyborgcode.pandora.model.CreationKind;
import io.cyborgcode.roa.framework.storage.Storage;
import io.cyborgcode.roa.ui.components.base.ComponentType;
import io.cyborgcode.roa.ui.components.list.ItemListService;
import io.cyborgcode.roa.ui.insertion.Insertion;
import io.cyborgcode.roa.ui.selenium.ListUiElement;
import io.cyborgcode.roa.ui.selenium.smart.SmartWebDriver;
import io.qameta.allure.Allure;
import java.util.Arrays;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;

import static io.cyborgcode.roa.ui.storage.StorageKeysUi.UI;

/**
 * Fluent service for interacting with list UI elements.
 *
 * <p>Provides methods for selecting, validating, and interacting with lists in a structured manner.
 *
 * <p>The generic type {@code T} represents the UI service fluent implementation that extends {@link UiServiceFluent},
 * allowing method chaining for seamless interaction.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@SuppressWarnings({"java:S5960", "unchecked"})
@Pandora(
      description = "Fluent UI service for interacting with lists: select/deselect, "
            + "visibility/enablement checks, retrievals and validations.",
      tags = {"ui", "fluent", "list"},
      creation = CreationKind.PROVIDED
)
@PandoraOptions(
      exampleFilesPath = "ai/roa/ui-usage.json",
      meta = {
         @PandoraOptions.Meta(key = "type", value = "fluent-service")
      }
)
public class ListServiceFluent<T extends UiServiceFluent<?>> implements Insertion {

   private static final String VALIDATING_SELECTED_ITEMS = "Validating Selected Items";
   private final ItemListService itemListService;
   private final T uiServiceFluent;
   private final Storage storage;
   private final SmartWebDriver driver;

   /**
    * Constructs a new ListServiceFluent instance.
    *
    * @param uiServiceFluent The UI service fluent instance.
    * @param storage         The storage instance for UI states.
    * @param itemListService The list service for interacting with UI elements.
    * @param webDriver       The smart web driver instance.
    */
   public ListServiceFluent(T uiServiceFluent, Storage storage, ItemListService itemListService,
                            SmartWebDriver webDriver) {
      this.itemListService = itemListService;
      this.uiServiceFluent = uiServiceFluent;
      this.storage = storage;
      driver = webDriver;
   }

   /**
    * Selects items from the list.
    *
    * @param element The list UI element.
    * @param values  The values to select.
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Select the given values in the list UI element and continue the fluent UI flow.",
         tags = {"ui", "list"}
   )
   @PandoraOptions(
         exampleFilesPath = "ai/roa/ui-usage.json"
   )
   public T select(final ListUiElement element,
         @Pandora(
               description = "Values to select in the list."
         ) final String... values) {
      Allure.step("[UI - List] [UI - List] Select values from list: " + Arrays.toString(values));
      element.before().accept(driver);
      itemListService.select(element.componentType(), element.locator(), values);
      element.after().accept(driver);
      return uiServiceFluent;
   }

   /**
    * Deselects items from the list.
    *
    * @param element The list UI element.
    * @param values  The values to deselect.
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Deselect the given values in the list UI element and continue the fluent UI flow.",
         tags = {"ui", "list"}
   )
   @PandoraOptions(
         exampleFilesPath = "ai/roa/ui-usage.json"
   )
   public T deSelect(final ListUiElement element,
         @Pandora(
               description = "Values to deselect from the list."
         ) final String... values) {
      Allure.step("[UI - List] Deselect values from list: " + Arrays.toString(values));
      element.before().accept(driver);
      itemListService.deSelect(element.componentType(), element.locator(), values);
      element.after().accept(driver);
      return uiServiceFluent;
   }

   /**
    * Checks if the given values are selected in the list.
    *
    * @param element The list UI element.
    * @param values  The values to check.
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Evaluate whether the specified values are selected and store the result in quest storage.",
         tags = {"ui", "list"}
   )
   @PandoraOptions(
         exampleFilesPath = "ai/roa/ui-usage.json"
   )
   public T areSelected(final ListUiElement element,
         @Pandora(
               description = "Values to check for selection."
         ) final String... values) {
      Allure.step(
            "[UI - List] Check if values are selected in the list: " + Arrays.toString(values));
      element.before().accept(driver);
      boolean selected = itemListService.areSelected(element.componentType(), element.locator(), values);
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), selected);
      return uiServiceFluent;
   }

   /**
    * Validates that the given values are selected in the list.
    *
    * @param element The list UI element.
    * @param values  The expected selected values.
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Validate that the specified values are selected (hard assertion).",
         tags = {"ui", "list"}
   )
   @PandoraOptions(
         exampleFilesPath = "ai/roa/ui-usage.json"
   )
   public T validateAreSelected(final ListUiElement element,
         @Pandora(
               description = "Values expected to be selected."
         ) final String... values) {
      Allure.step(
            "[UI - List] Validate if values are selected in the list: " + Arrays.toString(values));
      return validateAreSelected(element, true, false, values);
   }

   /**
    * Validates that the specified values are selected in the given list element.
    *
    * @param element The {@link ListUiElement} representing the list UI component.
    * @param soft    A boolean indicating whether the validation should be performed softly.
    *                If {@code true}, failures will be collected rather than throwing an exception immediately.
    * @param values  The expected values that should be selected in the list.
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Validate that the specified values are selected, optionally using a soft assertion.",
         tags = {"ui", "list"}
   )
   @PandoraOptions(
         exampleFilesPath = "ai/roa/ui-usage.json"
   )
   public T validateAreSelected(final ListUiElement element,
         @Pandora(
               description = "When true, use soft assertions (don't fail immediately)."
         ) boolean soft,
         @Pandora(
               description = "Values expected to be selected."
         ) final String... values) {
      Allure.step("[UI - List] Validate if values are selected (soft: " + soft + "): "
            + Arrays.toString(values));
      return validateAreSelected(element, true, soft, values);
   }

   private T validateAreSelected(final ListUiElement element, boolean shouldBeSelected, boolean soft,
                                 final String... values) {
      element.before().accept(driver);
      boolean selected = itemListService.areSelected(element.componentType(), element.locator(), values);
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), selected);

      String assertionMessage = shouldBeSelected
            ? "Validating list items are selected"
            : "Validating list items are not selected";

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
    * Validates that the given values are not selected in the list.
    *
    * @param element The list UI element.
    * @param values  The expected unselected values.
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Validate that the specified values are not selected (hard assertion).",
         tags = {"ui", "list"}
   )
   @PandoraOptions(
         exampleFilesPath = "ai/roa/ui-usage.json"
   )
   public T validateAreNotSelected(final ListUiElement element,
         @Pandora(
               description = "Values expected to NOT be selected."
         ) final String... values) {
      Allure.step("[UI - List] Validate if values are not selected in the list: "
            + Arrays.toString(values));
      return validateAreSelected(element, false, false, values);
   }

   /**
    * Validates that the specified values are not selected in the given list element.
    *
    * @param element The {@link ListUiElement} representing the list UI component.
    * @param soft    A boolean indicating whether the validation should be performed softly.
    *                If {@code true}, failures will be collected rather than throwing an exception immediately.
    * @param values  The values that should not be selected in the list.
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Validate that the specified values are not selected, optionally using a soft assertion.",
         tags = {"ui", "list"}
   )
   @PandoraOptions(
         exampleFilesPath = "ai/roa/ui-usage.json"
   )
   public T validateAreNotSelected(final ListUiElement element,
         @Pandora(
               description = "When true, use soft assertions (don't fail immediately)."
         ) boolean soft,
         @Pandora(
               description = "Values expected to NOT be selected."
         ) final String... values) {
      Allure.step("[UI - List] Validate if values are not selected (soft: " + soft + "): "
            + Arrays.toString(values));
      return validateAreSelected(element, false, soft, values);
   }

   /**
    * Checks if the specified value is selected in the given list element.
    *
    * @param element The {@link ListUiElement} representing the list UI component.
    * @param value   The value to check for selection.
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Evaluate whether the value is selected and store the result in quest storage.",
         tags = {"ui", "list"}
   )
   @PandoraOptions(
         exampleFilesPath = "ai/roa/ui-usage.json"
   )
   public T isSelected(final ListUiElement element,
         @Pandora(
               description = "Value to check for selection."
         ) final String value) {
      Allure.step("[UI - List] Check if value is selected in the list: " + value);
      element.before().accept(driver);
      boolean selected = itemListService.isSelected(element.componentType(), element.locator(), value);
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), selected);
      return uiServiceFluent;
   }

   /**
    * Validates that the specified value is selected in the given list element.
    *
    * @param element The {@link ListUiElement} representing the list UI component.
    * @param value   The value that should be selected.
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Validate that the value is selected (hard assertion).",
         tags = {"ui", "list"}
   )
   @PandoraOptions(
         exampleFilesPath = "ai/roa/ui-usage.json"
   )
   public T validateIsSelected(final ListUiElement element,
         @Pandora(
               description = "Value expected to be selected."
         ) final String value) {
      Allure.step("[UI - List] Validate if value is selected in the list: " + value);
      return validateAreSelected(element, true, false, value);
   }

   /**
    * Validates that the specified value is selected in the given list element.
    *
    * @param element The {@link ListUiElement} representing the list UI component.
    * @param soft    A boolean indicating whether the validation should be performed softly.
    *                If {@code true}, failures will be collected rather than throwing an exception immediately.
    * @param value   The value that should be selected.
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Validate that the value is selected, optionally using a soft assertion.",
         tags = {"ui", "list"}
   )
   @PandoraOptions(
         exampleFilesPath = "ai/roa/ui-usage.json"
   )
   public T validateIsSelected(final ListUiElement element,
         @Pandora(
               description = "When true, use soft assertions (don't fail immediately)."
         ) boolean soft,
         @Pandora(
               description = "Value expected to be selected."
         ) final String value) {
      Allure.step("[UI - List] Validate if value is selected (soft: " + soft + "): " + value);
      return validateAreSelected(element, true, soft, value);
   }

   /**
    * Validates that the specified value is not selected in the given list element.
    *
    * @param element The {@link ListUiElement} representing the list UI component.
    * @param value   The value that should not be selected.
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Validate that the value is not selected (hard assertion).",
         tags = {"ui", "list"}
   )
   @PandoraOptions(
         exampleFilesPath = "ai/roa/ui-usage.json"
   )
   public T validateIsNotSelected(final ListUiElement element,
         @Pandora(
               description = "Value expected to NOT be selected."
         ) final String value) {
      Allure.step("[UI - List] Validate if value is not selected in the list: " + value);
      return validateAreSelected(element, false, false, value);
   }

   /**
    * Validates that the specified value is not selected in the given list element.
    *
    * @param element The {@link ListUiElement} representing the list UI component.
    * @param soft    A boolean indicating whether the validation should be performed softly.
    *                If {@code true}, failures will be collected rather than throwing an exception immediately.
    * @param value   The value that should not be selected.
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Validate that the value is not selected, optionally using a soft assertion.",
         tags = {"ui", "list"}
   )
   @PandoraOptions(
         exampleFilesPath = "ai/roa/ui-usage.json"
   )
   public T validateIsNotSelected(final ListUiElement element,
         @Pandora(
               description = "When true, use soft assertions (don't fail immediately)."
         ) boolean soft,
         @Pandora(
               description = "Value expected to NOT be selected."
         ) final String value) {
      Allure.step("[UI - List] Validate if value is not selected (soft: " + soft + "): " + value);
      return validateAreSelected(element, false, soft, value);
   }

   /**
    * Checks if the given values are enabled in the list.
    *
    * @param element The list UI element.
    * @param values  The values to check.
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Evaluate whether the specified values are enabled and store the result in quest storage.",
         tags = {"ui", "list"}
   )
   @PandoraOptions(
         exampleFilesPath = "ai/roa/ui-usage.json"
   )
   public T areEnabled(final ListUiElement element,
         @Pandora(
               description = "Values to check for being enabled."
         ) final String... values) {
      Allure.step(
            "[UI - List] Check if values are enabled in the list: " + Arrays.toString(values));
      element.before().accept(driver);
      boolean enabled = itemListService.areEnabled(element.componentType(), element.locator(), values);
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), enabled);
      return uiServiceFluent;
   }

   /**
    * Validates that the list items are enabled.
    *
    * @param element The list UI element.
    * @param values  The values to validate.
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Validate that the specified values are enabled (hard assertion).",
         tags = {"ui", "list"}
   )
   @PandoraOptions(
         exampleFilesPath = "ai/roa/ui-usage.json"
   )
   public T validateAreEnabled(final ListUiElement element,
         @Pandora(
               description = "Values expected to be enabled."
         ) final String... values) {
      Allure.step(
            "[UI - List] Validate if values are enabled in the list: " + Arrays.toString(values));
      return validateAreEnabled(element, true, false, values);
   }

   /**
    * Validates that the specified values in the given list element are enabled.
    *
    * @param element The {@link ListUiElement} representing the list UI component.
    * @param soft    A boolean indicating whether the validation should be performed softly.
    *                If {@code true}, failures will be collected rather than throwing an exception immediately.
    * @param values  The values that should be enabled.
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Validate that the specified values are enabled, optionally using a soft assertion.",
         tags = {"ui", "list"}
   )
   @PandoraOptions(
         exampleFilesPath = "ai/roa/ui-usage.json"
   )
   public T validateAreEnabled(final ListUiElement element,
         @Pandora(
               description = "When true, use soft assertions (don't fail immediately)."
         ) boolean soft,
         @Pandora(
               description = "Values expected to be enabled."
         ) final String... values) {
      Allure.step("[UI - List] Validate if values are enabled (soft: " + soft + "): "
            + Arrays.toString(values));
      return validateAreEnabled(element, true, soft, values);
   }

   private T validateAreEnabled(final ListUiElement element, boolean shouldBeEnabled, boolean soft,
                                final String... values) {
      element.before().accept(driver);
      boolean enabled = itemListService.areEnabled(element.componentType(), element.locator(), values);
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), enabled);

      String assertionMessage = shouldBeEnabled
            ? "Validating list items are enabled"
            : "Validating list items are disabled";

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
    * Validates that the specified values in the given list element are disabled.
    *
    * @param element The {@link ListUiElement} representing the list UI component.
    * @param values  The values that should be disabled.
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Validate that the specified values are disabled (hard assertion).",
         tags = {"ui", "list"}
   )
   @PandoraOptions(
         exampleFilesPath = "ai/roa/ui-usage.json"
   )
   public T validateAreDisabled(final ListUiElement element,
         @Pandora(
               description = "Values expected to be disabled."
         ) final String... values) {
      Allure.step("[UI - List] Validate that the specified values in the list are disabled");
      return validateAreEnabled(element, false, false, values);
   }

   /**
    * Validates that the specified values in the given list element are disabled.
    *
    * @param element The {@link ListUiElement} representing the list UI component.
    * @param soft    A boolean indicating whether the validation should be performed softly.
    *                If {@code true}, failures will be collected rather than throwing an exception immediately.
    * @param values  The values that should be disabled.
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Validate that the specified values are disabled, optionally using a soft assertion.",
         tags = {"ui", "list"}
   )
   @PandoraOptions(
         exampleFilesPath = "ai/roa/ui-usage.json"
   )
   public T validateAreDisabled(final ListUiElement element,
         @Pandora(
               description = "When true, use soft assertions (don't fail immediately)."
         ) boolean soft,
         @Pandora(
               description = "Values expected to be disabled."
         ) final String... values) {
      Allure.step("[UI - List] Validate that the specified values in the list are disabled");
      return validateAreEnabled(element, false, soft, values);
   }

   /**
    * Checks if the specified value in the given list element is enabled.
    *
    * @param element The {@link ListUiElement} representing the list UI component.
    * @param value   The specific value to check for being enabled.
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Evaluate whether the value is enabled and store the result in quest storage.",
         tags = {"ui", "list"}
   )
   @PandoraOptions(
         exampleFilesPath = "ai/roa/ui-usage.json"
   )
   public T isEnabled(final ListUiElement element,
         @Pandora(
               description = "Value to check for being enabled."
         ) final String value) {
      Allure.step("[UI - List] Check if the specified value is enabled");

      element.before().accept(driver);
      boolean enabled = itemListService.isEnabled(element.componentType(), element.locator(), value);
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), enabled);
      return uiServiceFluent;
   }

   /**
    * Validates that the specified value in the given list element is enabled.
    *
    * @param element The {@link ListUiElement} representing the list UI component.
    * @param value   The specific value that should be enabled.
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Validate that the value is enabled (hard assertion).",
         tags = {"ui", "list"}
   )
   @PandoraOptions(
         exampleFilesPath = "ai/roa/ui-usage.json"
   )
   public T validateIsEnabled(final ListUiElement element,
         @Pandora(
               description = "Value expected to be enabled."
         ) final String value) {
      Allure.step("[UI - List] Validate that the specified value is enabled");
      return validateAreEnabled(element, true, false, value);
   }

   /**
    * Validates that the specified value in the given list element is enabled.
    *
    * @param element The {@link ListUiElement} representing the list UI component.
    * @param soft    A boolean indicating whether the validation should be performed softly.
    *                If {@code true}, failures will be collected rather than throwing an exception immediately.
    * @param value   The specific value that should be enabled.
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Validate that the value is enabled, optionally using a soft assertion.",
         tags = {"ui", "list"}
   )
   @PandoraOptions(
         exampleFilesPath = "ai/roa/ui-usage.json"
   )
   public T validateIsEnabled(final ListUiElement element,
         @Pandora(
               description = "When true, use soft assertions (don't fail immediately)."
         ) boolean soft,
         @Pandora(
               description = "Value expected to be enabled."
         ) final String value) {
      Allure.step("[UI - List] Validate that the specified value is enabled");
      return validateAreEnabled(element, true, soft, value);
   }

   /**
    * Validates that the specified value in the given list element is disabled.
    *
    * @param element The {@link ListUiElement} representing the list UI component.
    * @param value   The specific value that should be disabled.
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Validate that the value is disabled (hard assertion).",
         tags = {"ui", "list"}
   )
   @PandoraOptions(
         exampleFilesPath = "ai/roa/ui-usage.json"
   )
   public T validateIsDisabled(final ListUiElement element,
         @Pandora(
               description = "Value expected to be disabled."
         ) final String value) {
      Allure.step("[UI - List] Validate that the specified value is disabled");
      return validateAreEnabled(element, false, false, value);
   }

   /**
    * Validates that the specified value in the given list element is disabled.
    *
    * @param element The {@link ListUiElement} representing the list UI component.
    * @param soft    A boolean indicating whether the validation should be performed softly.
    *                If {@code true}, failures will be collected rather than throwing an exception immediately.
    * @param value   The specific value that should be disabled.
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Validate that the value is disabled, optionally using a soft assertion.",
         tags = {"ui", "list"}
   )
   @PandoraOptions(
         exampleFilesPath = "ai/roa/ui-usage.json"
   )
   public T validateIsDisabled(final ListUiElement element,
         @Pandora(
               description = "When true, use soft assertions (don't fail immediately)."
         ) boolean soft,
         @Pandora(
               description = "Value expected to be disabled."
         ) final String value) {
      Allure.step("[UI - List] Validate that the specified value is disabled");
      return validateAreEnabled(element, false, soft, value);
   }

   /**
    * Checks if the given values are visible in the list.
    *
    * @param element The list UI element.
    * @param values  The values to check.
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Evaluate whether the specified values are visible and store the result in quest storage.",
         tags = {"ui", "list"}
   )
   @PandoraOptions(
         exampleFilesPath = "ai/roa/ui-usage.json"
   )
   public T areVisible(final ListUiElement element,
         @Pandora(
               description = "Values to check for being visible."
         ) final String... values) {
      Allure.step("[UI - List] Check if the specified values are visible");

      element.before().accept(driver);
      boolean visible = itemListService.areVisible(element.componentType(), element.locator(), values);
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), visible);
      return uiServiceFluent;
   }

   /**
    * Validates that the given values are visible in the list.
    *
    * @param element The list UI element.
    * @param values  The expected visible values.
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Validate that the specified values are visible (hard assertion).",
         tags = {"ui", "list"}
   )
   @PandoraOptions(
         exampleFilesPath = "ai/roa/ui-usage.json"
   )
   public T validateAreVisible(final ListUiElement element,
         @Pandora(
               description = "Values expected to be visible."
         ) final String... values) {
      Allure.step("[UI - List] Validate that the specified values are visible in the list");
      return validateAreVisible(element, true, false, values);
   }

   /**
    * Validates that the specified values in the given list element are visible.
    *
    * @param element The {@link ListUiElement} representing the list UI component.
    * @param soft    A boolean indicating whether the validation should be performed softly.
    *                If {@code true}, failures will be collected rather than throwing an exception immediately.
    * @param values  The specific values that should be visible.
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Validate that the specified values are visible, optionally using a soft assertion.",
         tags = {"ui", "list"}
   )
   @PandoraOptions(
         exampleFilesPath = "ai/roa/ui-usage.json"
   )
   public T validateAreVisible(final ListUiElement element,
         @Pandora(
               description = "When true, use soft assertions (don't fail immediately)."
         ) boolean soft,
         @Pandora(
               description = "Values expected to be visible."
         ) final String... values) {
      Allure.step("[UI - List] Validate that the specified values are visible in the list");
      return validateAreVisible(element, true, soft, values);
   }

   private T validateAreVisible(final ListUiElement element, boolean shouldBeVisible, boolean soft,
                                final String... values) {
      element.before().accept(driver);
      boolean visible = itemListService.areVisible(element.componentType(), element.locator(), values);
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), visible);

      String assertionMessage = shouldBeVisible
            ? "Validating list items are visible"
            : "Validating list items are hidden";

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
    * Validates that the specified values in the given list element are hidden.
    *
    * @param element The {@link ListUiElement} representing the list UI component.
    * @param values  The specific values that should be hidden.
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Validate that the specified values are hidden (hard assertion).",
         tags = {"ui", "list"}
   )
   @PandoraOptions(
         exampleFilesPath = "ai/roa/ui-usage.json"
   )
   public T validateAreHidden(final ListUiElement element,
         @Pandora(
               description = "Values expected to be hidden."
         ) final String... values) {
      Allure.step("[UI - List] Validate that the specified values are hidden in the list");
      return validateAreVisible(element, false, false, values);
   }

   /**
    * Validates that the specified values in the given list element are hidden.
    *
    * @param element The {@link ListUiElement} representing the list UI component.
    * @param soft    A boolean indicating whether the validation should be performed softly.
    *                If {@code true}, failures will be collected rather than throwing an exception immediately.
    * @param values  The specific values that should be hidden.
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Validate that the specified values are hidden, optionally using a soft assertion.",
         tags = {"ui", "list"}
   )
   @PandoraOptions(
         exampleFilesPath = "ai/roa/ui-usage.json"
   )
   public T validateAreHidden(final ListUiElement element,
         @Pandora(
               description = "When true, use soft assertions (don't fail immediately)."
         ) boolean soft,
         @Pandora(
               description = "Values expected to be hidden."
         ) final String... values) {
      Allure.step("[UI - List] Validate that the specified values are hidden in the list");
      return validateAreVisible(element, false, soft, values);
   }

   /**
    * Checks if the specified value in the given list element is visible.
    *
    * @param element The {@link ListUiElement} representing the list UI component.
    * @param value   The specific value to check for visibility.
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Evaluate whether the value is visible and store the result in quest storage.",
         tags = {"ui", "list"}
   )
   @PandoraOptions(
         exampleFilesPath = "ai/roa/ui-usage.json"
   )
   public T isVisible(final ListUiElement element,
         @Pandora(
               description = "Value to check for visibility."
         ) final String value) {
      Allure.step("[UI - List] Check if the specified value is visible");

      element.before().accept(driver);
      boolean visible = itemListService.isVisible(element.componentType(), element.locator(), value);
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), visible);
      return uiServiceFluent;
   }

   /**
    * Validates that the specified value in the given list element is visible.
    *
    * @param element The {@link ListUiElement} representing the list UI component.
    * @param value   The specific value that should be visible.
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Validate that the value is visible (hard assertion).",
         tags = {"ui", "list"}
   )
   @PandoraOptions(
         exampleFilesPath = "ai/roa/ui-usage.json"
   )
   public T validateIsVisible(final ListUiElement element,
         @Pandora(
               description = "Value expected to be visible."
         ) final String value) {
      Allure.step("[UI - List] Validate that the specified value is visible");
      return validateAreVisible(element, true, false, value);
   }

   /**
    * Validates that the specified value in the given list element is visible.
    *
    * @param element The {@link ListUiElement} representing the list UI component.
    * @param soft    A boolean indicating whether the validation should be performed softly.
    *                If {@code true}, failures will be collected rather than throwing an exception immediately.
    * @param value   The specific value that should be visible.
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Validate that the value is visible, optionally using a soft assertion.",
         tags = {"ui", "list"}
   )
   @PandoraOptions(
         exampleFilesPath = "ai/roa/ui-usage.json"
   )
   public T validateIsVisible(final ListUiElement element,
         @Pandora(
               description = "When true, use soft assertions (don't fail immediately)."
         ) boolean soft,
         @Pandora(
               description = "Value expected to be visible."
         ) final String value) {
      Allure.step("[UI - List] Validate that the specified value is visible");
      return validateAreVisible(element, true, soft, value);
   }

   /**
    * Validates that the specified value in the given list element is hidden.
    *
    * @param element The {@link ListUiElement} representing the list UI component.
    * @param value   The specific value that should be hidden.
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Validate that the value is hidden (hard assertion).",
         tags = {"ui", "list"}
   )
   @PandoraOptions(
         exampleFilesPath = "ai/roa/ui-usage.json"
   )
   public T validateIsHidden(final ListUiElement element,
         @Pandora(
               description = "Value expected to be hidden."
         ) final String value) {
      Allure.step("[UI - List] Validate that the specified value is hidden");
      return validateAreVisible(element, false, false, value);
   }

   /**
    * Validates that the specified value in the given list element is hidden.
    *
    * @param element The {@link ListUiElement} representing the list UI component.
    * @param soft    A boolean indicating whether the validation should be performed softly.
    *                If {@code true}, failures will be collected rather than throwing an exception immediately.
    * @param value   The specific value that should be hidden.
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Validate that the value is hidden, optionally using a soft assertion.",
         tags = {"ui", "list"}
   )
   @PandoraOptions(
         exampleFilesPath = "ai/roa/ui-usage.json"
   )
   public T validateIsHidden(final ListUiElement element,
         @Pandora(
               description = "When true, use soft assertions (don't fail immediately)."
         ) boolean soft,
         @Pandora(
               description = "Value expected to be hidden."
         ) final String value) {
      Allure.step("[UI - List] Validate that the specified value is hidden");
      return validateAreVisible(element, false, soft, value);
   }

   /**
    * Retrieves the selected items from the list.
    *
    * @param element The list UI element.
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Retrieve selected list items and store them in quest storage.",
         tags = {"ui", "list"}
   )
   @PandoraOptions(
         exampleFilesPath = "ai/roa/ui-usage.json"
   )
   public T getSelected(final ListUiElement element) {
      Allure.step("[UI - List] Retrieve selected items from the list");
      element.before().accept(driver);
      List<String> selectedItems = itemListService.getSelected(element.componentType(), element.locator());
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), selectedItems);
      return uiServiceFluent;
   }

   /**
    * Validates that the specified items are selected within the given list UI element.
    *
    * @param element        The {@link ListUiElement} representing the list UI component.
    * @param expectedValues The expected values that should be selected.
    * @return The fluent UI service instance, allowing for method chaining.
    */
   @Pandora(
         description = "Validate that the specified items are selected (hard assertion).",
         tags = {"ui", "list"}
   )
   @PandoraOptions(
         exampleFilesPath = "ai/roa/ui-usage.json"
   )
   public T validateSelectedItems(final ListUiElement element,
         @Pandora(
               description = "Expected values that should be selected."
         ) final String... expectedValues) {
      Allure.step("[UI - List] Validate that the specified items are selected");
      return validateSelectedItems(element, true, false, expectedValues);
   }

   /**
    * Validates that the specified items are selected within the given list UI element.
    *
    * @param element        The {@link ListUiElement} representing the list UI component.
    * @param soft           A boolean indicating whether the validation should be performed softly.
    *                       If {@code true}, failures will be collected rather than throwing an exception immediately.
    * @param expectedValues The expected values that should be selected.
    * @return The fluent UI service instance, allowing for method chaining.
    */
   @Pandora(
         description = "Validate that the specified items are selected, optionally using a soft assertion.",
         tags = {"ui", "list"}
   )
   @PandoraOptions(
         exampleFilesPath = "ai/roa/ui-usage.json"
   )
   public T validateSelectedItems(final ListUiElement element,
         @Pandora(
               description = "When true, use soft assertions (don't fail immediately)."
         ) boolean soft,
         @Pandora(
               description = "Expected values that should be selected."
         ) final String... expectedValues) {
      Allure.step("[UI - List] Validate that the specified items are selected");
      return validateSelectedItems(element, true, soft, expectedValues);
   }

   private T validateSelectedItems(final ListUiElement element, boolean shouldBeSelected, boolean soft,
                                   final String... expectedValues) {
      element.before().accept(driver);
      List<String> selectedItems = itemListService.getSelected(element.componentType(), element.locator());
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), selectedItems);

      if (soft) {
         return (T) uiServiceFluent.validate(
               softAssertions -> {
                  if (shouldBeSelected) {
                     softAssertions.assertThat(selectedItems).as(VALIDATING_SELECTED_ITEMS)
                           .containsAll(Arrays.asList(expectedValues));
                  } else {
                     softAssertions.assertThat(selectedItems).as(VALIDATING_SELECTED_ITEMS)
                           .doesNotContainAnyElementsOf(Arrays.asList(expectedValues));
                  }
               }
         );
      } else {
         return (T) uiServiceFluent.validate(
               () -> {
                  if (shouldBeSelected) {
                     Assertions.assertThat(selectedItems).as(VALIDATING_SELECTED_ITEMS)
                           .containsAll(Arrays.asList(expectedValues));
                  } else {
                     Assertions.assertThat(selectedItems).as(VALIDATING_SELECTED_ITEMS)
                           .doesNotContainAnyElementsOf(Arrays.asList(expectedValues));
                  }
               }
         );
      }
   }

   /**
    * Validates that the specified items are not selected within the given list UI element.
    *
    * @param element        The {@link ListUiElement} representing the list UI component.
    * @param expectedValues The expected values that should not be selected.
    * @return The fluent UI service instance, allowing for method chaining.
    */
   @Pandora(
         description = "Validate that the specified items are not selected (hard assertion).",
         tags = {"ui", "list"}
   )
   @PandoraOptions(
         exampleFilesPath = "ai/roa/ui-usage.json"
   )
   public T validateNotSelectedItems(final ListUiElement element,
         @Pandora(
               description = "Expected values that should NOT be selected."
         ) final String... expectedValues) {
      Allure.step("[UI - List] Validate that the specified items are not selected");
      return validateSelectedItems(element, false, false, expectedValues);
   }

   /**
    * Validates that the specified items are not selected within the given list UI element.
    *
    * @param element        The {@link ListUiElement} representing the list UI component.
    * @param soft           A boolean indicating whether the validation should be performed softly.
    *                       If {@code true}, failures will be collected rather than throwing an exception immediately.
    * @param expectedValues The expected values that should not be selected.
    * @return The fluent UI service instance, allowing for method chaining.
    */
   @Pandora(
         description = "Validate that the specified items are not selected, optionally using a soft assertion.",
         tags = {"ui", "list"}
   )
   @PandoraOptions(
         exampleFilesPath = "ai/roa/ui-usage.json"
   )
   public T validateNotSelectedItems(final ListUiElement element,
         @Pandora(
               description = "When true, use soft assertions (don't fail immediately)."
         ) boolean soft,
         @Pandora(
               description = "Expected values that should NOT be selected."
         ) final String... expectedValues) {
      Allure.step("[UI - List] Validate that the specified items are not selected");
      return validateSelectedItems(element, false, soft, expectedValues);
   }

   /**
    * Retrieves all available items in the list.
    *
    * @param element The list UI element.
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Retrieve all list items and store them in quest storage.",
         tags = {"ui", "list"}
   )
   @PandoraOptions(
         exampleFilesPath = "ai/roa/ui-usage.json"
   )
   public T getAll(final ListUiElement element) {
      Allure.step("[UI - List] Retrieve all items from the list");

      element.before().accept(driver);
      List<String> allItems = itemListService.getAll(element.componentType(), element.locator());
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), allItems);
      return uiServiceFluent;
   }

   /**
    * Validates that all expected items are present within the given list UI element.
    *
    * @param element        The {@link ListUiElement} representing the list UI component.
    * @param expectedValues The expected values that should be present in the list.
    * @return The fluent UI service instance, allowing for method chaining.
    */
   @Pandora(
         description = "Validate that all expected items are present (hard assertion).",
         tags = {"ui", "list"}
   )
   @PandoraOptions(
         exampleFilesPath = "ai/roa/ui-usage.json"
   )
   public T validateAllItems(final ListUiElement element,
         @Pandora(
               description = "Expected items that should be present in the list."
         ) final String... expectedValues) {
      Allure.step("[UI - List] Validate that all expected items are present in the list");
      return validateAllItems(element, false, expectedValues);
   }

   /**
    * Validates that all expected items are present within the given list UI element.
    *
    * @param element        The {@link ListUiElement} representing the list UI component.
    * @param soft           A boolean indicating whether the validation should be performed softly.
    *                       If {@code true}, failures will be collected rather than throwing an exception immediately.
    * @param expectedValues The expected values that should be present in the list.
    * @return The fluent UI service instance, allowing for method chaining.
    */
   public T validateAllItems(final ListUiElement element,
         @Pandora(
               description = "When true, use soft assertions (don't fail immediately)."
         ) boolean soft,
         @Pandora(
               description = "Expected items that should be present in the list."
         ) final String... expectedValues) {
      Allure.step("[UI - List] Validate that all expected items are present in the list");
      element.before().accept(driver);
      List<String> allItems = itemListService.getSelected(element.componentType(), element.locator());
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), allItems);

      if (soft) {
         return (T) uiServiceFluent.validate(
               softAssertions -> softAssertions.assertThat(allItems)
                     .as("Validating Items").containsAll(Arrays.asList(expectedValues))
         );
      } else {
         return (T) uiServiceFluent.validate(
               () -> Assertions.assertThat(allItems)
                     .as("Validating Items").containsAll(Arrays.asList(expectedValues))
         );
      }
   }

   /**
    * Inserts values into the list.
    *
    * @param componentType The type of the list component.
    * @param locator       The locator of the list element.
    * @param values        The values to insert.
    */
   @Override
   @Pandora(
         description = "Insertion hook for list values using component type and locator.",
         tags = {"ui", "list", "insertion"}
   )
   @PandoraOptions(
         exampleFilesPath = "ai/roa/ui-usage.json"
   )
   public void insertion(final ComponentType componentType,
         @Pandora(
               description = "Locator identifying the list element."
         ) final By locator,
         @Pandora(
               description = "Values to insert into the list."
         ) final Object... values) {
      Allure.step("[UI - List] Insert values into the list");
      itemListService.insertion(componentType, locator, values);
   }

}
