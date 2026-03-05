package io.cyborgcode.roa.ui.elements;

import io.cyborgcode.roa.ui.components.accordion.AccordionServiceCore;
import io.cyborgcode.roa.ui.components.base.ComponentType;


public interface AccordionUiElementCore<L, D> extends UiElementCore<L, D> {

   @Override
   default <T extends ComponentType> T componentType() {
      return (T) AccordionServiceCore.DEFAULT_TYPE;
   }

}
