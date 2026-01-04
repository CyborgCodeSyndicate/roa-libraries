package io.cyborgcode.roa.ui.selenium;

import io.cyborgcode.pandora.annotation.Pandora;
import io.cyborgcode.pandora.annotation.PandoraOptions;
import io.cyborgcode.pandora.model.CreationKind;
import io.cyborgcode.roa.ui.components.base.ComponentType;
import io.cyborgcode.roa.ui.components.modal.ModalService;
import io.cyborgcode.roa.ui.pandora.AvailableOptionsRules;

/**
 * Represents a UI element for modal dialogs within the framework.
 *
 * <p>This interface extends {@link UiElement} and provides a default implementation
 * for retrieving the associated component type, ensuring that modal elements can
 * be interacted with in a standardized manner.
 *
 * <p>Modal dialogs are commonly used for confirmations, warnings, and additional
 * information overlays. This interface enables automation for handling modals
 * efficiently.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@Pandora(
      description = "Modal UI element descriptor used with the ModalService.",
      tags = {"ui", "ui-element", "modal"},
      creation = CreationKind.AUTO
)
@PandoraOptions(
      availableOptionsRule = AvailableOptionsRules.AvailableModalUiElements.class,
      exampleFilesPath = "ai/roa/ui-usage.json",
      meta = {
         @PandoraOptions.Meta(key = "type", value = "ui-element")
      }
)
public interface ModalUiElement extends UiElement {

   /**
    * Retrieves the component type associated with this modal element.
    *
    * <p>The default implementation returns {@link ModalService#DEFAULT_TYPE},
    * ensuring that the modal element is recognized as part of the modal service.
    *
    * @param <T> The component type.
    * @return The component type associated with this modal element.
    */
   @Override
   default <T extends ComponentType> T componentType() {
      return (T) ModalService.DEFAULT_TYPE;
   }

}
