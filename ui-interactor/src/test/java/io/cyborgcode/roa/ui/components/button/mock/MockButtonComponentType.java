package io.cyborgcode.roa.ui.components.button.mock;

import io.cyborgcode.roa.ui.components.button.ButtonComponentType;

public enum MockButtonComponentType implements ButtonComponentType {
   DUMMY_BUTTON;

   @Override
   public Enum<?> getType() {
      return this;
   }
}