package ${package}.ui.types;

import io.cyborgcode.roa.ui.components.select.SelectComponentType;

/**
 * Defines supported select/dropdown component types.
 * <p>
 * TODO: If your application has custom dropdown widgets (e.g., multi-select, searchable dropdowns),
 * add them here as enum values.
 * </p>
 */
public enum SelectFieldTypes implements SelectComponentType {

    /**
     * Example select field type.
     * <p>
     * This is a generic select type. Replace or extend with specific types
     * for your application.
     * </p>
     */
    EXAMPLE_SELECT_TYPE;

    /**
     * Constants for use in {@code @ImplementationOfType} annotations.
     * <p>
     * TODO: Add matching constants for your new enum values here.
     * </p>
     */
    public static final class Data {

        public static final String EXAMPLE_SELECT_TYPE = "EXAMPLE_SELECT_TYPE";

        private Data() {
        }
    }

    @Override
    public Enum<?> getType() {
        return this;
    }
}
