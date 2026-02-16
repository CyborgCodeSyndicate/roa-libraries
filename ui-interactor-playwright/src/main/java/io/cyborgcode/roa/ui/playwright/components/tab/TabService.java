package io.cyborgcode.roa.ui.playwright.components.tab;

import com.microsoft.playwright.Locator;
import io.cyborgcode.utilities.reflections.ReflectionUtil;

import static io.cyborgcode.roa.ui.playwright.config.UiConfigHolder.getPlaywrightConfig;

/**
 * Provides service-level methods for interacting with tab UI components.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface TabService {

   TabComponentType DEFAULT_TYPE = getDefaultType();

   private static TabComponentType getDefaultType() {
      try {
         return ReflectionUtil.findEnumImplementationsOfInterface(TabComponentType.class,
               getPlaywrightConfig().tabDefaultType(),
               getPlaywrightConfig().projectPackages());
      } catch (Exception ignored) {
         return null;
      }
   }

   default void select(Locator container, String tabLabel) {
      select(DEFAULT_TYPE, container, tabLabel);
   }

   void select(TabComponentType componentType, Locator container, String tabLabel);

   default void select(Locator container) {
      select(DEFAULT_TYPE, container);
   }

   void select(TabComponentType componentType, Locator container);

   default void select(String tabLabel) {
      select(DEFAULT_TYPE, tabLabel);
   }

   void select(TabComponentType componentType, String tabLabel);

   default void selectBySelector(String tabSelector) {
      selectBySelector(DEFAULT_TYPE, tabSelector);
   }

   void selectBySelector(TabComponentType componentType, String tabSelector);

   default boolean isSelected(Locator container, String tabLabel) {
      return isSelected(DEFAULT_TYPE, container, tabLabel);
   }

   boolean isSelected(TabComponentType componentType, Locator container, String tabLabel);

   default boolean isSelected(Locator container) {
      return isSelected(DEFAULT_TYPE, container);
   }

   boolean isSelected(TabComponentType componentType, Locator container);

   default boolean isSelected(String tabLabel) {
      return isSelected(DEFAULT_TYPE, tabLabel);
   }

   boolean isSelected(TabComponentType componentType, String tabLabel);

   default boolean isSelectedBySelector(String tabSelector) {
      return isSelectedBySelector(DEFAULT_TYPE, tabSelector);
   }

   boolean isSelectedBySelector(TabComponentType componentType, String tabSelector);

   default boolean isEnabled(Locator container, String tabLabel) {
      return isEnabled(DEFAULT_TYPE, container, tabLabel);
   }

   boolean isEnabled(TabComponentType componentType, Locator container, String tabLabel);

   default boolean isEnabled(Locator container) {
      return isEnabled(DEFAULT_TYPE, container);
   }

   boolean isEnabled(TabComponentType componentType, Locator container);

   default boolean isEnabled(String tabLabel) {
      return isEnabled(DEFAULT_TYPE, tabLabel);
   }

   boolean isEnabled(TabComponentType componentType, String tabLabel);

   default boolean isEnabledBySelector(String tabSelector) {
      return isEnabledBySelector(DEFAULT_TYPE, tabSelector);
   }

   boolean isEnabledBySelector(TabComponentType componentType, String tabSelector);
}
