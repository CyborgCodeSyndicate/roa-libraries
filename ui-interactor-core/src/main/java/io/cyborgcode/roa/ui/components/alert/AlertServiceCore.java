package io.cyborgcode.roa.ui.components.alert;

import io.cyborgcode.roa.ui.components.base.BaseUiElement;
import io.cyborgcode.utilities.reflections.ReflectionUtil;

import static io.cyborgcode.roa.ui.config.UiConfigHolderCore.getUiConfig;

/**
 * Provides service-level methods for interacting with alert UI components.
 *
 * <p>This interface defines operations for retrieving messages, checking visibility,
 * dismissing alerts, and getting alert types, delegating the actual interactions
 * to specific implementations based on the configured {@link AlertComponentType}.
 *
 * @param <E> The container/element type (e.g., Playwright's {@code Locator} or Selenium's {@code WebElement}).
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface AlertServiceCore<E extends BaseUiElement, L> {

   AlertComponentType DEFAULT_TYPE = getDefaultType();

   /**
    * Retrieves the default alert component type from the configuration.
    *
    * @return The default AlertComponentType.
    */
   static AlertComponentType getDefaultType() {
      try {
         return ReflectionUtil.findEnumImplementationsOfInterface(AlertComponentType.class,
               getUiConfig().alertDefaultType(),
               getUiConfig().projectPackages());
      } catch (Exception ignored) {
         return null;
      }
   }

   default String getValue(E container) {
      return getValue(DEFAULT_TYPE, container);
   }

   String getValue(AlertComponentType componentType, E container);

   default boolean isVisible(E container) {
      return isVisible(DEFAULT_TYPE, container);
   }

   boolean isVisible(AlertComponentType componentType, E container);

   default String getValue(L containerLocator) {
      return getValue(DEFAULT_TYPE, containerLocator);
   }

   String getValue(AlertComponentType componentType, L containerLocator);

   default boolean isVisible(L containerLocator) {
      return isVisible(DEFAULT_TYPE, containerLocator);
   }

   boolean isVisible(AlertComponentType componentType, L containerLocator);

}
