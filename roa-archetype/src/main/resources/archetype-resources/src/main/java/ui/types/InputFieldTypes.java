package ${package}.ui.types;

import io.cyborgcode.roa.ui.components.input.InputComponentType;

/**
 * Defines supported input component types.
 * <p>
 * TODO: If your application has different front-end technologies (components) like Angular Material, Bootstrap, etc.,
 * and add them here as enum values.
 * </p>
 */
public enum InputFieldTypes implements InputComponentType {

    /**
     * Example input field type.
     * <p>
     * This is a generic input type. Replace or extend with specific technology type(s) (components)
     * that exist in your application.
     * </p>
     */
    EXAMPLE_INPUT_TYPE;

    /**
     * Constants for use in {@code @ImplementationOfType} annotations.
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
