package io.cyborgcode.roa.ui.selenium;

import io.cyborgcode.pandora.aiteacher.annotation.AiLesson;
import io.cyborgcode.pandora.aiteacher.model.LessonLevel;
import io.cyborgcode.pandora.annotation.AiCompass;
import io.cyborgcode.pandora.annotation.AiCompassOptions;
import io.cyborgcode.pandora.model.CreationKind;
import io.cyborgcode.roa.ui.components.base.ComponentType;
import io.cyborgcode.roa.ui.components.link.LinkService;
import io.cyborgcode.roa.ui.pandora.AvailableOptionsUiRules;

/**
 * Represents a link UI element that integrates with the {@link LinkService}.
 *
 * <p>This interface extends {@link UiElement} and provides a default implementation
 * for retrieving the associated component type, ensuring that link elements can
 * be interacted with as part of the UI automation framework.
 *
 * <p>This element allows performing actions such as clicking, verifying visibility,
 * and checking if the link is enabled.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@AiCompass(
      description = "Link UI element enum contract (locator + component type + hooks) consumed by LinkService "
            + "for standardized link interactions.",
      tags = {"ui", "ui-element", "link"},
      creation = CreationKind.ENUM_CONSTANT
)
@AiCompassOptions(
      availableOptionsRule = AvailableOptionsUiRules.AvailableLinkUiElements.class,
      exampleFilesPath = "docs/usage/roa/ui-usage.json",
      meta = {
         @AiCompassOptions.Meta(key = "type", value = "ui-element")
      }
)
@AiLesson(
      category = "model",
      level = LessonLevel.EXCELLENT,
      description = "Link UI element enum contract that models clickable navigation targets "
            + "with locators, hooks, and a default component type.",
      whenToUse = "Use as a reference when defining enum-based link elements for UI "
            + "automation that should be reusable across click, visibility, and enabled-state "
            + "flows without repeating component wiring.",
      tags = {"ui", "link", "ui-element", "enum", "locator", "navigation"},
      related = {
         "io.cyborgcode.roa.ui.selenium.UiElement",
         "io.cyborgcode.roa.ui.components.link.LinkService",
         "io.cyborgcode.roa.ui.components.link.LinkComponentType",
         "io.cyborgcode.roa.ui.service.fluent.LinkServiceFluent"
      }
)
public interface LinkUiElement extends UiElement {

   /**
    * Retrieves the component type associated with this link element.
    *
    * <p>The default implementation returns {@link LinkService#DEFAULT_TYPE},
    * ensuring that the link element is recognized as part of the link service.
    *
    * @param <T> The component type.
    * @return The component type associated with this link element.
    */
   @Override
   default <T extends ComponentType> T componentType() {
      return (T) LinkService.DEFAULT_TYPE;
   }

}
