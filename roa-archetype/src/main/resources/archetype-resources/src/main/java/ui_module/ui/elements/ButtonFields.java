package ${package}.ui_module.ui.elements;

import io.cyborgcode.roa.ui.components.button.ButtonComponentType;
import io.cyborgcode.roa.ui.selenium.ButtonUiElement;
import io.cyborgcode.roa.ui.selenium.smart.SmartWebDriver;
import ${package}.ui_module.ui.types.ButtonFieldTypes;
import org.openqa.selenium.By;
import java.util.function.Consumer;

/**
 * Example button elements for demonstration and quick-start usage.
 */
public enum ButtonFields implements ButtonUiElement {

   GENERIC_BUTTON(
         By.cssSelector("button"),
         ButtonFieldTypes.EXAMPLE_BUTTON_TYPE
   ),

   SUBMIT_BUTTON(
         By.cssSelector("button[type='submit']"),
         ButtonFieldTypes.EXAMPLE_BUTTON_TYPE
   );

   private final By locator;
   private final ButtonComponentType componentType;

   ButtonFields(By locator, ButtonComponentType componentType) {
      this.locator = locator;
      this.componentType = componentType;
   }

   @Override
   public By locator() {
      return locator;
   }

   @Override
   public ButtonComponentType componentType() {
      return componentType;
   }

   @Override
   public Enum<?> enumImpl() {
      return this;
   }
}
