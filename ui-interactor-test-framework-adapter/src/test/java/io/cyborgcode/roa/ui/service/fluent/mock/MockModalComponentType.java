package io.cyborgcode.roa.ui.service.fluent.mock;

import io.cyborgcode.roa.ui.components.modal.ModalComponentType;

public enum MockModalComponentType implements ModalComponentType {
   DUMMY;

   @Override
   public Enum<?> getType() {
      return this;
   }
}
