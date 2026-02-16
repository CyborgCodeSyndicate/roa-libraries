package io.cyborgcode.roa.ui.playwright.components.input;

import com.microsoft.playwright.Locator;

/**
 * Defines operations for interacting with input fields.
 *
 * <p>This interface provides methods to insert, clear, retrieve values,
 * check enabled states, and handle error messages for input fields.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface Input {

   /**
    * Inserts a value into the input field within the specified container.
    *
    * @param container The container holding the input field.
    * @param value     The value to insert.
    */
   void insert(Locator container, String value);

   /**
    * Inserts a value into the input field identified by its label inside a container.
    *
    * @param container       The container holding the input field.
    * @param inputFieldLabel The label of the input field.
    * @param value           The value to insert.
    */
   void insert(Locator container, String inputFieldLabel, String value);

   /**
    * Inserts a value into the input field identified by its label.
    *
    * @param inputFieldLabel The label of the input field.
    * @param value           The value to insert.
    */
   void insert(String inputFieldLabel, String value);

   /**
    * Inserts a value into the input field located by the specified CSS selector.
    *
    * @param inputFieldContainerSelector The CSS selector identifying the input field.
    * @param value                       The value to insert.
    */
   void insertBySelector(String inputFieldContainerSelector, String value);

   /**
    * Clears the input field within the specified container.
    *
    * @param container The container holding the input field.
    */
   void clear(Locator container);

   /**
    * Clears the input field identified by its label inside a container.
    *
    * @param container       The container holding the input field.
    * @param inputFieldLabel The label of the input field.
    */
   void clear(Locator container, String inputFieldLabel);

   /**
    * Clears the input field identified by its label.
    *
    * @param inputFieldLabel The label of the input field.
    */
   void clear(String inputFieldLabel);

   /**
    * Clears the input field located by the specified CSS selector.
    *
    * @param inputFieldContainerSelector The CSS selector identifying the input field.
    */
   void clearBySelector(String inputFieldContainerSelector);

   /**
    * Retrieves the current value of the input field within the specified container.
    *
    * @param container The container holding the input field.
    * @return The value of the input field as a string.
    */
   String getValue(Locator container);

   /**
    * Retrieves the current value of the input field identified by its label inside a container.
    *
    * @param container       The container holding the input field.
    * @param inputFieldLabel The label of the input field.
    * @return The value of the input field as a string.
    */
   String getValue(Locator container, String inputFieldLabel);

   /**
    * Retrieves the current value of the input field identified by its label.
    *
    * @param inputFieldLabel The label of the input field.
    * @return The value of the input field as a string.
    */
   String getValue(String inputFieldLabel);

   /**
    * Retrieves the current value of the input field located by the specified CSS selector.
    *
    * @param inputFieldContainerSelector The CSS selector identifying the input field.
    * @return The value of the input field as a string.
    */
   String getValueBySelector(String inputFieldContainerSelector);

   /**
    * Checks if the input field within the specified container is enabled.
    *
    * @param container The container holding the input field.
    * @return true if the input field is enabled, false otherwise.
    */
   boolean isEnabled(Locator container);

   /**
    * Checks if the input field identified by its label inside a container is enabled.
    *
    * @param container       The container holding the input field.
    * @param inputFieldLabel The label of the input field.
    * @return true if the input field is enabled, false otherwise.
    */
   boolean isEnabled(Locator container, String inputFieldLabel);

   /**
    * Checks if the input field identified by its label is enabled.
    *
    * @param inputFieldLabel The label of the input field.
    * @return true if the input field is enabled, false otherwise.
    */
   boolean isEnabled(String inputFieldLabel);

   /**
    * Checks if the input field located by the specified CSS selector is enabled.
    *
    * @param inputFieldContainerSelector The CSS selector identifying the input field.
    * @return true if the input field is enabled, false otherwise.
    */
   boolean isEnabledBySelector(String inputFieldContainerSelector);

   /**
    * Retrieves the error message displayed for the input field within the specified container.
    *
    * @param container The container holding the input field.
    * @return The error message as a string, or null if no error message is displayed.
    */
   String getErrorMessage(Locator container);

   /**
    * Retrieves the error message displayed for the input field identified by its label inside a container.
    *
    * @param container       The container holding the input field.
    * @param inputFieldLabel The label of the input field.
    * @return The error message as a string, or null if no error message is displayed.
    */
   String getErrorMessage(Locator container, String inputFieldLabel);

   /**
    * Retrieves the error message displayed for the input field identified by its label.
    *
    * @param inputFieldLabel The label of the input field.
    * @return The error message as a string, or null if no error message is displayed.
    */
   String getErrorMessage(String inputFieldLabel);

   /**
    * Retrieves the error message displayed for the input field located by the specified CSS selector.
    *
    * @param inputFieldContainerSelector The CSS selector identifying the input field.
    * @return The error message as a string, or null if no error message is displayed.
    */
   String getErrorMessageBySelector(String inputFieldContainerSelector);

   /**
    * Handles table insertion for an input field.
    *
    * @param cell   The table cell containing the input field.
    * @param values The values to be inserted.
    */
   default void tableInsertion(Locator cell, String... values) {
   }

}
