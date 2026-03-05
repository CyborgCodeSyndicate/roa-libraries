package io.cyborgcode.roa.ui.playwright.components.tab;

import com.microsoft.playwright.Locator;
import io.cyborgcode.roa.ui.components.tab.TabCore;
import io.cyborgcode.roa.ui.playwright.base.PwBy;
import io.cyborgcode.roa.ui.playwright.base.PwElement;

/**
 * Playwright-specific tab component interface.
 *
 * <p>Binds the core generic {@link TabCore}
 * to Playwright's {@link PwElement} type. All method contracts are inherited from core.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface Tab extends TabCore<PwElement, PwBy> {

}
