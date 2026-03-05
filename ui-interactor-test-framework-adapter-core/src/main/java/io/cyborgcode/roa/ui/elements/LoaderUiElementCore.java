package io.cyborgcode.roa.ui.elements;

import io.cyborgcode.roa.ui.components.base.ComponentType;
import io.cyborgcode.roa.ui.components.loader.LoaderServiceCore;


public interface LoaderUiElementCore<L, D> extends UiElementCore<L, D> {

   @Override
   default <T extends ComponentType> T componentType() {
      return (T) LoaderServiceCore.DEFAULT_TYPE;
   }

}
