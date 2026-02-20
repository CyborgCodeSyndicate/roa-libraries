package io.cyborgcode.roa.ui.playwright.components.modal;

import com.microsoft.playwright.Locator;
import io.cyborgcode.roa.ui.components.modal.ModalComponentType;
import io.cyborgcode.roa.ui.components.modal.ModalServiceCore;
import io.cyborgcode.roa.ui.playwright.base.PwBy;

/**
 * Playwright-specific modal service interface.
 *
 * <p>Binds the core generic {@link ModalServiceCore}
 * to Playwright's {@link Locator} type. All method contracts are inherited from core.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface ModalService extends ModalServiceCore<Locator> {

   default void clickButton(PwBy buttonLocator) {
      clickButton(DEFAULT_TYPE, buttonLocator);
   }

   void clickButton(ModalComponentType componentType, PwBy buttonLocator);
}
