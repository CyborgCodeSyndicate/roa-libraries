package ${package}.ui.elements;

import io.cyborgcode.roa.ui.components.base.ComponentType;
import io.cyborgcode.roa.ui.components.button.ButtonComponentType;
import io.cyborgcode.roa.ui.selenium.ButtonUiElement;
import io.cyborgcode.roa.ui.selenium.smart.SmartWebDriver;
import ${package}.ui.types.ButtonFieldTypes;
import org.openqa.selenium.By;

import java.util.function.Consumer;

/**
 * Registry of button elements for your UI tests.
 */
public enum ButtonFields implements ButtonUiElement {

    /**
     * Example: Login button.
     * <p>
     * Demonstrates a clickable element that can be defined with hooks to wait for it to
     * become clickable before interaction and to wait for the page to be loaded after
     * interaction. Can be also without any hooks. Example:
     * LOGIN_BUTTON(
     *             By.id("login-button"),
     *             ButtonFieldTypes.EXAMPLE_BUTTON_TYPE
     *             );
     * </p>
     */
    LOGIN_BUTTON(
            By.id("login-button"),
            ButtonFieldTypes.EXAMPLE_BUTTON_TYPE,
            // Before interaction hook:
            webDriver -> {
                // Example: webDriver.waitForClickable(By.id("login-button"));
            },
            // After interaction hook:
            webDriver -> {
                // Example: webDriver.waitForPageLoad();
            }
    );

    private final By locator;
    private final ButtonComponentType componentType;
    private final Consumer<SmartWebDriver> before;
    private final Consumer<SmartWebDriver> after;

    ButtonFields(By locator, ButtonComponentType componentType) {
        this(locator, componentType, smartWebDriver -> {
        }, smartWebDriver -> {
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
