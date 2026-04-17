package io.cyborgcode.roa.ui.components.table.base;

import io.cyborgcode.pandora.annotation.AiCompass;
import io.cyborgcode.pandora.annotation.AiCompassOptions;
import io.cyborgcode.pandora.model.CreationKind;
import io.cyborgcode.roa.ui.components.base.ComponentType;
import io.cyborgcode.roa.ui.pandora.AvailableOptionsRules;

/**
 * Represents a marker interface for table-related component types.
 * This interface extends {@link ComponentType} and is used to classify
 * various table component types within the framework.
 *
 * <p>Implementations of this interface define specific table behaviors,
 * such as filtering, sorting, and data insertion.
 *
 * <p>Typically used in conjunction with {@code TableService} and
 * {@code TableElement} to manage different table types dynamically.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@AiCompass(
      description = "Component type marker for Table components; enums implement this to declare "
            + "available table types.",
      tags = {"ui", "ui-component-type", "table"},
      creation = CreationKind.ENUM_CONSTANT
)
@AiCompassOptions(
      availableOptionsRule = AvailableOptionsRules.AvailableTableComponentTypes.class,
      exampleFilesPath = "docs/usage/roa/ui-usage.json",
      meta = {
         @AiCompassOptions.Meta(key = "type", value = "ui-component-type")
      }
)
public interface TableComponentType extends ComponentType {
}
