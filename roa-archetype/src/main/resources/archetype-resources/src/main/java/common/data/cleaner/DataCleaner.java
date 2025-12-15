package ${package}.common.data.cleaner;

import io.cyborgcode.roa.framework.parameters.DataRipper;
import io.cyborgcode.roa.framework.quest.SuperQuest;

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
     * Example data cleaner enum.
     * <p>
     * TODO: Generic example that does nothing. Replace with real cleanup logic,
     * such as deleting a user created during the test.
     * </p>
     */
    EXAMPLE_CLEANUP(quest -> {
        // Example:
        // quest.use(Rings.RING_OF_API).delete("/users/" + quest.getData("userId"));
    });

    /**
     * Example String constants for use in annotations.
     * <p>
     * TODO: Define public static constants matching your enum names here.
     * This allows you to use {@code @Ripper(DataCleaner.Data.EXAMPLE_CLEANUP)} in tests.
     * </p>
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
