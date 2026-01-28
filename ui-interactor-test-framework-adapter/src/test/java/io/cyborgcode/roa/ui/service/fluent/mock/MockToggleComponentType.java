package io.cyborgcode.roa.ui.service.fluent.mock;

import io.cyborgcode.roa.ui.components.toggle.ToggleComponentType;

public enum MockToggleComponentType implements ToggleComponentType {
   DUMMY;

   @Override
   public Enum<?> getType() {
      return this;
   }
}