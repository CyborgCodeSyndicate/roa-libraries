package ${package}.common.data.creator;

import io.cyborgcode.roa.framework.parameters.DataForge;
import io.cyborgcode.roa.framework.parameters.Late;

/**
 * Registry of reusable test data factories.
 */
public enum DataCreator implements DataForge<DataCreator> {

    /**
     * Example data creator enum.
     *
     * <p>TODO: implement your data creator enum here by adding your late objects to the enum</p>
     */
//    EXAMPLE_MODEL(() -> null),
//    EXAMPLE_TABLE_MODEL(() -> null);

    /**
     * Example:
     *
     * <p>TODO: implement your string identifiers here so they are accessed via Craft annotations</p>
     */
//    public static final class Data {
//
//        public static final String EXAMPLE_MODEL = "EXAMPLE_MODEL";
//        public static final String EXAMPLE_TABLE_MODEL = "EXAMPLE_TABLE_MODEL";
//
//        private Data() {
//        }
//    }

    /**
     * Example:
     *
     * <p>TODO: implement your function here and reference it in the enum</p>
     */
//    private final Late<Object> createDataFunction;
//
//    DataCreator(final Late<Object> createDataFunction) {
//        this.createDataFunction = createDataFunction;
//    }

    @Override
    public Late<Object> dataCreator() {
//        return createDataFunction;
        return null;
    }

    @Override
    public DataCreator enumImpl() {
        return this;
    }
}
