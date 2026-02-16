package io.cyborgcode.roa.ui.playwright.components.list;

import com.microsoft.playwright.Locator;
import io.cyborgcode.utilities.reflections.ReflectionUtil;
import java.util.List;

import static io.cyborgcode.roa.ui.playwright.config.UiConfigHolder.getPlaywrightConfig;

/**
 * Provides service-level methods for interacting with list UI components.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface ItemListService extends io.cyborgcode.roa.ui.playwright.insertion.Insertion {

   ItemListComponentType DEFAULT_TYPE = getDefaultType();

   private static ItemListComponentType getDefaultType() {
      try {
         return ReflectionUtil.findEnumImplementationsOfInterface(ItemListComponentType.class,
               getPlaywrightConfig().listDefaultType(),
               getPlaywrightConfig().projectPackages());
      } catch (Exception ignored) {
         return null;
      }
   }

   default List<String> getItems(Locator container) {
      return getItems(DEFAULT_TYPE, container);
   }

   List<String> getItems(ItemListComponentType componentType, Locator container);

   default List<String> getItems(String listLabel) {
      return getItems(DEFAULT_TYPE, listLabel);
   }

   List<String> getItems(ItemListComponentType componentType, String listLabel);

   default List<String> getItemsBySelector(String listSelector) {
      return getItemsBySelector(DEFAULT_TYPE, listSelector);
   }

   List<String> getItemsBySelector(ItemListComponentType componentType, String listSelector);

   default void selectItem(Locator container, String itemText) {
      selectItem(DEFAULT_TYPE, container, itemText);
   }

   void selectItem(ItemListComponentType componentType, Locator container, String itemText);

   default void selectItem(String listLabel, String itemText) {
      selectItem(DEFAULT_TYPE, listLabel, itemText);
   }

   void selectItem(ItemListComponentType componentType, String listLabel, String itemText);

   default void selectItemBySelector(String listSelector, String itemText) {
      selectItemBySelector(DEFAULT_TYPE, listSelector, itemText);
   }

   void selectItemBySelector(ItemListComponentType componentType, String listSelector, String itemText);

   default boolean isItemPresent(Locator container, String itemText) {
      return isItemPresent(DEFAULT_TYPE, container, itemText);
   }

   boolean isItemPresent(ItemListComponentType componentType, Locator container, String itemText);

   default boolean isItemPresent(String listLabel, String itemText) {
      return isItemPresent(DEFAULT_TYPE, listLabel, itemText);
   }

   boolean isItemPresent(ItemListComponentType componentType, String listLabel, String itemText);

   default boolean isItemPresentBySelector(String listSelector, String itemText) {
      return isItemPresentBySelector(DEFAULT_TYPE, listSelector, itemText);
   }

   boolean isItemPresentBySelector(ItemListComponentType componentType, String listSelector, String itemText);
}
