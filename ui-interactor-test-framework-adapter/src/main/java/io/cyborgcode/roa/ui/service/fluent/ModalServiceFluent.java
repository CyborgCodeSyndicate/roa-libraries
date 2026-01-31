package io.cyborgcode.roa.ui.service.fluent;

import io.cyborgcode.pandora.annotation.Pandora;
import io.cyborgcode.pandora.annotation.PandoraOptions;
import io.cyborgcode.pandora.model.CreationKind;
import io.cyborgcode.roa.framework.storage.Storage;
import io.cyborgcode.roa.ui.components.modal.ModalService;
import io.cyborgcode.roa.ui.selenium.ModalUiElement;
import io.cyborgcode.roa.ui.selenium.smart.SmartWebDriver;
import io.qameta.allure.Allure;
import org.assertj.core.api.Assertions;

import static io.cyborgcode.roa.ui.storage.StorageKeysUi.UI;

/**
 * A fluent service class for interacting with modal UI elements in test automation.
 *
 * <p>Provides methods for clicking buttons within modals, retrieving modal details, and closing modals.
 *
 * <p>The generic type {@code T} represents the UI service fluent implementation that extends {@link UiServiceFluent},
 * allowing method chaining for seamless interaction.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@SuppressWarnings({"java:S5960", "unchecked"})
@Pandora(
      description = "Fluent UI service for interacting with modals: open/close checks, retrievals "
            + "and validations.",
      tags = {"ui", "fluent", "modal"},
      creation = CreationKind.PROVIDED
)
@PandoraOptions(
      exampleFilesPath = "docs/usage/roa/ui-usage.json",
      meta = {
         @PandoraOptions.Meta(key = "type", value = "fluent-service")
      }
)
public class ModalServiceFluent<T extends UiServiceFluent<?>> {

   private static final String VALIDATING_MODAL = "Validating Modal";
   private final ModalService modalService;
   private final T uiServiceFluent;
   private final Storage storage;
   private final SmartWebDriver driver;

   /**
    * Constructs a new {@code ModalServiceFluent} instance.
    *
    * @param uiServiceFluent The parent fluent UI service instance.
    * @param storage         The storage instance for storing validation results.
    * @param modalService    The modal service responsible for interacting with modals.
    * @param webDriver       The smart web driver used for interactions.
    */
   public ModalServiceFluent(T uiServiceFluent, Storage storage, ModalService modalService,
                             SmartWebDriver webDriver) {
      this.modalService = modalService;
      this.uiServiceFluent = uiServiceFluent;
      this.storage = storage;
      this.driver = webDriver;
   }

   /**
    * Checks whether the specified modal UI element is currently opened.
    *
    * @param element The {@link ModalUiElement} representing the modal to be checked.
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Evaluate whether the modal is opened and store the result in quest storage.",
         tags = {"ui", "modal"}
   )
   @PandoraOptions(
         exampleFilesPath = "docs/usage/roa/ui-usage.json"
   )
   public T isOpened(final ModalUiElement element) {
      Allure.step("[UI - Modal] Check if modal is opened");

      element.before().accept(driver);
      boolean isOpened = modalService.isOpened(element.componentType());
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), isOpened);
      return uiServiceFluent;
   }

   /**
    * Validates that the specified modal UI element is opened.
    *
    * @param element The {@link ModalUiElement} representing the modal to validate.
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Validate that the modal is opened (hard assertion).",
         tags = {"ui", "modal"}
   )
   @PandoraOptions(
         exampleFilesPath = "docs/usage/roa/ui-usage.json"
   )
   public T validateIsOpened(final ModalUiElement element) {
      return validateIsOpened(element, true, false);
   }

   /**
    * Validates that the specified modal UI element is opened, with the option to perform soft assertion.
    *
    * @param element The {@link ModalUiElement} representing the modal to validate.
    * @param soft    If true, performs a soft assertion; otherwise, performs a hard assertion.
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Validate that the modal is opened, optionally using a soft assertion.",
         tags = {"ui", "modal"}
   )
   @PandoraOptions(
         exampleFilesPath = "docs/usage/roa/ui-usage.json"
   )
   public T validateIsOpened(final ModalUiElement element,
         @Pandora(
               description = "When true, use soft assertions (don't fail immediately)."
         ) boolean soft) {
      return validateIsOpened(element, true, soft);
   }

   private T validateIsOpened(final ModalUiElement element, boolean shouldBeOpened, boolean soft) {
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

   /**
    * Validates that the specified modal UI element is closed.
    *
    * @param element The {@link ModalUiElement} representing the modal to validate.
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Validate that the modal is closed (hard assertion).",
         tags = {"ui", "modal"}
   )
   @PandoraOptions(
         exampleFilesPath = "docs/usage/roa/ui-usage.json"
   )
   public T validateIsClosed(final ModalUiElement element) {
      return validateIsOpened(element, false, false);
   }

   /**
    * Validates that the specified modal UI element is closed, with the option to perform soft assertion.
    *
    * @param element The {@link ModalUiElement} representing the modal to validate.
    * @param soft    If true, performs a soft assertion; otherwise, performs a hard assertion.
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Validate that the modal is closed, optionally using a soft assertion.",
         tags = {"ui", "modal"}
   )
   @PandoraOptions(
         exampleFilesPath = "docs/usage/roa/ui-usage.json"
   )
   public T validateIsClosed(final ModalUiElement element,
         @Pandora(
               description = "When true, use soft assertions (don't fail immediately)."
         ) boolean soft) {
      return validateIsOpened(element, false, soft);
   }

   /**
    * Clicks a button inside the specified modal UI element.
    *
    * @param element The {@link ModalUiElement} representing the modal UI component.
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Click a button inside the modal UI element and continue the fluent UI flow.",
         tags = {"ui", "modal"}
   )
   @PandoraOptions(
         exampleFilesPath = "docs/usage/roa/ui-usage.json"
   )
   public T click(final ModalUiElement element) {
      Allure.step("[UI - Modal] Click button inside the modal UI element");

      element.before().accept(driver);
      modalService.clickButton(element.componentType(), element.locator());
      element.after().accept(driver);
      return uiServiceFluent;
   }

   /**
    * Retrieves the title of the specified modal UI element.
    *
    * @param element The {@link ModalUiElement} representing the modal.
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Retrieve the modal title and store it in quest storage.",
         tags = {"ui", "modal"}
   )
   @PandoraOptions(
         exampleFilesPath = "docs/usage/roa/ui-usage.json"
   )
   public T getTitle(final ModalUiElement element) {
      Allure.step("[UI - Modal] Retrieve title of the modal UI element");

      element.before().accept(driver);
      String modalTitle = modalService.getTitle(element.componentType());
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), modalTitle);
      return uiServiceFluent;
   }

   /**
    * Validates the title of the specified modal UI element matches the expected value.
    *
    * @param element       The {@link ModalUiElement} representing the modal.
    * @param expectedValue The expected title value.
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Validate that the modal title equals the expected value (hard assertion).",
         tags = {"ui", "modal"}
   )
   @PandoraOptions(
         exampleFilesPath = "docs/usage/roa/ui-usage.json"
   )
   public T validateTitle(final ModalUiElement element,
         @Pandora(
               description = "Expected modal title."
         ) final String expectedValue) {
      Allure.step("[UI - Modal] Validate title of the modal");
      return validateTitle(element, false, expectedValue);
   }

   /**
    * Validates the title of the specified modal UI element matches the expected value,
    * with an option for soft assertion.
    *
    * @param element       The {@link ModalUiElement} representing the modal.
    * @param soft          If true, performs a soft assertion; otherwise, performs a hard assertion.
    * @param expectedValue The expected title value.
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Validate that the modal title equals the expected value, optionally using a soft assertion.",
         tags = {"ui", "modal"}
   )
   @PandoraOptions(
         exampleFilesPath = "docs/usage/roa/ui-usage.json"
   )
   public T validateTitle(final ModalUiElement element,
         @Pandora(
               description = "When true, use soft assertions (don't fail immediately)."
         ) boolean soft,
         @Pandora(
               description = "Expected modal title."
         ) final String expectedValue) {
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

   /**
    * Retrieves the content title of the specified modal UI element.
    *
    * @param element The {@link ModalUiElement} representing the modal.
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Retrieve the modal content title and store it in quest storage.",
         tags = {"ui", "modal"}
   )
   @PandoraOptions(
         exampleFilesPath = "docs/usage/roa/ui-usage.json"
   )
   public T getContentTitle(final ModalUiElement element) {
      Allure.step("[UI - Modal] Retrieve content title of the modal UI element");

      element.before().accept(driver);
      String modalContentTitle = modalService.getContentTitle(element.componentType());
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), modalContentTitle);
      return uiServiceFluent;
   }

   /**
    * Validates the content title of the specified modal UI element matches the expected value.
    *
    * @param element       The {@link ModalUiElement} representing the modal.
    * @param expectedValue The expected content title.
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Validate that the modal content title equals the expected value (hard assertion).",
         tags = {"ui", "modal"}
   )
   @PandoraOptions(
         exampleFilesPath = "docs/usage/roa/ui-usage.json"
   )
   public T validateContentTitle(final ModalUiElement element,
         @Pandora(
               description = "Expected modal content title."
         ) final String expectedValue) {
      Allure.step("[UI - Modal] Validate content title of the modal");
      return validateContentTitle(element, false, expectedValue);
   }

   /**
    * Validates the content title of the specified modal UI element matches the expected value,
    * with an option for soft assertion.
    *
    * @param element       The {@link ModalUiElement} representing the modal.
    * @param soft          If true, performs a soft assertion; otherwise, performs a hard assertion.
    * @param expectedValue The expected content title.
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Validate that the modal content title equals the expected value, optionally "
               + "using a soft assertion.",
         tags = {"ui", "modal"}
   )
   @PandoraOptions(
         exampleFilesPath = "docs/usage/roa/ui-usage.json"
   )
   public T validateContentTitle(final ModalUiElement element,
         @Pandora(
               description = "When true, use soft assertions (don't fail immediately)."
         ) boolean soft,
         @Pandora(
               description = "Expected modal content title."
         ) final String expectedValue) {
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

   /**
    * Retrieves the body text of the specified modal UI element.
    *
    * @param element The {@link ModalUiElement} representing the modal.
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Retrieve the modal body text and store it in quest storage.",
         tags = {"ui", "modal"}
   )
   @PandoraOptions(
         exampleFilesPath = "docs/usage/roa/ui-usage.json"
   )
   public T getBodyText(final ModalUiElement element) {
      Allure.step("[UI - Modal] Retrieve body text of the modal UI element");

      element.before().accept(driver);
      String modalBodyText = modalService.getBodyText(element.componentType());
      element.after().accept(driver);
      storage.sub(UI).put(element.enumImpl(), modalBodyText);
      return uiServiceFluent;
   }

   /**
    * Validates the content of the specified modal UI element matches the expected value.
    *
    * @param element      The {@link ModalUiElement} representing the modal.
    * @param expectedText The expected content.
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Validate that the modal body text equals the expected value (hard assertion).",
         tags = {"ui", "modal"}
   )
   @PandoraOptions(
         exampleFilesPath = "docs/usage/roa/ui-usage.json"
   )
   public T validateBodyText(final ModalUiElement element,
         @Pandora(
               description = "Expected modal body text."
         ) final String expectedText) {
      Allure.step("[UI - Modal] Validate content of the modal");
      return validateBodyText(element, false, expectedText);
   }

   /**
    * Validates the content of the specified modal UI element matches the expected value,
    * with an option for soft assertion.
    *
    * @param element       The {@link ModalUiElement} representing the modal.
    * @param soft          If true, performs a soft assertion; otherwise, performs a hard assertion.
    * @param expectedValue The expected content value.
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Validate that the modal body text equals the expected value, optionally "
               + "using a soft assertion.",
         tags = {"ui", "modal"}
   )
   @PandoraOptions(
         exampleFilesPath = "docs/usage/roa/ui-usage.json"
   )
   public T validateBodyText(final ModalUiElement element,
         @Pandora(
               description = "When true, use soft assertions (don't fail immediately)."
         ) boolean soft,
         @Pandora(
               description = "Expected modal body text."
         ) final String expectedValue) {
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

   /**
    * Closes the specified modal UI element.
    *
    * @param element The {@link ModalUiElement} representing the modal.
    * @return The fluent UI service instance.
    */
   @Pandora(
         description = "Close the modal UI element and continue the fluent UI flow.",
         tags = {"ui", "modal"}
   )
   @PandoraOptions(
         exampleFilesPath = "docs/usage/roa/ui-usage.json"
   )
   public T close(final ModalUiElement element) {
      Allure.step("[UI - Modal] Close the modal UI element");

      element.before().accept(driver);
      modalService.close(element.componentType());
      element.after().accept(driver);
      return uiServiceFluent;
   }
}
