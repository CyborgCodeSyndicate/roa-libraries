package io.cyborgcode.roa.ui.playwright.components.radio;

import io.cyborgcode.roa.ui.components.base.ComponentType;
import io.cyborgcode.roa.ui.components.radio.RadioComponentType;
import io.cyborgcode.roa.ui.components.radio.RadioServiceImplCore;
import io.cyborgcode.roa.ui.playwright.base.PwBy;
import io.cyborgcode.roa.ui.playwright.base.PwElement;
import io.cyborgcode.roa.ui.playwright.components.factory.ComponentFactory;
import com.microsoft.playwright.Page;

/**
 * Provides service-level operations for interacting with radio button components.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public class RadioServiceImpl extends RadioServiceImplCore<PwElement, Radio, Page, PwBy>
      implements RadioService {

   public RadioServiceImpl(Page page) {
      super(page);
   }

   @Override
   protected Radio createComponent(final RadioComponentType componentType) {
      return ComponentFactory.getRadioComponent(componentType, driver);
   }

   @Override
   public void insertion(ComponentType componentType, PwBy selector, Object... values) {
   }
}
