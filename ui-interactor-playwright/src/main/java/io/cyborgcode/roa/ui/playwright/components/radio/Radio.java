package io.cyborgcode.roa.ui.playwright.components.radio;

import com.microsoft.playwright.Locator;
import io.cyborgcode.roa.ui.components.radio.RadioCore;
import io.cyborgcode.roa.ui.playwright.base.PwBy;
import io.cyborgcode.roa.ui.playwright.base.PwElement;

/**
 * Playwright-specific radio component interface.
 *
 * <p>Binds the core generic {@link RadioCore}
 * to Playwright's {@link PwElement} type. All method contracts are inherited from core.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface Radio extends RadioCore<PwElement, PwBy> {

}
