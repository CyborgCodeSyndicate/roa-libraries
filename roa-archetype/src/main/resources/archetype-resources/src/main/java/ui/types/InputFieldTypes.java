package ${package}.ui.types;

import io.cyborgcode.roa.ui.components.input.InputComponentType;

/**
 * Defines supported input component types (e.g., standard input, date picker, etc.).
 */
public enum InputFieldTypes implements InputComponentType {

    /**
     * Example input field type.
     * <p>
     * TODO: Add specific types if your application has custom input widgets
     * that require specific handling in the automation framework.
     * </p>
     */
    EXAMPLE_INPUT_TYPE;

    /**
     * Constants for use in annotations.
     * <p>
     * TODO: Add matching constants for your new enum values here.
     * </p>
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
