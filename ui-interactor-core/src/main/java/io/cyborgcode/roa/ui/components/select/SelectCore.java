package io.cyborgcode.roa.ui.components.select;

import io.cyborgcode.roa.ui.util.strategy.Strategy;
import java.util.List;

/**
 * Defines operations for interacting with select (dropdown) UI elements.
 *
 * @param <E> The container/element type (e.g., Playwright's {@code Locator} or Selenium's {@code WebElement}).
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface SelectCore<E> {

   void selectOptions(E container, String... values);

   List<String> selectOptions(E container, Strategy strategy);

   List<String> getAvailableOptions(E container);

   List<String> getSelectedOptions(E container);

   boolean isOptionVisible(E container, String value);

   boolean isOptionEnabled(E container, String value);

}
