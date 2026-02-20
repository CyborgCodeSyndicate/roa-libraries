package io.cyborgcode.roa.ui.playwright.components.list;

import com.microsoft.playwright.Locator;
import io.cyborgcode.roa.ui.components.list.ItemListComponentType;
import io.cyborgcode.roa.ui.components.list.ItemListServiceCore;
import io.cyborgcode.roa.ui.util.strategy.Strategy;
import io.cyborgcode.roa.ui.playwright.base.PwBy;
import java.util.List;

/**
 * Playwright-specific list service interface.
 *
 * <p>Binds the core generic {@link ItemListServiceCore}
 * to Playwright's {@link Locator} type. All method contracts are inherited from core.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface ItemListService extends ItemListServiceCore<Locator, PwBy> {

   default void select(PwBy containerLocator, String... itemText) {
      select(DEFAULT_TYPE, containerLocator, itemText);
   }

   void select(ItemListComponentType componentType, PwBy containerLocator, String... itemText);

   default void select(PwBy containerLocator, Strategy strategy) {
      select(DEFAULT_TYPE, containerLocator, strategy);
   }

   void select(ItemListComponentType componentType, PwBy containerLocator, Strategy strategy);

   default void select(PwBy... itemListLocator) {
      select(DEFAULT_TYPE, itemListLocator);
   }

   void select(ItemListComponentType componentType, PwBy... itemListLocator);

   default void deSelect(PwBy containerLocator, String... itemText) {
      deSelect(DEFAULT_TYPE, containerLocator, itemText);
   }

   void deSelect(ItemListComponentType componentType, PwBy containerLocator, String... itemText);

   default void deSelect(PwBy containerLocator, Strategy strategy) {
      deSelect(DEFAULT_TYPE, containerLocator, strategy);
   }

   void deSelect(ItemListComponentType componentType, PwBy containerLocator, Strategy strategy);

   default void deSelect(PwBy... itemListLocator) {
      deSelect(DEFAULT_TYPE, itemListLocator);
   }

   void deSelect(ItemListComponentType componentType, PwBy... itemListLocator);

   default boolean areSelected(PwBy containerLocator, String... itemText) {
      return areSelected(DEFAULT_TYPE, containerLocator, itemText);
   }

   boolean areSelected(ItemListComponentType componentType, PwBy containerLocator, String... itemText);

   default boolean areSelected(PwBy... itemListLocator) {
      return areSelected(DEFAULT_TYPE, itemListLocator);
   }

   boolean areSelected(ItemListComponentType componentType, PwBy... itemListLocator);

   default boolean areEnabled(PwBy containerLocator, String... itemText) {
      return areEnabled(DEFAULT_TYPE, containerLocator, itemText);
   }

   boolean areEnabled(ItemListComponentType componentType, PwBy containerLocator, String... itemText);

   default boolean areEnabled(PwBy... itemLocator) {
      return areEnabled(DEFAULT_TYPE, itemLocator);
   }

   boolean areEnabled(ItemListComponentType componentType, PwBy... itemLocator);

   default boolean areVisible(PwBy containerLocator, String... itemText) {
      return areVisible(DEFAULT_TYPE, containerLocator, itemText);
   }

   boolean areVisible(ItemListComponentType componentType, PwBy containerLocator, String... itemText);

   default boolean areVisible(PwBy... itemLocator) {
      return areVisible(DEFAULT_TYPE, itemLocator);
   }

   boolean areVisible(ItemListComponentType componentType, PwBy... itemLocator);

   default List<String> getSelected(PwBy containerLocator) {
      return getSelected(DEFAULT_TYPE, containerLocator);
   }

   List<String> getSelected(ItemListComponentType componentType, PwBy containerLocator);

   default List<String> getAll(PwBy containerLocator) {
      return getAll(DEFAULT_TYPE, containerLocator);
   }

   List<String> getAll(ItemListComponentType componentType, PwBy containerLocator);
}
