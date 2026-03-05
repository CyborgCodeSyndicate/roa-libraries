package io.cyborgcode.roa.ui.elements;

import io.cyborgcode.roa.ui.components.base.ComponentType;
import io.cyborgcode.roa.ui.components.link.LinkServiceCore;


public interface LinkUiElementCore<L, D> extends UiElementCore<L, D> {

   @Override
   default <T extends ComponentType> T componentType() {
      return (T) LinkServiceCore.DEFAULT_TYPE;
   }

}
