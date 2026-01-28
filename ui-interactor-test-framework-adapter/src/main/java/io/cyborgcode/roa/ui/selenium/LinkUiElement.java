package io.cyborgcode.roa.ui.selenium;

import io.cyborgcode.pandora.annotation.Pandora;
import io.cyborgcode.pandora.annotation.PandoraOptions;
import io.cyborgcode.pandora.model.CreationKind;
import io.cyborgcode.roa.ui.components.base.ComponentType;
import io.cyborgcode.roa.ui.components.link.LinkService;
import io.cyborgcode.roa.ui.pandora.AvailableOptionsRules;

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
@Pandora(
      description = "Link UI element descriptor used with the LinkService.",
      tags = {"ui", "ui-element", "link"},
      creation = CreationKind.AUTO
)
@PandoraOptions(
      availableOptionsRule = AvailableOptionsRules.AvailableLinkUiElements.class,
      exampleFilesPath = "ai/roa/ui-usage.json",
      meta = {
         @PandoraOptions.Meta(key = "type", value = "ui-element")
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
