package ${package}.ui.types;

import io.cyborgcode.roa.ui.components.button.ButtonComponentType;

/**
 * Defines supported button component types for the project.
 */
public enum ButtonFieldTypes implements ButtonComponentType {

    /**
     * Example button field type enum.
     *
     * <p>TODO: implement your button field type enum here</p>
     */
//    EXAMPLE_BUTTON_TYPE;

    /**
     * Example:
     *
     * <p>TODO: implement your string identifiers here so they are accessed via ImplementationOfType annotations</p>
     */
//    public static final class Data {
//
//        public static final String EXAMPLE_BUTTON = "EXAMPLE_BUTTON_TYPE";
//
//        private Data() {
//        }
//    }

    @Override
    public Enum<?> getType() {
        return this;
    }
}
