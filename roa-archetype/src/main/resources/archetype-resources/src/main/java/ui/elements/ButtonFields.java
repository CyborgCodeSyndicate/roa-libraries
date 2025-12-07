package ${package}.ui.elements;

import io.cyborgcode.roa.ui.components.base.ComponentType;
import io.cyborgcode.roa.ui.components.button.ButtonComponentType;
import io.cyborgcode.roa.ui.selenium.ButtonUiElement;
import io.cyborgcode.roa.ui.selenium.smart.SmartWebDriver;
import ${package}.ui.functions.SharedUi;
import ${package}.ui.types.ButtonFieldTypes;
import org.openqa.selenium.By;

import java.util.function.Consumer;

/**
 * Example button field elements for demonstration and quick-start usage.
 */
public enum ButtonFields implements ButtonUiElement {

    /**
     * Example:
     *
     * <p>TODO: implement your button fields here</p>
     */
    GENERIC_BUTTON(
                    /*null, //locator
                    ButtonFieldTypes.EXAMPLE_BUTTON_TYPE*/ //componentType
                    /*,webDriver -> {}, //if needed, implement your before actions here (e.g. wait for element to be present)
                    webDriver -> {}*/ //if needed, implement your after actions here (e.g. wait for element to be removed)
    );


    /**
     * Example:
     *
     * <p>TODO: implement your enum constructors here for different button field types</p>
     */
//    private final By locator;
//    private final ButtonComponentType componentType;
//    private final Consumer<SmartWebDriver> before;
//    private final Consumer<SmartWebDriver> after;
//
//    ButtonFields(By locator, ButtonComponentType componentType) {
//        this(locator, componentType, smartWebDriver -> {
//        }, smartWebDriver -> {
//        });
//    }
//
//    ButtonFields(By locator,
//                 ButtonComponentType componentType,
//                 Consumer<SmartWebDriver> before,
//                 Consumer<SmartWebDriver> after) {
//        this.locator = locator;
//        this.componentType = componentType;
//        this.before = before;
//        this.after = after;
//    }

    @Override
    public By locator() {
//        return locator;
        return null;
    }

    @Override
    public <T extends ComponentType> T componentType() {
//        return (T) componentType;
        return null;
    }

    @Override
    public Enum<?> enumImpl() {
//        return this;
        return null;
    }

    @Override
    public Consumer<SmartWebDriver> before() {
//        return before;
        return null;
    }

    @Override
    public Consumer<SmartWebDriver> after() {
//        return after;
        return null;
    }
}