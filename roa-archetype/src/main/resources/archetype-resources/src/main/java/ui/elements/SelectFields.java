package ${package}.ui.elements;

import io.cyborgcode.roa.ui.components.base.ComponentType;
import io.cyborgcode.roa.ui.components.select.SelectComponentType;
import io.cyborgcode.roa.ui.selenium.SelectUiElement;
import io.cyborgcode.roa.ui.selenium.smart.SmartWebDriver;
import ${package}.ui.types.SelectFieldTypes;
import org.openqa.selenium.By;

import java.util.function.Consumer;

// TODO: Replace GENERIC_SELECT with your real select/dropdown fields and add more select elements as needed.
public enum SelectFields implements SelectUiElement {

    GENERIC_SELECT(
            By.id("example-select"),
            SelectFieldTypes.EXAMPLE_SELECT_TYPE,
            driver -> {
                // Example: driver.waitForElementPresent(By.id("example-select"));
            },
            driver -> {
                // Example: driver.takeScreenshot();
            }
    );

    private final By locator;
    private final SelectComponentType componentType;
    private final Consumer<SmartWebDriver> before;
    private final Consumer<SmartWebDriver> after;

    SelectFields(By locator, SelectComponentType componentType) {
        this(locator, componentType, driver -> {}, driver -> {});
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
