package io.cyborgcode.roa.ui.components.input;

import io.cyborgcode.roa.ui.components.base.BaseUiElement;
import io.cyborgcode.roa.ui.components.table.filters.FilterStrategy;

/**
 * Defines operations for interacting with input fields.
 *
 * <p>This interface provides methods to insert, clear, retrieve values,
 * check enabled states, and handle error messages for input fields.
 *
 * @param <E> The container/element type (e.g., Playwright's {@code Locator} or Selenium's {@code WebElement}).
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface InputCore<E extends BaseUiElement, L> {

   void insert(E container, String value);

   void insert(E container, String inputFieldLabel, String value);

   void insert(String inputFieldLabel, String value);

   void clear(E container);

   void clear(E container, String inputFieldLabel);

   void clear(String inputFieldLabel);

   String getValue(E container);

   String getValue(E container, String inputFieldLabel);

   String getValue(String inputFieldLabel);

   boolean isEnabled(E container);

   boolean isEnabled(E container, String inputFieldLabel);

   boolean isEnabled(String inputFieldLabel);

   String getErrorMessage(E container);

   String getErrorMessage(E container, String inputFieldLabel);

   String getErrorMessage(String inputFieldLabel);

   void insert(L inputFieldContainerLocator, String value);

   void clear(L inputFieldContainerLocator);

   String getValue(L inputFieldContainerLocator);

   boolean isEnabled(L inputFieldContainerLocator);

   String getErrorMessage(L inputFieldContainerLocator);

   default void tableInsertion(E cell, String... values) {
   }

   default void tableFilter(E headerCell, FilterStrategy filterStrategy, String... values) {
   }

}
