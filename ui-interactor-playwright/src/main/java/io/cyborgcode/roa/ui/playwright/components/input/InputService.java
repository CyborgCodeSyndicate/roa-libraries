package io.cyborgcode.roa.ui.playwright.components.input;

import com.microsoft.playwright.Locator;
import io.cyborgcode.roa.ui.components.input.InputComponentType;
import io.cyborgcode.roa.ui.components.input.InputServiceCore;
import io.cyborgcode.roa.ui.playwright.base.PwBy;

/**
 * Provides service-level methods for interacting with input UI components.
 *
 * <p>This interface defines operations for inserting, clearing, retrieving values,
 * and verifying the state of input fields, delegating the actual interactions
 * to specific implementations based on the configured {@link InputComponentType}.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface InputService extends InputServiceCore<Locator, PwBy> {

   default void insert(PwBy inputFieldContainerSelector, String value) {
      insert(DEFAULT_TYPE, inputFieldContainerSelector, value);
   }

   void insert(InputComponentType componentType, PwBy inputFieldContainerSelector, String value);


   default void clear(PwBy inputFieldContainerSelector) {
      clear(DEFAULT_TYPE, inputFieldContainerSelector);
   }

   void clear(InputComponentType componentType, PwBy inputFieldContainerSelector);


   default String getValue(PwBy inputFieldContainerSelector) {
      return getValue(DEFAULT_TYPE, inputFieldContainerSelector);
   }

   String getValue(InputComponentType componentType, PwBy inputFieldContainerSelector);


   default boolean isEnabled(PwBy inputFieldContainerSelector) {
      return isEnabled(DEFAULT_TYPE, inputFieldContainerSelector);
   }

   boolean isEnabled(InputComponentType componentType, PwBy inputFieldContainerSelector);


   default String getErrorMessage(PwBy inputFieldContainerSelector) {
      return getErrorMessage(DEFAULT_TYPE, inputFieldContainerSelector);
   }

   String getErrorMessage(InputComponentType componentType, PwBy inputFieldContainerSelector);
}
