package io.cyborgcode.roa.ui.playwright.insertion;

import io.cyborgcode.roa.ui.playwright.components.base.ComponentType;

/**
 * Defines a contract for inserting data into UI components.
 *
 * <p>This interface abstracts the behavior of inserting values into various UI elements,
 * ensuring that all components implement a consistent insertion mechanism.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface Insertion {

   /**
    * Performs an insertion operation on a specified UI component.
    *
    * @param componentType The type of UI component that should receive the insertion.
    * @param selector      The Playwright selector identifying the UI element.
    * @param values        The values to be inserted into the component.
    */
   void insertion(ComponentType componentType, String selector, Object... values);
}
