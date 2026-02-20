package io.cyborgcode.roa.ui.playwright.components.toggle;

import com.microsoft.playwright.Locator;
import io.cyborgcode.roa.ui.components.toggle.ToggleCore;
import io.cyborgcode.roa.ui.playwright.base.PwBy;

/**
 * Playwright-specific toggle component interface.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface Toggle extends ToggleCore<Locator> {

   void activate(PwBy toggleLocator);

   void deactivate(PwBy toggleLocator);

   boolean isEnabled(PwBy toggleLocator);

   boolean isActivated(PwBy toggleLocator);

}
