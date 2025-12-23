package ${package}.ui.components.select;

import io.cyborgcode.roa.ui.annotations.ImplementationOfType;
import io.cyborgcode.roa.ui.components.base.BaseComponent;
import io.cyborgcode.roa.ui.components.select.Select;
import io.cyborgcode.roa.ui.selenium.smart.SmartWebDriver;
import io.cyborgcode.roa.ui.selenium.smart.SmartWebElement;
import io.cyborgcode.roa.ui.util.strategy.Strategy;
import ${package}.ui.types.SelectFieldTypes;
import org.openqa.selenium.By;

import java.util.List;

/**
 * Minimal example implementation of the Select component.
 */
//@ImplementationOfType(SelectFieldTypes.Data.EXAMPLE_SELECT_TYPE)
public class SelectExampleImpl extends BaseComponent implements Select {

    /**
     * Example locator that can be used in the methods:
     */
//    private static final By OPTIONS_SELECTOR = By.tagName("options");

    public SelectExampleImpl(SmartWebDriver driver) {
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
//        List<String> availableOptions = options.stream()
//                .map(option -> option.findSmartElement(OPTIONS_SELECTOR).getText().trim()).toList();
//        return availableOptions;
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
