package io.cyborgcode.roa.ui.playwright.components.button;

import io.cyborgcode.roa.ui.playwright.components.base.AbstractComponentService;
import io.cyborgcode.roa.ui.playwright.components.base.ComponentType;
import io.cyborgcode.roa.ui.playwright.components.factory.ComponentFactory;
import io.cyborgcode.roa.ui.playwright.log.LogUi;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

/**
 * Provides service-level operations for interacting with button components.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public class ButtonServiceImpl extends AbstractComponentService<ButtonComponentType, Button> implements ButtonService {

   public ButtonServiceImpl(Page page) {
      super(page);
   }

   @Override
   protected Button createComponent(final ButtonComponentType componentType) {
      return ComponentFactory.getButtonComponent(componentType, page);
   }

   @Override
   public void click(final ButtonComponentType componentType, final Locator container, final String buttonText) {
      LogUi.step("Clicking button: '{}' in container", buttonText);
      buttonComponent(componentType).click(container, buttonText);
   }

   @Override
   public void click(final ButtonComponentType componentType, final Locator container) {
      LogUi.step("Clicking button in container");
      buttonComponent(componentType).click(container);
   }

   @Override
   public void click(final ButtonComponentType componentType, final String buttonText) {
      LogUi.step("Clicking button: '{}'", buttonText);
      buttonComponent(componentType).click(buttonText);
   }

   @Override
   public void clickBySelector(final ButtonComponentType componentType, final String buttonSelector) {
      LogUi.step("Clicking button using selector: '{}'", buttonSelector);
      buttonComponent(componentType).clickBySelector(buttonSelector);
   }

   @Override
   public boolean isEnabled(final ButtonComponentType componentType, final Locator container,
                            final String buttonText) {
      LogUi.step("Checking if button is enabled: '{}'", buttonText);
      return buttonComponent(componentType).isEnabled(container, buttonText);
   }

   @Override
   public boolean isEnabled(final ButtonComponentType componentType, final Locator container) {
      LogUi.step("Checking if button is enabled in container");
      return buttonComponent(componentType).isEnabled(container);
   }

   @Override
   public boolean isEnabled(final ButtonComponentType componentType, final String buttonText) {
      LogUi.step("Checking if button is enabled: '{}'", buttonText);
      return buttonComponent(componentType).isEnabled(buttonText);
   }

   @Override
   public boolean isEnabledBySelector(final ButtonComponentType componentType, final String buttonSelector) {
      LogUi.step("Checking if button is enabled using selector: '{}'", buttonSelector);
      return buttonComponent(componentType).isEnabledBySelector(buttonSelector);
   }

   @Override
   public boolean isVisible(final ButtonComponentType componentType, final Locator container,
                            final String buttonText) {
      LogUi.step("Checking if button is visible: '{}'", buttonText);
      return buttonComponent(componentType).isVisible(container, buttonText);
   }

   @Override
   public boolean isVisible(final ButtonComponentType componentType, final Locator container) {
      LogUi.step("Checking if button is visible in container");
      return buttonComponent(componentType).isVisible(container);
   }

   @Override
   public boolean isVisible(final ButtonComponentType componentType, final String buttonText) {
      LogUi.step("Checking if button is visible: '{}'", buttonText);
      return buttonComponent(componentType).isVisible(buttonText);
   }

   @Override
   public boolean isVisibleBySelector(final ButtonComponentType componentType, final String buttonSelector) {
      LogUi.step("Checking if button is visible using selector: '{}'", buttonSelector);
      return buttonComponent(componentType).isVisibleBySelector(buttonSelector);
   }

   @Override
   public void tableInsertion(Locator cellElement, ComponentType componentType, String... values) {
   }

   private Button buttonComponent(final ButtonComponentType componentType) {
      return getOrCreateComponent(componentType);
   }
}
