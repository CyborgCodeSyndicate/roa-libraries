package io.cyborgcode.roa.ui.playwright.components.input;

import io.cyborgcode.roa.ui.playwright.components.table.filters.TableFilter;
import io.cyborgcode.roa.ui.playwright.components.table.insertion.TableInsertion;
import io.cyborgcode.roa.ui.playwright.insertion.Insertion;
import com.microsoft.playwright.Locator;
import io.cyborgcode.utilities.reflections.ReflectionUtil;

import static io.cyborgcode.roa.ui.playwright.config.UiConfigHolder.getPlaywrightConfig;

/**
 * Provides service-level methods for interacting with input UI components.
 *
 * <p>This interface defines operations for inserting, clearing, retrieving values,
 * and verifying the state of input fields, delegating the actual interactions
 * to specific implementations based on the configured {@link InputComponentType}.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface InputService extends Insertion, TableInsertion, TableFilter {

   InputComponentType DEFAULT_TYPE = getDefaultType();

   /**
    * Retrieves the default input component type from the configuration.
    *
    * @return The default InputComponentType.
    */
   static InputComponentType getDefaultType() {
      try {
         return ReflectionUtil.findEnumImplementationsOfInterface(InputComponentType.class,
               getPlaywrightConfig().inputDefaultType(),
               getPlaywrightConfig().projectPackages());
      } catch (Exception ignored) {
         return null;
      }
   }

   default void insert(Locator container, String value) {
      insert(DEFAULT_TYPE, container, value);
   }

   void insert(InputComponentType componentType, Locator container, String value);

   default void insert(Locator container, String inputFieldLabel, String value) {
      insert(DEFAULT_TYPE, container, inputFieldLabel, value);
   }

   void insert(InputComponentType componentType, Locator container, String inputFieldLabel, String value);

   default void insert(String inputFieldLabel, String value) {
      insert(DEFAULT_TYPE, inputFieldLabel, value);
   }

   void insert(InputComponentType componentType, String inputFieldLabel, String value);

   default void insertBySelector(String inputFieldContainerSelector, String value) {
      insertBySelector(DEFAULT_TYPE, inputFieldContainerSelector, value);
   }

   void insertBySelector(InputComponentType componentType, String inputFieldContainerSelector, String value);

   default void clear(Locator container) {
      clear(DEFAULT_TYPE, container);
   }

   void clear(InputComponentType componentType, Locator container);

   default void clear(Locator container, String inputFieldLabel) {
      clear(DEFAULT_TYPE, container, inputFieldLabel);
   }

   void clear(InputComponentType componentType, Locator container, String inputFieldLabel);

   default void clear(String inputFieldLabel) {
      clear(DEFAULT_TYPE, inputFieldLabel);
   }

   void clear(InputComponentType componentType, String inputFieldLabel);

   default void clearBySelector(String inputFieldContainerSelector) {
      clearBySelector(DEFAULT_TYPE, inputFieldContainerSelector);
   }

   void clearBySelector(InputComponentType componentType, String inputFieldContainerSelector);

   default String getValue(Locator container) {
      return getValue(DEFAULT_TYPE, container);
   }

   String getValue(InputComponentType componentType, Locator container);

   default String getValue(Locator container, String inputFieldLabel) {
      return getValue(DEFAULT_TYPE, container, inputFieldLabel);
   }

   String getValue(InputComponentType componentType, Locator container, String inputFieldLabel);

   default String getValue(String inputFieldLabel) {
      return getValue(DEFAULT_TYPE, inputFieldLabel);
   }

   String getValue(InputComponentType componentType, String inputFieldLabel);

   default String getValueBySelector(String inputFieldContainerSelector) {
      return getValueBySelector(DEFAULT_TYPE, inputFieldContainerSelector);
   }

   String getValueBySelector(InputComponentType componentType, String inputFieldContainerSelector);

   default boolean isEnabled(Locator container) {
      return isEnabled(DEFAULT_TYPE, container);
   }

   boolean isEnabled(InputComponentType componentType, Locator container);

   default boolean isEnabled(Locator container, String inputFieldLabel) {
      return isEnabled(DEFAULT_TYPE, container, inputFieldLabel);
   }

   boolean isEnabled(InputComponentType componentType, Locator container, String inputFieldLabel);

   default boolean isEnabled(String inputFieldLabel) {
      return isEnabled(DEFAULT_TYPE, inputFieldLabel);
   }

   boolean isEnabled(InputComponentType componentType, String inputFieldLabel);

   default boolean isEnabledBySelector(String inputFieldContainerSelector) {
      return isEnabledBySelector(DEFAULT_TYPE, inputFieldContainerSelector);
   }

   boolean isEnabledBySelector(InputComponentType componentType, String inputFieldContainerSelector);

   default String getErrorMessage(Locator container) {
      return getErrorMessage(DEFAULT_TYPE, container);
   }

   String getErrorMessage(InputComponentType componentType, Locator container);

   default String getErrorMessage(Locator container, String inputFieldLabel) {
      return getErrorMessage(DEFAULT_TYPE, container, inputFieldLabel);
   }

   String getErrorMessage(InputComponentType componentType, Locator container, String inputFieldLabel);

   default String getErrorMessage(String inputFieldLabel) {
      return getErrorMessage(DEFAULT_TYPE, inputFieldLabel);
   }

   String getErrorMessage(InputComponentType componentType, String inputFieldLabel);

   default String getErrorMessageBySelector(String inputFieldContainerSelector) {
      return getErrorMessageBySelector(DEFAULT_TYPE, inputFieldContainerSelector);
   }

   String getErrorMessageBySelector(InputComponentType componentType, String inputFieldContainerSelector);
}
