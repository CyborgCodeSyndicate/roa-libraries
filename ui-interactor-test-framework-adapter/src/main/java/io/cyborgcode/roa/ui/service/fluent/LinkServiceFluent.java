package io.cyborgcode.roa.ui.service.fluent;

import io.cyborgcode.pandora.annotation.Pandora;
import io.cyborgcode.pandora.annotation.PandoraOptions;
import io.cyborgcode.pandora.model.CreationKind;
import io.cyborgcode.roa.framework.storage.Storage;
import io.cyborgcode.roa.ui.components.link.LinkService;
import io.cyborgcode.roa.ui.selenium.LinkUiElement;
import io.cyborgcode.roa.ui.selenium.smart.SmartWebDriver;
import io.qameta.allure.Allure;
import org.assertj.core.api.Assertions;

import static io.cyborgcode.roa.ui.storage.StorageKeysUi.UI;

/**
 * Provides a fluent interface for interacting with link UI elements.
 *
 * <p>This class enables various link-related operations such as clicking,
 * double-clicking, and validation of visibility and enablement.
 *
 * <p>The generic type {@code T} represents the UI service fluent implementation that extends {@link UiServiceFluent},
 * allowing method chaining for seamless interaction.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@SuppressWarnings({"java:S5960", "unchecked"})
@Pandora(
      description = "Fluent UI service for interacting with links: click/doubleClick, and "
            + "enablement/visibility checks.",
      tags = {"ui", "fluent", "link"},
      creation = CreationKind.PROVIDED
)
@PandoraOptions(
      exampleFilesPath = "ai/roa/ui-usage.json",
      meta = {
         @PandoraOptions.Meta(key = "type", value = "fluent-service")
      }
)
public class LinkServiceFluent<T extends UiServiceFluent<?>> {

   private final LinkService linkService;
   private final T uiServiceFluent;
   private final Storage storage;
   private final SmartWebDriver driver;

   /**
    * Constructs a {@code LinkServiceFluent} instance.
    *
    * @param uiServiceFluent The UI service fluent instance to allow method chaining.
    * @param storage         The storage instance for tracking UI state.
    * @param linkService     The service handling link-related operations.
    * @param webDriver       The SmartWebDriver instance.
    */
   public LinkServiceFluent(T uiServiceFluent, Storage storage, LinkService linkService,
                            SmartWebDriver webDriver) {
      this.linkService = linkService;
      this.uiServiceFluent = uiServiceFluent;
      this.storage = storage;
      driver = webDriver;
   }

   /**
    * Clicks on the specified link element.
    *
    * @param element The {@link LinkUiElement} to be clicked.
    * @return The current {@link UiServiceFluent} instance for method chaining.
    */
   @Pandora(
         description = "Click the given link UI element and continue the fluent UI flow.",
         tags = {"ui", "link"}
   )
   @PandoraOptions(
         exampleFilesPath = "ai/roa/ui-usage.json"
   )
   public T click(final LinkUiElement element) {
      Allure.step("[UI - Link] Clicking on the link element: " + element);
      element.before().accept(driver);
      linkService.click(element.componentType(), element.locator());
      element.after().accept(driver);
      return uiServiceFluent;
   }

   /**
    * Performs a double-click action on the specified link element.
    *
    * @param element The {@link LinkUiElement} to be double-clicked.
    * @return The current {@link UiServiceFluent} instance for method chaining.
    */
   @Pandora(
         description = "Double-click the given link UI element and continue the fluent UI flow.",
         tags = {"ui", "link"}
   )
   @PandoraOptions(
         exampleFilesPath = "ai/roa/ui-usage.json"
   )
   public T doubleClick(final LinkUiElement element) {
      Allure.step("[UI - Link] Double-clicking on the link element: " + element);
      element.before().accept(driver);
      linkService.doubleClick(element.componentType(), element.locator());
      element.after().accept(driver);
      return uiServiceFluent;
   }

   /**
    * Checks if the specified link element is enabled.
    *
    * @param element The {@link LinkUiElement} to check.
    * @return The current {@link UiServiceFluent} instance for method chaining.
    */
   @Pandora(
         description = "Evaluate whether the link is enabled and store the result in quest storage.",
         tags = {"ui", "link"}
   )
   @PandoraOptions(
         exampleFilesPath = "ai/roa/ui-usage.json"
   )
   public T isEnabled(final LinkUiElement element) {
      Allure.step("[UI - Link] Checking if the link element is enabled: " + element);
      element.before().accept(driver);
      boolean enabled = linkService.isEnabled(element.componentType(), element.locator());
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), enabled);
      return uiServiceFluent;
   }

   /**
    * Validates that the specified link element is enabled.
    *
    * @param element The {@link LinkUiElement} to validate.
    * @return The current {@link UiServiceFluent} instance for method chaining.
    */
   @Pandora(
         description = "Validate that the link is enabled (hard assertion).",
         tags = {"ui", "link"}
   )
   @PandoraOptions(
         exampleFilesPath = "ai/roa/ui-usage.json"
   )
   public T validateIsEnabled(final LinkUiElement element) {
      return validateIsEnabled(element, true, false);
   }

   /**
    * Validates whether the specified link element is enabled, with an option for soft assertions.
    *
    * @param element The {@link LinkUiElement} to validate.
    * @param soft    {@code true} to perform a soft assertion.
    * @return The current {@link UiServiceFluent} instance for method chaining.
    */
   @Pandora(
         description = "Validate that the link is enabled, optionally using a soft assertion.",
         tags = {"ui", "link"}
   )
   @PandoraOptions(
         exampleFilesPath = "ai/roa/ui-usage.json"
   )
   public T validateIsEnabled(final LinkUiElement element,
         @Pandora(
               description = "When true, use soft assertions (don't fail immediately)."
         ) boolean soft) {
      return validateIsEnabled(element, true, soft);
   }

   private T validateIsEnabled(final LinkUiElement element, boolean shouldBeEnabled, boolean soft) {
      Allure.step(
            "[UI - Link] Validating if the link element should be " + (shouldBeEnabled ? "enabled" : "disabled") + ": "
                  + element);
      element.before().accept(driver);
      boolean enabled = linkService.isEnabled(element.componentType(), element.locator());
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), enabled);

      String assertionMessage = shouldBeEnabled
            ? "Validating link is enabled"
            : "Validating link is disabled";

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
    * Validates that the specified link element is disabled.
    *
    * @param element The {@link LinkUiElement} to validate.
    * @return The current {@link UiServiceFluent} instance for method chaining.
    */
   @Pandora(
         description = "Validate that the link is disabled (hard assertion).",
         tags = {"ui", "link"}
   )
   @PandoraOptions(
         exampleFilesPath = "ai/roa/ui-usage.json"
   )
   public T validateIsDisabled(final LinkUiElement element) {
      return validateIsEnabled(element, false, false);
   }

   /**
    * Validates whether the specified link element is disabled, with an option for soft assertions.
    *
    * @param element The {@link LinkUiElement} to validate.
    * @param soft    {@code true} to perform a soft assertion.
    * @return The current {@link UiServiceFluent} instance for method chaining.
    */
   @Pandora(
         description = "Validate that the link is disabled, optionally using a soft assertion.",
         tags = {"ui", "link"}
   )
   @PandoraOptions(
         exampleFilesPath = "ai/roa/ui-usage.json"
   )
   public T validateIsDisabled(final LinkUiElement element,
         @Pandora(
               description = "When true, use soft assertions (don't fail immediately)."
         ) boolean soft) {
      return validateIsEnabled(element, false, soft);
   }

   /**
    * Checks if the specified link element is visible.
    *
    * @param element The {@link LinkUiElement} to check.
    * @return The current {@link UiServiceFluent} instance for method chaining.
    */
   @Pandora(
         description = "Evaluate whether the link is visible and store the result in quest storage.",
         tags = {"ui", "link"}
   )
   @PandoraOptions(
         exampleFilesPath = "ai/roa/ui-usage.json"
   )
   public T isVisible(final LinkUiElement element) {
      Allure.step("[UI - Link] Checking if the link element is visible: " + element);
      element.before().accept(driver);
      boolean visible = linkService.isVisible(element.componentType(), element.locator());
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), visible);
      return uiServiceFluent;
   }

   /**
    * Validates that the specified link element is visible.
    *
    * @param element The {@link LinkUiElement} to validate.
    * @return The current {@link UiServiceFluent} instance for method chaining.
    */
   @Pandora(
         description = "Validate that the link is visible (hard assertion).",
         tags = {"ui", "link"}
   )
   @PandoraOptions(
         exampleFilesPath = "ai/roa/ui-usage.json"
   )
   public T validateIsVisible(final LinkUiElement element) {
      return validateIsVisible(element, true, false);
   }

   /**
    * Validates whether the specified link element is visible, with an option for soft assertions.
    *
    * @param element The {@link LinkUiElement} to validate.
    * @param soft    {@code true} to perform a soft assertion.
    * @return The current {@link UiServiceFluent} instance for method chaining.
    */
   @Pandora(
         description = "Validate that the link is visible, optionally using a soft assertion.",
         tags = {"ui", "link"}
   )
   @PandoraOptions(
         exampleFilesPath = "ai/roa/ui-usage.json"
   )
   public T validateIsVisible(final LinkUiElement element,
         @Pandora(
               description = "When true, use soft assertions (don't fail immediately)."
         ) boolean soft) {
      return validateIsVisible(element, true, soft);
   }

   private T validateIsVisible(final LinkUiElement element, boolean shouldBeVisible, boolean soft) {
      Allure.step(
            "[UI - Link] Validating if the link element should be " + (shouldBeVisible ? "visible" : "hidden") + ": "
                  + element);
      element.before().accept(driver);
      boolean visible = linkService.isVisible(element.componentType(), element.locator());
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), visible);

      String assertionMessage = shouldBeVisible
            ? "Validating link is visible"
            : "Validating link is hidden";

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
    * Validates that the specified link element is hidden.
    *
    * @param element The {@link LinkUiElement} to validate.
    * @return The current {@link UiServiceFluent} instance for method chaining.
    */
   @Pandora(
         description = "Validate that the link is hidden (hard assertion).",
         tags = {"ui", "link"}
   )
   @PandoraOptions(
         exampleFilesPath = "ai/roa/ui-usage.json"
   )
   public T validateIsHidden(final LinkUiElement element) {
      return validateIsVisible(element, false, false);
   }

   /**
    * Validates whether the specified link element is hidden, with an option for soft assertions.
    *
    * @param element The {@link LinkUiElement} to validate.
    * @param soft    {@code true} to perform a soft assertion.
    * @return The current {@link UiServiceFluent} instance for method chaining.
    */
   @Pandora(
         description = "Validate that the link is hidden, optionally using a soft assertion.",
         tags = {"ui", "link"}
   )
   @PandoraOptions(
         exampleFilesPath = "ai/roa/ui-usage.json"
   )
   public T validateIsHidden(final LinkUiElement element,
         @Pandora(
               description = "When true, use soft assertions (don't fail immediately)."
         ) boolean soft) {
      return validateIsVisible(element, false, soft);
   }

}
