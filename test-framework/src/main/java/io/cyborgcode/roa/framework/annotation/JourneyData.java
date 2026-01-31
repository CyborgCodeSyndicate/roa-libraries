package io.cyborgcode.roa.framework.annotation;

import io.cyborgcode.pandora.annotation.Pandora;
import io.cyborgcode.pandora.annotation.PandoraOptions;
import io.cyborgcode.pandora.model.CreationKind;
import io.cyborgcode.roa.framework.pandora.AvailableOptionsRules;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Defines test data to be used within a pre-test setup journey.
 *
 * <p>This annotation is used in combination with {@code @Journey} to specify
 * predefined test data models that should be injected during the execution
 * of a test precondition.
 *
 * <p>The framework resolves the specified data model dynamically using reflection,
 * ensuring that the appropriate test data object is provided as part of the test setup.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({})
@Pandora(
      description = "Used inside @Journey(journeyData=...) to declare which DataForge model(s) should be "
            + "crafted and passed into the journey before the test body runs.",
      tags = {"framework", "test-data"},
      creation = CreationKind.PROVIDED
)
@PandoraOptions(
      exampleFilesPath = "docs/usage/roa/general-usage.json",
      meta = {
         @PandoraOptions.Meta(key = "type", value = "journey-data-annotation")
      }
)
public @interface JourneyData {

   /**
    * Specifies the test data model to be injected.
    *
    * <p>The value should match a predefined test data identifier, which the framework
    * resolves dynamically to generate the corresponding test object.
    *
    * @return The name of the test data model.
    */
   @Pandora(
         description = "DataForge model key to craft for this journey argument. Must match an enum constant name "
               + "from a project enum implementing DataForge (e.g., \"CREATE_ORDER\")."
   )
   @PandoraOptions(
         availableOptionsRule = AvailableOptionsRules.AvailableDataForgeOptions.class
   )
   String value();

   /**
    * Indicates whether the test data should be created lazily.
    *
    * <p>If set to {@code true}, the test data object is not created immediately.
    * Instead, it remains a deferred reference and must be explicitly accessed
    * when needed.
    * <br>
    * If set to {@code false}, the object is created just before the test execution,
    * ensuring it is available during the test.
    *
    * @return {@code true} if the test data should be created lazily and requires
    *     explicit resolution before use, otherwise {@code false}.
    */
   @Pandora(
         description = "If true, the journey receives a deferred model (Late<T>) instead of an eager instance, "
               + "so creation can be triggered later inside the journey flow (default: false)."
   )
   boolean late() default false;

}
