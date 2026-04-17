package io.cyborgcode.roa.ui.parameters;

import io.cyborgcode.pandora.annotation.AiCompass;
import io.cyborgcode.pandora.annotation.AiCompassOptions;
import io.cyborgcode.pandora.model.CreationKind;

/**
 * Defines a data interception contract for UI-driven API interactions.
 *
 * <p>Implementations specify which endpoint responses to intercept by providing
 * a substring match, along with an enum constant to identify the intercept flow.
 *
 * @param <T> the enum type used to identify specific intercept implementations
 * @author Cyborg Code Syndicate 💍👨💻
 */
@AiCompass(
      description = "Interface describing how to identify UI-driven API interceptions (endpoint substring + enum id).",
      tags = {"ui", "network", "intercept", "interface"},
      creation = CreationKind.ENUM_CONSTANT
)
@AiCompassOptions(
      exampleFilesPath = "docs/usage/roa/ui-usage.json",
      meta = {
         @AiCompassOptions.Meta(key = "type", value = "ui-data-intercept-interface"),
         @AiCompassOptions.Meta(key = "scope", value = "type")
      }
)
public interface DataIntercept<T extends Enum<T>> {

   /**
    * Returns the substring used to match the target endpoint for interception.
    *
    * <p>This substring is used to filter API calls whose URL contains it.
    *
    * @return the endpoint substring to intercept
    */
   @AiCompass(
         description = "URL substring used to match which API requests should be intercepted."
   )
   String getEndpointSubString();

   /**
    * Returns the enum constant identifying this data intercept implementation.
    *
    * <p>The returned enum value is used to look up the corresponding interception logic.
    *
    * @return the enum representing this intercept
    */
   @AiCompass(
         description = "Enum identifier that maps to the specific interception flow/handler."
   )
   T enumImpl();

}
