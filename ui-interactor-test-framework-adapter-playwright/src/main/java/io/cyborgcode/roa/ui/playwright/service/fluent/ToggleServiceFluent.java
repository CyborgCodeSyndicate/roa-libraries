package io.cyborgcode.roa.ui.playwright.service.fluent;

import com.microsoft.playwright.Page;
import io.cyborgcode.roa.framework.storage.Storage;
import io.cyborgcode.roa.ui.components.toggle.ToggleServiceCore;
import io.cyborgcode.roa.ui.playwright.base.PwBy;
import io.cyborgcode.roa.ui.playwright.base.PwElement;
import io.cyborgcode.roa.ui.service.fluent.ToggleServiceFluentCore;

/**
 * Playwright-specific fluent API for interacting with toggle components.
 *
 * @param <T> The fluent UI service type for method chaining.
 * @author Cyborg Code Syndicate 💍👨💻
 */
public class ToggleServiceFluent<T extends UiServiceFluent<?>>
      extends ToggleServiceFluentCore<T, PwBy, Page, PwElement> {

   /**
    * Constructs an instance of {@code ToggleServiceFluent}.
    *
    * @param uiServiceFluent The UI service fluent instance.
    * @param storage         The storage instance for persisting test values.
    * @param toggleService   The toggle service instance.
    * @param page            The Playwright Page instance.
    */
   public ToggleServiceFluent(T uiServiceFluent, Storage storage,
                              ToggleServiceCore<PwElement, PwBy> toggleService, Page page) {
      super(uiServiceFluent, storage, toggleService, page);
   }

}
