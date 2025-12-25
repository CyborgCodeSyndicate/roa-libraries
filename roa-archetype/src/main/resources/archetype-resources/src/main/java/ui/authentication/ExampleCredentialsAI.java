package ${package}.ui.authentication;

import io.cyborgcode.roa.ui.authentication.LoginCredentials;
import ${package}.common.data.test_data.Data;

public class ExampleCredentialsAI implements LoginCredentials {

    @Override
    public String username() {
        return Data.testData().exampleUsername();
    }

    @Override
    public String password() {
        return Data.testData().exampleValue();
    }

}
