package ${package}.ui.elements;

import io.cyborgcode.roa.ui.components.base.ComponentType;
import io.cyborgcode.roa.ui.components.link.LinkComponentType;
import io.cyborgcode.roa.ui.selenium.LinkUiElement;
import ${package}.ui.types.LinkFieldTypes;
import org.openqa.selenium.By;

/**
 * Example link registry for demonstration purposes.
 */
public enum LinkFields implements LinkUiElement {

   GENERIC_LINK(
         By.cssSelector("a"),
         LinkFieldTypes.EXAMPLE_LINK_TYPE
   ),

   HELP_LINK(
         By.cssSelector("a[href*='help']"),
         LinkFieldTypes.EXAMPLE_LINK_TYPE
   );

   private final By locator;
   private final LinkComponentType componentType;

   LinkFields(By locator, LinkComponentType componentType) {
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
