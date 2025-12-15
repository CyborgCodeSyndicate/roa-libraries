package ${package}.common.preconditions;

import io.cyborgcode.roa.framework.parameters.PreQuestJourney;
import io.cyborgcode.roa.framework.quest.SuperQuest;

import java.util.function.BiConsumer;

/**
 * Registry of pre-test journeys (Preconditions).
 * <p>
 * This enum implements {@link PreQuestJourney}. You can define setup steps here
 * that run before your test logic. Use the {@code @PreQuest} annotation to attach
 * them to your tests.
 * </p>
 */
public enum Preconditions implements PreQuestJourney<Preconditions> {

    /**
     * Example precondition.
     * <p>
     * TODO: Replace with real setup logic, like creating a user or resetting the DB.
     * The {@code objects} array contains parameters passed from the annotation.
     * </p>
     */
    EXAMPLE_PRECONDITION((quest, objects) -> {
        // Example logic:
        // String userType = (String) objects[0];
        // quest.use(Rings.RING_OF_API).post("/setup", new SetupDto(userType));
    });

    /**
     * Constants for use in {@code @PreQuest} annotations.
     * <p>
     * TODO: Add matching constants for your new enum values here.
     * </p>
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
