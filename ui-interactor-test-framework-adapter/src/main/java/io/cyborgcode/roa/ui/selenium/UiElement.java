package io.cyborgcode.roa.ui.selenium;

import io.cyborgcode.pandora.annotation.Pandora;
import io.cyborgcode.pandora.annotation.PandoraOptions;
import io.cyborgcode.pandora.model.CreationKind;
import io.cyborgcode.roa.ui.components.base.ComponentType;
import io.cyborgcode.roa.ui.selenium.smart.SmartWebDriver;
import java.util.function.Consumer;
import org.openqa.selenium.By;

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
@Pandora(
      description = "Base interface modeling a UI element: locator, component type, enum impl, and "
            + "pre/post interaction hooks.",
      tags = {"ui", "ui-element", "interface"},
      creation = CreationKind.AUTO
)
@PandoraOptions(
      exampleFilesPath = "ai/roa/ui-usage.json",
      meta = {
         @PandoraOptions.Meta(key = "type", value = "ui-element-interface"),
         @PandoraOptions.Meta(key = "scope", value = "type")
      }
)
@SuppressWarnings("java:S1452")
public interface UiElement {

   /**
    * Retrieves the locator used to identify this UI element on the web page.
    *
    * @return The {@link By} locator associated with this UI element.
    */
   @Pandora(
         description = "Selenium By locator for finding the UI element on the page."
   )
   By locator();

   /**
    * Retrieves the component type of this UI element.
    *
    * <p>The specific component type is determined by the implementing class.
    *
    * @param <T> The type of the component.
    * @return The component type associated with this UI element.
    */
   <T extends ComponentType> T componentType();

   /**
    * Retrieves the enum representation of this UI element.
    *
    * <p>This method is primarily useful for elements that are implemented as enums.
    *
    * @return The enum instance representing this UI element.
    */
   @Pandora(
         description = "Enum instance representing this element (useful when elements are modeled as enums)."
   )
   Enum<?> enumImpl();

   /**
    * Provides a hook for actions to be executed before interacting with this UI element.
    *
    * <p>By default, this method does nothing, but implementing classes can override it
    * to perform actions such as waiting for visibility, logging, or additional validations.
    *
    * @return A {@link Consumer} that takes a {@link SmartWebDriver} and executes actions before interaction.
    */
   @Pandora(
         description = "Optional pre-interaction hook to run with SmartWebDriver before acting on the element."
   )
   default Consumer<SmartWebDriver> before() {
      return smartWebDriver -> {
      };
   }

   /**
    * Provides a hook for actions to be executed after interacting with this UI element.
    *
    * <p>By default, this method does nothing, but implementing classes can override it to perform actions such as
    * logging, waiting for an expected state, or triggering additional validations.
    *
    * @return A {@link Consumer} that takes a {@link SmartWebDriver} and executes actions after interaction.
    */
   @Pandora(
         description = "Optional post-interaction hook to run with SmartWebDriver after acting on the element."
   )
   default Consumer<SmartWebDriver> after() {
      return smartWebDriver -> {
      };
   }

}
