package io.cyborgcode.roa.ui.components.list.mock;

import io.cyborgcode.roa.ui.components.list.ItemListComponentType;

public enum MockItemListComponentType implements ItemListComponentType {
   DUMMY_LIST;

   @Override
   public Enum<?> getType() {
      return this;
   }
}