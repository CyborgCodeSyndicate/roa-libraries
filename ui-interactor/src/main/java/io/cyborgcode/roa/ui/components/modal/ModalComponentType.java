package io.cyborgcode.roa.ui.components.modal;

import io.cyborgcode.pandora.annotation.Pandora;
import io.cyborgcode.pandora.annotation.PandoraOptions;
import io.cyborgcode.pandora.model.CreationKind;
import io.cyborgcode.roa.ui.components.base.ComponentType;
import io.cyborgcode.roa.ui.pandora.AvailableOptionsRules;

/**
 * Defines a specific type of modal component, extending {@link ComponentType} to ensure
 * a standardized approach for identifying and interacting with modal dialog elements
 * in a UI automation framework.
 *
 * <p>Implementation classes can represent different modal styles, enabling
 * consistent handling across various dialog presentations within the application.
 *
 * <p>This interface helps group modal attributes and identification logic,
 * ensuring that all modals are managed under a common contract.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@Pandora(
      description = "Component type marker for Modal components; enums implement this to declare "
            + "available modal types.",
      tags = {"ui", "ui-component-type", "modal"},
      creation = CreationKind.AUTO
)
@PandoraOptions(
      availableOptionsRule = AvailableOptionsRules.AvailableModalComponentTypes.class,
      exampleFilesPath = "ai/roa/ui-usage.json",
      meta = {
         @PandoraOptions.Meta(key = "type", value = "ui-component-type")
      }
)
public interface ModalComponentType extends ComponentType {
}
