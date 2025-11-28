package ${package}.ui_module.ui.types;

import io.cyborgcode.roa.ui.components.radio.RadioComponentType;

/**
 * Defines supported radio component types.
 */
public enum RadioFieldTypes implements RadioComponentType {

   EXAMPLE_RADIO_TYPE;

   public static final class Data {

      public static final String EXAMPLE_RADIO = "EXAMPLE_RADIO_TYPE";

      private Data() {
      }
   }

   @Override
   public Enum<?> getType() {
      return this;
   }
}
