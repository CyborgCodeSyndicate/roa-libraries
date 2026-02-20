package io.cyborgcode.roa.ui.components.radio;

import io.cyborgcode.roa.ui.util.strategy.Strategy;
import java.util.List;

/**
 * Defines operations for interacting with radio button UI elements.
 *
 * @param <E> The container/element type (e.g., Playwright's {@code Locator} or Selenium's {@code WebElement}).
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface RadioCore<E> {

   void select(E container, String radioButtonText);

   void select(E container, Strategy strategy);

   void select(String radioButtonText);

   boolean isEnabled(E container, String radioButtonText);

   boolean isEnabled(String radioButtonText);

   boolean isSelected(E container, String radioButtonText);

   boolean isSelected(String radioButtonText);

   boolean isVisible(E container, String radioButtonText);

   boolean isVisible(String radioButtonText);

   String getSelected(E container);

   List<String> getAll(E container);

}
