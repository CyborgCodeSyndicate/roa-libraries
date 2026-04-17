package io.cyborgcode.roa.ui.components.checkbox;

import io.cyborgcode.pandora.aiteacher.annotation.AiLesson;
import io.cyborgcode.pandora.aiteacher.model.LessonLevel;
import io.cyborgcode.pandora.annotation.AiCompass;
import io.cyborgcode.pandora.annotation.AiCompassOptions;
import io.cyborgcode.pandora.model.CreationKind;
import io.cyborgcode.roa.ui.components.base.ComponentType;
import io.cyborgcode.roa.ui.pandora.AvailableOptionsRules;

/**
 * Identifies a specific type of checkbox component.
 *
 * <p>Enums implementing this interface represent distinct variations of
 * checkbox UI elements, indicating which implementation should be used at runtime.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@AiCompass(
      description = "Component type marker for Checkbox components; enums implement this to declare "
            + "available checkbox types.",
      tags = {"ui", "ui-component-type", "checkbox"},
      creation = CreationKind.ENUM_CONSTANT
)
@AiCompassOptions(
      availableOptionsRule = AvailableOptionsRules.AvailableCheckboxComponentTypes.class,
      exampleFilesPath = "docs/usage/roa/ui-usage.json",
      meta = {
         @AiCompassOptions.Meta(key = "type", value = "ui-component-type")
      }
)
@AiLesson(
      category = "model",
      level = LessonLevel.EXCELLENT,
      description = "Checkbox component type marker for enum-based implementations resolved "
            + "at runtime.",
      whenToUse = "Use as a reference when defining enum-backed checkbox types resolved from "
            + "configuration and used by services to dispatch behavior without hard-coded "
            + "implementation classes.",
      tags = {"ui", "checkbox", "component-type", "enum", "configuration", "dispatch"},
      related = {
         "io.cyborgcode.roa.ui.components.base.ComponentType",
         "io.cyborgcode.roa.ui.components.checkbox.Checkbox",
         "io.cyborgcode.roa.ui.components.checkbox.CheckboxService",
         "io.cyborgcode.roa.ui.selenium.CheckboxUiElement"
      }
)
public interface CheckboxComponentType extends ComponentType {

}
