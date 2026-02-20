package io.cyborgcode.roa.ui.components.input;

import io.cyborgcode.roa.ui.components.base.AbstractComponentServiceCore;
import io.cyborgcode.roa.ui.components.base.ComponentType;
import io.cyborgcode.roa.ui.components.input.InputComponentType;
import io.cyborgcode.roa.ui.components.table.filters.FilterStrategy;
import io.cyborgcode.roa.ui.log.LogUi;

/**
 * Abstract base implementation for input service operations.
 *
 * <p>This class provides framework-agnostic implementations for all input service methods
 * defined in {@link InputServiceCore}, delegating the actual UI interactions to
 * specific {@link InputCore} component instances resolved via {@link #getOrCreateComponent}.
 *
 * @param <E> The element/container type (e.g., Playwright's {@code Locator} or Selenium's {@code WebElement}).
 * @param <C> The input component interface type (e.g., Playwright's {@code Input}).
 * @param <D> The driver or page type (e.g., Playwright's {@code Page} or Selenium's {@code WebDriver}).
 * @author Cyborg Code Syndicate 💍👨💻
 */
public abstract class InputServiceImplCore<E, C extends InputCore<E>, D, L>
      extends AbstractComponentServiceCore<InputComponentType, C, D>
      implements InputServiceCore<E, L> {

   /**
    * Constructs a new InputServiceImplCore with the given driver.
    *
    * @param driver The driver/page instance for UI interactions.
    */
   protected InputServiceImplCore(final D driver) {
      super(driver);
   }

   /**
    * Retrieves the input component for the given component type.
    *
    * @param componentType The input component type.
    * @return The input component instance.
    */
   protected C inputComponent(final InputComponentType componentType) {
      return getOrCreateComponent(componentType);
   }

   @Override
   public void insert(final InputComponentType componentType, final E container, final String value) {
      LogUi.step("Inserting value: '{}' into input component of type: '{}'.", value, componentType.getType().name());
      inputComponent(componentType).insert(container, value);
   }

   @Override
   public void insert(final InputComponentType componentType, final E container,
                      final String inputFieldLabel, final String value) {
      LogUi.step("Inserting value: '{}' into input field labeled: '{}' of type: '{}'.", value, inputFieldLabel,
            componentType.getType().name());
      inputComponent(componentType).insert(container, inputFieldLabel, value);
   }

   @Override
   public void insert(final InputComponentType componentType, final String inputFieldLabel, final String value) {
      LogUi.step("Inserting value: '{}' into input field labeled: '{}' of type: '{}'.", value, inputFieldLabel,
            componentType.getType().name());
      inputComponent(componentType).insert(inputFieldLabel, value);
   }

   @Override
   public void clear(final InputComponentType componentType, final E container) {
      LogUi.step("Clearing value in input component of type: '{}'.", componentType.getType().name());
      inputComponent(componentType).clear(container);
   }

   @Override
   public void clear(final InputComponentType componentType, final E container,
                     final String inputFieldLabel) {
      LogUi.step("Clearing value in input field labeled: '{}' of type: '{}'.", inputFieldLabel,
            componentType.getType().name());
      inputComponent(componentType).clear(container, inputFieldLabel);
   }

   @Override
   public void clear(final InputComponentType componentType, final String inputFieldLabel) {
      LogUi.step("Clearing value in input field labeled: '{}' of type: '{}'.", inputFieldLabel,
            componentType.getType().name());
      inputComponent(componentType).clear(inputFieldLabel);
   }

   @Override
   public String getValue(final InputComponentType componentType, final E container) {
      LogUi.step("Fetching value from input component of type: '{}'.", componentType.getType().name());
      return inputComponent(componentType).getValue(container);
   }

   @Override
   public String getValue(final InputComponentType componentType, final E container,
                          final String inputFieldLabel) {
      LogUi.step("Fetching value from input field labeled: '{}' of type: '{}'.", inputFieldLabel,
            componentType.getType().name());
      return inputComponent(componentType).getValue(container, inputFieldLabel);
   }

   @Override
   public String getValue(final InputComponentType componentType, final String inputFieldLabel) {
      LogUi.step("Fetching value from input field labeled: '{}' of type: '{}'.", inputFieldLabel,
            componentType.getType().name());
      return inputComponent(componentType).getValue(inputFieldLabel);
   }

   @Override
   public boolean isEnabled(final InputComponentType componentType, final E container) {
      LogUi.step("Checking if input component of type: '{}' is enabled.", componentType.getType().name());
      return inputComponent(componentType).isEnabled(container);
   }

   @Override
   public boolean isEnabled(final InputComponentType componentType, final E container,
                            final String inputFieldLabel) {
      LogUi.step("Checking if input field labeled: '{}' of type: '{}' is enabled.", inputFieldLabel,
            componentType.getType().name());
      return inputComponent(componentType).isEnabled(container, inputFieldLabel);
   }

   @Override
   public boolean isEnabled(final InputComponentType componentType, final String inputFieldLabel) {
      LogUi.step("Checking if input field labeled: '{}' of type: '{}' is enabled.", inputFieldLabel,
            componentType.getType().name());
      return inputComponent(componentType).isEnabled(inputFieldLabel);
   }

   @Override
   public String getErrorMessage(final InputComponentType componentType, final E container) {
      LogUi.step("Fetching error message from input component of type: '{}'.", componentType.getType().name());
      return inputComponent(componentType).getErrorMessage(container);
   }

   @Override
   public String getErrorMessage(final InputComponentType componentType, final E container,
                                 final String inputFieldLabel) {
      LogUi.step("Fetching error message from input field labeled: '{}' of type: '{}'.", inputFieldLabel,
            componentType.getType().name());
      return inputComponent(componentType).getErrorMessage(container, inputFieldLabel);
   }

   @Override
   public String getErrorMessage(final InputComponentType componentType, final String inputFieldLabel) {
      LogUi.step("Fetching error message from input field labeled: '{}' of type: '{}'.", inputFieldLabel,
            componentType.getType().name());
      return inputComponent(componentType).getErrorMessage(inputFieldLabel);
   }

   @Override
   public void tableInsertion(E cellElement, ComponentType componentType, String... values) {
      if (!(componentType instanceof InputComponentType)) {
         throw new IllegalArgumentException("Component type needs to be from: InputComponentType.");
      }
      LogUi.step("Inserting values into table cell for component type: '{}'.", componentType.getType().name());
      inputComponent((InputComponentType) componentType).tableInsertion(cellElement, values);
   }

   @Override
   public void tableFilter(E cellElement, ComponentType componentType, FilterStrategy filterStrategy, String... values) {
      if (!(componentType instanceof InputComponentType)) {
         throw new IllegalArgumentException("Component type needs to be from: InputComponentType.");
      }
      LogUi.step("Applying table filter for component type: '{}' with strategy: '{}'.",
            componentType.getType().name(), filterStrategy);
      inputComponent((InputComponentType) componentType).tableFilter(cellElement, filterStrategy, values);
   }
}
