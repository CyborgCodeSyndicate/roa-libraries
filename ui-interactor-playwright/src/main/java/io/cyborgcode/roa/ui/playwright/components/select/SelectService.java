package io.cyborgcode.roa.ui.playwright.components.select;

import com.microsoft.playwright.Locator;
import io.cyborgcode.utilities.reflections.ReflectionUtil;

import static io.cyborgcode.roa.ui.playwright.config.UiConfigHolder.getPlaywrightConfig;

/**
 * Provides service-level methods for interacting with select (dropdown) UI components.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface SelectService extends io.cyborgcode.roa.ui.playwright.insertion.Insertion {

   SelectComponentType DEFAULT_TYPE = getDefaultType();

   private static SelectComponentType getDefaultType() {
      try {
         return ReflectionUtil.findEnumImplementationsOfInterface(SelectComponentType.class,
               getPlaywrightConfig().selectDefaultType(),
               getPlaywrightConfig().projectPackages());
      } catch (Exception ignored) {
         return null;
      }
   }

   default void select(Locator container, String optionText) {
      select(DEFAULT_TYPE, container, optionText);
   }

   void select(SelectComponentType componentType, Locator container, String optionText);

   default void select(Locator container, String selectLabel, String optionText) {
      select(DEFAULT_TYPE, container, selectLabel, optionText);
   }

   void select(SelectComponentType componentType, Locator container, String selectLabel, String optionText);

   default void select(String selectLabel, String optionText) {
      select(DEFAULT_TYPE, selectLabel, optionText);
   }

   void select(SelectComponentType componentType, String selectLabel, String optionText);

   default void selectBySelector(String selectSelector, String optionText) {
      selectBySelector(DEFAULT_TYPE, selectSelector, optionText);
   }

   void selectBySelector(SelectComponentType componentType, String selectSelector, String optionText);

   default String getSelectedValue(Locator container) {
      return getSelectedValue(DEFAULT_TYPE, container);
   }

   String getSelectedValue(SelectComponentType componentType, Locator container);

   default String getSelectedValue(Locator container, String selectLabel) {
      return getSelectedValue(DEFAULT_TYPE, container, selectLabel);
   }

   String getSelectedValue(SelectComponentType componentType, Locator container, String selectLabel);

   default String getSelectedValue(String selectLabel) {
      return getSelectedValue(DEFAULT_TYPE, selectLabel);
   }

   String getSelectedValue(SelectComponentType componentType, String selectLabel);

   default String getSelectedValueBySelector(String selectSelector) {
      return getSelectedValueBySelector(DEFAULT_TYPE, selectSelector);
   }

   String getSelectedValueBySelector(SelectComponentType componentType, String selectSelector);

   default boolean isEnabled(Locator container, String selectLabel) {
      return isEnabled(DEFAULT_TYPE, container, selectLabel);
   }

   boolean isEnabled(SelectComponentType componentType, Locator container, String selectLabel);

   default boolean isEnabled(Locator container) {
      return isEnabled(DEFAULT_TYPE, container);
   }

   boolean isEnabled(SelectComponentType componentType, Locator container);

   default boolean isEnabled(String selectLabel) {
      return isEnabled(DEFAULT_TYPE, selectLabel);
   }

   boolean isEnabled(SelectComponentType componentType, String selectLabel);

   default boolean isEnabledBySelector(String selectSelector) {
      return isEnabledBySelector(DEFAULT_TYPE, selectSelector);
   }

   boolean isEnabledBySelector(SelectComponentType componentType, String selectSelector);
}
