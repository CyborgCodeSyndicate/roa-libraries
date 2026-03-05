package io.cyborgcode.roa.ui.playwright.components.tab;

import com.microsoft.playwright.Page;
import io.cyborgcode.roa.ui.components.tab.TabComponentType;
import io.cyborgcode.roa.ui.components.tab.TabServiceImplCore;
import io.cyborgcode.roa.ui.playwright.base.PwBy;
import io.cyborgcode.roa.ui.playwright.base.PwElement;
import io.cyborgcode.roa.ui.playwright.components.factory.ComponentFactory;

/**
 * Provides service-level operations for interacting with tab components.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public class TabServiceImpl extends TabServiceImplCore<PwElement, Tab, Page, PwBy>
      implements TabService {

   public TabServiceImpl(Page page) {
      super(page);
   }

   @Override
   protected Tab createComponent(final TabComponentType componentType) {
      return ComponentFactory.getTabComponent(componentType, driver);
   }

}
