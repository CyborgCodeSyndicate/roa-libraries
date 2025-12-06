package ${package}.ui.elements;

import io.cyborgcode.roa.ui.components.base.ComponentType;
import io.cyborgcode.roa.ui.components.input.InputComponentType;
import io.cyborgcode.roa.ui.selenium.InputUiElement;
import io.cyborgcode.roa.ui.selenium.smart.SmartWebDriver;
import ${package}.ui.functions.ContextConsumer;
import ${package}.ui.functions.SharedUi;
import ${package}.ui.types.InputFieldTypes;
import org.openqa.selenium.By;

import java.util.function.Consumer;

/**
 * Example input field registry for demonstration purposes.
 */
public enum InputFields implements InputUiElement {

    GENERIC_INPUT(
            By.cssSelector("input"),
            InputFieldTypes.EXAMPLE_INPUT_TYPE,
            SharedUi.WAIT_FOR_PRESENCE,
            SharedUi.WAIT_FOR_LOADING
    ),

    USERNAME_INPUT(
            By.cssSelector("input[name='username']"),
            InputFieldTypes.EXAMPLE_INPUT_TYPE
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
                ContextConsumer before) {
        this(locator, componentType, before.asConsumer(locator), smartWebDriver -> {
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

    InputFields(By locator,
                InputComponentType componentType,
                ContextConsumer before,
                ContextConsumer after) {
        this(locator, componentType, before.asConsumer(locator), after.asConsumer(locator));
    }

    @Override
    public By locator() {
        return locator;
    }

    @Override
    public <T extends ComponentType> T componentType() {
        if (componentType == null) {
            return InputUiElement.super.componentType();
        }
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
