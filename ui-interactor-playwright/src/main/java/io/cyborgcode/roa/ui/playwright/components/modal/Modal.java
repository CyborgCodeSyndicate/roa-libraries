package io.cyborgcode.roa.ui.playwright.components.modal;

import com.microsoft.playwright.Locator;
import io.cyborgcode.roa.ui.components.modal.ModalCore;
import io.cyborgcode.roa.ui.playwright.base.PwBy;

/**
 * Playwright-specific modal component interface.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface Modal extends ModalCore<Locator> {

   void clickButton(PwBy buttonLocator);

}
