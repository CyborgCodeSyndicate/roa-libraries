package io.cyborgcode.roa.ui.components.link;

import io.cyborgcode.pandora.annotation.AiCompass;
import io.cyborgcode.pandora.annotation.AiCompassOptions;
import io.cyborgcode.pandora.model.CreationKind;
import io.cyborgcode.roa.ui.components.button.ButtonComponentType;
import io.cyborgcode.roa.ui.pandora.AvailableOptionsRules;

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
      availableOptionsRule = AvailableOptionsRules.AvailableLinkComponentTypes.class,
      exampleFilesPath = "docs/usage/roa/ui-usage.json",
      meta = {
         @AiCompassOptions.Meta(key = "type", value = "ui-component-type")
      }
)
public interface LinkComponentType extends ButtonComponentType {

}
