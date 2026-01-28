package io.cyborgcode.roa.ui.service.fluent.mock;

import io.cyborgcode.roa.ui.components.button.ButtonComponentType;

public enum MockButtonComponentType implements ButtonComponentType {
   DUMMY;

   @Override
   public Enum<?> getType() {
      return this;
   }
}