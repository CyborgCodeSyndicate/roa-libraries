package io.cyborgcode.roa.ui.service.fluent;

import io.cyborgcode.roa.framework.storage.Storage;
import io.cyborgcode.roa.ui.components.base.BaseUiElement;
import io.cyborgcode.roa.ui.components.modal.ModalServiceCore;
import io.cyborgcode.roa.ui.elements.ModalUiElementCore;
import io.qameta.allure.Allure;
import org.assertj.core.api.Assertions;

import static io.cyborgcode.roa.ui.storage.StorageKeysUi.UI;

/**
 * Provides a fluent API for interacting with modal components.
 *
 * @param <T> Represents the fluent UI service that extends {@link UiServiceFluentCore}.
 * @author Cyborg Code Syndicate 💍👨💻
 */
@SuppressWarnings({"java:S5960", "unchecked"})
public class ModalServiceFluentCore<T extends UiServiceFluentCore<L, D, E, ?>, L, D, E extends BaseUiElement> {

   private static final String VALIDATING_MODAL = "Validating Modal";
   private final ModalServiceCore<E, L> modalService;
   private final T uiServiceFluent;
   private final Storage storage;
   private final D driver;

   public ModalServiceFluentCore(T uiServiceFluent, Storage storage, ModalServiceCore<E, L> modalService,
                                 D webDriver) {
      this.modalService = modalService;
      this.uiServiceFluent = uiServiceFluent;
      this.storage = storage;
      this.driver = webDriver;
   }

