package io.cyborgcode.roa.ui.service.fluent;

import io.cyborgcode.roa.framework.storage.Storage;
import io.cyborgcode.roa.ui.components.base.BaseUiElement;
import io.cyborgcode.roa.ui.components.input.InputServiceCore;
import io.cyborgcode.roa.ui.elements.InputUiElementCore;
import io.qameta.allure.Allure;
import org.assertj.core.api.Assertions;

import static io.cyborgcode.roa.ui.storage.StorageKeysUi.UI;

/**
 * Provides a fluent API for interacting with input components.
 *
 * @param <T> Represents the fluent UI service that extends {@link UiServiceFluentCore}.
 * @author Cyborg Code Syndicate 💍👨💻
 */
@SuppressWarnings({"java:S5960", "unchecked"})
public class InputServiceFluentCore<T extends UiServiceFluentCore<L, D, E, ?>, L, D, E extends BaseUiElement> {

   private final InputServiceCore<E, L> inputService;
   private final T uiServiceFluent;
   private final Storage storage;
   private final D driver;

   public InputServiceFluentCore(T uiServiceFluent, Storage storage, InputServiceCore<E, L> inputService,
                                 D webDriver) {
      this.inputService = inputService;
      this.uiServiceFluent = uiServiceFluent;
      this.storage = storage;
      driver = webDriver;
   }

   public T insert(final InputUiElementCore<L, D> element, final String value) {
      Allure.step("[UI - Input] Insert value '" + value + "' into input element: " + element);
      element.before().accept(driver);
      inputService.insert(element.componentType(), element.locator(), value);
      element.after().accept(driver);
      return uiServiceFluent;
   }

   public T clear(final InputUiElementCore<L, D> element) {
      Allure.step("[UI - Input] Clear input element: " + element);
      element.before().accept(driver);
      inputService.clear(element.componentType(), element.locator());
      element.after().accept(driver);
      return uiServiceFluent;
   }

   public T getValue(final InputUiElementCore<L, D> element) {
      Allure.step("[UI - Input] Get value from input element: " + element);
      element.before().accept(driver);
      String value = inputService.getValue(element.componentType(), element.locator());
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), value);
      return uiServiceFluent;
   }

   public T validateValue(final InputUiElementCore<L, D> element, String expectedValue) {
      return validateValue(element, expectedValue, false);
   }

   public T validateValue(final InputUiElementCore<L, D> element, String expectedValue, boolean soft) {
      Allure.step("[UI - Input] Validate value of input element: " + element + " is equal to: " + expectedValue);
      element.before().accept(driver);
      String value = inputService.getValue(element.componentType(), element.locator());
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), value);
      if (soft) {
         return (T) uiServiceFluent.validate(
               softAssertions -> softAssertions.assertThat(value).as("Validating Input value")
                     .isEqualTo(expectedValue));
      } else {
         return (T) uiServiceFluent.validate(
               () -> Assertions.assertThat(value).as("Validating Input value")
                     .isEqualTo(expectedValue));
      }
   }

   public T isEnabled(final InputUiElementCore<L, D> element) {
      Allure.step("[UI - Input] Check if input element is enabled: " + element);
      element.before().accept(driver);
      boolean enabled = inputService.isEnabled(element.componentType(), element.locator());
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), enabled);
      return uiServiceFluent;
   }

   public T validateIsEnabled(final InputUiElementCore<L, D> element) {
      return validateIsEnabled(element, true, false);
   }

   public T validateIsEnabled(final InputUiElementCore<L, D> element, boolean soft) {
      return validateIsEnabled(element, true, soft);
   }

   private T validateIsEnabled(final InputUiElementCore<L, D> element, boolean shouldBeEnabled, boolean soft) {
      Allure.step(
            "[UI - Input] Validate if input element " + element + " is " + (shouldBeEnabled ? "enabled" : "disabled"));
      element.before().accept(driver);
      boolean enabled = inputService.isEnabled(element.componentType(), element.locator());
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), enabled);

      String assertionMessage = shouldBeEnabled
            ? "Validating input is enabled"
            : "Validating input is disabled";

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

   public T validateIsDisabled(final InputUiElementCore<L, D> element) {
      return validateIsEnabled(element, false, false);
   }

   public T validateIsDisabled(final InputUiElementCore<L, D> element, boolean soft) {
      return validateIsEnabled(element, false, soft);
   }

   public T getErrorMessage(final InputUiElementCore<L, D> element) {
      Allure.step("[UI - Input] Get error message for input element: " + element);
      element.before().accept(driver);
      String errorMessage = inputService.getErrorMessage(element.componentType(), element.locator());
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), errorMessage);
      return uiServiceFluent;
   }

   public T validateErrorMessage(final InputUiElementCore<L, D> element, String expectedMessage) {
      return validateErrorMessage(element, expectedMessage, false);
   }

   public T validateErrorMessage(final InputUiElementCore<L, D> element, String expectedMessage, boolean soft) {
      Allure.step(
            "[UI - Input] Validate error message for input element: " + element + " is equal to: " + expectedMessage);
      element.before().accept(driver);
      String errorMessage = inputService.getErrorMessage(element.componentType(), element.locator());
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), errorMessage);
      if (soft) {
         return (T) uiServiceFluent.validate(
               softAssertions -> softAssertions.assertThat(errorMessage)
                     .as("Validating UI Message")
                     .isEqualTo(expectedMessage));
      } else {
         return (T) uiServiceFluent.validate(
               () -> Assertions.assertThat(errorMessage).as("Validating UI Message")
                     .isEqualTo(expectedMessage));
      }
   }
}
