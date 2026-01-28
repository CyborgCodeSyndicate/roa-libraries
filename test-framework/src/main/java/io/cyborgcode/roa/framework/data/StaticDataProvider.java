package io.cyborgcode.roa.framework.data;

import io.cyborgcode.pandora.annotation.Pandora;
import io.cyborgcode.pandora.annotation.PandoraOptions;
import io.cyborgcode.pandora.model.CreationKind;
import java.util.Map;

/**
 * Defines a contract for providing static test data.
 *
 * <p>Implementing classes must define a method that returns a predefined
 * set of key-value pairs to be used as test data during execution.
 *
 * <p>This interface is primarily used with annotations that specify static
 * data providers, allowing the framework to dynamically load and inject
 * test data before test execution.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@Pandora(
      description = "Contract for providing static key-value test "
            + "data that the framework loads before test execution. "
            + "Used together with @StaticTestData to dynamically "
            + "instantiate a provider and inject its data into the test context.",
      tags = {"framework", "test-data"},
      creation = CreationKind.PROVIDED
)
@PandoraOptions(
      meta = {
         @PandoraOptions.Meta(key = "type", value = "static-data-provider"),
         @PandoraOptions.Meta(key = "usedBy", value = "StaticTestData")
      }
)
public interface StaticDataProvider {

   /**
    * Provides a set of predefined static test data.
    *
    * <p>The returned map contains key-value pairs representing test data
    * that can be accessed during test execution.
    *
    * @return A map containing static test data.
    */
   @Pandora(
         description = "Returns a map of static test data entries "
               + "(key-value pairs) that will be loaded into the test context."
   )
   Map<String, Object> staticTestData();

}
