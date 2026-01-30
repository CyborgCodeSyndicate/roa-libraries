package io.cyborgcode.roa.ui.annotations;

import io.cyborgcode.pandora.annotation.Pandora;
import io.cyborgcode.pandora.annotation.PandoraOptions;
import io.cyborgcode.pandora.model.CreationKind;
import io.cyborgcode.roa.ui.pandora.AvailableOptionsRules;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation for intercepting network requests during UI automation tests.
 *
 * <p>This annotation is used on test methods to specify a list of request URL substrings
 * that should be intercepted and monitored during execution. It is particularly useful
 * for verifying API calls made by the UI, debugging network interactions, and applying
 * request modifications.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@Pandora(
      description = "Method-level annotation to configure request URL substring interception during UI tests.",
      tags = {"ui", "network", "intercept", "annotation"},
      creation = CreationKind.PROVIDED
)
@PandoraOptions(
      exampleFilesPath = "ai/roa/ui-usage.json",
      meta = {
         @PandoraOptions.Meta(key = "type", value = "ui-intercept-annotation"),
         @PandoraOptions.Meta(key = "scope", value = "method")
      }
)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface InterceptRequests {

   /**
    * Specifies an array of request URL substrings to intercept.
    *
    * <p>Any request made by the UI that contains one of these substrings
    * will be intercepted during the test execution.
    *
    * @return An array of substrings representing parts of request URLs to intercept.
    */
   @Pandora(
         description = "List of URL substrings; any matching network request will be intercepted during the test."
   )
   @PandoraOptions(
         availableOptionsRule = AvailableOptionsRules.AvailableDataInterceptOptions.class,
         exampleFilesPath = "ai/roa/ui-usage.json"
   )
   String[] requestUrlSubStrings() default {};
}
