package io.cyborgcode.roa.ui.components.link;

import io.cyborgcode.roa.ui.components.button.ButtonService;
import io.cyborgcode.roa.ui.selenium.smart.SmartWebElement;
import io.cyborgcode.utilities.reflections.ReflectionUtil;
import org.openqa.selenium.By;

import static io.cyborgcode.roa.ui.config.UiConfigHolder.getUiConfig;

/**
 * Provides service-level methods for interacting with link UI components.
 *
 * <p>This interface defines operations for performing actions on links, such as
 * clicking and double-clicking, while delegating the actual interactions to
 * specific implementations based on the configured {@link LinkComponentType}.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface LinkService extends ButtonService {

   LinkComponentType DEFAULT_TYPE = getDefaultType();

   /**
    * Retrieves the default link component type from the configuration.
    *
    * @return The default LinkComponentType.
    */
   private static LinkComponentType getDefaultType() {
      try {
         return ReflectionUtil.findEnumImplementationsOfInterface(LinkComponentType.class,
               getUiConfig().linkDefaultType(),
               getUiConfig().projectPackage());
      } catch (Exception ignored) {
         return null;
      }
   }

   /**
    * Double-clicks a link with the specified text inside a container, using the default link component type.
    *
    * @param container The SmartWebElement container that contains the link.
    * @param linkText  The text of the link to click.
    */
   default void doubleClick(SmartWebElement container, String linkText) {
      doubleClick(DEFAULT_TYPE, container, linkText);
   }

   /**
    * Double-clicks a link with the specified text inside a container, using the given link component type.
    *
    * @param componentType The specific link component type.
    * @param container     The SmartWebElement container that contains the link.
    * @param linkText      The text of the link to click.
    */
   void doubleClick(LinkComponentType componentType, SmartWebElement container, String linkText);

   /**
    * Double-clicks a link inside a container, using the default link component type.
    *
    * @param container The SmartWebElement container that contains the link.
    */
   default void doubleClick(SmartWebElement container) {
      doubleClick(DEFAULT_TYPE, container);
   }

   /**
    * Double-clicks a link inside a container, using the given link component type.
    *
    * @param componentType The specific link component type.
    * @param container     The SmartWebElement container that contains the link.
    */
   void doubleClick(LinkComponentType componentType, SmartWebElement container);

   /**
    * Double-clicks a link with the specified text, using the default link component type.
    *
    * @param linkText The text of the link to click.
    */
   default void doubleClick(String linkText) {
      doubleClick(DEFAULT_TYPE, linkText);
   }

   /**
    * Double-clicks a link with the specified text, using the given link component type.
    *
    * @param componentType The specific link component type.
    * @param linkText      The text of the link to click.
    */
   void doubleClick(LinkComponentType componentType, String linkText);

   /**
    * Double-clicks a link located by the specified locator, using the default link component type.
    *
    * @param linkLocator The By locator for the link to click.
    */
   default void doubleClick(By linkLocator) {
      doubleClick(DEFAULT_TYPE, linkLocator);
   }

   /**
    * Double-clicks a link located by the specified locator, using the given link component type.
    *
    * @param componentType The specific link component type.
    * @param linkLocator   The By locator for the link to click.
    */
   void doubleClick(LinkComponentType componentType, By linkLocator);
}