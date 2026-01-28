package io.cyborgcode.roa.ui.components.select;

import io.cyborgcode.pandora.annotation.Pandora;
import io.cyborgcode.pandora.annotation.PandoraOptions;
import io.cyborgcode.pandora.model.CreationKind;
import io.cyborgcode.roa.ui.components.base.ComponentType;
import io.cyborgcode.roa.ui.pandora.AvailableOptionsRules;

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
@Pandora(
      description = "Component type marker for Select components; enums implement this to declare "
            + "available select types.",
      tags = {"ui", "ui-component-type", "select"},
      creation = CreationKind.AUTO
)
@PandoraOptions(
      availableOptionsRule = AvailableOptionsRules.AvailableSelectComponentTypes.class,
      exampleFilesPath = "ai/roa/ui-usage.json",
      meta = {
         @PandoraOptions.Meta(key = "type", value = "ui-component-type")
      }
)
public interface SelectComponentType extends ComponentType {

}