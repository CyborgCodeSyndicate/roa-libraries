package io.cyborgcode.roa.ui.components.checkbox;

import io.cyborgcode.roa.ui.components.base.BaseUiElement;
import io.cyborgcode.roa.ui.insertion.Insertion;
import io.cyborgcode.roa.ui.util.strategy.Strategy;
import io.cyborgcode.utilities.reflections.ReflectionUtil;
import java.util.List;

import static io.cyborgcode.roa.ui.config.UiConfigHolderCore.getUiConfig;

/**
 * Provides service-level methods for interacting with checkbox UI components.
 *
 * <p>This interface defines operations for checking, unchecking, and verifying the state
 * of checkbox components, delegating the actual interactions
 * to specific implementations based on the configured {@link CheckboxComponentType}.
 *
 * @param <E> The container/element type (e.g., Playwright's {@code Locator} or Selenium's {@code WebElement}).
 * @param <E> The selector type (e.g., {@code String} for CSS selectors).
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface CheckboxServiceCore<E extends BaseUiElement, L> extends Insertion<L> {

   CheckboxComponentType DEFAULT_TYPE = getDefaultType();

   /**
    * Retrieves the default checkbox component type from the configuration.
    *
    * @return The default CheckboxComponentType.
    */
   static CheckboxComponentType getDefaultType() {
      try {
         return ReflectionUtil.findEnumImplementationsOfInterface(CheckboxComponentType.class,
               getUiConfig().checkboxDefaultType(),
               getUiConfig().projectPackages());
      } catch (Exception ignored) {
         return null;
      }
   }

   default void select(E container, String... checkBoxText) {
      select(DEFAULT_TYPE, container, checkBoxText);
   }

   void select(CheckboxComponentType componentType, E container, String... checkBoxText);

   default void select(E container, Strategy strategy) {
      select(DEFAULT_TYPE, container, strategy);
   }

   void select(CheckboxComponentType componentType, E container, Strategy strategy);

   default void select(String... checkBoxText) {
      select(DEFAULT_TYPE, checkBoxText);
   }

   void select(CheckboxComponentType componentType, String... checkBoxText);

   default void deSelect(E container, String... checkBoxText) {
      deSelect(DEFAULT_TYPE, container, checkBoxText);
   }

   void deSelect(CheckboxComponentType componentType, E container, String... checkBoxText);

   default void deSelect(E container, Strategy strategy) {
      deSelect(DEFAULT_TYPE, container, strategy);
   }

   void deSelect(CheckboxComponentType componentType, E container, Strategy strategy);

   default void deSelect(String... checkBoxText) {
      deSelect(DEFAULT_TYPE, checkBoxText);
   }

   void deSelect(CheckboxComponentType componentType, String... checkBoxText);

   default boolean areSelected(E container, String... checkBoxText) {
      return areSelected(DEFAULT_TYPE, container, checkBoxText);
   }

   boolean areSelected(CheckboxComponentType componentType, E container, String... checkBoxText);

   default boolean areSelected(String... checkBoxText) {
      return areSelected(DEFAULT_TYPE, checkBoxText);
   }

   boolean areSelected(CheckboxComponentType componentType, String... checkBoxText);

   default boolean areEnabled(E container, String... checkBoxText) {
      return areEnabled(DEFAULT_TYPE, container, checkBoxText);
   }

   boolean areEnabled(CheckboxComponentType componentType, E container, String... checkBoxText);

   default boolean areEnabled(String... checkBoxText) {
      return areEnabled(DEFAULT_TYPE, checkBoxText);
   }

   boolean areEnabled(CheckboxComponentType componentType, String... checkBoxText);

   default List<String> getSelected(E container) {
      return getSelected(DEFAULT_TYPE, container);
   }

   List<String> getSelected(CheckboxComponentType componentType, E container);

   default List<String> getAll(E container) {
      return getAll(DEFAULT_TYPE, container);
   }

   List<String> getAll(CheckboxComponentType componentType, E container);

   default void select(L... checkBoxLocator) {
      select(DEFAULT_TYPE, checkBoxLocator);
   }

   void select(CheckboxComponentType componentType, L... checkBoxLocator);

   default void deSelect(L... checkBoxLocator) {
      deSelect(DEFAULT_TYPE, checkBoxLocator);
   }

   void deSelect(CheckboxComponentType componentType, L... checkBoxLocator);

   default boolean areSelected(L... checkBoxLocator) {
      return areSelected(DEFAULT_TYPE, checkBoxLocator);
   }

   boolean areSelected(CheckboxComponentType componentType, L... checkBoxLocator);

   default boolean areEnabled(L... checkBoxLocator) {
      return areEnabled(DEFAULT_TYPE, checkBoxLocator);
   }

   boolean areEnabled(CheckboxComponentType componentType, L... checkBoxLocator);

   default List<String> getSelected(L containerLocator) {
      return getSelected(DEFAULT_TYPE, containerLocator);
   }

   List<String> getSelected(CheckboxComponentType componentType, L containerLocator);

   default List<String> getAll(L containerLocator) {
      return getAll(DEFAULT_TYPE, containerLocator);
   }

   List<String> getAll(CheckboxComponentType componentType, L containerLocator);

}
