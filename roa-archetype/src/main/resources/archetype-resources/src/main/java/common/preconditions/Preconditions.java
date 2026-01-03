package ${package}.common.preconditions;
#set($mods = $modules.toUpperCase())
import io.cyborgcode.roa.framework.parameters.PreQuestJourney;
import io.cyborgcode.roa.framework.quest.SuperQuest;
import ${package}.common.base.Rings;
import java.util.function.BiConsumer;
#if($mods.contains("API"))
import ${package}.api.ExampleEndpoints;
#end

// TODO: Replace EXAMPLE_PRECONDITION with your real pre-test conditions (or delete this enum if you don't use @Journey).
public enum Preconditions implements PreQuestJourney<Preconditions> {

    EXAMPLE_PRECONDITION((quest, objects) -> {
        // Example logic:
        // quest.use(Rings.RING_OF_CUSTOM).exampleFlow()
#if($mods.contains("API"))
        // .drop().use(Rings.RING_OF_API).request(ExampleEndpoints.EXAMPLE_POST, objects);
#end
    });

    public static final class Data {
        public static final String EXAMPLE_PRECONDITION = "EXAMPLE_PRECONDITION";

        private Data() {
        }
    }

    private final BiConsumer<SuperQuest, Object[]> function;

    Preconditions(BiConsumer<SuperQuest, Object[]> function) {
        this.function = function;
    }

    @Override
    public BiConsumer<SuperQuest, Object[]> journey() {
        return function;
    }

    @Override
    public Preconditions enumImpl() {
        return this;
    }
}
