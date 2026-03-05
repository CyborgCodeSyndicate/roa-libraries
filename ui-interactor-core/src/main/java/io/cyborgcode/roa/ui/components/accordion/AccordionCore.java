package io.cyborgcode.roa.ui.components.accordion;

import io.cyborgcode.roa.ui.components.base.BaseUiElement;
import io.cyborgcode.roa.ui.util.strategy.Strategy;
import java.util.List;

/**
 * Defines operations for interacting with accordion UI elements.
 *
 * @param <E> The container/element type (e.g., Playwright's {@code Locator} or Selenium's {@code WebElement}).
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface AccordionCore<E extends BaseUiElement, L> {

   void expand(E container, String... accordionText);

   String expand(E container, Strategy strategy);

   void expand(L... accordionLocator);


   void expand(String... accordionText);

   void collapse(E container, String... accordionText);

   String collapse(E container, Strategy strategy);

   void collapse(L... accordionLocator);

   void collapse(String... accordionText);

   boolean areEnabled(E container, String... accordionText);

   boolean areEnabled(L... accordionLocator);

   boolean areEnabled(String... accordionText);

   List<String> getExpanded(E container);

   List<String> getCollapsed(E container);

   List<String> getAll(E container);


   List<String> getExpanded(L containerLocator);

   List<String> getCollapsed(L containerLocator);

   List<String> getAll(L containerLocator);

   String getTitle(L accordionLocator);

   String getText(L accordionLocator);

}
