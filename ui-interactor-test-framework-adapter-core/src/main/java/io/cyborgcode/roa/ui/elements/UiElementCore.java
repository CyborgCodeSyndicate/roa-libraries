package io.cyborgcode.roa.ui.elements;

import io.cyborgcode.roa.ui.components.base.ComponentType;
import java.util.function.Consumer;

/**
 * Represents a base interface for UI elements in the framework.
 *
 * <p>This interface defines common behavior for all UI elements, including
 * locating elements, identifying component types, and executing actions
 * before and after interaction. It serves as the foundation for all UI
 * element interfaces (e.g., buttons, checkboxes, inputs, etc.).
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
@SuppressWarnings("java:S1452")
public interface UiElementCore<L, D> {

   /**
    * Retrieves the locator used to identify this UI element on the web page.
    *
    * @return The {@link L} locator associated with this UI element.
    */
   L locator();

   /**
    * Retrieves the component type of this UI element.
    *
    * <p>The specific component type is determined by the implementing class.
    *
    * @param <C> The type of the component.
    * @return The component type associated with this UI element.
    */
   <C extends ComponentType> C componentType();

   /**
    * Retrieves the enum representation of this UI element.
    *
    * <p>This method is primarily useful for elements that are implemented as enums.
    *
    * @return The enum instance representing this UI element.
    */
   Enum<?> enumImpl();

   /**
    * Provides a hook for actions to be executed before interacting with this UI element.
    *
    * <p>By default, this method does nothing, but implementing classes can override it
    * to perform actions such as waiting for visibility, logging, or additional validations.
    *
    * @return A {@link Consumer} that takes a {@link D} and executes actions before interaction.
    */
   default Consumer<D> before() {
      return driver -> {
      };
   }

   /**
    * Provides a hook for actions to be executed after interacting with this UI element.
    *
    * <p>By default, this method does nothing, but implementing classes can override it to perform actions such as
    * logging, waiting for an expected state, or triggering additional validations.
    *
    * @return A {@link Consumer} that takes a {@link D} and executes actions after interaction.
    */
   default Consumer<D> after() {
      return driver -> {
      };
   }

}
