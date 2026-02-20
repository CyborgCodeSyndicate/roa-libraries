package io.cyborgcode.roa.ui.components.accordion;

import io.cyborgcode.roa.ui.components.accordion.AccordionComponentType;
import io.cyborgcode.roa.ui.util.strategy.Strategy;
import io.cyborgcode.utilities.reflections.ReflectionUtil;
import java.util.List;

import static io.cyborgcode.roa.ui.config.UiConfigHolderCore.getUiConfig;

/**
 * Provides service-level methods for interacting with accordion UI components.
 *
 * <p>This interface defines operations for expanding, collapsing, checking state,
 * and verifying the enabled state of accordion components, delegating the actual interactions
 * to specific implementations based on the configured {@link AccordionComponentType}.
 *
 * @param <E> The container/element type (e.g., Playwright's {@code Locator} or Selenium's {@code WebElement}).
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface AccordionServiceCore<E> {

   AccordionComponentType DEFAULT_TYPE = getDefaultType();

   /**
    * Retrieves the default accordion component type from the configuration.
    *
    * @return The default AccordionComponentType.
    */
   static AccordionComponentType getDefaultType() {
      try {
         return ReflectionUtil.findEnumImplementationsOfInterface(AccordionComponentType.class,
               getUiConfig().accordionDefaultType(),
               getUiConfig().projectPackages());
      } catch (Exception ignored) {
         return null;
      }
   }

   default void expand(E container, String... accordionText) {
      expand(DEFAULT_TYPE, container, accordionText);
   }

   void expand(AccordionComponentType componentType, E container, String... accordionText);

   default void expand(E container, Strategy strategy) {
      expand(DEFAULT_TYPE, container, strategy);
   }

   void expand(AccordionComponentType componentType, E container, Strategy strategy);

   default void expand(String... accordionText) {
      expand(DEFAULT_TYPE, accordionText);
   }

   void expand(AccordionComponentType componentType, String... accordionText);

   default void collapse(E container, String... accordionText) {
      collapse(DEFAULT_TYPE, container, accordionText);
   }

   void collapse(AccordionComponentType componentType, E container, String... accordionText);

   default void collapse(E container, Strategy strategy) {
      collapse(DEFAULT_TYPE, container, strategy);
   }

   void collapse(AccordionComponentType componentType, E container, Strategy strategy);

   default void collapse(String... accordionText) {
      collapse(DEFAULT_TYPE, accordionText);
   }

   void collapse(AccordionComponentType componentType, String... accordionText);

   default boolean areEnabled(E container, String... accordionText) {
      return areEnabled(DEFAULT_TYPE, container, accordionText);
   }

   boolean areEnabled(AccordionComponentType componentType, E container, String... accordionText);

   default boolean areEnabled(String... accordionText) {
      return areEnabled(DEFAULT_TYPE, accordionText);
   }

   boolean areEnabled(AccordionComponentType componentType, String... accordionText);

   default List<String> getExpanded(E container) {
      return getExpanded(DEFAULT_TYPE, container);
   }

   List<String> getExpanded(AccordionComponentType componentType, E container);

   default List<String> getCollapsed(E container) {
      return getCollapsed(DEFAULT_TYPE, container);
   }

   List<String> getCollapsed(AccordionComponentType componentType, E container);

   default List<String> getAll(E container) {
      return getAll(DEFAULT_TYPE, container);
   }

   List<String> getAll(AccordionComponentType componentType, E container);

}
