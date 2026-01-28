package io.cyborgcode.roa.ui.service.fluent;

import io.cyborgcode.pandora.annotation.Pandora;
import io.cyborgcode.pandora.annotation.PandoraOptions;
import io.cyborgcode.pandora.model.CreationKind;
import io.cyborgcode.roa.framework.storage.Storage;
import io.cyborgcode.roa.ui.components.loader.LoaderService;
import io.cyborgcode.roa.ui.selenium.LoaderUiElement;
import io.cyborgcode.roa.ui.selenium.smart.SmartWebDriver;
import io.qameta.allure.Allure;
import org.assertj.core.api.Assertions;

import static io.cyborgcode.roa.ui.storage.StorageKeysUi.UI;

/**
 * A fluent service class for handling loader UI elements in test automation.
 *
 * <p>Provides methods to check visibility, validate state, and wait for loader elements to disappear or
 * become available.
 *
 * <p>The generic type {@code T} represents the UI service fluent implementation that extends {@link UiServiceFluent},
 * allowing method chaining for seamless interaction.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@SuppressWarnings({"java:S5960", "unchecked"})
@Pandora(
      description = "Fluent UI service for handling loaders: visibility checks, waits, and validations.",
      tags = {"ui", "fluent", "loader"},
      creation = CreationKind.PROVIDED
)
@PandoraOptions(
      exampleFilesPath = "ai/roa/ui-usage.json",
      meta = {
         @PandoraOptions.Meta(key = "type", value = "fluent-service")
      }
)
public class LoaderServiceFluent<T extends UiServiceFluent<?>> {

   private final LoaderService loaderService;
   private final T uiServiceFluent;
   private final Storage storage;
   private final SmartWebDriver driver;

   /**
    * Constructs a new {@code LoaderServiceFluent} instance.
    *
    * @param uiServiceFluent The parent fluent UI service instance.
    * @param storage         The storage instance for storing validation results.
    * @param loaderService   The loader service responsible for interacting with loaders.
    * @param webDriver       The smart web driver used for interactions.
    */
   public LoaderServiceFluent(T uiServiceFluent, Storage storage, LoaderService loaderService,
                              SmartWebDriver webDriver) {
      this.loaderService = loaderService;
      this.uiServiceFluent = uiServiceFluent;
      this.storage = storage;
      driver = webDriver;
   }

   /**
    * Checks if the specified loader UI element is currently visible.
    *
    * @param element The {@link LoaderUiElement} representing the loader component.
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Evaluate whether the loader is visible and store the result in quest storage.",
         tags = {"ui", "loader"}
   )
   @PandoraOptions(
         exampleFilesPath = "ai/roa/ui-usage.json"
   )
   public T isVisible(final LoaderUiElement element) {
      Allure.step("[UI - Loader] Check if the loader UI element is visible");

      element.before().accept(driver);
      boolean visible = loaderService.isVisible(element.componentType(), element.locator());
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), visible);
      return uiServiceFluent;
   }

   /**
    * Validates that the loader UI element is visible.
    *
    * @param element The {@link LoaderUiElement} to validate.
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Validate that the loader is visible (hard assertion).",
         tags = {"ui", "loader"}
   )
   @PandoraOptions(
         exampleFilesPath = "ai/roa/ui-usage.json"
   )
   public T validateIsVisible(final LoaderUiElement element) {
      Allure.step("[UI - Loader] Validate that the loader UI element is visible");
      return validateIsVisible(element, true, false);
   }

   /**
    * Validates that the loader UI element is visible with an optional soft assertion.
    *
    * @param element The {@link LoaderUiElement} to validate.
    * @param soft    A boolean indicating whether to perform soft validation.
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Validate that the loader is visible, optionally using a soft assertion.",
         tags = {"ui", "loader"}
   )
   @PandoraOptions(
         exampleFilesPath = "ai/roa/ui-usage.json"
   )
   public T validateIsVisible(final LoaderUiElement element,
         @Pandora(
               description = "When true, use soft assertions (don't fail immediately)."
         ) boolean soft) {
      Allure.step("[UI - Loader] Validate that the loader UI element is visible with soft validation option");
      return validateIsVisible(element, true, soft);
   }

   /**
    * Performs validation on whether the loader is visible or hidden.
    *
    * @param element         The {@link LoaderUiElement} to validate.
    * @param shouldBeVisible A boolean indicating the expected visibility state.
    * @param soft            A boolean indicating whether to perform soft validation.
    * @return The fluent UI service instance.
    */
   private T validateIsVisible(final LoaderUiElement element, boolean shouldBeVisible, boolean soft) {
      element.before().accept(driver);
      boolean visible = loaderService.isVisible(element.componentType(), element.locator());
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), visible);

      String assertionMessage = shouldBeVisible
            ? "Validating loader is visible"
            : "Validating loader is hidden";

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
    * Validates that the loader UI element is hidden.
    *
    * @param element The {@link LoaderUiElement} to validate.
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Validate that the loader is hidden (hard assertion).",
         tags = {"ui", "loader"}
   )
   @PandoraOptions(
         exampleFilesPath = "ai/roa/ui-usage.json"
   )
   public T validateIsHidden(final LoaderUiElement element) {
      Allure.step("[UI - Loader] Validate that the loader UI element is hidden");
      return validateIsVisible(element, false, false);
   }

   /**
    * Validates that the loader UI element is hidden with an optional soft assertion.
    *
    * @param element The {@link LoaderUiElement} to validate.
    * @param soft    A boolean indicating whether to perform soft validation.
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Validate that the loader is hidden, optionally using a soft assertion.",
         tags = {"ui", "loader"}
   )
   @PandoraOptions(
         exampleFilesPath = "ai/roa/ui-usage.json"
   )
   public T validateIsHidden(final LoaderUiElement element,
         @Pandora(
               description = "When true, use soft assertions (don't fail immediately)."
         ) boolean soft) {
      Allure.step("[UI - Loader] Validate that the loader UI element is hidden with soft validation option");
      return validateIsVisible(element, false, soft);
   }

   /**
    * Waits until the specified loader UI element is visible within the given timeout.
    *
    * @param element      The {@link LoaderUiElement} to wait for.
    * @param secondsShown The maximum time (in seconds) to wait for visibility.
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Wait for the loader to be shown up to the given number of seconds.",
         tags = {"ui", "loader"}
   )
   @PandoraOptions(
         exampleFilesPath = "ai/roa/ui-usage.json"
   )
   public T waitToBeShown(final LoaderUiElement element,
         @Pandora(
               description = "Maximum time in seconds to wait for the loader to become visible."
         ) int secondsShown) {
      Allure.step(
            "[UI - Loader] [UI - Loader] Wait for the loader UI element to be shown for " + secondsShown + " seconds");

      element.before().accept(driver);
      loaderService.waitToBeShown(element.componentType(), element.locator(), secondsShown);
      element.after().accept(driver);
      return uiServiceFluent;
   }

   /**
    * Waits until the specified loader UI element is removed from visibility within the given timeout.
    *
    * @param element        The {@link LoaderUiElement} to wait for.
    * @param secondsRemoved The maximum time (in seconds) to wait for removal.
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Wait for the loader to be removed up to the given number of seconds.",
         tags = {"ui", "loader"}
   )
   @PandoraOptions(
         exampleFilesPath = "ai/roa/ui-usage.json"
   )
   public T waitToBeRemoved(final LoaderUiElement element,
         @Pandora(
               description = "Maximum time in seconds to wait for the loader to be removed."
         ) int secondsRemoved) {
      Allure.step("[UI - Loader] Wait for the loader UI element to be removed for " + secondsRemoved + " seconds");

      element.before().accept(driver);
      loaderService.waitToBeRemoved(element.componentType(), element.locator(), secondsRemoved);
      element.after().accept(driver);
      return uiServiceFluent;
   }

   /**
    * Waits for the loader UI element to become visible and then disappear within the given time limits.
    *
    * @param element        The {@link LoaderUiElement} to wait for.
    * @param secondsShown   The maximum time (in seconds) to wait for visibility.
    * @param secondsRemoved The maximum time (in seconds) to wait for removal.
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Wait for the loader to be shown and then removed within the given time limits.",
         tags = {"ui", "loader"}
   )
   @PandoraOptions(
         exampleFilesPath = "ai/roa/ui-usage.json"
   )
   public T waitToBeShownAndRemoved(final LoaderUiElement element,
         @Pandora(
               description = "Maximum time in seconds to wait for the loader to become visible."
         ) int secondsShown,
         @Pandora(
               description = "Maximum time in seconds to wait for the loader to be removed."
         ) int secondsRemoved) {
      Allure.step("[UI - Loader] Wait for the loader UI element to be shown and then removed within "
            + secondsShown + " and " + secondsRemoved + " seconds respectively");

      element.before().accept(driver);
      loaderService.waitToBeShownAndRemoved(element.componentType(), element.locator(), secondsShown, secondsRemoved);
      element.after().accept(driver);
      return uiServiceFluent;
   }

}
