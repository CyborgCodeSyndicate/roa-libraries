package io.cyborgcode.roa.ui.components.link;

import io.cyborgcode.pandora.annotations.AiCompass;
import io.cyborgcode.pandora.annotations.AiCompassOptions;
import io.cyborgcode.pandora.annotations.CreationKind;
import io.cyborgcode.roa.ui.components.button.ButtonComponentType;

/**
 * Identifies a specific type of link component.
 *
 * <p>Enums implementing this interface represent distinct variations of
 * link UI elements, indicating which implementation should be used at runtime.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@AiCompass(
      description = "Component type marker for Link components; enums implement this to declare available link types.",
      tags = {"ui", "ui-component-type", "link"},
      creation = CreationKind.ENUM_CONSTANT
)
@AiCompassOptions(
      meta = {
         @AiCompassOptions.Meta(key = "type", value = "ui-component-type")
      },
      implementationsOf = LinkComponentType.class
)
public interface LinkComponentType extends ButtonComponentType {

}
