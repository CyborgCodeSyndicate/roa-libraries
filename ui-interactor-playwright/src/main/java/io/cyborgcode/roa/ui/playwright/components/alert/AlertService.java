package io.cyborgcode.roa.ui.playwright.components.alert;

import com.microsoft.playwright.Locator;
import io.cyborgcode.roa.ui.components.alert.AlertComponentType;
import io.cyborgcode.roa.ui.components.alert.AlertServiceCore;
import io.cyborgcode.roa.ui.playwright.base.PwBy;

/**
 * Playwright-specific alert service interface.
 *
 * <p>Binds the core generic {@link AlertServiceCore}
 * to Playwright's {@link Locator} type. All method contracts are inherited from core.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface AlertService extends AlertServiceCore<Locator> {

   default String getValue(PwBy containerLocator) {
      return getValue(DEFAULT_TYPE, containerLocator);
   }

   String getValue(AlertComponentType componentType, PwBy containerLocator);

   default boolean isVisible(PwBy containerLocator) {
      return isVisible(DEFAULT_TYPE, containerLocator);
   }

   boolean isVisible(AlertComponentType componentType, PwBy containerLocator);

}
