package io.cyborgcode.roa.ui.annotations;

import io.cyborgcode.roa.ui.components.base.ComponentType;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Associates a specific implementation class with a UI component type.
 *
 * <p>This annotation is used on concrete component classes to indicate
 * which {@link ComponentType} they implement, enabling dynamic instantiation
 * by the {@code ComponentFactory}.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
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