   public T isOpened(final ModalUiElementCore<L, D> element) {
      Allure.step("[UI - Modal] Check if modal is opened");
      element.before().accept(driver);
      boolean isOpened = modalService.isOpened(element.componentType());
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), isOpened);
      return uiServiceFluent;
   }

   public T validateIsOpened(final ModalUiElementCore<L, D> element) {
      return validateIsOpened(element, true, false);
   }

   public T validateIsOpened(final ModalUiElementCore<L, D> element, boolean soft) {
      return validateIsOpened(element, true, soft);
   }

   private T validateIsOpened(final ModalUiElementCore<L, D> element, boolean shouldBeOpened, boolean soft) {
      Allure.step(String.format("[UI - Modal] Checking if modal with component type: %s is opened",
            element.componentType()));
      element.before().accept(driver);
      boolean isOpened = modalService.isOpened(element.componentType());
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), isOpened);

      String assertionMessage = shouldBeOpened
            ? "Validating Modal is opened"
            : "Validating Modal is closed";

      if (soft) {
         return (T) uiServiceFluent.validate(
               softAssertions -> {
                  if (shouldBeOpened) {
                     softAssertions.assertThat(isOpened).as(assertionMessage).isTrue();
                  } else {
                     softAssertions.assertThat(isOpened).as(assertionMessage).isFalse();
                  }
               }
         );
      } else {
         return (T) uiServiceFluent.validate(
               () -> {
                  if (shouldBeOpened) {
                     Assertions.assertThat(isOpened).as(assertionMessage).isTrue();
                  } else {
                     Assertions.assertThat(isOpened).as(assertionMessage).isFalse();
                  }
               }
         );
      }
   }

   public T validateIsClosed(final ModalUiElementCore<L, D> element) {
      return validateIsOpened(element, false, false);
   }

   public T validateIsClosed(final ModalUiElementCore<L, D> element, boolean soft) {
      return validateIsOpened(element, false, soft);
   }

   public T click(final ModalUiElementCore<L, D> element) {
      Allure.step("[UI - Modal] Click button inside the modal UI element");
      element.before().accept(driver);
      modalService.clickButton(element.componentType(), element.locator());
      element.after().accept(driver);
      return uiServiceFluent;
   }

   public T getTitle(final ModalUiElementCore<L, D> element) {
      Allure.step("[UI - Modal] Retrieve title of the modal UI element");
      element.before().accept(driver);
      String modalTitle = modalService.getTitle(element.componentType());
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), modalTitle);
      return uiServiceFluent;
   }

   public T validateTitle(final ModalUiElementCore<L, D> element, final String expectedValue) {
      Allure.step("[UI - Modal] Validate title of the modal");
      return validateTitle(element, false, expectedValue);
   }

   public T validateTitle(final ModalUiElementCore<L, D> element, boolean soft, final String expectedValue) {
      Allure.step("[UI - Modal] Validate title of the modal");
      element.before().accept(driver);
      String modalTitle = modalService.getTitle(element.componentType());
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), modalTitle);

      if (soft) {
         return (T) uiServiceFluent.validate(
               softAssertions -> softAssertions.assertThat(modalTitle)
                     .as(VALIDATING_MODAL)
                     .isEqualTo(expectedValue)
         );
      } else {
         return (T) uiServiceFluent.validate(
               () -> Assertions.assertThat(modalTitle)
                     .as(VALIDATING_MODAL)
                     .isEqualTo(expectedValue)
         );
      }
   }

   public T getContentTitle(final ModalUiElementCore<L, D> element) {
      Allure.step("[UI - Modal] Retrieve content title of the modal UI element");
      element.before().accept(driver);
      String modalContentTitle = modalService.getContentTitle(element.componentType());
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), modalContentTitle);
      return uiServiceFluent;
   }

   public T validateContentTitle(final ModalUiElementCore<L, D> element, final String expectedValue) {
      Allure.step("[UI - Modal] Validate content title of the modal");
      return validateContentTitle(element, false, expectedValue);
   }

   public T validateContentTitle(final ModalUiElementCore<L, D> element, boolean soft, final String expectedValue) {
      Allure.step("[UI - Modal] Validate content title of the modal");
      element.before().accept(driver);
      String modalContentTitle = modalService.getContentTitle(element.componentType());
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), modalContentTitle);

      if (soft) {
         return (T) uiServiceFluent.validate(
               softAssertions -> softAssertions.assertThat(modalContentTitle)
                     .as(VALIDATING_MODAL)
                     .isEqualTo(expectedValue)
         );
      } else {
         return (T) uiServiceFluent.validate(
               () -> Assertions.assertThat(modalContentTitle)
                     .as(VALIDATING_MODAL)
                     .isEqualTo(expectedValue)
         );
      }
   }

   public T getBodyText(final ModalUiElementCore<L, D> element) {
      Allure.step("[UI - Modal] Retrieve body text of the modal UI element");
      element.before().accept(driver);
      String modalBodyText = modalService.getBodyText(element.componentType());
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), modalBodyText);
      return uiServiceFluent;
   }

   public T validateBodyText(final ModalUiElementCore<L, D> element, final String expectedText) {
      Allure.step("[UI - Modal] Validate content of the modal");
      return validateBodyText(element, false, expectedText);
   }

   public T validateBodyText(final ModalUiElementCore<L, D> element, boolean soft, final String expectedValue) {
      Allure.step("[UI - Modal] Validate content of the modal");
      element.before().accept(driver);
      String modalBodyText = modalService.getBodyText(element.componentType());
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), modalBodyText);

      if (soft) {
         return (T) uiServiceFluent.validate(
               softAssertions -> softAssertions.assertThat(modalBodyText)
                     .as(VALIDATING_MODAL)
                     .isEqualTo(expectedValue)
         );
      } else {
         return (T) uiServiceFluent.validate(
               () -> Assertions.assertThat(modalBodyText)
                     .as(VALIDATING_MODAL)
                     .isEqualTo(expectedValue)
         );
      }
   }

   public T close(final ModalUiElementCore<L, D> element) {
      Allure.step("[UI - Modal] Close the modal UI element");
      element.before().accept(driver);
      modalService.close(element.componentType());
      element.after().accept(driver);
      return uiServiceFluent;
   }
}
