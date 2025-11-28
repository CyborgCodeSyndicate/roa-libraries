package ${package}.ui_module.ui.components.alert;

import io.cyborgcode.roa.ui.annotations.ImplementationOfType;
import io.cyborgcode.roa.ui.components.alert.Alert;
import io.cyborgcode.roa.ui.components.base.BaseComponent;
import io.cyborgcode.roa.ui.selenium.smart.SmartWebDriver;
import io.cyborgcode.roa.ui.selenium.smart.SmartWebElement;
import ${package}.ui_module.ui.types.AlertFieldTypes;
import org.openqa.selenium.By;

/**
 * Minimal example implementation of the Alert component.
 */
@ImplementationOfType(AlertFieldTypes.Data.EXAMPLE_ALERT)
public class AlertExampleImpl extends BaseComponent implements Alert {

   private static final By ALERT_SELECTOR = By.className("alert");

   public AlertExampleImpl(SmartWebDriver driver) {
      super(driver);
   }

   @Override
   public String getValue(SmartWebElement container) {
      SmartWebElement alert = container.findSmartElement(ALERT_SELECTOR);
      return alert.getText().trim();
   }

   @Override
   public String getValue(By locator) {
      return driver.findSmartElement(locator).getText().trim();
   }

   @Override
   public boolean isVisible(SmartWebElement container) {
      return driver.checkNoException(() -> container.findSmartElement(ALERT_SELECTOR))
            && container.findSmartElement(ALERT_SELECTOR).isDisplayed();
   }

   @Override
   public boolean isVisible(By locator) {
      return driver.checkNoException(() -> driver.findSmartElement(locator))
            && driver.findSmartElement(locator).isDisplayed();
   }
}
