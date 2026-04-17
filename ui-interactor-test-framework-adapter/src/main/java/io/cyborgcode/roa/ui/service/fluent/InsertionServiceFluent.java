package io.cyborgcode.roa.ui.service.fluent;

import io.cyborgcode.pandora.annotation.AiCompass;
import io.cyborgcode.pandora.annotation.AiCompassOptions;
import io.cyborgcode.pandora.model.CreationKind;
import io.cyborgcode.roa.framework.storage.Storage;
import io.cyborgcode.roa.ui.insertion.InsertionService;
import io.qameta.allure.Allure;

/**
 * A fluent service for handling data insertion operations.
 *
 * <p>This class provides a streamlined interface to facilitate data insertion while maintaining
 * fluent interactions with the UI testing framework.
 *
 * <p>The generic type {@code T} represents the UI service fluent implementation that extends {@link UiServiceFluent},
 * enabling method chaining.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@AiCompass(
      description = "Fluent UI service for performing data insertions.",
      tags = {"ui", "fluent", "insertion"},
      creation = CreationKind.PROVIDED
)
@AiCompassOptions(
      exampleFilesPath = "docs/usage/roa/ui-usage.json",
      meta = {
         @AiCompassOptions.Meta(key = "type", value = "fluent-service")
      }
)
public class InsertionServiceFluent<T extends UiServiceFluent<?>> {

   private final InsertionService insertionService;
   private final T uiServiceFluent;
   private final Storage storage;

   /**
    * Constructs an {@code InsertionServiceFluent} instance.
    *
    * @param insertionService The service responsible for handling insertion operations.
    * @param uiServiceFluent  The UI service fluent instance to maintain fluent method chaining.
    * @param storage          The storage instance used to maintain test data.
    */
   public InsertionServiceFluent(final InsertionService insertionService, final T uiServiceFluent,
                                 final Storage storage) {
      this.insertionService = insertionService;
      this.uiServiceFluent = uiServiceFluent;
      this.storage = storage;
   }

   /**
    * Inserts the specified data using the {@link InsertionService}.
    *
    * @param data The data to be inserted.
    * @return The current {@link UiServiceFluent} instance for method chaining.
    */
   @AiCompass(
         description = "Insert the provided data using the InsertionService and continue the fluent flow.",
         tags = {"ui", "insertion"}
   )
   @AiCompassOptions(
         exampleFilesPath = "docs/usage/roa/ui-usage.json"
   )
   public T insertData(
         @AiCompass(
               description = "Data object to insert."
         ) Object data) {
      Allure.step("[UI - Insertion] Insert data: " + data);
      insertionService.insertData(data);
      return uiServiceFluent;
   }
}
