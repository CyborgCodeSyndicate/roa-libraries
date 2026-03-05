package io.cyborgcode.roa.ui.playwright.components.toggle;

import com.microsoft.playwright.Locator;
import io.cyborgcode.roa.ui.components.toggle.ToggleServiceCore;
import io.cyborgcode.roa.ui.playwright.base.PwBy;
import io.cyborgcode.roa.ui.playwright.base.PwElement;

/**
 * Playwright-specific toggle service interface.
 *
 * <p>Binds the core generic {@link ToggleServiceCore}
 * to Playwright's {@link PwElement} type. All method contracts are inherited from core.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface ToggleService extends ToggleServiceCore<PwElement, PwBy> {

}
