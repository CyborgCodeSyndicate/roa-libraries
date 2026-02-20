package io.cyborgcode.roa.ui.playwright.components.toggle;

import io.cyborgcode.roa.ui.components.toggle.ToggleComponentType;
import io.cyborgcode.roa.ui.components.toggle.ToggleServiceImplCore;
import io.cyborgcode.roa.ui.log.LogUi;
import io.cyborgcode.roa.ui.playwright.base.PwBy;
import io.cyborgcode.roa.ui.playwright.components.factory.ComponentFactory;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

/**
 * Provides service-level operations for interacting with toggle components.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public class ToggleServiceImpl extends ToggleServiceImplCore<Locator, Toggle, Page>
      implements ToggleService {

   public ToggleServiceImpl(Page page) {
      super(page);
   }

   @Override
   protected Toggle createComponent(final ToggleComponentType componentType) {
      return ComponentFactory.getToggleComponent(componentType, driver);
   }

   @Override
   public void activate(final ToggleComponentType componentType, final PwBy toggleLocator) {
      LogUi.step("Activating toggle by locator");
      toggleComponent(componentType).activate(toggleLocator);
   }

   @Override
   public void deactivate(final ToggleComponentType componentType, final PwBy toggleLocator) {
      LogUi.step("Deactivating toggle by locator");
      toggleComponent(componentType).deactivate(toggleLocator);
   }

   @Override
   public boolean isEnabled(final ToggleComponentType componentType, final PwBy toggleLocator) {
      return toggleComponent(componentType).isEnabled(toggleLocator);
   }

   @Override
   public boolean isActivated(final ToggleComponentType componentType, final PwBy toggleLocator) {
      return toggleComponent(componentType).isActivated(toggleLocator);
   }
}
