package io.cyborgcode.roa.ui.components.link;

import io.cyborgcode.pandora.annotation.Pandora;
import io.cyborgcode.pandora.annotation.PandoraOptions;
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
@Pandora(
      description = "Component type marker for Link components; enums implement this to declare available link types.",
      tags = {"ui", "ui-component-type", "link"},
      creation = CreationKind.AUTO
)
@PandoraOptions(
      availableOptionsRule = AvailableOptionsRules.AvailableLinkComponentTypes.class,
      exampleFilesPath = "ai/roa/ui-usage.json",
      meta = {
         @PandoraOptions.Meta(key = "type", value = "ui-component-type")
      }
)
public interface LinkComponentType extends ButtonComponentType {

}
