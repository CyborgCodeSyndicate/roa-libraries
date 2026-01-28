package io.cyborgcode.roa.ui.service.fluent.mock;

import io.cyborgcode.roa.ui.components.radio.RadioComponentType;

public enum MockRadioComponentType implements RadioComponentType {
   DUMMY;

   @Override
   public Enum<?> getType() {
      return this;
   }
}
