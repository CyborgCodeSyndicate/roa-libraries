package io.cyborgcode.roa.api.service.mock;

import io.cyborgcode.roa.api.authentication.AuthenticationKey;
import io.cyborgcode.roa.api.authentication.BaseAuthenticationClient;
import io.cyborgcode.roa.api.service.RestService;
import io.restassured.http.Header;
import lombok.NonNull;

public class MockAuthClient extends BaseAuthenticationClient {

   @Override
   public AuthenticationKey authenticate(@NonNull RestService restService, @NonNull String user,
                                         String pass, boolean cache) {
      return new AuthenticationKey("username", "password", null);
   }

   @Override
   protected Header authenticateImpl(RestService restService, String username, String password) {
      return null;
   }
}