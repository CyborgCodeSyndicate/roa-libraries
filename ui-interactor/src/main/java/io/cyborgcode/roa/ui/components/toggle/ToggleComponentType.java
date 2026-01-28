package io.cyborgcode.roa.ui.components.toggle;

import io.cyborgcode.pandora.annotation.Pandora;
import io.cyborgcode.pandora.annotation.PandoraOptions;
import io.cyborgcode.pandora.model.CreationKind;
import io.cyborgcode.roa.ui.components.base.ComponentType;
import io.cyborgcode.roa.ui.pandora.AvailableOptionsRules;

/**
 * Defines a specific type of toggle component, extending {@link ComponentType} to maintain
 * a consistent approach for identifying and interacting with toggle-like elements.
 *
 * <p>Implementing classes may represent various toggle behaviors—like a checkbox,
 * a switch, or a button with ON/OFF states—ensuring uniform handling across different
 * UI designs.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@Pandora(
      description = "Component type marker for Toggle components; enums implement this to declare "
            + "available toggle types.",
      tags = {"ui", "ui-component-type", "toggle"},
      creation = CreationKind.AUTO
)
@PandoraOptions(
      availableOptionsRule = AvailableOptionsRules.AvailableToggleComponentTypes.class,
      exampleFilesPath = "ai/roa/ui-usage.json",
      meta = {
         @PandoraOptions.Meta(key = "type", value = "ui-component-type")
      }
)
public interface ToggleComponentType extends ComponentType {

}
