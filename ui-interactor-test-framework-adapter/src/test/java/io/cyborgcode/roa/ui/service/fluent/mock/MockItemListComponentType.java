package io.cyborgcode.roa.ui.service.fluent.mock;

import io.cyborgcode.roa.ui.components.list.ItemListComponentType;

public enum MockItemListComponentType implements ItemListComponentType {
   DUMMY;

   @Override
   public Enum<?> getType() {
      return this;
   }
}