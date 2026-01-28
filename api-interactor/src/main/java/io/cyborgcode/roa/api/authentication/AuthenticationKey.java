package io.cyborgcode.roa.api.authentication;

import io.cyborgcode.pandora.annotation.Pandora;
import io.cyborgcode.pandora.annotation.PandoraOptions;
import io.cyborgcode.pandora.model.CreationKind;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Represents a unique authentication key for identifying authenticated sessions.
 *
 * <p>This key is used for caching authentication details and retrieving authentication headers
 * in the test automation framework.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@AllArgsConstructor
@Data
@Pandora(
      description = "Opaque key representing an authenticated session, "
            + "used to cache and look up authentication headers for API calls.",
      tags = {"api", "auth"},
      creation = CreationKind.PROVIDED
)
@PandoraOptions(
      exampleFilesPath = "ai/roa/api-usage.json",
      meta = {
         @PandoraOptions.Meta(key = "type", value = "authentication-key")
      }
)
public class AuthenticationKey {

   /**
    * The username associated with the authentication session.
    */
   @Pandora(
         description = "Username associated with this authenticated session; part of the cache key for auth headers."
   )
   private String username;

   /**
    * The password associated with the authentication session.
    */
   @Pandora(
         description = "Password (or secret) used for this authenticated session; "
               + "also part of the cache key. Tools should avoid logging this value."
   )
   private String password;

   /**
    * The type of authentication client handling this session.
    */
   @Pandora(
         description = "Concrete BaseAuthenticationClient implementation "
               + "that produced this session; differentiates auth strategies in the cache."
   )
   private Class<? extends BaseAuthenticationClient> type;
}
