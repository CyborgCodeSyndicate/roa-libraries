package io.cyborgcode.roa.ui.components.link;

import io.cyborgcode.roa.ui.components.base.AbstractComponentServiceCore;
import io.cyborgcode.roa.ui.components.base.BaseUiElement;
import io.cyborgcode.roa.ui.components.base.ComponentType;
import io.cyborgcode.roa.ui.components.button.ButtonComponentType;
import io.cyborgcode.roa.ui.log.LogUi;

/**
 * Abstract base implementation for link service operations.
 *
 * @param <E> The element/container type.
 * @param <C> The link component interface type.
 * @param <D> The driver or page type.
 * @author Cyborg Code Syndicate 💍👨💻
 */
public abstract class LinkServiceImplCore<E extends BaseUiElement, C extends LinkCore<E, L>, D, L>
      extends AbstractComponentServiceCore<LinkComponentType, C, D>
      implements LinkServiceCore<E, L> {

   protected LinkServiceImplCore(final D driver) {
      super(driver);
   }

   protected C linkComponent(final ButtonComponentType componentType) {
      return getOrCreateComponent((LinkComponentType) componentType);
   }

   public void click(final ButtonComponentType componentType, final E container, final String linkText) {
      LogUi.step("Clicking link: '{}'", linkText);
      linkComponent(componentType).click(container, linkText);
   }

   public void click(final ButtonComponentType componentType, final E container) {
      LogUi.step("Clicking link in container");
      linkComponent(componentType).click(container);
   }

   public void click(final ButtonComponentType componentType, final String linkText) {
      LogUi.step("Clicking link: '{}'", linkText);
      linkComponent(componentType).click(linkText);
   }


   public boolean isEnabled(final ButtonComponentType componentType, final E container, final String linkText) {
      return linkComponent(componentType).isEnabled(container, linkText);
   }

   public boolean isEnabled(final ButtonComponentType componentType, final E container) {
      return linkComponent(componentType).isEnabled(container);
   }

   public boolean isEnabled(final ButtonComponentType componentType, final String linkText) {
      return linkComponent(componentType).isEnabled(linkText);
   }

   public boolean isVisible(final ButtonComponentType componentType, final E container, final String linkText) {
      return linkComponent(componentType).isVisible(container, linkText);
   }

   public boolean isVisible(final ButtonComponentType componentType, final E container) {
      return linkComponent(componentType).isVisible(container);
   }

   public boolean isVisible(final ButtonComponentType componentType, final String linkText) {
      return linkComponent(componentType).isVisible(linkText);
   }

   public void doubleClick(final LinkComponentType componentType, final E container, final String linkText) {
      linkComponent(componentType).doubleClick(container, linkText);
   }

   public void doubleClick(final LinkComponentType componentType, final E container) {
      linkComponent(componentType).doubleClick(container);
   }

   public void doubleClick(final LinkComponentType componentType, final String linkText) {
      linkComponent(componentType).doubleClick(linkText);
   }

   public void click(final ButtonComponentType componentType, final L linkSelector) {
      LogUi.step("Clicking link by selector");
      linkComponent(componentType).click(linkSelector);
   }

   public boolean isEnabled(final ButtonComponentType componentType, final L linkSelector) {
      return linkComponent(componentType).isEnabled(linkSelector);
   }

   public boolean isVisible(final ButtonComponentType componentType, final L linkSelector) {
      return linkComponent(componentType).isVisible(linkSelector);
   }

   public void doubleClick(final LinkComponentType componentType, final L linkSelector) {
      LogUi.step("Double-clicking link by selector");
      linkComponent(componentType).doubleClick(linkSelector);
   }

   @Override
   public void tableInsertion(E cellElement, ComponentType componentType, String... values) {
      if (!(componentType instanceof LinkComponentType linkType)) {
         throw new IllegalArgumentException("Component type needs to be from: LinkComponentType.");
      }
      linkComponent(linkType).clickElementInCell(cellElement);
   }


}
