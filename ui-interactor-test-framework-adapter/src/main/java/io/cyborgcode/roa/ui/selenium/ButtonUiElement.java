package io.cyborgcode.roa.ui.selenium;

import io.cyborgcode.pandora.annotation.Pandora;
import io.cyborgcode.pandora.annotation.PandoraOptions;
import io.cyborgcode.pandora.model.CreationKind;
import io.cyborgcode.roa.ui.components.base.ComponentType;
import io.cyborgcode.roa.ui.components.button.ButtonService;
import io.cyborgcode.roa.ui.pandora.AvailableOptionsRules;

/**
 * Represents a Button UI element that integrates with the {@link ButtonService}.
 *
 * <p>This interface extends {@link UiElement} and provides a default implementation
 * for retrieving the associated component type, allowing interaction with button components
 * within the UI.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@Pandora(
      description = "Button UI element descriptor used with the ButtonService.",
      tags = {"ui", "ui-element", "button"},
      creation = CreationKind.ENUM_CONSTANT
)
@PandoraOptions(
      availableOptionsRule = AvailableOptionsRules.AvailableButtonUiElements.class,
      exampleFilesPath = "ai/roa/ui-usage.json",
      meta = {
         @PandoraOptions.Meta(key = "type", value = "ui-element")
      }
)
public interface ButtonUiElement extends UiElement {

   /**
    * Retrieves the component type associated with this button element.
    *
    * <p>The default implementation returns {@link ButtonService#DEFAULT_TYPE},
    * ensuring that the button element is recognized as part of the button service.
    *
    * @param <T> The component type.
    * @return The component type associated with this button element.
    */
   @Override
   default <T extends ComponentType> T componentType() {
      return (T) ButtonService.DEFAULT_TYPE;
   }

}
