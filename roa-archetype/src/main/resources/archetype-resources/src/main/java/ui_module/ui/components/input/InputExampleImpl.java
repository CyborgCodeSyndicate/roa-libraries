package ${package}.ui_module.ui.components.input;

import io.cyborgcode.roa.ui.annotations.ImplementationOfType;
import io.cyborgcode.roa.ui.components.base.BaseComponent;
import io.cyborgcode.roa.ui.components.input.Input;
import io.cyborgcode.roa.ui.selenium.smart.SmartWebDriver;
import io.cyborgcode.roa.ui.selenium.smart.SmartWebElement;
import ${package}.ui_module.ui.types.InputFieldTypes;
import org.openqa.selenium.By;

/**
 * Minimal example implementation of the Input component.
 */
@ImplementationOfType(InputFieldTypes.Data.EXAMPLE_INPUT)
public class InputExampleImpl extends BaseComponent implements Input {

   private static final By INPUT_SELECTOR = By.tagName("input");
   private static final String VALUE_ATTR = "value";

   public InputExampleImpl(SmartWebDriver driver) {
      super(driver);
   }

    @Override
    public void insert(SmartWebElement container, String value) {

    }

    @Override
    public void insert(SmartWebElement container, String inputFieldLabel, String value) {

    }

    @Override
   public void insert(String label, String value) {
      SmartWebElement input = findByLabel(label);
      input.clearAndSendKeys(value);
   }

   @Override
   public void insert(By locator, String value) {
      SmartWebElement input = driver.findSmartElement(locator);
      input.clearAndSendKeys(value);
   }

    @Override
    public void clear(SmartWebElement container) {

    }

    @Override
    public void clear(SmartWebElement container, String inputFieldLabel) {

    }

    @Override
   public void clear(String label) {
      SmartWebElement input = findByLabel(label);
      input.clear();
   }

   @Override
   public void clear(By locator) {
      driver.findSmartElement(locator).clear();
   }

    @Override
    public String getValue(SmartWebElement container) {
        return "";
    }

    @Override
    public String getValue(SmartWebElement container, String inputFieldLabel) {
        return "";
    }

    @Override
   public String getValue(String label) {
      SmartWebElement input = findByLabel(label);
      return input.getDomAttribute(VALUE_ATTR);
   }

   @Override
   public String getValue(By locator) {
      return driver.findSmartElement(locator).getDomAttribute(VALUE_ATTR);
   }

    @Override
    public boolean isEnabled(SmartWebElement container) {
        return false;
    }

    @Override
    public boolean isEnabled(SmartWebElement container, String inputFieldLabel) {
        return false;
    }

    @Override
   public boolean isEnabled(String label) {
      SmartWebElement input = findByLabel(label);
      return input.isEnabled();
   }

   @Override
   public boolean isEnabled(By locator) {
      return driver.findSmartElement(locator).isEnabled();
   }

    @Override
    public String getErrorMessage(SmartWebElement container) {
        return "";
    }

    @Override
    public String getErrorMessage(SmartWebElement container, String inputFieldLabel) {
        return "";
    }

    @Override
    public String getErrorMessage(String inputFieldLabel) {
        return "";
    }

    @Override
    public String getErrorMessage(By inputFieldContainerLocator) {
        return "";
    }

    private SmartWebElement findFirstInput() {
      return driver.findSmartElements(INPUT_SELECTOR).stream()
            .findFirst()
            .orElseThrow(() -> new RuntimeException("No <input> elements found"));
   }

   private SmartWebElement findByLabel(String labelText) {
      return driver.findSmartElements(INPUT_SELECTOR).stream()
            .filter(e -> {
               SmartWebElement label = e.findSmartElement(By.tagName("label"));
               return label != null && label.getText().trim().equalsIgnoreCase(labelText);
            })
            .findFirst()
            .orElseThrow(() ->
                  new RuntimeException("Input field with label '" + labelText + "' not found"));
   }
}
