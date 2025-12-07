package ${package}.common.data.test_data;

import io.cyborgcode.utilities.config.PropertyConfig;
import org.aeonbits.owner.Config;

/**
 * Strongly-typed configuration interface for test data.
 *
 * <p>Values are loaded from:
 * <ul>
 *   <li>System properties</li>
 *   <li>{@code ${test.data.file}.properties} on the classpath</li>
 * </ul></p>
 */
@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:${test.data.file}.properties"
})
public interface DataProperties extends PropertyConfig {

    /**
     * Example configuration entry.
     *
     * <p>TODO: Replace or extend with your real test data keys.</p>
     */
    @Key("example.username")
    String exampleUsername();

    @Key("example.value")
    String exampleValue();
}
