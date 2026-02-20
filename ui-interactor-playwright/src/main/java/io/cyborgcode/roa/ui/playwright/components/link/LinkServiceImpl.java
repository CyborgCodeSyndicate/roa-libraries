package io.cyborgcode.roa.ui.playwright.components.link;

import io.cyborgcode.roa.ui.components.button.ButtonComponentType;
import io.cyborgcode.roa.ui.components.link.LinkComponentType;
import io.cyborgcode.roa.ui.components.link.LinkServiceImplCore;
import io.cyborgcode.roa.ui.log.LogUi;
import io.cyborgcode.roa.ui.playwright.base.PwBy;
import io.cyborgcode.roa.ui.playwright.components.factory.ComponentFactory;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

/**
 * Provides service-level operations for interacting with link components.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public class LinkServiceImpl extends LinkServiceImplCore<Locator, Link, Page>
      implements LinkService {

   public LinkServiceImpl(Page page) {
      super(page);
   }

   @Override
   protected Link createComponent(final LinkComponentType componentType) {
      return ComponentFactory.getLinkComponent(componentType, driver);
   }

   @Override
   public void click(final LinkComponentType componentType, final PwBy linkSelector) {
      LogUi.step("Clicking link by selector");
      linkComponent(componentType).click(linkSelector);
   }

   @Override
   public boolean isVisible(final LinkComponentType componentType, final PwBy linkSelector) {
      return linkComponent(componentType).isVisible(linkSelector);
   }

   @Override
   public boolean isEnabled(final LinkComponentType componentType, final PwBy linkSelector) {
      return linkComponent(componentType).isEnabled(linkSelector);
   }

   @Override
   public void doubleClick(final LinkComponentType componentType, final PwBy linkSelector) {
      LogUi.step("Clicking link by selector");
      linkComponent(componentType).doubleClick(linkSelector);
   }
}
