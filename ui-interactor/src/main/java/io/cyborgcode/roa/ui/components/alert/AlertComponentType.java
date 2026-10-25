package io.cyborgcode.roa.ui.components.alert;

import io.cyborgcode.pandora.annotations.AiCompass;
import io.cyborgcode.pandora.annotations.AiCompassOptions;
import io.cyborgcode.pandora.annotations.CreationKind;
import io.cyborgcode.roa.ui.components.base.ComponentType;

/**
 * Identifies a specific variant of an alert component.
 *
 * <p>Enums implementing this interface map to concrete {@link Alert}
 * implementations in the UI framework.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@AiCompass(
      description = "Component type marker for Alert components; enums implement this to declare "
            + "available alert types.",
      tags = {"ui", "ui-component-type", "alert"},
      creation = CreationKind.ENUM_CONSTANT
)
@AiCompassOptions(
      meta = {
         @AiCompassOptions.Meta(key = "type", value = "ui-component-type")
      },
      implementationsOf = AlertComponentType.class
)
public interface AlertComponentType extends ComponentType {

}
