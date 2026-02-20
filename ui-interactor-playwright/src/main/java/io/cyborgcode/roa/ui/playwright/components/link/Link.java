package io.cyborgcode.roa.ui.playwright.components.link;

import com.microsoft.playwright.Locator;
import io.cyborgcode.roa.ui.components.link.LinkCore;
import io.cyborgcode.roa.ui.playwright.base.PwBy;

/**
 * Playwright-specific link component interface.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface Link extends LinkCore<Locator> {

   void click(PwBy linkSelector);

   boolean isEnabled(PwBy linkSelector);

   boolean isVisible(PwBy linkSelector);
   
   void doubleClick(PwBy buttonLocator);

}
