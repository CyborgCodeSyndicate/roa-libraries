package ${package}.api.authentication;

import io.cyborgcode.roa.api.authentication.Credentials;
import ${package}.common.data.test_data.DataProperties;
import org.aeonbits.owner.ConfigCache;

// TODO: Implement credentials for specific user
public class ExampleCredentials implements Credentials {

    private static final DataProperties DATA_PROPERTIES =
            ConfigCache.getOrCreate(DataProperties.class);

    @Override
    public String username() {
        return DATA_PROPERTIES.exampleUsername();
    }

    @Override
    public String password() {
        return DATA_PROPERTIES.exampleValue();
    }
}
