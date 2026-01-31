package io.cyborgcode.roa.ui.service.tables;

import io.cyborgcode.pandora.annotation.Pandora;
import io.cyborgcode.pandora.annotation.PandoraOptions;
import io.cyborgcode.pandora.model.CreationKind;
import io.cyborgcode.roa.ui.components.table.base.TableComponentType;
import io.cyborgcode.roa.ui.pandora.AvailableOptionsUiRules;
import io.cyborgcode.roa.ui.selenium.smart.SmartWebDriver;
import java.util.function.Consumer;

/**
 * Represents a table element that can be interacted with in the UI.
 *
 * <p>This interface provides methods for retrieving table metadata, such as
 * its type, row representation class, and lifecycle hooks (before and after actions).
 *
 * <p>Implementations of this interface are used to define specific tables
 * and their associated behaviors within the test automation framework.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@Pandora(
      description = "UI table element descriptor: type, row model class, and lifecycle hooks (before/after).",
      tags = {"ui", "table", "table-element"},
      creation = CreationKind.ENUM_CONSTANT
)
@PandoraOptions(
      availableOptionsRule = AvailableOptionsUiRules.AvailableTableUiElements.class,
      exampleFilesPath = "docs/usage/roa/ui-usage.json",
      meta = {
         @PandoraOptions.Meta(key = "type", value = "table-element")
      }
)
public interface TableElement<K extends Enum<K>> {

   /**
    * Retrieves the table type for the implementing table.
    *
    * <p>By default, this method returns {@link DefaultTableTypes#DEFAULT}.
    * Implementations may override this method to provide a custom table type.
    *
    * @param <T> The type of {@link TableComponentType}.
    * @return The table type associated with this table element.
    */
   @SuppressWarnings("unchecked")
   @Pandora(
         description = "Retrieve the table component type used to operate on this table.",
         tags = {"ui", "table"}
   )
   @PandoraOptions(
         exampleFilesPath = "docs/usage/roa/ui-usage.json"
   )
   default <T extends TableComponentType> T tableType() {
      return (T) DefaultTableTypes.DEFAULT;
   }

   /**
    * Retrieves the class representing the rows of the table.
    *
    * @param <T> The type of the row representation class.
    * @return The {@link Class} representing the table rows.
    */
   @Pandora(
         description = "Get the class representing the row model for this table.",
         tags = {"ui", "table"}
   )
   @PandoraOptions(
         exampleFilesPath = "docs/usage/roa/ui-usage.json"
   )
   <T> Class<T> rowsRepresentationClass();

   /**
    * Retrieves the enumeration instance representing this table element.
    *
    * @return An {@link Enum} instance associated with this table element.
    */
   @Pandora(
         description = "Get the enum constant representing this table element.",
         tags = {"ui", "table"}
   )
   @PandoraOptions(
         exampleFilesPath = "docs/usage/roa/ui-usage.json"
   )
   K enumImpl();

   /**
    * Provides a before-action hook that is executed before interacting with the table.
    *
    * <p>This method allows implementations to define preconditions,
    * such as waiting for the table to load.
    *
    * @return A {@link Consumer} accepting {@link SmartWebDriver} for executing preconditions.
    */
   @Pandora(
         description = "Before-action hook executed prior to interacting with the table.",
         tags = {"ui", "table"}
   )
   @PandoraOptions(
         exampleFilesPath = "docs/usage/roa/ui-usage.json"
   )
   default Consumer<SmartWebDriver> before() {
      return smartWebDriver -> {
      };
   }

   /**
    * Provides an after-action hook that is executed after interacting with the table.
    *
    * <p>This method allows implementations to define post-interaction actions,
    * such as logging interactions or refreshing the UI state.
    *
    * @return A {@link Consumer} accepting {@link SmartWebDriver} for executing postconditions.
    */
   @Pandora(
         description = "After-action hook executed after interacting with the table.",
         tags = {"ui", "table"}
   )
   @PandoraOptions(
         exampleFilesPath = "docs/usage/roa/ui-usage.json"
   )
   default Consumer<SmartWebDriver> after() {
      return smartWebDriver -> {
      };
   }

}
