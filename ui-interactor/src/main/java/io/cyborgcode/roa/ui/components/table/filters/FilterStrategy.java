package io.cyborgcode.roa.ui.components.table.filters;

import io.cyborgcode.pandora.annotation.Pandora;
import io.cyborgcode.pandora.annotation.PandoraOptions;
import io.cyborgcode.pandora.model.CreationKind;
import io.cyborgcode.roa.ui.pandora.AvailableOptionsRules;

/**
 * Defines filtering strategies for table elements.
 *
 * <p>These strategies determine how table elements are filtered,
 * allowing selection and deselection actions.
 *
 * <p>Currently, there are no direct usages, but it is intended
 * to be used in conjunction with filtering functionalities
 * such as {@link CellFilterFunction}.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@Pandora(
      description = "Filtering strategy for table interactions (select-only/select/select-all/unselect/unselect-all).",
      tags = {"ui", "table", "filter-strategy"},
      creation = CreationKind.ENUM_CONSTANT
)
@PandoraOptions(
      exampleFilesPath = "docs/usage/roa/ui-usage.json",
      availableOptionsRule = AvailableOptionsRules.AvailableFilterStrategyOptions.class
)
public enum FilterStrategy {

   /**
    * Selects only the specified elements, deselecting others.
    */
   SELECT_ONLY,

   /**
    * Selects the specified elements without affecting others.
    */
   SELECT,

   /**
    * Selects all available elements.
    */
   SELECT_ALL,

   /**
    * Unselects the specified elements without affecting others.
    */
   UNSELECT,

   /**
    * Unselects all elements.
    */
   UNSELECT_ALL
}
