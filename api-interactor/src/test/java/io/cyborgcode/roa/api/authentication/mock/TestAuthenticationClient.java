package io.cyborgcode.roa.api.authentication.mock;

import io.cyborgcode.roa.api.authentication.BaseAuthenticationClient;
import io.cyborgcode.roa.api.service.RestService;
import io.restassured.http.Header;

public class TestAuthenticationClient extends BaseAuthenticationClient {

   @Override
   protected Header authenticateImpl(RestService restService, String username, String password) {
      return new Header("Authorization", "Bearer dummy-token");
   }
}
