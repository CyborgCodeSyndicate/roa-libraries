package ${package}.ui.types;

import io.cyborgcode.roa.ui.components.input.InputComponentType;

// TODO: Replace EXAMPLE_INPUT_TYPE with your real input type(s) and add more enum values if needed.
public enum InputFieldTypes implements InputComponentType {

    EXAMPLE_INPUT_TYPE;

    public static final class Data {

        public static final String EXAMPLE_INPUT_TYPE = "EXAMPLE_INPUT_TYPE";

        private Data() {
        }
    }

    @Override
    public Enum<?> getType() {
        return this;
    }
}
