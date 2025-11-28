package ${package}.ui_module.ui.types;

import io.cyborgcode.roa.ui.components.link.LinkComponentType;

/**
 * Defines supported link component types.
 */
public enum LinkFieldTypes implements LinkComponentType {

   EXAMPLE_LINK_TYPE;

   public static final class Data {

      public static final String EXAMPLE_LINK = "EXAMPLE_LINK_TYPE";

      private Data() {}
   }

   @Override
   public Enum<?> getType() {
      return this;
   }
}
