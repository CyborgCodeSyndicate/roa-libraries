package ${package}.common.data.test_data;

import org.aeonbits.owner.ConfigCache;

public final class DataAI {

    private DataAI() {
    }

    public static DataProperties testData() {
        return ConfigCache.getOrCreate(DataProperties.class);
    }
}
