package io.cyborgcode.roa.ui.playwright.service.fluent;

import com.microsoft.playwright.Page;
import io.cyborgcode.roa.framework.storage.Storage;
import io.cyborgcode.roa.ui.components.tab.TabServiceCore;
import io.cyborgcode.roa.ui.playwright.base.PwBy;
import io.cyborgcode.roa.ui.playwright.base.PwElement;
import io.cyborgcode.roa.ui.service.fluent.TabServiceFluentCore;

/**
 * Playwright-specific fluent API for interacting with tab components.
 *
 * @param <T> The fluent UI service type for method chaining.
 * @author Cyborg Code Syndicate 💍👨💻
 */
public class TabServiceFluent<T extends UiServiceFluent<?>>
      extends TabServiceFluentCore<T, PwBy, Page, PwElement> {

   /**
    * Constructs an instance of {@code TabServiceFluent}.
    *
    * @param uiServiceFluent The UI service fluent instance.
    * @param storage         The storage instance for persisting test values.
    * @param tabService      The tab service instance.
    * @param page            The Playwright Page instance.
    */
   public TabServiceFluent(T uiServiceFluent, Storage storage,
                           TabServiceCore<PwElement, PwBy> tabService, Page page) {
      super(uiServiceFluent, storage, tabService, page);
   }

}
