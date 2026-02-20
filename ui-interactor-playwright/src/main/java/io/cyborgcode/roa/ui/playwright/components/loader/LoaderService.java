package io.cyborgcode.roa.ui.playwright.components.loader;

import com.microsoft.playwright.Locator;
import io.cyborgcode.roa.ui.components.loader.LoaderComponentType;
import io.cyborgcode.roa.ui.components.loader.LoaderServiceCore;
import io.cyborgcode.roa.ui.playwright.base.PwBy;

/**
 * Playwright-specific loader service interface.
 *
 * <p>Binds the core generic {@link LoaderServiceCore}
 * to Playwright's {@link Locator} type. All method contracts are inherited from core.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface LoaderService extends LoaderServiceCore<Locator> {

   default boolean isVisible(PwBy loaderLocator) {
      return isVisible(DEFAULT_TYPE, loaderLocator);
   }

   boolean isVisible(LoaderComponentType componentType, PwBy loaderLocator);

   default void waitToBeShown(PwBy loaderLocator, int secondsShown) {
      waitToBeShown(DEFAULT_TYPE, loaderLocator, secondsShown);
   }

   void waitToBeShown(LoaderComponentType componentType, PwBy loaderLocator, int secondsShown);

   default void waitToBeRemoved(PwBy loaderLocator, int secondsRemoved) {
      waitToBeRemoved(DEFAULT_TYPE, loaderLocator, secondsRemoved);
   }

   void waitToBeRemoved(LoaderComponentType componentType, PwBy loaderLocator, int secondsRemoved);
}
