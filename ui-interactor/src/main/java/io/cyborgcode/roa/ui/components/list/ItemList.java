package io.cyborgcode.roa.ui.components.list;

import io.cyborgcode.pandora.annotation.Pandora;
import io.cyborgcode.pandora.annotation.PandoraOptions;
import io.cyborgcode.pandora.model.CreationKind;
import io.cyborgcode.roa.ui.selenium.smart.SmartWebElement;
import io.cyborgcode.roa.ui.util.strategy.Strategy;
import java.util.List;
import org.openqa.selenium.By;

/**
 * Represents a list-based component allowing items to be selected, deselected, verified, or retrieved.
 * Provides methods for interacting with items via text, container locators, and custom strategies.
 *
 * <p>Usage examples include selecting items by text, deselecting them, checking their state (selected, visible,
 * or enabled), and retrieving all or only the selected items from a list component.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@Pandora(
      description = "Represents a list-based component allowing items to be selected, deselected, verified, "
            + "or retrieved with various interaction methods.",
      tags = {"ui", "component-contract", "list"},
      creation = CreationKind.AUTO
)
@PandoraOptions(
      exampleFilesPath = "ai/roa/ui-usage.json",
      meta = {
         @PandoraOptions.Meta(key = "type", value = "ui-component")
      }
)
public interface ItemList {

   /**
    * Selects one or more items, identified by the provided text, within the specified container.
    *
    * @param container the container element holding the item list.
    * @param itemText  one or more text labels identifying the items to select.
    */
   @Pandora(
         description = "Selects one or more items, identified by the provided text, within the specified container.",
         tags = {"component-contract", "item-list"}
   )
   void select(SmartWebElement container, String... itemText);

   /**
    * Selects one or more items, identified by the provided text, within the container located by the specified locator.
    *
    * @param containerLocator the locator used to find the container element.
    * @param itemText         one or more text labels identifying the items to select.
    */
   @Pandora(
         description = "Selects one or more items, identified by the provided text, within the container "
               + "located by the specified locator.",
         tags = {"component-contract", "item-list"}
   )
   void select(By containerLocator, String... itemText);

   /**
    * Selects items in the specified container using a custom strategy.
    *
    * @param container the container element holding the item list.
    * @param strategy  the strategy defining how the items should be selected.
    * @return a string representation of the selection result, if applicable.
    */
   @Pandora(
         description = "Selects items in the specified container using a custom strategy.",
         tags = {"component-contract", "item-list"}
   )
   String select(SmartWebElement container, Strategy strategy);

   /**
    * Selects items within the container located by the specified locator, using a custom strategy.
    *
    * @param containerLocator the locator used to find the container element.
    * @param strategy         the strategy defining how the items should be selected.
    * @return a string representation of the selection result, if applicable.
    */
   @Pandora(
         description = "Selects items within the container located by the specified locator, using a custom strategy.",
         tags = {"component-contract", "item-list"}
   )
   String select(By containerLocator, Strategy strategy);

   /**
    * Selects one or more items by text without specifying a container, if the default container is implicitly known.
    *
    * @param itemText one or more text labels identifying the items to select.
    */
   @Pandora(
         description = "Selects one or more items by text without specifying a container, if the "
               + "default container is implicitly known.",
         tags = {"component-contract", "item-list"}
   )
   void select(String... itemText);

   /**
    * Selects items via one or more locators.
    *
    * @param itemListLocator one or more locators identifying the items to select.
    */
   @Pandora(
         description = "Selects items via one or more locators.",
         tags = {"component-contract", "item-list"}
   )
   void select(By... itemListLocator);

   /**
    * Deselects one or more items, identified by the provided text, within the specified container.
    *
    * @param container the container element holding the item list.
    * @param itemText  one or more text labels identifying the items to deselect.
    */
   @Pandora(
         description = "Deselects one or more items, identified by the provided text, within the specified container.",
         tags = {"component-contract", "item-list"}
   )
   void deSelect(SmartWebElement container, String... itemText);

   /**
    * Deselects one or more items, identified by the provided text, within the container located by the specified
    * locator.
    *
    * @param containerLocator the locator used to find the container element.
    * @param itemText         one or more text labels identifying the items to deselect.
    */
   @Pandora(
         description = "Deselects one or more items, identified by the provided text, within "
               + "the container located by the specified locator.",
         tags = {"component-contract", "item-list"}
   )
   void deSelect(By containerLocator, String... itemText);

   /**
    * Deselects items in the specified container using a custom strategy.
    *
    * @param container the container element holding the item list.
    * @param strategy  the strategy defining how the items should be deselected.
    * @return a string representation of the deselection result, if applicable.
    */
   @Pandora(
         description = "Deselects items in the specified container using a custom strategy.",
         tags = {"component-contract", "item-list"}
   )
   String deSelect(SmartWebElement container, Strategy strategy);

   /**
    * Deselects items within the container located by the specified locator, using a custom strategy.
    *
    * @param containerLocator the locator used to find the container element.
    * @param strategy         the strategy defining how the items should be deselected.
    * @return a string representation of the deselection result, if applicable.
    */
   @Pandora(
         description = "Deselects items within the container located by the specified locator, "
               + "using a custom strategy.",
         tags = {"component-contract", "item-list"}
   )
   String deSelect(By containerLocator, Strategy strategy);

   /**
    * Deselects one or more items by text without specifying a container, if the default container is implicitly known.
    *
    * @param itemText one or more text labels identifying the items to deselect.
    */
   @Pandora(
         description = "Deselects one or more items by text without specifying a container, if the "
               + "default container is implicitly known.",
         tags = {"component-contract", "item-list"}
   )
   void deSelect(String... itemText);

   /**
    * Deselects items identified by one or more locators.
    *
    * @param itemListLocator one or more locators identifying the items to deselect.
    */
   @Pandora(
         description = "Deselects items identified by one or more locators.",
         tags = {"component-contract", "item-list"}
   )
   void deSelect(By... itemListLocator);

   /**
    * Determines if the specified items, identified by text, are selected within the specified container.
    *
    * @param container the container element holding the item list.
    * @param itemText  one or more text labels identifying the items to check.
    * @return true if all specified items are selected, otherwise false.
    */
   @Pandora(
         description = "Determines if the specified items, identified by text, are selected within "
               + "the specified container.",
         tags = {"component-contract", "item-list"}
   )
   boolean areSelected(SmartWebElement container, String... itemText);

   /**
    * Determines if the specified items, identified by text, are selected within the container located by the
    * specified locator.
    *
    * @param containerLocator the locator used to find the container element.
    * @param itemText         one or more text labels identifying the items to check.
    * @return true if all specified items are selected, otherwise false.
    */
   @Pandora(
         description = "Determines if the specified items, identified by text, are selected within "
               + "the container located by the specified locator.",
         tags = {"component-contract", "item-list"}
   )
   boolean areSelected(By containerLocator, String... itemText);

   /**
    * Determines if the specified items, identified by text, are selected without specifying a container.
    *
    * @param itemText one or more text labels identifying the items to check.
    * @return true if all specified items are selected, otherwise false.
    */
   @Pandora(
         description = "Determines if the specified items, identified by text, are selected without "
               + "specifying a container.",
         tags = {"component-contract", "item-list"}
   )
   boolean areSelected(String... itemText);

   /**
    * Determines if the specified items are selected using one or more locators.
    *
    * @param itemListLocator one or more locators identifying the items to check.
    * @return true if all located items are selected, otherwise false.
    */
   @Pandora(
         description = "Determines if the specified items are selected using one or more locators.",
         tags = {"component-contract", "item-list"}
   )
   boolean areSelected(By... itemListLocator);

   /**
    * Determines if the specified items, identified by text, are enabled within the specified container.
    *
    * @param container the container element holding the item list.
    * @param itemText  one or more text labels identifying the items to check.
    * @return true if all specified items are enabled, otherwise false.
    */
   @Pandora(
         description = "Determines if the specified items, identified by text, are enabled within "
               + "the specified container.",
         tags = {"component-contract", "item-list"}
   )
   boolean areEnabled(SmartWebElement container, String... itemText);

   /**
    * Determines if the specified items, identified by text, are enabled within the container located by the specified
    * locator.
    *
    * @param containerLocator the locator used to find the container element.
    * @param itemText         one or more text labels identifying the items to check.
    * @return true if all specified items are enabled, otherwise false.
    */
   @Pandora(
         description = "Determines if the specified items, identified by text, are enabled within "
               + "the container located by the specified locator.",
         tags = {"component-contract", "item-list"}
   )
   boolean areEnabled(By containerLocator, String... itemText);

   /**
    * Determines if the specified items, identified by text, are enabled without specifying a container.
    *
    * @param itemText one or more text labels identifying the items to check.
    * @return true if all specified items are enabled, otherwise false.
    */
   @Pandora(
         description = "Determines if the specified items, identified by text, are enabled without "
               + "specifying a container.",
         tags = {"component-contract", "item-list"}
   )
   boolean areEnabled(String... itemText);

   /**
    * Determines if items identified by one or more locators are enabled.
    *
    * @param itemLocator one or more locators identifying the items to check.
    * @return true if all located items are enabled, otherwise false.
    */
   @Pandora(
         description = "Determines if items identified by one or more locators are enabled.",
         tags = {"component-contract", "item-list"}
   )
   boolean areEnabled(By... itemLocator);

   /**
    * Determines if the specified items, identified by text, are visible within the specified container.
    *
    * @param container the container element holding the item list.
    * @param itemText  one or more text labels identifying the items to check.
    * @return true if all specified items are visible, otherwise false.
    */
   @Pandora(
         description = "Determines if the specified items, identified by text, are visible within "
               + "the specified container.",
         tags = {"component-contract", "item-list"}
   )
   boolean areVisible(SmartWebElement container, String... itemText);

   /**
    * Determines if the specified items, identified by text, are visible within the container located by the specified
    * locator.
    *
    * @param containerLocator the locator used to find the container element.
    * @param itemText         one or more text labels identifying the items to check.
    * @return true if all specified items are visible, otherwise false.
    */
   @Pandora(
         description = "Determines if the specified items, identified by text, are visible within "
               + "the container located by the specified locator.",
         tags = {"component-contract", "item-list"}
   )
   boolean areVisible(By containerLocator, String... itemText);

   /**
    * Determines if the specified items, identified by text, are visible without specifying a container.
    *
    * @param itemText one or more text labels identifying the items to check.
    * @return true if all specified items are visible, otherwise false.
    */
   @Pandora(
         description = "Determines if the specified items, identified by text, are visible without "
               + "specifying a container.",
         tags = {"component-contract", "item-list"}
   )
   boolean areVisible(String... itemText);

   /**
    * Determines if items identified by one or more locators are visible.
    *
    * @param itemLocator one or more locators identifying the items to check.
    * @return true if all located items are visible, otherwise false.
    */
   @Pandora(
         description = "Determines if items identified by one or more locators are visible.",
         tags = {"component-contract", "item-list"}
   )
   boolean areVisible(By... itemLocator);

   /**
    * Retrieves the currently selected items as text from the specified container.
    *
    * @param container the container element holding the item list.
    * @return a list of text values for the selected items.
    */
   @Pandora(
         description = "Retrieves the currently selected items as text from the specified container.",
         tags = {"component-contract", "item-list"}
   )
   List<String> getSelected(SmartWebElement container);

   /**
    * Retrieves the currently selected items as text from the container located by the specified locator.
    *
    * @param containerLocator the locator used to find the container element.
    * @return a list of text values for the selected items.
    */
   @Pandora(
         description = "Retrieves the currently selected items as text from the container located "
               + "by the specified locator.",
         tags = {"component-contract", "item-list"}
   )
   List<String> getSelected(By containerLocator);

   /**
    * Retrieves all items as text from the specified container.
    *
    * @param container the container element holding the item list.
    * @return a list of all item text values.
    */
   @Pandora(
         description = "Retrieves all items as text from the specified container.",
         tags = {"component-contract", "item-list"}
   )
   List<String> getAll(SmartWebElement container);

   /**
    * Retrieves all items as text from the container located by the specified locator.
    *
    * @param containerLocator the locator used to find the container element.
    * @return a list of all item text values.
    */
   @Pandora(
         description = "Retrieves all items as text from the container located by the specified locator.",
         tags = {"component-contract", "item-list"}
   )
   List<String> getAll(By containerLocator);

}
