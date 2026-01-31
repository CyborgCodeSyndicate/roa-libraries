package io.cyborgcode.roa.ui.components.button;

import io.cyborgcode.pandora.annotation.Pandora;
import io.cyborgcode.pandora.annotation.PandoraOptions;
import io.cyborgcode.pandora.model.CreationKind;
import io.cyborgcode.roa.ui.components.base.ComponentType;
import io.cyborgcode.roa.ui.pandora.AvailableOptionsRules;

/**
 * Identifies a specific type of button component.
 *
 * <p>Enums implementing this interface represent distinct implementations
 * or frameworks for a button UI element. By implementing
 * {@link ButtonComponentType}, each enum value signals the framework
 * which button variation to use at runtime.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@Pandora(
      description = "Component type marker for Button components; enums implement this to declare "
            + "available button types.",
      tags = {"ui", "ui-component-type", "button"},
      creation = CreationKind.ENUM_CONSTANT
)
@PandoraOptions(
      availableOptionsRule = AvailableOptionsRules.AvailableButtonComponentTypes.class,
      exampleFilesPath = "docs/usage/roa/ui-usage.json",
      meta = {
         @PandoraOptions.Meta(key = "type", value = "ui-component-type")
      }
)
public interface ButtonComponentType extends ComponentType {
}
