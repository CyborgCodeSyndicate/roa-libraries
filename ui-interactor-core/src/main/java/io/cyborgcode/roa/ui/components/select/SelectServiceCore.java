package io.cyborgcode.roa.ui.components.select;

import io.cyborgcode.roa.ui.components.base.BaseUiElement;
import io.cyborgcode.roa.ui.components.select.SelectComponentType;
import io.cyborgcode.roa.ui.insertion.Insertion;
import io.cyborgcode.roa.ui.util.strategy.Strategy;
import io.cyborgcode.utilities.reflections.ReflectionUtil;
import java.util.List;

import static io.cyborgcode.roa.ui.config.UiConfigHolderCore.getUiConfig;

/**
 * Provides service-level methods for interacting with select (dropdown) UI components.
 *
 * <p>This interface defines operations for selecting options, getting selected values,
 * and verifying the enabled state, delegating the actual interactions
 * to specific implementations based on the configured {@link SelectComponentType}.
 *
 * @param <E> The container/element type (e.g., Playwright's {@code Locator} or Selenium's {@code WebElement}).
 * @param <L> The selector type (e.g., {@code String} for CSS selectors).
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface SelectServiceCore<E extends BaseUiElement, L> extends Insertion<L> {

   SelectComponentType DEFAULT_TYPE = getDefaultType();

   /**
    * Retrieves the default select component type from the configuration.
    *
    * @return The default SelectComponentType.
    */
   static SelectComponentType getDefaultType() {
      try {
         return ReflectionUtil.findEnumImplementationsOfInterface(SelectComponentType.class,
               getUiConfig().selectDefaultType(),
               getUiConfig().projectPackages());
      } catch (Exception ignored) {
         return null;
      }
   }

   default void selectOptions(E container, String... values) {
      selectOptions(DEFAULT_TYPE, container, values);
   }

   void selectOptions(SelectComponentType componentType, E container, String... values);


   default List<String> selectOptions(E container, Strategy strategy) {
      return selectOptions(DEFAULT_TYPE, container, strategy);
   }

   List<String> selectOptions(SelectComponentType componentType, E container, Strategy strategy);

   default List<String> getAvailableOptions(E container) {
      return getAvailableOptions(DEFAULT_TYPE, container);
   }

   List<String> getAvailableOptions(SelectComponentType componentType, E container);

   default List<String> getSelectedOptions(E container) {
      return getSelectedOptions(DEFAULT_TYPE, container);
   }

   List<String> getSelectedOptions(SelectComponentType componentType, E container);

   default boolean isOptionVisible(E container, String value) {
      return isOptionVisible(DEFAULT_TYPE, container, value);
   }

   boolean isOptionVisible(SelectComponentType componentType, E container, String value);

   default boolean isOptionEnabled(E container, String value) {
      return isOptionEnabled(DEFAULT_TYPE, container, value);
   }

   boolean isOptionEnabled(SelectComponentType componentType, E container, String value);

   default void selectOptions(L containerLocator, String... values) {
      selectOptions(DEFAULT_TYPE, containerLocator, values);
   }

   void selectOptions(SelectComponentType componentType, L containerLocator, String... values);

   default void selectOptions(L containerLocator, Strategy strategy) {
      selectOptions(DEFAULT_TYPE, containerLocator, strategy);
   }

   void selectOptions(SelectComponentType componentType, L containerLocator, Strategy strategy);

   default List<String> getAvailableOptions(L containerLocator) {
      return getAvailableOptions(DEFAULT_TYPE, containerLocator);
   }

   List<String> getAvailableOptions(SelectComponentType componentType, L containerLocator);

   default List<String> getSelectedOptions(L containerLocator) {
      return getSelectedOptions(DEFAULT_TYPE, containerLocator);
   }

   List<String> getSelectedOptions(SelectComponentType componentType, L containerLocator);

   default boolean isOptionVisible(L containerLocator, String value) {
      return isOptionVisible(DEFAULT_TYPE, containerLocator, value);
   }

   boolean isOptionVisible(SelectComponentType componentType, L containerLocator, String value);

   default boolean isOptionEnabled(L containerLocator, String value) {
      return isOptionEnabled(DEFAULT_TYPE, containerLocator, value);
   }

   boolean isOptionEnabled(SelectComponentType componentType, L containerLocator, String value);
}
