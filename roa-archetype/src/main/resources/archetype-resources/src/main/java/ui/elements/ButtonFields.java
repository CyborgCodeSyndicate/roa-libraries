package ${package}.ui.elements;

import io.cyborgcode.roa.ui.components.base.ComponentType;
import io.cyborgcode.roa.ui.components.button.ButtonComponentType;
import io.cyborgcode.roa.ui.selenium.ButtonUiElement;
import io.cyborgcode.roa.ui.selenium.smart.SmartWebDriver;
import ${package}.ui.functions.ContextConsumer;
import ${package}.ui.functions.SharedUi;
import ${package}.ui.functions.SharedUiFunctions;
import ${package}.ui.types.ButtonFieldTypes;
import org.openqa.selenium.By;

import java.util.function.Consumer;

/**
 * Example button elements for demonstration and quick-start usage.
 */
public enum ButtonFields implements ButtonUiElement {

    GENERIC_BUTTON(
            By.cssSelector("button"),
            ButtonFieldTypes.EXAMPLE_BUTTON_TYPE,
            SharedUi.WAIT_FOR_LOADING
    );

    private final By locator;
    private final ButtonComponentType componentType;
    private final Consumer<SmartWebDriver> before;
    private final Consumer<SmartWebDriver> after;

    ButtonFields(By locator,
                 ButtonComponentType componentType,
                 ContextConsumer before) {
        this(locator, componentType, before.asConsumer(locator), smartWebDriver -> {
        });
    }

    ButtonFields(By locator,
                 ButtonComponentType componentType,
                 Consumer<SmartWebDriver> before,
                 Consumer<SmartWebDriver> after) {
        this.locator = locator;
        this.componentType = componentType;
        this.before = before;
        this.after = after;
    }

    ButtonFields(By locator,
                 ButtonComponentType componentType,
                 ContextConsumer before,
                 ContextConsumer after) {
        this(locator, componentType, before.asConsumer(locator), after.asConsumer(locator));
    }

    ButtonFields(By locator,
                 ButtonComponentType componentType,
                 ContextConsumer before,
                 Consumer<SmartWebDriver> after) {
        this(locator, componentType, before.asConsumer(locator), after);
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

    private static void waitForPresence(SmartWebDriver driver) {
        SharedUiFunctions.waitForPresence(driver,
                By.cssSelector("vaadin-dialog-overlay#overlay"));
    }
}