package ${package}.ui.types;

import io.cyborgcode.roa.ui.components.button.ButtonComponentType;

public enum ButtonFieldTypesAI implements ButtonComponentType {

    // TODO: Add your button types here (Bootsrap, Material Design, Vaadin...)
    EXAMPLE_BUTTON_TYPE;

    public static final class Data {

        public static final String EXAMPLE_BUTTON_TYPE = "EXAMPLE_BUTTON_TYPE";

        private Data() {
        }
    }

    @Override
    public Enum<?> getType() {
        return this;
    }
}
