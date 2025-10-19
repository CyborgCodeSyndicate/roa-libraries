package io.cyborgcode.roa.ui.service.fluent.mock;

import io.cyborgcode.roa.ui.components.link.LinkComponentType;

public enum MockLinkComponentType implements LinkComponentType {
   DUMMY;

   @Override
   public Enum<?> getType() {
      return this;
   }
}