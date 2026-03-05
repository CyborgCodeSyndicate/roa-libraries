package io.cyborgcode.roa.ui.components.select;

import io.cyborgcode.roa.ui.components.base.AbstractComponentServiceCore;
import io.cyborgcode.roa.ui.components.base.BaseUiElement;
import io.cyborgcode.roa.ui.components.select.SelectComponentType;
import io.cyborgcode.roa.ui.components.select.SelectServiceCore;
import io.cyborgcode.roa.ui.log.LogUi;
import io.cyborgcode.roa.ui.util.strategy.Strategy;
import java.util.List;

/**
 * Abstract base implementation for select (dropdown) service operations.
 *
 * @param <E> The element/container type.
 * @param <C> The select component interface type.
 * @param <D> The driver or page type.
 * @author Cyborg Code Syndicate 💍👨💻
 */
public abstract class SelectServiceImplCore<E extends BaseUiElement, C extends SelectCore<E, L>, D, L>
      extends AbstractComponentServiceCore<SelectComponentType, C, D>
      implements SelectServiceCore<E, L> {


   protected SelectServiceImplCore(final D driver) {
      super(driver);
   }

   protected C selectComponent(final SelectComponentType componentType) {
      return getOrCreateComponent(componentType);
   }

   public void selectOptions(final SelectComponentType componentType, final E container, final String... values) {
      LogUi.step("Selecting options");
      selectComponent(componentType).selectOptions(container, values);
   }

   public List<String> selectOptions(final SelectComponentType componentType, final E container, final Strategy strategy) {
      LogUi.step("Selecting options");
      return selectComponent(componentType).selectOptions(container, strategy);
   }

   public List<String> getAvailableOptions(final SelectComponentType componentType, final E container) {
      return selectComponent(componentType).getAvailableOptions(container);
   }

   public List<String> getSelectedOptions(final SelectComponentType componentType, final E container) {
      return selectComponent(componentType).getSelectedOptions(container);
   }

   public boolean isOptionVisible(final SelectComponentType componentType, final E container, final String value) {
      return selectComponent(componentType).isOptionVisible(container, value);
   }

   public boolean isOptionEnabled(final SelectComponentType componentType, final E container, final String value) {
      return selectComponent(componentType).isOptionEnabled(container, value);
   }

   public void selectOptions(final SelectComponentType componentType, final L containerLocator, final String... values) {
      LogUi.step("Selecting options");
      selectComponent(componentType).selectOptions(containerLocator, values);
   }

   public void selectOptions(final SelectComponentType componentType, final L containerLocator, final Strategy strategy) {
      LogUi.step("Selecting options");
      selectComponent(componentType).selectOptions(containerLocator, strategy);
   }

   public List<String> getAvailableOptions(final SelectComponentType componentType, final L containerLocator) {
      return selectComponent(componentType).getAvailableOptions(containerLocator);
   }

   public List<String> getSelectedOptions(final SelectComponentType componentType, final L containerLocator) {
      return selectComponent(componentType).getSelectedOptions(containerLocator);
   }

   public boolean isOptionVisible(final SelectComponentType componentType, final L containerLocator, final String value) {
      return selectComponent(componentType).isOptionVisible(containerLocator, value);
   }

   public boolean isOptionEnabled(final SelectComponentType componentType, final L containerLocator, final String value) {
      return selectComponent(componentType).isOptionEnabled(containerLocator, value);
   }
}
