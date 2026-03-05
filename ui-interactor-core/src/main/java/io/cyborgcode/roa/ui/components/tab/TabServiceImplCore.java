package io.cyborgcode.roa.ui.components.tab;

import io.cyborgcode.roa.ui.components.base.AbstractComponentServiceCore;
import io.cyborgcode.roa.ui.components.base.BaseUiElement;
import io.cyborgcode.roa.ui.components.base.ComponentType;
import io.cyborgcode.roa.ui.components.button.ButtonComponentType;
import io.cyborgcode.roa.ui.components.tab.TabComponentType;
import io.cyborgcode.roa.ui.components.tab.TabServiceCore;
import io.cyborgcode.roa.ui.log.LogUi;

/**
 * Abstract base implementation for tab service operations.
 *
 * @param <E> The element/container type.
 * @param <C> The tab component interface type.
 * @param <D> The driver or page type.
 * @author Cyborg Code Syndicate 💍👨💻
 */
public abstract class TabServiceImplCore<E extends BaseUiElement, C extends TabCore<E, L>, D, L>
      extends AbstractComponentServiceCore<TabComponentType, C, D>
      implements TabServiceCore<E, L> {

   protected TabServiceImplCore(final D driver) {
      super(driver);
   }

   protected C tabComponent(final ButtonComponentType componentType) {
      return getOrCreateComponent((TabComponentType) componentType);
   }

   public void click(final ButtonComponentType componentType, final E container, final String linkText) {
      LogUi.step("Clicking tab: '{}'", linkText);
      tabComponent(componentType).click(container, linkText);
   }

   public void click(final ButtonComponentType componentType, final E container) {
      LogUi.step("Clicking tab in container");
      tabComponent(componentType).click(container);
   }

   public void click(final ButtonComponentType componentType, final String linkText) {
      LogUi.step("Clicking tab: '{}'", linkText);
      tabComponent(componentType).click(linkText);
   }


   public boolean isEnabled(final ButtonComponentType componentType, final E container, final String linkText) {
      return tabComponent(componentType).isEnabled(container, linkText);
   }

   public boolean isEnabled(final ButtonComponentType componentType, final E container) {
      return tabComponent(componentType).isEnabled(container);
   }

   public boolean isEnabled(final ButtonComponentType componentType, final String linkText) {
      return tabComponent(componentType).isEnabled(linkText);
   }

   public boolean isVisible(final ButtonComponentType componentType, final E container, final String linkText) {
      return tabComponent(componentType).isVisible(container, linkText);
   }

   public boolean isVisible(final ButtonComponentType componentType, final E container) {
      return tabComponent(componentType).isVisible(container);
   }

   public boolean isVisible(final ButtonComponentType componentType, final String linkText) {
      return tabComponent(componentType).isVisible(linkText);
   }


   public boolean isSelected(final TabComponentType componentType, final E container, final String buttonText) {
      return tabComponent(componentType).isSelected(container, buttonText);
   }

   public boolean isSelected(final TabComponentType componentType, final E container) {
      return tabComponent(componentType).isSelected(container);
   }

   public boolean isSelected(final TabComponentType componentType, final String buttonText) {
      return tabComponent(componentType).isSelected(buttonText);
   }

   public void click(final ButtonComponentType componentType, final L tabSelector) {
      LogUi.step("Clicking tab by selector");
      tabComponent(componentType).click(tabSelector);
   }

   public boolean isEnabled(final ButtonComponentType componentType, final L tabSelector) {
      return tabComponent(componentType).isEnabled(tabSelector);
   }

   public boolean isVisible(final ButtonComponentType componentType, final L tabSelector) {
      return tabComponent(componentType).isVisible(tabSelector);
   }

   public boolean isSelected(final TabComponentType componentType, final L tabSelector) {
      return tabComponent(componentType).isSelected(tabSelector);
   }

   @Override
   public void tableInsertion(E cellElement, ComponentType componentType, String... values) {
      if (!(componentType instanceof TabComponentType tabType)) {
         throw new IllegalArgumentException("Component type needs to be from: TabComponentType.");
      }
      tabComponent(tabType).clickElementInCell(cellElement);
   }

}
