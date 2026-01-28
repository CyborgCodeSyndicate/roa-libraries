package io.cyborgcode.roa.ui.components.accordion;

import io.cyborgcode.pandora.annotation.Pandora;
import io.cyborgcode.pandora.annotation.PandoraOptions;
import io.cyborgcode.pandora.model.CreationKind;
import io.cyborgcode.roa.ui.selenium.smart.SmartWebElement;
import io.cyborgcode.roa.ui.util.strategy.Strategy;
import java.util.List;
import org.openqa.selenium.By;

/**
 * Defines the contract for an accordion UI component.
 *
 * <p>An accordion typically consists of multiple sections or panels
 * that can be expanded or collapsed to show or hide content.
 * Implementations of this interface provide consistent ways to
 * manipulate accordion panels by expanding or collapsing them,
 * as well as checking their state.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@Pandora(
      description = "Interface defining the contract for an accordion UI component. An accordion "
            + "typically consists of multiple sections or panels that can be expanded or "
            + "collapsed to show or hide content.",
      tags = {"ui", "component-contract", "accordion"},
      creation = CreationKind.AUTO
)
@PandoraOptions(
      exampleFilesPath = "ai/roa/ui-usage.json",
      meta = {
         @PandoraOptions.Meta(key = "type", value = "ui-component")
      }
)
public interface Accordion {

   /**
    * Expands specific accordion panels within a given container.
    *
    * @param container     The container element holding the accordion.
    * @param accordionText One or more text labels identifying the panels to expand.
    */
   @Pandora(
         description = "Expands specific accordion panels within a given container by their text labels.",
         tags = {"component-contract", "accordion"}
   )
   void expand(SmartWebElement container, String... accordionText);

   /**
    * Expands an accordion panel determined by a strategy,
    * such as selecting a random or first panel.
    *
    * @param container The container element holding the accordion.
    * @param strategy  The strategy for determining which panel to expand.
    * @return A string indicating which panel was expanded, if applicable.
    */
   @Pandora(
         description = "Expands an accordion panel using a specified strategy (e.g., random, first) "
               + "within a container.",
         tags = {"component-contract", "accordion"}
   )
   String expand(SmartWebElement container, Strategy strategy);

   /**
    * Expands one or more accordion panels by text label, without specifying a container.
    *
    * @param accordionText One or more text labels identifying the panels to expand.
    */
   @Pandora(
         description = "Expands one or more accordion panels by their text labels without specifying a container.",
         tags = {"component-contract", "accordion"}
   )
   void expand(String... accordionText);

   /**
    * Expands accordion panels identified by one or more locators.
    *
    * @param accordionLocator One or more {@link By} locators for the target panels.
    */
   @Pandora(
         description = "Expands accordion panels identified by one or more locators.",
         tags = {"component-contract", "accordion"}
   )
   void expand(By... accordionLocator);

   /**
    * Collapses specific accordion panels within a given container.
    *
    * @param container     The container element holding the accordion.
    * @param accordionText One or more text labels identifying the panels to collapse.
    */
   @Pandora(
         description = "Collapses specific accordion panels within a given container by their text labels.",
         tags = {"component-contract", "accordion"}
   )
   void collapse(SmartWebElement container, String... accordionText);

   /**
    * Collapses an accordion panel determined by a strategy.
    *
    * @param container The container element holding the accordion.
    * @param strategy  The strategy for determining which panel to collapse.
    * @return A string indicating which panel was collapsed, if applicable.
    */
   @Pandora(
         description = "Collapses an accordion panel using a specified strategy (e.g., random, first) "
               + "within a container.",
         tags = {"component-contract", "accordion"}
   )
   String collapse(SmartWebElement container, Strategy strategy);

   /**
    * Collapses one or more accordion panels by text label, without specifying a container.
    *
    * @param accordionText One or more text labels identifying the panels to collapse.
    */
   @Pandora(
         description = "Collapses one or more accordion panels by their text labels without specifying a container.",
         tags = {"component-contract", "accordion"}
   )
   void collapse(String... accordionText);

   /**
    * Collapses accordion panels identified by one or more locators.
    *
    * @param accordionLocator One or more {@link By} locators for the target panels.
    */
   @Pandora(
         description = "Collapses accordion panels identified by one or more locators.",
         tags = {"component-contract", "accordion"}
   )
   void collapse(By... accordionLocator);

   /**
    * Checks whether specific accordion panels are enabled within a given container.
    *
    * @param container     The container element holding the accordion.
    * @param accordionText One or more text labels identifying the panels to check.
    * @return {@code true} if all specified panels are enabled, otherwise {@code false}.
    */
   @Pandora(
         description = "Checks if specific accordion panels within a container are enabled.",
         tags = {"component-contract", "accordion"}
   )
   boolean areEnabled(SmartWebElement container, String... accordionText);

   /**
    * Checks whether specific accordion panels (by text) are enabled, without a container.
    *
    * @param accordionText One or more text labels identifying the panels to check.
    * @return {@code true} if all specified panels are enabled, otherwise {@code false}.
    */
   @Pandora(
         description = "Checks if specific accordion panels are enabled without specifying a container.",
         tags = {"component-contract", "accordion"}
   )
   boolean areEnabled(String... accordionText);

   /**
    * Checks whether accordion panels identified by one or more locators are enabled.
    *
    * @param accordionLocator One or more {@link By} locators for the target panels.
    * @return {@code true} if all specified panels are enabled, otherwise {@code false}.
    */
   @Pandora(
         description = "Checks if accordion panels identified by locators are enabled.",
         tags = {"component-contract", "accordion"}
   )
   boolean areEnabled(By... accordionLocator);

   /**
    * Retrieves a list of text labels for accordion panels currently in an expanded state within a container element.
    *
    * @param container The container element holding the accordion.
    * @return A list of expanded panel labels.
    */
   @Pandora(
         description = "Retrieves a list of text labels for all expanded accordion panels within a container.",
         tags = {"component-contract", "accordion"}
   )
   List<String> getExpanded(SmartWebElement container);

   /**
    * Retrieves a list of text labels for accordion panels currently in an expanded state identified by a locator.
    *
    * @param containerLocator The {@link By} locator for the accordion elements.
    * @return A list of expanded panel labels.
    */
   @Pandora(
         description = "Retrieves a list of text labels for all expanded accordion panels using a locator.",
         tags = {"component-contract", "accordion"}
   )
   List<String> getExpanded(By containerLocator);

   /**
    * Retrieves a list of text labels for accordion panels currently in a collapsed state within a container element.
    *
    * @param container The container element holding the accordion.
    * @return A list of collapsed panel labels.
    */
   @Pandora(
         description = "Retrieves a list of text labels for all collapsed accordion panels within a container.",
         tags = {"component-contract", "accordion"}
   )
   List<String> getCollapsed(SmartWebElement container);

   /**
    * Retrieves a list of text labels for accordion panels currently in a collapsed state identified by a locator.
    *
    * @param containerLocator The {@link By} locator for the accordion elements.
    * @return A list of collapsed panel labels.
    */
   @Pandora(
         description = "Retrieves a list of text labels for all collapsed accordion panels using a locator.",
         tags = {"component-contract", "accordion"}
   )
   List<String> getCollapsed(By containerLocator);

   /**
    * Retrieves a list of all panel labels within the accordion container,
    * regardless of their expanded or collapsed state.
    *
    * @param container The container element holding the accordion.
    * @return A list of all panel labels in the accordion.
    */
   @Pandora(
         description = "Retrieves a list of all panel labels within an accordion container, regardless of their state.",
         tags = {"component-contract", "accordion"}
   )
   List<String> getAll(SmartWebElement container);

   /**
    * Retrieves a list of all panel labels identified by a locator,
    * regardless of their expanded or collapsed state.
    *
    * @param containerLocator The {@link By} locator for the accordion elements.
    * @return A list of all panel labels in the accordion.
    */
   @Pandora(
         description = "Retrieves a list of all panel labels using a locator, regardless of their state.",
         tags = {"component-contract", "accordion"}
   )
   List<String> getAll(By containerLocator);

   /**
    * Retrieves the title text from a specific accordion panel using a locator.
    *
    * @param accordionLocator The {@link By} locator identifying the target panel.
    * @return The title text of the accordion panel.
    */
   @Pandora(
         description = "Retrieves the title text from a specific accordion panel using a locator.",
         tags = {"component-contract", "accordion"}
   )
   String getTitle(By accordionLocator);

   /**
    * Retrieves the main text content from a specific accordion panel using a locator.
    *
    * @param accordionLocator The {@link By} locator identifying the target panel.
    * @return The text content of the accordion panel.
    */
   @Pandora(
         description = "Retrieves the main text content from a specific accordion panel using a locator.",
         tags = {"component-contract", "accordion"}
   )
   String getText(By accordionLocator);
}
