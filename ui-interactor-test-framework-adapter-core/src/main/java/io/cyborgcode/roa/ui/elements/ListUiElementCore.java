package io.cyborgcode.roa.ui.elements;

import io.cyborgcode.roa.ui.components.base.ComponentType;
import io.cyborgcode.roa.ui.components.list.ItemListServiceCore;


public interface ListUiElementCore<L, D> extends UiElementCore<L, D> {

   @Override
   default <T extends ComponentType> T componentType() {
      return (T) ItemListServiceCore.DEFAULT_TYPE;
   }

}
