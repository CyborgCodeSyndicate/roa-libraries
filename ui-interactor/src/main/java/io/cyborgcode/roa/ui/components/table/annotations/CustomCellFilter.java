package io.cyborgcode.roa.ui.components.table.annotations;

import io.cyborgcode.pandora.annotations.AiCompass;
import io.cyborgcode.pandora.annotations.AiCompassOptions;
import io.cyborgcode.pandora.annotations.CreationKind;
import io.cyborgcode.roa.ui.components.table.filters.CellFilterFunction;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Defines a custom filtering mechanism for a table cell.
 * This annotation allows specifying a {@link CellFilterFunction} to handle
 * filtering logic beyond the standard component-based approach.
 *
 * <p>Applied to fields in a row model, it enables dynamic filtering using a
 * function that executes at runtime.
 *
 * <p>The provided {@code cellFilterFunction} class must implement {@code CellFilterFunction}
 * to define the filtering logic.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@AiCompass(
      description = "Annotation defining a custom filtering mechanism for a table cell using a "
            + "function implementation.",
      tags = {"ui", "annotation", "custom-cell-filter"},
      creation = CreationKind.PROVIDED
)
@AiCompassOptions(
      meta = {
         @AiCompassOptions.Meta(key = "type", value = "annotation")
      }
)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface CustomCellFilter {

   /**
    * Specifies the filtering function used to filter table data dynamically.
    *
    * @return the class implementing {@link CellFilterFunction}.
    */
   @AiCompass(
         description = "The filtering function class used to filter table data dynamically.",
         tags = {"annotation", "custom-cell-filter"}
   )
   Class<? extends CellFilterFunction> cellFilterFunction();
}
