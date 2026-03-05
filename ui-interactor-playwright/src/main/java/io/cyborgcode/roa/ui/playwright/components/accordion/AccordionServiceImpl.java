package io.cyborgcode.roa.ui.playwright.components.accordion;

import com.microsoft.playwright.Page;
import io.cyborgcode.roa.ui.components.accordion.AccordionComponentType;
import io.cyborgcode.roa.ui.components.accordion.AccordionServiceImplCore;
import io.cyborgcode.roa.ui.playwright.base.PwBy;
import io.cyborgcode.roa.ui.playwright.base.PwElement;
import io.cyborgcode.roa.ui.playwright.components.factory.ComponentFactory;

/**
 * Provides service-level operations for interacting with accordion components.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public class AccordionServiceImpl extends AccordionServiceImplCore<PwElement, Accordion, Page, PwBy>
      implements AccordionService {

   public AccordionServiceImpl(Page page) {
      super(page);
   }

   @Override
   protected Accordion createComponent(final AccordionComponentType accordionComponentType) {
      return ComponentFactory.getAccordionComponent(accordionComponentType, driver);
   }

}
