package ${package}.ui.components.button;

import io.cyborgcode.roa.ui.components.base.BaseComponent;
import io.cyborgcode.roa.ui.components.button.Button;
import io.cyborgcode.roa.ui.selenium.smart.SmartWebDriver;
import io.cyborgcode.roa.ui.selenium.smart.SmartWebElement;
import org.openqa.selenium.By;

//TODO: Add the button type in the @ImplementationOfType applicable to the app
public class ButtonExampleImplAI extends BaseComponent implements Button {

    public ButtonExampleImplAI(SmartWebDriver driver) {
        super(driver);
    }

    @Override
    public void click(SmartWebElement container, String buttonText) {}

    @Override
    public void click(SmartWebElement container) {}

    @Override
    public void click(String buttonText) {}

    @Override
    public void click(By locator) {}

    @Override
    public boolean isEnabled(SmartWebElement container, String buttonText) { return false; }

    @Override
    public boolean isEnabled(SmartWebElement container) { return false; }

    @Override
    public boolean isEnabled(String buttonText) { return false; }

    @Override
    public boolean isEnabled(By buttonLocator) { return false; }

    @Override
    public boolean isVisible(SmartWebElement container, String buttonText) { return false; }

    @Override
    public boolean isVisible(SmartWebElement container) { return false; }

    @Override
    public boolean isVisible(String buttonText) { return false; }

    @Override
    public boolean isVisible(By buttonLocator) { return false; }
}
