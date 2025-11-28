package ${package}.ui_module.ui.types;

import io.cyborgcode.roa.ui.components.alert.AlertComponentType;

/**
 * Defines supported alert component types.
 */
public enum AlertFieldTypes implements AlertComponentType {

   EXAMPLE_ALERT_TYPE;

   public static final class Data {

      public static final String EXAMPLE_ALERT = "EXAMPLE_ALERT_TYPE";

      private Data() {
      }
   }

   @Override
   public Enum<?> getType() {
      return this;
   }
}
