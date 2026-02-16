package io.cyborgcode.roa.ui.playwright.components.toggle;

import com.microsoft.playwright.Locator;
import io.cyborgcode.utilities.reflections.ReflectionUtil;

import static io.cyborgcode.roa.ui.playwright.config.UiConfigHolder.getPlaywrightConfig;

/**
 * Provides service-level methods for interacting with toggle UI components.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface ToggleService {

   ToggleComponentType DEFAULT_TYPE = getDefaultType();

   private static ToggleComponentType getDefaultType() {
      try {
         return ReflectionUtil.findEnumImplementationsOfInterface(ToggleComponentType.class,
               getPlaywrightConfig().toggleDefaultType(),
               getPlaywrightConfig().projectPackages());
      } catch (Exception ignored) {
         return null;
      }
   }

   default void toggle(Locator container, String toggleLabel) {
      toggle(DEFAULT_TYPE, container, toggleLabel);
   }

   void toggle(ToggleComponentType componentType, Locator container, String toggleLabel);

   default void toggle(Locator container) {
      toggle(DEFAULT_TYPE, container);
   }

   void toggle(ToggleComponentType componentType, Locator container);

   default void toggle(String toggleLabel) {
      toggle(DEFAULT_TYPE, toggleLabel);
   }

   void toggle(ToggleComponentType componentType, String toggleLabel);

   default void toggleBySelector(String toggleSelector) {
      toggleBySelector(DEFAULT_TYPE, toggleSelector);
   }

   void toggleBySelector(ToggleComponentType componentType, String toggleSelector);

   default boolean isOn(Locator container, String toggleLabel) {
      return isOn(DEFAULT_TYPE, container, toggleLabel);
   }

   boolean isOn(ToggleComponentType componentType, Locator container, String toggleLabel);

   default boolean isOn(Locator container) {
      return isOn(DEFAULT_TYPE, container);
   }

   boolean isOn(ToggleComponentType componentType, Locator container);

   default boolean isOn(String toggleLabel) {
      return isOn(DEFAULT_TYPE, toggleLabel);
   }

   boolean isOn(ToggleComponentType componentType, String toggleLabel);

   default boolean isOnBySelector(String toggleSelector) {
      return isOnBySelector(DEFAULT_TYPE, toggleSelector);
   }

   boolean isOnBySelector(ToggleComponentType componentType, String toggleSelector);

   default boolean isEnabled(Locator container, String toggleLabel) {
      return isEnabled(DEFAULT_TYPE, container, toggleLabel);
   }

   boolean isEnabled(ToggleComponentType componentType, Locator container, String toggleLabel);

   default boolean isEnabled(Locator container) {
      return isEnabled(DEFAULT_TYPE, container);
   }

   boolean isEnabled(ToggleComponentType componentType, Locator container);

   default boolean isEnabled(String toggleLabel) {
      return isEnabled(DEFAULT_TYPE, toggleLabel);
   }

   boolean isEnabled(ToggleComponentType componentType, String toggleLabel);

   default boolean isEnabledBySelector(String toggleSelector) {
      return isEnabledBySelector(DEFAULT_TYPE, toggleSelector);
   }

   boolean isEnabledBySelector(ToggleComponentType componentType, String toggleSelector);
}
