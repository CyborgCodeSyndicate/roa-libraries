package io.cyborgcode.roa.ui.playwright.components.input;

import com.microsoft.playwright.Locator;
import io.cyborgcode.roa.ui.components.input.InputCore;
import io.cyborgcode.roa.ui.playwright.base.PwBy;
import io.cyborgcode.roa.ui.playwright.base.PwElement;

/**
 * Playwright-specific input component interface.
 *
 * <p>Binds the core generic {@link InputCore}
 * to Playwright's {@link PwElement} type. All method contracts are inherited from core.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface Input extends InputCore<PwElement, PwBy> {

}
