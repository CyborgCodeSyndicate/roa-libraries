package ${package}.ui.types;

import io.cyborgcode.roa.ui.components.select.SelectComponentType;

/**
 * Defines supported select component types.
 */
public enum SelectFieldTypes implements SelectComponentType {

   EXAMPLE_SELECT_TYPE;

   public static final class Data {

      public static final String EXAMPLE_SELECT = "EXAMPLE_SELECT_TYPE";

      private Data() {
      }
   }

   @Override
   public Enum<?> getType() {
      return this;
   }
}
