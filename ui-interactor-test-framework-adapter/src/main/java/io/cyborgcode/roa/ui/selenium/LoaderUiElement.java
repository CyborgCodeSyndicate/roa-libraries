package io.cyborgcode.roa.ui.selenium;

import io.cyborgcode.pandora.annotation.Pandora;
import io.cyborgcode.pandora.annotation.PandoraOptions;
import io.cyborgcode.pandora.model.CreationKind;
import io.cyborgcode.roa.ui.components.base.ComponentType;
import io.cyborgcode.roa.ui.components.loader.LoaderService;
import io.cyborgcode.roa.ui.pandora.AvailableOptionsRules;

/**
 * Represents a UI element for loaders within the framework.
 *
 * <p>This interface extends {@link UiElement} and provides a default implementation
 * for retrieving the associated component type, ensuring that loader elements can
 * be interacted with in a standardized manner.
 *
 * <p>Loaders are commonly used to indicate ongoing processing or waiting states.
 * This interface allows interaction with such elements for validation and automation.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@Pandora(
      description = "Loader UI element descriptor used with the LoaderService.",
      tags = {"ui", "ui-element", "loader"},
      creation = CreationKind.ENUM_CONSTANT
)
@PandoraOptions(
      availableOptionsRule = AvailableOptionsRules.AvailableLoaderUiElements.class,
      exampleFilesPath = "ai/roa/ui-usage.json",
      meta = {
         @PandoraOptions.Meta(key = "type", value = "ui-element")
      }
)
public interface LoaderUiElement extends UiElement {

   /**
    * Retrieves the component type associated with this loader element.
    *
    * <p>The default implementation returns {@link LoaderService#DEFAULT_TYPE},
    * ensuring that the loader element is recognized as part of the loader service.
    *
    * @param <T> The component type.
    * @return The component type associated with this loader element.
    */
   @Override
   default <T extends ComponentType> T componentType() {
      return (T) LoaderService.DEFAULT_TYPE;
   }

}
