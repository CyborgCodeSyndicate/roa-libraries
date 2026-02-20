package io.cyborgcode.roa.ui.components.table.annotations;

import io.cyborgcode.roa.ui.components.table.filters.CellFilterFunction;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Defines a custom filtering mechanism for a table cell.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface CustomCellFilter {

   /**
    * Specifies the filtering function used to filter table data dynamically.
    *
    * @return the class implementing {@link CellFilterFunction}.
    */
   Class<? extends CellFilterFunction<?>> cellFilterFunction();
}
