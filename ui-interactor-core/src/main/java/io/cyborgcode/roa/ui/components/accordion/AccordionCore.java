package io.cyborgcode.roa.ui.components.accordion;

import io.cyborgcode.roa.ui.util.strategy.Strategy;
import java.util.List;

/**
 * Defines operations for interacting with accordion UI elements.
 *
 * @param <E> The container/element type (e.g., Playwright's {@code Locator} or Selenium's {@code WebElement}).
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface AccordionCore<E> {

   void expand(E container, String... accordionText);

   String expand(E container, Strategy strategy);

   void expand(String... accordionText);

   void collapse(E container, String... accordionText);

   String collapse(E container, Strategy strategy);

   void collapse(String... accordionText);

   boolean areEnabled(E container, String... accordionText);

   boolean areEnabled(String... accordionText);

   List<String> getExpanded(E container);

   List<String> getCollapsed(E container);

   List<String> getAll(E container);

}
