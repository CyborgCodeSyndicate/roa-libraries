package io.cyborgcode.roa.api.storage;

import io.cyborgcode.pandora.annotation.Pandora;
import io.cyborgcode.pandora.annotation.PandoraOptions;
import io.cyborgcode.pandora.model.CreationKind;
import io.cyborgcode.roa.framework.storage.DataExtractor;
import io.cyborgcode.roa.framework.storage.DataExtractorImpl;
import io.restassured.response.Response;

/**
 * Provides utility methods for extracting data from API responses.
 *
 * <p>This class defines reusable {@link DataExtractor} implementations for extracting
 * response body values and HTTP status codes.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@Pandora(
      description = "Utility helpers for creating DataExtractor instances targeting API responses "
            + "(JSON fields and HTTP status codes).",
      tags = {"api", "test-data"},
      creation = CreationKind.PROVIDED
)
@PandoraOptions(
      exampleFilesPath = "ai/roa/api-usage.json",
      meta = {
         @PandoraOptions.Meta(key = "type", value = "api-data-extractors")
      }
)
public class DataExtractorsApi {

   private DataExtractorsApi() {
   }

   /**
    * Creates a {@link DataExtractor} to extract a value from a response body using a JSON path.
    *
    * @param key      The storage key associated with the extraction.
    * @param jsonPath The JSON path expression to locate the value in the response body.
    * @param <T>      The type of the extracted data.
    * @return A {@code DataExtractor} configured for response body extraction.
    */
   @Pandora(
         description = "Create a DataExtractor that reads a JSON field from the API response body "
               + "using the given JSON path and stores it under the provided storage key."
   )
   public static <T> DataExtractor<T> responseBodyExtraction(
         @Pandora(description = "Enum key used as the storage identifier for the extracted value.")
         Enum<?> key,
         @Pandora(description = "JSON path expression used to locate the field in the response body.")
         String jsonPath) {
      return new DataExtractorImpl<>(
            StorageKeysApi.API,
            key,
            raw -> {
               Response response = (Response) raw;
               return response.body().jsonPath().get(jsonPath);
            }
      );
   }

   /**
    * Creates a {@link DataExtractor} to extract the HTTP status code from a response.
    *
    * @param key The storage key associated with the extraction.
    * @return A {@code DataExtractor} configured for status code extraction.
    */
   @Pandora(
         description = "Create a DataExtractor that reads the HTTP status code from the API response "
               + "and stores it under the provided storage key."
   )
   public static DataExtractor<Integer> statusExtraction(
         @Pandora(description = "Enum key used as the storage identifier for the extracted status code.")
         Enum<?> key) {
      return new DataExtractorImpl<>(
            StorageKeysApi.API,
            key,
            raw -> {
               Response response = (Response) raw;
               return response.statusCode();
            }
      );
   }

}
