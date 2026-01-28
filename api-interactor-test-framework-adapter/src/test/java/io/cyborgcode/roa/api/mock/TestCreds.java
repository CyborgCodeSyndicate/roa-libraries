package io.cyborgcode.roa.api.mock;

import io.cyborgcode.roa.api.authentication.Credentials;

public class TestCreds implements Credentials {

   @Override
   public String username() {
      return "testUser";
   }

   @Override
   public String password() {
      return "testPass";
   }
}
