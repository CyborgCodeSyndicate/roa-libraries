package io.cyborgcode.roa.ui.playwright.components.button;

import com.microsoft.playwright.Locator;
import io.cyborgcode.roa.ui.components.button.ButtonCore;
import io.cyborgcode.roa.ui.playwright.base.PwBy;

/**
 * Playwright-specific button component interface.
 *
 * <p>Binds the core generic {@link ButtonCore}
 * to Playwright's {@link Locator} type. All method contracts are inherited from core.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface Button extends ButtonCore<Locator> {

   void click(PwBy buttonSelector);

   boolean isEnabled(PwBy buttonSelector);

   boolean isVisible(PwBy buttonSelector);
   
   
}
