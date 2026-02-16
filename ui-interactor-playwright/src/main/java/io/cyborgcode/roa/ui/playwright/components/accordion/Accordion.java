package io.cyborgcode.roa.ui.playwright.components.accordion;

import com.microsoft.playwright.Locator;

/**
 * Defines operations for interacting with accordion UI elements.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface Accordion {

   void expand(Locator container, String sectionLabel);

   void expand(Locator container);

   void expand(String sectionLabel);

   void expandBySelector(String accordionSelector);

   void collapse(Locator container, String sectionLabel);

   void collapse(Locator container);

   void collapse(String sectionLabel);

   void collapseBySelector(String accordionSelector);

   boolean isExpanded(Locator container, String sectionLabel);

   boolean isExpanded(Locator container);

   boolean isExpanded(String sectionLabel);

   boolean isExpandedBySelector(String accordionSelector);

   boolean isEnabled(Locator container, String sectionLabel);

   boolean isEnabled(Locator container);

   boolean isEnabled(String sectionLabel);

   boolean isEnabledBySelector(String accordionSelector);
}
