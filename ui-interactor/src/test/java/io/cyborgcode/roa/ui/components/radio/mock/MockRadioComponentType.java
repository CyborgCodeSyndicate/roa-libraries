package io.cyborgcode.roa.ui.components.radio.mock;

import io.cyborgcode.roa.ui.components.radio.RadioComponentType;

public enum MockRadioComponentType implements RadioComponentType {
   DUMMY_RADIO;

   @Override
   public Enum<?> getType() {
      return this;
   }
}