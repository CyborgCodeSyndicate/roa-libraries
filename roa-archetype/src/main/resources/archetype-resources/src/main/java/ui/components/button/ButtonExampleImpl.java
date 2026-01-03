package ${package}.ui.components.button;

import io.cyborgcode.roa.ui.annotations.ImplementationOfType;
import io.cyborgcode.roa.ui.components.base.BaseComponent;
import io.cyborgcode.roa.ui.components.button.Button;
import io.cyborgcode.roa.ui.selenium.smart.SmartWebDriver;
import io.cyborgcode.roa.ui.selenium.smart.SmartWebElement;
import ${package}.ui.types.ButtonFieldTypes;
import org.openqa.selenium.By;

import java.util.Objects;

// TODO: Replace this dummy example implementation with your real Button component implementation
//       (update @ImplementationOfType target + implement methods).
@ImplementationOfType(ButtonFieldTypes.Data.EXAMPLE_BUTTON_TYPE)
public class ButtonExampleImpl extends BaseComponent implements Button {


    public ButtonExampleImpl(SmartWebDriver driver) {
        super(driver);
    }

    @Override
    public void click(SmartWebElement container, String buttonText) {

    }

    @Override
    public void click(SmartWebElement container) {
        // Example:
        // driver.findSmartElement(By.cssSelector(".small-btn")).click();
    }

    @Override
    public void click(String buttonText) {

    }

    @Override
    public void click(By locator) {

    }

    @Override
    public boolean isEnabled(SmartWebElement container, String buttonText) {
        return false;
    }

    @Override
    public boolean isEnabled(SmartWebElement container) {
        return false;
    }

    @Override
    public boolean isEnabled(String buttonText) {
        return false;
    }

    @Override
    public boolean isEnabled(By buttonLocator) {
        return false;
    }

    @Override
    public boolean isVisible(SmartWebElement container, String buttonText) {
        return false;
    }

    @Override
    public boolean isVisible(SmartWebElement container) {
        return false;
    }

    @Override
    public boolean isVisible(String buttonText) {
        return false;
    }

    @Override
    public boolean isVisible(By buttonLocator) {
        return false;
    }
}
