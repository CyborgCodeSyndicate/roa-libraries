package io.cyborgcode.roa.ui.components.loader;

import io.cyborgcode.pandora.annotation.Pandora;
import io.cyborgcode.pandora.annotation.PandoraOptions;
import io.cyborgcode.pandora.model.CreationKind;
import io.cyborgcode.roa.ui.components.base.ComponentType;
import io.cyborgcode.roa.ui.pandora.AvailableOptionsRules;

/**
 * Defines a specific type of loader component, extending {@link ComponentType} to ensure
 * compatibility with the broader UI automation framework. Implementations typically
 * represent different loader or spinner elements.
 *
 * <p>By categorizing loader elements, automation can consistently identify and interact
 * with various loading indicators throughout the UI.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@Pandora(
      description = "Component type marker for Loader components; enums implement this to declare "
            + "available loader types.",
      tags = {"ui", "ui-component-type", "loader"},
      creation = CreationKind.ENUM_CONSTANT
)
@PandoraOptions(
      availableOptionsRule = AvailableOptionsRules.AvailableLoaderComponentTypes.class,
      exampleFilesPath = "docs/usage/roa/ui-usage.json",
      meta = {
         @PandoraOptions.Meta(key = "type", value = "ui-component-type")
      }
)
public interface LoaderComponentType extends ComponentType {
}
