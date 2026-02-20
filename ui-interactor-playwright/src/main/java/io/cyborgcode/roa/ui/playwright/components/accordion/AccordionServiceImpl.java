package io.cyborgcode.roa.ui.playwright.components.accordion;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.cyborgcode.roa.ui.components.accordion.AccordionComponentType;
import io.cyborgcode.roa.ui.components.accordion.AccordionServiceImplCore;
import io.cyborgcode.roa.ui.playwright.base.PwBy;
import io.cyborgcode.roa.ui.playwright.components.factory.ComponentFactory;
import java.util.List;

/**
 * Provides service-level operations for interacting with accordion components.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public class AccordionServiceImpl extends AccordionServiceImplCore<Locator, Accordion, Page>
      implements AccordionService {

   public AccordionServiceImpl(Page page) {
      super(page);
   }

   @Override
   protected Accordion createComponent(final AccordionComponentType accordionComponentType) {
      return ComponentFactory.getAccordionComponent(accordionComponentType, driver);
   }

   @Override
   public void expand(AccordionComponentType accordionComponentType, PwBy... accordionLocator) {
      accordionComponent(accordionComponentType).expand(accordionLocator);
   }

   @Override
   public void collapse(AccordionComponentType accordionComponentType, PwBy... accordionLocator) {
      accordionComponent(accordionComponentType).collapse(accordionLocator);
   }

   @Override
   public boolean areEnabled(AccordionComponentType accordionComponentType, PwBy... accordionLocator) {
      return accordionComponent(accordionComponentType).areEnabled(accordionLocator);
   }

   @Override
   public List<String> getExpanded(AccordionComponentType accordionComponentType, PwBy containerLocator) {
      return accordionComponent(accordionComponentType).getExpanded(containerLocator);
   }

   @Override
   public List<String> getCollapsed(AccordionComponentType accordionComponentType, PwBy containerLocator) {
      return accordionComponent(accordionComponentType).getCollapsed(containerLocator);
   }

   @Override
   public List<String> getAll(AccordionComponentType accordionComponentType, PwBy containerLocator) {
      return accordionComponent(accordionComponentType).getAll(containerLocator);
   }

   @Override
   public String getTitle(AccordionComponentType accordionComponentType, PwBy accordionLocator) {
      return accordionComponent(accordionComponentType).getTitle(accordionLocator);
   }

   @Override
   public String getText(AccordionComponentType accordionComponentType, PwBy accordionLocator) {
      return accordionComponent(accordionComponentType).getText(accordionLocator);
   }
}
