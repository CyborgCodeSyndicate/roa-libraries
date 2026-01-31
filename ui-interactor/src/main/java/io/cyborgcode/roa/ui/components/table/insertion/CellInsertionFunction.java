package io.cyborgcode.roa.ui.components.table.insertion;

import io.cyborgcode.pandora.annotation.Pandora;
import io.cyborgcode.pandora.annotation.PandoraOptions;
import io.cyborgcode.pandora.model.CreationKind;
import io.cyborgcode.roa.ui.selenium.smart.SmartWebElement;
import java.util.function.BiConsumer;

/**
 * Functional interface for inserting values into a table cell.
 *
 * <p>This interface provides a contract for custom cell insertion logic,
 * enabling dynamic handling of table cell modifications.
 *
 * <p>It is used in {@code TableImpl}, {@code CellLocator}, {@code TableEntry}, and {@code CustomCellInsertion}.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@FunctionalInterface
@Pandora(
      description = "Functional interface contract for inserting values into a table cell.",
      tags = {"ui", "table", "cell-insertion"},
      creation = CreationKind.AUTO
)
@PandoraOptions(
      exampleFilesPath = "docs/usage/roa/ui-usage.json",
      meta = {
         @PandoraOptions.Meta(key = "type", value = "cell-insertion-function")
      }
)
public interface CellInsertionFunction extends BiConsumer<SmartWebElement, String[]> {

   /**
    * Performs the insertion of values into the specified table cell.
    *
    * @param cellElement The {@link SmartWebElement} representing the target cell.
    * @param values      The values to be inserted into the cell.
    */
   @Pandora(
         description = "Performs the insertion of values into the specified table cell.",
         tags = {"table", "cell-insertion"}
   )
   void cellInsertionFunction(SmartWebElement cellElement, String... values);

   /**
    * Default implementation of {@link BiConsumer#accept}, forwarding execution
    * to {@link #cellInsertionFunction(SmartWebElement, String...)}.
    *
    * @param smartWebElement The target table cell.
    * @param objects         The values to insert.
    */
   @Override
   @Pandora(
         description = "Default BiConsumer accept; delegates to cellInsertionFunction.",
         tags = {"table", "cell-insertion"}
   )
   default void accept(SmartWebElement smartWebElement, String[] objects) {
      cellInsertionFunction(smartWebElement, objects);
   }

}
