package io.cyborgcode.roa.ui.playwright.components.button;

import com.microsoft.playwright.Locator;
import io.cyborgcode.roa.ui.components.button.ButtonComponentType;
import io.cyborgcode.roa.ui.components.button.ButtonServiceCore;
import io.cyborgcode.roa.ui.playwright.base.PwBy;

/**
 * Playwright-specific button service interface.
 *
 * <p>Binds the core generic {@link ButtonServiceCore}
 * to Playwright's {@link Locator} type. All method contracts are inherited from core.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface ButtonService extends ButtonServiceCore<Locator> {

   default void click(PwBy buttonSelector) {
      click(DEFAULT_TYPE, buttonSelector);
   }

   void click(ButtonComponentType componentType, PwBy buttonSelector);

   default boolean isEnabled(PwBy buttonSelector) {
      return isEnabled(DEFAULT_TYPE, buttonSelector);
   }

   boolean isEnabled(ButtonComponentType componentType, PwBy buttonSelector);

   default boolean isVisible(PwBy buttonSelector) {
      return isVisible(DEFAULT_TYPE, buttonSelector);
   }

   boolean isVisible(ButtonComponentType componentType, PwBy buttonSelector);

}
