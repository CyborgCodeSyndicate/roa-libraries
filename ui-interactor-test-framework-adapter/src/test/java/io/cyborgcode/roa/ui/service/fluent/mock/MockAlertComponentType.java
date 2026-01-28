package io.cyborgcode.roa.ui.service.fluent.mock;

import io.cyborgcode.roa.ui.components.alert.AlertComponentType;

public enum MockAlertComponentType implements AlertComponentType {

   DUMMY;

   @Override
   public Enum<?> getType() {
      return this;
   }
}
