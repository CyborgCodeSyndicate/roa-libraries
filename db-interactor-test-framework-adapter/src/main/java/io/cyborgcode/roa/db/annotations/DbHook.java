package io.cyborgcode.roa.db.annotations;

import io.cyborgcode.pandora.annotation.Pandora;
import io.cyborgcode.pandora.annotation.PandoraOptions;
import io.cyborgcode.pandora.model.CreationKind;
import io.cyborgcode.roa.framework.hooks.HookExecution;
import io.cyborgcode.roa.db.hooks.DbHookFlow;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Defines a database hook to run before or after all tests in a class.
 *
 * <p>Annotate a test class with one or more {@code @DbHook} to execute
 * custom database logic (via {@link DbHookFlow})
 * at the specified lifecycle phase.
 * Hooks with the same {@linkplain #when() timing} are ordered by {@linkplain #order()}.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@Pandora(
      description = "Annotation added on a test class to define database hook logic "
            + "that runs before or after all tests in the class lifecycle.",
      tags = {"db", "hook", "annotation"},
      creation = CreationKind.PROVIDED
)
@PandoraOptions(
      meta = {
         @PandoraOptions.Meta(key = "type", value = "db-hook-annotation"),
         @PandoraOptions.Meta(key = "scope", value = "class")
      }
)
@Repeatable(DbHooks.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface DbHook {

   /**
    * When to execute this database hook relative to the test lifecycle.
    *
    * @return {
    *       @link HookExecution#BEFORE} to run before all tests;
    *       {@link HookExecution#AFTER} to run after all tests
    */
   @Pandora(
         description = "Defines whether the database hook runs before or after "
               + "all tests in the annotated test class."
   )
   HookExecution when();

   /**
    * The identifier of the hook implementation to invoke.
    *
    * <p>This value is passed into
    * {@link io.cyborgcode.utilities.reflections.ReflectionUtil#findEnumImplementationsOfInterface}
    * to locate the {@link DbHookFlow} enum.
    *
    * @return the hook type name
    */
   @Pandora(
         description = "Identifier used to locate the DbHookFlow implementation "
               + "that contains the database hook logic to execute."
   )
   String type();

   /**
    * Optional string arguments to pass to the hook logic.
    *
    * @return an array of argument strings
    */
   @Pandora(
         description = "Optional string arguments that will be passed to the "
               + "database hook logic during execution."
   )
   String[] arguments() default {};

   /**
    * Order of execution among hooks with the same {@linkplain #when timing}.
    * Lower values execute earlier.
    *
    * @return the execution order
    */
   @Pandora(
         description = "Execution order among hooks with the same lifecycle timing; "
               + "lower values execute earlier."
   )
   int order() default 0;

}
