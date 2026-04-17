package io.cyborgcode.roa.ui.selenium;

import io.cyborgcode.pandora.aiteacher.annotation.AiLesson;
import io.cyborgcode.pandora.aiteacher.model.LessonLevel;
import io.cyborgcode.pandora.annotation.AiCompass;
import io.cyborgcode.pandora.annotation.AiCompassOptions;
import io.cyborgcode.pandora.model.CreationKind;
import io.cyborgcode.roa.ui.components.base.ComponentType;
import io.cyborgcode.roa.ui.components.input.InputService;
import io.cyborgcode.roa.ui.pandora.AvailableOptionsUiRules;

/**
 * Represents an Input UI element that integrates with the {@link InputService}.
 *
 * <p>This interface extends {@link UiElement} and provides a default implementation
 * for retrieving the associated component type, allowing interaction with input fields
 * within the UI.
 *
 * <p>This element can be used for performing actions such as inserting text, clearing
 * input fields, and validating their values.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@AiCompass(
      description = "Input UI element enum contract (locator + component type + hooks) consumed by InputService "
            + "for standardized input interactions.",
      tags = {"ui", "ui-element", "input"},
      creation = CreationKind.ENUM_CONSTANT
)
@AiCompassOptions(
      availableOptionsRule = AvailableOptionsUiRules.AvailableInputUiElements.class,
      exampleFilesPath = "docs/usage/roa/ui-usage.json",
      meta = {
         @AiCompassOptions.Meta(key = "type", value = "ui-element")
      }
)
@AiLesson(
      category = "model",
      level = LessonLevel.EXCELLENT,
      description = "Input UI element enum contract that shows how text fields are exposed to "
            + "the fluent UI layer through locators, hooks, and a default component type.",
      whenToUse = "Use as a reference when defining enum-based input fields that plug into "
            + "input services, support UiElement hooks, and avoid repeating component type "
            + "configuration for each field.",
      tags = {"ui", "input", "ui-element", "enum", "locator", "insertion"},
      related = {
         "io.cyborgcode.roa.ui.selenium.UiElement",
         "io.cyborgcode.roa.ui.components.input.InputService",
         "io.cyborgcode.roa.ui.components.input.InputComponentType",
         "io.cyborgcode.roa.ui.service.fluent.InputServiceFluent"
      }
)
public interface InputUiElement extends UiElement {

   /**
    * Retrieves the component type associated with this input element.
    *
    * <p>The default implementation returns {@link InputService#DEFAULT_TYPE},
    * ensuring that the input element is recognized as part of the input service.
    *
    * @param <T> The component type.
    * @return The component type associated with this input element.
    */
   @Override
   default <T extends ComponentType> T componentType() {
      return (T) InputService.DEFAULT_TYPE;
   }

}
