package io.cyborgcode.roa.ui.playwright.components.link;

import com.microsoft.playwright.Locator;
import io.cyborgcode.roa.ui.components.link.LinkComponentType;
import io.cyborgcode.roa.ui.components.link.LinkServiceCore;
import io.cyborgcode.roa.ui.playwright.base.PwBy;

/**
 * Playwright-specific link service interface.
 *
 * <p>Binds the core generic {@link LinkServiceCore}
 * to Playwright's {@link Locator} type. All method contracts are inherited from core.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface LinkService extends LinkServiceCore<Locator> {

   default void click(PwBy linkSelector) {
      click(DEFAULT_TYPE, linkSelector);
   }

   void click(LinkComponentType componentType, PwBy linkSelector);

   default boolean isEnabled(PwBy linkSelector) {
      return isEnabled(DEFAULT_TYPE, linkSelector);
   }

   boolean isEnabled(LinkComponentType componentType, PwBy linkSelector);

   default boolean isVisible(PwBy linkSelector) {
      return isVisible(DEFAULT_TYPE, linkSelector);
   }

   boolean isVisible(LinkComponentType componentType, PwBy linkSelector);

   default void doubleClick(PwBy linkSelector) {
      doubleClick(DEFAULT_TYPE, linkSelector);
   }

   void doubleClick(LinkComponentType componentType, PwBy linkSelector);


}
