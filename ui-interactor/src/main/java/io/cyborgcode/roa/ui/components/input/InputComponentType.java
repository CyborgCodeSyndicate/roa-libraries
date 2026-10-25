package io.cyborgcode.roa.ui.components.input;

import io.cyborgcode.pandora.annotations.AiCompass;
import io.cyborgcode.pandora.annotations.AiCompassOptions;
import io.cyborgcode.pandora.annotations.CreationKind;
import io.cyborgcode.roa.ui.components.base.ComponentType;

/**
 * Identifies a specific type of input component.
 *
 * <p>Enums implementing this interface represent distinct variations of
 * input field UI elements, indicating which implementation should be used at runtime.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@AiCompass(
      description = "Component type marker for Input components; enums implement this to declare "
            + "available input types.",
      tags = {"ui", "ui-component-type", "input"},
      creation = CreationKind.ENUM_CONSTANT
)
@AiCompassOptions(
      meta = {
         @AiCompassOptions.Meta(key = "type", value = "ui-component-type")
      },
      implementationsOf = InputComponentType.class
)
public interface InputComponentType extends ComponentType {

}
