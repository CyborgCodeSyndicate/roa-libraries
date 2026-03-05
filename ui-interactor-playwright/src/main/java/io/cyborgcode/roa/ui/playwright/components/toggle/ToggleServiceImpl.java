package io.cyborgcode.roa.ui.playwright.components.toggle;

import io.cyborgcode.roa.ui.components.toggle.ToggleComponentType;
import io.cyborgcode.roa.ui.components.toggle.ToggleServiceImplCore;
import io.cyborgcode.roa.ui.playwright.base.PwBy;
import io.cyborgcode.roa.ui.playwright.base.PwElement;
import io.cyborgcode.roa.ui.playwright.components.factory.ComponentFactory;
import com.microsoft.playwright.Page;

/**
 * Provides service-level operations for interacting with toggle components.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public class ToggleServiceImpl extends ToggleServiceImplCore<PwElement, Toggle, Page, PwBy>
      implements ToggleService {

   public ToggleServiceImpl(Page page) {
      super(page);
   }

   @Override
   protected Toggle createComponent(final ToggleComponentType componentType) {
      return ComponentFactory.getToggleComponent(componentType, driver);
   }

}
