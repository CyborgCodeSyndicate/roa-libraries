package io.cyborgcode.roa.ui.playwright.components.link;

import io.cyborgcode.roa.ui.components.link.LinkComponentType;
import io.cyborgcode.roa.ui.components.link.LinkServiceImplCore;
import io.cyborgcode.roa.ui.playwright.base.PwBy;
import io.cyborgcode.roa.ui.playwright.base.PwElement;
import io.cyborgcode.roa.ui.playwright.components.factory.ComponentFactory;
import com.microsoft.playwright.Page;

/**
 * Provides service-level operations for interacting with link components.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public class LinkServiceImpl extends LinkServiceImplCore<PwElement, Link, Page, PwBy>
      implements LinkService {

   public LinkServiceImpl(Page page) {
      super(page);
   }

   @Override
   protected Link createComponent(final LinkComponentType componentType) {
      return ComponentFactory.getLinkComponent(componentType, driver);
   }

}
