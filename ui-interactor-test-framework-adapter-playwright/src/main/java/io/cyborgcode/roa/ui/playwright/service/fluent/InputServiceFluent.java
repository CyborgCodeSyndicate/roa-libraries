package io.cyborgcode.roa.ui.playwright.service.fluent;

import com.microsoft.playwright.Page;
import io.cyborgcode.roa.framework.storage.Storage;
import io.cyborgcode.roa.ui.components.input.InputServiceCore;
import io.cyborgcode.roa.ui.playwright.base.PwBy;
import io.cyborgcode.roa.ui.playwright.base.PwElement;
import io.cyborgcode.roa.ui.service.fluent.InputServiceFluentCore;

/**
 * Playwright-specific fluent API for interacting with input components.
 *
 * @param <T> The fluent UI service type for method chaining.
 * @author Cyborg Code Syndicate 💍👨💻
 */
public class InputServiceFluent<T extends UiServiceFluent<?>>
      extends InputServiceFluentCore<T, PwBy, Page, PwElement> {

   /**
    * Constructs an instance of {@code InputServiceFluent}.
    *
    * @param uiServiceFluent The UI service fluent instance.
    * @param storage         The storage instance for persisting test values.
    * @param inputService    The input service instance.
    * @param page            The Playwright Page instance.
    */
   public InputServiceFluent(T uiServiceFluent, Storage storage,
                             InputServiceCore<PwElement, PwBy> inputService, Page page) {
      super(uiServiceFluent, storage, inputService, page);
   }

}
