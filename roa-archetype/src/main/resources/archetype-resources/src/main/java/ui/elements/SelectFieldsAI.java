package ${package}.ui.elements;

import io.cyborgcode.roa.ui.components.base.ComponentType;
import io.cyborgcode.roa.ui.selenium.SelectUiElement;
import io.cyborgcode.roa.ui.selenium.smart.SmartWebDriver;
import ${package}.ui.types.SelectFieldTypes;
import org.openqa.selenium.By;
import java.util.function.Consumer;

public enum SelectFieldsAI implements SelectUiElement {

    ;

    private final By locator;
    private final SelectFieldTypes componentType;
    private final Consumer<SmartWebDriver> before;
    private final Consumer<SmartWebDriver> after;

    SelectFieldsAI(By locator, SelectFieldTypes componentType) {
        this(locator, componentType, driver -> {}, driver -> {});
    }

    SelectFieldsAI(By locator, SelectFieldTypes componentType, 
                   Consumer<SmartWebDriver> before, Consumer<SmartWebDriver> after) {
        this.locator = locator;
        this.componentType = componentType;
        this.before = before;
        this.after = after;
    }

    @Override
    public By locator() { return locator; }

    @Override
    public <T extends ComponentType> T componentType() {
        return (T) componentType;
    }

    @Override
    public Enum<?> enumImpl() { return this; }
    
    @Override
    public Consumer<SmartWebDriver> before() { return before; }
    
    @Override
    public Consumer<SmartWebDriver> after() { return after; }
}
