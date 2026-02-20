package io.cyborgcode.roa.ui.components.accordion;

import io.cyborgcode.roa.ui.components.accordion.AccordionComponentType;
import io.cyborgcode.roa.ui.components.accordion.AccordionCore;
import io.cyborgcode.roa.ui.components.base.AbstractComponentServiceCore;
import io.cyborgcode.roa.ui.log.LogUi;
import io.cyborgcode.roa.ui.util.strategy.Strategy;
import java.util.List;

/**
 * Abstract base implementation for accordion service operations.
 *
 * @param <E> The element/container type.
 * @param <C> The accordion component interface type.
 * @param <D> The driver or page type.
 * @author Cyborg Code Syndicate 💍👨💻
 */
public abstract class AccordionServiceImplCore<E, C extends AccordionCore<E>, D>
      extends AbstractComponentServiceCore<AccordionComponentType, C, D>
      implements AccordionServiceCore<E> {

   protected AccordionServiceImplCore(final D driver) {
      super(driver);
   }

   protected C accordionComponent(final AccordionComponentType componentType) {
      return getOrCreateComponent(componentType);
   }

   public void expand(final AccordionComponentType componentType, final E container, final String... accordionText) {
      LogUi.step("Expanding accordion");
      accordionComponent(componentType).expand(container, accordionText);
   }

   public void expand(final AccordionComponentType componentType, final E container, final Strategy strategy) {
      LogUi.step("Expanding accordion");
      accordionComponent(componentType).expand(container, strategy);
   }

   public void expand(final AccordionComponentType componentType, final String... accordionText) {
      LogUi.step("Expanding accordion");
      accordionComponent(componentType).expand(accordionText);
   }

   public void collapse(final AccordionComponentType componentType, final E container, final String... accordionText) {
      LogUi.step("Collapsing accordion");
      accordionComponent(componentType).collapse(container, accordionText);
   }

   public void collapse(final AccordionComponentType componentType, final E container, final Strategy strategy) {
      LogUi.step("Collapsing accordion");
      accordionComponent(componentType).collapse(container, strategy);
   }

   public void collapse(final AccordionComponentType componentType, final String... accordionText) {
      LogUi.step("Collapsing accordion");
      accordionComponent(componentType).collapse(accordionText);
   }

   public boolean areEnabled(final AccordionComponentType componentType, final E container, final String... accordionText) {
      return accordionComponent(componentType).areEnabled(container, accordionText);
   }

   public boolean areEnabled(final AccordionComponentType componentType, final String... accordionText) {
      return accordionComponent(componentType).areEnabled(accordionText);
   }

   public List<String> getExpanded(final AccordionComponentType componentType, final E container) {
      return accordionComponent(componentType).getExpanded(container);
   }

   public List<String> getCollapsed(final AccordionComponentType componentType, final E container) {
      return accordionComponent(componentType).getCollapsed(container);
   }

   public List<String> getAll(final AccordionComponentType componentType, final E container) {
      return accordionComponent(componentType).getAll(container);
   }

}
