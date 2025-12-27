package ${package}.ui.types;

import io.cyborgcode.roa.ui.components.select.SelectComponentType;

public enum SelectFieldTypesAI implements SelectComponentType {

    ;

    public static final class Data {

        public static final String EXAMPLE_SELECT_TYPE = "EXAMPLE_SELECT_TYPE";

        private Data() {
        }
    }

    @Override
    public Enum<?> getType() {
        return this;
    }
}
