package ${package}.common.data.creator;

import io.cyborgcode.roa.framework.parameters.DataForge;
import io.cyborgcode.roa.framework.parameters.Late;

public enum DataCreatorAI implements DataForge<DataCreatorAI> {

    ;

    private final Late<Object> createDataFunction;

    DataCreatorAI(final Late<Object> createDataFunction) {
        this.createDataFunction = createDataFunction;
    }

    @Override
    public Late<Object> dataCreator() {
        return createDataFunction;
    }

    @Override
    public DataCreatorAI enumImpl() {
        return this;
    }
}
