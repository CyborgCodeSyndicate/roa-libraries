package ${package}.ui.types;

import io.cyborgcode.roa.ui.components.button.ButtonComponentType;

/**
 * Defines supported button component types.
 * <p>
 * TODO: If your application has custom button widgets (e.g., icon buttons, toggle buttons),
 * add them here as enum values.
 * </p>
 */
public enum ButtonFieldTypes implements ButtonComponentType {

    /**
     * Example button field type.
     * <p>
     * This is a generic button type. Replace or extend with specific types
     * for your application.
     * </p>
     */
    EXAMPLE_BUTTON_TYPE;

    /**
     * Constants for use in {@code @ImplementationOfType} annotations.
     * <p>
     * TODO: Add matching constants for your new enum values here.
     * </p>
     */
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
