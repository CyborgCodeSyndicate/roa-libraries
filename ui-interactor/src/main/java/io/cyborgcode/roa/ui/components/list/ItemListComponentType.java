package io.cyborgcode.roa.ui.components.list;

import io.cyborgcode.pandora.annotation.Pandora;
import io.cyborgcode.pandora.annotation.PandoraOptions;
import io.cyborgcode.pandora.model.CreationKind;
import io.cyborgcode.roa.ui.components.base.ComponentType;
import io.cyborgcode.roa.ui.pandora.AvailableOptionsRules;

/**
 * Defines a specific type of list-based component. Implementing classes specify the
 * unique characteristics or identification details for item list components.
 * This interface extends {@link ComponentType}, indicating that any item list
 * component type must also meet the contract defined for general components.
 *
 * <p>Implementation classes will typically be used to label or categorize
 * different kinds of item list components for use in a UI automation framework.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@Pandora(
      description = "Component type marker for List components; enums implement this to declare available list types.",
      tags = {"ui", "ui-component-type", "list"},
      creation = CreationKind.ENUM_CONSTANT
)
@PandoraOptions(
      availableOptionsRule = AvailableOptionsRules.AvailableListComponentTypes.class,
      exampleFilesPath = "docs/usage/roa/ui-usage.json",
      meta = {
         @PandoraOptions.Meta(key = "type", value = "ui-component-type")
      }
)
public interface ItemListComponentType extends ComponentType {

}
