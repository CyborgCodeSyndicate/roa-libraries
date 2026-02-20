package io.cyborgcode.roa.ui.playwright.components.tab;

import com.microsoft.playwright.Locator;
import io.cyborgcode.roa.ui.components.link.LinkComponentType;
import io.cyborgcode.roa.ui.components.tab.TabComponentType;
import io.cyborgcode.roa.ui.components.tab.TabServiceCore;
import io.cyborgcode.roa.ui.playwright.base.PwBy;

/**
 * Playwright-specific tab service interface.
 *
 * <p>Binds the core generic {@link TabServiceCore}
 * to Playwright's {@link Locator} type. All method contracts are inherited from core.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface TabService extends TabServiceCore<Locator> {

   default void click(PwBy tabSelector) {
      click(DEFAULT_TYPE, tabSelector);
   }

   void click(TabComponentType componentType, PwBy tabSelector);

   default boolean isEnabled(PwBy tabSelector) {
      return isEnabled(DEFAULT_TYPE, tabSelector);
   }

   boolean isEnabled(TabComponentType componentType, PwBy tabSelector);

   default boolean isVisible(PwBy tabSelector) {
      return isVisible(DEFAULT_TYPE, tabSelector);
   }

   boolean isVisible(TabComponentType componentType, PwBy tabSelector);

   default boolean isSelected(PwBy tabSelector) {
      return isSelected(DEFAULT_TYPE, tabSelector);
   }

   boolean isSelected(TabComponentType componentType, PwBy tabSelector);


}
