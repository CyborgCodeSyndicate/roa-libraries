package ${package}.common.preconditions;

import io.cyborgcode.roa.framework.parameters.PreQuestJourney;
import io.cyborgcode.roa.framework.quest.SuperQuest;

import java.util.function.BiConsumer;

/**
 * Example registry of pre-test journeys.</p>
 */
public enum Preconditions implements PreQuestJourney<Preconditions> {

    /**
     * Example:
     *
     * <p>TODO: implement your own precondition enums.</p>
     */
//    EXAMPLE_PRECONDITION((quest, objects) -> {});

    /**
     * Example:
     *
     * <p>TODO: implement your string identifiers here so they are accessed via PreQuest annotations.</p>
     */
//    public static final class Data {
//        public static final String EXAMPLE_PRECONDITION = "EXAMPLE_PRECONDITION";
//
//        private Data() {
//        }
//    }

    /**
     * Example:
     *
     * <p>TODO: implement your function and reference it in the enum.</p>
     */
//    private final BiConsumer<SuperQuest, Object[]> function;
//
//    Preconditions(BiConsumer<SuperQuest, Object[]> function) {
//        this.function = function;
//    }

    @Override
    public BiConsumer<SuperQuest, Object[]> journey() {
//        return function;
        return null;
    }

    @Override
    public Preconditions enumImpl() {
//        return this;
        return null;
    }
}
