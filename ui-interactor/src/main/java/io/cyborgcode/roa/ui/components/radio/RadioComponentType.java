package io.cyborgcode.roa.ui.components.radio;

import io.cyborgcode.pandora.annotation.Pandora;
import io.cyborgcode.pandora.annotation.PandoraOptions;
import io.cyborgcode.pandora.model.CreationKind;
import io.cyborgcode.roa.ui.components.base.ComponentType;
import io.cyborgcode.roa.ui.pandora.AvailableOptionsRules;

/**
 * Defines a specific type of radio button component, extending {@link ComponentType} to
 * standardize the identification and interaction with radio buttons or radio groups.
 *
 * <p>Implementation classes can represent various styles or configurations of radio inputs,
 * ensuring that UI automation remains consistent when handling different radio button setups
 * across an application.
 *
 * <p>This interface helps categorize radio components by their specific characteristics,
 * allowing automation to handle them uniformly.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@Pandora(
      description = "Component type marker for Radio components; enums implement this to declare "
            + "available radio types.",
      tags = {"ui", "ui-component-type", "radio"},
      creation = CreationKind.AUTO
)
@PandoraOptions(
      availableOptionsRule = AvailableOptionsRules.AvailableRadioComponentTypes.class,
      exampleFilesPath = "ai/roa/ui-usage.json",
      meta = {
         @PandoraOptions.Meta(key = "type", value = "ui-component-type")
      }
)
public interface RadioComponentType extends ComponentType {

}