package io.cyborgcode.roa.ui.service.fluent;

import io.cyborgcode.roa.framework.storage.Storage;
import io.cyborgcode.roa.ui.components.base.BaseUiElement;
import io.cyborgcode.roa.ui.components.select.SelectServiceCore;
import io.cyborgcode.roa.ui.elements.SelectUiElementCore;
import io.cyborgcode.roa.ui.util.strategy.Strategy;
import io.qameta.allure.Allure;
import java.util.Arrays;
import java.util.List;
import org.assertj.core.api.Assertions;

import static io.cyborgcode.roa.ui.storage.StorageKeysUi.UI;

/**
 * Provides a fluent API for interacting with select/dropdown components.
 *
 * @param <T> Represents the fluent UI service that extends {@link UiServiceFluentCore}.
 * @author Cyborg Code Syndicate 💍👨💻
 */
@SuppressWarnings({"java:S5960", "squid:S1192", "unchecked"})
public class SelectServiceFluentCore<T extends UiServiceFluentCore<L, D, E, ?>, L, D, E extends BaseUiElement> {

   private static final String VALIDATING_AVAILABLE_OPTIONS = "Validating Available Options";

   private final SelectServiceCore<E, L> selectService;
   private final T uiServiceFluent;
   private final Storage storage;
   private final D driver;

   public SelectServiceFluentCore(T uiServiceFluent, Storage storage, SelectServiceCore<E, L> selectService,
                                  D webDriver) {
      this.selectService = selectService;
      this.uiServiceFluent = uiServiceFluent;
      this.storage = storage;
      driver = webDriver;
   }

   public T selectOptions(final SelectUiElementCore<L, D> element, final String... values) {
      Allure.step("[UI - Select] Selecting multiple options in dropdown: " + element.enumImpl() + " (Values: "
            + Arrays.toString(values) + ")");
      element.before().accept(driver);
      selectService.selectOptions(element.componentType(), element.locator(), values);
      element.after().accept(driver);
      return uiServiceFluent;
   }

   public T selectOptions(final SelectUiElementCore<L, D> element, final Strategy strategy) {
      Allure.step(
            "[UI - Select] Selecting option in dropdown: " + element.enumImpl() + " (Strategy: " + strategy + ")");
      element.before().accept(driver);
      selectService.selectOptions(element.componentType(), element.locator(), strategy);
      element.after().accept(driver);
      return uiServiceFluent;
   }

   public T selectOption(final SelectUiElementCore<L, D> element, final String value) {
      Allure.step("[UI - Select] Selecting option in dropdown: " + element.enumImpl() + " (Value: " + value + ")");
      element.before().accept(driver);
      selectService.selectOptions(element.componentType(), element.locator(), value);
      element.after().accept(driver);
      return uiServiceFluent;
   }

