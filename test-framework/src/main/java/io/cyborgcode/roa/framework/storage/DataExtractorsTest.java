package io.cyborgcode.roa.framework.storage;


import io.cyborgcode.pandora.annotation.Pandora;
import io.cyborgcode.pandora.annotation.PandoraOptions;
import io.cyborgcode.pandora.model.CreationKind;
import java.util.Map;

/**
 * Provides utility methods for creating {@link DataExtractor} instances that retrieve
 * static test data from the framework's storage.
 *
 * <p>This class focuses on accessing and returning values from the static test data map,
 * allowing tests to dynamically fetch pre-defined data by a string key.
 *
 * <p>Generic Type {@code T}: the type of the data retrieved from the static test data map.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@Pandora(
      description = "Factory utility for creating DataExtractor instances that "
            + " values from the static test data map stored in quest storage.",
      tags = {"framework", "storage", "test-data"},
      creation = CreationKind.STATIC_FACTORY
)
@PandoraOptions(
      exampleFilesPath = "docs/usage/roa/general-usage.json",
      meta = {
         @PandoraOptions.Meta(key = "type", value = "data-extractors-test"),
         @PandoraOptions.Meta(key = "scope", value = "storage")
      }
)
public class DataExtractorsTest {

   private DataExtractorsTest() {
   }

   /**
    * Creates a {@link DataExtractor} for retrieving a specific value from the static test data map.
    *
    * <p>The returned extractor locates the static test data map within the storage and fetches
    * the object associated with the provided key.
    *
    * @param key the string key identifying the data in the static test data map
    * @param <T> the type of the data to be extracted
    * @return a {@code DataExtractor} that retrieves the specified value from static test data
    */
   @Pandora(
         description = "Creates a DataExtractor that reads the STATIC_DATA map "
               + "from storage and returns the value for the given string key."
   )
   @PandoraOptions(
         exampleFilesPath = "docs/usage/roa/general-usage.json"
   )
   public static <T> DataExtractor<T> staticTestData(
         @Pandora(
               description = "Static test-data key to read from StorageKeysTest.STATIC_DATA. "
                     + "Must be a string constant defined by a project class implementing StaticDataProvider "
                     + "(e.g., StaticData.ORDER)."
         ) String key) {
      return new DataExtractorImpl<>(
            StorageKeysTest.STATIC_DATA,
            raw -> {
               Map<String, Object> staticTestData = (Map<String, Object>) raw;
               return (T) staticTestData.get(key);
            }
      );
   }

}
