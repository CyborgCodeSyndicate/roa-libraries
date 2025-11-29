package ${package}.ui.types;

import io.cyborgcode.roa.ui.components.input.InputComponentType;

/**
 * Defines supported input component types.
 */
public enum InputFieldTypes implements InputComponentType {

    EXAMPLE_INPUT_TYPE;

    /**
     * String identifiers used by @ImplementationOfType annotations.
     */
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
