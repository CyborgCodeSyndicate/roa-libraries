package io.cyborgcode.roa.api.authentication;

import io.cyborgcode.pandora.annotation.AiCompass;
import io.cyborgcode.pandora.annotation.AiCompassOptions;
import io.cyborgcode.pandora.model.CreationKind;
import io.cyborgcode.roa.api.service.RestService;
import lombok.NonNull;

/**
 * Defines the contract for authentication mechanisms.
 *
 * <p>Implementations of this interface handle user authentication and return an {@link AuthenticationKey}
 * that uniquely identifies authenticated sessions.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@AiCompass(
      description = "Contract for API authentication clients "
            + "used by ROA to log in users and identify authenticated sessions.",
      tags = {"api", "auth"},
      creation = CreationKind.PROVIDED
)
@AiCompassOptions(
      exampleFilesPath = "docs/usage/roa/api-usage.json",
      meta = {
         @AiCompassOptions.Meta(key = "type", value = "authentication-client")
      }
)
public interface AuthenticationClient {

   /**
    * Authenticates a user using the provided credentials.
    *
    * @param restService The {@link RestService} instance used to execute the authentication request.
    * @param username    The username of the user.
    * @param password    The password of the user.
    * @param cache       If {@code true}, the authentication result is cached for reuse.
    * @return The {@link AuthenticationKey} representing the authenticated session.
    */
   @AiCompass(
         description = "Perform authentication using username/password and return an AuthenticationKey "
               + "used to look up the cached auth header."
   )
   @AiCompassOptions(
         exampleFilesPath = "docs/usage/roa/api-usage.json"
   )
   AuthenticationKey authenticate(@NonNull RestService restService,
                                  @AiCompass(
                                        description = "Username used to authenticate the user."
                                  )
                                  @NonNull String username,
                                  @AiCompass(
                                        description = "Password used to authenticate the user."
                                  )
                                  String password,
                                  @AiCompass(
                                        description = "Whether the authentication result "
                                              + "should be cached and reused for subsequent calls."
                                  )
                                  boolean cache);
}
