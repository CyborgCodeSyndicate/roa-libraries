package io.cyborgcode.roa.api.mock;

import io.cyborgcode.roa.api.authentication.BaseAuthenticationClient;
import io.cyborgcode.roa.api.service.RestService;
import io.restassured.http.Header;

public class TestAuthClient extends BaseAuthenticationClient {

   @Override
   protected Header authenticateImpl(RestService restService, String username, String password) {
      return new Header("Authorization", "Bearer test-token-" + username);
   }
}