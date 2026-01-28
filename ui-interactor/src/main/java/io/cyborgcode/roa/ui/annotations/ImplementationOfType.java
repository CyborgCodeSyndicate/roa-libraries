package io.cyborgcode.roa.ui.annotations;

import io.cyborgcode.pandora.annotation.Pandora;
import io.cyborgcode.pandora.annotation.PandoraOptions;
import io.cyborgcode.pandora.model.CreationKind;
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
@Pandora(
      description = "Annotation linking an implementation class to a logical UI ComponentType identifier.",
      tags = {"ui", "annotation", "implementation-of-type"},
      creation = CreationKind.PROVIDED
)
@PandoraOptions(
      exampleFilesPath = "ai/roa/ui-usage.json",
      meta = {
         @PandoraOptions.Meta(key = "type", value = "annotation")
      }
)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ImplementationOfType {

   /**
    * Specifies the component type this implementation corresponds to.
    *
    * @return The type identifier for the UI component.
    */
   String value();
}
