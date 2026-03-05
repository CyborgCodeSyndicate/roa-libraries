package io.cyborgcode.roa.ui.playwright.service.fluent;

import com.microsoft.playwright.Page;
import io.cyborgcode.roa.framework.storage.Storage;
import io.cyborgcode.roa.ui.components.accordion.AccordionServiceCore;
import io.cyborgcode.roa.ui.playwright.base.PwBy;
import io.cyborgcode.roa.ui.playwright.base.PwElement;
import io.cyborgcode.roa.ui.service.fluent.AccordionServiceFluentCore;

/**
 * Playwright-specific fluent API for interacting with accordion components.
 *
 * @param <T> The fluent UI service type for method chaining.
 * @author Cyborg Code Syndicate 💍👨💻
 */
public class AccordionServiceFluent<T extends UiServiceFluent<?>>
      extends AccordionServiceFluentCore<T, PwBy, Page, PwElement> {

   /**
    * Constructs an instance of {@code AccordionServiceFluent}.
    *
    * @param uiServiceFluent  The UI service fluent instance.
    * @param storage          The storage instance for persisting test values.
    * @param accordionService The accordion service instance.
    * @param page             The Playwright Page instance.
    */
   public AccordionServiceFluent(T uiServiceFluent, Storage storage,
                                 AccordionServiceCore<PwElement, PwBy> accordionService, Page page) {
      super(uiServiceFluent, storage, accordionService, page);
   }

}
