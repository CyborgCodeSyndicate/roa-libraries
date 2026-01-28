package io.cyborgcode.roa.ui.components.input.mock;

import io.cyborgcode.roa.ui.components.input.InputComponentType;

public enum MockInputComponentType implements InputComponentType {
   DUMMY_INPUT;

   @Override
   public Enum<?> getType() {
      return this;
   }
}