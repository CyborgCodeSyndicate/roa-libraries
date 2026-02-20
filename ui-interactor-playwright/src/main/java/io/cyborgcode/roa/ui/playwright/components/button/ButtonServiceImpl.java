package io.cyborgcode.roa.ui.playwright.components.button;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.cyborgcode.roa.ui.components.button.ButtonComponentType;
import io.cyborgcode.roa.ui.components.button.ButtonServiceImplCore;
import io.cyborgcode.roa.ui.log.LogUi;
import io.cyborgcode.roa.ui.playwright.base.PwBy;
import io.cyborgcode.roa.ui.playwright.components.factory.ComponentFactory;

/**
 * Provides service-level operations for interacting with button components.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public class ButtonServiceImpl extends ButtonServiceImplCore<Locator, Button, Page>
      implements ButtonService {

   public ButtonServiceImpl(Page page) {
      super(page);
   }

   @Override
   protected Button createComponent(final ButtonComponentType componentType) {
      return ComponentFactory.getButtonComponent(componentType, driver);
   }

   public void click(final ButtonComponentType componentType, final PwBy buttonSelector) {
      LogUi.step("Clicking button using selector: '{}'", buttonSelector);
      buttonComponent(componentType).click(buttonSelector);
   }

   public boolean isEnabled(final ButtonComponentType componentType, final PwBy buttonSelector) {
      LogUi.step("Checking if button is enabled using selector: '{}'", buttonSelector);
      return buttonComponent(componentType).isEnabled(buttonSelector);
   }

   public boolean isVisible(final ButtonComponentType componentType, final PwBy buttonSelector) {
      LogUi.step("Checking if button is visible using selector: '{}'", buttonSelector);
      return buttonComponent(componentType).isVisible(buttonSelector);
   }
}
