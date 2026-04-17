package io.cyborgcode.roa.ui.components.button;

import io.cyborgcode.pandora.annotation.AiCompass;
import io.cyborgcode.pandora.annotation.AiCompassOptions;
import io.cyborgcode.pandora.model.CreationKind;
import io.cyborgcode.roa.ui.components.base.ComponentType;
import io.cyborgcode.roa.ui.pandora.AvailableOptionsRules;

/**
 * Identifies a specific type of button component.
 *
 * <p>Enums implementing this interface represent distinct implementations
 * or frameworks for a button UI element. By implementing
 * {@link ButtonComponentType}, each enum value signals the framework
 * which button variation to use at runtime.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@AiCompass(
      description = "Component type marker for Button components; enums implement this to declare "
            + "available button types.",
      tags = {"ui", "ui-component-type", "button"},
      creation = CreationKind.ENUM_CONSTANT
)
@AiCompassOptions(
      availableOptionsRule = AvailableOptionsRules.AvailableButtonComponentTypes.class,
      exampleFilesPath = "docs/usage/roa/ui-usage.json",
      meta = {
         @AiCompassOptions.Meta(key = "type", value = "ui-component-type")
      }
)
public interface ButtonComponentType extends ComponentType {
}
