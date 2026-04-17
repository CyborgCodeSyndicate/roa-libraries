package io.cyborgcode.roa.ui.components.alert;

import io.cyborgcode.pandora.annotation.AiCompass;
import io.cyborgcode.pandora.annotation.AiCompassOptions;
import io.cyborgcode.pandora.model.CreationKind;
import io.cyborgcode.roa.ui.components.base.ComponentType;
import io.cyborgcode.roa.ui.pandora.AvailableOptionsRules;

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
      availableOptionsRule = AvailableOptionsRules.AvailableAlertComponentTypes.class,
      exampleFilesPath = "docs/usage/roa/ui-usage.json",
      meta = {
         @AiCompassOptions.Meta(key = "type", value = "ui-component-type")
      }
)
public interface AlertComponentType extends ComponentType {

}
