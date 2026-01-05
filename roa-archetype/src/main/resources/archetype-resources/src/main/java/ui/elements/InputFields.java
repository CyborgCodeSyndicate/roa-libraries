package ${package}.ui.elements;

import io.cyborgcode.roa.ui.components.base.ComponentType;
import io.cyborgcode.roa.ui.components.input.InputComponentType;
import io.cyborgcode.roa.ui.selenium.InputUiElement;
import io.cyborgcode.roa.ui.selenium.smart.SmartWebDriver;
import ${package}.ui.types.InputFieldTypes;
import org.openqa.selenium.By;

import java.util.function.Consumer;

// TODO: Replace USERNAME/PASSWORD with your real input fields and add more input elements as needed.
public enum InputFields implements InputUiElement {

    USERNAME(
            By.id("username-input"),
            InputFieldTypes.EXAMPLE_INPUT_TYPE
    ),

    PASSWORD(
            By.id("password-input"),
            InputFieldTypes.EXAMPLE_INPUT_TYPE,
            driver -> {
                // Example: driver.waitForElementPresent(By.id("password-input"));
            },
            driver -> {
                // Example: driver.takeScreenshot();
            }
    );

    private final By locator;
    private final InputComponentType componentType;
    private final Consumer<SmartWebDriver> before;
    private final Consumer<SmartWebDriver> after;

    InputFields(By locator, InputComponentType componentType) {
        this(locator, componentType, driver -> {}, driver -> {});
    }

    InputFields(By locator,
                InputComponentType componentType,
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
