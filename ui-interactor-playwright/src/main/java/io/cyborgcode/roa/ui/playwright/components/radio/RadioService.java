package io.cyborgcode.roa.ui.playwright.components.radio;

import com.microsoft.playwright.Locator;
import io.cyborgcode.utilities.reflections.ReflectionUtil;

import static io.cyborgcode.roa.ui.playwright.config.UiConfigHolder.getPlaywrightConfig;

/**
 * Provides service-level methods for interacting with radio button UI components.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface RadioService extends io.cyborgcode.roa.ui.playwright.insertion.Insertion {

   RadioComponentType DEFAULT_TYPE = getDefaultType();

   private static RadioComponentType getDefaultType() {
      try {
         return ReflectionUtil.findEnumImplementationsOfInterface(RadioComponentType.class,
               getPlaywrightConfig().radioDefaultType(),
               getPlaywrightConfig().projectPackages());
      } catch (Exception ignored) {
         return null;
      }
   }

   default void select(Locator container, String radioLabel) {
      select(DEFAULT_TYPE, container, radioLabel);
   }

   void select(RadioComponentType componentType, Locator container, String radioLabel);

   default void select(Locator container) {
      select(DEFAULT_TYPE, container);
   }

   void select(RadioComponentType componentType, Locator container);

   default void select(String radioLabel) {
      select(DEFAULT_TYPE, radioLabel);
   }

   void select(RadioComponentType componentType, String radioLabel);

   default void selectBySelector(String radioSelector) {
      selectBySelector(DEFAULT_TYPE, radioSelector);
   }

   void selectBySelector(RadioComponentType componentType, String radioSelector);

   default boolean isSelected(Locator container, String radioLabel) {
      return isSelected(DEFAULT_TYPE, container, radioLabel);
   }

   boolean isSelected(RadioComponentType componentType, Locator container, String radioLabel);

   default boolean isSelected(Locator container) {
      return isSelected(DEFAULT_TYPE, container);
   }

   boolean isSelected(RadioComponentType componentType, Locator container);

   default boolean isSelected(String radioLabel) {
      return isSelected(DEFAULT_TYPE, radioLabel);
   }

   boolean isSelected(RadioComponentType componentType, String radioLabel);

   default boolean isSelectedBySelector(String radioSelector) {
      return isSelectedBySelector(DEFAULT_TYPE, radioSelector);
   }

   boolean isSelectedBySelector(RadioComponentType componentType, String radioSelector);

   default boolean isEnabled(Locator container, String radioLabel) {
      return isEnabled(DEFAULT_TYPE, container, radioLabel);
   }

   boolean isEnabled(RadioComponentType componentType, Locator container, String radioLabel);

   default boolean isEnabled(Locator container) {
      return isEnabled(DEFAULT_TYPE, container);
   }

   boolean isEnabled(RadioComponentType componentType, Locator container);

   default boolean isEnabled(String radioLabel) {
      return isEnabled(DEFAULT_TYPE, radioLabel);
   }

   boolean isEnabled(RadioComponentType componentType, String radioLabel);

   default boolean isEnabledBySelector(String radioSelector) {
      return isEnabledBySelector(DEFAULT_TYPE, radioSelector);
   }

   boolean isEnabledBySelector(RadioComponentType componentType, String radioSelector);
}
