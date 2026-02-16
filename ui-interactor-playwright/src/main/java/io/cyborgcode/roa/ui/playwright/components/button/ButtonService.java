package io.cyborgcode.roa.ui.playwright.components.button;

import com.microsoft.playwright.Locator;
import io.cyborgcode.utilities.reflections.ReflectionUtil;

import static io.cyborgcode.roa.ui.playwright.config.UiConfigHolder.getPlaywrightConfig;

/**
 * Provides service-level methods for interacting with button UI components.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface ButtonService extends io.cyborgcode.roa.ui.playwright.components.table.insertion.TableInsertion {

   ButtonComponentType DEFAULT_TYPE = getDefaultType();

   private static ButtonComponentType getDefaultType() {
      try {
         return ReflectionUtil.findEnumImplementationsOfInterface(ButtonComponentType.class,
               getPlaywrightConfig().buttonDefaultType(),
               getPlaywrightConfig().projectPackages());
      } catch (Exception ignored) {
         return null;
      }
   }

   default void click(Locator container, String buttonText) {
      click(DEFAULT_TYPE, container, buttonText);
   }

   <T extends ButtonComponentType> void click(T componentType, Locator container, String buttonText);

   default void click(Locator container) {
      click(DEFAULT_TYPE, container);
   }

   <T extends ButtonComponentType> void click(T componentType, Locator container);

   default void click(String buttonText) {
      click(DEFAULT_TYPE, buttonText);
   }

   <T extends ButtonComponentType> void click(T componentType, String buttonText);

   default void clickBySelector(String buttonSelector) {
      clickBySelector(DEFAULT_TYPE, buttonSelector);
   }

   <T extends ButtonComponentType> void clickBySelector(T componentType, String buttonSelector);

   default boolean isEnabled(Locator container, String buttonText) {
      return isEnabled(DEFAULT_TYPE, container, buttonText);
   }

   <T extends ButtonComponentType> boolean isEnabled(T componentType, Locator container, String buttonText);

   default boolean isEnabled(Locator container) {
      return isEnabled(DEFAULT_TYPE, container);
   }

   <T extends ButtonComponentType> boolean isEnabled(T componentType, Locator container);

   default boolean isEnabled(String buttonText) {
      return isEnabled(DEFAULT_TYPE, buttonText);
   }

   <T extends ButtonComponentType> boolean isEnabled(T componentType, String buttonText);

   default boolean isEnabledBySelector(String buttonSelector) {
      return isEnabledBySelector(DEFAULT_TYPE, buttonSelector);
   }

   <T extends ButtonComponentType> boolean isEnabledBySelector(T componentType, String buttonSelector);

   default boolean isVisible(Locator container, String buttonText) {
      return isVisible(DEFAULT_TYPE, container, buttonText);
   }

   <T extends ButtonComponentType> boolean isVisible(T componentType, Locator container, String buttonText);

   default boolean isVisible(Locator container) {
      return isVisible(DEFAULT_TYPE, container);
   }

   <T extends ButtonComponentType> boolean isVisible(T componentType, Locator container);

   default boolean isVisible(String buttonText) {
      return isVisible(DEFAULT_TYPE, buttonText);
   }

   <T extends ButtonComponentType> boolean isVisible(T componentType, String buttonText);

   default boolean isVisibleBySelector(String buttonSelector) {
      return isVisibleBySelector(DEFAULT_TYPE, buttonSelector);
   }

   <T extends ButtonComponentType> boolean isVisibleBySelector(T componentType, String buttonSelector);
}
