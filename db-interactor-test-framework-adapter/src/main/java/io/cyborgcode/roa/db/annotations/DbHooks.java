package io.cyborgcode.roa.db.annotations;

import io.cyborgcode.pandora.annotation.Pandora;
import io.cyborgcode.pandora.annotation.PandoraOptions;
import io.cyborgcode.pandora.model.CreationKind;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Container annotation that allows multiple {@link DbHook} annotations
 * to be applied to a test class
 *
 * <p>This annotation is used internally by Java’s repeatable annotation mechanism;
 * you normally use {@code @DbHook} directly
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@Pandora(
      description = "Container annotation that holds multiple @DbHook definitions "
            + "declared on the same test class. Typically used implicitly by Java "
            + "to support repeatable DbHook annotations.",
      tags = {"db", "hook", "annotation"},
      creation = CreationKind.PROVIDED
)
@PandoraOptions(
      meta = {
         @PandoraOptions.Meta(key = "type", value = "db-hook-container-annotation"),
         @PandoraOptions.Meta(key = "scope", value = "class")
      }
)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface DbHooks {

   /**
    * The wrapped array of {@link DbHook} annotations
    *
    * <p>Each element represents a single {@code @DbHook} on the test class
    *
    * @return one or more DbHook annotations
    */
   @Pandora(
         description = "Wrapped list of DbHook annotations declared on the same test class."
   )
   DbHook[] value() default {};

}
