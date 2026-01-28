package io.cyborgcode.roa.ui.components.checkbox.mock;

import io.cyborgcode.roa.ui.components.checkbox.CheckboxComponentType;

public enum MockCheckboxComponentType implements CheckboxComponentType {
   DUMMY_CHECKBOX;

   @Override
   public Enum<?> getType() {
      return this;
   }
}