package io.cyborgcode.roa.ui.playwright.components.loader;

import com.microsoft.playwright.Locator;
import io.cyborgcode.utilities.reflections.ReflectionUtil;

import static io.cyborgcode.roa.ui.playwright.config.UiConfigHolder.getPlaywrightConfig;

/**
 * Provides service-level methods for interacting with loader/spinner UI components.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface LoaderService {

   LoaderComponentType DEFAULT_TYPE = getDefaultType();

   private static LoaderComponentType getDefaultType() {
      try {
         return ReflectionUtil.findEnumImplementationsOfInterface(LoaderComponentType.class,
               getPlaywrightConfig().loaderDefaultType(),
               getPlaywrightConfig().projectPackages());
      } catch (Exception ignored) {
         return null;
      }
   }

   default void waitUntilLoaded(Locator container) {
      waitUntilLoaded(DEFAULT_TYPE, container);
   }

   void waitUntilLoaded(LoaderComponentType componentType, Locator container);

   default void waitUntilLoaded(Locator container, int seconds) {
      waitUntilLoaded(DEFAULT_TYPE, container, seconds);
   }

   void waitUntilLoaded(LoaderComponentType componentType, Locator container, int seconds);

   default void waitUntilLoaded(String loaderLabel) {
      waitUntilLoaded(DEFAULT_TYPE, loaderLabel);
   }

   void waitUntilLoaded(LoaderComponentType componentType, String loaderLabel);

   default void waitUntilLoaded(String loaderLabel, int seconds) {
      waitUntilLoaded(DEFAULT_TYPE, loaderLabel, seconds);
   }

   void waitUntilLoaded(LoaderComponentType componentType, String loaderLabel, int seconds);

   default void waitUntilLoadedBySelector(String loaderSelector) {
      waitUntilLoadedBySelector(DEFAULT_TYPE, loaderSelector);
   }

   void waitUntilLoadedBySelector(LoaderComponentType componentType, String loaderSelector);

   default void waitUntilLoadedBySelector(String loaderSelector, int seconds) {
      waitUntilLoadedBySelector(DEFAULT_TYPE, loaderSelector, seconds);
   }

   void waitUntilLoadedBySelector(LoaderComponentType componentType, String loaderSelector, int seconds);

   default boolean isLoading(Locator container) {
      return isLoading(DEFAULT_TYPE, container);
   }

   boolean isLoading(LoaderComponentType componentType, Locator container);

   default boolean isLoading(String loaderLabel) {
      return isLoading(DEFAULT_TYPE, loaderLabel);
   }

   boolean isLoading(LoaderComponentType componentType, String loaderLabel);

   default boolean isLoadingBySelector(String loaderSelector) {
      return isLoadingBySelector(DEFAULT_TYPE, loaderSelector);
   }

   boolean isLoadingBySelector(LoaderComponentType componentType, String loaderSelector);
}
