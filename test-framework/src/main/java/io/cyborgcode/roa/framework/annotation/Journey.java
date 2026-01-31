package io.cyborgcode.roa.framework.annotation;

import io.cyborgcode.pandora.annotation.Pandora;
import io.cyborgcode.pandora.annotation.PandoraOptions;
import io.cyborgcode.pandora.model.CreationKind;
import io.cyborgcode.roa.framework.pandora.AvailableOptionsRules;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Defines a pre-test setup step ("journey") to be executed before the test runs.
 *
 * <p>This annotation is used to specify a predefined setup action ("journey") that should be executed
 * before a test starts. It is typically applied in combination with {@code @PreQuest}
 * to configure multiple setup steps in a defined order.
 *
 * <p>The framework processes this annotation using reflection in order to resolve the specified journey,
 * execute the corresponding setup logic, and inject any required test data.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@Repeatable(PreQuest.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Pandora(
      description = "Declares a pre-test journey (precondition) executed before the test body by resolving a "
            + "project-defined PreQuestJourney key; can be repeated and ordered, and can receive crafted "
            + "inputs via journeyData.",
      tags = {"framework", "precondition"},
      creation = CreationKind.PROVIDED
)
@PandoraOptions(
      exampleFilesPath = "docs/usage/roa/general-usage.json",
      meta = {
         @PandoraOptions.Meta(key = "type", value = "journey-annotation"),
         @PandoraOptions.Meta(key = "scope", value = "method"),
         @PandoraOptions.Meta(key = "phase", value = "before-test")
      }
)
public @interface Journey {

   /**
    * Specifies the journey to be executed before the test.
    *
    * <p>The value should match a predefined journey identifier, which the framework
    * resolves and executes to set up the necessary preconditions for the test.
    *
    * @return The name of the journey.
    */
   @Pandora(
         description = "PreQuestJourney key to execute before this test. Must match an enum constant name "
               + "from a project enum implementing PreQuestJourney (e.g., \"USER_LOGIN_PRECONDITION\")."
   )
   @PandoraOptions(
         availableOptionsRule = AvailableOptionsRules.AvailablePreQuestJourneyOptions.class
   )
   String value();

   /**
    * Defines additional test data to be used within the journey.
    *
    * <p>This allows the journey to be parameterized with specific test data objects,
    * which are resolved dynamically by the framework.
    *
    * @return An array of {@code JourneyData} objects containing test data.
    */
   JourneyData[] journeyData() default {};

   /**
    * Specifies the execution order of the journey when multiple journeys are defined.
    *
    * <p>Lower values indicate higher priority, meaning they will be executed first.
    *
    * @return The execution order of the journey.
    */
   @Pandora(
         description = "Optional execution order when multiple @Journey annotations are present; lower "
               + "values run first (default: 0)."
   )
   int order() default 0;

}
