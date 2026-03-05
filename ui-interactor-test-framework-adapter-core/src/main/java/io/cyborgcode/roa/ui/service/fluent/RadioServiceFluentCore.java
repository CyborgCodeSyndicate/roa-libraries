package io.cyborgcode.roa.ui.service.fluent;

import io.cyborgcode.roa.framework.storage.Storage;
import io.cyborgcode.roa.ui.components.base.BaseUiElement;
import io.cyborgcode.roa.ui.components.radio.RadioServiceCore;
import io.cyborgcode.roa.ui.elements.RadioUiElementCore;
import io.qameta.allure.Allure;
import java.util.Arrays;
import java.util.List;
import org.assertj.core.api.Assertions;

import static io.cyborgcode.roa.ui.storage.StorageKeysUi.UI;

/**
 * Provides a fluent API for interacting with radio button components.
 *
 * @param <T> Represents the fluent UI service that extends {@link UiServiceFluentCore}.
 * @author Cyborg Code Syndicate 💍👨💻
 */
@SuppressWarnings({"java:S5960", "unchecked"})
public class RadioServiceFluentCore<T extends UiServiceFluentCore<L, D, E, ?>, L, D, E extends BaseUiElement> {

   private static final String UI_RADIO_VALIDATING_IF_RADIO_BUTTON_IS = "[UI - Radio] Validating if radio button is ";

   private final RadioServiceCore<E, L> radioService;
   private final T uiServiceFluent;
   private final Storage storage;
   private final D driver;

   public RadioServiceFluentCore(T uiServiceFluent, Storage storage, RadioServiceCore<E, L> radioService,
                                 D webDriver) {
      this.radioService = radioService;
      this.uiServiceFluent = uiServiceFluent;
      this.storage = storage;
      driver = webDriver;
   }

   public T select(final RadioUiElementCore<L, D> element) {
      Allure.step("[UI - Radio] Selecting radio button: " + element.enumImpl());
      element.before().accept(driver);
      radioService.select(element.componentType(), element.locator());
      element.after().accept(driver);
      return uiServiceFluent;
   }

   public T isEnabled(final RadioUiElementCore<L, D> element) {
      Allure.step("[UI - Radio] Checking if radio button is enabled: " + element.enumImpl());
      element.before().accept(driver);
      boolean enabled = radioService.isEnabled(element.componentType(), element.locator());
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), enabled);
      return uiServiceFluent;
   }

   public T validateIsEnabled(final RadioUiElementCore<L, D> element) {
      return validateIsEnabled(element, true, false);
   }

   public T validateIsEnabled(final RadioUiElementCore<L, D> element, boolean soft) {
      return validateIsEnabled(element, true, soft);
   }

   private T validateIsEnabled(final RadioUiElementCore<L, D> element, boolean shouldBeEnabled, boolean soft) {
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

   public T validateIsDisabled(final RadioUiElementCore<L, D> element) {
      return validateIsEnabled(element, false, false);
   }

   public T validateIsDisabled(final RadioUiElementCore<L, D> element, boolean soft) {
      return validateIsEnabled(element, false, soft);
   }

   public T isSelected(final RadioUiElementCore<L, D> element) {
      Allure.step("[UI - Radio] Checking if radio button is selected: " + element.enumImpl());
      element.before().accept(driver);
      boolean selected = radioService.isSelected(element.componentType(), element.locator());
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), selected);
      return uiServiceFluent;
   }

   public T validateIsSelected(final RadioUiElementCore<L, D> element) {
      return validateIsSelected(element, true, false);
   }

   public T validateIsSelected(final RadioUiElementCore<L, D> element, boolean soft) {
      return validateIsSelected(element, true, soft);
   }

   private T validateIsSelected(final RadioUiElementCore<L, D> element, boolean shouldBeSelected, boolean soft) {
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

   public T validateIsNotSelected(final RadioUiElementCore<L, D> element) {
      return validateIsSelected(element, false, false);
   }

   public T validateIsNotSelected(final RadioUiElementCore<L, D> element, boolean soft) {
      return validateIsSelected(element, false, soft);
   }

   public T isVisible(final RadioUiElementCore<L, D> element) {
      Allure.step("[UI - Radio] Checking if radio button is visible: " + element.enumImpl());
      element.before().accept(driver);
      boolean visible = radioService.isVisible(element.componentType(), element.locator());
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), visible);
      return uiServiceFluent;
   }

   public T validateIsVisible(final RadioUiElementCore<L, D> element) {
      return validateIsVisible(element, true, false);
   }

   public T validateIsVisible(final RadioUiElementCore<L, D> element, boolean soft) {
      return validateIsVisible(element, true, soft);
   }

   private T validateIsVisible(final RadioUiElementCore<L, D> element, boolean shouldBeVisible, boolean soft) {
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

   public T validateIsHidden(final RadioUiElementCore<L, D> element) {
      return validateIsVisible(element, false, false);
   }

   public T validateIsHidden(final RadioUiElementCore<L, D> element, boolean soft) {
      return validateIsVisible(element, false, soft);
   }

   public T getSelected(final RadioUiElementCore<L, D> element) {
      Allure.step("[UI - Radio] Retrieving selected value for radio button: " + element.enumImpl());
      element.before().accept(driver);
      String selectedValue = radioService.getSelected(element.componentType(), element.locator());
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), selectedValue);
      return uiServiceFluent;
   }

   public T validateSelected(final RadioUiElementCore<L, D> element, String expected) {
      return validateSelected(element, expected, false);
   }

   public T validateSelected(final RadioUiElementCore<L, D> element, String expected, boolean soft) {
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

   public T getAll(final RadioUiElementCore<L, D> element) {
      Allure.step("[UI - Radio] Retrieving all radio button options for: " + element.enumImpl());
      element.before().accept(driver);
      List<String> allItems = radioService.getAll(element.componentType(), element.locator());
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), allItems);
      return uiServiceFluent;
   }

   public T validateAllRadioInputs(final RadioUiElementCore<L, D> element, final String... expectedValues) {
      return validateAllRadioInputs(element, false, expectedValues);
   }

   public T validateAllRadioInputs(final RadioUiElementCore<L, D> element, boolean soft,
                                   final String... expectedValues) {
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
}
