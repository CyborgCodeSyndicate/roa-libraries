package io.cyborgcode.roa.ui.components.list;

import io.cyborgcode.roa.ui.components.list.ItemListComponentType;
import io.cyborgcode.roa.ui.insertion.Insertion;
import io.cyborgcode.roa.ui.util.strategy.Strategy;
import io.cyborgcode.utilities.reflections.ReflectionUtil;
import java.util.List;

import static io.cyborgcode.roa.ui.config.UiConfigHolderCore.getUiConfig;

/**
 * Provides service-level methods for interacting with list UI components.
 *
 * <p>This interface defines operations for retrieving items, selecting items,
 * and checking item presence, delegating the actual interactions
 * to specific implementations based on the configured {@link ItemListComponentType}.
 *
 * @param <E> The container/element type (e.g., Playwright's {@code Locator} or Selenium's {@code WebElement}).
 * @param <L> The selector type (e.g., {@code String} for CSS selectors).
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface ItemListServiceCore<E, L> extends Insertion<L> {

   ItemListComponentType DEFAULT_TYPE = getDefaultType();

   /**
    * Retrieves the default list component type from the configuration.
    *
    * @return The default ItemListComponentType.
    */
   static ItemListComponentType getDefaultType() {
      try {
         return ReflectionUtil.findEnumImplementationsOfInterface(ItemListComponentType.class,
               getUiConfig().listDefaultType(),
               getUiConfig().projectPackages());
      } catch (Exception ignored) {
         return null;
      }
   }

   default void select(E container, String... itemText) {
      select(DEFAULT_TYPE, container, itemText);
   }

   void select(ItemListComponentType componentType, E container, String... itemText);

   default void select(E container, Strategy strategy) {
      select(DEFAULT_TYPE, container, strategy);
   }

   void select(ItemListComponentType componentType, E container, Strategy strategy);

   default void select(String... itemText) {
      select(DEFAULT_TYPE, itemText);
   }

   void select(ItemListComponentType componentType, String... itemText);

   default void deSelect(E container, String... itemText) {
      deSelect(DEFAULT_TYPE, container, itemText);
   }

   void deSelect(ItemListComponentType componentType, E container, String... itemText);

   default void deSelect(E container, Strategy strategy) {
      deSelect(DEFAULT_TYPE, container, strategy);
   }

   void deSelect(ItemListComponentType componentType, E container, Strategy strategy);

   default void deSelect(String... itemText) {
      deSelect(DEFAULT_TYPE, itemText);
   }

   void deSelect(ItemListComponentType componentType, String... itemText);

   default boolean areSelected(E container, String... itemText) {
      return areSelected(DEFAULT_TYPE, container, itemText);
   }

   boolean areSelected(ItemListComponentType componentType, E container, String... itemText);

   default boolean areSelected(String... itemText) {
      return areSelected(DEFAULT_TYPE, itemText);
   }

   boolean areSelected(ItemListComponentType componentType, String... itemText);

   default boolean areEnabled(E container, String... itemText) {
      return areEnabled(DEFAULT_TYPE, container, itemText);
   }

   boolean areEnabled(ItemListComponentType componentType, E container, String... itemText);

   default boolean areEnabled(String... itemText) {
      return areEnabled(DEFAULT_TYPE, itemText);
   }

   boolean areEnabled(ItemListComponentType componentType, String... itemText);

   default boolean areVisible(E container, String... itemText) {
      return areVisible(DEFAULT_TYPE, container, itemText);
   }

   boolean areVisible(ItemListComponentType componentType, E container, String... itemText);

   default boolean areVisible(String... itemText) {
      return areVisible(DEFAULT_TYPE, itemText);
   }

   boolean areVisible(ItemListComponentType componentType, String... itemText);

   default List<String> getSelected(E container) {
      return getSelected(DEFAULT_TYPE, container);
   }

   List<String> getSelected(ItemListComponentType componentType, E container);

   default List<String> getAll(E container) {
      return getAll(DEFAULT_TYPE, container);
   }

   List<String> getAll(ItemListComponentType componentType, E container);
}
