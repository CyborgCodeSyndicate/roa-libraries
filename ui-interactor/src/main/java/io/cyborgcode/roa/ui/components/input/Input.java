package io.cyborgcode.roa.ui.components.input;

import io.cyborgcode.pandora.annotation.Pandora;
import io.cyborgcode.pandora.annotation.PandoraOptions;
import io.cyborgcode.pandora.model.CreationKind;
import io.cyborgcode.roa.ui.components.table.filters.FilterStrategy;
import io.cyborgcode.roa.ui.selenium.smart.SmartWebElement;
import org.openqa.selenium.By;

/**
 * Defines operations for interacting with input fields.
 *
 * <p>This interface provides methods to insert, clear, retrieve values,
 * check enabled states, and handle error messages for input fields.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@Pandora(
      description = "Defines operations for interacting with input fields, including text insertion, "
            + "clearing, value retrieval, and validation.",
      tags = {"ui", "component-contract", "input"},
      creation = CreationKind.AUTO
)
@PandoraOptions(
      exampleFilesPath = "docs/usage/roa/ui-usage.json",
      meta = {
         @PandoraOptions.Meta(key = "type", value = "ui-component")
      }
)
public interface Input {

   /**
    * Inserts a value into the input field within the specified container.
    *
    * @param container The container holding the input field.
    * @param value     The value to insert.
    */
   @Pandora(
         description = "Inserts a value into the input field within the specified container.",
         tags = {"component-contract", "input"}
   )
   void insert(SmartWebElement container, String value);

   /**
    * Inserts a value into the input field identified by its label inside a container.
    *
    * @param container       The container holding the input field.
    * @param inputFieldLabel The label of the input field.
    * @param value           The value to insert.
    */
   @Pandora(
         description = "Inserts a value into the input field identified by its label inside a container.",
         tags = {"component-contract", "input"}
   )
   void insert(SmartWebElement container, String inputFieldLabel, String value);

   /**
    * Inserts a value into the input field identified by its label.
    *
    * @param inputFieldLabel The label of the input field.
    * @param value           The value to insert.
    */
   @Pandora(
         description = "Inserts a value into the input field identified by its label.",
         tags = {"component-contract", "input"}
   )
   void insert(String inputFieldLabel, String value);

   /**
    * Inserts a value into the input field located by the specified locator.
    *
    * @param inputFieldContainerLocator The locator identifying the input field.
    * @param value                      The value to insert.
    */
   @Pandora(
         description = "Inserts a value into the input field located by the specified locator.",
         tags = {"component-contract", "input"}
   )
   void insert(By inputFieldContainerLocator, String value);

   /**
    * Clears the input field within the specified container.
    *
    * @param container The container holding the input field.
    */
   @Pandora(
         description = "Clears the input field within the specified container.",
         tags = {"component-contract", "input"}
   )
   void clear(SmartWebElement container);

   /**
    * Clears the input field identified by its label inside a container.
    *
    * @param container       The container holding the input field.
    * @param inputFieldLabel The label of the input field.
    */
   @Pandora(
         description = "Clears the input field identified by its label inside a container.",
         tags = {"component-contract", "input"}
   )
   void clear(SmartWebElement container, String inputFieldLabel);

   /**
    * Clears the input field identified by its label.
    *
    * @param inputFieldLabel The label of the input field.
    */
   @Pandora(
         description = "Clears the input field identified by its label.",
         tags = {"component-contract", "input"}
   )
   void clear(String inputFieldLabel);

   /**
    * Clears the input field located by the specified locator.
    *
    * @param inputFieldContainerLocator The locator identifying the input field.
    */
   @Pandora(
         description = "Clears the input field located by the specified locator.",
         tags = {"component-contract", "input"}
   )
   void clear(By inputFieldContainerLocator);

   /**
    * Retrieves the current value of the input field within the specified container.
    *
    * @param container The container holding the input field.
    * @return The value of the input field as a string.
    */
   @Pandora(
         description = "Retrieves the current value of the input field within the specified container.",
         tags = {"component-contract", "input"}
   )
   String getValue(SmartWebElement container);

   /**
    * Retrieves the current value of the input field identified by its label inside a container.
    *
    * @param container       The container holding the input field.
    * @param inputFieldLabel The label of the input field.
    * @return The value of the input field as a string.
    */
   @Pandora(
         description = "Retrieves the current value of the input field identified by its label inside a container.",
         tags = {"component-contract", "input"}
   )
   String getValue(SmartWebElement container, String inputFieldLabel);

   /**
    * Retrieves the current value of the input field identified by its label.
    *
    * @param inputFieldLabel The label of the input field.
    * @return The value of the input field as a string.
    */
   @Pandora(
         description = "Retrieves the current value of the input field identified by its label.",
         tags = {"component-contract", "input"}
   )
   String getValue(String inputFieldLabel);

   /**
    * Retrieves the current value of the input field located by the specified locator.
    *
    * @param inputFieldContainerLocator The locator identifying the input field.
    * @return The value of the input field as a string.
    */
   @Pandora(
         description = "Retrieves the current value of the input field located by the specified locator.",
         tags = {"component-contract", "input"}
   )
   String getValue(By inputFieldContainerLocator);

   /**
    * Checks if the input field within the specified container is enabled.
    *
    * @param container The container holding the input field.
    * @return true if the input field is enabled, false otherwise.
    */
   @Pandora(
         description = "Checks if the input field within the specified container is enabled.",
         tags = {"component-contract", "input"}
   )
   boolean isEnabled(SmartWebElement container);

   /**
    * Checks if the input field identified by its label inside a container is enabled.
    *
    * @param container       The container holding the input field.
    * @param inputFieldLabel The label of the input field.
    * @return true if the input field is enabled, false otherwise.
    */
   @Pandora(
         description = "Checks if the input field identified by its label inside a container is enabled.",
         tags = {"component-contract", "input"}
   )
   boolean isEnabled(SmartWebElement container, String inputFieldLabel);

   /**
    * Checks if the input field identified by its label is enabled.
    *
    * @param inputFieldLabel The label of the input field.
    * @return true if the input field is enabled, false otherwise.
    */
   @Pandora(
         description = "Checks if the input field identified by its label is enabled.",
         tags = {"component-contract", "input"}
   )
   boolean isEnabled(String inputFieldLabel);

   /**
    * Checks if the input field located by the specified locator is enabled.
    *
    * @param inputFieldContainerLocator The locator identifying the input field.
    * @return true if the input field is enabled, false otherwise.
    */
   @Pandora(
         description = "Checks if the input field located by the specified locator is enabled.",
         tags = {"component-contract", "input"}
   )
   boolean isEnabled(By inputFieldContainerLocator);

   /**
    * Retrieves the error message displayed for the input field within the specified container.
    *
    * @param container The container holding the input field.
    * @return The error message as a string, or null if no error message is displayed.
    */
   @Pandora(
         description = "Retrieves the error message displayed for the input field within the specified container.",
         tags = {"component-contract", "input"}
   )
   String getErrorMessage(SmartWebElement container);

   /**
    * Retrieves the error message displayed for the input field identified by its label inside a container.
    *
    * @param container       The container holding the input field.
    * @param inputFieldLabel The label of the input field.
    * @return The error message as a string, or null if no error message is displayed.
    */
   @Pandora(
         description = "Retrieves the error message displayed for the input field identified by its label "
               + "inside a container.",
         tags = {"component-contract", "input"}
   )
   String getErrorMessage(SmartWebElement container, String inputFieldLabel);

   /**
    * Retrieves the error message displayed for the input field identified by its label.
    *
    * @param inputFieldLabel The label of the input field.
    * @return The error message as a string, or null if no error message is displayed.
    */
   @Pandora(
         description = "Retrieves the error message displayed for the input field identified by its label.",
         tags = {"component-contract", "input"}
   )
   String getErrorMessage(String inputFieldLabel);

   /**
    * Retrieves the error message displayed for the input field located by the specified locator.
    *
    * @param inputFieldContainerLocator The locator identifying the input field.
    * @return The error message as a string, or null if no error message is displayed.
    */
   @Pandora(
         description = "Retrieves the error message displayed for the input field located by the specified locator.",
         tags = {"component-contract", "input"}
   )
   String getErrorMessage(By inputFieldContainerLocator);

   /**
    * Handles table insertion for an input field.
    *
    * @param cell   The table cell containing the input field.
    * @param values The values to be inserted.
    */
   @Pandora(
         description = "Handles table insertion for an input field.",
         tags = {"component-contract", "input"}
   )
   default void tableInsertion(SmartWebElement cell, String... values) {
   }

   /**
    * Applies a filter to an input field in a table header.
    *
    * @param headerCell     The table header cell containing the input field.
    * @param filterStrategy The filter strategy to apply.
    * @param values         The values to be filtered.
    */
   @Pandora(
         description = "Applies a filter to an input field in a table header.",
         tags = {"component-contract", "input"}
   )
   default void tableFilter(SmartWebElement headerCell, FilterStrategy filterStrategy, String... values) {
   }

}
