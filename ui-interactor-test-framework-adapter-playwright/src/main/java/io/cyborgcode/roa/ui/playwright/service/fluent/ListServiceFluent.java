package io.cyborgcode.roa.ui.playwright.service.fluent;

import com.microsoft.playwright.Page;
import io.cyborgcode.roa.framework.storage.Storage;
import io.cyborgcode.roa.ui.components.list.ItemListServiceCore;
import io.cyborgcode.roa.ui.playwright.base.PwBy;
import io.cyborgcode.roa.ui.playwright.base.PwElement;
import io.cyborgcode.roa.ui.service.fluent.ListServiceFluentCore;

/**
 * Playwright-specific fluent API for interacting with list components.
 *
 * @param <T> The fluent UI service type for method chaining.
 * @author Cyborg Code Syndicate 💍👨💻
 */
public class ListServiceFluent<T extends UiServiceFluent<?>>
      extends ListServiceFluentCore<T, PwBy, Page, PwElement> {

   /**
    * Constructs an instance of {@code ListServiceFluent}.
    *
    * @param uiServiceFluent  The UI service fluent instance.
    * @param storage          The storage instance for persisting test values.
    * @param itemListService  The item list service instance.
    * @param page             The Playwright Page instance.
    */
   public ListServiceFluent(T uiServiceFluent, Storage storage,
                            ItemListServiceCore<PwElement, PwBy> itemListService, Page page) {
      super(uiServiceFluent, storage, itemListService, page);
   }

}
