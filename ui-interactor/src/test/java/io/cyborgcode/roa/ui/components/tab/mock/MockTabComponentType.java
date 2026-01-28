package io.cyborgcode.roa.ui.components.tab.mock;

import io.cyborgcode.roa.ui.components.tab.TabComponentType;

public enum MockTabComponentType implements TabComponentType {
   DUMMY_TAB;

   @Override
   public Enum<?> getType() {
      return this;
   }
}