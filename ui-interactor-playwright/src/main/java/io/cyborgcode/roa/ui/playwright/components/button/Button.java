package io.cyborgcode.roa.ui.playwright.components.button;

import io.cyborgcode.roa.ui.components.button.ButtonCore;
import io.cyborgcode.roa.ui.playwright.base.PwBy;
import io.cyborgcode.roa.ui.playwright.base.PwElement;

/**
 * Playwright-specific button component interface.
 *
 * <p>Binds the core generic {@link ButtonCore}
 * to Playwright's {@link PwElement} type. All method contracts are inherited from core.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface Button extends ButtonCore<PwElement, PwBy> {

}
