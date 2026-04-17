package io.cyborgcode.roa.ui.components.alert;

import io.cyborgcode.pandora.annotation.AiCompass;
import io.cyborgcode.pandora.annotation.AiCompassOptions;
import io.cyborgcode.pandora.model.CreationKind;
import io.cyborgcode.roa.ui.selenium.smart.SmartWebElement;
import org.openqa.selenium.By;

/**
 * Represents a UI alert component, typically used to display messages such as
 * success, error, or informational notifications.
 *
 * <p>Provides methods to retrieve the alert’s text and check its visibility within
 * a specified container or through a direct {@link By} locator.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@AiCompass(
      description = "Interface defining the contract for an alert UI component, typically used to display "
            + "messages such as success, error, or informational notifications.",
      tags = {"ui", "component-contract", "alert"},
      creation = CreationKind.AUTO
)
@AiCompassOptions(
      exampleFilesPath = "docs/usage/roa/ui-usage.json",
      meta = {
         @AiCompassOptions.Meta(key = "type", value = "ui-component")
      }
)
public interface Alert {

   /**
    * Retrieves the alert’s text value within a container element.
    *
    * @param container The container element enclosing the alert.
    * @return The visible text of the alert.
    */
   @AiCompass(
         description = "Retrieves the alert's text value within a container element.",
         tags = {"component-contract", "alert"}
   )
   String getValue(SmartWebElement container);

   /**
    * Retrieves the alert’s text value identified by a locator.
    *
    * @param containerLocator The {@link By} locator for the alert element.
    * @return The visible text of the alert.
    */
   @AiCompass(
         description = "Retrieves the alert's text value identified by a locator.",
         tags = {"component-contract", "alert"}
   )
   String getValue(By containerLocator);

   /**
    * Checks whether the alert is visible within a container element.
    *
    * @param container The container element enclosing the alert.
    * @return {@code true} if the alert is visible; {@code false} otherwise.
    */
   @AiCompass(
         description = "Checks whether the alert is visible within a container element.",
         tags = {"component-contract", "alert"}
   )
   boolean isVisible(SmartWebElement container);

   /**
    * Checks whether the alert is visible based on a locator.
    *
    * @param containerLocator The {@link By} locator for the alert element.
    * @return {@code true} if the alert is visible; {@code false} otherwise.
    */
   @AiCompass(
         description = "Checks whether the alert is visible based on a locator.",
         tags = {"component-contract", "alert"}
   )
   boolean isVisible(By containerLocator);

}
