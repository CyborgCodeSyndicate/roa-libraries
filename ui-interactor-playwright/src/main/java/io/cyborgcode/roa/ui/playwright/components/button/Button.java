package io.cyborgcode.roa.ui.playwright.components.button;

import com.microsoft.playwright.Locator;

/**
 * Represents a UI button component with various ways to locate and interact
 * with buttons in a web interface.
 *
 * <p>Provides methods for clicking a button by text label, container reference,
 * or selector, as well as checking if the button is enabled or visible.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface Button {

   /**
    * Clicks a button identified by text, within a given container.
    *
    * @param container  The container holding the button.
    * @param buttonText The text label of the button to click.
    */
   void click(Locator container, String buttonText);

   /**
    * Clicks a button within the given container, selecting the first or only
    * button found in that container.
    *
    * @param container The container holding the button.
    */
   void click(Locator container);

   /**
    * Clicks a button identified by its text label, at the top level (no container).
    *
    * @param buttonText The text label of the button to click.
    */
   void click(String buttonText);

   /**
    * Clicks a button identified by a specific CSS selector.
    *
    * @param buttonSelector A CSS selector for the button element.
    */
   void clickBySelector(String buttonSelector);

   /**
    * Checks if a button (by text) inside the specified container is enabled.
    *
    * @param container  The container holding the button.
    * @param buttonText The text label of the button.
    * @return {@code true} if the button is enabled; {@code false} otherwise.
    */
   boolean isEnabled(Locator container, String buttonText);

   /**
    * Checks if the only or first button inside the specified container is enabled.
    *
    * @param container The container holding the button.
    * @return {@code true} if the button is enabled; {@code false} otherwise.
    */
   boolean isEnabled(Locator container);

   /**
    * Checks if a button identified by text is enabled at the top level (no container).
    *
    * @param buttonText The text label of the button.
    * @return {@code true} if the button is enabled; {@code false} otherwise.
    */
   boolean isEnabled(String buttonText);

   /**
    * Checks if a button identified by a CSS selector is enabled.
    *
    * @param buttonSelector A CSS selector for the button element.
    * @return {@code true} if the button is enabled; {@code false} otherwise.
    */
   boolean isEnabledBySelector(String buttonSelector);

   /**
    * Checks if a button (by text) inside the specified container is visible on the page.
    *
    * @param container  The container holding the button.
    * @param buttonText The text label of the button.
    * @return {@code true} if the button is visible; {@code false} otherwise.
    */
   boolean isVisible(Locator container, String buttonText);

   /**
    * Checks if the only or first button inside the specified container is visible on the page.
    *
    * @param container The container holding the button.
    * @return {@code true} if the button is visible; {@code false} otherwise.
    */
   boolean isVisible(Locator container);

   /**
    * Checks if a button identified by text is visible at the top level (no container).
    *
    * @param buttonText The text label of the button.
    * @return {@code true} if the button is visible; {@code false} otherwise.
    */
   boolean isVisible(String buttonText);

   /**
    * Checks if a button identified by a CSS selector is visible.
    *
    * @param buttonSelector A CSS selector for the button element.
    * @return {@code true} if the button is visible; {@code false} otherwise.
    */
   boolean isVisibleBySelector(String buttonSelector);

   /**
    * Optionally clicks an element within a table cell or container; default no-op.
    *
    * @param cell The cell element that may contain a button.
    */
   default void clickElementInCell(Locator cell) {
   }
}
