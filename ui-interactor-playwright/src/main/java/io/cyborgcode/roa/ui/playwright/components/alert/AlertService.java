package io.cyborgcode.roa.ui.playwright.components.alert;

import com.microsoft.playwright.Locator;
import io.cyborgcode.roa.ui.components.alert.AlertServiceCore;
import io.cyborgcode.roa.ui.playwright.base.PwBy;
import io.cyborgcode.roa.ui.playwright.base.PwElement;

/**
 * Playwright-specific alert service interface.
 *
 * <p>Binds the core generic {@link AlertServiceCore}
 * to Playwright's {@link PwElement} type. All method contracts are inherited from core.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface AlertService extends AlertServiceCore<PwElement, PwBy> {

}
