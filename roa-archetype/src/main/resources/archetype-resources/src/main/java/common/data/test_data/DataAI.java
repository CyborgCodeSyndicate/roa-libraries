package ${package}.common.data.test_data;

import org.aeonbits.owner.ConfigCache;

public final class DataAI {

    private Data() {
    }

    public static DataProperties testData() {
        return ConfigCache.getOrCreate(DataProperties.class);
    }
}
