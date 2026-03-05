package io.cyborgcode.roa.ui.elements;

import io.cyborgcode.roa.ui.components.base.ComponentType;
import io.cyborgcode.roa.ui.components.button.ButtonServiceCore;


public interface ButtonUiElementCore<L, D> extends UiElementCore<L, D> {

   @Override
   default <T extends ComponentType> T componentType() {
      return (T) ButtonServiceCore.DEFAULT_TYPE;
   }

}
