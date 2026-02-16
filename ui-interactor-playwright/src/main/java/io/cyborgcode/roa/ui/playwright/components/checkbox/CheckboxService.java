package io.cyborgcode.roa.ui.playwright.components.checkbox;

import com.microsoft.playwright.Locator;
import io.cyborgcode.utilities.reflections.ReflectionUtil;

import static io.cyborgcode.roa.ui.playwright.config.UiConfigHolder.getPlaywrightConfig;

/**
 * Provides service-level methods for interacting with checkbox UI components.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface CheckboxService extends io.cyborgcode.roa.ui.playwright.insertion.Insertion {

   CheckboxComponentType DEFAULT_TYPE = getDefaultType();

   private static CheckboxComponentType getDefaultType() {
      try {
         return ReflectionUtil.findEnumImplementationsOfInterface(CheckboxComponentType.class,
               getPlaywrightConfig().checkboxDefaultType(),
               getPlaywrightConfig().projectPackages());
      } catch (Exception ignored) {
         return null;
      }
   }

   default void check(Locator container, String checkboxLabel) {
      check(DEFAULT_TYPE, container, checkboxLabel);
   }

   void check(CheckboxComponentType componentType, Locator container, String checkboxLabel);

   default void check(Locator container) {
      check(DEFAULT_TYPE, container);
   }

   void check(CheckboxComponentType componentType, Locator container);

   default void check(String checkboxLabel) {
      check(DEFAULT_TYPE, checkboxLabel);
   }

   void check(CheckboxComponentType componentType, String checkboxLabel);

   default void checkBySelector(String checkboxSelector) {
      checkBySelector(DEFAULT_TYPE, checkboxSelector);
   }

   void checkBySelector(CheckboxComponentType componentType, String checkboxSelector);

   default void uncheck(Locator container, String checkboxLabel) {
      uncheck(DEFAULT_TYPE, container, checkboxLabel);
   }

   void uncheck(CheckboxComponentType componentType, Locator container, String checkboxLabel);

   default void uncheck(Locator container) {
      uncheck(DEFAULT_TYPE, container);
   }

   void uncheck(CheckboxComponentType componentType, Locator container);

   default void uncheck(String checkboxLabel) {
      uncheck(DEFAULT_TYPE, checkboxLabel);
   }

   void uncheck(CheckboxComponentType componentType, String checkboxLabel);

   default void uncheckBySelector(String checkboxSelector) {
      uncheckBySelector(DEFAULT_TYPE, checkboxSelector);
   }

   void uncheckBySelector(CheckboxComponentType componentType, String checkboxSelector);

   default boolean isChecked(Locator container, String checkboxLabel) {
      return isChecked(DEFAULT_TYPE, container, checkboxLabel);
   }

   boolean isChecked(CheckboxComponentType componentType, Locator container, String checkboxLabel);

   default boolean isChecked(Locator container) {
      return isChecked(DEFAULT_TYPE, container);
   }

   boolean isChecked(CheckboxComponentType componentType, Locator container);

   default boolean isChecked(String checkboxLabel) {
      return isChecked(DEFAULT_TYPE, checkboxLabel);
   }

   boolean isChecked(CheckboxComponentType componentType, String checkboxLabel);

   default boolean isCheckedBySelector(String checkboxSelector) {
      return isCheckedBySelector(DEFAULT_TYPE, checkboxSelector);
   }

   boolean isCheckedBySelector(CheckboxComponentType componentType, String checkboxSelector);

   default boolean isEnabled(Locator container, String checkboxLabel) {
      return isEnabled(DEFAULT_TYPE, container, checkboxLabel);
   }

   boolean isEnabled(CheckboxComponentType componentType, Locator container, String checkboxLabel);

   default boolean isEnabled(Locator container) {
      return isEnabled(DEFAULT_TYPE, container);
   }

   boolean isEnabled(CheckboxComponentType componentType, Locator container);

   default boolean isEnabled(String checkboxLabel) {
      return isEnabled(DEFAULT_TYPE, checkboxLabel);
   }

   boolean isEnabled(CheckboxComponentType componentType, String checkboxLabel);

   default boolean isEnabledBySelector(String checkboxSelector) {
      return isEnabledBySelector(DEFAULT_TYPE, checkboxSelector);
   }

   boolean isEnabledBySelector(CheckboxComponentType componentType, String checkboxSelector);
}
