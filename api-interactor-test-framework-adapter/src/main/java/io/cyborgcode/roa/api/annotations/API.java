package io.cyborgcode.roa.api.annotations;

import io.cyborgcode.pandora.annotation.Pandora;
import io.cyborgcode.pandora.annotation.PandoraOptions;
import io.cyborgcode.pandora.model.CreationKind;
import io.cyborgcode.roa.api.extensions.ApiHookExtension;
import io.cyborgcode.roa.api.extensions.ApiTestExtension;
import io.cyborgcode.roa.framework.annotation.FrameworkAdapter;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 * Marks a test class as an API test.
 *
 * <p>This annotation enables API testing by applying the {@link ApiTestExtension} and
 * integrating API-related framework functionalities. It ensures that necessary API
 * configurations, authentication mechanisms, and request handling are properly managed
 * during test execution.
 *
 * <p>Applying this annotation to a test class automatically enables API-specific features
 * such as API request execution, response validation, and authentication handling.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@Pandora(
      description = "Marks a JUnit test class as an API test and wires ROA's API extensions and configuration.",
      tags = {"api", "annotation"},
      creation = CreationKind.PROVIDED
)
@PandoraOptions(
      exampleFilesPath = "ai/roa/api-usage.json",
      meta = {
         @PandoraOptions.Meta(key = "type", value = "api-annotation"),
         @PandoraOptions.Meta(key = "scope", value = "class")
      }
)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@FrameworkAdapter(basePackages = {"io.cyborgcode.roa.api"})
@ExtendWith({ApiTestExtension.class, ApiHookExtension.class})
@SuppressWarnings("checkstyle:AbbreviationAsWordInName")
public @interface API {

}
