package io.cyborgcode.roa.ui.components.toggle.mock;

import io.cyborgcode.roa.ui.components.toggle.ToggleComponentType;

public enum MockToggleComponentType implements ToggleComponentType {
   DUMMY_TOGGLE;

   @Override
   public Enum<?> getType() {
      return this;
   }
}