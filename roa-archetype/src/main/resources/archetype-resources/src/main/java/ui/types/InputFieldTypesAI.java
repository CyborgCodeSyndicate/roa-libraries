package ${package}.ui.types;

import io.cyborgcode.roa.ui.components.input.InputComponentType;

public enum InputFieldTypesAI implements InputComponentType {

    // TODO: Add your input types here
    EXAMPLE_INPUT_TYPE;

    @Override
    public Enum<?> getType() {
        return this;
    }
}
