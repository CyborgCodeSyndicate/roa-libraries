package io.cyborgcode.roa.ui.playwright.components.loader;

import com.microsoft.playwright.Locator;
import io.cyborgcode.roa.ui.components.loader.LoaderServiceCore;
import io.cyborgcode.roa.ui.playwright.base.PwBy;
import io.cyborgcode.roa.ui.playwright.base.PwElement;

/**
 * Playwright-specific loader service interface.
 *
 * <p>Binds the core generic {@link LoaderServiceCore}
 * to Playwright's {@link PwElement} type. All method contracts are inherited from core.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface LoaderService extends LoaderServiceCore<PwElement, PwBy> {

}
