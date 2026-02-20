package io.cyborgcode.roa.ui.playwright.components.alert;

import com.microsoft.playwright.Locator;
import io.cyborgcode.roa.ui.components.alert.AlertCore;
import io.cyborgcode.roa.ui.playwright.base.PwBy;

/**
 * Playwright-specific alert component interface.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface Alert extends AlertCore<Locator> {

   String getValue(PwBy containerLocator);

   boolean isVisible(PwBy containerLocator);

}
