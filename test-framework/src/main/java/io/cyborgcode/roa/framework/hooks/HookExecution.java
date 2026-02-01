package io.cyborgcode.roa.framework.hooks;

import io.cyborgcode.pandora.annotation.Pandora;
import io.cyborgcode.pandora.annotation.PandoraOptions;
import io.cyborgcode.pandora.model.CreationKind;
import io.cyborgcode.roa.framework.pandora.AvailableOptionsTestFrameworkRules;

/**
 * Specifies the timing for hook execution relative to the test lifecycle.
 *
 * <p>Use {@link #BEFORE} to execute a hook before all tests in a class are run,
 * and {@link #AFTER} to execute a hook after all tests in a class have completed.</p>
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@Pandora(
      description = "Defines when a hook executes relative to "
            + "the test lifecycle (before all tests or after all tests). "
            + "Used by hook annotations such as @ApiHook.when().",
      tags = {"framework", "hook"},
      creation = CreationKind.ENUM_CONSTANT
)
@PandoraOptions(
      exampleFilesPath = "docs/usage/roa/general-usage.json",
      availableOptionsRule = AvailableOptionsTestFrameworkRules.AvailableHookExecutionOptions.class,
      meta = {
         @PandoraOptions.Meta(key = "type", value = "hook-execution"),
         @PandoraOptions.Meta(key = "usedBy", value = "ApiHook.when")
      }
)
public enum HookExecution {

   /**
    * Indicates that the hook should run before tests (setup phase).
    */
   @Pandora(description = "Execute the hook before all tests in the class (setup phase).")
   BEFORE,

   /**
    * Indicates that the hook should run after tests (teardown phase).
    */
   @Pandora(description = "Execute the hook after all tests in the class (teardown phase).")
   AFTER
}
