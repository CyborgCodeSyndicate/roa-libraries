package io.cyborgcode.roa.ui.playwright.components.link;

import com.microsoft.playwright.Locator;
import io.cyborgcode.roa.ui.components.link.LinkCore;
import io.cyborgcode.roa.ui.playwright.base.PwBy;
import io.cyborgcode.roa.ui.playwright.base.PwElement;

/**
 * Playwright-specific link component interface.
 *
 * <p>Binds the core generic {@link LinkCore}
 * to Playwright's {@link PwElement} type. All method contracts are inherited from core.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface Link extends LinkCore<PwElement, PwBy> {

}
