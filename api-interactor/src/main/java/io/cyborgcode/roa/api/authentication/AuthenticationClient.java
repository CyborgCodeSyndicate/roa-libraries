package io.cyborgcode.roa.api.authentication;

import io.cyborgcode.pandora.annotation.Pandora;
import io.cyborgcode.pandora.annotation.PandoraOptions;
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
@Pandora(
      description = "Contract for API authentication clients "
            + "used by ROA to log in users and identify authenticated sessions.",
      tags = {"api", "auth"},
      creation = CreationKind.PROVIDED
)
@PandoraOptions(
      exampleFilesPath = "ai/roa/api-usage.json",
      meta = {
         @PandoraOptions.Meta(key = "type", value = "authentication-client")
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
   @Pandora(
         description = "Perform authentication using username/password and return an AuthenticationKey "
               + "used to look up the cached auth header."
   )
   AuthenticationKey authenticate(@NonNull RestService restService,
                                  @Pandora(
                                        description = "Username used to authenticate the user."
                                  )
                                  @NonNull String username,
                                  @Pandora(
                                        description = "Password used to authenticate the user. "
                                              + "May be null when an implementation supports "
                                              + "token-only or external credential flows."
                                  )
                                  String password,
                                  @Pandora(
                                        description = "Whether the authentication result "
                                              + "should be cached and reused for subsequent calls."
                                  )
                                  boolean cache);
}
