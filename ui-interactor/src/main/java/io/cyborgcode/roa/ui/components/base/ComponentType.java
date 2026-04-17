package io.cyborgcode.roa.ui.components.base;

import io.cyborgcode.pandora.annotation.AiCompass;
import io.cyborgcode.pandora.annotation.AiCompassOptions;
import io.cyborgcode.pandora.model.CreationKind;

/**
 * Represents a UI component type.
 *
 * <p>This interface is used to define various types of UI components such as
 * buttons, checkboxes, dropdowns, accordions, and more.
 *
 * <p>Each specific component type extends this interface and provides
 * its own enumeration values representing available component variations.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@AiCompass(
      description = "Interface that classifies UI component types and exposes their enum representation.",
      tags = {"ui", "component", "interface"},
      creation = CreationKind.ENUM_CONSTANT
)
@AiCompassOptions(
      exampleFilesPath = "docs/usage/roa/ui-usage.json",
      meta = {
         @AiCompassOptions.Meta(key = "type", value = "ui-component-type-interface"),
         @AiCompassOptions.Meta(key = "scope", value = "type")
      }
)
@SuppressWarnings("java:S1452")
public interface ComponentType {

   /**
    * Retrieves the enum representation of the component type.
    *
    * <p>This method is used to determine the specific type of UI component,
    * allowing for dynamic handling within the framework.
    *
    * @return The enum value representing the component type.
    */
   @AiCompass(
         description = "Enum value representing the concrete component type (used for dynamic handling)."
   )
   Enum<?> getType();
}
