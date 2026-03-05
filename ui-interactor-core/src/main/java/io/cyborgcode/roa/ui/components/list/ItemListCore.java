package io.cyborgcode.roa.ui.components.list;

import io.cyborgcode.roa.ui.components.base.BaseUiElement;
import io.cyborgcode.roa.ui.util.strategy.Strategy;
import java.util.List;

/**
 * Defines operations for interacting with list UI elements.
 *
 * @param <E> The container/element type (e.g., Playwright's {@code Locator} or Selenium's {@code WebElement}).
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface ItemListCore<E extends BaseUiElement, L> {

   void select(E container, String... itemText);

   void select(E container, Strategy strategy);

   void select(String... itemText);

   void deSelect(E container, String... itemText);

   void deSelect(E container, Strategy strategy);

   void deSelect(String... itemText);

   boolean areSelected(E container, String... itemText);

   boolean areSelected(String... itemText);

   boolean areEnabled(E container, String... itemText);

   boolean areEnabled(String... itemText);

   boolean areVisible(E container, String... itemText);

   boolean areVisible(String... itemText);

   List<String> getSelected(E container);

   List<String> getAll(E container);

   void select(L containerLocator, String... itemText);

   String select(L containerLocator, Strategy strategy);

   void select(L... itemListLocator);

   void deSelect(L containerLocator, String... itemText);

   String deSelect(L containerLocator, Strategy strategy);

   void deSelect(L... itemListLocator);

   boolean areSelected(L containerLocator, String... itemText);

   boolean areSelected(L... itemListLocator);

   boolean areEnabled(L containerLocator, String... itemText);

   boolean areEnabled(L... itemLocator);

   boolean areVisible(L containerLocator, String... itemText);

   boolean areVisible(L... itemLocator);

   List<String> getSelected(L containerLocator);

   List<String> getAll(L containerLocator);

}
