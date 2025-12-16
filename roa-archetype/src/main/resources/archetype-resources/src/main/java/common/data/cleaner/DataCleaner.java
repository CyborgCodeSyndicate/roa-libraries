package ${package}.common.data.cleaner;

import io.cyborgcode.roa.framework.parameters.DataRipper;
import io.cyborgcode.roa.framework.quest.SuperQuest;
import ${package}.common.base.Rings;

import java.util.function.Consumer;

/**
 * Defines reusable cleanup (data ripping) operations for your test suite.
 * <p>
 * This enum implements {@link DataRipper}, allowing you to define cleanup logic
 * that can be attached to tests using the {@code @Ripper} annotation.
 * </p>
 */
public enum DataCleaner implements DataRipper<DataCleaner> {

    /**
     * Example cleanup operation.
     */
    EXAMPLE_CLEANUP(quest -> {
        // Example:
        // quest.use(Rings.RING_OF_API).delete("/users/" + quest.getData("userId"));
    });

    /**
     * Constants referencing the enum names for use in {@code @Ripper} annotations.
     */
    public static final class Data {

        public static final String EXAMPLE_CLEANUP = "EXAMPLE_CLEANUP";

        private Data() {
        }
    }

    private final Consumer<SuperQuest> cleanUpFunction;

    DataCleaner(Consumer<SuperQuest> cleanUpFunction) {
        this.cleanUpFunction = cleanUpFunction;
    }

    @Override
    public Consumer<SuperQuest> eliminate() {
        return cleanUpFunction;
    }

    @Override
    public DataCleaner enumImpl() {
        return this;
    }
}
