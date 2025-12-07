package ${package}.ui.elements;

import io.cyborgcode.roa.ui.components.base.ComponentType;
import io.cyborgcode.roa.ui.components.select.SelectComponentType;
import io.cyborgcode.roa.ui.selenium.SelectUiElement;
import ${package}.ui.types.SelectFieldTypes;
import org.openqa.selenium.By;

/**
 * Example select (dropdown) registry for demonstration purposes.
 */
public enum SelectFields implements SelectUiElement {

    GENERIC_SELECT(
            By.cssSelector("select"),
            SelectFieldTypes.EXAMPLE_SELECT_TYPE
    );

    private final By locator;
    private final SelectComponentType componentType;

    SelectFields(By locator, SelectComponentType componentType) {
        this.locator = locator;
        this.componentType = componentType;
    }

    @Override
    public By locator() {
        return locator;
    }

    @Override
    public <T extends ComponentType> T componentType() {
        return (T) componentType;
    }

    @Override
    public Enum<?> enumImpl() {
        return this;
    }
}
