package io.cyborgcode.roa.ui.playwright.service.fluent;

import com.microsoft.playwright.Page;
import io.cyborgcode.roa.framework.storage.Storage;
import io.cyborgcode.roa.ui.components.radio.RadioServiceCore;
import io.cyborgcode.roa.ui.playwright.base.PwBy;
import io.cyborgcode.roa.ui.playwright.base.PwElement;
import io.cyborgcode.roa.ui.service.fluent.RadioServiceFluentCore;

/**
 * Playwright-specific fluent API for interacting with radio button components.
 *
 * @param <T> The fluent UI service type for method chaining.
 * @author Cyborg Code Syndicate 💍👨💻
 */
public class RadioServiceFluent<T extends UiServiceFluent<?>>
      extends RadioServiceFluentCore<T, PwBy, Page, PwElement> {

   /**
    * Constructs an instance of {@code RadioServiceFluent}.
    *
    * @param uiServiceFluent The UI service fluent instance.
    * @param storage         The storage instance for persisting test values.
    * @param radioService    The radio service instance.
    * @param page            The Playwright Page instance.
    */
   public RadioServiceFluent(T uiServiceFluent, Storage storage,
                             RadioServiceCore<PwElement, PwBy> radioService, Page page) {
      super(uiServiceFluent, storage, radioService, page);
   }

}
