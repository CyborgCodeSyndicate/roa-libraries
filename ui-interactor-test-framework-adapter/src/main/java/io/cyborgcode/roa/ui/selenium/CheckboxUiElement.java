package io.cyborgcode.roa.ui.selenium;

import io.cyborgcode.pandora.aiteacher.annotation.AiLesson;
import io.cyborgcode.pandora.aiteacher.model.LessonLevel;
import io.cyborgcode.pandora.annotation.AiCompass;
import io.cyborgcode.pandora.annotation.AiCompassOptions;
import io.cyborgcode.pandora.model.CreationKind;
import io.cyborgcode.roa.ui.components.base.ComponentType;
import io.cyborgcode.roa.ui.components.checkbox.CheckboxService;
import io.cyborgcode.roa.ui.pandora.AvailableOptionsUiRules;

/**
 * Represents a Checkbox UI element that integrates with the {@link CheckboxService}.
 *
 * <p>This interface extends {@link UiElement} and provides a default implementation
 * for retrieving the associated component type, allowing interaction with checkbox components
 * within the UI.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@AiCompass(
      description = "Checkbox UI element enum contract (locator + component type + hooks) consumed by CheckboxService "
            + "for standardized checkbox interactions.",
      tags = {"ui", "element", "checkbox"},
      creation = CreationKind.ENUM_CONSTANT
)
@AiCompassOptions(
      availableOptionsRule = AvailableOptionsUiRules.AvailableCheckboxUiElements.class,
      exampleFilesPath = "docs/usage/roa/ui-usage.json",
      meta = {
         @AiCompassOptions.Meta(key = "type", value = "ui-element")
      }
)
@AiLesson(
      category = "model",
      level = LessonLevel.EXCELLENT,
      description = "Checkbox UI element enum contract that binds locator metadata and hooks "
            + "to the checkbox service while defaulting the component type.",
      whenToUse = "Use as a reference when creating enum-based checkbox fields for the fluent "
            + "UI layer, especially when each field should inherit the default component type "
            + "and be consumable by services without extra wiring.",
      tags = {"ui", "checkbox", "ui-element", "enum", "locator", "default-type"},
      related = {
         "io.cyborgcode.roa.ui.selenium.UiElement",
         "io.cyborgcode.roa.ui.components.checkbox.CheckboxService",
         "io.cyborgcode.roa.ui.components.checkbox.CheckboxComponentType",
         "io.cyborgcode.roa.ui.service.fluent.CheckboxServiceFluent"
      }
)
public interface CheckboxUiElement extends UiElement {

   /**
    * Retrieves the component type associated with this checkbox element.
    *
    * <p>The default implementation returns {@link CheckboxService#DEFAULT_TYPE},
    * ensuring that the checkbox element is recognized as part of the checkbox service.
    *
    * @param <T> The component type.
    * @return The component type associated with this checkbox element.
    */
   @Override
   default <T extends ComponentType> T componentType() {
      return (T) CheckboxService.DEFAULT_TYPE;
   }

}
