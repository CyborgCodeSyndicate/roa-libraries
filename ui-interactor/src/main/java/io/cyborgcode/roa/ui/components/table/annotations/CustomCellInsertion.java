package io.cyborgcode.roa.ui.components.table.annotations;

import io.cyborgcode.pandora.annotation.Pandora;
import io.cyborgcode.pandora.annotation.PandoraOptions;
import io.cyborgcode.pandora.model.CreationKind;
import io.cyborgcode.roa.ui.components.table.insertion.CellInsertionFunction;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Specifies a custom function for inserting data into a table cell.
 * This annotation allows defining a {@link CellInsertionFunction} that provides
 * a dynamic insertion mechanism instead of relying on a predefined component type.
 *
 * <p>Applied to fields in a row model, it enables executing custom logic
 * when inserting values into table cells.
 *
 * <p>The insertion function must implement {@code CellInsertionFunction}, defining
 * how data should be inserted into the cell.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@Pandora(
      description = "Annotation specifying a custom function for inserting data into a table cell.",
      tags = {"ui", "annotation", "custom-cell-insertion"},
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
public @interface CustomCellInsertion {

   /**
    * The custom function responsible for inserting values into the cell.
    *
    * @return the class implementing {@link CellInsertionFunction}.
    */
   @Pandora(
         description = "The custom function class used to insert values into the table cell.",
         tags = {"annotation", "custom-cell-insertion"}
   )
   Class<? extends CellInsertionFunction> insertionFunction();

   /**
    * Defines the execution order when multiple insertions exist within the same row.
    *
    * @return the order in which the insertion should be executed, default is 0.
    */
   @Pandora(
         description = "Execution order for the custom insertion when multiple exist in the same row. Default is 0",
         tags = {"annotation", "custom-cell-insertion"}
   )
   int order() default 0;
}
