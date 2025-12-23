package ${package}.common.preconditions;

import io.cyborgcode.roa.framework.parameters.PreQuestJourney;
import io.cyborgcode.roa.framework.quest.SuperQuest;
import ${package}.common.base.Rings;
import ${package}.api.dto.request.ExampleRequestDto;

import java.util.function.BiConsumer;

/**
 * Registry of pre-test journeys (Preconditions).
 * <p>
 * This enum implements {@link PreQuestJourney}. You can define setup steps here
 * that run before your test logic. Use the {@code @Journey} annotation in tests.
 * </p>
 */
public enum Preconditions implements PreQuestJourney<Preconditions> {

    /**
     * Example precondition used for test setup.
     */
    EXAMPLE_PRECONDITION((quest, objects) -> {
        // Example logic:
        // String userType = (String) objects[0];
        // quest.use(Rings.RING_OF_API).post("/setup", new ExampleRequestDto(userType));
    });

    /**
     * Constants referencing the enum names for use in {@code @Journey} annotations.
     *
     * Example in tests:
     *
     * @Journey(value = Precondition.Data.EXAMPLE_PRECONDITION)
     */
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
