package io.cyborgcode.roa.ui.components.modal;

import io.cyborgcode.pandora.annotation.Pandora;
import io.cyborgcode.pandora.annotation.PandoraOptions;
import io.cyborgcode.pandora.model.CreationKind;
import io.cyborgcode.roa.ui.selenium.smart.SmartWebElement;
import org.openqa.selenium.By;

/**
 * Represents a modal or dialog window in a UI automation context. Provides methods for
 * checking whether the modal is open, interacting with contained buttons, retrieving
 * textual content, and closing the modal.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@Pandora(
      description = "Interface for interacting with modal dialog components. Provides methods to check "
            + "modal state, interact with buttons, and retrieve content.",
      tags = {"ui", "component-contract", "modal"},
      creation = CreationKind.AUTO
)
@PandoraOptions(
      exampleFilesPath = "docs/usage/roa/ui-usage.json",
      meta = {
         @PandoraOptions.Meta(key = "type", value = "ui-component")
      }
)
public interface Modal {

   /**
    * Checks if the modal is currently open.
    *
    * @return {@code true} if the modal is visible/active, otherwise {@code false}.
    */
   @Pandora(
         description = "Checks if the modal dialog is currently open and visible.",
         tags = {"component-contract", "modal"}
   )
   boolean isOpened();

   /**
    * Clicks a button with the specified text inside a given container.
    *
    * @param container  the container element within which the button is located.
    * @param buttonText the visible text of the button to be clicked.
    */
   @Pandora(
         description = "Clicks a button with the specified text within the given container element.",
         tags = {"component-contract", "modal"}
   )
   void clickButton(SmartWebElement container, String buttonText);

   /**
    * Clicks a button identified by its visible text, without specifying a container.
    *
    * @param buttonText the visible text of the button to be clicked.
    */
   @Pandora(
         description = "Clicks a button within the modal dialog that matches the specified text.",
         tags = {"component-contract", "modal"}
   )
   void clickButton(String buttonText);

   /**
    * Clicks a button identified by a locator.
    *
    * @param buttonLocator the locator referencing the button.
    */
   @Pandora(
         description = "Clicks a button within the modal dialog using the specified locator.",
         tags = {"component-contract", "modal"}
   )
   void clickButton(By buttonLocator);

   /**
    * Retrieves the main title text of the modal.
    *
    * @return the modal's title text, or an empty string if not present.
    */
   @Pandora(
         description = "Retrieves the main title text of the modal dialog.",
         tags = {"component-contract", "modal"}
   )
   String getTitle();

   /**
    * Retrieves the body text of the modal.
    *
    * @return the main textual content of the modal, or an empty string if not present.
    */
   @Pandora(
         description = "Retrieves the main body text content of the modal dialog.",
         tags = {"component-contract", "modal"}
   )
   String getBodyText();

   /**
    * Retrieves the content title, which may be separate from the main title, depending on the modal structure.
    *
    * @return the content's title, or an empty string if not present.
    */
   @Pandora(
         description = "Retrieves the content title of the modal, which may be separate from the main title.",
         tags = {"component-contract", "modal"}
   )
   String getContentTitle();

   /**
    * Closes or dismisses the modal.
    *
    * <p>Typically performs an action such as clicking a close button or ESC key simulation,
    * depending on the specific modal implementation.
    */
   @Pandora(
         description = "Closes the modal dialog by clicking its close button or using a close action.",
         tags = {"component-contract", "modal"}
   )
   void close();

}
