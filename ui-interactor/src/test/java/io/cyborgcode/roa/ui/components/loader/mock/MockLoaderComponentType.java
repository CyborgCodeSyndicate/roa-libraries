package io.cyborgcode.roa.ui.components.loader.mock;

import io.cyborgcode.roa.ui.components.loader.LoaderComponentType;

public enum MockLoaderComponentType implements LoaderComponentType {
   DUMMY_LOADER;

   @Override
   public Enum<?> getType() {
      return this;
   }
}