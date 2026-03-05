package io.cyborgcode.roa.ui.playwright.components.link;

import com.microsoft.playwright.Locator;
import io.cyborgcode.roa.ui.components.link.LinkServiceCore;
import io.cyborgcode.roa.ui.playwright.base.PwBy;
import io.cyborgcode.roa.ui.playwright.base.PwElement;

/**
 * Playwright-specific link service interface.
 *
 * <p>Binds the core generic {@link LinkServiceCore}
 * to Playwright's {@link PwElement} type. All method contracts are inherited from core.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface LinkService extends LinkServiceCore<PwElement, PwBy> {

}
