package io.cyborgcode.roa.ui.exceptions;

import io.cyborgcode.roa.ui.exceptions.AuthenticationUiException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AuthenticationUiExceptionTest {

   @Test
   void testConstructorWithMessageAndCause() {
      String message = "Authentication failed due to missing token";
      Throwable cause = new RuntimeException("Token not found");

      AuthenticationUiException exception = new AuthenticationUiException(message, cause);

      assertEquals(message, exception.getMessage());
      assertEquals(cause, exception.getCause());
   }

   @Test
   void testConstructorWithOnlyCause() {
      Throwable cause = new IllegalStateException("Session timeout");

      AuthenticationUiException exception = new AuthenticationUiException(cause);

      assertEquals("Failed to authenticate via UI", exception.getMessage());
      assertEquals(cause, exception.getCause());
   }
}