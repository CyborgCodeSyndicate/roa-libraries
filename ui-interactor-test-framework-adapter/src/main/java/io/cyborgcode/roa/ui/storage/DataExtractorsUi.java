package io.cyborgcode.roa.ui.storage;

import io.cyborgcode.pandora.annotation.Pandora;
import io.cyborgcode.pandora.annotation.PandoraOptions;
import io.cyborgcode.pandora.model.CreationKind;
import com.jayway.jsonpath.JsonPath;
import io.cyborgcode.roa.ui.pandora.AvailableOptionsUiRules;
import io.cyborgcode.roa.framework.storage.DataExtractor;
import io.cyborgcode.roa.framework.storage.DataExtractorImpl;
import io.cyborgcode.roa.ui.components.interceptor.ApiResponse;
import io.cyborgcode.roa.ui.util.table.TableReflectionUtil;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Utility class providing data extraction mechanisms for UI-related data storage and retrieval.
 *
 * <p>This class contains static methods for extracting data from API responses and table elements.
 * It provides functionalities to:
 * <ul>
 *   <li>Extract JSON data from stored API responses.</li>
 *   <li>Retrieve specific table rows based on search criteria or index.</li>
 *   <li>Process and filter data for validation and assertions.</li>
 * </ul>
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@Pandora(
      description = "Utilities to build DataExtractor instances for UI flows (API response JSON paths and "
            + "table row retrieval).",
      tags = {"ui", "data", "extractor", "utility"},
      creation = CreationKind.PROVIDED
)
@PandoraOptions(
      exampleFilesPath = "docs/usage/roa/ui-usage.json",
      meta = {
         @PandoraOptions.Meta(key = "type", value = "ui-data-extractors"),
         @PandoraOptions.Meta(key = "scope", value = "type")
      }
)
public class DataExtractorsUi {

   private DataExtractorsUi() {
   }

   /**
    * Creates a {@link DataExtractor} to retrieve a value from a stored API response body using a JSONPath expression.
    *
    * @param responsePrefix The prefix of the API response URL used to filter relevant responses.
    * @param jsonPath       The JSONPath expression used to extract data from the response body.
    * @param <T>            The expected return type of the extracted data.
    * @return A {@link DataExtractor} that retrieves the specified value from the API response.
    */
   @Pandora(
         description = "Builds a DataExtractor that reads a value from the most recent matching API response "
               + "using a JSONPath."
   )
   @PandoraOptions(
         exampleFilesPath = "docs/usage/roa/ui-usage.json"
   )
   public static <T> DataExtractor<T> responseBodyExtraction(
         @Pandora(
               description = "Request/response identifier used to filter stored responses (typically mapped "
                     + "from a DataIntercept target)."
         ) String responsePrefix,
         @Pandora(
               description = "JSONPath expression used to extract a value from the selected response body."
         ) String jsonPath) {
      return responseBodyExtraction(responsePrefix, jsonPath, 1);
   }

   /**
    * Creates a {@link DataExtractor} to retrieve a value from a stored API response body using a JSONPath expression.
    *
    * @param responsePrefix The prefix of the API response URL used to filter relevant responses.
    * @param jsonPath       The JSONPath expression used to extract data from the response body.
    * @param index          The index (from the most recent) of the filtered API responses to extract data from.
    * @param <T>            The expected return type of the extracted data.
    * @return A {@link DataExtractor} that retrieves the specified value from the API response.
    * @throws IllegalArgumentException If the provided index is out of range.
    */
   @Pandora(
         description = "Builds a DataExtractor that reads a value from the Nth most recent matching API "
               + "response using a JSONPath."
   )
   @PandoraOptions(
         exampleFilesPath = "docs/usage/roa/ui-usage.json"
   )
   public static <T> DataExtractor<T> responseBodyExtraction(
         @Pandora(
               description = "Request/response identifier used to filter stored responses (typically mapped "
                     + "from a DataIntercept target)."
         ) String responsePrefix,
         @Pandora(
               description = "JSONPath expression used to extract a value from the selected response body."
         ) String jsonPath,
         @Pandora(
               description = "Nth most recent match to use (1 = latest, 2 = previous, ...)."
         ) int index) {
      return new DataExtractorImpl<>(
            StorageKeysUi.UI,
            StorageKeysUi.RESPONSES,
            raw -> {
               List<ApiResponse> responses = (List<ApiResponse>) raw;

               List<ApiResponse> filteredResponses = responses.stream()
                     .filter(
                           response -> response.getUrl().startsWith(responsePrefix))
                     .toList();

               int adjustedIndex = filteredResponses.size() - index;
               if (adjustedIndex < 0 || adjustedIndex >= filteredResponses.size()) {
                  throw new IllegalArgumentException("Invalid index for response list.");
               }

               ApiResponse selectedResponse = filteredResponses.get(adjustedIndex);

               return JsonPath.read(selectedResponse.getBody(), jsonPath);
            }
      );
   }


