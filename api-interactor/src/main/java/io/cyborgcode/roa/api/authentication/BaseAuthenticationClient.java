package io.cyborgcode.roa.api.authentication;

import io.cyborgcode.pandora.annotation.Pandora;
import io.cyborgcode.pandora.annotation.PandoraOptions;
import io.cyborgcode.pandora.model.CreationKind;
import io.cyborgcode.roa.api.log.LogApi;
import io.cyborgcode.roa.api.service.RestService;
import io.restassured.http.Header;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.NonNull;

/**
 * Abstract base class for authentication clients.
 *
 * <p>Provides a caching mechanism for authentication headers and defines
 * a template method for implementing authentication logic.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@Pandora(
      description = "Base implementation of AuthenticationClient that "
            + "adds caching for authentication headers and a template method for the actual login call.",
      tags = {"api", "auth"},
      creation = CreationKind.PROVIDED
)
@PandoraOptions(
      exampleFilesPath = "ai/roa/api-usage.json",
      meta = {
         @PandoraOptions.Meta(key = "type", value = "authentication-client-base")
      }
)
public abstract class BaseAuthenticationClient implements AuthenticationClient {

   /**
    * Stores authentication headers mapped by their corresponding authentication keys.
    */
   protected static final Map<AuthenticationKey, Header> userAuthenticationHeaderMap = new ConcurrentHashMap<>();

   /**
    * Authenticates a user and caches the authentication header if caching is enabled.
    *
    * @param restService The {@code RestService} instance handling the authentication request.
    * @param username    The username for authentication.
    * @param password    The password for authentication.
    * @param cache       Whether to cache the authentication header.
    * @return The generated {@code AuthenticationKey}.
    */
   @Override
   @Pandora(
         description = "Authenticate a user and optionally cache "
               + "the resulting auth header under the returned AuthenticationKey."
   )
   public AuthenticationKey authenticate(final @NonNull RestService restService,
                                         @Pandora(
                                               description = "Username used for authenticating the user."
                                         )
                                         @NonNull final String username,
                                         @Pandora(
                                               description = "Password used for authenticating the user."
                                         ) final String password,
                                         @Pandora(
                                               description = "If true, reuse a cached authentication header "
                                                     + "when available; otherwise always perform a fresh login."
                                         )
                                         boolean cache) {
      AuthenticationKey authenticationKey = new AuthenticationKey(username, password, this.getClass());

      if (!cache) {
         Header header = authenticateImpl(restService, username, password);
         userAuthenticationHeaderMap.put(authenticationKey, header);
         LogApi.info("Successfully authenticated user: {}", username);
      } else {
         userAuthenticationHeaderMap.computeIfAbsent(authenticationKey, key -> {
            Header header = authenticateImpl(restService, username, password);
            LogApi.info("Successfully authenticated user: {}", username);
            return header;
         });
      }
      return authenticationKey;
   }

   /**
    * Retrieves the authentication header associated with the given authentication key.
    *
    * @param authenticationKey The authentication key identifying the session.
    * @return The corresponding authentication header, or {@code null} if not found.
    */
   @Pandora(
         description = "Retrieve the cached authentication header "
               + "for a previously authenticated session identified by AuthenticationKey."
   )
   public Header getAuthentication(final AuthenticationKey authenticationKey) {
      if (authenticationKey == null) {
         LogApi.error("AuthenticationKey is null. Cannot retrieve authentication header.");
         throw new IllegalArgumentException("AuthenticationKey cannot be null.");
      }
      return userAuthenticationHeaderMap.get(authenticationKey);
   }

   /**
    * Performs the actual authentication process and returns the authentication header.
    *
    * @param restService The {@code RestService} instance handling the request.
    * @param username    The username for authentication.
    * @param password    The password for authentication.
    * @return The authentication header containing credentials.
    */
   @Pandora(
         description = "Template method to be implemented by concrete "
               + "authentication clients; performs the real login call and returns the auth header."
   )
   protected abstract Header authenticateImpl(RestService restService,
                                              @Pandora(
                                                    description = "Username passed into the login request."
                                              )
                                              String username,
                                              @Pandora(
                                                    description = "Password passed into the login request."
                                              )
                                              String password);
}
