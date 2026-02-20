package io.cyborgcode.roa.ui.components.list;

import io.cyborgcode.roa.ui.util.strategy.Strategy;
import java.util.List;

/**
 * Defines operations for interacting with list UI elements.
 *
 * @param <E> The container/element type (e.g., Playwright's {@code Locator} or Selenium's {@code WebElement}).
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface ItemListCore<E> {

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

}
