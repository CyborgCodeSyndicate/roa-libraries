package ${package}.ui.elements;

import io.cyborgcode.roa.ui.components.base.ComponentType;
import io.cyborgcode.roa.ui.components.radio.RadioComponentType;
import io.cyborgcode.roa.ui.selenium.RadioUiElement;
import ${package}.ui.types.RadioFieldTypes;
import org.openqa.selenium.By;

/**
 * Example radio field registry for demonstration purposes.
 */
public enum RadioFields implements RadioUiElement {

    GENERIC_RADIO(
            By.cssSelector("input[type='radio']"),
            RadioFieldTypes.EXAMPLE_RADIO_TYPE
    ),

    YES_OPTION(
            By.cssSelector("input[value='yes']"),
            RadioFieldTypes.EXAMPLE_RADIO_TYPE
    );

    private final By locator;
    private final RadioComponentType componentType;

    RadioFields(By locator, RadioComponentType componentType) {
        this.locator = locator;
        this.componentType = componentType;
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
}
