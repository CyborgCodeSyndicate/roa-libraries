package io.cyborgcode.roa.ui.playwright.components.list;

import io.cyborgcode.roa.ui.components.base.ComponentType;
import io.cyborgcode.roa.ui.components.list.ItemListComponentType;
import io.cyborgcode.roa.ui.components.list.ItemListServiceImplCore;
import io.cyborgcode.roa.ui.log.LogUi;
import io.cyborgcode.roa.ui.util.strategy.Strategy;
import io.cyborgcode.roa.ui.playwright.base.PwBy;
import io.cyborgcode.roa.ui.playwright.components.factory.ComponentFactory;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import java.util.Arrays;
import java.util.List;

/**
 * Provides service-level operations for interacting with list components.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public class ItemListServiceImpl extends ItemListServiceImplCore<Locator, ItemList, Page, PwBy>
      implements ItemListService {

   private static final String INSERT_VALUES_INTO_COMPONENT_LOCATOR =
         "Inserting values %s into component %s using locator %s";

   public ItemListServiceImpl(Page page) {
      super(page);
   }

   @Override
   protected ItemList createComponent(final ItemListComponentType componentType) {
      return ComponentFactory.getListComponent(componentType, driver);
   }

   @Override
   public void select(final ItemListComponentType componentType, final PwBy containerLocator, final String... itemText) {
      LogUi.step("Selecting item");
      itemListComponent(componentType).select(containerLocator, itemText);
   }

   @Override
   public void select(final ItemListComponentType componentType, final PwBy containerLocator, final Strategy strategy) {
      LogUi.step("Selecting item");
      itemListComponent(componentType).select(containerLocator, strategy);
   }

   @Override
   public void select(final ItemListComponentType componentType, final PwBy... itemListLocator) {
      LogUi.step("Selecting item");
      itemListComponent(componentType).select(itemListLocator);
   }

   @Override
   public void deSelect(final ItemListComponentType componentType, final PwBy containerLocator, final String... itemText) {
      LogUi.step("Deselecting item");
      itemListComponent(componentType).deSelect(containerLocator, itemText);
   }

   @Override
   public void deSelect(final ItemListComponentType componentType, final PwBy containerLocator, final Strategy strategy) {
      LogUi.step("Deselecting item");
      itemListComponent(componentType).deSelect(containerLocator, strategy);
   }

   @Override
   public void deSelect(final ItemListComponentType componentType, final PwBy... itemListLocator) {
      LogUi.step("Deselecting item");
      itemListComponent(componentType).deSelect(itemListLocator);
   }

   @Override
   public boolean areSelected(final ItemListComponentType componentType, final PwBy containerLocator, final String... itemText) {
      return itemListComponent(componentType).areSelected(containerLocator, itemText);
   }

   @Override
   public boolean areSelected(final ItemListComponentType componentType, final PwBy... itemListLocator) {
      return itemListComponent(componentType).areSelected(itemListLocator);
   }

   @Override
   public boolean areEnabled(final ItemListComponentType componentType, final PwBy containerLocator, final String... itemText) {
      return itemListComponent(componentType).areEnabled(containerLocator, itemText);
   }

   @Override
   public boolean areEnabled(final ItemListComponentType componentType, final PwBy... itemLocator) {
      return itemListComponent(componentType).areEnabled(itemLocator);
   }

   @Override
   public boolean areVisible(final ItemListComponentType componentType, final PwBy containerLocator, final String... itemText) {
      return itemListComponent(componentType).areVisible(containerLocator, itemText);
   }

   @Override
   public boolean areVisible(final ItemListComponentType componentType, final PwBy... itemLocator) {
      return itemListComponent(componentType).areVisible(itemLocator);
   }

   @Override
   public List<String> getSelected(final ItemListComponentType componentType, final PwBy containerLocator) {
      return itemListComponent(componentType).getSelected(containerLocator);
   }

   @Override
   public List<String> getAll(final ItemListComponentType componentType, final PwBy containerLocator) {
      return itemListComponent(componentType).getAll(containerLocator);
   }

   @Override
   public void insertion(ComponentType componentType, PwBy selector, Object... values) {
      if (!(componentType instanceof ItemListComponentType listType)) {
         throw new IllegalArgumentException("Component type needs to be from: ItemListComponentType.");
      }
      String[] stringValues = Arrays.stream(values)
            .map(String::valueOf)
            .toArray(String[]::new);
      LogUi.step(String.format(INSERT_VALUES_INTO_COMPONENT_LOCATOR, Arrays.toString(stringValues), componentType,
            selector));
      select(listType, selector, stringValues);
   }
}
