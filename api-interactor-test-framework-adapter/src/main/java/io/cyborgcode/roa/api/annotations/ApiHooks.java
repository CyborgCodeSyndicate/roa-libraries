package io.cyborgcode.roa.api.annotations;

import io.cyborgcode.pandora.annotation.Pandora;
import io.cyborgcode.pandora.annotation.PandoraOptions;
import io.cyborgcode.pandora.model.CreationKind;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Container annotation that allows multiple {@link ApiHook} annotations
 * to be applied to a test class.
 *
 * <p>This annotation is used internally by Java’s repeatable annotation mechanism;
 * you normally use {@code @ApiHook} directly.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@Pandora(
      description = "Container annotation that holds multiple @ApiHook "
            + "definitions on a single test class. Normally used implicitly by Java.",
      tags = {"api", "hook", "annotation"},
      creation = CreationKind.PROVIDED
)
@PandoraOptions(
      exampleFilesPath = "ai/roa/api-usage.json",
      meta = {
         @PandoraOptions.Meta(key = "type", value = "api-hook-container-annotation"),
         @PandoraOptions.Meta(key = "scope", value = "class")
      }
)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ApiHooks {

   /**
    * The wrapped array of {@link ApiHook} annotations.
    *
    * @return one or more ApiHook annotations
    */
   @Pandora(
         description = "Wrapped list of ApiHook annotations declared on the same test class."
   )
   ApiHook[] value() default {};

}
