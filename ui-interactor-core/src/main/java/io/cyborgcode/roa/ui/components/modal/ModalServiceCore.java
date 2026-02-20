package io.cyborgcode.roa.ui.components.modal;

import io.cyborgcode.roa.ui.components.modal.ModalComponentType;
import io.cyborgcode.utilities.reflections.ReflectionUtil;

import static io.cyborgcode.roa.ui.config.UiConfigHolderCore.getUiConfig;

/**
 * Provides service-level methods for interacting with modal UI components.
 *
 * <p>This interface defines operations for checking modal state, closing, getting title/content,
 * and confirming modals, delegating the actual interactions
 * to specific implementations based on the configured {@link ModalComponentType}.
 *
 * @param <E> The container/element type (e.g., Playwright's {@code Locator} or Selenium's {@code WebElement}).
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface ModalServiceCore<E> {

   ModalComponentType DEFAULT_TYPE = getDefaultType();

   /**
    * Retrieves the default modal component type from the configuration.
    *
    * @return The default ModalComponentType.
    */
   static ModalComponentType getDefaultType() {
      try {
         return ReflectionUtil.findEnumImplementationsOfInterface(ModalComponentType.class,
               getUiConfig().modalDefaultType(),
               getUiConfig().projectPackages());
      } catch (Exception ignored) {
         return null;
      }
   }

   default boolean isOpened() {
      return isOpened(DEFAULT_TYPE);
   }

   boolean isOpened(ModalComponentType componentType);

   default void clickButton(E container, String buttonText) {
      clickButton(DEFAULT_TYPE, container, buttonText);
   }

   void clickButton(ModalComponentType componentType, E container, String buttonText);

   default void clickButton(String buttonText) {
      clickButton(DEFAULT_TYPE, buttonText);
   }

   void clickButton(ModalComponentType componentType, String buttonText);

   default String getTitle() {
      return getTitle(DEFAULT_TYPE);
   }

   String getTitle(ModalComponentType componentType);

   default String getBodyText() {
      return getBodyText(DEFAULT_TYPE);
   }

   String getBodyText(ModalComponentType componentType);

   default String getContentTitle() {
      return getContentTitle(DEFAULT_TYPE);
   }

   String getContentTitle(ModalComponentType componentType);

   default void close() {
      close(DEFAULT_TYPE);
   }

   void close(ModalComponentType componentType);
}
