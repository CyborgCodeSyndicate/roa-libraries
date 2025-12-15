package ${package}.ui.elements;

import io.cyborgcode.roa.ui.components.base.ComponentType;
import io.cyborgcode.roa.ui.components.select.SelectComponentType;
import io.cyborgcode.roa.ui.selenium.SelectUiElement;
import io.cyborgcode.roa.ui.selenium.smart.SmartWebDriver;
import ${package}.ui.types.SelectFieldTypes;
import org.openqa.selenium.By;

import java.util.function.Consumer;

/**
 * Registry of select/dropdown field elements for your UI tests.
 * <p>
 * Define your application's dropdowns here.
 * </p>
 */
public enum SelectFields implements SelectUiElement {

    /**
     * Example select field.
     */
    GENERIC_SELECT(
            By.id("example-select"),
            SelectFieldTypes.EXAMPLE_SELECT_TYPE,
            // Before interaction hook:
            webDriver -> {},
            // After interaction hook:
            webDriver -> {}
    );

    private final By locator;
    private final SelectComponentType componentType;
    private final Consumer<SmartWebDriver> before;
    private final Consumer<SmartWebDriver> after;

    SelectFields(By locator, SelectComponentType componentType) {
        this(locator, componentType, smartWebDriver -> {}, smartWebDriver -> {});
    }

    SelectFields(By locator,
                 SelectComponentType componentType,
                 Consumer<SmartWebDriver> before,
                 Consumer<SmartWebDriver> after) {
        this.locator = locator;
        this.componentType = componentType;
        this.before = before;
        this.after = after;
    }

    @Override
    public By locator() {
        return locator;
    }

    @Override
    public <T extends ComponentType> T componentType() {
        //noinspection unchecked
        return (T) componentType;
    }

    @Override
    public Enum<?> enumImpl() {
        return this;
    }

    @Override
    public Consumer<SmartWebDriver> before() {
        return before;
    }

    @Override
    public Consumer<SmartWebDriver> after() {
        return after;
    }
}
