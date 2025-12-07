package ${package}.ui.authentication;

import io.cyborgcode.roa.ui.authentication.LoginCredentials;
import ${package}.common.data.test_data.Data;

/**
 * Provides example credentials for UI authentication.
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
