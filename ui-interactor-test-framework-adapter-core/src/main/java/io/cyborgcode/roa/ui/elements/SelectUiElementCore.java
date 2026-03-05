package io.cyborgcode.roa.ui.elements;

import io.cyborgcode.roa.ui.components.base.ComponentType;
import io.cyborgcode.roa.ui.components.select.SelectServiceCore;


public interface SelectUiElementCore<L, D> extends UiElementCore<L, D> {

   @Override
   default <T extends ComponentType> T componentType() {
      return (T) SelectServiceCore.DEFAULT_TYPE;
   }

}
