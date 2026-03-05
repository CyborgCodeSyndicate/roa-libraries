package io.cyborgcode.roa.ui.service.fluent;

import io.cyborgcode.roa.framework.storage.Storage;
import io.cyborgcode.roa.ui.components.base.BaseUiElement;
import io.cyborgcode.roa.ui.components.list.ItemListServiceCore;
import io.cyborgcode.roa.ui.elements.ListUiElementCore;
import io.qameta.allure.Allure;
import java.util.Arrays;
import java.util.List;
import org.assertj.core.api.Assertions;

import static io.cyborgcode.roa.ui.storage.StorageKeysUi.UI;

/**
 * Provides a fluent API for interacting with list components.
 *
 * @param <T> Represents the fluent UI service that extends {@link UiServiceFluentCore}.
 * @author Cyborg Code Syndicate 💍👨💻
 */
@SuppressWarnings({"java:S5960", "unchecked"})
public class ListServiceFluentCore<T extends UiServiceFluentCore<L, D, E, ?>, L, D, E extends BaseUiElement> {

   private static final String VALIDATING_SELECTED_ITEMS = "Validating Selected Items";
   private final ItemListServiceCore<E, L> itemListService;
   private final T uiServiceFluent;
   private final Storage storage;
   private final D driver;

   public ListServiceFluentCore(T uiServiceFluent, Storage storage, ItemListServiceCore<E, L> itemListService,
                                D webDriver) {
      this.itemListService = itemListService;
      this.uiServiceFluent = uiServiceFluent;
      this.storage = storage;
      driver = webDriver;
   }

   public T select(final ListUiElementCore<L, D> element, final String... values) {
      Allure.step("[UI - List] Select values from list: " + Arrays.toString(values));
      element.before().accept(driver);
      itemListService.select(element.componentType(), element.locator(), values);
      element.after().accept(driver);
      return uiServiceFluent;
   }

   public T deSelect(final ListUiElementCore<L, D> element, final String... values) {
      Allure.step("[UI - List] Deselect values from list: " + Arrays.toString(values));
      element.before().accept(driver);
      itemListService.deSelect(element.componentType(), element.locator(), values);
      element.after().accept(driver);
      return uiServiceFluent;
   }

