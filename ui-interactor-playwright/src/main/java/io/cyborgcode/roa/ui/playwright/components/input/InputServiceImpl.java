package io.cyborgcode.roa.ui.playwright.components.input;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.cyborgcode.roa.ui.components.base.ComponentType;
import io.cyborgcode.roa.ui.components.input.InputComponentType;
import io.cyborgcode.roa.ui.components.input.InputServiceImplCore;
import io.cyborgcode.roa.ui.log.LogUi;
import io.cyborgcode.roa.ui.playwright.base.PwBy;
import io.cyborgcode.roa.ui.playwright.components.factory.ComponentFactory;

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
public class InputServiceImpl extends InputServiceImplCore<Locator, Input, Page, PwBy>
      implements InputService {

   /**
    * Constructs a new InputServiceImpl using the specified Page.
    *
    * @param page the Page used for UI interactions.
    */
   public InputServiceImpl(Page page) {
      super(page);
   }

   @Override
   protected Input createComponent(final InputComponentType componentType) {
      return ComponentFactory.getInputComponent(componentType, driver);
   }

   @Override
   public void insert(final InputComponentType componentType, final PwBy inputFieldContainerSelector,
                      final String value) {
      LogUi.step("Inserting value: '{}' into input component of type: '{}'.", value, componentType.getType().name());
      inputComponent(componentType).insert(inputFieldContainerSelector, value);
   }

   @Override
   public void clear(final InputComponentType componentType, final PwBy inputFieldContainerSelector) {
      LogUi.step("Clearing value in input component of type: '{}'.", componentType.getType().name());
      inputComponent(componentType).clear(inputFieldContainerSelector);
   }

   @Override
   public String getValue(final InputComponentType componentType,
                          final PwBy inputFieldContainerSelector) {
      LogUi.step("Fetching value from input component of type: '{}'.", componentType.getType().name());
      return inputComponent(componentType).getValue(inputFieldContainerSelector);
   }

   @Override
   public boolean isEnabled(final InputComponentType componentType,
                            final PwBy inputFieldContainerSelector) {
      LogUi.step("Checking if input component of type: '{}' is enabled.", componentType.getType().name());
      return inputComponent(componentType).isEnabled(inputFieldContainerSelector);
   }

   @Override
   public String getErrorMessage(final InputComponentType componentType,
                                 final PwBy inputFieldContainerSelector) {
      LogUi.step("Fetching error message from input component of type: '{}'.", componentType.getType().name());
      return inputComponent(componentType).getErrorMessage(inputFieldContainerSelector);
   }

   @Override
   public void insertion(ComponentType componentType, PwBy selector, Object... values) {
      if (!(componentType instanceof InputComponentType)) {
         throw new IllegalArgumentException("Component type needs to be from: InputComponentType.");
      }
      LogUi.step("Inserting value into component of type: '{}' using locator.", componentType.getType().name());
      insert((InputComponentType) componentType, selector, (String) values[0]);
   }
}
