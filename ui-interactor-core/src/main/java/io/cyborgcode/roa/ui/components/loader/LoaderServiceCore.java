package io.cyborgcode.roa.ui.components.loader;

import io.cyborgcode.roa.ui.components.base.BaseUiElement;
import io.cyborgcode.roa.ui.components.loader.LoaderComponentType;
import io.cyborgcode.utilities.reflections.ReflectionUtil;

import static io.cyborgcode.roa.ui.config.UiConfigHolderCore.getUiConfig;

/**
 * Provides service-level methods for interacting with loader/spinner UI components.
 *
 * <p>This interface defines operations for waiting until loaded and checking loading state,
 * delegating the actual interactions to specific implementations based on the configured
 * {@link LoaderComponentType}.
 *
 * @param <E> The container/element type (e.g., Playwright's {@code Locator} or Selenium's {@code WebElement}).
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface LoaderServiceCore<E extends BaseUiElement, L> {

   LoaderComponentType DEFAULT_TYPE = getDefaultType();

   /**
    * Retrieves the default loader component type from the configuration.
    *
    * @return The default LoaderComponentType.
    */
   static LoaderComponentType getDefaultType() {
      try {
         return ReflectionUtil.findEnumImplementationsOfInterface(LoaderComponentType.class,
               getUiConfig().loaderDefaultType(),
               getUiConfig().projectPackages());
      } catch (Exception ignored) {
         return null;
      }
   }

   default boolean isVisible(E container) {
      return isVisible(DEFAULT_TYPE, container);
   }

   boolean isVisible(LoaderComponentType componentType, E container);

   default void waitToBeShown(E container, int secondsShown) {
      waitToBeShown(DEFAULT_TYPE, container, secondsShown);
   }

   void waitToBeShown(LoaderComponentType componentType, E container, int secondsShown);

   default void waitToBeShown(int secondsShown) {
      waitToBeShown(DEFAULT_TYPE, secondsShown);
   }

   void waitToBeShown(LoaderComponentType componentType, int secondsShown);

   default void waitToBeRemoved(E container, int secondsRemoved) {
      waitToBeRemoved(DEFAULT_TYPE, container, secondsRemoved);
   }

   void waitToBeRemoved(LoaderComponentType componentType, E container, int secondsRemoved);

   default void waitToBeRemoved(int secondsRemoved) {
      waitToBeRemoved(DEFAULT_TYPE, secondsRemoved);
   }

   void waitToBeRemoved(LoaderComponentType componentType, int secondsRemoved);

   default boolean isVisible(L loaderLocator) {
      return isVisible(DEFAULT_TYPE, loaderLocator);
   }

   boolean isVisible(LoaderComponentType componentType, L loaderLocator);

   default void waitToBeShown(L loaderLocator, int secondsShown) {
      waitToBeShown(DEFAULT_TYPE, loaderLocator, secondsShown);
   }

   void waitToBeShown(LoaderComponentType componentType, L loaderLocator, int secondsShown);

   default void waitToBeRemoved(L loaderLocator, int secondsRemoved) {
      waitToBeRemoved(DEFAULT_TYPE, loaderLocator, secondsRemoved);
   }

   void waitToBeRemoved(LoaderComponentType componentType, L loaderLocator, int secondsRemoved);

   default void waitToBeShownAndRemoved(LoaderComponentType componentType, E container,
                                        int secondsShown, int secondsRemoved) {
      waitToBeShown(componentType, container, secondsShown);
      waitToBeRemoved(componentType, container, secondsRemoved);
   }

   /**
    * Waits for the loader to be shown and then removed within the given durations, using the given loader component
    * type.
    *
    * @param componentType  The specific loader component type.
    * @param secondsShown   The maximum time to wait for the loader to be shown, in seconds.
    * @param secondsRemoved The maximum time to wait for the loader to be removed, in seconds.
    */
   default void waitToBeShownAndRemoved(LoaderComponentType componentType, int secondsShown, int secondsRemoved) {
      waitToBeShown(componentType, secondsShown);
      waitToBeRemoved(componentType, secondsRemoved);
   }

   /**
    * Waits for the loader to be shown and then removed using the specified locator within the given durations, using
    * the given loader component type.
    *
    * @param componentType  The specific loader component type.
    * @param loaderLocator  The By locator for the loader element.
    * @param secondsShown   The maximum time to wait for the loader to be shown, in seconds.
    * @param secondsRemoved The maximum time to wait for the loader to be removed, in seconds.
    */
   default void waitToBeShownAndRemoved(LoaderComponentType componentType, L loaderLocator, int secondsShown,
                                        int secondsRemoved) {
      waitToBeShown(componentType, loaderLocator, secondsShown);
      waitToBeRemoved(componentType, loaderLocator, secondsRemoved);
   }

}
