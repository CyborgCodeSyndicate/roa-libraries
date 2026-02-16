package io.cyborgcode.roa.ui.playwright.components.link;

import com.microsoft.playwright.Locator;
import io.cyborgcode.utilities.reflections.ReflectionUtil;

import static io.cyborgcode.roa.ui.playwright.config.UiConfigHolder.getPlaywrightConfig;

/**
 * Provides service-level methods for interacting with link UI components.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface LinkService extends io.cyborgcode.roa.ui.playwright.components.table.insertion.TableInsertion {

   LinkComponentType DEFAULT_TYPE = getDefaultType();

   private static LinkComponentType getDefaultType() {
      try {
         return ReflectionUtil.findEnumImplementationsOfInterface(LinkComponentType.class,
               getPlaywrightConfig().linkDefaultType(),
               getPlaywrightConfig().projectPackages());
      } catch (Exception ignored) {
         return null;
      }
   }

   default void click(Locator container, String linkText) {
      click(DEFAULT_TYPE, container, linkText);
   }

   void click(LinkComponentType componentType, Locator container, String linkText);

   default void click(Locator container) {
      click(DEFAULT_TYPE, container);
   }

   void click(LinkComponentType componentType, Locator container);

   default void click(String linkText) {
      click(DEFAULT_TYPE, linkText);
   }

   void click(LinkComponentType componentType, String linkText);

   default void clickBySelector(String linkSelector) {
      clickBySelector(DEFAULT_TYPE, linkSelector);
   }

   void clickBySelector(LinkComponentType componentType, String linkSelector);

   default String getHref(Locator container, String linkText) {
      return getHref(DEFAULT_TYPE, container, linkText);
   }

   String getHref(LinkComponentType componentType, Locator container, String linkText);

   default String getHref(Locator container) {
      return getHref(DEFAULT_TYPE, container);
   }

   String getHref(LinkComponentType componentType, Locator container);

   default String getHref(String linkText) {
      return getHref(DEFAULT_TYPE, linkText);
   }

   String getHref(LinkComponentType componentType, String linkText);

   default String getHrefBySelector(String linkSelector) {
      return getHrefBySelector(DEFAULT_TYPE, linkSelector);
   }

   String getHrefBySelector(LinkComponentType componentType, String linkSelector);

   default boolean isVisible(Locator container, String linkText) {
      return isVisible(DEFAULT_TYPE, container, linkText);
   }

   boolean isVisible(LinkComponentType componentType, Locator container, String linkText);

   default boolean isVisible(Locator container) {
      return isVisible(DEFAULT_TYPE, container);
   }

   boolean isVisible(LinkComponentType componentType, Locator container);

   default boolean isVisible(String linkText) {
      return isVisible(DEFAULT_TYPE, linkText);
   }

   boolean isVisible(LinkComponentType componentType, String linkText);

   default boolean isVisibleBySelector(String linkSelector) {
      return isVisibleBySelector(DEFAULT_TYPE, linkSelector);
   }

   boolean isVisibleBySelector(LinkComponentType componentType, String linkSelector);

   default boolean isEnabled(Locator container, String linkText) {
      return isEnabled(DEFAULT_TYPE, container, linkText);
   }

   boolean isEnabled(LinkComponentType componentType, Locator container, String linkText);

   default boolean isEnabled(Locator container) {
      return isEnabled(DEFAULT_TYPE, container);
   }

   boolean isEnabled(LinkComponentType componentType, Locator container);

   default boolean isEnabled(String linkText) {
      return isEnabled(DEFAULT_TYPE, linkText);
   }

   boolean isEnabled(LinkComponentType componentType, String linkText);

   default boolean isEnabledBySelector(String linkSelector) {
      return isEnabledBySelector(DEFAULT_TYPE, linkSelector);
   }

   boolean isEnabledBySelector(LinkComponentType componentType, String linkSelector);
}
