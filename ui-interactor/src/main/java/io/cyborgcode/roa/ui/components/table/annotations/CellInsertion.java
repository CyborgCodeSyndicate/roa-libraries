package io.cyborgcode.roa.ui.components.table.annotations;

import io.cyborgcode.pandora.annotation.Pandora;
import io.cyborgcode.pandora.annotation.PandoraOptions;
import io.cyborgcode.pandora.model.CreationKind;
import io.cyborgcode.roa.ui.components.base.ComponentType;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Specifies that a table cell supports value insertion using a defined UI component.
 * This annotation is used within row model classes to automate cell interactions.
 *
 * <p>It works alongside table processing logic to determine how and where values
 * should be inserted using the specified {@link #type()} and {@link #componentType()}.
 *
 * <p>The {@code order} parameter controls execution priority when multiple insertions exist.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@Pandora(
      description = "Annotation indicating a table cell supports value insertion using a specified component type.",
      tags = {"ui", "annotation", "cell-insertion"},
      creation = CreationKind.PROVIDED
)
@PandoraOptions(
      exampleFilesPath = "docs/usage/roa/ui-usage.json",
      meta = {
         @PandoraOptions.Meta(key = "type", value = "annotation")
      }
)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface CellInsertion {

   /**
    * The component type class responsible for handling the insertion process.
    *
    * @return the class representing the component type.
    */
   @Pandora(
         description = "The UI component type class that performs the insertion.",
         tags = {"annotation", "cell-insertion"}
   )
   Class<? extends ComponentType> type();

   /**
    * The specific component type identifier for insertion.
    *
    * @return a string representing the component type.
    */
   @Pandora(
         description = "The specific component type identifier used for insertion.",
         tags = {"annotation", "cell-insertion"}
   )
   String componentType();

   /**
    * The execution order of the insertion when multiple insertions exist in a row.
    * A lower value executes earlier. Defaults to 0.
    *
    * @return the insertion order.
    */
   @Pandora(
         description = "Execution order for the insertion when multiple exist in a row (lower runs earlier).",
         tags = {"annotation", "cell-insertion"}
   )
   int order() default 0;

}
