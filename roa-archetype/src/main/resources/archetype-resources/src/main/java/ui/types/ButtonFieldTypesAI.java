package ${package}.ui.types;

import io.cyborgcode.roa.ui.components.button.ButtonComponentType;

public enum ButtonFieldTypesAI implements ButtonComponentType {

    EXAMPLE_BUTTON_TYPE;

    @Override
    public Enum<?> getType() {
        return this;
    }
}
