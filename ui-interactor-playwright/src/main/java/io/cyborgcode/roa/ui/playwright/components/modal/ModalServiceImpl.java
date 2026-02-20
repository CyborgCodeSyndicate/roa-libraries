package io.cyborgcode.roa.ui.playwright.components.modal;

import io.cyborgcode.roa.ui.components.modal.ModalComponentType;
import io.cyborgcode.roa.ui.components.modal.ModalServiceImplCore;
import io.cyborgcode.roa.ui.log.LogUi;
import io.cyborgcode.roa.ui.playwright.base.PwBy;
import io.cyborgcode.roa.ui.playwright.components.factory.ComponentFactory;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

/**
 * Provides service-level operations for interacting with modal components.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public class ModalServiceImpl extends ModalServiceImplCore<Locator, Modal, Page>
      implements ModalService {

   public ModalServiceImpl(Page page) {
      super(page);
   }

   @Override
   protected Modal createComponent(final ModalComponentType componentType) {
      return ComponentFactory.getModalComponent(componentType, driver);
   }

   @Override
   public void clickButton(final ModalComponentType componentType, final PwBy buttonLocator) {
      LogUi.step("Clicking button in modal by locator");
      modalComponent(componentType).clickButton(buttonLocator);
   }
}
