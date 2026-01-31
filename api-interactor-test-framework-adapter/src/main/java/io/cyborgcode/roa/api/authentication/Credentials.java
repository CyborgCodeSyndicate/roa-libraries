package io.cyborgcode.roa.api.authentication;

import io.cyborgcode.pandora.annotation.Pandora;
import io.cyborgcode.pandora.annotation.PandoraOptions;
import io.cyborgcode.pandora.model.CreationKind;

/**
 * Represents authentication credentials for API authentication.
 *
 * <p>Implementing classes provide username and password details used for authentication
 * in API requests.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@Pandora(
      description = "Contract for API authentication credentials. "
            + "Implementations provide username/password used by API auth flows.",
      tags = {"api", "auth"},
      creation = CreationKind.PROVIDED
)
@PandoraOptions(
      exampleFilesPath = "docs/usage/roa/api-usage.json",
      meta = {
         @PandoraOptions.Meta(key = "type", value = "credentials-provider")
      }
)
public interface Credentials {

   /**
    * Retrieves the username associated with the credentials.
    *
    * @return The username.
    */
   @Pandora(
         description = "Username associated with these API "
               + "credentials (usually loaded from externalized test data or secrets)."
   )
   @PandoraOptions(
         exampleFilesPath = "docs/usage/roa/api-usage.json"
   )
   String username();

   /**
    * Retrieves the password associated with the credentials.
    *
    * @return The password.
    */
   @Pandora(
         description = "Password associated with these API "
               + "credentials (usually loaded from externalized test data or secrets)."
   )
   @PandoraOptions(
         exampleFilesPath = "docs/usage/roa/api-usage.json"
   )
   String password();
}
