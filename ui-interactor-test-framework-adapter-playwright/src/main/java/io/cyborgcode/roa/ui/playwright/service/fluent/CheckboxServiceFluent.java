package io.cyborgcode.roa.ui.playwright.service.fluent;

import com.microsoft.playwright.Page;
import io.cyborgcode.roa.framework.storage.Storage;
import io.cyborgcode.roa.ui.components.checkbox.CheckboxServiceCore;
import io.cyborgcode.roa.ui.playwright.base.PwBy;
import io.cyborgcode.roa.ui.playwright.base.PwElement;
import io.cyborgcode.roa.ui.service.fluent.CheckboxServiceFluentCore;

/**
 * Playwright-specific fluent API for interacting with checkbox components.
 *
 * @param <T> The fluent UI service type for method chaining.
 * @author Cyborg Code Syndicate 💍👨💻
 */
public class CheckboxServiceFluent<T extends UiServiceFluent<?>>
      extends CheckboxServiceFluentCore<T, PwBy, Page, PwElement> {

   /**
    * Constructs an instance of {@code CheckboxServiceFluent}.
    *
    * @param uiServiceFluent  The UI service fluent instance.
    * @param storage          The storage instance for persisting test values.
    * @param checkboxService  The checkbox service instance.
    * @param page             The Playwright Page instance.
    */
   public CheckboxServiceFluent(T uiServiceFluent, Storage storage,
                                CheckboxServiceCore<PwElement, PwBy> checkboxService, Page page) {
      super(uiServiceFluent, storage, checkboxService, page);
   }

}
