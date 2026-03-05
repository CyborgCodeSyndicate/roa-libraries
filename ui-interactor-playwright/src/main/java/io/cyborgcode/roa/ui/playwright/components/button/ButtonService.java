package io.cyborgcode.roa.ui.playwright.components.button;

import com.microsoft.playwright.Locator;
import io.cyborgcode.roa.ui.components.button.ButtonComponentType;
import io.cyborgcode.roa.ui.components.button.ButtonServiceCore;
import io.cyborgcode.roa.ui.playwright.base.PwBy;
import io.cyborgcode.roa.ui.playwright.base.PwElement;

/**
 * Playwright-specific button service interface.
 *
 * <p>Binds the core generic {@link ButtonServiceCore}
 * to Playwright's {@link PwElement} type. All method contracts are inherited from core.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface ButtonService extends ButtonServiceCore<PwElement, PwBy> {

}
