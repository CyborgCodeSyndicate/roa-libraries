package ${package}.ui_module.ui.elements;

import io.cyborgcode.roa.ui.components.base.ComponentType;
import io.cyborgcode.roa.ui.components.input.InputComponentType;
import io.cyborgcode.roa.ui.selenium.InputUiElement;
import ${package}.ui_module.ui.types.InputFieldTypes;
import org.openqa.selenium.By;

/**
 * Example input field registry for demonstration purposes.
 */
public enum InputFields implements InputUiElement {

   GENERIC_INPUT(
         By.cssSelector("input"),
         InputFieldTypes.EXAMPLE_INPUT_TYPE
   ),

   USERNAME_INPUT(
         By.cssSelector("input[name='username']"),
         InputFieldTypes.EXAMPLE_INPUT_TYPE
   );

   private final By locator;
   private final InputComponentType componentType;

   InputFields(By locator, InputComponentType componentType) {
      this.locator = locator;
      this.componentType = componentType;
   }

   @Override
   public By locator() {
      return locator;
   }

   @Override
   public <T extends ComponentType> T componentType() {
      return (T) componentType;
   }

   @Override
   public Enum<?> enumImpl() {
      return this;
   }
}
