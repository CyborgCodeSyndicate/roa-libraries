package io.cyborgcode.roa.ui.playwright.components.checkbox;

import io.cyborgcode.roa.ui.components.checkbox.CheckboxComponentType;
import io.cyborgcode.roa.ui.components.checkbox.CheckboxServiceImplCore;
import io.cyborgcode.roa.ui.playwright.base.PwBy;
import io.cyborgcode.roa.ui.playwright.base.PwElement;
import io.cyborgcode.roa.ui.playwright.components.factory.ComponentFactory;
import com.microsoft.playwright.Page;

/**
 * Provides service-level operations for interacting with checkbox components.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public class CheckboxServiceImpl extends CheckboxServiceImplCore<PwElement, Checkbox, Page, PwBy>
      implements CheckboxService {

   public CheckboxServiceImpl(Page page) {
      super(page);
   }

   @Override
   protected Checkbox createComponent(final CheckboxComponentType componentType) {
      return ComponentFactory.getCheckBoxComponent(componentType, driver);
   }

}
