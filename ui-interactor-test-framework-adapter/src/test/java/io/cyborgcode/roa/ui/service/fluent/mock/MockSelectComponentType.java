package io.cyborgcode.roa.ui.service.fluent.mock;

import io.cyborgcode.roa.ui.components.select.SelectComponentType;

public enum MockSelectComponentType implements SelectComponentType {
   DUMMY;

   @Override
   public Enum<?> getType() {
      return this;
   }
}
