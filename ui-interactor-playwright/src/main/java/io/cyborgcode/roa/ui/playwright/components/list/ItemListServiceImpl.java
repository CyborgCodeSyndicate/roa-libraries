package io.cyborgcode.roa.ui.playwright.components.list;

import io.cyborgcode.roa.ui.components.base.ComponentType;
import io.cyborgcode.roa.ui.components.list.ItemListComponentType;
import io.cyborgcode.roa.ui.components.list.ItemListServiceImplCore;
import io.cyborgcode.roa.ui.log.LogUi;
import io.cyborgcode.roa.ui.playwright.base.PwBy;
import io.cyborgcode.roa.ui.playwright.base.PwElement;
import io.cyborgcode.roa.ui.playwright.components.factory.ComponentFactory;
import com.microsoft.playwright.Page;
import java.util.Arrays;

/**
 * Provides service-level operations for interacting with list components.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public class ItemListServiceImpl extends ItemListServiceImplCore<PwElement, ItemList, Page, PwBy>
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
