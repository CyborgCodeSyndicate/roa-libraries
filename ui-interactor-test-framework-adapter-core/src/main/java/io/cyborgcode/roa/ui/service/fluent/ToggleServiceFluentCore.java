package io.cyborgcode.roa.ui.service.fluent;

import io.cyborgcode.roa.framework.storage.Storage;
import io.cyborgcode.roa.ui.components.base.BaseUiElement;
import io.cyborgcode.roa.ui.components.toggle.ToggleServiceCore;
import io.cyborgcode.roa.ui.elements.ToggleUiElementCore;
import io.qameta.allure.Allure;
import org.assertj.core.api.Assertions;

import static io.cyborgcode.roa.ui.storage.StorageKeysUi.UI;

/**
 * Provides a fluent API for interacting with toggle components.
 *
 * @param <T> Represents the fluent UI service that extends {@link UiServiceFluentCore}.
 * @author Cyborg Code Syndicate 💍👨💻
 */
@SuppressWarnings({"java:S5960", "unchecked"})
public class ToggleServiceFluentCore<T extends UiServiceFluentCore<L, D, E, ?>, L, D, E extends BaseUiElement> {

   private final ToggleServiceCore<E, L> toggleService;
   private final T uiServiceFluent;
   private final Storage storage;
   private final D driver;

   public ToggleServiceFluentCore(T uiServiceFluent, Storage storage, ToggleServiceCore<E, L> toggleService,
                                  D webDriver) {
      this.uiServiceFluent = uiServiceFluent;
      this.toggleService = toggleService;
      this.storage = storage;
      driver = webDriver;
   }

   public T activate(final ToggleUiElementCore<L, D> element) {
      Allure.step("[UI - Toggle] Activating the toggle element: " + element);
      element.before().accept(driver);
      toggleService.activate(element.componentType(), element.locator());
      element.after().accept(driver);
      return uiServiceFluent;
   }

   public T deactivate(final ToggleUiElementCore<L, D> element) {
      Allure.step("[UI - Toggle] Deactivating the toggle element: " + element);
      element.before().accept(driver);
      toggleService.deactivate(element.componentType(), element.locator());
      element.after().accept(driver);
      return uiServiceFluent;
   }

   public T isEnabled(final ToggleUiElementCore<L, D> element) {
      Allure.step("[UI - Toggle] Checking if the toggle element is enabled: " + element);
      element.before().accept(driver);
      boolean enabled = toggleService.isEnabled(element.componentType(), element.locator());
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), enabled);
      return uiServiceFluent;
   }

   public T validateIsEnabled(final ToggleUiElementCore<L, D> element) {
      return validateIsEnabled(element, true, false);
   }

   public T validateIsEnabled(final ToggleUiElementCore<L, D> element, boolean soft) {
      return validateIsEnabled(element, true, soft);
   }

   private T validateIsEnabled(final ToggleUiElementCore<L, D> element, boolean shouldBeEnabled, boolean soft) {
      Allure.step("Validating if the toggle " + element + " is " + (shouldBeEnabled ? "enabled" : "disabled"));
      element.before().accept(driver);
      boolean enabled = toggleService.isEnabled(element.componentType(), element.locator());
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), enabled);

      String assertionMessage = shouldBeEnabled
            ? "Validating toggle is enabled"
            : "Validating toggle is disabled";

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

   public T validateIsDisabled(final ToggleUiElementCore<L, D> element) {
      return validateIsEnabled(element, false, false);
   }

   public T validateIsDisabled(final ToggleUiElementCore<L, D> element, boolean soft) {
      return validateIsEnabled(element, false, soft);
   }

   public T isActivated(final ToggleUiElementCore<L, D> element) {
      Allure.step("[UI - Toggle] Checking if the toggle element is activated: " + element);
      boolean enabled = toggleService.isActivated(element.componentType(), element.locator());
      storage.sub(UI).put(element.enumImpl(), enabled);
      return uiServiceFluent;
   }

   public T validateIsActivated(final ToggleUiElementCore<L, D> element) {
      return validateIsActivated(element, true, false);
   }

   public T validateIsActivated(final ToggleUiElementCore<L, D> element, boolean soft) {
      return validateIsActivated(element, true, soft);
   }

   private T validateIsActivated(final ToggleUiElementCore<L, D> element, boolean shouldBeEnabled, boolean soft) {
      Allure.step("Validating if the toggle " + element + " is " + (shouldBeEnabled ? "activated" : "deactivated"));
      element.before().accept(driver);
      boolean activated = toggleService.isActivated(element.componentType(), element.locator());
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), activated);

      String assertionMessage = shouldBeEnabled
            ? "Validating toggle is activated"
            : "Validating toggle is deactivated";

      if (soft) {
         return (T) uiServiceFluent.validate(
               softAssertions -> {
                  if (shouldBeEnabled) {
                     softAssertions.assertThat(activated).as(assertionMessage).isTrue();
                  } else {
                     softAssertions.assertThat(activated).as(assertionMessage).isFalse();
                  }
               }
         );
      } else {
         return (T) uiServiceFluent.validate(
               () -> {
                  if (shouldBeEnabled) {
                     Assertions.assertThat(activated).as(assertionMessage).isTrue();
                  } else {
                     Assertions.assertThat(activated).as(assertionMessage).isFalse();
                  }
               }
         );
      }
   }

   public T validateIsDeactivated(final ToggleUiElementCore<L, D> element) {
      return validateIsActivated(element, false, false);
   }

   public T validateIsDeactivated(final ToggleUiElementCore<L, D> element, boolean soft) {
      return validateIsActivated(element, false, soft);
   }
}
