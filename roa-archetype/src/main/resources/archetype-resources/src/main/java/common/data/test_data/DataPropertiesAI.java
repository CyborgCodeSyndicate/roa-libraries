package ${package}.common.data.test_data;

import io.cyborgcode.utilities.config.PropertyConfig;
import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:${test.data.file}.properties"
})
public interface DataPropertiesAI extends PropertyConfig {

}
