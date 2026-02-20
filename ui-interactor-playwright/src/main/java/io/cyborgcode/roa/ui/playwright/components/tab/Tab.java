package io.cyborgcode.roa.ui.playwright.components.tab;

import com.microsoft.playwright.Locator;
import io.cyborgcode.roa.ui.components.tab.TabCore;
import io.cyborgcode.roa.ui.playwright.base.PwBy;

/**
 * Playwright-specific tab component interface.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface Tab extends TabCore<Locator> {

   void click(PwBy tabSelector);

   boolean isEnabled(PwBy tabSelector);

   boolean isVisible(PwBy tabSelector);
   
   boolean isSelected(PwBy tabSelector);

}
