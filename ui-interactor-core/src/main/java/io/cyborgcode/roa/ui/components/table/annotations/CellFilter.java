package io.cyborgcode.roa.ui.components.table.annotations;

import io.cyborgcode.roa.ui.components.base.ComponentType;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates that the annotated field should be used as a filterable column or property in a table.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface CellFilter {

   /**
    * Specifies the UI component type that will handle filtering for this field.
    *
    * @return the class representing the component type.
    */
   Class<? extends ComponentType> type();

   /**
    * Defines the component type identifier to distinguish which specific filter mechanism to apply.
    *
    * @return a string matching a known component type identifier.
    */
   String componentType();
}
