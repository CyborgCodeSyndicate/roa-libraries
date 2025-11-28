package ${package}.ui_module.ui.components.button;

import io.cyborgcode.roa.ui.annotations.ImplementationOfType;
import io.cyborgcode.roa.ui.components.base.BaseComponent;
import io.cyborgcode.roa.ui.components.button.Button;
import io.cyborgcode.roa.ui.selenium.smart.SmartWebDriver;
import io.cyborgcode.roa.ui.selenium.smart.SmartWebElement;
import ${package}.ui_module.ui.types.ButtonFieldTypes;
import org.openqa.selenium.By;
import java.util.Objects;

/**
 * Minimal example implementation of the Button component.
 */
@ImplementationOfType(ButtonFieldTypes.Data.EXAMPLE_BUTTON)
public class ButtonExampleImpl extends BaseComponent implements Button {

   private static final By BUTTON_SELECTOR = By.tagName("button");

   public ButtonExampleImpl(SmartWebDriver driver) {
      super(driver);
   }

    @Override
    public void click(SmartWebElement container, String buttonText) {
        
    }

    @Override
    public void click(SmartWebElement container) {

    }

    @Override
   public void click(String buttonText) {
      SmartWebElement button = findByText(buttonText);
      button.click();
   }

   @Override
   public void click(By locator) {
      driver.findSmartElement(locator).click();
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
      SmartWebElement button = findByText(buttonText);
      return !Objects.requireNonNull(button.getDomAttribute("class"))
            .contains("disabled");
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
      SmartWebElement button = findByText(buttonText);
      return button.isDisplayed();
   }

    @Override
    public boolean isVisible(By buttonLocator) {
        return false;
    }

    private SmartWebElement findByText(String text) {
      return driver.findSmartElements(BUTTON_SELECTOR).stream()
            .filter(e -> e.getText().contains(text))
            .findFirst()
            .orElseThrow(() ->
                  new RuntimeException("Button with text '" + text + "' not found"));
   }
}
