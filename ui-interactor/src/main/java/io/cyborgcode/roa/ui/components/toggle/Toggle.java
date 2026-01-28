package io.cyborgcode.roa.ui.components.toggle;

import io.cyborgcode.pandora.annotation.Pandora;
import io.cyborgcode.pandora.annotation.PandoraOptions;
import io.cyborgcode.pandora.model.CreationKind;
import io.cyborgcode.roa.ui.selenium.smart.SmartWebElement;
import org.openqa.selenium.By;

/**
 * Represents a toggle switch in a UI automation context.
 * Provides methods to activate or deactivate the toggle, and to check if it is enabled
 * or currently activated.
 *
 * <p>Implementations typically use Selenium-based interactions with container elements
 * ({@link SmartWebElement}) or direct locators ({@link By}). Whether toggles are styled
 * like classic checkboxes or custom toggle switches, this interface provides a standard
 * contract for testing or simulating user interactions.
 *
 * <p>This interface can handle toggles identified by text, container references, or direct
 * locators, making it flexible for a wide range of UI designs.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@Pandora(
      description = "Interface contract for toggle components, providing methods to activate/deactivate "
            + "and verify enabled/activated states.",
      tags = {"ui", "component-contract", "toggle"},
      creation = CreationKind.AUTO
)
@PandoraOptions(
      exampleFilesPath = "ai/roa/ui-usage.json",
      meta = {
         @PandoraOptions.Meta(key = "type", value = "ui-component")
      }
)
public interface Toggle {

   /**
    * Activates the toggle identified by text within the specified container.
    *
    * @param container  the container holding the toggle.
    * @param toggleText the text identifying the toggle to activate.
    */
   @Pandora(
         description = "Activates the toggle identified by text within the specified container.",
         tags = {"component-contract", "toggle"}
   )
   void activate(SmartWebElement container, String toggleText);

   /**
    * Activates the toggle identified by text without referencing a container.
    *
    * @param toggleText the text identifying the toggle to activate.
    */
   @Pandora(
         description = "Activates the toggle identified by text (no container).",
         tags = {"component-contract", "toggle"}
   )
   void activate(String toggleText);

   /**
    * Activates the toggle identified by a locator.
    *
    * @param toggleLocator the locator referencing the toggle to activate.
    */
   @Pandora(
         description = "Activates the toggle identified by a locator.",
         tags = {"component-contract", "toggle"}
   )
   void activate(By toggleLocator);

   /**
    * Deactivates the toggle identified by text within the specified container.
    *
    * @param container  the container holding the toggle.
    * @param toggleText the text identifying the toggle to deactivate.
    */
   @Pandora(
         description = "Deactivates the toggle identified by text within the specified container.",
         tags = {"component-contract", "toggle"}
   )
   void deactivate(SmartWebElement container, String toggleText);

   /**
    * Deactivates the toggle identified by text without referencing a container.
    *
    * @param toggleText the text identifying the toggle to deactivate.
    */
   @Pandora(
         description = "Deactivates the toggle identified by text (no container).",
         tags = {"component-contract", "toggle"}
   )
   void deactivate(String toggleText);

   /**
    * Deactivates the toggle identified by a locator.
    *
    * @param toggleLocator the locator referencing the toggle to deactivate.
    */
   @Pandora(
         description = "Deactivates the toggle identified by a locator.",
         tags = {"component-contract", "toggle"}
   )
   void deactivate(By toggleLocator);

   /**
    * Checks if the toggle, identified by text within a container, is enabled.
    *
    * @param container  the container holding the toggle.
    * @param toggleText the text identifying the toggle to check.
    * @return true if the toggle is enabled, otherwise false.
    */
   @Pandora(
         description = "Checks if the toggle identified by text is enabled within the specified container.",
         tags = {"component-contract", "toggle"}
   )
   boolean isEnabled(SmartWebElement container, String toggleText);

   /**
    * Checks if the toggle, identified by text, is enabled without referencing a container.
    *
    * @param toggleText the text identifying the toggle to check.
    * @return true if the toggle is enabled, otherwise false.
    */
   @Pandora(
         description = "Checks if the toggle identified by text is enabled (no container).",
         tags = {"component-contract", "toggle"}
   )
   boolean isEnabled(String toggleText);

   /**
    * Checks if the toggle, identified by a locator, is enabled.
    *
    * @param toggleLocator the locator referencing the toggle to check.
    * @return true if the toggle is enabled, otherwise false.
    */
   @Pandora(
         description = "Checks if the toggle identified by a locator is enabled.",
         tags = {"component-contract", "toggle"}
   )
   boolean isEnabled(By toggleLocator);

   /**
    * Checks if the toggle, identified by text within a container, is currently activated.
    *
    * @param container  the container holding the toggle.
    * @param toggleText the text identifying the toggle to check.
    * @return true if the toggle is activated, otherwise false.
    */
   @Pandora(
         description = "Checks if the toggle identified by text is activated within the specified container.",
         tags = {"component-contract", "toggle"}
   )
   boolean isActivated(SmartWebElement container, String toggleText);

   /**
    * Checks if the toggle, identified by text, is currently activated without referencing a container.
    *
    * @param toggleText the text identifying the toggle to check.
    * @return true if the toggle is activated, otherwise false.
    */
   @Pandora(
         description = "Checks if the toggle identified by text is activated (no container).",
         tags = {"component-contract", "toggle"}
   )
   boolean isActivated(String toggleText);

   /**
    * Checks if the toggle, identified by a locator, is currently activated.
    *
    * @param toggleLocator the locator referencing the toggle to check.
    * @return true if the toggle is activated, otherwise false.
    */
   @Pandora(
         description = "Checks if the toggle identified by a locator is activated.",
         tags = {"component-contract", "toggle"}
   )
   boolean isActivated(By toggleLocator);

}
