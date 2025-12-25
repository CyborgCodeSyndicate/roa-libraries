package ${package}.ui.components.select;

import io.cyborgcode.roa.ui.components.base.BaseComponent;
import io.cyborgcode.roa.ui.components.select.Select;
import io.cyborgcode.roa.ui.selenium.smart.SmartWebDriver;
import io.cyborgcode.roa.ui.selenium.smart.SmartWebElement;
import org.openqa.selenium.By;
import java.util.List;
import java.util.Collections;

//TODO: Add the input type in the @ImplementationOfType applicable to the app
//@ImplementationOfType(SelectFieldTypes.Data.EXAMPLE_SELECT_TYPE)
public class SelectExampleImplAI extends BaseComponent implements Select {

    public SelectExampleImplAI(SmartWebDriver driver) {
        super(driver);
    }

    @Override
    public void selectOptions(SmartWebElement container, String... values) {
    }

    @Override
    public void selectOptions(By containerLocator, String... values) {
    }

    @Override
    public List<String> selectOptions(SmartWebElement container, Strategy strategy) {
        return null;
    }

    @Override
    public List<String> selectOptions(By containerLocator, Strategy strategy) {
        return null;
    }

    @Override
    public List<String> getAvailableOptions(SmartWebElement container) {
        return null;
    }

    @Override
    public List<String> getAvailableOptions(By locator) {
        return null;
    }

    @Override
    public List<String> getSelectedOptions(SmartWebElement container) {
        return null;
    }

    @Override
    public List<String> getSelectedOptions(By locator) {
        return null;
    }

    @Override
    public boolean isOptionVisible(SmartWebElement container, String value) {
        return false;
    }

    @Override
    public boolean isOptionEnabled(By locator, String visibleText) {
        return false;
    }

    @Override
    public boolean isOptionVisible(By locator, String visibleText) {
        return false;
    }

    @Override
    public boolean isOptionEnabled(SmartWebElement container, String value) {
        return false;
    }
}
