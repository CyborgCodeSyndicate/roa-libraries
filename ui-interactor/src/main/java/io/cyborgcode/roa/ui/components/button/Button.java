package io.cyborgcode.roa.ui.components.button;

import io.cyborgcode.pandora.annotation.Pandora;
import io.cyborgcode.pandora.annotation.PandoraOptions;
import io.cyborgcode.pandora.model.CreationKind;
import io.cyborgcode.roa.ui.selenium.smart.SmartWebElement;
import org.openqa.selenium.By;

/**
 * Represents a UI button component with various ways to locate and interact
 * with buttons in a web interface.
 *
 * <p>Provides methods for clicking a button by text label, container reference,
 * or locator, as well as checking if the button is enabled or visible.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@Pandora(
      description = "Interface defining the contract for a button UI component. Provides methods for "
            + "interacting with buttons in a web interface, including clicking by text, container "
            + "reference, or locator, and checking button states.",
      tags = {"ui", "component-contract", "button"},
      creation = CreationKind.AUTO
)
@PandoraOptions(
      exampleFilesPath = "docs/usage/roa/ui-usage.json",
      meta = {
         @PandoraOptions.Meta(key = "type", value = "ui-component")
      }
)
public interface Button {

   /**
    * Clicks a button identified by text, within a given container.
    *
    * @param container  The container holding the button.
    * @param buttonText The text label of the button to click.
    */
   @Pandora(
         description = "Clicks a button identified by its text within a specified container.",
         tags = {"component-contract", "button"}
   )
   void click(SmartWebElement container, String buttonText);

   /**
    * Clicks a button within the given container, selecting the first or only
    * button found in that container.
    *
    * @param container The container holding the button.
    */
   @Pandora(
         description = "Clicks the first button found within the specified container.",
         tags = {"component-contract", "button"}
   )
   void click(SmartWebElement container);

   /**
    * Clicks a button identified by its text label, at the top level (no container).
    *
    * @param buttonText The text label of the button to click.
    */
   @Pandora(
         description = "Clicks a button identified by its text label at the top level of the page.",
         tags = {"component-contract", "button"}
   )
   void click(String buttonText);

   /**
    * Clicks a button identified by a specific locator.
    *
    * @param buttonLocator A {@link By} locator for the button element.
    */
   @Pandora(
         description = "Clicks a button identified by a specific locator.",
         tags = {"component-contract", "button"}
   )
   void click(By buttonLocator);

   /**
    * Checks if a button (by text) inside the specified container is enabled.
    *
    * @param container  The container holding the button.
    * @param buttonText The text label of the button.
    * @return {@code true} if the button is enabled; {@code false} otherwise.
    */
   @Pandora(
         description = "Checks if a button with the specified text within a container is enabled.",
         tags = {"component-contract", "button"}
   )
   boolean isEnabled(SmartWebElement container, String buttonText);

   /**
    * Checks if the only or first button inside the specified container is enabled.
    *
    * @param container The container holding the button.
    * @return {@code true} if the button is enabled; {@code false} otherwise.
    */
   @Pandora(
         description = "Checks if the first button found within a container is enabled.",
         tags = {"component-contract", "button"}
   )
   boolean isEnabled(SmartWebElement container);

   /**
    * Checks if a button identified by text is enabled at the top level (no container).
    *
    * @param buttonText The text label of the button.
    * @return {@code true} if the button is enabled; {@code false} otherwise.
    */
   @Pandora(
         description = "Checks if a button with the specified text is enabled at the top level of the page.",
         tags = {"component-contract", "button"}
   )
   boolean isEnabled(String buttonText);

   /**
    * Checks if a button identified by a locator is enabled.
    *
    * @param buttonLocator A {@link By} locator for the button element.
    * @return {@code true} if the button is enabled; {@code false} otherwise.
    */
   @Pandora(
         description = "Checks if a button identified by a locator is enabled.",
         tags = {"component-contract", "button"}
   )
   boolean isEnabled(By buttonLocator);

   /**
    * Checks if a button (by text) inside the specified container is visible on the page.
    *
    * @param container  The container holding the button.
    * @param buttonText The text label of the button.
    * @return {@code true} if the button is visible; {@code false} otherwise.
    */
   @Pandora(
         description = "Checks if a button with the specified text within a container is visible.",
         tags = {"component-contract", "button"}
   )
   boolean isVisible(SmartWebElement container, String buttonText);

   /**
    * Checks if the only or first button inside the specified container is visible on the page.
    *
    * @param container The container holding the button.
    * @return {@code true} if the button is visible; {@code false} otherwise.
    */
   @Pandora(
         description = "Checks if the first button found within a container is visible.",
         tags = {"component-contract", "button"}
   )
   boolean isVisible(SmartWebElement container);

   /**
    * Checks if a button identified by text is visible at the top level (no container).
    *
    * @param buttonText The text label of the button.
    * @return {@code true} if the button is visible; {@code false} otherwise.
    */
   @Pandora(
         description = "Checks if a button with the specified text is visible at the top level of the page.",
         tags = {"component-contract", "button"}
   )
   boolean isVisible(String buttonText);

   /**
    * Checks if a button identified by a locator is visible.
    *
    * @param buttonLocator A {@link By} locator for the button element.
    * @return {@code true} if the button is visible; {@code false} otherwise.
    */
   @Pandora(
         description = "Checks if a button identified by a locator is visible.",
         tags = {"component-contract", "button"}
   )
   boolean isVisible(By buttonLocator);

   /**
    * Optionally clicks an element within a table cell or container; default no-op.
    *
    * <p>Overridden by implementations if cell-level actions for buttons are required.
    *
    * @param cell The cell element that may contain a button.
    */
   @Pandora(
         description = "Optionally clicks an element within a table cell or container.",
         tags = {"component-contract", "button"}
   )
   default void clickElementInCell(SmartWebElement cell) {
   }
}
