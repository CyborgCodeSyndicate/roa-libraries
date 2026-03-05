package io.cyborgcode.roa.ui.playwright.service.fluent;

import com.microsoft.playwright.Page;
import io.cyborgcode.roa.framework.storage.Storage;
import io.cyborgcode.roa.ui.components.link.LinkServiceCore;
import io.cyborgcode.roa.ui.playwright.base.PwBy;
import io.cyborgcode.roa.ui.playwright.base.PwElement;
import io.cyborgcode.roa.ui.service.fluent.LinkServiceFluentCore;

/**
 * Playwright-specific fluent API for interacting with link components.
 *
 * @param <T> The fluent UI service type for method chaining.
 * @author Cyborg Code Syndicate 💍👨💻
 */
public class LinkServiceFluent<T extends UiServiceFluent<?>>
      extends LinkServiceFluentCore<T, PwBy, Page, PwElement> {

   /**
    * Constructs an instance of {@code LinkServiceFluent}.
    *
    * @param uiServiceFluent The UI service fluent instance.
    * @param storage         The storage instance for persisting test values.
    * @param linkService     The link service instance.
    * @param page            The Playwright Page instance.
    */
   public LinkServiceFluent(T uiServiceFluent, Storage storage,
                            LinkServiceCore<PwElement, PwBy> linkService, Page page) {
      super(uiServiceFluent, storage, linkService, page);
   }

}
