package io.cyborgcode.roa.ui.components.select;

import io.cyborgcode.pandora.annotation.Pandora;
import io.cyborgcode.pandora.annotation.PandoraOptions;
import io.cyborgcode.pandora.model.CreationKind;
import io.cyborgcode.roa.ui.selenium.smart.SmartWebElement;
import io.cyborgcode.roa.ui.util.strategy.Strategy;
import java.util.List;
import org.openqa.selenium.By;

/**
 * Represents a selectable component, typically an HTML 'select' or similar UI element
 * that supports selecting multiple options. Provides methods to select options by text
 * or strategy, retrieve available or selected options, and check visibility or enabled states.
 *
 * <p>Implementations commonly rely on Selenium-based operations using container elements
 * ({@link SmartWebElement}) or locators ({@link By}). This interface standardizes how
 * select widgets are handled, regardless of the underlying framework or implementation.
 *
 * <p>Usage ranges from simple dropdown selects to more complex multi-select scenarios.
 * The {@code selectOptions()} methods allow specifying multiple values or applying a
 * {@link Strategy} for custom selection logic.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@Pandora(
      description = "Interface for interacting with select/dropdown components. Provides methods to "
            + "select options, retrieve available and selected options, and verify option visibility or enabled state.",
      tags = {"ui", "component-contract", "select"},
      creation = CreationKind.AUTO
)
@PandoraOptions(
      exampleFilesPath = "docs/usage/roa/ui-usage.json",
      meta = {
         @PandoraOptions.Meta(key = "type", value = "ui-component")
      }
)
public interface Select {

   /**
    * Selects one or more options, identified by their display text or value,
    * within the specified container.
    *
    * @param container the container holding the select component.
    * @param values    one or more option texts or values to be selected.
    */
   @Pandora(
         description = "Selects one or more options, identified by display text or value, within "
               + "the specified container.",
         tags = {"component-contract", "select"}
   )
   void selectOptions(SmartWebElement container, String... values);

   /**
    * Selects one or more options, identified by their display text or value,
    * within the container located by the given locator.
    *
    * @param containerLocator the locator referencing the container.
    * @param values           one or more option texts or values to be selected.
    */
   @Pandora(
         description = "Selects one or more options, identified by display text or value, within "
               + "the container located by the given locator.",
         tags = {"component-contract", "select"}
   )
   void selectOptions(By containerLocator, String... values);

   /**
    * Selects one or more options based on a custom {@link Strategy}, returning the texts
    * of the selected options.
    *
    * @param container the container holding the select component.
    * @param strategy  the selection strategy determining which options to select.
    * @return a list of texts representing the newly selected options.
    */
   @Pandora(
         description = "Selects options based on a custom strategy within the specified container "
               + "and returns the selected option texts.",
         tags = {"component-contract", "select"}
   )
   List<String> selectOptions(SmartWebElement container, Strategy strategy);

   /**
    * Selects one or more options, determined by a custom {@link Strategy}, within the container
    * located by the given locator.
    *
    * @param containerLocator the locator referencing the container.
    * @param strategy         the selection strategy determining which options to select.
    * @return a list of texts representing the newly selected options.
    */
   @Pandora(
         description = "Selects options based on a custom strategy within the container located by "
               + "the given locator and returns the selected option texts.",
         tags = {"component-contract", "select"}
   )
   List<String> selectOptions(By containerLocator, Strategy strategy);

   /**
    * Retrieves all available option texts within the specified container.
    *
    * @param container the container holding the select component.
    * @return a list of strings representing the available options.
    */
   @Pandora(
         description = "Retrieves all available option texts within the specified container.",
         tags = {"component-contract", "select"}
   )
   List<String> getAvailableOptions(SmartWebElement container);

   /**
    * Retrieves all available option texts within the container located by the given locator.
    *
    * @param containerLocator the locator referencing the container.
    * @return a list of strings representing the available options.
    */
   @Pandora(
         description = "Retrieves all available option texts within the container located by the given locator.",
         tags = {"component-contract", "select"}
   )
   List<String> getAvailableOptions(By containerLocator);

   /**
    * Retrieves all currently selected option texts within the specified container.
    *
    * @param container the container holding the select component.
    * @return a list of strings representing the selected options.
    */
   @Pandora(
         description = "Retrieves all currently selected option texts within the specified container.",
         tags = {"component-contract", "select"}
   )
   List<String> getSelectedOptions(SmartWebElement container);

   /**
    * Retrieves all currently selected option texts within the container located by the given locator.
    *
    * @param containerLocator the locator referencing the container.
    * @return a list of strings representing the selected options.
    */
   @Pandora(
         description = "Retrieves all currently selected option texts within the container located by "
               + "the given locator.",
         tags = {"component-contract", "select"}
   )
   List<String> getSelectedOptions(By containerLocator);

   /**
    * Checks if a specific option is visible, identified by display text or value, within the given container.
    *
    * @param container the container holding the select component.
    * @param value     the text or value identifying the option.
    * @return true if the option is visible, otherwise false.
    */
   @Pandora(
         description = "Checks if a specific option is visible, identified by its text or value, within "
               + "the given container.",
         tags = {"component-contract", "select"}
   )
   boolean isOptionVisible(SmartWebElement container, String value);

   /**
    * Checks if a specific option is visible within the container located by the given locator.
    *
    * @param containerLocator the locator referencing the container.
    * @param value            the text or value identifying the option.
    * @return true if the option is visible, otherwise false.
    */
   @Pandora(
         description = "Checks if a specific option is visible within the container located by the given locator.",
         tags = {"component-contract", "select"}
   )
   boolean isOptionVisible(By containerLocator, String value);

   /**
    * Checks if a specific option is enabled, identified by display text or value,
    * within the given container.
    *
    * @param container the container holding the select component.
    * @param value     the text or value identifying the option.
    * @return true if the option is enabled, otherwise false.
    */
   @Pandora(
         description = "Checks if a specific option is enabled, identified by its text or value, within "
               + "the given container.",
         tags = {"component-contract", "select"}
   )
   boolean isOptionEnabled(SmartWebElement container, String value);

   /**
    * Checks if a specific option is enabled within the container located by the given locator.
    *
    * @param containerLocator the locator referencing the container.
    * @param value            the text or value identifying the option.
    * @return true if the option is enabled, otherwise false.
    */
   @Pandora(
         description = "Checks if a specific option is enabled within the container located by the given locator.",
         tags = {"component-contract", "select"}
   )
   boolean isOptionEnabled(By containerLocator, String value);

}
