package io.cyborgcode.roa.ui.components.table.sort;

import io.cyborgcode.pandora.annotation.Pandora;
import io.cyborgcode.pandora.annotation.PandoraOptions;
import io.cyborgcode.pandora.model.CreationKind;
import io.cyborgcode.roa.ui.pandora.AvailableOptionsRules;

/**
 * Defines sorting strategies that can be applied to table columns.
 *
 * <p>This enum provides different sorting options for organizing table data.
 *
 * <p>This enum is primarily used in the {@code TableService} and {@code TableServiceImpl} classes
 * to control the sorting behavior of table columns.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@Pandora(
      description = "Sorting strategy for table columns (ascending/descending/none).",
      tags = {"ui", "table", "sort", "strategy"},
      creation = CreationKind.ENUM_CONSTANT
)
@PandoraOptions(
      exampleFilesPath = "docs/usage/roa/ui-usage.json",
      availableOptionsRule = AvailableOptionsRules.AvailableSortingStrategyOptions.class
)
public enum SortingStrategy {

   /**
    * Sorts the column in ascending order (e.g., A-Z, 0-9).
    */
   ASC,

   /**
    * Sorts the column in descending order (e.g., Z-A, 9-0).
    */
   DESC,

   /**
    * No sorting is applied to the column.
    */
   NO_SORT

}
