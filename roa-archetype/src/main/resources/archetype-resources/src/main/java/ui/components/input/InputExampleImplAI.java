package ${package}.ui.components.input;

import io.cyborgcode.roa.ui.components.base.BaseComponent;
import io.cyborgcode.roa.ui.components.input.Input;
import io.cyborgcode.roa.ui.selenium.smart.SmartWebDriver;
import io.cyborgcode.roa.ui.selenium.smart.SmartWebElement;
import org.openqa.selenium.By;

//TODO: Add the input type in the @ImplementationOfType applicable to the app
//@ImplementationOfType(InputFieldTypes.Data.EXAMPLE_INPUT_TYPE)
public class InputExampleImplAI extends BaseComponent implements Input {

    public InputExampleImplAI(SmartWebDriver driver) {
        super(driver);
    }

    @Override
    public void insert(SmartWebElement container, String value) {}

    @Override
    public void insert(SmartWebElement container, String inputFieldLabel, String value) {}

    @Override
    public void insert(String label, String value) {}

    @Override
    public void insert(By locator, String value) {}

    @Override
    public void clear(SmartWebElement container) {}

    @Override
    public void clear(SmartWebElement container, String inputFieldLabel) {}

    @Override
    public void clear(String label) {}

    @Override
    public void clear(By locator) {}

    @Override
    public String getValue(SmartWebElement container) { return ""; }

    @Override
    public String getValue(SmartWebElement container, String inputFieldLabel) { return ""; }

    @Override
    public String getValue(String label) { return ""; }

    @Override
    public String getValue(By locator) { return ""; }

    @Override
    public boolean isEnabled(SmartWebElement container) { return false; }

    @Override
    public boolean isEnabled(SmartWebElement container, String inputFieldLabel) { return false; }

    @Override
    public boolean isEnabled(String label) { return false; }

    @Override
    public boolean isEnabled(By locator) { return false; }

    @Override
    public String getErrorMessage(SmartWebElement container) { return ""; }

    @Override
    public String getErrorMessage(SmartWebElement container, String inputFieldLabel) { return ""; }

    @Override
    public String getErrorMessage(String inputFieldLabel) { return ""; }

    @Override
    public String getErrorMessage(By inputFieldContainerLocator) { return ""; }
}
