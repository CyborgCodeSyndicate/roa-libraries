package io.cyborgcode.roa.ui.components.link.mock;

import io.cyborgcode.roa.ui.components.link.LinkComponentType;

public enum MockLinkComponentType implements LinkComponentType {
   DUMMY_LINK;

   @Override
   public Enum<?> getType() {
      return this;
   }
}