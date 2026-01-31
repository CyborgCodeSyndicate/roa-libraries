package io.cyborgcode.roa.ui.service.fluent;

import io.cyborgcode.pandora.annotation.Pandora;
import io.cyborgcode.pandora.annotation.PandoraOptions;
import io.cyborgcode.pandora.model.CreationKind;
import io.cyborgcode.roa.framework.storage.Storage;
import io.cyborgcode.roa.ui.components.tab.TabService;
import io.cyborgcode.roa.ui.selenium.TabUiElement;
import io.cyborgcode.roa.ui.selenium.smart.SmartWebDriver;
import io.qameta.allure.Allure;
import org.assertj.core.api.Assertions;

import static io.cyborgcode.roa.ui.storage.StorageKeysUi.UI;

/**
 * Provides a fluent API for interacting with tab UI components.
 *
 * <p>This class enables interactions with tab elements, including clicking,
 * checking visibility, validating selection, and enabling or disabling tabs.
 * It integrates seamlessly with {@link UiServiceFluent} to support method chaining.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@SuppressWarnings({"java:S5960", "unchecked"})
@Pandora(
      description = "Fluent UI service for interacting with tabs: click, "
            + "selection/visibility/enablement checks and validations.",
      tags = {"ui", "fluent", "tab"},
      creation = CreationKind.PROVIDED
)
@PandoraOptions(
      exampleFilesPath = "docs/usage/roa/ui-usage.json",
      meta = {
         @PandoraOptions.Meta(key = "type", value = "fluent-service")
      }
)
public class TabServiceFluent<T extends UiServiceFluent<?>> {

   private static final String UI_TAB_CHECKING_IF_THE_TAB = "[UI - Tab] Checking if the tab ";
   private static final String UI_TAB_VALIDATING_IF_THE_TAB = "[UI - Tab] Validating if the tab ";
   private static final String UI_TAB_PERFORMING_SOFT_VALIDATION_FOR_THE_TAB =
         "[UI - Tab] Performing soft validation for the tab ";
   private static final String UI_TAB_PERFORMING_STRICT_VALIDATION_FOR_THE_TAB =
         "[UI - Tab] Performing strict validation for the tab ";

   private final TabService tabService;
   private final T uiServiceFluent;
   private final Storage storage;
   private final SmartWebDriver driver;

   /**
    * Constructs a new {@code TabServiceFluent} instance.
    *
    * @param uiServiceFluent The parent UI service fluent instance.
    * @param storage         The storage instance for persisting UI states.
    * @param tabService      The tab service instance for performing tab-related operations.
    * @param webDriver       The WebDriver instance for browser interactions.
    */
   public TabServiceFluent(T uiServiceFluent, Storage storage, TabService tabService,
                           SmartWebDriver webDriver) {
      this.tabService = tabService;
      this.uiServiceFluent = uiServiceFluent;
      this.storage = storage;
      driver = webDriver;
   }

   /**
    * Clicks on the specified tab UI element.
    *
    * @param element The {@link TabUiElement} representing the tab.
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Click the given tab UI element and continue the fluent UI flow.",
         tags = {"ui", "tab"}
   )
   @PandoraOptions(
         exampleFilesPath = "docs/usage/roa/ui-usage.json"
   )
   public T click(final TabUiElement element) {
      Allure.step("[UI - Tab] Clicking on the tab " + element);
      element.before().accept(driver);
      tabService.click(element.componentType(), element.locator());
      element.after().accept(driver);
      return uiServiceFluent;
   }

   /**
    * Checks if the specified tab is enabled.
    *
    * @param element The {@link TabUiElement} representing the tab.
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Evaluate whether the tab is enabled and store the result in quest storage.",
         tags = {"ui", "tab"}
   )
   @PandoraOptions(
         exampleFilesPath = "docs/usage/roa/ui-usage.json"
   )
   public T isEnabled(final TabUiElement element) {
      Allure.step(UI_TAB_CHECKING_IF_THE_TAB + element + " is enabled.");
      element.before().accept(driver);
      boolean enabled = tabService.isEnabled(element.componentType(), element.locator());
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), enabled);
      return uiServiceFluent;
   }

   /**
    * Validates that the specified tab is enabled.
    *
    * @param element The {@link TabUiElement} representing the tab.
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Validate that the tab is enabled (hard assertion).",
         tags = {"ui", "tab"}
   )
   @PandoraOptions(
         exampleFilesPath = "docs/usage/roa/ui-usage.json"
   )
   public T validateIsEnabled(final TabUiElement element) {
      return validateIsEnabled(element, true, false);
   }

   /**
    * Validates whether the specified tab is enabled or disabled.
    *
    * @param element The {@link TabUiElement} representing the tab.
    * @param soft    Whether to perform a soft assertion.
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Validate that the tab is enabled, optionally using a soft assertion.",
         tags = {"ui", "tab"}
   )
   @PandoraOptions(
         exampleFilesPath = "docs/usage/roa/ui-usage.json"
   )
   public T validateIsEnabled(final TabUiElement element,
                              @Pandora(
                                    description = "When true, use soft assertions (don't fail immediately)."
                              ) boolean soft) {
      return validateIsEnabled(element, true, soft);
   }

   private T validateIsEnabled(final TabUiElement element, boolean shouldBeEnabled, boolean soft) {
      Allure.step(UI_TAB_VALIDATING_IF_THE_TAB + element + " is " + (shouldBeEnabled ? "enabled" : "disabled"));
      element.before().accept(driver);
      boolean enabled = tabService.isEnabled(element.componentType(), element.locator());
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), enabled);

      String assertionMessage = shouldBeEnabled
            ? "Validating tab is enabled"
            : "Validating tab is disabled";

      if (soft) {
         Allure.step(UI_TAB_PERFORMING_SOFT_VALIDATION_FOR_THE_TAB + element);
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
         Allure.step(UI_TAB_PERFORMING_STRICT_VALIDATION_FOR_THE_TAB + element);
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
    * Validates that the specified tab is disabled.
    *
    * @param element The {@link TabUiElement} representing the tab.
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Validate that the tab is disabled (hard assertion).",
         tags = {"ui", "tab"}
   )
   @PandoraOptions(
         exampleFilesPath = "docs/usage/roa/ui-usage.json"
   )
   public T validateIsDisabled(final TabUiElement element) {
      return validateIsEnabled(element, false, false);
   }

   /**
    * Validates whether the specified tab is disabled.
    *
    * @param element The {@link TabUiElement} representing the tab.
    * @param soft    Whether to perform a soft assertion.
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Validate that the tab is disabled, optionally using a soft assertion.",
         tags = {"ui", "tab"}
   )
   @PandoraOptions(
         exampleFilesPath = "ai/roa/ui-usage.json"
   )
   public T validateIsDisabled(final TabUiElement element,
                               @Pandora(
                                     description = "When true, use soft assertions (don't fail immediately)."
                               ) boolean soft) {
      return validateIsEnabled(element, false, soft);
   }

   /**
    * Checks if the specified tab is visible.
    *
    * @param element The {@link TabUiElement} representing the tab.
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Evaluate whether the tab is visible and store the result in quest storage.",
         tags = {"ui", "tab"}
   )
   @PandoraOptions(
         exampleFilesPath = "ai/roa/ui-usage.json"
   )
   public T isVisible(final TabUiElement element) {
      Allure.step(UI_TAB_CHECKING_IF_THE_TAB + element + " is visible.");
      element.before().accept(driver);
      boolean visible = tabService.isVisible(element.componentType(), element.locator());
      storage.sub(UI).put(element.enumImpl(), visible);
      element.after().accept(driver);
      return uiServiceFluent;
   }

   /**
    * Validates that the specified tab is visible.
    *
    * @param element The {@link TabUiElement} representing the tab.
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Validate that the tab is visible (hard assertion).",
         tags = {"ui", "tab"}
   )
   @PandoraOptions(
         exampleFilesPath = "ai/roa/ui-usage.json"
   )
   public T validateIsVisible(final TabUiElement element) {
      return validateIsVisible(element, true, false);
   }

   /**
    * Validates whether the specified tab is visible.
    *
    * @param element The {@link TabUiElement} representing the tab.
    * @param soft    Whether to perform a soft assertion.
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Validate that the tab is visible, optionally using a soft assertion.",
         tags = {"ui", "tab"}
   )
   @PandoraOptions(
         exampleFilesPath = "ai/roa/ui-usage.json"
   )
   public T validateIsVisible(final TabUiElement element,
                              @Pandora(
                                    description = "When true, use soft assertions (don't fail immediately)."
                              ) boolean soft) {
      return validateIsVisible(element, true, soft);
   }

   private T validateIsVisible(final TabUiElement element, boolean shouldBeVisible, boolean soft) {
      Allure.step(UI_TAB_VALIDATING_IF_THE_TAB + element + " is " + (shouldBeVisible ? "visible" : "hidden"));
      element.before().accept(driver);
      boolean visible = tabService.isVisible(element.componentType(), element.locator());
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), visible);

      String assertionMessage = shouldBeVisible
            ? "Validating tab is visible"
            : "Validating tab is hidden";

      if (soft) {
         Allure.step(UI_TAB_PERFORMING_SOFT_VALIDATION_FOR_THE_TAB + element);
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
         Allure.step(UI_TAB_PERFORMING_STRICT_VALIDATION_FOR_THE_TAB + element);
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
    * Validates that the specified tab is hidden.
    *
    * @param element The {@link TabUiElement} representing the tab.
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Validate that the tab is hidden (hard assertion).",
         tags = {"ui", "tab"}
   )
   @PandoraOptions(
         exampleFilesPath = "ai/roa/ui-usage.json"
   )
   public T validateIsHidden(final TabUiElement element) {
      return validateIsVisible(element, false, false);
   }

   /**
    * Validates whether the specified tab is hidden.
    *
    * @param element The {@link TabUiElement} representing the tab.
    * @param soft    Whether to perform a soft assertion.
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Validate that the tab is hidden, optionally using a soft assertion.",
         tags = {"ui", "tab"}
   )
   @PandoraOptions(
         exampleFilesPath = "ai/roa/ui-usage.json"
   )
   public T validateIsHidden(final TabUiElement element,
                             @Pandora(
                                   description = "When true, use soft assertions (don't fail immediately)."
                             ) boolean soft) {
      return validateIsVisible(element, false, soft);
   }

   /**
    * Checks if the specified tab is selected.
    *
    * @param element The {@link TabUiElement} representing the tab.
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Evaluate whether the tab is selected and store the result in quest storage.",
         tags = {"ui", "tab"}
   )
   @PandoraOptions(
         exampleFilesPath = "ai/roa/ui-usage.json"
   )
   public T isSelected(final TabUiElement element) {
      Allure.step(UI_TAB_CHECKING_IF_THE_TAB + element + " is selected.");
      element.before().accept(driver);
      boolean selected = tabService.isSelected(element.componentType(), element.locator());
      storage.sub(UI).put(element.enumImpl(), selected);
      element.after().accept(driver);
      return uiServiceFluent;
   }

   /**
    * Validates that the specified tab is selected.
    *
    * @param element The {@link TabUiElement} representing the tab.
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Validate that the tab is selected (hard assertion).",
         tags = {"ui", "tab"}
   )
   @PandoraOptions(
         exampleFilesPath = "ai/roa/ui-usage.json"
   )
   public T validateIsSelected(final TabUiElement element) {
      return validateIsSelected(element, true, false);
   }

   /**
    * Validates whether the specified tab is selected.
    *
    * @param element The {@link TabUiElement} representing the tab.
    * @param soft    A boolean indicating whether the validation should be performed softly.
    *                If {@code true}, failures will be collected rather than throwing an exception immediately.
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Validate that the tab is selected, optionally using a soft assertion.",
         tags = {"ui", "tab"}
   )
   @PandoraOptions(
         exampleFilesPath = "ai/roa/ui-usage.json"
   )
   public T validateIsSelected(final TabUiElement element,
                               @Pandora(
                                     description = "When true, use soft assertions (don't fail immediately)."
                               ) boolean soft) {
      return validateIsSelected(element, true, soft);
   }

   private T validateIsSelected(final TabUiElement element, boolean shouldBeSelected, boolean soft) {
      Allure.step(UI_TAB_VALIDATING_IF_THE_TAB + element + " is " + (shouldBeSelected ? "selected" : "not selected"));
      element.before().accept(driver);
      boolean selected = tabService.isSelected(element.componentType(), element.locator());
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), selected);

      String assertionMessage = shouldBeSelected
            ? "Validating tab is selected"
            : "Validating tab is not selected";

      if (soft) {
         Allure.step(UI_TAB_PERFORMING_SOFT_VALIDATION_FOR_THE_TAB + element);
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
         Allure.step(UI_TAB_PERFORMING_STRICT_VALIDATION_FOR_THE_TAB + element);
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
    * Validates whether the specified tab is not selected.
    *
    * @param element The {@link TabUiElement} representing the tab.
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Validate that the tab is not selected (hard assertion).",
         tags = {"ui", "tab"}
   )
   @PandoraOptions(
         exampleFilesPath = "ai/roa/ui-usage.json"
   )
   public T validateIsNotSelected(final TabUiElement element) {
      return validateIsSelected(element, false, false);
   }

   /**
    * Validates whether the specified tab is not selected.
    *
    * @param element The {@link TabUiElement} representing the tab.
    * @param soft    A boolean indicating whether the validation should be performed softly.
    *                If {@code true}, failures will be collected rather than throwing an exception immediately.
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Validate that the tab is not selected, optionally using a soft assertion.",
         tags = {"ui", "tab"}
   )
   @PandoraOptions(
         exampleFilesPath = "ai/roa/ui-usage.json"
   )
   public T validateIsNotSelected(final TabUiElement element,
                                  @Pandora(
                                        description = "When true, use soft assertions (don't fail immediately)."
                                  ) boolean soft) {
      return validateIsSelected(element, false, soft);
   }
}
