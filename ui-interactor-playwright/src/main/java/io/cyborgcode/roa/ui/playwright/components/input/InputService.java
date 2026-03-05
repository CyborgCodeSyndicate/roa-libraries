package io.cyborgcode.roa.ui.playwright.components.input;

import com.microsoft.playwright.Locator;
import io.cyborgcode.roa.ui.components.input.InputComponentType;
import io.cyborgcode.roa.ui.components.input.InputServiceCore;
import io.cyborgcode.roa.ui.playwright.base.PwBy;
import io.cyborgcode.roa.ui.playwright.base.PwElement;

/**
 * Playwright-specific input service interface.
 *
 * <p>Binds the core generic {@link InputServiceCore}
 * to Playwright's {@link PwElement} type. All method contracts are inherited from core.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface InputService extends InputServiceCore<PwElement, PwBy> {

}
