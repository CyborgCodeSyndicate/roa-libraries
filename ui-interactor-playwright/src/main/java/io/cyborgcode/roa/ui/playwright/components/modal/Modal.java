package io.cyborgcode.roa.ui.playwright.components.modal;

import com.microsoft.playwright.Locator;
import io.cyborgcode.roa.ui.components.modal.ModalCore;
import io.cyborgcode.roa.ui.playwright.base.PwBy;
import io.cyborgcode.roa.ui.playwright.base.PwElement;

/**
 * Playwright-specific modal component interface.
 *
 * <p>Binds the core generic {@link ModalCore}
 * to Playwright's {@link PwElement} type. All method contracts are inherited from core.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface Modal extends ModalCore<PwElement, PwBy> {

}
