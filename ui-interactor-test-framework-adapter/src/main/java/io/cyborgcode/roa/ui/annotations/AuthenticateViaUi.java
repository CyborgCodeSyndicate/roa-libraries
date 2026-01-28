package io.cyborgcode.roa.ui.annotations;

import io.cyborgcode.pandora.annotation.Pandora;
import io.cyborgcode.pandora.annotation.PandoraOptions;
import io.cyborgcode.pandora.model.CreationKind;
import io.cyborgcode.roa.ui.authentication.BaseLoginClient;
import io.cyborgcode.roa.ui.authentication.LoginCredentials;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to authenticate a user via the UI in test scenarios.
 *
 * <p>This annotation is used to perform UI-based authentication by specifying
 * the required credentials and the login client responsible for handling the authentication process.
 * It supports caching credentials to optimize performance by reducing redundant logins.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@Pandora(
      description = "Method-level annotation that configures how UI authentication is performed for a test "
            + "(credentials + client + caching).",
      tags = {"ui", "auth", "annotation"},
      creation = CreationKind.PROVIDED
)
@PandoraOptions(
      exampleFilesPath = "ai/roa/ui-usage.json",
      meta = {
         @PandoraOptions.Meta(key = "type", value = "ui-auth-annotation"),
         @PandoraOptions.Meta(key = "scope", value = "method")
      }
)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AuthenticateViaUi {

   /**
    * Specifies the credentials class required for authentication.
    *
    * @return The class that provides login credentials.
    */
   @Pandora(
         description = "Credentials provider used to supply username/password or tokens for UI login."
   )
   Class<? extends LoginCredentials> credentials();

   /**
    * Specifies the UI login client class responsible for handling authentication.
    *
    * @return The class implementing the authentication logic.
    */
   @Pandora(
         description = "UI login client implementation that performs the login flow and stores "
               + "resulting session/tokens."
   )
   Class<? extends BaseLoginClient> type();

   /**
    * Determines whether the credentials should be cached.
    *
    * <p>If set to {@code true}, the framework will store the credentials and reuse them
    * for subsequent tests to avoid redundant logins.
    *
    * @return {@code true} if credentials should be cached, {@code false} otherwise.
    */
   @Pandora(
         description = "Whether to cache resolved credentials (and/or tokens) across tests instead of "
               + "logging in every time."
   )
   boolean cacheCredentials() default false;

}
