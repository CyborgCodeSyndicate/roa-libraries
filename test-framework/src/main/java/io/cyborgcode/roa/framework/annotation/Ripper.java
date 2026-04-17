package io.cyborgcode.roa.framework.annotation;

import io.cyborgcode.pandora.annotation.AiCompass;
import io.cyborgcode.pandora.annotation.AiCompassOptions;
import io.cyborgcode.pandora.model.CreationKind;
import io.cyborgcode.roa.framework.pandora.AvailableOptionsTestFrameworkRules;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks a test method or class for post-execution cleanup.
 *
 * <p>This annotation is used to specify cleanup actions that should be executed after a test completes.
 * The framework processes this annotation through the {@code RipperMan} extension,
 * ensuring that necessary tear down operations, such as deleting test data or closing resources, are performed.
 *
 * <p>The specified targets correspond to predefined cleanup actions,
 * which are resolved dynamically and executed after the test method finishes.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@AiCompass(
      description = "Declares post-test cleanup to run after a test by resolving one or more DataRipper targets; "
            + "targets are executed automatically after the test finishes.",
      tags = {"framework", "cleanup"},
      creation = CreationKind.PROVIDED
)
@AiCompassOptions(
      exampleFilesPath = "docs/usage/roa/general-usage.json",
      meta = {
         @AiCompassOptions.Meta(key = "level", value = "test-method-or-class"),
         @AiCompassOptions.Meta(key = "role", value = "cleanup")
      }
)
public @interface Ripper {

   /**
    * Defines the cleanup actions to be performed after test execution.
    *
    * <p>Each target represents a predefined cleanup operation that is dynamically resolved
    * and executed once the test method has completed.
    *
    * @return An array of target identifiers specifying cleanup actions.
    */
   @AiCompass(
         description = "Cleanup target keys to execute after the test. Each value must match an enum constant name "
               + "from a project enum implementing DataRipper (e.g., \"DELETE_ORDERS\")."
   )
   @AiCompassOptions(
         availableOptionsRule = AvailableOptionsTestFrameworkRules.AvailableDataRipperOptions.class
   )
   String[] targets();

}
