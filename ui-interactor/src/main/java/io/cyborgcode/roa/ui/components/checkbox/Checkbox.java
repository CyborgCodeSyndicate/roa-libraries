package io.cyborgcode.roa.ui.components.checkbox;

import io.cyborgcode.pandora.annotation.Pandora;
import io.cyborgcode.pandora.annotation.PandoraOptions;
import io.cyborgcode.pandora.model.CreationKind;
import io.cyborgcode.roa.ui.selenium.smart.SmartWebElement;
import io.cyborgcode.roa.ui.util.strategy.Strategy;
import java.util.List;
import org.openqa.selenium.By;

/**
 * Defines operations for interacting with checkbox components.
 *
 * <p>This interface provides methods to select or deselect checkboxes,
 * verify their selected and enabled states, and retrieve checkbox labels.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@Pandora(
      description = "Interface defining operations for interacting with checkbox UI components. Provides "
            + "methods to check, uncheck, and verify the state of checkboxes in a web interface.",
      tags = {"ui", "component-contract", "checkbox"},
      creation = CreationKind.AUTO
)
@PandoraOptions(
      exampleFilesPath = "ai/roa/ui-usage.json",
      meta = {
         @PandoraOptions.Meta(key = "type", value = "ui-component")
      }
)
public interface Checkbox {

   /**
    * Selects checkboxes with the specified text within the given container.
    *
    * @param container    The container holding the checkboxes.
    * @param checkBoxText The text identifiers for the checkboxes to select.
    */
   @Pandora(
         description = "Selects one or more checkboxes within a container by their display text.",
         tags = {"component-contract", "checkbox"}
   )
   void select(SmartWebElement container, String... checkBoxText);

   /**
    * Selects checkboxes within the given container using a specified strategy.
    *
    * @param container The container holding the checkboxes.
    * @param strategy  The strategy used for selecting checkboxes.
    * @return A string representing the selected checkbox label if applicable.
    */
   @Pandora(
         description = "Selects checkboxes within a container using a custom selection strategy.",
         tags = {"component-contract", "checkbox"}
   )
   String select(SmartWebElement container, Strategy strategy);

   /**
    * Selects checkboxes identified by the given text at the top level.
    *
    * @param checkBoxText The text identifiers for the checkboxes to select.
    */
   @Pandora(
         description = "Selects one or more checkboxes at the top level of the page by their display text.",
         tags = {"component-contract", "checkbox"}
   )
   void select(String... checkBoxText);

   /**
    * Selects checkboxes located by the specified locator.
    *
    * @param checkBoxLocator The locator identifying the checkboxes.
    */
   @Pandora(
         description = "Selects one or more checkboxes using Selenium By locators.",
         tags = {"component-contract", "checkbox"}
   )
   void select(By... checkBoxLocator);

   /**
    * Deselects checkboxes with the specified text within the given container.
    *
    * @param container    The container holding the checkboxes.
    * @param checkBoxText The text identifiers for the checkboxes to deselect.
    */
   @Pandora(
         description = "Deselects one or more checkboxes within a container by their display text.",
         tags = {"component-contract", "checkbox"}
   )
   void deSelect(SmartWebElement container, String... checkBoxText);

   /**
    * Deselects checkboxes within the given container using a specified strategy.
    *
    * @param container The container holding the checkboxes.
    * @param strategy  The strategy used for deselecting checkboxes.
    * @return A string representing the deselected checkbox label if applicable.
    */
   @Pandora(
         description = "Deselects checkboxes within a container using a custom selection strategy.",
         tags = {"component-contract", "checkbox"}
   )
   String deSelect(SmartWebElement container, Strategy strategy);

   /**
    * Deselects checkboxes identified by the given text at the top level.
    *
    * @param checkBoxText The text identifiers for the checkboxes to deselect.
    */
   @Pandora(
         description = "Deselects one or more checkboxes at the top level of the page by their display text.",
         tags = {"component-contract", "checkbox"}
   )
   void deSelect(String... checkBoxText);

   /**
    * Deselects checkboxes located by the specified locator.
    *
    * @param checkBoxLocator The locator identifying the checkboxes.
    */
   @Pandora(
         description = "Deselects one or more checkboxes using Selenium By locators.",
         tags = {"component-contract", "checkbox"}
   )
   void deSelect(By... checkBoxLocator);

   /**
    * Checks if checkboxes with the specified text within the given container are selected.
    *
    * @param container    The container holding the checkboxes.
    * @param checkBoxText The text identifiers for the checkboxes.
    * @return true if all specified checkboxes are selected; false otherwise.
    */
   @Pandora(
         description = "Verifies if the specified checkboxes within a container are selected.",
         tags = {"component-contract", "checkbox"}
   )
   boolean areSelected(SmartWebElement container, String... checkBoxText);

   /**
    * Checks if checkboxes identified by the given text at the top level are selected.
    *
    * @param checkBoxText The text identifiers for the checkboxes.
    * @return true if all specified checkboxes are selected; false otherwise.
    */
   @Pandora(
         description = "Verifies if the specified checkboxes at the top level are selected.",
         tags = {"component-contract", "checkbox"}
   )
   boolean areSelected(String... checkBoxText);

   /**
    * Checks if checkboxes located by the specified locator are selected.
    *
    * @param checkBoxLocator The locator identifying the checkboxes.
    * @return true if all specified checkboxes are selected; false otherwise.
    */
   @Pandora(
         description = "Verifies if the checkboxes identified by the given locators are selected.",
         tags = {"component-contract", "checkbox"}
   )
   boolean areSelected(By... checkBoxLocator);

   /**
    * Checks if checkboxes with the specified text within the given container are enabled.
    *
    * @param container    The container holding the checkboxes.
    * @param checkBoxText The text identifiers for the checkboxes.
    * @return true if all specified checkboxes are enabled; false otherwise.
    */
   @Pandora(
         description = "Verifies if the specified checkboxes within a container are enabled.",
         tags = {"component-contract", "checkbox"}
   )
   boolean areEnabled(SmartWebElement container, String... checkBoxText);

   /**
    * Checks if checkboxes identified by the given text at the top level are enabled.
    *
    * @param checkBoxText The text identifiers for the checkboxes.
    * @return true if all specified checkboxes are enabled; false otherwise.
    */
   @Pandora(
         description = "Verifies if the specified checkboxes at the top level are enabled.",
         tags = {"component-contract", "checkbox"}
   )
   boolean areEnabled(String... checkBoxText);

   /**
    * Checks if checkboxes located by the specified locator are enabled.
    *
    * @param checkBoxLocator The locator identifying the checkboxes.
    * @return true if all specified checkboxes are enabled; false otherwise.
    */
   @Pandora(
         description = "Verifies if the checkboxes identified by the given locators are enabled.",
         tags = {"component-contract", "checkbox"}
   )
   boolean areEnabled(By... checkBoxLocator);

   /**
    * Retrieves a list of labels for all selected checkboxes within the given container.
    *
    * @param container The container holding the checkboxes.
    * @return A list of selected checkbox labels.
    */
   @Pandora(
         description = "Retrieves the labels of all selected checkboxes within the specified container.",
         tags = {"component-contract", "checkbox"}
   )
   List<String> getSelected(SmartWebElement container);

   /**
    * Retrieves a list of labels for all selected checkboxes located by the specified locator.
    *
    * @param containerLocator The locator identifying the checkboxes.
    * @return A list of selected checkbox labels.
    */
   @Pandora(
         description = "Retrieves the labels of all selected checkboxes identified by the given locator.",
         tags = {"component-contract", "checkbox"}
   )
   List<String> getSelected(By containerLocator);

   /**
    * Retrieves a list of labels for all checkboxes within the given container.
    *
    * @param container The container holding the checkboxes.
    * @return A list of all checkbox labels.
    */
   @Pandora(
         description = "Retrieves the labels of all checkboxes within the specified container.",
         tags = {"component-contract", "checkbox"}
   )
   List<String> getAll(SmartWebElement container);

   /**
    * Retrieves a list of labels for all checkboxes located by the specified locator.
    *
    * @param containerLocator The locator identifying the checkboxes.
    * @return A list of all checkbox labels.
    */
   @Pandora(
         description = "Retrieves the labels of all checkboxes identified by the given locator.",
         tags = {"component-contract", "checkbox"}
   )
   List<String> getAll(By containerLocator);

}
