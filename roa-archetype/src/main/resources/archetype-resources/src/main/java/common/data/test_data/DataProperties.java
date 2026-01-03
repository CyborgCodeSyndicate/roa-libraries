package ${package}.common.data.test_data;

import io.cyborgcode.utilities.config.PropertyConfig;
import org.aeonbits.owner.Config;

// TODO: Replace these example keys with your real test-data properties.
@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:${test.data.file}.properties"
})
public interface DataProperties extends PropertyConfig {

    @Key("example.username")
    String exampleUsername();

    @Key("example.value")
    String exampleValue();
}
