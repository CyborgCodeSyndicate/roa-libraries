package ${package}.common.preconditions;

import io.cyborgcode.roa.framework.parameters.PreQuestJourney;
import io.cyborgcode.roa.framework.quest.SuperQuest;
import java.util.function.BiConsumer;

public enum PreconditionsAI implements PreQuestJourney<PreconditionsAI> {

    ;

    private final BiConsumer<SuperQuest, Object[]> function;

    PreconditionsAI(BiConsumer<SuperQuest, Object[]> function) {
        this.function = function;
    }

    @Override
    public BiConsumer<SuperQuest, Object[]> journey() {
        return function;
    }

    @Override
    public PreconditionsAI enumImpl() {
        return this;
    }
}
