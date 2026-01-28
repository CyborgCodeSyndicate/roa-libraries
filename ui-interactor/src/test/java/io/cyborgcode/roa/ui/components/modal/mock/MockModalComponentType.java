package io.cyborgcode.roa.ui.components.modal.mock;

import io.cyborgcode.roa.ui.components.modal.ModalComponentType;

public enum MockModalComponentType implements ModalComponentType {
   DUMMY_MODAL;

   @Override
   public Enum<?> getType() {
      return this;
   }
}