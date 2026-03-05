package io.cyborgcode.roa.ui.playwright.service.fluent;

import com.microsoft.playwright.Page;
import io.cyborgcode.roa.framework.storage.Storage;
import io.cyborgcode.roa.ui.components.modal.ModalServiceCore;
import io.cyborgcode.roa.ui.playwright.base.PwBy;
import io.cyborgcode.roa.ui.playwright.base.PwElement;
import io.cyborgcode.roa.ui.service.fluent.ModalServiceFluentCore;

/**
 * Playwright-specific fluent API for interacting with modal components.
 *
 * @param <T> The fluent UI service type for method chaining.
 * @author Cyborg Code Syndicate 💍👨💻
 */
public class ModalServiceFluent<T extends UiServiceFluent<?>>
      extends ModalServiceFluentCore<T, PwBy, Page, PwElement> {

   /**
    * Constructs an instance of {@code ModalServiceFluent}.
    *
    * @param uiServiceFluent The UI service fluent instance.
    * @param storage         The storage instance for persisting test values.
    * @param modalService    The modal service instance.
    * @param page            The Playwright Page instance.
    */
   public ModalServiceFluent(T uiServiceFluent, Storage storage,
                             ModalServiceCore<PwElement, PwBy> modalService, Page page) {
      super(uiServiceFluent, storage, modalService, page);
   }

}
