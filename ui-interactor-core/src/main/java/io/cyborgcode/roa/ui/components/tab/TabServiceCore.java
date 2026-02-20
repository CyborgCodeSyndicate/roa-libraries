package io.cyborgcode.roa.ui.components.tab;

import io.cyborgcode.roa.ui.components.button.ButtonServiceCore;
import io.cyborgcode.roa.ui.components.tab.TabComponentType;
import io.cyborgcode.utilities.reflections.ReflectionUtil;

import static io.cyborgcode.roa.ui.config.UiConfigHolderCore.getUiConfig;

/**
 * Provides service-level methods for interacting with tab UI components.
 *
 * <p>This interface defines operations for selecting tabs, checking selected state,
 * and verifying the enabled state of tab components, delegating the actual interactions
 * to specific implementations based on the configured {@link TabComponentType}.
 *
 * @param <E> The container/element type (e.g., Playwright's {@code Locator} or Selenium's {@code WebElement}).
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface TabServiceCore<E> extends ButtonServiceCore<E> {

   TabComponentType DEFAULT_TYPE = getDefaultType();

   /**
    * Retrieves the default tab component type from the configuration.
    *
    * @return The default TabComponentType.
    */
   static TabComponentType getDefaultType() {
      try {
         return ReflectionUtil.findEnumImplementationsOfInterface(TabComponentType.class,
               getUiConfig().tabDefaultType(),
               getUiConfig().projectPackages());
      } catch (Exception ignored) {
         return null;
      }
   }


   default boolean isSelected(E container, String buttonText) {
      return isSelected(DEFAULT_TYPE, container, buttonText);
   }

   boolean isSelected(TabComponentType componentType, E container, String buttonText);

   default boolean isSelected(E container) {
      return isSelected(DEFAULT_TYPE, container);
   }

   boolean isSelected(TabComponentType componentType, E container);

   default boolean isSelected(String buttonText) {
      return isSelected(DEFAULT_TYPE, buttonText);
   }

   boolean isSelected(TabComponentType componentType, String buttonText);

}
