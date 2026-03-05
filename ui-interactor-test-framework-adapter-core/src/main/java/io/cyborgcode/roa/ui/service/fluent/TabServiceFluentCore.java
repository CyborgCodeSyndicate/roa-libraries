package io.cyborgcode.roa.ui.service.fluent;

import io.cyborgcode.roa.framework.storage.Storage;
import io.cyborgcode.roa.ui.components.base.BaseUiElement;
import io.cyborgcode.roa.ui.components.tab.TabServiceCore;
import io.cyborgcode.roa.ui.elements.TabUiElementCore;
import io.qameta.allure.Allure;
import org.assertj.core.api.Assertions;

import static io.cyborgcode.roa.ui.storage.StorageKeysUi.UI;

/**
 * Provides a fluent API for interacting with tab components.
 *
 * @param <T> Represents the fluent UI service that extends {@link UiServiceFluentCore}.
 * @author Cyborg Code Syndicate 💍👨💻
 */
@SuppressWarnings({"java:S5960", "unchecked"})
public class TabServiceFluentCore<T extends UiServiceFluentCore<L, D, E, ?>, L, D, E extends BaseUiElement> {

   private static final String UI_TAB_CHECKING_IF_THE_TAB = "[UI - Tab] Checking if the tab ";
   private static final String UI_TAB_VALIDATING_IF_THE_TAB = "[UI - Tab] Validating if the tab ";
   private static final String UI_TAB_PERFORMING_SOFT_VALIDATION_FOR_THE_TAB =
         "[UI - Tab] Performing soft validation for the tab ";
   private static final String UI_TAB_PERFORMING_STRICT_VALIDATION_FOR_THE_TAB =
         "[UI - Tab] Performing strict validation for the tab ";

   private final TabServiceCore<E, L> tabService;
   private final T uiServiceFluent;
   private final Storage storage;
   private final D driver;

   public TabServiceFluentCore(T uiServiceFluent, Storage storage, TabServiceCore<E, L> tabService,
                               D webDriver) {
      this.tabService = tabService;
      this.uiServiceFluent = uiServiceFluent;
      this.storage = storage;
      driver = webDriver;
   }

   public T click(final TabUiElementCore<L, D> element) {
      Allure.step("[UI - Tab] Clicking on the tab " + element);
      element.before().accept(driver);
      tabService.click(element.componentType(), element.locator());
      element.after().accept(driver);
      return uiServiceFluent;
   }

   public T isEnabled(final TabUiElementCore<L, D> element) {
      Allure.step(UI_TAB_CHECKING_IF_THE_TAB + element + " is enabled.");
      element.before().accept(driver);
      boolean enabled = tabService.isEnabled(element.componentType(), element.locator());
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), enabled);
      return uiServiceFluent;
   }

   public T validateIsEnabled(final TabUiElementCore<L, D> element) {
      return validateIsEnabled(element, true, false);
   }

   public T validateIsEnabled(final TabUiElementCore<L, D> element, boolean soft) {
      return validateIsEnabled(element, true, soft);
   }

   private T validateIsEnabled(final TabUiElementCore<L, D> element, boolean shouldBeEnabled, boolean soft) {
      Allure.step(UI_TAB_VALIDATING_IF_THE_TAB + element + " is " + (shouldBeEnabled ? "enabled" : "disabled"));
      element.before().accept(driver);
      boolean enabled = tabService.isEnabled(element.componentType(), element.locator());
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), enabled);

      String assertionMessage = shouldBeEnabled
            ? "Validating tab is enabled"
            : "Validating tab is disabled";

      if (soft) {
         Allure.step(UI_TAB_PERFORMING_SOFT_VALIDATION_FOR_THE_TAB + element);
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
         Allure.step(UI_TAB_PERFORMING_STRICT_VALIDATION_FOR_THE_TAB + element);
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

   public T validateIsDisabled(final TabUiElementCore<L, D> element) {
      return validateIsEnabled(element, false, false);
   }

   public T validateIsDisabled(final TabUiElementCore<L, D> element, boolean soft) {
      return validateIsEnabled(element, false, soft);
   }

   public T isVisible(final TabUiElementCore<L, D> element) {
      Allure.step(UI_TAB_CHECKING_IF_THE_TAB + element + " is visible.");
      element.before().accept(driver);
      boolean visible = tabService.isVisible(element.componentType(), element.locator());
      storage.sub(UI).put(element.enumImpl(), visible);
      element.after().accept(driver);
      return uiServiceFluent;
   }

   public T validateIsVisible(final TabUiElementCore<L, D> element) {
      return validateIsVisible(element, true, false);
   }

   public T validateIsVisible(final TabUiElementCore<L, D> element, boolean soft) {
      return validateIsVisible(element, true, soft);
   }

   private T validateIsVisible(final TabUiElementCore<L, D> element, boolean shouldBeVisible, boolean soft) {
      Allure.step(UI_TAB_VALIDATING_IF_THE_TAB + element + " is " + (shouldBeVisible ? "visible" : "hidden"));
      element.before().accept(driver);
      boolean visible = tabService.isVisible(element.componentType(), element.locator());
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), visible);

      String assertionMessage = shouldBeVisible
            ? "Validating tab is visible"
            : "Validating tab is hidden";

      if (soft) {
         Allure.step(UI_TAB_PERFORMING_SOFT_VALIDATION_FOR_THE_TAB + element);
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
         Allure.step(UI_TAB_PERFORMING_STRICT_VALIDATION_FOR_THE_TAB + element);
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

   public T validateIsHidden(final TabUiElementCore<L, D> element) {
      return validateIsVisible(element, false, false);
   }

   public T validateIsHidden(final TabUiElementCore<L, D> element, boolean soft) {
      return validateIsVisible(element, false, soft);
   }

   public T isSelected(final TabUiElementCore<L, D> element) {
      Allure.step(UI_TAB_CHECKING_IF_THE_TAB + element + " is selected.");
      element.before().accept(driver);
      boolean selected = tabService.isSelected(element.componentType(), element.locator());
      storage.sub(UI).put(element.enumImpl(), selected);
      element.after().accept(driver);
      return uiServiceFluent;
   }

   public T validateIsSelected(final TabUiElementCore<L, D> element) {
      return validateIsSelected(element, true, false);
   }

   public T validateIsSelected(final TabUiElementCore<L, D> element, boolean soft) {
      return validateIsSelected(element, true, soft);
   }

   private T validateIsSelected(final TabUiElementCore<L, D> element, boolean shouldBeSelected, boolean soft) {
      Allure.step(UI_TAB_VALIDATING_IF_THE_TAB + element + " is " + (shouldBeSelected ? "selected" : "not selected"));
      element.before().accept(driver);
      boolean selected = tabService.isSelected(element.componentType(), element.locator());
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), selected);

      String assertionMessage = shouldBeSelected
            ? "Validating tab is selected"
            : "Validating tab is not selected";

      if (soft) {
         Allure.step(UI_TAB_PERFORMING_SOFT_VALIDATION_FOR_THE_TAB + element);
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
         Allure.step(UI_TAB_PERFORMING_STRICT_VALIDATION_FOR_THE_TAB + element);
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

   public T validateIsNotSelected(final TabUiElementCore<L, D> element) {
      return validateIsSelected(element, false, false);
   }

   public T validateIsNotSelected(final TabUiElementCore<L, D> element, boolean soft) {
      return validateIsSelected(element, false, soft);
   }
}
