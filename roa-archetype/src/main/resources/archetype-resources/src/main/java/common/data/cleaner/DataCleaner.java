package ${package}.common.data.cleaner;
#set($mods = $modules.toUpperCase())
import io.cyborgcode.roa.framework.parameters.DataRipper;
import io.cyborgcode.roa.framework.quest.SuperQuest;
#if($mods.contains("API"))
import ${package}.common.base.Rings;
#end
import java.util.function.Consumer;

// TODO: Replace EXAMPLE_CLEANUP with your real cleanup functions (or delete this enum if not needed).
public enum DataCleaner implements DataRipper<DataCleaner> {

    EXAMPLE_CLEANUP(quest -> {
#if($mods.contains("API"))
        // Example:
        // quest.use(Rings.RING_OF_API).delete("/users/" + quest.getData("userId"));
#end
    });

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
