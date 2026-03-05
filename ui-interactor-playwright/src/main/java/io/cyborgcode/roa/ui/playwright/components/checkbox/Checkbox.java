package io.cyborgcode.roa.ui.playwright.components.checkbox;

import com.microsoft.playwright.Locator;
import io.cyborgcode.roa.ui.components.checkbox.CheckboxCore;
import io.cyborgcode.roa.ui.playwright.base.PwBy;
import io.cyborgcode.roa.ui.playwright.base.PwElement;

/**
 * Playwright-specific checkbox component interface.
 *
 * <p>Binds the core generic {@link CheckboxCore}
 * to Playwright's {@link PwElement} type. All method contracts are inherited from core.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface Checkbox extends CheckboxCore<PwElement, PwBy> {

}