   public T getAvailableOptions(final SelectUiElementCore<L, D> element) {
      Allure.step("[UI - Select] Retrieving available options in dropdown: " + element.enumImpl());
      element.before().accept(driver);
      List<String> availableOptions = selectService.getAvailableOptions(element.componentType(), element.locator());
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), availableOptions);
      return uiServiceFluent;
   }

   public T validateAvailableOptions(final SelectUiElementCore<L, D> element, final String... expectedValues) {
      return validateAvailableOptions(element, false, expectedValues);
   }

   public T validateAvailableOptions(final SelectUiElementCore<L, D> element, boolean soft,
                                     final String... expectedValues) {
      Allure.step("[UI - Select] Validating available options in dropdown: " + element.enumImpl() + " (Soft: "
            + soft + ", Expected Values: " + Arrays.toString(expectedValues) + ")");
      element.before().accept(driver);
      List<String> availableOptions = selectService.getAvailableOptions(element.componentType(), element.locator());
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), availableOptions);

      if (soft) {
         return (T) uiServiceFluent.validate(
               softAssertions -> softAssertions.assertThat(availableOptions)
                     .as(VALIDATING_AVAILABLE_OPTIONS).containsAll(Arrays.asList(expectedValues))
         );
      } else {
         return (T) uiServiceFluent.validate(
               () -> Assertions.assertThat(availableOptions)
                     .as(VALIDATING_AVAILABLE_OPTIONS).containsAll(Arrays.asList(expectedValues))
         );
      }
   }

   public T validateAvailableOptions(final SelectUiElementCore<L, D> element, final int expectedValuesCount) {
      return validateAvailableOptions(element, false, expectedValuesCount);
   }

   public T validateAvailableOptions(final SelectUiElementCore<L, D> element, boolean soft,
                                     final int expectedValuesCount) {
      Allure.step("[UI - Select] Validating number of available options in dropdown: " + element.enumImpl()
            + " (Soft: " + soft + ", Expected Count: " + expectedValuesCount + ")");
      element.before().accept(driver);
      List<String> availableOptions = selectService.getAvailableOptions(element.componentType(), element.locator());
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), availableOptions);

      if (soft) {
         return (T) uiServiceFluent.validate(
               softAssertions -> softAssertions.assertThat(availableOptions.size())
                     .as(VALIDATING_AVAILABLE_OPTIONS).isEqualTo(expectedValuesCount)
         );
      } else {
         return (T) uiServiceFluent.validate(
               () -> Assertions.assertThat(availableOptions.size())
                     .as(VALIDATING_AVAILABLE_OPTIONS).isEqualTo(expectedValuesCount)
         );
      }
   }

   public T getSelectedOptions(final SelectUiElementCore<L, D> element) {
      Allure.step("[UI - Select] Retrieving selected options in dropdown: " + element.enumImpl());
      element.before().accept(driver);
      List<String> selectedOptions = selectService.getSelectedOptions(element.componentType(), element.locator());
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), selectedOptions);
      return uiServiceFluent;
   }

   public T validateSelectedOptions(final SelectUiElementCore<L, D> element, final String... expectedValues) {
      return validateSelectedOptions(element, false, expectedValues);
   }

   public T validateSelectedOptions(final SelectUiElementCore<L, D> element, boolean soft,
                                    final String... expectedValues) {
      Allure.step("[UI - Select] Validating selected options in dropdown: " + element.enumImpl()
            + " (Soft: " + soft + ", Expected Values: " + Arrays.toString(expectedValues) + ")");
      element.before().accept(driver);
      List<String> selectedOptions = selectService.getSelectedOptions(element.componentType(), element.locator());
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), selectedOptions);

      if (soft) {
         return (T) uiServiceFluent.validate(
               softAssertions -> softAssertions.assertThat(selectedOptions)
                     .as("Validating selected options").containsAll(Arrays.asList(expectedValues))
         );
      } else {
         return (T) uiServiceFluent.validate(
               () -> Assertions.assertThat(selectedOptions)
                     .as("Validating selected options").containsAll(Arrays.asList(expectedValues))
         );
      }
   }

   public T isOptionVisible(final SelectUiElementCore<L, D> element, final String value) {
      Allure.step("[UI - Select] Checking if option " + value + " is visible in select element " + element);
      element.before().accept(driver);
      boolean visibleOption = selectService.isOptionVisible(element.componentType(), element.locator(), value);
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), visibleOption);
      return uiServiceFluent;
   }

   public T validateIsOptionVisible(final SelectUiElementCore<L, D> element, final String value) {
      return validateIsVisible(element, true, value, false);
   }

   public T validateIsOptionVisible(final SelectUiElementCore<L, D> element, final String value, boolean soft) {
      return validateIsVisible(element, true, value, soft);
   }

   public T validateIsOptionHidden(final SelectUiElementCore<L, D> element, final String value) {
      return validateIsVisible(element, false, value, false);
   }

   public T validateIsOptionHidden(final SelectUiElementCore<L, D> element, final String value, boolean soft) {
      return validateIsVisible(element, false, value, soft);
   }

   private T validateIsVisible(final SelectUiElementCore<L, D> element, boolean shouldBeVisible, String value,
                               boolean soft) {
      Allure.step("[UI - Select] Validating visibility of option " + value + " in select element " + element);
      element.before().accept(driver);
      boolean visible = selectService.isOptionVisible(element.componentType(), element.locator(), value);
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), visible);

      String assertionMessage = shouldBeVisible
            ? "Validating option is visible"
            : "Validating option is hidden";

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

   public T isOptionEnabled(final SelectUiElementCore<L, D> element, final String value) {
      Allure.step("[UI - Select] Checking if option " + value + " is enabled in select element " + element);
      element.before().accept(driver);
      boolean enabledOption = selectService.isOptionEnabled(element.componentType(), element.locator(), value);
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), enabledOption);
      return uiServiceFluent;
   }

   public T validateIsOptionEnabled(final SelectUiElementCore<L, D> element, final String value) {
      return validateIsEnabled(element, true, value, false);
   }

   public T validateIsOptionEnabled(final SelectUiElementCore<L, D> element, final String value, boolean soft) {
      return validateIsEnabled(element, true, value, soft);
   }

   public T validateIsOptionDisabled(final SelectUiElementCore<L, D> element, final String value) {
      return validateIsEnabled(element, false, value, false);
   }

   public T validateIsOptionDisabled(final SelectUiElementCore<L, D> element, final String value, boolean soft) {
      return validateIsEnabled(element, false, value, soft);
   }

   private T validateIsEnabled(final SelectUiElementCore<L, D> element, boolean shouldBeEnabled, String value,
                               boolean soft) {
      Allure.step("[UI - Select] Validating whether option " + value + " is "
            + (shouldBeEnabled ? "enabled" : "disabled") + " in select element " + element);
      element.before().accept(driver);
      boolean enabled = selectService.isOptionVisible(element.componentType(), element.locator(), value);
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), enabled);

      String assertionMessage = shouldBeEnabled
            ? "Validating option is enabled"
            : "Validating option is disabled";

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
}
