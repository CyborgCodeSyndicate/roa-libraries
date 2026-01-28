package io.cyborgcode.roa.ui.selenium;

import io.cyborgcode.roa.ui.components.base.ComponentType;
import io.cyborgcode.roa.ui.components.input.InputService;

/**
 * Represents an Input UI element that integrates with the {@link InputService}.
 *
 * <p>This interface extends {@link UiElement} and provides a default implementation
 * for retrieving the associated component type, allowing interaction with input fields
 * within the UI.
 *
 * <p>This element can be used for performing actions such as inserting text, clearing
 * input fields, and validating their values.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface InputUiElement extends UiElement {

   /**
    * Retrieves the component type associated with this input element.
    *
    * <p>The default implementation returns {@link InputService#DEFAULT_TYPE},
    * ensuring that the input element is recognized as part of the input service.
    *
    * @param <T> The component type.
    * @return The component type associated with this input element.
    */
   @Override
   default <T extends ComponentType> T componentType() {
      return (T) InputService.DEFAULT_TYPE;
   }

}
