package io.cyborgcode.roa.ui.components.checkbox;

import io.cyborgcode.pandora.annotations.AiCompass;
import io.cyborgcode.pandora.annotations.AiCompassOptions;
import io.cyborgcode.pandora.annotations.CreationKind;
import io.cyborgcode.roa.ui.components.base.ComponentType;

/**
 * Identifies a specific type of checkbox component.
 *
 * <p>Enums implementing this interface represent distinct variations of
 * checkbox UI elements, indicating which implementation should be used at runtime.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@AiCompass(
      description = "Component type marker for Checkbox components; enums implement this to declare "
            + "available checkbox types.",
      tags = {"ui", "ui-component-type", "checkbox"},
      creation = CreationKind.ENUM_CONSTANT
)
@AiCompassOptions(
      meta = {
         @AiCompassOptions.Meta(key = "type", value = "ui-component-type")
      },
      implementationsOf = CheckboxComponentType.class
)
public interface CheckboxComponentType extends ComponentType {

}
