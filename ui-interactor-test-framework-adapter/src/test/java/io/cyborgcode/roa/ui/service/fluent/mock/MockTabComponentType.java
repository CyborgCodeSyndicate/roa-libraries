package io.cyborgcode.roa.ui.service.fluent.mock;

import io.cyborgcode.roa.ui.components.tab.TabComponentType;

public enum MockTabComponentType implements TabComponentType {
   DUMMY;

   @Override
   public Enum<?> getType() {
      return this;
   }
}