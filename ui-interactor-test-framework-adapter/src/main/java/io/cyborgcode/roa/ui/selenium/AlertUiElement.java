package io.cyborgcode.roa.ui.selenium;

import io.cyborgcode.pandora.annotation.Pandora;
import io.cyborgcode.pandora.annotation.PandoraOptions;
import io.cyborgcode.pandora.model.CreationKind;
import io.cyborgcode.roa.ui.components.alert.AlertService;
import io.cyborgcode.roa.ui.components.base.ComponentType;
import io.cyborgcode.roa.ui.pandora.AvailableOptionsUiRules;

/**
 * Represents an Alert UI element that integrates with the {@link AlertService}.
 *
 * <p>This interface extends {@link UiElement} and provides a default implementation
 * for retrieving the associated component type, allowing interaction with alert components
 * within the UI.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@Pandora(
      description = "Alert UI element enum contract (locator + component type + hooks) consumed by AlertService "
            + "for standardized alert interactions.",
      tags = {"ui", "ui-element", "alert"},
      creation = CreationKind.ENUM_CONSTANT
)
@PandoraOptions(
      availableOptionsRule = AvailableOptionsUiRules.AvailableAlertUiElements.class,
      exampleFilesPath = "docs/usage/roa/ui-usage.json",
      meta = {
         @PandoraOptions.Meta(key = "type", value = "ui-element")
      }
)
public interface AlertUiElement extends UiElement {

   /**
    * Retrieves the component type associated with this alert element.
    *
    * <p>The default implementation returns {@link AlertService#DEFAULT_TYPE},
    * ensuring that the alert element is recognized as part of the alert service.
    *
    * @param <T> The component type.
    * @return The component type associated with this alert element.
    */
   @Override
   default <T extends ComponentType> T componentType() {
      return (T) AlertService.DEFAULT_TYPE;
   }

}
