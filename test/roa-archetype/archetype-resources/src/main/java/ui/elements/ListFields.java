package ${package}.ui.elements;

import io.cyborgcode.roa.ui.components.base.ComponentType;
import io.cyborgcode.roa.ui.components.list.ItemListComponentType;
import io.cyborgcode.roa.ui.selenium.ListUiElement;
import ${package}.ui.types.ListFieldTypes;
import org.openqa.selenium.By;

/**
 * Example list registry for simple list containers.
 */
public enum ListFields implements ListUiElement {

    MAIN_TABS(By.className("nav-tabs"), ListFieldTypes.EXAMPLE_LIST_TYPE),
    SIDE_MENU(By.className("list-group"), ListFieldTypes.EXAMPLE_LIST_TYPE);

    private final By locator;
    private final ItemListComponentType componentType;

    ListFields(By locator, ItemListComponentType componentType) {
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
