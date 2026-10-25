package io.cyborgcode.roa.framework.hooks;

import io.cyborgcode.pandora.annotations.AiCompass;
import io.cyborgcode.pandora.annotations.AiCompassOptions;
import io.cyborgcode.pandora.annotations.CreationKind;

/**
 * Specifies the timing for hook execution relative to the test lifecycle.
 *
 * <p>Use {@link #BEFORE} to execute a hook before all tests in a class are run,
 * and {@link #AFTER} to execute a hook after all tests in a class have completed.</p>
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@AiCompass(
      description = "Defines when a hook executes relative to "
            + "the test lifecycle (before all tests or after all tests). "
            + "Used by hook annotations such as @ApiHook.when().",
      tags = {"framework", "hook"},
      creation = CreationKind.ENUM_CONSTANT
)
@AiCompassOptions(
      meta = {
         @AiCompassOptions.Meta(key = "type", value = "hook-execution"),
         @AiCompassOptions.Meta(key = "usedBy", value = "ApiHook.when")
      },
      enumType = HookExecution.class
)
public enum HookExecution {

   /**
    * Indicates that the hook should run before tests (setup phase).
    */
   @AiCompass(description = "Execute the hook before all tests in the class (setup phase).")
   BEFORE,

   /**
    * Indicates that the hook should run after tests (teardown phase).
    */
   @AiCompass(description = "Execute the hook after all tests in the class (teardown phase).")
   AFTER
}
