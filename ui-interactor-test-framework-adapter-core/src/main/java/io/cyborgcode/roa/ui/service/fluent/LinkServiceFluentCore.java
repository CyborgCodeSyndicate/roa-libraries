package io.cyborgcode.roa.ui.service.fluent;

import io.cyborgcode.roa.framework.storage.Storage;
import io.cyborgcode.roa.ui.components.base.BaseUiElement;
import io.cyborgcode.roa.ui.components.link.LinkServiceCore;
import io.cyborgcode.roa.ui.elements.LinkUiElementCore;
import io.qameta.allure.Allure;
import org.assertj.core.api.Assertions;

import static io.cyborgcode.roa.ui.storage.StorageKeysUi.UI;

/**
 * Provides a fluent API for interacting with link components.
 *
 * @param <T> Represents the fluent UI service that extends {@link UiServiceFluentCore}.
 * @author Cyborg Code Syndicate 💍👨💻
 */
@SuppressWarnings({"java:S5960", "unchecked"})
public class LinkServiceFluentCore<T extends UiServiceFluentCore<L, D, E, ?>, L, D, E extends BaseUiElement> {

   private final LinkServiceCore<E, L> linkService;
   private final T uiServiceFluent;
   private final Storage storage;
   private final D driver;

   public LinkServiceFluentCore(T uiServiceFluent, Storage storage, LinkServiceCore<E, L> linkService,
                                D webDriver) {
      this.linkService = linkService;
      this.uiServiceFluent = uiServiceFluent;
      this.storage = storage;
      driver = webDriver;
   }

   public T click(final LinkUiElementCore<L, D> element) {
      Allure.step("[UI - Link] Clicking on the link element: " + element);
      element.before().accept(driver);
      linkService.click(element.componentType(), element.locator());
      element.after().accept(driver);
      return uiServiceFluent;
   }

   public T doubleClick(final LinkUiElementCore<L, D> element) {
      Allure.step("[UI - Link] Double-clicking on the link element: " + element);
      element.before().accept(driver);
      linkService.doubleClick(element.componentType(), element.locator());
      element.after().accept(driver);
      return uiServiceFluent;
   }

   public T isEnabled(final LinkUiElementCore<L, D> element) {
      Allure.step("[UI - Link] Checking if the link element is enabled: " + element);
      element.before().accept(driver);
      boolean enabled = linkService.isEnabled(element.componentType(), element.locator());
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), enabled);
      return uiServiceFluent;
   }

   public T validateIsEnabled(final LinkUiElementCore<L, D> element) {
      return validateIsEnabled(element, true, false);
   }

   public T validateIsEnabled(final LinkUiElementCore<L, D> element, boolean soft) {
      return validateIsEnabled(element, true, soft);
   }

   private T validateIsEnabled(final LinkUiElementCore<L, D> element, boolean shouldBeEnabled, boolean soft) {
      Allure.step(
            "[UI - Link] Validating if the link element should be " + (shouldBeEnabled ? "enabled" : "disabled") + ": "
                  + element);
      element.before().accept(driver);
      boolean enabled = linkService.isEnabled(element.componentType(), element.locator());
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), enabled);

      String assertionMessage = shouldBeEnabled
            ? "Validating link is enabled"
            : "Validating link is disabled";

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

   public T validateIsDisabled(final LinkUiElementCore<L, D> element) {
      return validateIsEnabled(element, false, false);
   }

   public T validateIsDisabled(final LinkUiElementCore<L, D> element, boolean soft) {
      return validateIsEnabled(element, false, soft);
   }

   public T isVisible(final LinkUiElementCore<L, D> element) {
      Allure.step("[UI - Link] Checking if the link element is visible: " + element);
      element.before().accept(driver);
      boolean visible = linkService.isVisible(element.componentType(), element.locator());
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), visible);
      return uiServiceFluent;
   }

   public T validateIsVisible(final LinkUiElementCore<L, D> element) {
      return validateIsVisible(element, true, false);
   }

   public T validateIsVisible(final LinkUiElementCore<L, D> element, boolean soft) {
      return validateIsVisible(element, true, soft);
   }

   private T validateIsVisible(final LinkUiElementCore<L, D> element, boolean shouldBeVisible, boolean soft) {
      Allure.step(
            "[UI - Link] Validating if the link element should be " + (shouldBeVisible ? "visible" : "hidden") + ": "
                  + element);
      element.before().accept(driver);
      boolean visible = linkService.isVisible(element.componentType(), element.locator());
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), visible);

      String assertionMessage = shouldBeVisible
            ? "Validating link is visible"
            : "Validating link is hidden";

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

   public T validateIsHidden(final LinkUiElementCore<L, D> element) {
      return validateIsVisible(element, false, false);
   }

   public T validateIsHidden(final LinkUiElementCore<L, D> element, boolean soft) {
      return validateIsVisible(element, false, soft);
   }
}
