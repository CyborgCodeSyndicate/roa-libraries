package io.cyborgcode.roa.ui.playwright.service.fluent;

import com.microsoft.playwright.Page;
import io.cyborgcode.roa.framework.storage.Storage;
import io.cyborgcode.roa.ui.components.loader.LoaderServiceCore;
import io.cyborgcode.roa.ui.playwright.base.PwBy;
import io.cyborgcode.roa.ui.playwright.base.PwElement;
import io.cyborgcode.roa.ui.service.fluent.LoaderServiceFluentCore;

/**
 * Playwright-specific fluent API for interacting with loader components.
 *
 * @param <T> The fluent UI service type for method chaining.
 * @author Cyborg Code Syndicate 💍👨💻
 */
public class LoaderServiceFluent<T extends UiServiceFluent<?>>
      extends LoaderServiceFluentCore<T, PwBy, Page, PwElement> {

   /**
    * Constructs an instance of {@code LoaderServiceFluent}.
    *
    * @param uiServiceFluent The UI service fluent instance.
    * @param storage         The storage instance for persisting test values.
    * @param loaderService   The loader service instance.
    * @param page            The Playwright Page instance.
    */
   public LoaderServiceFluent(T uiServiceFluent, Storage storage,
                              LoaderServiceCore<PwElement, PwBy> loaderService, Page page) {
      super(uiServiceFluent, storage, loaderService, page);
   }

}
