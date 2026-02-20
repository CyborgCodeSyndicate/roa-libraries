package io.cyborgcode.roa.ui.components.input;

import io.cyborgcode.roa.ui.components.input.InputComponentType;
import io.cyborgcode.roa.ui.components.table.filters.TableFilter;
import io.cyborgcode.roa.ui.components.table.insertion.TableInsertion;
import io.cyborgcode.roa.ui.insertion.Insertion;
import io.cyborgcode.utilities.reflections.ReflectionUtil;

import static io.cyborgcode.roa.ui.config.UiConfigHolderCore.getUiConfig;

/**
 * Provides service-level methods for interacting with input UI components.
 *
 * <p>This interface defines operations for inserting, clearing, retrieving values,
 * and verifying the state of input fields, delegating the actual interactions
 * to specific implementations based on the configured {@link InputComponentType}.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface InputServiceCore<E, L> extends TableFilter<E>, TableInsertion<E>, Insertion<L> {

   InputComponentType DEFAULT_TYPE = getDefaultType();

   /**
    * Retrieves the default input component type from the configuration.
    *
    * @return The default InputComponentType.
    */
   static InputComponentType getDefaultType() {
      try {
         return ReflectionUtil.findEnumImplementationsOfInterface(InputComponentType.class,
               getUiConfig().inputDefaultType(),
               getUiConfig().projectPackages());
      } catch (Exception ignored) {
         return null;
      }
   }

   default void insert(E container, String value) {
      insert(DEFAULT_TYPE, container, value);
   }

   void insert(InputComponentType componentType, E container, String value);

   default void insert(E container, String inputFieldLabel, String value) {
      insert(DEFAULT_TYPE, container, inputFieldLabel, value);
   }

   void insert(InputComponentType componentType, E container, String inputFieldLabel, String value);

   default void insert(String inputFieldLabel, String value) {
      insert(DEFAULT_TYPE, inputFieldLabel, value);
   }

   void insert(InputComponentType componentType, String inputFieldLabel, String value);

   default void clear(E container) {
      clear(DEFAULT_TYPE, container);
   }

   void clear(InputComponentType componentType, E container);

   default void clear(E container, String inputFieldLabel) {
      clear(DEFAULT_TYPE, container, inputFieldLabel);
   }

   void clear(InputComponentType componentType, E container, String inputFieldLabel);

   default void clear(String inputFieldLabel) {
      clear(DEFAULT_TYPE, inputFieldLabel);
   }

   void clear(InputComponentType componentType, String inputFieldLabel);

   default String getValue(E container) {
      return getValue(DEFAULT_TYPE, container);
   }

   String getValue(InputComponentType componentType, E container);

   default String getValue(E container, String inputFieldLabel) {
      return getValue(DEFAULT_TYPE, container, inputFieldLabel);
   }

   String getValue(InputComponentType componentType, E container, String inputFieldLabel);

   default String getValue(String inputFieldLabel) {
      return getValue(DEFAULT_TYPE, inputFieldLabel);
   }

   String getValue(InputComponentType componentType, String inputFieldLabel);

   default boolean isEnabled(E container) {
      return isEnabled(DEFAULT_TYPE, container);
   }

   boolean isEnabled(InputComponentType componentType, E container);

   default boolean isEnabled(E container, String inputFieldLabel) {
      return isEnabled(DEFAULT_TYPE, container, inputFieldLabel);
   }

   boolean isEnabled(InputComponentType componentType, E container, String inputFieldLabel);

   default boolean isEnabled(String inputFieldLabel) {
      return isEnabled(DEFAULT_TYPE, inputFieldLabel);
   }

   boolean isEnabled(InputComponentType componentType, String inputFieldLabel);

   default String getErrorMessage(E container) {
      return getErrorMessage(DEFAULT_TYPE, container);
   }

   String getErrorMessage(InputComponentType componentType, E container);

   default String getErrorMessage(E container, String inputFieldLabel) {
      return getErrorMessage(DEFAULT_TYPE, container, inputFieldLabel);
   }

   String getErrorMessage(InputComponentType componentType, E container, String inputFieldLabel);

   default String getErrorMessage(String inputFieldLabel) {
      return getErrorMessage(DEFAULT_TYPE, inputFieldLabel);
   }

   String getErrorMessage(InputComponentType componentType, String inputFieldLabel);

}
