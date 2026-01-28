package io.cyborgcode.roa.ui.service.fluent.mock;

import io.cyborgcode.roa.ui.components.input.InputComponentType;

public enum MockInputComponentType implements InputComponentType {
   DUMMY;

   @Override
   public Enum<?> getType() {
      return this;
   }
}