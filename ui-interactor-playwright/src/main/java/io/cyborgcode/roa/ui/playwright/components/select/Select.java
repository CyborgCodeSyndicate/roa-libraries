package io.cyborgcode.roa.ui.playwright.components.select;

import com.microsoft.playwright.Locator;
import io.cyborgcode.roa.ui.components.select.SelectCore;
import io.cyborgcode.roa.ui.playwright.base.PwBy;
import io.cyborgcode.roa.ui.playwright.base.PwElement;

/**
 * Playwright-specific select component interface.
 *
 * <p>Binds the core generic {@link SelectCore}
 * to Playwright's {@link PwElement} type. All method contracts are inherited from core.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface Select extends SelectCore<PwElement, PwBy> {

}
