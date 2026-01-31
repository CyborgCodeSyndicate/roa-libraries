package io.cyborgcode.roa.framework.annotation;

import io.cyborgcode.pandora.annotation.Pandora;
import io.cyborgcode.pandora.annotation.PandoraOptions;
import io.cyborgcode.pandora.model.CreationKind;
import io.cyborgcode.roa.framework.data.StaticDataProvider;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Specifies a static data provider for test execution.
 *
 * <p>This annotation is used to define a class responsible for providing static test data.
 * The specified class must extend {@code StaticDataProvider}, and its {@code staticTestData()} method
 * will be invoked to retrieve predefined data before the test execution.
 *
 * <p>The framework dynamically instantiates the data provider and retrieves the test data,
 * making it available within the test execution context.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Pandora(
      description = "Declares a static test-data provider to run before a test. "
            + "The provider is instantiated and its staticTestData() output is made available in the test context.",
      tags = {"framework", "test-data", "annotation"},
      creation = CreationKind.PROVIDED
)
@PandoraOptions(
      exampleFilesPath = "docs/usage/roa/general-usage.json",
      meta = {
         @PandoraOptions.Meta(key = "type", value = "static-test-data-annotation"),
         @PandoraOptions.Meta(key = "scope", value = "type-or-method")
      }
)
public @interface StaticTestData {

   /**
    * Specifies the data provider class that supplies static test data.
    *
    * <p>The class must extend {@code StaticDataProvider} and implement a method to return
    * predefined test data for use within the test.
    *
    * @return The class responsible for providing static test data.
    */
   @Pandora(
         description = "Concrete StaticDataProvider implementation class "
               + "that will be instantiated to supply predefined (static) test data."
   )
   Class<? extends StaticDataProvider> value();

}
