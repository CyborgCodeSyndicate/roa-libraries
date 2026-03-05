package io.cyborgcode.roa.ui.playwright.components.radio;

import com.microsoft.playwright.Locator;
import io.cyborgcode.roa.ui.components.radio.RadioServiceCore;
import io.cyborgcode.roa.ui.playwright.base.PwBy;
import io.cyborgcode.roa.ui.playwright.base.PwElement;

/**
 * Playwright-specific radio service interface.
 *
 * <p>Binds the core generic {@link RadioServiceCore}
 * to Playwright's {@link PwElement} type. All method contracts are inherited from core.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface RadioService extends RadioServiceCore<PwElement, PwBy> {

}
