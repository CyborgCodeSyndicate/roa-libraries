package io.cyborgcode.roa.ui.service.fluent.mock;

import io.cyborgcode.roa.ui.components.accordion.AccordionComponentType;

public enum MockAccordionComponentType implements AccordionComponentType {
   DUMMY;

   @Override
   public Enum<?> getType() {
      return this;
   }
}
