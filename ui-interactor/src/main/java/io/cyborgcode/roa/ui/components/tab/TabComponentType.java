package io.cyborgcode.roa.ui.components.tab;

import io.cyborgcode.pandora.annotation.Pandora;
import io.cyborgcode.pandora.annotation.PandoraOptions;
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
@Pandora(
      description = "Component type marker for Tab components; enums implement this to declare available tab types.",
      tags = {"ui", "ui-component-type", "tab"},
      creation = CreationKind.ENUM_CONSTANT
)
@PandoraOptions(
      availableOptionsRule = AvailableOptionsRules.AvailableTabComponentTypes.class,
      exampleFilesPath = "docs/usage/roa/ui-usage.json",
      meta = {
         @PandoraOptions.Meta(key = "type", value = "ui-component-type")
      }
)
public interface TabComponentType extends ButtonComponentType {
}
