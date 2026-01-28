package io.cyborgcode.roa.ui.components.select.mock;

import io.cyborgcode.roa.ui.components.select.SelectComponentType;

public enum MockSelectComponentType implements SelectComponentType {
   DUMMY_SELECT;

   @Override
   public Enum<?> getType() {
      return this;
   }
}