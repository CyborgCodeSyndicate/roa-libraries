package io.cyborgcode.roa.ui.components.tab;

import io.cyborgcode.pandora.annotation.AiCompass;
import io.cyborgcode.pandora.annotation.AiCompassOptions;
import io.cyborgcode.pandora.model.CreationKind;
import io.cyborgcode.roa.ui.components.button.ButtonComponentType;
import io.cyborgcode.roa.ui.pandora.AvailableOptionsRules;

/**
 * Defines a specific type of tab component, extending {@link ButtonComponentType} to unify
 * button-based interactions with specialized tab behaviors in a UI automation framework.
 *
 * <p>Implementation classes may represent various tab styles or configurations,
 * ensuring consistent handling of tab elements across different UI designs.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@AiCompass(
      description = "Component type marker for Tab components; enums implement this to declare available tab types.",
      tags = {"ui", "ui-component-type", "tab"},
      creation = CreationKind.ENUM_CONSTANT
)
@AiCompassOptions(
      availableOptionsRule = AvailableOptionsRules.AvailableTabComponentTypes.class,
      exampleFilesPath = "docs/usage/roa/ui-usage.json",
      meta = {
         @AiCompassOptions.Meta(key = "type", value = "ui-component-type")
      }
)
public interface TabComponentType extends ButtonComponentType {
}
