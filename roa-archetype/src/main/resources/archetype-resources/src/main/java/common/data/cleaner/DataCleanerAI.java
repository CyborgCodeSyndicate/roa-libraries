package ${package}.common.data.cleaner;

import io.cyborgcode.roa.framework.parameters.DataRipper;
import io.cyborgcode.roa.framework.quest.SuperQuest;
import java.util.function.Consumer;

public enum DataCleanerAI implements DataRipper<DataCleanerAI> {

    ;

    private final Consumer<SuperQuest> cleanUpFunction;

    DataCleanerAI(Consumer<SuperQuest> cleanUpFunction) {
        this.cleanUpFunction = cleanUpFunction;
    }

    @Override
    public Consumer<SuperQuest> eliminate() {
        return cleanUpFunction;
    }

    @Override
    public DataCleanerAI enumImpl() {
        return this;
    }
}
