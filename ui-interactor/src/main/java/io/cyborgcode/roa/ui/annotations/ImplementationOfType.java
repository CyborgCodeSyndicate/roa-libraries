package io.cyborgcode.roa.ui.annotations;

import io.cyborgcode.pandora.annotation.AiCompass;
import io.cyborgcode.pandora.annotation.AiCompassOptions;
import io.cyborgcode.pandora.model.CreationKind;
import io.cyborgcode.roa.ui.pandora.AvailableOptionsRules;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Defines an implementation type for UI components.
 *
 * <p>This annotation is used to associate a specific implementation class
 * with a predefined UI component type. It allows the framework to
 * dynamically instantiate the correct component implementation based
 * on its type definition.
 *
 * <p>Commonly used in UI component implementations such as alerts, checkboxes,
 * dropdowns, and other interactive elements.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@AiCompass(
      description = "Annotation linking an implementation class to a logical UI ComponentType identifier.",
      tags = {"ui", "annotation", "implementation-of-type"},
      creation = CreationKind.PROVIDED
)
@AiCompassOptions(
      exampleFilesPath = "docs/usage/roa/ui-usage.json",
      meta = {
         @AiCompassOptions.Meta(key = "type", value = "annotation")
      }
)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ImplementationOfType {

   /**
    * Identifier that binds this concrete component implementation to a logical {@code ComponentType}.
    * ROA matches this value against the project-declared component type (typically an enum constant name or a
    * shared key used by {@code *ComponentType} registries) to resolve and instantiate the correct implementation
    * at runtime; the match must be exact.
    *
    * @return the component-type identifier for this implementation
    */
   @AiCompass(
         description = "ComponentType identifier key used by ROA to resolve this implementation at runtime.",
         tags = {"ui", "annotation", "component-type", "resolution"},
         creation = CreationKind.PROVIDED
   )
   @AiCompassOptions(
         availableOptionsRule = AvailableOptionsRules.AvailableComponentTypes.class,
         exampleFilesPath = "docs/usage/roa/ui-usage.json"
   )
   String value();
}
