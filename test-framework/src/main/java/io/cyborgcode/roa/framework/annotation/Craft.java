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
 * Annotation for injecting test data into test method parameters.
 *
 * <p>This annotation is used in test methods to automatically generate
 * and provide test data objects based on predefined models.
 * The annotated parameter is resolved dynamically by the test framework,
 * which retrieves the corresponding data creator from an appropriate implementation.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
@AiCompass(
      description = "Parameter annotation that injects crafted test data into a @Test method by resolving a "
            + "project-defined DataForge model key and creating the requested object type.",
      tags = {"framework", "test-data"},
      creation = CreationKind.PROVIDED
)
@AiCompassOptions(
      exampleFilesPath = "docs/usage/roa/general-usage.json",
      meta = {
         @AiCompassOptions.Meta(key = "type", value = "parameter-annotation")
      }
)
public @interface Craft {

   /**
    * Specifies the test data model to be injected.
    *
    * <p>The value should match a valid test data model identifier,
    * which the framework will use to generate the corresponding test object.
    *
    * @return The name of the test data model.
    */
   @AiCompass(
         description = "DataForge model key to craft for this parameter. Must match an enum constant name from a "
               + "project enum implementing DataForge (e.g., \"CREATE_ORDER\")."
   )
   @AiCompassOptions(
         availableOptionsRule = AvailableOptionsTestFrameworkRules.AvailableDataForgeOptions.class
   )
   String model();

}
