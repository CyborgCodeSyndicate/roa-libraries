package io.cyborgcode.roa.api.annotations;

import io.cyborgcode.pandora.annotation.Pandora;
import io.cyborgcode.pandora.annotation.PandoraOptions;
import io.cyborgcode.pandora.model.CreationKind;
import io.cyborgcode.roa.api.pandora.AvailableOptionsApiAdapterRules;
import io.cyborgcode.roa.framework.hooks.HookExecution;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Defines a hook to be executed before or after all tests in a test class.
 *
 * <p>Each {@code @ApiHook} specifies a hook type (looked up via
 * {@link io.cyborgcode.utilities.reflections.ReflectionUtil#findEnumImplementationsOfInterface}),
 * the timing of execution, optional arguments to pass to the hook, and an execution order.
 * Hooks of the same timing are sorted by {@linkplain #order()} before invocation.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@Pandora(
      description = "Declares a class-level API hook flow to run before or after all tests in a JUnit class.",
      tags = {"api", "hook", "annotation"},
      creation = CreationKind.PROVIDED
)
@PandoraOptions(
      exampleFilesPath = "docs/usage/roa/api-usage.json",
      meta = {
         @PandoraOptions.Meta(key = "type", value = "api-hook-annotation"),
         @PandoraOptions.Meta(key = "scope", value = "class")
      }
)
@Repeatable(ApiHooks.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ApiHook {

   /**
    * The identifier of the hook flow implementation to invoke.
    *
    * <p>This will be passed as the {@code type} argument to
    * {@link io.cyborgcode.utilities.reflections.ReflectionUtil#findEnumImplementationsOfInterface}.
    *
    * @return the hook flow type name
    */
   @Pandora(
         description = "Identifier of the hook flow implementation to execute (typically an enum name)."
   )
   @PandoraOptions(
         exampleFilesPath = "docs/usage/roa/api-usage.json",
         availableOptionsRule = AvailableOptionsApiAdapterRules.AvailableApiHookFlows.class
   )
   String type();

   /**
    * When to execute the hook relative to the test lifecycle.
    *
    * @return {@link HookExecution#BEFORE} to run before all tests, {@link HookExecution#AFTER} to run after all tests
    */
   @Pandora(
         description = "When the hook should run relative to the test lifecycle (BEFORE or AFTER all tests)."
   )
   @PandoraOptions(
         exampleFilesPath = "docs/usage/roa/api-usage.json"
   )
   HookExecution when();

   /**
    * Optional arguments to pass into the hook flow.
    *
    * @return an array of {@code String} arguments
    */
   @Pandora(
         description = "Optional string arguments that will be passed to the hook flow."
   )
   @PandoraOptions(
         exampleFilesPath = "docs/usage/roa/api-usage.json"
   )
   String[] arguments() default {};

   /**
    * Execution order of this hook among others with the same {@linkplain #when timing}.
    *
    * <p>Hooks with lower order values run earlier.
    *
    * @return an integer defining the sort order
    */
   @Pandora(
         description = "Execution order among hooks with the same timing; lower values are executed first."
   )
   @PandoraOptions(
         exampleFilesPath = "docs/usage/roa/api-usage.json"
   )
   int order() default 0;

}
