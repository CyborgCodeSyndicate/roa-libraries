package io.cyborgcode.roa.ui.components.accordion.mock;

import io.cyborgcode.roa.ui.components.accordion.AccordionComponentType;

public enum MockAccordionComponentType implements AccordionComponentType {

   DUMMY_ACCORDION;

   @Override
   public Enum<?> getType() {
      return this;
   }
}
