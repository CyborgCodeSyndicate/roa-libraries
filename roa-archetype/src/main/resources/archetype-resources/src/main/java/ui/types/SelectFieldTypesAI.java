package ${package}.ui.types;

import io.cyborgcode.roa.ui.components.select.SelectComponentType;

public enum SelectFieldTypesAI implements SelectComponentType {

    // TODO: Add your select types here
    EXAMPLE_SELECT_TYPE;

    @Override
    public Enum<?> getType() {
        return this;
    }
}
