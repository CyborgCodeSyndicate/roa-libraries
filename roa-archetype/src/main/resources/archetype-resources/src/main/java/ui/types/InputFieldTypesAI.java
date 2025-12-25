package ${package}.ui.types;

import io.cyborgcode.roa.ui.components.input.InputComponentType;

public enum InputFieldTypesAI implements InputComponentType {

    // TODO: Add your input types here (Bootsrap, Material Design, Vaadin...)
    EXAMPLE_INPUT_TYPE;

    public static final class Data {

        public static final String EXAMPLE_INPUT = "EXAMPLE_INPUT_TYPE";

        private Data() {
        }
    }

    @Override
    public Enum<?> getType() {
        return this;
    }
}
