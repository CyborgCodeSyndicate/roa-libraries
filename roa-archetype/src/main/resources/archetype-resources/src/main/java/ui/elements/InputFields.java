package ${package}.ui.elements;

import io.cyborgcode.roa.ui.components.base.ComponentType;
import io.cyborgcode.roa.ui.components.input.InputComponentType;
import io.cyborgcode.roa.ui.selenium.InputUiElement;
import io.cyborgcode.roa.ui.selenium.smart.SmartWebDriver;
import ${package}.ui.types.InputFieldTypes;
import org.openqa.selenium.By;

import java.util.function.Consumer;

/**
 * Registry of input field elements for your UI tests.
 * <p>
 * Define your application's input fields here. Each enum value represents a specific input
 * on a page (e.g., USERNAME, PASSWORD).
 * </p>
 */
public enum InputFields implements InputUiElement {

    /**
     * Example: Username field.
     * <p>
     * Demonstrates a simple input field definition using a generic type.
     * </p>
     */
    USERNAME(
            By.id("username-input"),
            InputFieldTypes.EXAMPLE_INPUT_TYPE
    ),

    /**
     * Example: Password field with Consumer hooks.
     * <p>
     * Demonstrates using 'before' and 'after' hooks.
     * </p>
     */
    PASSWORD(
            By.id("password-input"),
            InputFieldTypes.EXAMPLE_INPUT_TYPE,
            // Before interaction hook:
            webDriver -> {
                // Example: webDriver.waitForElementPresent(By.id("password-input"));
            },
            // After interaction hook:
            webDriver -> {
                // Example: webDriver.takeScreenshot();
            }
    );

    private final By locator;
    private final InputComponentType componentType;
    private final Consumer<SmartWebDriver> before;
    private final Consumer<SmartWebDriver> after;

    InputFields(By locator, InputComponentType componentType) {
        this(locator, componentType, smartWebDriver -> {
        }, smartWebDriver -> {
        });
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
