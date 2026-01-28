package io.cyborgcode.roa.ui.selenium;

import io.cyborgcode.pandora.annotation.Pandora;
import io.cyborgcode.pandora.annotation.PandoraOptions;
import io.cyborgcode.pandora.model.CreationKind;
import io.cyborgcode.roa.ui.components.base.ComponentType;
import io.cyborgcode.roa.ui.components.select.SelectService;
import io.cyborgcode.roa.ui.pandora.AvailableOptionsRules;

/**
 * Represents a UI element for dropdown/select components within the framework.
 *
 * <p>This interface extends {@link UiElement} and provides a default implementation
 * for retrieving the associated component type, ensuring that dropdown elements
 * can be interacted with in a standardized manner.
 *
 * <p>Dropdown/select elements allow users to choose from multiple options. This interface
 * provides automation capabilities for selecting, retrieving, and validating options
 * within dropdown menus.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@Pandora(
      description = "Select UI element descriptor used with the SelectService.",
      tags = {"ui", "ui-element", "select"},
      creation = CreationKind.AUTO
)
@PandoraOptions(
      availableOptionsRule = AvailableOptionsRules.AvailableSelectUiElements.class,
      exampleFilesPath = "ai/roa/ui-usage.json",
      meta = {
         @PandoraOptions.Meta(key = "type", value = "ui-element")
      }
)
public interface SelectUiElement extends UiElement {

   /**
    * Retrieves the component type associated with this select element.
    *
    * <p>The default implementation returns {@link SelectService#DEFAULT_TYPE},
    * ensuring that the select element is recognized as part of the select service.
    *
    * @param <T> The component type.
    * @return The component type associated with this select element.
    */
   @Override
   default <T extends ComponentType> T componentType() {
      return (T) SelectService.DEFAULT_TYPE;
   }

}
