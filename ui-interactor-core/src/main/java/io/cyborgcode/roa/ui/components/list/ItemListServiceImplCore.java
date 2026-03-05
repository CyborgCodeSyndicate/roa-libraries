package io.cyborgcode.roa.ui.components.list;

import io.cyborgcode.roa.ui.components.base.AbstractComponentServiceCore;
import io.cyborgcode.roa.ui.components.base.BaseUiElement;
import io.cyborgcode.roa.ui.log.LogUi;
import io.cyborgcode.roa.ui.util.strategy.Strategy;
import java.util.List;

/**
 * Abstract base implementation for list service operations.
 *
 * @param <E> The element/container type.
 * @param <C> The list component interface type.
 * @param <D> The driver or page type.
 * @author Cyborg Code Syndicate 💍👨💻
 */
public abstract class ItemListServiceImplCore<E extends BaseUiElement, C extends ItemListCore<E, L>, D, L>
      extends AbstractComponentServiceCore<ItemListComponentType, C, D>
      implements ItemListServiceCore<E, L> {

   protected ItemListServiceImplCore(final D driver) {
      super(driver);
   }

   protected C itemListComponent(final ItemListComponentType componentType) {
      return getOrCreateComponent(componentType);
   }

   public void select(final ItemListComponentType componentType, final E container, final String... itemText) {
      LogUi.step("Selecting item");
      itemListComponent(componentType).select(container, itemText);
   }

   public void select(final ItemListComponentType componentType, final E container, final Strategy strategy) {
      LogUi.step("Selecting item");
      itemListComponent(componentType).select(container, strategy);
   }

   public void select(final ItemListComponentType componentType, final String... itemText) {
      LogUi.step("Selecting item");
      itemListComponent(componentType).select(itemText);
   }

   public void deSelect(final ItemListComponentType componentType, final E container, 
                        final String... itemText) {
      LogUi.step("Deselecting item");
      itemListComponent(componentType).deSelect(container, itemText);
   }

   public void deSelect(final ItemListComponentType componentType, final E container, final Strategy strategy) {
      LogUi.step("Deselecting item");
      itemListComponent(componentType).deSelect(container, strategy);
   }

   public void deSelect(final ItemListComponentType componentType, final String... itemText) {
      LogUi.step("Deselecting item");
      itemListComponent(componentType).deSelect(itemText);
   }

   public boolean areSelected(final ItemListComponentType componentType, final E container, final String... itemText) {
      return itemListComponent(componentType).areSelected(container, itemText);
   }

   public boolean areSelected(final ItemListComponentType componentType, final String... itemText) {
      return itemListComponent(componentType).areSelected(itemText);
   }

   public boolean areEnabled(final ItemListComponentType componentType, final E container, final String... itemText) {
      return itemListComponent(componentType).areEnabled(container, itemText);
   }

   public boolean areEnabled(final ItemListComponentType componentType, final String... itemText) {
      return itemListComponent(componentType).areEnabled(itemText);
   }

   public boolean areVisible(final ItemListComponentType componentType, final E container, final String... itemText) {
      return itemListComponent(componentType).areVisible(container, itemText);
   }

   public boolean areVisible(final ItemListComponentType componentType, final String... itemText) {
      return itemListComponent(componentType).areVisible(itemText);
   }

   public List<String> getSelected(final ItemListComponentType componentType, final E container) {
      return itemListComponent(componentType).getSelected(container);
   }

   public List<String> getAll(final ItemListComponentType componentType, final E container) {
      return itemListComponent(componentType).getAll(container);
   }

   public void select(final ItemListComponentType componentType, final L containerLocator, final String... itemText) {
      LogUi.step("Selecting item");
      itemListComponent(componentType).select(containerLocator, itemText);
   }

   public void select(final ItemListComponentType componentType, final L containerLocator, final Strategy strategy) {
      LogUi.step("Selecting item");
      itemListComponent(componentType).select(containerLocator, strategy);
   }

   public void select(final ItemListComponentType componentType, final L... itemListLocator) {
      LogUi.step("Selecting item");
      itemListComponent(componentType).select(itemListLocator);
   }

   public void deSelect(final ItemListComponentType componentType, final L containerLocator, final String... itemText) {
      LogUi.step("Deselecting item");
      itemListComponent(componentType).deSelect(containerLocator, itemText);
   }

   public void deSelect(final ItemListComponentType componentType, final L containerLocator, final Strategy strategy) {
      LogUi.step("Deselecting item");
      itemListComponent(componentType).deSelect(containerLocator, strategy);
   }

   public void deSelect(final ItemListComponentType componentType, final L... itemListLocator) {
      LogUi.step("Deselecting item");
      itemListComponent(componentType).deSelect(itemListLocator);
   }

   public boolean areSelected(final ItemListComponentType componentType, final L containerLocator, final String... itemText) {
      return itemListComponent(componentType).areSelected(containerLocator, itemText);
   }

   public boolean areSelected(final ItemListComponentType componentType, final L... itemListLocator) {
      return itemListComponent(componentType).areSelected(itemListLocator);
   }

   public boolean areEnabled(final ItemListComponentType componentType, final L containerLocator, final String... itemText) {
      return itemListComponent(componentType).areEnabled(containerLocator, itemText);
   }

   public boolean areEnabled(final ItemListComponentType componentType, final L... itemLocator) {
      return itemListComponent(componentType).areEnabled(itemLocator);
   }

   public boolean areVisible(final ItemListComponentType componentType, final L containerLocator, final String... itemText) {
      return itemListComponent(componentType).areVisible(containerLocator, itemText);
   }

   public boolean areVisible(final ItemListComponentType componentType, final L... itemLocator) {
      return itemListComponent(componentType).areVisible(itemLocator);
   }

   public List<String> getSelected(final ItemListComponentType componentType, final L containerLocator) {
      return itemListComponent(componentType).getSelected(containerLocator);
   }

   public List<String> getAll(final ItemListComponentType componentType, final L containerLocator) {
      return itemListComponent(componentType).getAll(containerLocator);
   }
}
