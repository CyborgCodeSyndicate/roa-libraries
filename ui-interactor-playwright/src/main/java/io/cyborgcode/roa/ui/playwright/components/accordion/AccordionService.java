package io.cyborgcode.roa.ui.playwright.components.accordion;

import com.microsoft.playwright.Locator;
import io.cyborgcode.roa.ui.components.accordion.AccordionServiceCore;
import io.cyborgcode.roa.ui.playwright.base.PwBy;
import io.cyborgcode.roa.ui.playwright.base.PwElement;

/**
 * Playwright-specific accordion service interface.
 *
 * <p>Binds the core generic {@link AccordionServiceCore}
 * to Playwright's {@link PwElement} type. All method contracts are inherited from core.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface AccordionService extends AccordionServiceCore<PwElement, PwBy> {

}
