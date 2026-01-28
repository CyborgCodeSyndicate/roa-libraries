package io.cyborgcode.roa.ui.components.factory.mock;

import io.cyborgcode.roa.ui.components.input.InputComponentType;

public enum MockInputComponentType implements InputComponentType {
   DUMMY,
   FAIL,
   NON_EXISTENT;

   @Override
   public Enum<?> getType() {
      return this;
   }
}