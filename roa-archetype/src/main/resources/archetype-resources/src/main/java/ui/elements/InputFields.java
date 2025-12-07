package ${package}.ui.elements;

import io.cyborgcode.roa.ui.components.base.ComponentType;
import io.cyborgcode.roa.ui.components.input.InputComponentType;
import io.cyborgcode.roa.ui.selenium.InputUiElement;
import io.cyborgcode.roa.ui.selenium.smart.SmartWebDriver;
import ${package}.ui.functions.SharedUi;
import ${package}.ui.types.InputFieldTypes;
import org.openqa.selenium.By;

import java.util.function.Consumer;

/**
 * Example input field elements for demonstration and quick-start usage.
 */
public enum InputFields implements InputUiElement {

    /**
     * Example:
     *
     * <p>TODO: implement your input fields here</p>
     */
    GENERIC_INPUT(
                    /*null, //locator
                    InputFieldTypes.EXAMPLE_INPUT_TYPE*/   //componentType
                    /*,webDriver -> {}, //if needed, implement your before actions here (e.g. wait for element to be present)
                    webDriver -> {}*/ //if needed, implement your after actions here (e.g. wait for element to be removed)
    );

    /**
     * Example:
     *
     * <p>TODO: implement your enum constructors here for different input field types</p>
     */
//    private final By locator;
//    private final InputComponentType componentType;
//    private final Consumer<SmartWebDriver> before;
//    private final Consumer<SmartWebDriver> after;
//
//    InputFields(By locator, InputComponentType componentType) {
//        this(locator, componentType, smartWebDriver -> {
//        }, smartWebDriver -> {
//        });
//    }
//
//    InputFields(By locator,
//                InputComponentType componentType,
//                Consumer<SmartWebDriver> before,
//                Consumer<SmartWebDriver> after) {
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
//        if (componentType == null) {
//            return InputUiElement.super.componentType();
//        }
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
