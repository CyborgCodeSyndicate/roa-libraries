package ${package}.ui.components.radio;

import io.cyborgcode.roa.ui.annotations.ImplementationOfType;
import io.cyborgcode.roa.ui.components.base.BaseComponent;
import io.cyborgcode.roa.ui.components.radio.Radio;
import io.cyborgcode.roa.ui.selenium.smart.SmartWebDriver;
import io.cyborgcode.roa.ui.selenium.smart.SmartWebElement;
import ${package}.ui.types.RadioFieldTypes;
import org.openqa.selenium.By;

/**
 * Minimal example implementation of the Radio component.
 */
@ImplementationOfType(RadioFieldTypes.Data.EXAMPLE_RADIO)
public class RadioExampleImpl extends BaseComponent implements Radio {

    private static final By RADIO_SELECTOR = By.cssSelector("input[type='radio']");

    public RadioExampleImpl(SmartWebDriver driver) {
        super(driver);
    }

    @Override
    public void select(SmartWebElement container, String radioButtonText) {

    }

    @Override
    public String select(SmartWebElement container, Strategy strategy) {
        return "";
    }

    @Override
    public void select(String label) {
        SmartWebElement radio = findByLabel(label);
        radio.click();
    }

    @Override
    public void select(By locator) {
        driver.findSmartElement(locator).click();
    }

    @Override
    public boolean isEnabled(SmartWebElement container, String radioButtonText) {
        return false;
    }

    @Override
    public boolean isSelected(String label) {
        SmartWebElement radio = findByLabel(label);
        return radio.isSelected();
    }

    @Override
    public boolean isSelected(By locator) {
        return driver.findSmartElement(locator).isSelected();
    }

    @Override
    public boolean isVisible(SmartWebElement container, String radioButtonText) {
        return false;
    }

    @Override
    public boolean isEnabled(String label) {
        SmartWebElement radio = findByLabel(label);
        return radio.isEnabled();
    }

    @Override
    public boolean isEnabled(By locator) {
        return driver.findSmartElement(locator).isEnabled();
    }

    @Override
    public boolean isSelected(SmartWebElement container, String radioButtonText) {
        return false;
    }

    @Override
    public boolean isVisible(String label) {
        SmartWebElement radio = findByLabel(label);
        return radio.isDisplayed();
    }

    @Override
    public boolean isVisible(By locator) {
        return driver.findSmartElement(locator).isDisplayed();
    }

    @Override
    public String getSelected(SmartWebElement container) {
        return "";
    }

    @Override
    public String getSelected(By groupLocator) {
        return driver.findSmartElement(groupLocator)
                .findSmartElements(RADIO_SELECTOR).stream()
                .filter(SmartWebElement::isSelected)
                .findFirst()
                .map(this::findLabelForRadio)
                .orElse(null);
    }

    @Override
    public List<String> getAll(SmartWebElement container) {
        return List.of();
    }

    @Override
    public List<String> getAll(By containerLocator) {
        return List.of();
    }

    private SmartWebElement findByLabel(String labelText) {
        return driver.findSmartElements(RADIO_SELECTOR).stream()
                .filter(r -> {
                    String label = findLabelForRadio(r);
                    return label.equalsIgnoreCase(labelText);
                })
                .findFirst()
                .orElseThrow(() ->
                        new RuntimeException("Radio with label '" + labelText + "' not found"));
    }

    /**
     * Returns the label text associated with a radio button.
     * Assumes structure:
     * <label>
     * <input type="radio"> Label Text
     * </label>
     */
    private String findLabelForRadio(SmartWebElement radio) {
        SmartWebElement parent = radio.findSmartElement(By.xpath("./.."));
        return parent.getText().trim();
    }
}
