package io.cyborgcode.roa.ui.selenium;

import io.cyborgcode.pandora.annotation.Pandora;
import io.cyborgcode.pandora.annotation.PandoraOptions;
import io.cyborgcode.pandora.model.CreationKind;
import io.cyborgcode.roa.ui.components.base.ComponentType;
import io.cyborgcode.roa.ui.components.checkbox.CheckboxService;
import io.cyborgcode.roa.ui.pandora.AvailableOptionsRules;

/**
 * Represents a Checkbox UI element that integrates with the {@link CheckboxService}.
 *
 * <p>This interface extends {@link UiElement} and provides a default implementation
 * for retrieving the associated component type, allowing interaction with checkbox components
 * within the UI.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@Pandora(
      description = "Checkbox UI element descriptor used with the CheckboxService.",
      tags = {"ui", "element", "checkbox"},
      creation = CreationKind.ENUM_CONSTANT
)
@PandoraOptions(
      availableOptionsRule = AvailableOptionsRules.AvailableCheckboxUiElements.class,
      exampleFilesPath = "ai/roa/ui-usage.json",
      meta = {
         @PandoraOptions.Meta(key = "type", value = "ui-element")
      }
)
public interface CheckboxUiElement extends UiElement {

   /**
    * Retrieves the component type associated with this checkbox element.
    *
    * <p>The default implementation returns {@link CheckboxService#DEFAULT_TYPE},
    * ensuring that the checkbox element is recognized as part of the checkbox service.
    *
    * @param <T> The component type.
    * @return The component type associated with this checkbox element.
    */
   @Override
   default <T extends ComponentType> T componentType() {
      return (T) CheckboxService.DEFAULT_TYPE;
   }

}
