package io.cyborgcode.roa.ui.service.fluent;

import io.cyborgcode.roa.framework.storage.Storage;
import io.cyborgcode.roa.ui.insertion.InsertionService;
import io.qameta.allure.Allure;

/**
 * A fluent service for handling data insertion operations.
 *
 * <p>This class provides a streamlined interface to facilitate data insertion while maintaining
 * fluent interactions with the UI testing framework.
 *
 * @param <T> The type of {@link UiServiceFluentCore} for chaining fluent methods.
 * @author Cyborg Code Syndicate 💍👨💻
 */
public class InsertionServiceFluentCore<T extends UiServiceFluentCore<?, ?, ?, ?>> {

   private final InsertionService insertionService;
   private final T uiServiceFluent;
   private final Storage storage;

   /**
    * Constructs an {@code InsertionServiceFluentCore} instance.
    *
    * @param insertionService The service responsible for handling insertion operations.
    * @param uiServiceFluent  The UI service fluent instance to maintain fluent method chaining.
    * @param storage          The storage instance used to maintain test data.
    */
   public InsertionServiceFluentCore(final InsertionService insertionService, final T uiServiceFluent,
                                     final Storage storage) {
      this.insertionService = insertionService;
      this.uiServiceFluent = uiServiceFluent;
      this.storage = storage;
   }

   /**
    * Inserts the specified data using the {@link InsertionService}.
    *
    * @param data The data to be inserted.
    * @return The current {@link UiServiceFluentCore} instance for method chaining.
    */
   public T insertData(Object data) {
      Allure.step("[UI - Insertion] Insert data: " + data);
      insertionService.insertData(data);
      return uiServiceFluent;
   }
}
