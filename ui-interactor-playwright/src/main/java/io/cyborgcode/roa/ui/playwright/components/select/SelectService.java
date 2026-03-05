package io.cyborgcode.roa.ui.playwright.components.select;

import com.microsoft.playwright.Locator;
import io.cyborgcode.roa.ui.components.select.SelectServiceCore;
import io.cyborgcode.roa.ui.playwright.base.PwBy;
import io.cyborgcode.roa.ui.playwright.base.PwElement;

/**
 * Playwright-specific select service interface.
 *
 * <p>Binds the core generic {@link SelectServiceCore}
 * to Playwright's {@link PwElement} type. All method contracts are inherited from core.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface SelectService extends SelectServiceCore<PwElement, PwBy> {

}
