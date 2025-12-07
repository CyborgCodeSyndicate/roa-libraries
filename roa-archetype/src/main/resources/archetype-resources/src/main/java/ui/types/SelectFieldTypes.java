package ${package}.ui.types;

import io.cyborgcode.roa.ui.components.select.SelectComponentType;

/**
 * Defines supported select component types.
 */
public enum SelectFieldTypes implements SelectComponentType {

    /**
     * Example select field type enum.
     *
     * <p>TODO: implement your select field type enum here</p>
     */
//    EXAMPLE_SELECT_TYPE;

    /**
     * Example:
     *
     * <p>TODO: implement your string identifiers here so they are accessed via ImplementationOfType annotations</p>
     */
//    public static final class Data {
//
//        public static final String EXAMPLE_SELECT = "EXAMPLE_SELECT_TYPE";
//
//        private Data() {
//        }
//    }

    @Override
    public Enum<?> getType() {
        return this;
    }
}
