package ${package}.ui_module.ui.types;

import io.cyborgcode.roa.ui.components.list.ItemListComponentType;

/**
 * Defines supported list component types.
 */
public enum ListFieldTypes implements ItemListComponentType {

   EXAMPLE_LIST_TYPE;

   public static final class Data {

      public static final String EXAMPLE_LIST = "EXAMPLE_LIST_TYPE";

      private Data() {}
   }

   @Override
   public Enum<?> getType() {
      return this;
   }
}
