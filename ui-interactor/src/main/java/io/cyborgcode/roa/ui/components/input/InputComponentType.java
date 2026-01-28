package io.cyborgcode.roa.ui.components.input;

import io.cyborgcode.pandora.annotation.Pandora;
import io.cyborgcode.pandora.annotation.PandoraOptions;
import io.cyborgcode.pandora.model.CreationKind;
import io.cyborgcode.roa.ui.components.base.ComponentType;
import io.cyborgcode.roa.ui.pandora.AvailableOptionsRules;

/**
 * Identifies a specific type of input component.
 *
 * <p>Enums implementing this interface represent distinct variations of
 * input field UI elements, indicating which implementation should be used at runtime.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@Pandora(
      description = "Component type marker for Input components; enums implement this to declare "
            + "available input types.",
      tags = {"ui", "ui-component-type", "input"},
      creation = CreationKind.AUTO
)
@PandoraOptions(
      availableOptionsRule = AvailableOptionsRules.AvailableInputComponentTypes.class,
      exampleFilesPath = "ai/roa/ui-usage.json",
      meta = {
         @PandoraOptions.Meta(key = "type", value = "ui-component-type")
      }
)
public interface InputComponentType extends ComponentType {

}
