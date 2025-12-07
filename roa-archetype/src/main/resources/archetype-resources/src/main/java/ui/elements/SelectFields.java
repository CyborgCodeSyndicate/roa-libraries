package ${package}.ui.elements;

import io.cyborgcode.roa.ui.components.base.ComponentType;
import io.cyborgcode.roa.ui.components.select.SelectComponentType;
import io.cyborgcode.roa.ui.selenium.SelectUiElement;
import ${package}.ui.types.SelectFieldTypes;
import org.openqa.selenium.By;

/**
 * Example button field elements for demonstration and quick-start usage.
 */
public enum SelectFields implements SelectUiElement {

    /**
     * Example:
     *
     * <p>TODO: implement your select fields here</p>
     */
    GENERIC_SELECT(
                    /*null, //locator
                    SelectFieldTypes.EXAMPLE_SELECT_TYPE*/ //componentType
    );

    /**
     * Example:
     *
     * <p>TODO: implement your enum constructors here for different select field types</p>
     */
//    private final By locator;
//    private final SelectComponentType componentType;
//
//    SelectFields(By locator, SelectComponentType componentType) {
//        this.locator = locator;
//        this.componentType = componentType;
//    }

    @Override
    public By locator() {
//        return locator;
        return null;
    }

    @Override
    public <T extends ComponentType> T componentType() {
//        return (T) componentType;
        return null;
    }

    @Override
    public Enum<?> enumImpl() {
//        return this;
        return null;
    }
}
