package ${package}.ui.types;

import io.cyborgcode.roa.ui.components.button.ButtonComponentType;

// TODO: Replace EXAMPLE_BUTTON_TYPE with your real button type(s) and add more enum values if needed.
public enum ButtonFieldTypes implements ButtonComponentType {

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
