package ${package}.ui_module.ui.types;

import io.cyborgcode.roa.ui.components.button.ButtonComponentType;

/**
 * Defines supported button component types for the project.
 */
public enum ButtonFieldTypes implements ButtonComponentType {

   EXAMPLE_BUTTON_TYPE;

   /**
    * String identifiers used by @ImplementationOfType annotations.
    */
   public static final class Data {

      public static final String EXAMPLE_BUTTON = "EXAMPLE_BUTTON_TYPE";

      private Data() {
      }
   }

   @Override
   public Enum<?> getType() {
      return this;
   }
}
