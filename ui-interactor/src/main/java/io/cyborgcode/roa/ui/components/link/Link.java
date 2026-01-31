package io.cyborgcode.roa.ui.components.link;

import io.cyborgcode.pandora.annotation.Pandora;
import io.cyborgcode.pandora.annotation.PandoraOptions;
import io.cyborgcode.pandora.model.CreationKind;
import io.cyborgcode.roa.ui.components.button.Button;
import io.cyborgcode.roa.ui.selenium.smart.SmartWebElement;
import org.openqa.selenium.By;

/**
 * Defines operations for interacting with link components.
 *
 * <p>This interface extends {@link Button} and provides additional functionality
 * for handling link elements, including support for double-click actions.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@Pandora(
      description = "Defines operations for interacting with link components, extending button "
            + "functionality with link-specific actions like double-click.",
      tags = {"ui", "component-contract", "link"},
      creation = CreationKind.AUTO
)
@PandoraOptions(
      exampleFilesPath = "docs/usage/roa/ui-usage.json",
      meta = {
         @PandoraOptions.Meta(key = "type", value = "ui-component")
      }
)
public interface Link extends Button {

   /**
    * Performs a double-click action on a link with the specified text within the given container.
    *
    * @param container  The container holding the link.
    * @param buttonText The text of the link to be double-clicked.
    */
   @Pandora(
         description = "Performs a double-click action on a link with the specified text within the given container.",
         tags = {"component-contract", "link"}
   )
   void doubleClick(SmartWebElement container, String buttonText);

   /**
    * Performs a double-click action on a link within the specified container.
    *
    * @param container The container holding the link.
    */
   @Pandora(
         description = "Performs a double-click action on a link within the specified container.",
         tags = {"component-contract", "link"}
   )
   void doubleClick(SmartWebElement container);

   /**
    * Performs a double-click action on a link identified by its text.
    *
    * @param buttonText The text of the link to be double-clicked.
    */
   @Pandora(
         description = "Performs a double-click action on a link identified by its text.",
         tags = {"component-contract", "link"}
   )
   void doubleClick(String buttonText);

   /**
    * Performs a double-click action on a link located using the specified locator.
    *
    * @param buttonLocator The locator identifying the link.
    */
   @Pandora(
         description = "Performs a double-click action on a link located using the specified locator.",
         tags = {"component-contract", "link"}
   )
   void doubleClick(By buttonLocator);
}
