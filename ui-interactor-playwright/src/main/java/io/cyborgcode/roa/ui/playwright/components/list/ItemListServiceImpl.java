package io.cyborgcode.roa.ui.playwright.components.list;

import io.cyborgcode.roa.ui.playwright.components.base.AbstractComponentService;
import io.cyborgcode.roa.ui.playwright.components.base.ComponentType;
import io.cyborgcode.roa.ui.playwright.components.factory.ComponentFactory;
import io.cyborgcode.roa.ui.playwright.log.LogUi;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import java.util.List;

/**
 * Provides service-level operations for interacting with list components.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public class ItemListServiceImpl extends AbstractComponentService<ItemListComponentType, ItemList>
      implements ItemListService {

   public ItemListServiceImpl(Page page) {
      super(page);
   }

   @Override
   protected ItemList createComponent(final ItemListComponentType componentType) {
      return ComponentFactory.getListComponent(componentType, page);
   }

   @Override
   public List<String> getItems(final ItemListComponentType ct, final Locator c) {
      return comp(ct).getItems(c);
   }

   @Override
   public List<String> getItems(final ItemListComponentType ct, final String l) {
      return comp(ct).getItems(l);
   }

   @Override
   public List<String> getItemsBySelector(final ItemListComponentType ct, final String s) {
      return comp(ct).getItemsBySelector(s);
   }

   @Override
   public void selectItem(final ItemListComponentType ct, final Locator c, final String t) {
      LogUi.step("Selecting item: '{}'", t);
      comp(ct).selectItem(c, t);
   }

   @Override
   public void selectItem(final ItemListComponentType ct, final String l, final String t) {
      LogUi.step("Selecting item: '{}' in list: '{}'", t, l);
      comp(ct).selectItem(l, t);
   }

   @Override
   public void selectItemBySelector(final ItemListComponentType ct, final String s, final String t) {
      LogUi.step("Selecting item: '{}' by selector", t);
      comp(ct).selectItemBySelector(s, t);
   }

   @Override
   public boolean isItemPresent(final ItemListComponentType ct, final Locator c, final String t) {
      return comp(ct).isItemPresent(c, t);
   }

   @Override
   public boolean isItemPresent(final ItemListComponentType ct, final String l, final String t) {
      return comp(ct).isItemPresent(l, t);
   }

   @Override
   public boolean isItemPresentBySelector(final ItemListComponentType ct, final String s, final String t) {
      return comp(ct).isItemPresentBySelector(s, t);
   }

   @Override
   public void insertion(ComponentType componentType, String selector, Object... values) {
   }

   private ItemList comp(final ItemListComponentType componentType) {
      return getOrCreateComponent(componentType);
   }
}
