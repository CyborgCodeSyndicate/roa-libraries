package io.cyborgcode.roa.ui.playwright.components.list;

import com.microsoft.playwright.Locator;
import java.util.List;

/**
 * Defines operations for interacting with list UI elements.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface ItemList {

   List<String> getItems(Locator container);

   List<String> getItems(String listLabel);

   List<String> getItemsBySelector(String listSelector);

   void selectItem(Locator container, String itemText);

   void selectItem(String listLabel, String itemText);

   void selectItemBySelector(String listSelector, String itemText);

   boolean isItemPresent(Locator container, String itemText);

   boolean isItemPresent(String listLabel, String itemText);

   boolean isItemPresentBySelector(String listSelector, String itemText);

   default void tableInsertion(Locator cell, String... values) {
   }
}
