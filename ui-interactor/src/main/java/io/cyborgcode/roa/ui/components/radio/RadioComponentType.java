package io.cyborgcode.roa.ui.components.radio;

import io.cyborgcode.pandora.annotations.AiCompass;
import io.cyborgcode.pandora.annotations.AiCompassOptions;
import io.cyborgcode.pandora.annotations.CreationKind;
import io.cyborgcode.roa.ui.components.base.ComponentType;

/**
 * Defines a specific type of radio button component, extending {@link ComponentType} to
 * standardize the identification and interaction with radio buttons or radio groups.
 *
 * <p>Implementation classes can represent various styles or configurations of radio inputs,
 * ensuring that UI automation remains consistent when handling different radio button setups
 * across an application.
 *
 * <p>This interface helps categorize radio components by their specific characteristics,
 * allowing automation to handle them uniformly.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@AiCompass(
      description = "Component type marker for Radio components; enums implement this to declare "
            + "available radio types.",
      tags = {"ui", "ui-component-type", "radio"},
      creation = CreationKind.ENUM_CONSTANT
)
@AiCompassOptions(
      meta = {
         @AiCompassOptions.Meta(key = "type", value = "ui-component-type")
      },
      implementationsOf = RadioComponentType.class
)
public interface RadioComponentType extends ComponentType {

}
