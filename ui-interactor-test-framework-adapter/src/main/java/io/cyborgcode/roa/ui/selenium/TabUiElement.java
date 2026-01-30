package io.cyborgcode.roa.ui.selenium;

import io.cyborgcode.pandora.annotation.Pandora;
import io.cyborgcode.pandora.annotation.PandoraOptions;
import io.cyborgcode.pandora.model.CreationKind;
import io.cyborgcode.roa.ui.components.base.ComponentType;
import io.cyborgcode.roa.ui.components.tab.TabService;
import io.cyborgcode.roa.ui.pandora.AvailableOptionsRules;

/**
 * Represents a UI element for tab components within the framework.
 *
 * <p>This interface extends {@link UiElement} and provides a default implementation
 * for retrieving the associated component type, ensuring that tab elements
 * can be interacted with in a standardized manner.
 *
 * <p>Tabs allow users to switch between different sections of content in a UI.
 * This interface facilitates automation of tab interactions, such as selecting,
 * validating visibility, and checking if a tab is enabled or selected.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@Pandora(
      description = "Tab UI element descriptor used with the TabService.",
      tags = {"ui", "ui-element", "tab"},
      creation = CreationKind.ENUM_CONSTANT
)
@PandoraOptions(
      availableOptionsRule = AvailableOptionsRules.AvailableTabUiElements.class,
      exampleFilesPath = "ai/roa/ui-usage.json",
      meta = {
         @PandoraOptions.Meta(key = "type", value = "ui-element")
      }
)
public interface TabUiElement extends UiElement {

   /**
    * Retrieves the component type associated with this tab element.
    *
    * <p>The default implementation returns {@link TabService#DEFAULT_TYPE},
    * ensuring that the tab element is recognized as part of the tab service.
    *
    * @param <T> The component type.
    * @return The component type associated with this tab element.
    */
   @Override
   default <T extends ComponentType> T componentType() {
      return (T) TabService.DEFAULT_TYPE;
   }

}