   public T areSelected(final ListUiElementCore<L, D> element, final String... values) {
      Allure.step("[UI - List] Check if values are selected in the list: " + Arrays.toString(values));
      element.before().accept(driver);
      boolean selected = itemListService.areSelected(element.componentType(), element.locator(), values);
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), selected);
      return uiServiceFluent;
   }

   public T validateAreSelected(final ListUiElementCore<L, D> element, final String... values) {
      Allure.step("[UI - List] Validate if values are selected in the list: " + Arrays.toString(values));
      return validateAreSelected(element, true, false, values);
   }

   public T validateAreSelected(final ListUiElementCore<L, D> element, boolean soft, final String... values) {
      Allure.step("[UI - List] Validate if values are selected (soft: " + soft + "): " + Arrays.toString(values));
      return validateAreSelected(element, true, soft, values);
   }

   private T validateAreSelected(final ListUiElementCore<L, D> element, boolean shouldBeSelected, boolean soft,
                                 final String... values) {
      element.before().accept(driver);
      boolean selected = itemListService.areSelected(element.componentType(), element.locator(), values);
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), selected);

      String assertionMessage = shouldBeSelected
            ? "Validating list items are selected"
            : "Validating list items are not selected";

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

   public T validateAreNotSelected(final ListUiElementCore<L, D> element, final String... values) {
      Allure.step("[UI - List] Validate if values are not selected in the list: " + Arrays.toString(values));
      return validateAreSelected(element, false, false, values);
   }

   public T validateAreNotSelected(final ListUiElementCore<L, D> element, boolean soft, final String... values) {
      Allure.step("[UI - List] Validate if values are not selected (soft: " + soft + "): " + Arrays.toString(values));
      return validateAreSelected(element, false, soft, values);
   }

   public T isSelected(final ListUiElementCore<L, D> element, final String value) {
      Allure.step("[UI - List] Check if value is selected in the list: " + value);
      element.before().accept(driver);
      boolean selected = itemListService.areSelected(element.componentType(), element.locator(), value);
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), selected);
      return uiServiceFluent;
   }

   public T validateIsSelected(final ListUiElementCore<L, D> element, final String value) {
      Allure.step("[UI - List] Validate if value is selected in the list: " + value);
      return validateAreSelected(element, true, false, value);
   }

   public T validateIsSelected(final ListUiElementCore<L, D> element, boolean soft, final String value) {
      Allure.step("[UI - List] Validate if value is selected (soft: " + soft + "): " + value);
      return validateAreSelected(element, true, soft, value);
   }

   public T validateIsNotSelected(final ListUiElementCore<L, D> element, final String value) {
      Allure.step("[UI - List] Validate if value is not selected in the list: " + value);
      return validateAreSelected(element, false, false, value);
   }

   public T validateIsNotSelected(final ListUiElementCore<L, D> element, boolean soft, final String value) {
      Allure.step("[UI - List] Validate if value is not selected (soft: " + soft + "): " + value);
      return validateAreSelected(element, false, soft, value);
   }

   public T areEnabled(final ListUiElementCore<L, D> element, final String... values) {
      Allure.step("[UI - List] Check if values are enabled in the list: " + Arrays.toString(values));
      element.before().accept(driver);
      boolean enabled = itemListService.areEnabled(element.componentType(), element.locator(), values);
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), enabled);
      return uiServiceFluent;
   }

   public T validateAreEnabled(final ListUiElementCore<L, D> element, final String... values) {
      Allure.step("[UI - List] Validate if values are enabled in the list: " + Arrays.toString(values));
      return validateAreEnabled(element, true, false, values);
   }

   public T validateAreEnabled(final ListUiElementCore<L, D> element, boolean soft, final String... values) {
      Allure.step("[UI - List] Validate if values are enabled (soft: " + soft + "): " + Arrays.toString(values));
      return validateAreEnabled(element, true, soft, values);
   }

   private T validateAreEnabled(final ListUiElementCore<L, D> element, boolean shouldBeEnabled, boolean soft,
                                final String... values) {
      element.before().accept(driver);
      boolean enabled = itemListService.areEnabled(element.componentType(), element.locator(), values);
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), enabled);

      String assertionMessage = shouldBeEnabled
            ? "Validating list items are enabled"
            : "Validating list items are disabled";

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

   public T validateAreDisabled(final ListUiElementCore<L, D> element, final String... values) {
      Allure.step("[UI - List] Validate that the specified values in the list are disabled");
      return validateAreEnabled(element, false, false, values);
   }

   public T validateAreDisabled(final ListUiElementCore<L, D> element, boolean soft, final String... values) {
      Allure.step("[UI - List] Validate that the specified values in the list are disabled");
      return validateAreEnabled(element, false, soft, values);
   }

   public T isEnabled(final ListUiElementCore<L, D> element, final String value) {
      Allure.step("[UI - List] Check if the specified value is enabled");
      element.before().accept(driver);
      boolean enabled = itemListService.areEnabled(element.componentType(), element.locator(), value);
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), enabled);
      return uiServiceFluent;
   }

   public T validateIsEnabled(final ListUiElementCore<L, D> element, final String value) {
      Allure.step("[UI - List] Validate that the specified value is enabled");
      return validateAreEnabled(element, true, false, value);
   }

   public T validateIsEnabled(final ListUiElementCore<L, D> element, boolean soft, final String value) {
      Allure.step("[UI - List] Validate that the specified value is enabled");
      return validateAreEnabled(element, true, soft, value);
   }

   public T validateIsDisabled(final ListUiElementCore<L, D> element, final String value) {
      Allure.step("[UI - List] Validate that the specified value is disabled");
      return validateAreEnabled(element, false, false, value);
   }

   public T validateIsDisabled(final ListUiElementCore<L, D> element, boolean soft, final String value) {
      Allure.step("[UI - List] Validate that the specified value is disabled");
      return validateAreEnabled(element, false, soft, value);
   }

   public T areVisible(final ListUiElementCore<L, D> element, final String... values) {
      Allure.step("[UI - List] Check if the specified values are visible");
      element.before().accept(driver);
      boolean visible = itemListService.areVisible(element.componentType(), element.locator(), values);
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), visible);
      return uiServiceFluent;
   }

   public T validateAreVisible(final ListUiElementCore<L, D> element, final String... values) {
      Allure.step("[UI - List] Validate that the specified values are visible in the list");
      return validateAreVisible(element, true, false, values);
   }

   public T validateAreVisible(final ListUiElementCore<L, D> element, boolean soft, final String... values) {
      Allure.step("[UI - List] Validate that the specified values are visible in the list");
      return validateAreVisible(element, true, soft, values);
   }

   private T validateAreVisible(final ListUiElementCore<L, D> element, boolean shouldBeVisible, boolean soft,
                                final String... values) {
      element.before().accept(driver);
      boolean visible = itemListService.areVisible(element.componentType(), element.locator(), values);
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), visible);

      String assertionMessage = shouldBeVisible
            ? "Validating list items are visible"
            : "Validating list items are hidden";

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

   public T validateAreHidden(final ListUiElementCore<L, D> element, final String... values) {
      Allure.step("[UI - List] Validate that the specified values are hidden in the list");
      return validateAreVisible(element, false, false, values);
   }

   public T validateAreHidden(final ListUiElementCore<L, D> element, boolean soft, final String... values) {
      Allure.step("[UI - List] Validate that the specified values are hidden in the list");
      return validateAreVisible(element, false, soft, values);
   }

   public T isVisible(final ListUiElementCore<L, D> element, final String value) {
      Allure.step("[UI - List] Check if the specified value is visible");
      element.before().accept(driver);
      boolean visible = itemListService.areVisible(element.componentType(), element.locator(), value);
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), visible);
      return uiServiceFluent;
   }

   public T validateIsVisible(final ListUiElementCore<L, D> element, final String value) {
      Allure.step("[UI - List] Validate that the specified value is visible");
      return validateAreVisible(element, true, false, value);
   }

   public T validateIsVisible(final ListUiElementCore<L, D> element, boolean soft, final String value) {
      Allure.step("[UI - List] Validate that the specified value is visible");
      return validateAreVisible(element, true, soft, value);
   }

   public T validateIsHidden(final ListUiElementCore<L, D> element, final String value) {
      Allure.step("[UI - List] Validate that the specified value is hidden");
      return validateAreVisible(element, false, false, value);
   }

   public T validateIsHidden(final ListUiElementCore<L, D> element, boolean soft, final String value) {
      Allure.step("[UI - List] Validate that the specified value is hidden");
      return validateAreVisible(element, false, soft, value);
   }

   public T getSelected(final ListUiElementCore<L, D> element) {
      Allure.step("[UI - List] Retrieve selected items from the list");
      element.before().accept(driver);
      List<String> selectedItems = itemListService.getSelected(element.componentType(), element.locator());
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), selectedItems);
      return uiServiceFluent;
   }

   public T validateSelectedItems(final ListUiElementCore<L, D> element, final String... expectedValues) {
      Allure.step("[UI - List] Validate that the specified items are selected");
      return validateSelectedItems(element, true, false, expectedValues);
   }

   public T validateSelectedItems(final ListUiElementCore<L, D> element, boolean soft,
                                  final String... expectedValues) {
      Allure.step("[UI - List] Validate that the specified items are selected");
      return validateSelectedItems(element, true, soft, expectedValues);
   }

   private T validateSelectedItems(final ListUiElementCore<L, D> element, boolean shouldBeSelected, boolean soft,
                                   final String... expectedValues) {
      element.before().accept(driver);
      List<String> selectedItems = itemListService.getSelected(element.componentType(), element.locator());
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), selectedItems);

      if (soft) {
         return (T) uiServiceFluent.validate(
               softAssertions -> {
                  if (shouldBeSelected) {
                     softAssertions.assertThat(selectedItems).as(VALIDATING_SELECTED_ITEMS)
                           .containsAll(Arrays.asList(expectedValues));
                  } else {
                     softAssertions.assertThat(selectedItems).as(VALIDATING_SELECTED_ITEMS)
                           .doesNotContainAnyElementsOf(Arrays.asList(expectedValues));
                  }
               }
         );
      } else {
         return (T) uiServiceFluent.validate(
               () -> {
                  if (shouldBeSelected) {
                     Assertions.assertThat(selectedItems).as(VALIDATING_SELECTED_ITEMS)
                           .containsAll(Arrays.asList(expectedValues));
                  } else {
                     Assertions.assertThat(selectedItems).as(VALIDATING_SELECTED_ITEMS)
                           .doesNotContainAnyElementsOf(Arrays.asList(expectedValues));
                  }
               }
         );
      }
   }

   public T validateNotSelectedItems(final ListUiElementCore<L, D> element, final String... expectedValues) {
      Allure.step("[UI - List] Validate that the specified items are not selected");
      return validateSelectedItems(element, false, false, expectedValues);
   }

   public T validateNotSelectedItems(final ListUiElementCore<L, D> element, boolean soft,
                                     final String... expectedValues) {
      Allure.step("[UI - List] Validate that the specified items are not selected");
      return validateSelectedItems(element, false, soft, expectedValues);
   }

   public T getAll(final ListUiElementCore<L, D> element) {
      Allure.step("[UI - List] Retrieve all items from the list");
      element.before().accept(driver);
      List<String> allItems = itemListService.getAll(element.componentType(), element.locator());
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), allItems);
      return uiServiceFluent;
   }

   public T validateAllItems(final ListUiElementCore<L, D> element, final String... expectedValues) {
      Allure.step("[UI - List] Validate that all expected items are present in the list");
      return validateAllItems(element, false, expectedValues);
   }

   public T validateAllItems(final ListUiElementCore<L, D> element, boolean soft, final String... expectedValues) {
      Allure.step("[UI - List] Validate that all expected items are present in the list");
      element.before().accept(driver);
      List<String> allItems = itemListService.getAll(element.componentType(), element.locator());
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), allItems);

      if (soft) {
         return (T) uiServiceFluent.validate(
               softAssertions -> softAssertions.assertThat(allItems)
                     .as("Validating all list items")
                     .containsAll(Arrays.asList(expectedValues))
         );
      } else {
         return (T) uiServiceFluent.validate(
               () -> Assertions.assertThat(allItems)
                     .as("Validating all list items")
                     .containsAll(Arrays.asList(expectedValues))
         );
      }
   }
}
