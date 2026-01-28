package io.cyborgcode.roa.ui.components.table.service.mock;

import io.cyborgcode.roa.ui.components.table.base.TableComponentType;

public enum MockTableComponentType implements TableComponentType {
   DUMMY_TABLE;

   @Override
   public Enum<?> getType() {
      return this;
   }
}