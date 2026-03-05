package io.cyborgcode.roa.ui.playwright.service.fluent;

import com.microsoft.playwright.Page;
import io.cyborgcode.roa.framework.storage.Storage;
import io.cyborgcode.roa.ui.components.button.ButtonServiceCore;
import io.cyborgcode.roa.ui.playwright.base.PwBy;
import io.cyborgcode.roa.ui.playwright.base.PwElement;
import io.cyborgcode.roa.ui.service.fluent.ButtonServiceFluentCore;

/**
 * Playwright-specific fluent API for interacting with button components.
 *
 * @param <T> The fluent UI service type for method chaining.
 * @author Cyborg Code Syndicate 💍👨💻
 */
public class ButtonServiceFluent<T extends UiServiceFluent<?>>
      extends ButtonServiceFluentCore<T, PwBy, Page, PwElement> {

   /**
    * Constructs an instance of {@code ButtonServiceFluent}.
    *
    * @param uiServiceFluent The UI service fluent instance.
    * @param storage         The storage instance for persisting test values.
    * @param buttonService   The button service instance.
    * @param page            The Playwright Page instance.
    */
   public ButtonServiceFluent(T uiServiceFluent, Storage storage,
                              ButtonServiceCore<PwElement, PwBy> buttonService, Page page) {
      super(uiServiceFluent, storage, buttonService, page);
   }

}
