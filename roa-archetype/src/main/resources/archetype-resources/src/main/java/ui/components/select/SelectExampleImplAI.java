package ${package}.ui.components.select;

import io.cyborgcode.roa.ui.components.base.BaseComponent;
import io.cyborgcode.roa.ui.components.select.Select;
import io.cyborgcode.roa.ui.selenium.smart.SmartWebDriver;
import io.cyborgcode.roa.ui.selenium.smart.SmartWebElement;
import org.openqa.selenium.By;
import java.util.List;
import java.util.Collections;

public class SelectExampleImplAI extends BaseComponent implements Select {

    public SelectExampleImplAI(SmartWebDriver driver) {
        super(driver);
    }

    @Override
    public void selectOption(SmartWebElement container, String option) {}

    @Override
    public void selectOption(SmartWebElement container, String label, String option) {}

    @Override
    public void selectOption(String label, String option) {}

    @Override
    public void selectOption(By locator, String option) {}

    @Override
    public String getSelectedOption(SmartWebElement container) { return ""; }

    @Override
    public String getSelectedOption(SmartWebElement container, String label) { return ""; }

    @Override
    public String getSelectedOption(String label) { return ""; }

    @Override
    public String getSelectedOption(By locator) { return ""; }

    @Override
    public List<String> getAvailableOptions(SmartWebElement container) { return Collections.emptyList(); }

    @Override
    public List<String> getAvailableOptions(SmartWebElement container, String label) { return Collections.emptyList(); }

    @Override
    public List<String> getAvailableOptions(String label) { return Collections.emptyList(); }

    @Override
    public List<String> getAvailableOptions(By locator) { return Collections.emptyList(); }

    @Override
    public boolean isEnabled(SmartWebElement container) { return false; }

    @Override
    public boolean isEnabled(SmartWebElement container, String label) { return false; }

    @Override
    public boolean isEnabled(String label) { return false; }

    @Override
    public boolean isEnabled(By locator) { return false; }

    @Override
    public String getErrorMessage(SmartWebElement container) { return ""; }

    @Override
    public String getErrorMessage(SmartWebElement container, String label) { return ""; }

    @Override
    public String getErrorMessage(String label) { return ""; }

    @Override
    public String getErrorMessage(By locator) { return ""; }
}
