package ${package}.ui.components.select;

import io.cyborgcode.roa.ui.annotations.ImplementationOfType;
import io.cyborgcode.roa.ui.components.base.BaseComponent;
import io.cyborgcode.roa.ui.components.select.Select;
import io.cyborgcode.roa.ui.selenium.smart.SmartWebDriver;
import io.cyborgcode.roa.ui.selenium.smart.SmartWebElement;
import ${package}.ui.types.SelectFieldTypes;
import org.openqa.selenium.By;

import java.util.List;

/**
 * Minimal example implementation of the Select component.
 */
@ImplementationOfType(SelectFieldTypes.Data.EXAMPLE_SELECT)
public class SelectExampleImpl extends BaseComponent implements Select {

   private static final By OPTION_SELECTOR = By.tagName("option");

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
        return List.of();
    }

    @Override
    public List<String> selectOptions(By containerLocator, Strategy strategy) {
        return List.of();
    }

    @Override
    public List<String> getAvailableOptions(SmartWebElement container) {
        return List.of();
    }

    @Override
   public List<String> getAvailableOptions(By locator) {
      SmartWebElement select = driver.findSmartElement(locator);
      return select.findSmartElements(OPTION_SELECTOR).stream()
            .map(e -> e.getText().trim())
            .toList();
   }

    @Override
    public List<String> getSelectedOptions(SmartWebElement container) {
        return List.of();
    }

    @Override
   public List<String> getSelectedOptions(By locator) {
      SmartWebElement select = driver.findSmartElement(locator);
      return select.findSmartElements(OPTION_SELECTOR).stream()
            .filter(SmartWebElement::isSelected)
            .map(e -> e.getText().trim())
            .toList();
   }

    @Override
    public boolean isOptionVisible(SmartWebElement container, String value) {
        return false;
    }

    @Override
   public boolean isOptionEnabled(By locator, String visibleText) {
      SmartWebElement select = driver.findSmartElement(locator);
      SmartWebElement option = findOption(select, visibleText);
      return option.isEnabled();
   }

   @Override
   public boolean isOptionVisible(By locator, String visibleText) {
      SmartWebElement select = driver.findSmartElement(locator);
      SmartWebElement option = findOption(select, visibleText);
      return option.isDisplayed();
   }

    @Override
    public boolean isOptionEnabled(SmartWebElement container, String value) {
        return false;
    }

    private SmartWebElement findFirstSelect() {
      return driver.findSmartElements(By.tagName("select")).stream()
            .findFirst()
            .orElseThrow(() -> new RuntimeException("No <select> elements found"));
   }

   private SmartWebElement findOption(SmartWebElement select, String visibleText) {
      return select.findSmartElements(OPTION_SELECTOR).stream()
            .filter(e -> e.getText().trim().equalsIgnoreCase(visibleText))
            .findFirst()
            .orElseThrow(() ->
                  new RuntimeException("Option '" + visibleText + "' not found"));
   }
}
