package ${package}.common.data.cleaner;

import io.cyborgcode.roa.framework.parameters.DataRipper;
import io.cyborgcode.roa.framework.quest.SuperQuest;

import java.util.function.Consumer;

/**
 * Defines reusable cleanup (data ripping) operations for your test suite.
 */
public enum DataCleaner implements DataRipper<DataCleaner> {

    /**
     * Example data cleaner enum:
     *
     * <p>TODO: implement your data cleaner enum here by adding your cleanup functions to the enum</p>
     */
    EXAMPLE_CLEANUP(quest -> {});

    /**
     * Example:
     *
     * <p>TODO: implement your string identifiers here so they are accessed via Ripper annotations</p>
     */
//    public static final class Data {
//
//        private Data() {
//        }
//
//        public static final String EXAMPLE_CLEANUP = "EXAMPLE_CLEANUP";
//    }

    /**
     * Example:
     *
     * <p>TODO: implement your consumer and reference it in the enum</p>
     */
    private final Consumer<SuperQuest> cleanUpFunction;

    DataCleaner(Consumer<SuperQuest> cleanUpFunction) {
        this.cleanUpFunction = cleanUpFunction;
    }

    @Override
    public Consumer<SuperQuest> eliminate() {
//        return cleanUpFunction;
        return null;
    }

    @Override
    public DataCleaner enumImpl() {
//        return this;
        return null;
    }
}
