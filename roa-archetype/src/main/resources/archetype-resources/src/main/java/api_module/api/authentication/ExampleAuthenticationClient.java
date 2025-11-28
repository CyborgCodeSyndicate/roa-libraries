package ${package}.api_module.api.authentication;

import io.cyborgcode.roa.api.authentication.BaseAuthenticationClient;
import io.cyborgcode.roa.api.service.RestService;
import io.restassured.http.Header;

/**
 * Example authentication client.
 *
 * <p>Replace this implementation with your application's real authentication
 * logic (e.g., token retrieval, login endpoint, session cookies, API keys).</p>
 */
public class ExampleAuthenticationClient extends BaseAuthenticationClient {

   @Override
   protected Header authenticateImpl(RestService restService, String username, String password) {

      // TODO: Replace with your real authentication call.
      // For now, return a placeholder header.

      return new Header("Authorization", "Bearer example-token");
   }
}
