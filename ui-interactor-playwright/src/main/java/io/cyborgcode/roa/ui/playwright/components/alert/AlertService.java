package io.cyborgcode.roa.ui.playwright.components.alert;

import com.microsoft.playwright.Locator;
import io.cyborgcode.utilities.reflections.ReflectionUtil;

import static io.cyborgcode.roa.ui.playwright.config.UiConfigHolder.getPlaywrightConfig;

/**
 * Provides service-level methods for interacting with alert UI components.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface AlertService {

   AlertComponentType DEFAULT_TYPE = getDefaultType();

   private static AlertComponentType getDefaultType() {
      try {
         return ReflectionUtil.findEnumImplementationsOfInterface(AlertComponentType.class,
               getPlaywrightConfig().alertDefaultType(),
               getPlaywrightConfig().projectPackages());
      } catch (Exception ignored) {
         return null;
      }
   }

   default String getMessage(Locator container) {
      return getMessage(DEFAULT_TYPE, container);
   }

   String getMessage(AlertComponentType componentType, Locator container);

   default String getMessage(String alertLabel) {
      return getMessage(DEFAULT_TYPE, alertLabel);
   }

   String getMessage(AlertComponentType componentType, String alertLabel);

   default String getMessageBySelector(String alertSelector) {
      return getMessageBySelector(DEFAULT_TYPE, alertSelector);
   }

   String getMessageBySelector(AlertComponentType componentType, String alertSelector);

   default boolean isVisible(Locator container) {
      return isVisible(DEFAULT_TYPE, container);
   }

   boolean isVisible(AlertComponentType componentType, Locator container);

   default boolean isVisible(String alertLabel) {
      return isVisible(DEFAULT_TYPE, alertLabel);
   }

   boolean isVisible(AlertComponentType componentType, String alertLabel);

   default boolean isVisibleBySelector(String alertSelector) {
      return isVisibleBySelector(DEFAULT_TYPE, alertSelector);
   }

   boolean isVisibleBySelector(AlertComponentType componentType, String alertSelector);

   default void dismiss(Locator container) {
      dismiss(DEFAULT_TYPE, container);
   }

   void dismiss(AlertComponentType componentType, Locator container);

   default void dismiss(String alertLabel) {
      dismiss(DEFAULT_TYPE, alertLabel);
   }

   void dismiss(AlertComponentType componentType, String alertLabel);

   default void dismissBySelector(String alertSelector) {
      dismissBySelector(DEFAULT_TYPE, alertSelector);
   }

   void dismissBySelector(AlertComponentType componentType, String alertSelector);

   default String getType(Locator container) {
      return getType(DEFAULT_TYPE, container);
   }

   String getType(AlertComponentType componentType, Locator container);

   default String getType(String alertLabel) {
      return getType(DEFAULT_TYPE, alertLabel);
   }

   String getType(AlertComponentType componentType, String alertLabel);

   default String getTypeBySelector(String alertSelector) {
      return getTypeBySelector(DEFAULT_TYPE, alertSelector);
   }

   String getTypeBySelector(AlertComponentType componentType, String alertSelector);
}
