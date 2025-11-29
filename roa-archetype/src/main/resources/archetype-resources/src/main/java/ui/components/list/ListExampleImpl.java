package ${package}.ui.components.list;

import io.cyborgcode.roa.ui.annotations.ImplementationOfType;
import io.cyborgcode.roa.ui.components.base.BaseComponent;
import io.cyborgcode.roa.ui.components.list.ItemList;
import io.cyborgcode.roa.ui.selenium.smart.*;
import ${package}.ui.types.ListFieldTypes;
import org.openqa.selenium.By;
import java.util.List;

/**
 * Minimal example implementation of an item list (e.g., tabs or menu lists).
 */
@ImplementationOfType(ListFieldTypes.Data.EXAMPLE_LIST)
public class ListExampleImpl extends BaseComponent implements ItemList {

   private static final By ITEM_LOCATOR = By.tagName("li");
    private static final By LABEL_LOCATOR = By.tagName("a");

    public ListExampleImpl(SmartWebDriver driver) {
        super(driver);
    }

    @Override
    public void select(SmartWebElement container, String... labels) {
        for (String label : labels) {
            SmartWebElement item = findItem(container, label);
            item.click();
        }
    }

    @Override
    public void select(By containerLocator, String... labels) {
        SmartWebElement container = driver.findSmartElement(containerLocator);
        select(container, labels);
    }

    @Override
    public String select(SmartWebElement container, Strategy strategy) {
        return "";
    }

    @Override
    public String select(By containerLocator, Strategy strategy) {
        return "";
    }

    @Override
    public void select(String... itemText) {

    }

    @Override
    public void select(By... itemListLocator) {

    }

    @Override
    public void deSelect(SmartWebElement container, String... itemText) {

    }

    @Override
    public void deSelect(By containerLocator, String... itemText) {

    }

    @Override
    public String deSelect(SmartWebElement container, Strategy strategy) {
        return "";
    }

    @Override
    public String deSelect(By containerLocator, Strategy strategy) {
        return "";
    }

    @Override
    public void deSelect(String... itemText) {

    }

    @Override
    public void deSelect(By... itemListLocator) {

    }

    @Override
    public boolean areSelected(SmartWebElement container, String... itemText) {
        return false;
    }

    @Override
    public boolean areSelected(By containerLocator, String... itemText) {
        return false;
    }

    @Override
    public boolean areSelected(String... itemText) {
        return false;
    }

    @Override
    public boolean areSelected(By... itemListLocator) {
        return false;
    }

    @Override
    public boolean areEnabled(SmartWebElement container, String... itemText) {
        return false;
    }

    @Override
    public boolean areEnabled(By containerLocator, String... itemText) {
        return false;
    }

    @Override
    public boolean areEnabled(String... itemText) {
        return false;
    }

    @Override
    public boolean areEnabled(By... itemLocator) {
        return false;
    }

    @Override
    public List<String> getAll(SmartWebElement container) {
        return container.findSmartElements(ITEM_LOCATOR).stream()
                .map(this::getLabel)
                .toList();
    }

    @Override
    public List<String> getAll(By containerLocator) {
        SmartWebElement container = driver.findSmartElement(containerLocator);
        return getAll(container);
    }

    @Override
    public List<String> getSelected(SmartWebElement container) {
        return container.findSmartElements(ITEM_LOCATOR).stream()
                .filter(e -> e.getDomAttribute("class") != null &&
                        e.getDomAttribute("class").contains("active"))
                .map(this::getLabel)
                .toList();
    }

    @Override
    public List<String> getSelected(By containerLocator) {
        SmartWebElement container = driver.findSmartElement(containerLocator);
        return getSelected(container);
    }

    @Override
    public boolean areVisible(SmartWebElement container, String... labels) {
        List<String> existing = getAll(container);
        for (String label : labels) {
            if (!existing.contains(label)) return false;
        }
        return true;
    }

    @Override
    public boolean areVisible(By containerLocator, String... labels) {
        SmartWebElement container = driver.findSmartElement(containerLocator);
        return areVisible(container, labels);
    }

    @Override
    public boolean areVisible(String... itemText) {
        return false;
    }

    @Override
    public boolean areVisible(By... itemLocator) {
        return false;
    }

    private SmartWebElement findItem(SmartWebElement container, String label) {
        return container.findSmartElements(ITEM_LOCATOR).stream()
                .filter(e -> getLabel(e).equalsIgnoreCase(label))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("List item '" + label + "' not found"));
    }

    private String getLabel(SmartWebElement item) {
        SmartWebElement label = item.findSmartElement(LABEL_LOCATOR);
        return label.getText().trim();
    }
}
