package io.cyborgcode.roa.ui.components.loader;

import io.cyborgcode.pandora.annotations.AiCompass;
import io.cyborgcode.pandora.annotations.AiCompassOptions;
import io.cyborgcode.pandora.annotations.CreationKind;
import io.cyborgcode.roa.ui.components.base.ComponentType;

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
@AiCompass(
      description = "Component type marker for Loader components; enums implement this to declare "
            + "available loader types.",
      tags = {"ui", "ui-component-type", "loader"},
      creation = CreationKind.ENUM_CONSTANT
)
@AiCompassOptions(
      meta = {
         @AiCompassOptions.Meta(key = "type", value = "ui-component-type")
      },
      implementationsOf = LoaderComponentType.class
)
public interface LoaderComponentType extends ComponentType {
}
