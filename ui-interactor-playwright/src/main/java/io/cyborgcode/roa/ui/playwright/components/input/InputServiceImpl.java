package io.cyborgcode.roa.ui.playwright.components.input;

import io.cyborgcode.roa.ui.playwright.components.base.AbstractComponentService;
import io.cyborgcode.roa.ui.playwright.components.base.ComponentType;
import io.cyborgcode.roa.ui.playwright.components.factory.ComponentFactory;
import io.cyborgcode.roa.ui.playwright.components.table.filters.FilterStrategy;
import io.cyborgcode.roa.ui.playwright.log.LogUi;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

/**
 * Provides service-level operations for interacting with input components.
 *
 * <p>This class manages interactions with input fields, such as inserting values,
 * clearing fields, retrieving values, and checking states.
 * The actual interactions are delegated to specific {@link Input} implementations based on
 * the provided {@link InputComponentType}.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public class InputServiceImpl extends AbstractComponentService<InputComponentType, Input> implements InputService {

   /**
    * Constructs a new InputServiceImpl using the specified Page.
    *
    * @param Page the Page used for UI interactions.
    */
   public InputServiceImpl(Page page) {
      super(page);
   }

   @Override
   protected Input createComponent(final InputComponentType componentType) {
      return ComponentFactory.getInputComponent(componentType, page);
   }

   @Override
   public void insert(final InputComponentType componentType, final Locator container, final String value) {
      LogUi.step("Inserting value: '{}' into input component of type: '{}'.", value, componentType.getType().name());
      inputComponent(componentType).insert(container, value);
   }

   @Override
   public void insert(final InputComponentType componentType, final Locator container,
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
   public void insertBySelector(final InputComponentType componentType, final String inputFieldContainerSelector,
                                final String value) {
      LogUi.step("Inserting value: '{}' into input component of type: '{}'.", value, componentType.getType().name());
      inputComponent(componentType).insertBySelector(inputFieldContainerSelector, value);
   }

   @Override
   public void clear(final InputComponentType componentType, final Locator container) {
      LogUi.step("Clearing value in input component of type: '{}'.", componentType.getType().name());
      inputComponent(componentType).clear(container);
   }

   @Override
   public void clear(final InputComponentType componentType, final Locator container,
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
   public void clearBySelector(final InputComponentType componentType, final String inputFieldContainerSelector) {
      LogUi.step("Clearing value in input component of type: '{}'.", componentType.getType().name());
      inputComponent(componentType).clearBySelector(inputFieldContainerSelector);
   }

   @Override
   public String getValue(final InputComponentType componentType, final Locator container) {
      LogUi.step("Fetching value from input component of type: '{}'.", componentType.getType().name());
      return inputComponent(componentType).getValue(container);
   }

   @Override
   public String getValue(final InputComponentType componentType, final Locator container,
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
   public String getValueBySelector(final InputComponentType componentType,
                                    final String inputFieldContainerSelector) {
      LogUi.step("Fetching value from input component of type: '{}'.", componentType.getType().name());
      return inputComponent(componentType).getValueBySelector(inputFieldContainerSelector);
   }

   @Override
   public boolean isEnabled(final InputComponentType componentType, final Locator container) {
      LogUi.step("Checking if input component of type: '{}' is enabled.", componentType.getType().name());
      return inputComponent(componentType).isEnabled(container);
   }

   @Override
   public boolean isEnabled(final InputComponentType componentType, final Locator container,
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
   public boolean isEnabledBySelector(final InputComponentType componentType,
                                      final String inputFieldContainerSelector) {
      LogUi.step("Checking if input component of type: '{}' is enabled.", componentType.getType().name());
      return inputComponent(componentType).isEnabledBySelector(inputFieldContainerSelector);
   }

   @Override
   public String getErrorMessage(final InputComponentType componentType, final Locator container) {
      LogUi.step("Fetching error message from input component of type: '{}'.", componentType.getType().name());
      return inputComponent(componentType).getErrorMessage(container);
   }

   @Override
   public String getErrorMessage(final InputComponentType componentType, final Locator container,
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
   public String getErrorMessageBySelector(final InputComponentType componentType,
                                           final String inputFieldContainerSelector) {
      LogUi.step("Fetching error message from input component of type: '{}'.", componentType.getType().name());
      return inputComponent(componentType).getErrorMessageBySelector(inputFieldContainerSelector);
   }

   private Input inputComponent(final InputComponentType componentType) {
      return getOrCreateComponent(componentType);
   }

   @Override
   public void tableFilter(Locator cellElement, ComponentType componentType, FilterStrategy filterStrategy, String... values) {

   }

   @Override
   public void tableInsertion(Locator cellElement, ComponentType componentType, String... values) {

   }

   @Override
   public void insertion(ComponentType componentType, String selector, Object... values) {

   }
}
