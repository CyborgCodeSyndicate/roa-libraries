package ${package}.ui_module.ui.elements;

import io.cyborgcode.roa.ui.components.alert.AlertComponentType;
import io.cyborgcode.roa.ui.components.base.ComponentType;
import io.cyborgcode.roa.ui.selenium.AlertUiElement;
import ${package}.ui_module.ui.types.AlertFieldTypes;
import org.openqa.selenium.By;

/**
 * Example alert registry for demonstration purposes.
 */
public enum AlertFields implements AlertUiElement {

   GENERIC_ALERT(
         By.cssSelector(".alert"),
         AlertFieldTypes.EXAMPLE_ALERT_TYPE
   ),

   SUCCESS_ALERT(
         By.cssSelector(".alert.alert-success"),
         AlertFieldTypes.EXAMPLE_ALERT_TYPE
   );

   private final By locator;
   private final AlertComponentType componentType;

   AlertFields(By locator, AlertComponentType componentType) {
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
