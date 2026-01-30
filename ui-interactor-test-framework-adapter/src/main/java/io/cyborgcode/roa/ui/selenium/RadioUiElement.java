package io.cyborgcode.roa.ui.selenium;

import io.cyborgcode.pandora.annotation.Pandora;
import io.cyborgcode.pandora.annotation.PandoraOptions;
import io.cyborgcode.pandora.model.CreationKind;
import io.cyborgcode.roa.ui.components.base.ComponentType;
import io.cyborgcode.roa.ui.components.radio.RadioService;
import io.cyborgcode.roa.ui.pandora.AvailableOptionsRules;

/**
 * Represents a UI element for radio button components within the framework.
 *
 * <p>This interface extends {@link UiElement} and provides a default implementation
 * for retrieving the associated component type, ensuring that radio buttons can
 * be interacted with in a standardized manner.
 *
 * <p>Radio buttons are commonly used in forms to allow users to select a single option
 * from a predefined set. This interface enables automation for selecting, validating,
 * and interacting with radio buttons efficiently.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@Pandora(
      description = "Radio UI element descriptor used with the RadioService.",
      tags = {"ui", "ui-element", "radio"},
      creation = CreationKind.ENUM_CONSTANT
)
@PandoraOptions(
      availableOptionsRule = AvailableOptionsRules.AvailableRadioUiElements.class,
      exampleFilesPath = "ai/roa/ui-usage.json",
      meta = {
         @PandoraOptions.Meta(key = "type", value = "ui-element")
      }
)
public interface RadioUiElement extends UiElement {

   /**
    * Retrieves the component type associated with this radio button element.
    *
    * <p>The default implementation returns {@link RadioService#DEFAULT_TYPE},
    * ensuring that the radio button element is recognized as part of the radio button service.
    *
    * @param <T> The component type.
    * @return The component type associated with this radio button element.
    */
   @Override
   default <T extends ComponentType> T componentType() {
      return (T) RadioService.DEFAULT_TYPE;
   }

}
