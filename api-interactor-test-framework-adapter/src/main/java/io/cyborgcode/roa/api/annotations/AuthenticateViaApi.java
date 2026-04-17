package io.cyborgcode.roa.api.annotations;

import io.cyborgcode.pandora.annotation.AiCompass;
import io.cyborgcode.pandora.annotation.AiCompassOptions;
import io.cyborgcode.pandora.model.CreationKind;
import io.cyborgcode.roa.api.authentication.BaseAuthenticationClient;
import io.cyborgcode.roa.api.authentication.Credentials;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Defines API authentication parameters for test execution.
 *
 * <p>This annotation specifies the authentication credentials and client type
 * required for executing API tests.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@AiCompass(
      description = "Method-level annotation that configures how API "
            + "authentication is performed for a test (credentials + client + caching).",
      tags = {"api", "auth", "annotation"},
      creation = CreationKind.PROVIDED
)
@AiCompassOptions(
      exampleFilesPath = "docs/usage/roa/api-usage.json",
      meta = {
         @AiCompassOptions.Meta(key = "type", value = "api-auth-annotation"),
         @AiCompassOptions.Meta(key = "scope", value = "method")
      }
)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AuthenticateViaApi {

   /**
    * Specifies the credentials class providing authentication details.
    *
    * @return The class implementing {@link Credentials}.
    */
   @AiCompass(
         description = "Credentials provider used to supply username/password or tokens for authentication.",
         tags = {"api", "auth", "annotation"}
   )
   @AiCompassOptions(
         exampleFilesPath = "docs/usage/roa/api-usage.json"
   )
   Class<? extends Credentials> credentials();

   /**
    * Specifies the authentication client responsible for handling authentication.
    *
    * @return The class extending {@link BaseAuthenticationClient}.
    */
   @AiCompass(
         description = "Authentication client implementation that knows "
               + "how to perform the login call and store resulting tokens/headers."
   )
   @AiCompassOptions(
         exampleFilesPath = "docs/usage/roa/api-usage.json"
   )
   Class<? extends BaseAuthenticationClient> type();

   /**
    * Determines whether authentication credentials should be cached.
    *
    * @return {@code true} if credentials should be cached, otherwise {@code false}.
    */
   @AiCompass(
         description = "Whether to cache resolved credentials "
               + "(and/or tokens) across tests instead of logging in every time."
   )
   @AiCompassOptions(
         exampleFilesPath = "docs/usage/roa/api-usage.json"
   )
   boolean cacheCredentials() default false;

}
