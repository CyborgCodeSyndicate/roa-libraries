package io.cyborgcode.roa.api.annotations;

import io.cyborgcode.pandora.annotation.AiCompass;
import io.cyborgcode.pandora.annotation.AiCompassOptions;
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
@AiCompass(
      description = "Container annotation that holds multiple @ApiHook "
            + "definitions on a single test class. Normally used implicitly by Java.",
      tags = {"api", "hook", "annotation"},
      creation = CreationKind.PROVIDED
)
@AiCompassOptions(
      exampleFilesPath = "docs/usage/roa/api-usage.json",
      meta = {
         @AiCompassOptions.Meta(key = "type", value = "api-hook-container-annotation"),
         @AiCompassOptions.Meta(key = "scope", value = "class")
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
   @AiCompass(
         description = "Wrapped list of ApiHook annotations declared on the same test class."

   )
   @AiCompassOptions(
         exampleFilesPath = "docs/usage/roa/api-usage.json"
   )
   ApiHook[] value() default {};

}
