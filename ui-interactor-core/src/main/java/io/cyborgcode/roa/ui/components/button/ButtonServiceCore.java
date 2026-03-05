package io.cyborgcode.roa.ui.components.button;

import io.cyborgcode.roa.ui.components.base.BaseUiElement;
import io.cyborgcode.roa.ui.components.button.ButtonComponentType;
import io.cyborgcode.roa.ui.components.table.insertion.TableInsertion;
import io.cyborgcode.utilities.reflections.ReflectionUtil;

import static io.cyborgcode.roa.ui.config.UiConfigHolderCore.getUiConfig;
/**
 * Provides service-level methods for interacting with button UI components.
 *
 * <p>This interface defines operations for clicking buttons, checking enabled/visible state,
 * delegating the actual interactions to specific implementations based on the configured
 * {@link ButtonComponentType}.
 *
 * @param <E> The container/element type (e.g., Playwright's {@code Locator} or Selenium's {@code WebElement}).
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface ButtonServiceCore<E extends BaseUiElement, L> extends TableInsertion<E> {

   ButtonComponentType DEFAULT_TYPE = getDefaultType();

   /**
    * Retrieves the default button component type from the configuration.
    *
    * @return The default ButtonComponentType.
    */
   static ButtonComponentType getDefaultType() {
      try {
         return ReflectionUtil.findEnumImplementationsOfInterface(ButtonComponentType.class,
               getUiConfig().buttonDefaultType(),
               getUiConfig().projectPackages());
      } catch (Exception ignored) {
         return null;
      }
   }

   default void click(E container, String buttonText) {
      click(DEFAULT_TYPE, container, buttonText);
   }

   void click(ButtonComponentType componentType, E container, String buttonText);

   default void click(E container) {
      click(DEFAULT_TYPE, container);
   }

   void click(ButtonComponentType componentType, E container);

   default void click(L buttonSelector) {
      click(DEFAULT_TYPE, buttonSelector);
   }

   void click(ButtonComponentType componentType, L buttonSelector);

   default void click(String buttonText) {
      click(DEFAULT_TYPE, buttonText);
   }

   void click(ButtonComponentType componentType, String buttonText);



   default boolean isEnabled(E container, String buttonText) {
      return isEnabled(DEFAULT_TYPE, container, buttonText);
   }

   boolean isEnabled(ButtonComponentType componentType, E container, String buttonText);

   default boolean isEnabled(E container) {
      return isEnabled(DEFAULT_TYPE, container);
   }

   boolean isEnabled(ButtonComponentType componentType, E container);

   default boolean isEnabled(L buttonSelector) {
      return isEnabled(DEFAULT_TYPE, buttonSelector);
   }

   boolean isEnabled(ButtonComponentType componentType, L buttonSelector);

   default boolean isEnabled(String buttonText) {
      return isEnabled(DEFAULT_TYPE, buttonText);
   }

   boolean isEnabled(ButtonComponentType componentType, String buttonText);


   default boolean isVisible(E container, String buttonText) {
      return isVisible(DEFAULT_TYPE, container, buttonText);
   }

   boolean isVisible(ButtonComponentType componentType, E container, String buttonText);

   default boolean isVisible(E container) {
      return isVisible(DEFAULT_TYPE, container);
   }

   boolean isVisible(ButtonComponentType componentType, E container);

   default boolean isVisible(L buttonSelector) {
      return isVisible(DEFAULT_TYPE, buttonSelector);
   }

   boolean isVisible(ButtonComponentType componentType, L buttonSelector);

   default boolean isVisible(String buttonText) {
      return isVisible(DEFAULT_TYPE, buttonText);
   }

   boolean isVisible(ButtonComponentType componentType, String buttonText);


}
