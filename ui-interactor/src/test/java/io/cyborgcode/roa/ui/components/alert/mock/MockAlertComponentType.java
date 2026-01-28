package io.cyborgcode.roa.ui.components.alert.mock;

import io.cyborgcode.roa.ui.components.alert.AlertComponentType;

public enum MockAlertComponentType implements AlertComponentType {

   DUMMY_ALERT;

   @Override
   public Enum<?> getType() {
      return this;
   }
}