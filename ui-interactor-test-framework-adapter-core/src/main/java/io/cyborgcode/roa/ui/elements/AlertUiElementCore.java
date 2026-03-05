package io.cyborgcode.roa.ui.elements;

import io.cyborgcode.roa.ui.components.alert.AlertServiceCore;
import io.cyborgcode.roa.ui.components.base.ComponentType;


public interface AlertUiElementCore<L, D> extends UiElementCore<L, D> {

   @Override
   default <T extends ComponentType> T componentType() {
      return (T) AlertServiceCore.DEFAULT_TYPE;
   }

}
