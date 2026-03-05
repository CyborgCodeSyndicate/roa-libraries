package io.cyborgcode.roa.ui.service.fluent;

import io.cyborgcode.roa.framework.storage.Storage;
import io.cyborgcode.roa.ui.components.base.BaseUiElement;
import io.cyborgcode.roa.ui.components.button.ButtonServiceCore;
import io.cyborgcode.roa.ui.elements.ButtonUiElementCore;
import io.qameta.allure.Allure;
import org.assertj.core.api.Assertions;

import static io.cyborgcode.roa.ui.storage.StorageKeysUi.UI;

/**
 * Provides a fluent API for interacting with button components.
 *
 * <p>This class enables structured interactions with UI buttons, including actions such as clicking,
 * validating visibility, and checking whether a button is enabled. It integrates with {@link ButtonServiceFluentCore}
 * to perform UI operations efficiently.
 *
 * <p>The fluent API design allows method chaining to improve test readability and maintainability.
 *
 * @param <T> Represents the fluent UI service that extends {@link UiServiceFluentCore}.
 *            This type parameter ensures that method chaining correctly returns the calling instance type.
 * @author Cyborg Code Syndicate 💍👨💻
 */
@SuppressWarnings({"java:S5960", "unchecked"})
public class ButtonServiceFluentCore<T extends UiServiceFluentCore<L, D, E, ?>, L, D, E extends BaseUiElement> {

   private final ButtonServiceCore<E, L> buttonService;
   private final T uiServiceFluent;
   private final Storage storage;
   private final D driver;

   /**
    * Constructs an instance of {@code ButtonServiceFluent}.
    *
    * @param uiServiceFluent The UI service fluent instance
    * @param storage         The storage instance for persisting test values
    * @param buttonService   The button service instance
    * @param webDriver       The WebDriver instance
    */
   public ButtonServiceFluentCore(T uiServiceFluent, Storage storage, ButtonServiceCore<E, L> buttonService,
                                  D webDriver) {
      this.buttonService = buttonService;
      this.uiServiceFluent = uiServiceFluent;
      this.storage = storage;
      driver = webDriver;
   }

   /**
    * Clicks the specified button element.
    *
    * @param element The button element to click
    * @return The fluent UI service instance
    */
   public T click(final ButtonUiElementCore<L, D> element) {
      Allure.step(String.format("[UI - Button] Clicking button with componentType: %s, locator: %s",
            element.componentType(), element.locator()));
      element.before().accept(driver);
      buttonService.click(element.componentType(), element.locator());
      element.after().accept(driver);
      return uiServiceFluent;
   }

   /**
    * Checks if the button is enabled.
    *
    * @param element The button element
    * @return The fluent UI service instance
    */
   public T isEnabled(final ButtonUiElementCore<L, D> element) {
      Allure.step(String.format("[UI - Button] Checking if button is enabled with componentType: %s, locator: %s",
            element.componentType(), element.locator()));
      element.before().accept(driver);
      boolean enabled = buttonService.isEnabled(element.componentType(), element.locator());
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), enabled);
      return uiServiceFluent;
   }

   /**
    * Validates if the button is enabled.
    *
    * @param element The button element
    * @return The fluent UI service instance
    */
   public T validateIsEnabled(final ButtonUiElementCore<L, D> element) {
      return validateIsEnabled(element, true, false);
   }

   /**
    * Validates if the button is enabled with soft assertion.
    *
    * @param element The button element
    * @param soft    Whether to perform a soft assertion
    * @return The fluent UI service instance
    */
   public T validateIsEnabled(final ButtonUiElementCore<L, D> element, boolean soft) {
      return validateIsEnabled(element, true, soft);
   }

   private T validateIsEnabled(final ButtonUiElementCore<L, D> element, boolean shouldBeEnabled, boolean soft) {
      Allure.step(String.format("[UI - Button] Validating if button is enabled/disabled with componentType: %s, "
            + "locator: %s", element.componentType(), element.locator()));
      element.before().accept(driver);
      boolean enabled = buttonService.isEnabled(element.componentType(), element.locator());
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), enabled);

      String assertionMessage = shouldBeEnabled
            ? "Validating button is enabled"
            : "Validating button is disabled";

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
    * Validates if the button is disabled.
    *
    * @param element The button element
    * @return The fluent UI service instance
    */
   public T validateIsDisabled(final ButtonUiElementCore<L, D> element) {
      return validateIsEnabled(element, false, false);
   }

   /**
    * Validates if the button is disabled with soft assertion.
    *
    * @param element The button element
    * @param soft    Whether to perform a soft assertion
    * @return The fluent UI service instance
    */
   public T validateIsDisabled(final ButtonUiElementCore<L, D> element, boolean soft) {
      return validateIsEnabled(element, false, soft);
   }

   /**
    * Checks if the button is visible.
    *
    * @param element The button element
    * @return The fluent UI service instance
    */
   public T isVisible(final ButtonUiElementCore<L, D> element) {
      Allure.step(String.format("[UI - Button] Checking if button is visible with componentType: %s, locator: %s",
            element.componentType(), element.locator()));
      element.before().accept(driver);
      boolean visible = buttonService.isVisible(element.componentType(), element.locator());
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), visible);
      return uiServiceFluent;
   }

   /**
    * Validates if the button is visible.
    *
    * @param element The button element
    * @return The fluent UI service instance
    */
   public T validateIsVisible(final ButtonUiElementCore<L, D> element) {
      return validateIsVisible(element, true, false);
   }

   /**
    * Validates whether the specified button UI element is visible.
    *
    * @param element The {@link ButtonUiElementCore<L, D>} to be validated.
    * @param soft    If {@code true}, the validation will be performed as a soft assertion.
    * @return The instance of {@link UiServiceFluentCore} to allow method chaining.
    */
   public T validateIsVisible(final ButtonUiElementCore<L, D> element, boolean soft) {
      Allure.step(String.format("[UI - Button] Validating if button is visible with componentType: %s, locator: %s",
            element.componentType(), element.locator()));
      return validateIsVisible(element, true, soft);
   }

   private T validateIsVisible(final ButtonUiElementCore<L, D> element, boolean shouldBeVisible, boolean soft) {
      Allure.step(String.format("[UI - Button] Validating if button is visible/hidden with componentType: %s, "
            + "locator: %s", element.componentType(), element.locator()));
      element.before().accept(driver);
      boolean visible = buttonService.isVisible(element.componentType(), element.locator());
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), visible);

      String assertionMessage = shouldBeVisible
            ? "Validating button is visible"
            : "Validating button is hidden";

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
    * Validates if the button is hidden.
    *
    * @param element The button element
    * @return The fluent UI service instance
    */
   public T validateIsHidden(final ButtonUiElementCore<L, D> element) {
      return validateIsVisible(element, false, false);
   }

   /**
    * Validates whether the specified button UI element is hidden.
    *
    * @param element The {@link ButtonUiElementCore<L, D>} to be validated.
    * @param soft    If {@code true}, the validation will be performed as a soft assertion.
    * @return The instance of {@link UiServiceFluentCore} to allow method chaining.
    */
   public T validateIsHidden(final ButtonUiElementCore<L, D> element, boolean soft) {
      return validateIsVisible(element, false, soft);
   }
}
