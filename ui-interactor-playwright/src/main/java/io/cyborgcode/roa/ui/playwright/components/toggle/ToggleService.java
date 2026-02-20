package io.cyborgcode.roa.ui.playwright.components.toggle;

import com.microsoft.playwright.Locator;
import io.cyborgcode.roa.ui.components.toggle.ToggleComponentType;
import io.cyborgcode.roa.ui.components.toggle.ToggleServiceCore;
import io.cyborgcode.roa.ui.playwright.base.PwBy;

/**
 * Playwright-specific toggle service interface.
 *
 * <p>Binds the core generic {@link ToggleServiceCore}
 * to Playwright's {@link Locator} type. All method contracts are inherited from core.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface ToggleService extends ToggleServiceCore<Locator> {

   default void activate(PwBy toggleLocator) {
      activate(DEFAULT_TYPE, toggleLocator);
   }

   void activate(ToggleComponentType componentType, PwBy toggleLocator);

   default void deactivate(PwBy toggleLocator) {
      deactivate(DEFAULT_TYPE, toggleLocator);
   }

   void deactivate(ToggleComponentType componentType, PwBy toggleLocator);

   default boolean isEnabled(PwBy toggleLocator) {
      return isEnabled(DEFAULT_TYPE, toggleLocator);
   }

   boolean isEnabled(ToggleComponentType componentType, PwBy toggleLocator);

   default boolean isActivated(PwBy toggleLocator) {
      return isActivated(DEFAULT_TYPE, toggleLocator);
   }

   boolean isActivated(ToggleComponentType componentType, PwBy toggleLocator);
}