   /**
    * Creates a {@link DataExtractor} to retrieve a specific table row from storage based on search criteria.
    *
    * <p>This method searches stored table data for a row containing all specified indicator values.
    * It compares row values case-insensitively after trimming whitespace.
    *
    * @param key        The key used to retrieve the stored table data.
    * @param indicators The values used to search for a matching row.
    * @param <T>        The expected return type of the extracted table row.
    * @return A {@link DataExtractor} that retrieves the matching row from the table.
    */
   @Pandora(
         description = "Builds a DataExtractor that finds the first table row whose values include all given "
               + "indicators (case-insensitive)."
   )
   @PandoraOptions(
         exampleFilesPath = "docs/usage/roa/ui-usage.json"
   )
   public static <T> DataExtractor<T> tableRowExtractor(
         @Pandora(
               description = "TableElement key used to locate the stored table rows. Must be an enum constant name "
                     + "from a project enum implementing TableElement (e.g., \"ALL_TRANSACTIONS\"."
         )
         @PandoraOptions(
               availableOptionsRule = AvailableOptionsUiRules.AvailableTableElementOptions.class
         ) Enum<?> key,
         @Pandora(
               description = "One or more row indicators used for case-insensitive contains matching."
         ) String... indicators) {
      return new DataExtractorImpl<>(
            StorageKeysUi.UI,
            key,
            raw -> {
               List<T> rows = new ArrayList<>();
               if (List.class.isAssignableFrom(raw.getClass())) {
                  rows = (List<T>) raw;
               } else {
                  rows.add((T) raw);
               }

               return rows.stream().filter(row -> {
                  List<String> rowValues = TableReflectionUtil.extractTextsFromRow(row);
                  List<String> modifiedRowValues = TableReflectionUtil.lowerCaseAndTrim(rowValues);
                  List<String> modifiedIndicators = TableReflectionUtil.lowerCaseAndTrim(List.of(indicators));
                  return new HashSet<>(modifiedRowValues).containsAll(modifiedIndicators);
               }).findFirst().orElse(null);
            }
      );
   }

   /**
    * Creates a {@link DataExtractor} to retrieve a specific table row from storage based on its index.
    *
    * @param key   The key used to retrieve the stored table data.
    * @param index The zero-based index of the row to retrieve.
    * @param <T>   The expected return type of the extracted table row.
    * @return A {@link DataExtractor} that retrieves the row at the specified index.
    * @throws IndexOutOfBoundsException If the index is out of range.
    */
   @Pandora(
         description = "Builds a DataExtractor that returns the table row at the given index from storage."
   )
   @PandoraOptions(
         exampleFilesPath = "docs/usage/roa/ui-usage.json"
   )
   public static <T> DataExtractor<T> tableRowExtractor(
         @Pandora(
               description = "TableElement key used to locate the stored table rows. Must be an enum constant name "
                     + "from a project enum implementing TableElement (e.g., \"ALL_TRANSACTIONS\"."
         )
         @PandoraOptions(
               availableOptionsRule = AvailableOptionsUiRules.AvailableTableElementOptions.class
         ) Enum<?> key,
         @Pandora(
               description = "Zero-based row index to return from the stored table rows."
         ) int index) {
      return new DataExtractorImpl<>(
            StorageKeysUi.UI,
            key,
            raw -> {
               List<T> rows = new ArrayList<>();
               if (List.class.isAssignableFrom(raw.getClass())) {
                  rows = (List<T>) raw;
               } else {
                  rows.add((T) raw);
               }

               return rows.get(index);
            }
      );
   }

}
