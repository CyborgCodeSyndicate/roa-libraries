package io.cyborgcode.roa.ui.playwright.components.accordion;

import com.microsoft.playwright.Locator;
import io.cyborgcode.utilities.reflections.ReflectionUtil;

import static io.cyborgcode.roa.ui.playwright.config.UiConfigHolder.getPlaywrightConfig;

/**
 * Provides service-level methods for interacting with accordion UI components.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface AccordionService {

   AccordionComponentType DEFAULT_TYPE = getDefaultType();

   private static AccordionComponentType getDefaultType() {
      try {
         return ReflectionUtil.findEnumImplementationsOfInterface(AccordionComponentType.class,
               getPlaywrightConfig().accordionDefaultType(),
               getPlaywrightConfig().projectPackages());
      } catch (Exception ignored) {
         return null;
      }
   }

   default void expand(Locator container, String sectionLabel) { expand(DEFAULT_TYPE, container, sectionLabel); }
   void expand(AccordionComponentType componentType, Locator container, String sectionLabel);
   default void expand(Locator container) { expand(DEFAULT_TYPE, container); }
   void expand(AccordionComponentType componentType, Locator container);
   default void expand(String sectionLabel) { expand(DEFAULT_TYPE, sectionLabel); }
   void expand(AccordionComponentType componentType, String sectionLabel);
   default void expandBySelector(String accordionSelector) { expandBySelector(DEFAULT_TYPE, accordionSelector); }
   void expandBySelector(AccordionComponentType componentType, String accordionSelector);

   default void collapse(Locator container, String sectionLabel) { collapse(DEFAULT_TYPE, container, sectionLabel); }
   void collapse(AccordionComponentType componentType, Locator container, String sectionLabel);
   default void collapse(Locator container) { collapse(DEFAULT_TYPE, container); }
   void collapse(AccordionComponentType componentType, Locator container);
   default void collapse(String sectionLabel) { collapse(DEFAULT_TYPE, sectionLabel); }
   void collapse(AccordionComponentType componentType, String sectionLabel);
   default void collapseBySelector(String accordionSelector) { collapseBySelector(DEFAULT_TYPE, accordionSelector); }
   void collapseBySelector(AccordionComponentType componentType, String accordionSelector);

   default boolean isExpanded(Locator container, String sectionLabel) { return isExpanded(DEFAULT_TYPE, container, sectionLabel); }
   boolean isExpanded(AccordionComponentType componentType, Locator container, String sectionLabel);
   default boolean isExpanded(Locator container) { return isExpanded(DEFAULT_TYPE, container); }
   boolean isExpanded(AccordionComponentType componentType, Locator container);
   default boolean isExpanded(String sectionLabel) { return isExpanded(DEFAULT_TYPE, sectionLabel); }
   boolean isExpanded(AccordionComponentType componentType, String sectionLabel);
   default boolean isExpandedBySelector(String accordionSelector) { return isExpandedBySelector(DEFAULT_TYPE, accordionSelector); }
   boolean isExpandedBySelector(AccordionComponentType componentType, String accordionSelector);

   default boolean isEnabled(Locator container, String sectionLabel) { return isEnabled(DEFAULT_TYPE, container, sectionLabel); }
   boolean isEnabled(AccordionComponentType componentType, Locator container, String sectionLabel);
   default boolean isEnabled(Locator container) { return isEnabled(DEFAULT_TYPE, container); }
   boolean isEnabled(AccordionComponentType componentType, Locator container);
   default boolean isEnabled(String sectionLabel) { return isEnabled(DEFAULT_TYPE, sectionLabel); }
   boolean isEnabled(AccordionComponentType componentType, String sectionLabel);
   default boolean isEnabledBySelector(String accordionSelector) { return isEnabledBySelector(DEFAULT_TYPE, accordionSelector); }
   boolean isEnabledBySelector(AccordionComponentType componentType, String accordionSelector);
}
