package io.cyborgcode.roa.ui.components.button;

import io.cyborgcode.pandora.annotations.AiCompass;
import io.cyborgcode.pandora.annotations.AiCompassOptions;
import io.cyborgcode.pandora.annotations.CreationKind;
import io.cyborgcode.roa.ui.components.base.ComponentType;

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
      meta = {
         @AiCompassOptions.Meta(key = "type", value = "ui-component-type")
      },
      implementationsOf = ButtonComponentType.class
)
public interface ButtonComponentType extends ComponentType {
}
