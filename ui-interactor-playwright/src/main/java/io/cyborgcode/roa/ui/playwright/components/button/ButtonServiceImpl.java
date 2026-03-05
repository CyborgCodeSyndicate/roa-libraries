package io.cyborgcode.roa.ui.playwright.components.button;

import com.microsoft.playwright.Page;
import io.cyborgcode.roa.ui.components.button.ButtonComponentType;
import io.cyborgcode.roa.ui.components.button.ButtonServiceImplCore;
import io.cyborgcode.roa.ui.playwright.base.PwBy;
import io.cyborgcode.roa.ui.playwright.base.PwElement;
import io.cyborgcode.roa.ui.playwright.components.factory.ComponentFactory;

/**
 * Provides service-level operations for interacting with button components.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public class ButtonServiceImpl extends ButtonServiceImplCore<PwElement, Button, Page, PwBy>
      implements ButtonService {

   public ButtonServiceImpl(Page page) {
      super(page);
   }

   @Override
   protected Button createComponent(final ButtonComponentType componentType) {
      return ComponentFactory.getButtonComponent(componentType, driver);
   }

}
