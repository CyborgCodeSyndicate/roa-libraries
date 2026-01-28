package io.cyborgcode.roa.ui.service.fluent.mock;

import io.cyborgcode.roa.ui.components.checkbox.CheckboxComponentType;

public enum MockCheckboxComponentType implements CheckboxComponentType {
   DUMMY;

   @Override
   public Enum<?> getType() {
      return this;
   }
}