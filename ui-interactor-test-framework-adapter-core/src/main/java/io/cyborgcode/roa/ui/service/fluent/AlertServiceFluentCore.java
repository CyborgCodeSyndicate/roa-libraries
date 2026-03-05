package io.cyborgcode.roa.ui.service.fluent;

import io.cyborgcode.roa.framework.storage.Storage;
import io.cyborgcode.roa.ui.components.alert.AlertServiceCore;
import io.cyborgcode.roa.ui.components.base.BaseUiElement;
import io.cyborgcode.roa.ui.elements.AlertUiElementCore;
import io.qameta.allure.Allure;
import org.assertj.core.api.Assertions;

import static io.cyborgcode.roa.ui.storage.StorageKeysUi.UI;

/**
 * Provides a fluent API for interacting with alert components.
 *
 * @param <T> Represents the fluent UI service that extends {@link UiServiceFluentCore}.
 * @author Cyborg Code Syndicate 💍👨💻
 */
@SuppressWarnings({"java:S5960", "unchecked"})
public class AlertServiceFluentCore<T extends UiServiceFluentCore<L, D, E, ?>, L, D, E extends BaseUiElement> {

   private final AlertServiceCore<E, L> alertService;
   private final T uiServiceFluent;
   private final Storage storage;
   private final D driver;

   public AlertServiceFluentCore(T uiServiceFluent, Storage storage, AlertServiceCore<E, L> alertService,
                                 D webDriver) {
      this.alertService = alertService;
      this.uiServiceFluent = uiServiceFluent;
      this.storage = storage;
      this.driver = webDriver;
   }

   public T getValue(final AlertUiElementCore<L, D> element) {
      Allure.step(String.format("[UI - Alert] Retrieving value for alert with componentType: %s, locator: %s",
            element.componentType(), element.locator()));
      element.before().accept(driver);
      String value = alertService.getValue(element.componentType(), element.locator());
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), value);
      return uiServiceFluent;
   }

   public T validateValue(final AlertUiElementCore<L, D> element, String expectedValue) {
      return validateValue(element, expectedValue, false);
   }

   public T validateValue(final AlertUiElementCore<L, D> element, String expectedValue, boolean soft) {
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

   public T isVisible(final AlertUiElementCore<L, D> element) {
      Allure.step(String.format("[UI - Alert] Checking visibility for alert with componentType: %s, locator: %s",
            element.componentType(), element.locator()));
      element.before().accept(driver);
      boolean enabled = alertService.isVisible(element.componentType(), element.locator());
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), enabled);
      return uiServiceFluent;
   }

   public T validateIsVisible(final AlertUiElementCore<L, D> element) {
      return validateIsVisible(element, true, false);
   }

   public T validateIsVisible(final AlertUiElementCore<L, D> element, boolean soft) {
      return validateIsVisible(element, true, soft);
   }

   private T validateIsVisible(final AlertUiElementCore<L, D> element, boolean shouldBeVisible, boolean soft) {
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

   public T validateIsHidden(final AlertUiElementCore<L, D> element) {
      return validateIsVisible(element, false, false);
   }

   public T validateIsHidden(final AlertUiElementCore<L, D> element, boolean soft) {
      return validateIsVisible(element, false, soft);
   }
}
