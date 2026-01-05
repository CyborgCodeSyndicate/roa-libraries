package ${package}.ui.types;

import io.cyborgcode.roa.ui.components.select.SelectComponentType;

// TODO: Replace EXAMPLE_SELECT_TYPE with your real select type(s) and add more enum values if needed.
public enum SelectFieldTypes implements SelectComponentType {

    EXAMPLE_SELECT_TYPE;

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
