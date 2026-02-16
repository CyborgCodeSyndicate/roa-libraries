package io.cyborgcode.roa.ui.playwright.components.modal;

import com.microsoft.playwright.Locator;
import io.cyborgcode.utilities.reflections.ReflectionUtil;

import static io.cyborgcode.roa.ui.playwright.config.UiConfigHolder.getPlaywrightConfig;

/**
 * Provides service-level methods for interacting with modal UI components.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface ModalService {

   ModalComponentType DEFAULT_TYPE = getDefaultType();

   private static ModalComponentType getDefaultType() {
      try {
         return ReflectionUtil.findEnumImplementationsOfInterface(ModalComponentType.class,
               getPlaywrightConfig().modalDefaultType(),
               getPlaywrightConfig().projectPackages());
      } catch (Exception ignored) {
         return null;
      }
   }

   default boolean isOpen(Locator container) {
      return isOpen(DEFAULT_TYPE, container);
   }

   boolean isOpen(ModalComponentType componentType, Locator container);

   default boolean isOpen(String modalLabel) {
      return isOpen(DEFAULT_TYPE, modalLabel);
   }

   boolean isOpen(ModalComponentType componentType, String modalLabel);

   default boolean isOpenBySelector(String modalSelector) {
      return isOpenBySelector(DEFAULT_TYPE, modalSelector);
   }

   boolean isOpenBySelector(ModalComponentType componentType, String modalSelector);

   default void close(Locator container) {
      close(DEFAULT_TYPE, container);
   }

   void close(ModalComponentType componentType, Locator container);

   default void close(String modalLabel) {
      close(DEFAULT_TYPE, modalLabel);
   }

   void close(ModalComponentType componentType, String modalLabel);

   default void closeBySelector(String modalSelector) {
      closeBySelector(DEFAULT_TYPE, modalSelector);
   }

   void closeBySelector(ModalComponentType componentType, String modalSelector);

   default String getTitle(Locator container) {
      return getTitle(DEFAULT_TYPE, container);
   }

   String getTitle(ModalComponentType componentType, Locator container);

   default String getTitle(String modalLabel) {
      return getTitle(DEFAULT_TYPE, modalLabel);
   }

   String getTitle(ModalComponentType componentType, String modalLabel);

   default String getTitleBySelector(String modalSelector) {
      return getTitleBySelector(DEFAULT_TYPE, modalSelector);
   }

   String getTitleBySelector(ModalComponentType componentType, String modalSelector);

   default String getContent(Locator container) {
      return getContent(DEFAULT_TYPE, container);
   }

   String getContent(ModalComponentType componentType, Locator container);

   default String getContent(String modalLabel) {
      return getContent(DEFAULT_TYPE, modalLabel);
   }

   String getContent(ModalComponentType componentType, String modalLabel);

   default String getContentBySelector(String modalSelector) {
      return getContentBySelector(DEFAULT_TYPE, modalSelector);
   }

   String getContentBySelector(ModalComponentType componentType, String modalSelector);

   default void confirm(Locator container) {
      confirm(DEFAULT_TYPE, container);
   }

   void confirm(ModalComponentType componentType, Locator container);

   default void confirm(String modalLabel) {
      confirm(DEFAULT_TYPE, modalLabel);
   }

   void confirm(ModalComponentType componentType, String modalLabel);

   default void confirmBySelector(String modalSelector) {
      confirmBySelector(DEFAULT_TYPE, modalSelector);
   }

   void confirmBySelector(ModalComponentType componentType, String modalSelector);
}
