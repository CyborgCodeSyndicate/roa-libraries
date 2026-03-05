package io.cyborgcode.roa.ui.playwright.components.loader;

import com.microsoft.playwright.Locator;
import io.cyborgcode.roa.ui.components.loader.LoaderCore;
import io.cyborgcode.roa.ui.playwright.base.PwBy;
import io.cyborgcode.roa.ui.playwright.base.PwElement;

/**
 * Playwright-specific loader component interface.
 *
 * <p>Binds the core generic {@link LoaderCore}
 * to Playwright's {@link PwElement} type. All method contracts are inherited from core.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface Loader extends LoaderCore<PwElement, PwBy> {

}
