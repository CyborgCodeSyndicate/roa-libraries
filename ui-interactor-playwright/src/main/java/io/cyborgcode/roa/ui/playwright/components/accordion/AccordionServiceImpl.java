package io.cyborgcode.roa.ui.playwright.components.accordion;

import io.cyborgcode.roa.ui.playwright.components.base.AbstractComponentService;
import io.cyborgcode.roa.ui.playwright.components.factory.ComponentFactory;
import io.cyborgcode.roa.ui.playwright.log.LogUi;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

/**
 * Provides service-level operations for interacting with accordion components.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public class AccordionServiceImpl extends AbstractComponentService<AccordionComponentType, Accordion>
      implements AccordionService {

   public AccordionServiceImpl(Page page) {
      super(page);
   }

   @Override
   protected Accordion createComponent(final AccordionComponentType componentType) {
      return ComponentFactory.getAccordionComponent(componentType, page);
   }

   @Override
   public void expand(final AccordionComponentType ct, final Locator c, final String l) {
      LogUi.step("Expanding accordion: '{}'", l);
      comp(ct).expand(c, l);
   }

   @Override
   public void expand(final AccordionComponentType ct, final Locator c) {
      LogUi.step("Expanding accordion in container");
      comp(ct).expand(c);
   }

   @Override
   public void expand(final AccordionComponentType ct, final String l) {
      LogUi.step("Expanding accordion: '{}'", l);
      comp(ct).expand(l);
   }

   @Override
   public void expandBySelector(final AccordionComponentType ct, final String s) {
      LogUi.step("Expanding accordion by selector");
      comp(ct).expandBySelector(s);
   }

   @Override
   public void collapse(final AccordionComponentType ct, final Locator c, final String l) {
      LogUi.step("Collapsing accordion: '{}'", l);
      comp(ct).collapse(c, l);
   }

   @Override
   public void collapse(final AccordionComponentType ct, final Locator c) {
      LogUi.step("Collapsing accordion in container");
      comp(ct).collapse(c);
   }

   @Override
   public void collapse(final AccordionComponentType ct, final String l) {
      LogUi.step("Collapsing accordion: '{}'", l);
      comp(ct).collapse(l);
   }

   @Override
   public void collapseBySelector(final AccordionComponentType ct, final String s) {
      LogUi.step("Collapsing accordion by selector");
      comp(ct).collapseBySelector(s);
   }

   @Override
   public boolean isExpanded(final AccordionComponentType ct, final Locator c, final String l) {
      return comp(ct).isExpanded(c, l);
   }

   @Override
   public boolean isExpanded(final AccordionComponentType ct, final Locator c) {
      return comp(ct).isExpanded(c);
   }

   @Override
   public boolean isExpanded(final AccordionComponentType ct, final String l) {
      return comp(ct).isExpanded(l);
   }

   @Override
   public boolean isExpandedBySelector(final AccordionComponentType ct, final String s) {
      return comp(ct).isExpandedBySelector(s);
   }

   @Override
   public boolean isEnabled(final AccordionComponentType ct, final Locator c, final String l) {
      return comp(ct).isEnabled(c, l);
   }

   @Override
   public boolean isEnabled(final AccordionComponentType ct, final Locator c) {
      return comp(ct).isEnabled(c);
   }

   @Override
   public boolean isEnabled(final AccordionComponentType ct, final String l) {
      return comp(ct).isEnabled(l);
   }

   @Override
   public boolean isEnabledBySelector(final AccordionComponentType ct, final String s) {
      return comp(ct).isEnabledBySelector(s);
   }

   private Accordion comp(final AccordionComponentType componentType) {
      return getOrCreateComponent(componentType);
   }
}
