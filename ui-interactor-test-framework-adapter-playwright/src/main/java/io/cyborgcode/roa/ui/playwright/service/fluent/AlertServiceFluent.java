package io.cyborgcode.roa.ui.playwright.service.fluent;

import com.microsoft.playwright.Page;
import io.cyborgcode.roa.framework.storage.Storage;
import io.cyborgcode.roa.ui.components.alert.AlertServiceCore;
import io.cyborgcode.roa.ui.playwright.base.PwBy;
import io.cyborgcode.roa.ui.playwright.base.PwElement;
import io.cyborgcode.roa.ui.service.fluent.AlertServiceFluentCore;

/**
 * Playwright-specific fluent API for interacting with alert components.
 *
 * @param <T> The fluent UI service type for method chaining.
 * @author Cyborg Code Syndicate 💍👨💻
 */
public class AlertServiceFluent<T extends UiServiceFluent<?>>
      extends AlertServiceFluentCore<T, PwBy, Page, PwElement> {

   /**
    * Constructs an instance of {@code AlertServiceFluent}.
    *
    * @param uiServiceFluent The UI service fluent instance.
    * @param storage         The storage instance for persisting test values.
    * @param alertService    The alert service instance.
    * @param page            The Playwright Page instance.
    */
   public AlertServiceFluent(T uiServiceFluent, Storage storage,
                             AlertServiceCore<PwElement, PwBy> alertService, Page page) {
      super(uiServiceFluent, storage, alertService, page);
   }

}
