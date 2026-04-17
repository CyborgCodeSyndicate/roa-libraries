package io.cyborgcode.roa.ui.selenium;

import io.cyborgcode.pandora.aiteacher.annotation.AiLesson;
import io.cyborgcode.pandora.aiteacher.model.LessonLevel;
import io.cyborgcode.pandora.annotation.AiCompass;
import io.cyborgcode.pandora.annotation.AiCompassOptions;
import io.cyborgcode.pandora.model.CreationKind;
import io.cyborgcode.roa.ui.components.base.ComponentType;
import io.cyborgcode.roa.ui.components.select.SelectService;
import io.cyborgcode.roa.ui.pandora.AvailableOptionsUiRules;

/**
 * Represents a UI element for dropdown/select components within the framework.
 *
 * <p>This interface extends {@link UiElement} and provides a default implementation
 * for retrieving the associated component type, ensuring that dropdown elements
 * can be interacted with in a standardized manner.
 *
 * <p>Dropdown/select elements allow users to choose from multiple options. This interface
 * provides automation capabilities for selecting, retrieving, and validating options
 * within dropdown menus.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@AiCompass(
      description = "Select UI element enum contract (locator + component type + hooks) consumed by SelectService "
            + "for standardized select interactions.",
      tags = {"ui", "ui-element", "select"},
      creation = CreationKind.ENUM_CONSTANT
)
@AiCompassOptions(
      availableOptionsRule = AvailableOptionsUiRules.AvailableSelectUiElements.class,
      exampleFilesPath = "docs/usage/roa/ui-usage.json",
      meta = {
         @AiCompassOptions.Meta(key = "type", value = "ui-element")
      }
)
@AiLesson(
      category = "model",
      level = LessonLevel.EXCELLENT,
      description = "Select UI element enum contract that shows how dropdown fields are "
            + "declared for the fluent UI layer with shared locator metadata.",
      whenToUse = "Use as a reference when defining enum-based select fields that must work "
            + "with option selection, validation, and retrieval flows while delegating "
            + "dropdown behavior to the select service.",
      tags = {"ui", "select", "ui-element", "enum", "locator", "dropdown"},
      related = {
         "io.cyborgcode.roa.ui.selenium.UiElement",
         "io.cyborgcode.roa.ui.components.select.SelectService",
         "io.cyborgcode.roa.ui.components.select.SelectComponentType",
         "io.cyborgcode.roa.ui.service.fluent.SelectServiceFluent"
      }
)
public interface SelectUiElement extends UiElement {

   /**
    * Retrieves the component type associated with this select element.
    *
    * <p>The default implementation returns {@link SelectService#DEFAULT_TYPE},
    * ensuring that the select element is recognized as part of the select service.
    *
    * @param <T> The component type.
    * @return The component type associated with this select element.
    */
   @Override
   default <T extends ComponentType> T componentType() {
      return (T) SelectService.DEFAULT_TYPE;
   }

}
