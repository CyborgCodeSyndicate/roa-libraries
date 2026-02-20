package io.cyborgcode.roa.ui.components.checkbox;

import io.cyborgcode.roa.ui.util.strategy.Strategy;
import java.util.List;

/**
 * Defines operations for interacting with checkbox UI elements.
 *
 * @param <E> The container/element type (e.g., Playwright's {@code Locator} or Selenium's {@code WebElement}).
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface CheckboxCore<E> {

   void select(E container, String... checkBoxText);

   void select(E container, Strategy strategy);

   void select(String... checkBoxText);

   void deSelect(E container, String... checkBoxText);

   void deSelect(E container, Strategy strategy);

   void deSelect(String... checkBoxText);

   boolean areSelected(E container, String... checkBoxText);

   boolean areSelected(String... checkBoxText);

   boolean areEnabled(E container, String... checkBoxText);

   boolean areEnabled(String... checkBoxText);

   List<String> getSelected(E container);

   List<String> getAll(E container);

}
