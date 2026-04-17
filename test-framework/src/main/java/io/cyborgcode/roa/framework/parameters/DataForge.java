package io.cyborgcode.roa.framework.parameters;

import io.cyborgcode.pandora.annotation.AiCompass;
import io.cyborgcode.pandora.annotation.AiCompassOptions;
import io.cyborgcode.pandora.model.CreationKind;

/**
 * Defines a contract for dynamically generating test data within the framework.
 *
 * <p>Implementations of this interface serve as structured data providers, ensuring
 * consistent and reusable test data generation.
 *
 * <p>The data creation process is managed through a {@link Late} instance,
 * allowing deferred initialization until the data is explicitly needed.
 * Additionally, implementations provide an enumeration reference
 * that identifies the specific data model.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@AiCompass(
      description = "Contract for lazily generating test data models. Implementations return a Late creator "
            + "and expose an enum constant that uniquely identifies the model.",
      tags = {"framework", "test-data"},
      creation = CreationKind.ENUM_CONSTANT
)
@AiCompassOptions(
      exampleFilesPath = "docs/usage/roa/general-usage.json",
      meta = {
         @AiCompassOptions.Meta(key = "type", value = "data-forge")
      }
)
public interface DataForge<T extends Enum<T>> {

   /**
    * Provides a deferred test data creation mechanism.
    *
    * <p>Implementations return a {@link Late} instance that generates the
    * test data object when required. This allows for on-demand data creation
    * rather than instantiating all test data at the start.
    *
    * @return A {@link Late} instance responsible for creating test data objects.
    */
   @AiCompass(
         description = "Returns the deferred creator (Late) that materializes the test data object on demand."
   )
   @AiCompassOptions(
         exampleFilesPath = "docs/usage/roa/general-usage.json"
   )
   Late<Object> dataCreator();

   /**
    * Returns the enumeration instance associated with the test data model.
    *
    * <p>This method ensures that test data can be uniquely identified and retrieved
    * in a structured manner using its corresponding enum representation.
    *
    * @return An {@link Enum} instance representing the test data definition.
    */
   @AiCompass(
         description = "Returns the deferred creator (Late) that materializes the test data object on demand."
   )
   @AiCompassOptions(
         exampleFilesPath = "docs/usage/roa/general-usage.json"
   )
   T enumImpl();

}
