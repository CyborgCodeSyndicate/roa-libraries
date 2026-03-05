package io.cyborgcode.roa.ui.components.toggle;

import io.cyborgcode.roa.ui.components.base.BaseUiElement;
import io.cyborgcode.utilities.reflections.ReflectionUtil;

import static io.cyborgcode.roa.ui.config.UiConfigHolderCore.getUiConfig;

/**
 * Provides service-level methods for interacting with toggle UI components.
 *
 * <p>This interface defines operations for toggling, checking state,
 * and verifying the enabled state of toggle components, delegating the actual interactions
 * to specific implementations based on the configured {@link ToggleComponentType}.
 *
 * @param <E> The container/element type (e.g., Playwright's {@code Locator} or Selenium's {@code WebElement}).
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface ToggleServiceCore<E extends BaseUiElement, L> {

   ToggleComponentType DEFAULT_TYPE = getDefaultType();

   /**
    * Retrieves the default toggle component type from the configuration.
    *
    * @return The default ToggleComponentType.
    */
   static ToggleComponentType getDefaultType() {
      try {
         return ReflectionUtil.findEnumImplementationsOfInterface(ToggleComponentType.class,
               getUiConfig().toggleDefaultType(),
               getUiConfig().projectPackages());
      } catch (Exception ignored) {
         return null;
      }
   }

   default void activate(E container, String toggleText) {
      activate(DEFAULT_TYPE, container, toggleText);
   }

   void activate(ToggleComponentType componentType, E container, String toggleText);

   default void activate(String toggleText) {
      activate(DEFAULT_TYPE, toggleText);
   }

   void activate(ToggleComponentType componentType, String toggleText);

   default void deactivate(E container, String toggleText) {
      deactivate(DEFAULT_TYPE, container, toggleText);
   }

   void deactivate(ToggleComponentType componentType, E container, String toggleText);

   default void deactivate(String toggleText) {
      deactivate(DEFAULT_TYPE, toggleText);
   }

   void deactivate(ToggleComponentType componentType, String toggleText);

   default boolean isEnabled(E container, String toggleText) {
      return isEnabled(DEFAULT_TYPE, container, toggleText);
   }

   boolean isEnabled(ToggleComponentType componentType, E container, String toggleText);

   default boolean isEnabled(String toggleText) {
      return isEnabled(DEFAULT_TYPE, toggleText);
   }

   boolean isEnabled(ToggleComponentType componentType, String toggleText);

   default boolean isActivated(E container, String toggleText) {
      return isActivated(DEFAULT_TYPE, container, toggleText);
   }

   boolean isActivated(ToggleComponentType componentType, E container, String toggleText);

   default boolean isActivated(String toggleText) {
      return isActivated(DEFAULT_TYPE, toggleText);
   }

   boolean isActivated(ToggleComponentType componentType, String toggleText);

   default void activate(L toggleLocator) {
      activate(DEFAULT_TYPE, toggleLocator);
   }

   void activate(ToggleComponentType componentType, L toggleLocator);

   default void deactivate(L toggleLocator) {
      deactivate(DEFAULT_TYPE, toggleLocator);
   }

   void deactivate(ToggleComponentType componentType, L toggleLocator);

   default boolean isEnabled(L toggleLocator) {
      return isEnabled(DEFAULT_TYPE, toggleLocator);
   }

   boolean isEnabled(ToggleComponentType componentType, L toggleLocator);

   default boolean isActivated(L toggleLocator) {
      return isActivated(DEFAULT_TYPE, toggleLocator);
   }

   boolean isActivated(ToggleComponentType componentType, L toggleLocator);
}
