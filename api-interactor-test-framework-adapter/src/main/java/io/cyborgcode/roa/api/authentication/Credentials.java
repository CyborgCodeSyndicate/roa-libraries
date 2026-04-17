package io.cyborgcode.roa.api.authentication;

import io.cyborgcode.pandora.annotation.AiCompass;
import io.cyborgcode.pandora.annotation.AiCompassOptions;
import io.cyborgcode.pandora.model.CreationKind;

/**
 * Represents authentication credentials for API authentication.
 *
 * <p>Implementing classes provide username and password details used for authentication
 * in API requests.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@AiCompass(
      description = "Contract for API authentication credentials. "
            + "Implementations provide username/password used by API auth flows.",
      tags = {"api", "auth"},
      creation = CreationKind.PROVIDED
)
@AiCompassOptions(
      exampleFilesPath = "docs/usage/roa/api-usage.json",
      meta = {
         @AiCompassOptions.Meta(key = "type", value = "credentials-provider")
      }
)
public interface Credentials {

   /**
    * Retrieves the username associated with the credentials.
    *
    * @return The username.
    */
   @AiCompass(
         description = "Username associated with these API "
               + "credentials (usually loaded from externalized test data or secrets)."
   )
   @AiCompassOptions(
         exampleFilesPath = "docs/usage/roa/api-usage.json"
   )
   String username();

   /**
    * Retrieves the password associated with the credentials.
    *
    * @return The password.
    */
   @AiCompass(
         description = "Password associated with these API "
               + "credentials (usually loaded from externalized test data or secrets)."
   )
   @AiCompassOptions(
         exampleFilesPath = "docs/usage/roa/api-usage.json"
   )
   String password();
}
