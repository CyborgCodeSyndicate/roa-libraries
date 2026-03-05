package io.cyborgcode.roa.ui.playwright.service.fluent;

import io.cyborgcode.roa.framework.storage.Storage;
import io.cyborgcode.roa.ui.insertion.InsertionService;
import io.cyborgcode.roa.ui.service.fluent.InsertionServiceFluentCore;

public class InsertionServiceFluent<T extends UiServiceFluent<?>> extends InsertionServiceFluentCore<T> {
   /**
    * Constructs an {@code InsertionServiceFluentCore} instance.
    *
    * @param insertionService The service responsible for handling insertion operations.
    * @param uiServiceFluent  The UI service fluent instance to maintain fluent method chaining.
    * @param storage          The storage instance used to maintain test data.
    */
   public InsertionServiceFluent(InsertionService insertionService, T uiServiceFluent, Storage storage) {
      super(insertionService, uiServiceFluent, storage);
   }
}
