package ${package}.ui.authentication;

import io.cyborgcode.roa.ui.authentication.LoginCredentials;
import ${package}.common.data.test_data.Data;

/**
 * Provides example credentials for UI authentication.
 * <p>
 * This class implements {@link LoginCredentials} to supply credentials
 * for authentication operations. It integrates with ROA's {@code @AuthenticateViaUi}
 * annotation to perform automatic login before tests execute.
 * </p>
 * <p>
 * Credentials are sourced from {@link Data#testData()} configuration, allowing
 * environment-specific overrides without code changes. This pattern centralizes
 * credential management and supports credential rotation or environment-based variations.
 * </p>
 * <p>
 * Usage example:
 * <pre>{@code
 * @Test
 * @AuthenticateViaUi(credentials = ExampleCredentials.class, type = ExampleAppUiLogin.class)
 * void myTest(Quest quest) {
 *     // Test starts with user already logged in
 * }
 * }</pre>
 * </p>
 */
public class ExampleCredentials implements LoginCredentials {

   @Override
   public String username() {
      return Data.testData().exampleUsername();
   }

   @Override
   public String password() {
      return Data.testData().exampleValue();
   }

}
