package ${package}.ui.types;

import io.cyborgcode.roa.ui.components.input.InputComponentType;

/**
 * Defines supported input component types.
 */
public enum InputFieldTypes implements InputComponentType {

    /**
     * Example input field type enum.
     *
     * <p>TODO: implement your input field type enum here</p>
     */
    EXAMPLE_INPUT_TYPE;

    /**
     * Example:
     *
     * <p>TODO: implement your string identifiers here so they are accessed via ImplementationOfType annotations</p>
     */
//    public static final class Data {
//
//        public static final String EXAMPLE_INPUT = "EXAMPLE_INPUT_TYPE";
//
//        private Data() {
//        }
//    }

    @Override
    public Enum<?> getType() {
//        return this;
        return null;
    }
}
