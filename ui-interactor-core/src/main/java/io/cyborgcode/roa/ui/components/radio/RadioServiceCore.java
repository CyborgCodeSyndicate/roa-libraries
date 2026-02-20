package io.cyborgcode.roa.ui.components.radio;

import io.cyborgcode.roa.ui.components.radio.RadioComponentType;
import io.cyborgcode.roa.ui.insertion.Insertion;
import io.cyborgcode.roa.ui.util.strategy.Strategy;
import io.cyborgcode.utilities.reflections.ReflectionUtil;
import java.util.List;

import static io.cyborgcode.roa.ui.config.UiConfigHolderCore.getUiConfig;

/**
 * Provides service-level methods for interacting with radio button UI components.
 *
 * <p>This interface defines operations for selecting radio buttons, checking selected state,
 * and verifying the enabled state, delegating the actual interactions
 * to specific implementations based on the configured {@link RadioComponentType}.
 *
 * @param <E> The container/element type (e.g., Playwright's {@code Locator} or Selenium's {@code WebElement}).
 * @param <L> The selector type (e.g., {@code String} for CSS selectors).
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface RadioServiceCore<E, L> extends Insertion<L> {

   RadioComponentType DEFAULT_TYPE = getDefaultType();

   /**
    * Retrieves the default radio component type from the configuration.
    *
    * @return The default RadioComponentType.
    */
   static RadioComponentType getDefaultType() {
      try {
         return ReflectionUtil.findEnumImplementationsOfInterface(RadioComponentType.class,
               getUiConfig().radioDefaultType(),
               getUiConfig().projectPackages());
      } catch (Exception ignored) {
         return null;
      }
   }

   default void select(E container, String radioButtonText) {
      select(DEFAULT_TYPE, container, radioButtonText);
   }

   void select(RadioComponentType componentType, E container, String radioButtonText);

   default void select(E container, Strategy strategy) {
      select(DEFAULT_TYPE, container, strategy);
   }

   void select(RadioComponentType componentType, E container, Strategy strategy);

   default void select(String radioButtonText) {
      select(DEFAULT_TYPE, radioButtonText);
   }

   void select(RadioComponentType componentType, String radioButtonText);

   default boolean isEnabled(E container, String radioButtonText) {
      return isEnabled(DEFAULT_TYPE, container, radioButtonText);
   }

   boolean isEnabled(RadioComponentType componentType, E container, String radioButtonText);

   default boolean isEnabled(String radioButtonText) {
      return isEnabled(DEFAULT_TYPE, radioButtonText);
   }

   boolean isEnabled(RadioComponentType componentType, String radioButtonText);

   default boolean isSelected(E container, String radioButtonText) {
      return isSelected(DEFAULT_TYPE, container, radioButtonText);
   }

   boolean isSelected(RadioComponentType componentType, E container, String radioButtonText);

   default boolean isSelected(String radioButtonText) {
      return isSelected(DEFAULT_TYPE, radioButtonText);
   }

   boolean isSelected(RadioComponentType componentType, String radioButtonText);

   default boolean isVisible(E container, String radioButtonText) {
      return isVisible(DEFAULT_TYPE, container, radioButtonText);
   }

   boolean isVisible(RadioComponentType componentType, E container, String radioButtonText);

   default boolean isVisible(String radioButtonText) {
      return isVisible(DEFAULT_TYPE, radioButtonText);
   }

   boolean isVisible(RadioComponentType componentType, String radioButtonText);

   default String getSelected(E container) {
      return getSelected(DEFAULT_TYPE, container);
   }

   String getSelected(RadioComponentType componentType, E container);

   default List<String> getAll(E container) {
      return getAll(DEFAULT_TYPE, container);
   }

   List<String> getAll(RadioComponentType componentType, E container);
}
