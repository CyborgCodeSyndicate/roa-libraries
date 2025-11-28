package ${package}.ui_module.ui.components.link;

import io.cyborgcode.roa.ui.annotations.ImplementationOfType;
import io.cyborgcode.roa.ui.components.base.BaseComponent;
import io.cyborgcode.roa.ui.components.link.Link;
import io.cyborgcode.roa.ui.selenium.smart.SmartWebDriver;
import io.cyborgcode.roa.ui.selenium.smart.SmartWebElement;
import ${package}.ui_module.ui.types.LinkFieldTypes;
import org.openqa.selenium.By;

/**
 * Minimal example implementation of the Link component.
 */
@ImplementationOfType(LinkFieldTypes.Data.EXAMPLE_LINK)
public class LinkExampleImpl extends BaseComponent implements Link {

   private static final By LINK_SELECTOR = By.tagName("a");

   public LinkExampleImpl(SmartWebDriver driver) {
      super(driver);
   }

   @Override
   public void click(String linkText) {
      SmartWebElement link = findByText(linkText);
      link.click();
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
   public void click(SmartWebElement container, String linkText) {
      SmartWebElement link = container.findSmartElements(LINK_SELECTOR).stream()
            .filter(e -> e.getText().contains(linkText))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Link '" + linkText + "' not found"));
      link.click();
   }

    @Override
    public void click(SmartWebElement container) {

    }

    @Override
   public boolean isVisible(String linkText) {
      SmartWebElement link = findByText(linkText);
      return link.isDisplayed();
   }

   @Override
   public boolean isVisible(By locator) {
      return driver.findSmartElement(locator).isDisplayed();
   }

   @Override
   public boolean isEnabled(String linkText) {
      SmartWebElement link = findByText(linkText);
      return link.isEnabled();
   }

   @Override
   public boolean isEnabled(By locator) {
      return driver.findSmartElement(locator).isEnabled();
   }

    @Override
    public boolean isVisible(SmartWebElement container, String buttonText) {
        return false;
    }

    @Override
    public boolean isVisible(SmartWebElement container) {
        return false;
    }

    private SmartWebElement findByText(String text) {
      return driver.findSmartElements(LINK_SELECTOR).stream()
            .filter(e -> e.getText().trim().contains(text))
            .findFirst()
            .orElseThrow(() ->
                  new RuntimeException("Link containing '" + text + "' not found"));
   }

    @Override
    public void doubleClick(SmartWebElement container, String buttonText) {
        
    }

    @Override
    public void doubleClick(SmartWebElement container) {

    }

    @Override
    public void doubleClick(String buttonText) {

    }

    @Override
    public void doubleClick(By buttonLocator) {

    }
}
