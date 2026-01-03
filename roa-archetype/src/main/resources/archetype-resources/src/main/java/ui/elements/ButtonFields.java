package ${package}.ui.elements;

import io.cyborgcode.roa.ui.components.base.ComponentType;
import io.cyborgcode.roa.ui.components.button.ButtonComponentType;
import io.cyborgcode.roa.ui.selenium.ButtonUiElement;
import io.cyborgcode.roa.ui.selenium.smart.SmartWebDriver;
import ${package}.ui.types.ButtonFieldTypes;
import org.openqa.selenium.By;

import java.util.function.Consumer;

// TODO: Replace LOGIN_BUTTON with your real button entries and add more button elements as needed.
public enum ButtonFields implements ButtonUiElement {

    LOGIN_BUTTON(
            By.id("login-button"),
            ButtonFieldTypes.EXAMPLE_BUTTON_TYPE,
            driver -> {
                // Example: driver.waitForClickable(By.id("login-button"));
            },
            driver -> {
                // Example: driver.waitForPageLoad();
            }
    );

    private final By locator;
    private final ButtonComponentType componentType;
    private final Consumer<SmartWebDriver> before;
    private final Consumer<SmartWebDriver> after;

    ButtonFields(By locator, ButtonComponentType componentType) {
        this(locator, componentType, driver -> {}, driver -> {});
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
