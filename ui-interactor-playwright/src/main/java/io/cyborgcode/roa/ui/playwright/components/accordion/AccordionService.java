package io.cyborgcode.roa.ui.playwright.components.accordion;

import com.microsoft.playwright.Locator;
import io.cyborgcode.roa.ui.components.accordion.AccordionComponentType;
import io.cyborgcode.roa.ui.components.accordion.AccordionServiceCore;
import io.cyborgcode.roa.ui.playwright.base.PwBy;
import java.util.List;

/**
 * Playwright-specific accordion service interface.
 *
 * <p>Binds the core generic {@link AccordionServiceCore}
 * to Playwright's {@link Locator} type. All method contracts are inherited from core.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface AccordionService extends AccordionServiceCore<Locator> {

   default void expand(PwBy... accordionLocator) {
      expand(DEFAULT_TYPE, accordionLocator);
   }

   void expand(AccordionComponentType componentType, PwBy... accordionLocator);

   default void collapse(PwBy... accordionLocator) {
      collapse(DEFAULT_TYPE, accordionLocator);
   }

   void collapse(AccordionComponentType componentType, PwBy... accordionLocator);

   default boolean areEnabled(PwBy... accordionLocator) {
      return areEnabled(DEFAULT_TYPE, accordionLocator);
   }

   boolean areEnabled(AccordionComponentType componentType, PwBy... accordionLocator);

   default List<String> getExpanded(PwBy containerLocator) {
      return getExpanded(DEFAULT_TYPE, containerLocator);
   }

   List<String> getExpanded(AccordionComponentType componentType, PwBy containerLocator);

   default List<String> getCollapsed(PwBy containerLocator) {
      return getCollapsed(DEFAULT_TYPE, containerLocator);
   }

   List<String> getCollapsed(AccordionComponentType componentType, PwBy containerLocator);

   default List<String> getAll(PwBy containerLocator) {
      return getAll(DEFAULT_TYPE, containerLocator);
   }

   List<String> getAll(AccordionComponentType componentType, PwBy containerLocator);

   default String getTitle(PwBy accordionLocator) {
      return getTitle(DEFAULT_TYPE, accordionLocator);
   }

   String getText(AccordionComponentType componentType, PwBy accordionLocator);

   default String getText(PwBy accordionLocator) {
      return getText(DEFAULT_TYPE, accordionLocator);
   }

   String getTitle(AccordionComponentType componentType, PwBy accordionLocator);

}
