package io.cyborgcode.roa.ui.components.select;

import io.cyborgcode.pandora.annotations.AiCompass;
import io.cyborgcode.pandora.annotations.AiCompassOptions;
import io.cyborgcode.pandora.annotations.CreationKind;
import io.cyborgcode.roa.ui.components.base.ComponentType;

/**
 * Defines a specific type of selectable (e.g. HTML 'select') component, extending {@link ComponentType}
 * to standardize identification and interaction across multiple UI automation scenarios.
 *
 * <p>Implementation classes may encapsulate various configurations or styles of select
 * widgets, ensuring that UI testing remains consistent when handling dropdowns
 * or multi-select elements within an application.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@AiCompass(
      description = "Component type marker for Select components; enums implement this to declare "
            + "available select types.",
      tags = {"ui", "ui-component-type", "select"},
      creation = CreationKind.ENUM_CONSTANT
)
@AiCompassOptions(
      meta = {
         @AiCompassOptions.Meta(key = "type", value = "ui-component-type")
      },
      implementationsOf = SelectComponentType.class
)
public interface SelectComponentType extends ComponentType {

}
