package io.cyborgcode.roa.ui.playwright.components.accordion;

import com.microsoft.playwright.Locator;
import io.cyborgcode.roa.ui.components.accordion.AccordionCore;
import io.cyborgcode.roa.ui.playwright.base.PwBy;
import io.cyborgcode.roa.ui.util.strategy.Strategy;
import java.util.List;

/**
 * Playwright-specific accordion component interface.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface Accordion extends AccordionCore<Locator> {

   void expand(PwBy... accordionLocator);

   void collapse(PwBy... accordionLocator);

   boolean areEnabled(PwBy... accordionLocator);

   List<String> getExpanded(PwBy containerLocator);

   List<String> getCollapsed(PwBy containerLocator);

   List<String> getAll(PwBy containerLocator);

   String getTitle(PwBy accordionLocator);

   String getText(PwBy accordionLocator);

}
