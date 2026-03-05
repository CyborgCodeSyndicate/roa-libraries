package io.cyborgcode.roa.ui.playwright.service.fluent;

import com.microsoft.playwright.Page;
import io.cyborgcode.roa.framework.storage.Storage;
import io.cyborgcode.roa.ui.components.select.SelectServiceCore;
import io.cyborgcode.roa.ui.playwright.base.PwBy;
import io.cyborgcode.roa.ui.playwright.base.PwElement;
import io.cyborgcode.roa.ui.service.fluent.SelectServiceFluentCore;

/**
 * Playwright-specific fluent API for interacting with select/dropdown components.
 *
 * @param <T> The fluent UI service type for method chaining.
 * @author Cyborg Code Syndicate 💍👨💻
 */
public class SelectServiceFluent<T extends UiServiceFluent<?>>
      extends SelectServiceFluentCore<T, PwBy, Page, PwElement> {

   /**
    * Constructs an instance of {@code SelectServiceFluent}.
    *
    * @param uiServiceFluent The UI service fluent instance.
    * @param storage         The storage instance for persisting test values.
    * @param selectService   The select service instance.
    * @param page            The Playwright Page instance.
    */
   public SelectServiceFluent(T uiServiceFluent, Storage storage,
                              SelectServiceCore<PwElement, PwBy> selectService, Page page) {
      super(uiServiceFluent, storage, selectService, page);
   }

}
