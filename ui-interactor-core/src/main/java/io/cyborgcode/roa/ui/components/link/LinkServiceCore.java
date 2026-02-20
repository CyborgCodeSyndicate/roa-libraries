package io.cyborgcode.roa.ui.components.link;

import io.cyborgcode.roa.ui.components.button.ButtonServiceCore;
import io.cyborgcode.utilities.reflections.ReflectionUtil;

import static io.cyborgcode.roa.ui.config.UiConfigHolderCore.getUiConfig;

/**
 * Provides service-level methods for interacting with link UI components.
 *
 * <p>This interface defines operations for clicking links, getting href values,
 * checking visibility and enabled state, delegating the actual interactions
 * to specific implementations based on the configured {@link LinkComponentType}.
 *
 * @param <E> The container/element type (e.g., Playwright's {@code Locator} or Selenium's {@code WebElement}).
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface LinkServiceCore<E> extends ButtonServiceCore<E> {

   LinkComponentType DEFAULT_TYPE = getDefaultType();

   /**
    * Retrieves the default link component type from the configuration.
    *
    * @return The default LinkComponentType.
    */
   static LinkComponentType getDefaultType() {
      try {
         return ReflectionUtil.findEnumImplementationsOfInterface(LinkComponentType.class,
               getUiConfig().linkDefaultType(),
               getUiConfig().projectPackages());
      } catch (Exception ignored) {
         return null;
      }
   }

   default void doubleClick(E container, String linkText) {
      doubleClick(DEFAULT_TYPE, container, linkText);
   }

   void doubleClick(LinkComponentType componentType, E container, String linkText);

   default void doubleClick(E container) {
      doubleClick(DEFAULT_TYPE, container);
   }

   void doubleClick(LinkComponentType componentType, E container);

   default void doubleClick(String linkText) {
      doubleClick(DEFAULT_TYPE, linkText);
   }

   void doubleClick(LinkComponentType componentType, String linkText);


}
