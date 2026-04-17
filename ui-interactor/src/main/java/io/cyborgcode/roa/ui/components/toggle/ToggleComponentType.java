package io.cyborgcode.roa.ui.components.toggle;

import io.cyborgcode.pandora.annotation.AiCompass;
import io.cyborgcode.pandora.annotation.AiCompassOptions;
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
@AiCompass(
      description = "Component type marker for Toggle components; enums implement this to declare "
            + "available toggle types.",
      tags = {"ui", "ui-component-type", "toggle"},
      creation = CreationKind.ENUM_CONSTANT
)
@AiCompassOptions(
      availableOptionsRule = AvailableOptionsRules.AvailableToggleComponentTypes.class,
      exampleFilesPath = "docs/usage/roa/ui-usage.json",
      meta = {
         @AiCompassOptions.Meta(key = "type", value = "ui-component-type")
      }
)
public interface ToggleComponentType extends ComponentType {

}
