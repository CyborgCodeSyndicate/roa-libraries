package io.cyborgcode.roa.ui.playwright.components.tab;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.cyborgcode.roa.ui.components.tab.TabComponentType;
import io.cyborgcode.roa.ui.components.tab.TabServiceImplCore;
import io.cyborgcode.roa.ui.log.LogUi;
import io.cyborgcode.roa.ui.playwright.base.PwBy;
import io.cyborgcode.roa.ui.playwright.components.factory.ComponentFactory;

/**
 * Provides service-level operations for interacting with tab components.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public class TabServiceImpl extends TabServiceImplCore<Locator, Tab, Page>
      implements TabService {

   public TabServiceImpl(Page page) {
      super(page);
   }

   @Override
   protected Tab createComponent(final TabComponentType componentType) {
      return ComponentFactory.getTabComponent(componentType, driver);
   }

   @Override
   public void click(final TabComponentType componentType, final PwBy tabSelector) {
      LogUi.step("Clicking link by selector");
      tabComponent(componentType).click(tabSelector);
   }

   @Override
   public boolean isVisible(final TabComponentType componentType, final PwBy tabSelector) {
      return tabComponent(componentType).isVisible(tabSelector);
   }

   @Override
   public boolean isEnabled(final TabComponentType componentType, final PwBy tabSelector) {
      return tabComponent(componentType).isEnabled(tabSelector);
   }

   @Override
   public boolean isSelected(final TabComponentType componentType, final PwBy tabSelector) {
      return tabComponent(componentType).isSelected(tabSelector);
   }

}
