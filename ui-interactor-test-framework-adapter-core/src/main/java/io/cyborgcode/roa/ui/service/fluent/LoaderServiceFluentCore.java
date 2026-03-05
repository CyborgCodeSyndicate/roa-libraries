package io.cyborgcode.roa.ui.service.fluent;

import io.cyborgcode.roa.framework.storage.Storage;
import io.cyborgcode.roa.ui.components.base.BaseUiElement;
import io.cyborgcode.roa.ui.components.loader.LoaderServiceCore;
import io.cyborgcode.roa.ui.elements.LoaderUiElementCore;
import io.qameta.allure.Allure;
import org.assertj.core.api.Assertions;

import static io.cyborgcode.roa.ui.storage.StorageKeysUi.UI;

/**
 * Provides a fluent API for interacting with loader components.
 *
 * @param <T> Represents the fluent UI service that extends {@link UiServiceFluentCore}.
 * @author Cyborg Code Syndicate 💍👨💻
 */
@SuppressWarnings({"java:S5960", "unchecked"})
public class LoaderServiceFluentCore<T extends UiServiceFluentCore<L, D, E, ?>, L, D, E extends BaseUiElement> {

   private final LoaderServiceCore<E, L> loaderService;
   private final T uiServiceFluent;
   private final Storage storage;
   private final D driver;

   public LoaderServiceFluentCore(T uiServiceFluent, Storage storage, LoaderServiceCore<E, L> loaderService,
                                  D webDriver) {
      this.loaderService = loaderService;
      this.uiServiceFluent = uiServiceFluent;
      this.storage = storage;
      driver = webDriver;
   }

   public T isVisible(final LoaderUiElementCore<L, D> element) {
      Allure.step("[UI - Loader] Check if the loader UI element is visible");
      element.before().accept(driver);
      boolean visible = loaderService.isVisible(element.componentType(), element.locator());
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), visible);
      return uiServiceFluent;
   }

   public T validateIsVisible(final LoaderUiElementCore<L, D> element) {
      Allure.step("[UI - Loader] Validate that the loader UI element is visible");
      return validateIsVisible(element, true, false);
   }

   public T validateIsVisible(final LoaderUiElementCore<L, D> element, boolean soft) {
      Allure.step("[UI - Loader] Validate that the loader UI element is visible with soft validation option");
      return validateIsVisible(element, true, soft);
   }

   private T validateIsVisible(final LoaderUiElementCore<L, D> element, boolean shouldBeVisible, boolean soft) {
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

   public T validateIsHidden(final LoaderUiElementCore<L, D> element) {
      Allure.step("[UI - Loader] Validate that the loader UI element is hidden");
      return validateIsVisible(element, false, false);
   }

   public T validateIsHidden(final LoaderUiElementCore<L, D> element, boolean soft) {
      Allure.step("[UI - Loader] Validate that the loader UI element is hidden with soft validation option");
      return validateIsVisible(element, false, soft);
   }

   public T waitToBeShown(final LoaderUiElementCore<L, D> element, int secondsShown) {
      Allure.step("[UI - Loader] Wait for the loader UI element to be shown for " + secondsShown + " seconds");
      element.before().accept(driver);
      loaderService.waitToBeShown(element.componentType(), element.locator(), secondsShown);
      element.after().accept(driver);
      return uiServiceFluent;
   }

   public T waitToBeRemoved(final LoaderUiElementCore<L, D> element, int secondsRemoved) {
      Allure.step("[UI - Loader] Wait for the loader UI element to be removed for " + secondsRemoved + " seconds");
      element.before().accept(driver);
      loaderService.waitToBeRemoved(element.componentType(), element.locator(), secondsRemoved);
      element.after().accept(driver);
      return uiServiceFluent;
   }

   public T waitToBeShownAndRemoved(final LoaderUiElementCore<L, D> element, int secondsShown, int secondsRemoved) {
      Allure.step("[UI - Loader] Wait for the loader UI element to be shown and then removed within "
            + secondsShown + " and " + secondsRemoved + " seconds respectively");
      element.before().accept(driver);
      loaderService.waitToBeShownAndRemoved(element.componentType(), element.locator(), secondsShown, secondsRemoved);
      element.after().accept(driver);
      return uiServiceFluent;
   }
}
