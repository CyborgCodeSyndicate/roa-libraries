package io.cyborgcode.roa.ui.playwright.components.input;

import com.microsoft.playwright.Page;
import io.cyborgcode.roa.ui.components.base.ComponentType;
import io.cyborgcode.roa.ui.components.input.InputComponentType;
import io.cyborgcode.roa.ui.components.input.InputServiceImplCore;
import io.cyborgcode.roa.ui.log.LogUi;
import io.cyborgcode.roa.ui.playwright.base.PwBy;
import io.cyborgcode.roa.ui.playwright.base.PwElement;
import io.cyborgcode.roa.ui.playwright.components.factory.ComponentFactory;

/**
 * Provides service-level operations for interacting with input components.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public class InputServiceImpl extends InputServiceImplCore<PwElement, Input, Page, PwBy>
      implements InputService {

   public InputServiceImpl(Page page) {
      super(page);
   }

   @Override
   protected Input createComponent(final InputComponentType componentType) {
      return ComponentFactory.getInputComponent(componentType, driver);
   }

   @Override
   public void insertion(ComponentType componentType, PwBy selector, Object... values) {
      if (!(componentType instanceof InputComponentType)) {
         throw new IllegalArgumentException("Component type needs to be from: InputComponentType.");
      }
      LogUi.step("Inserting value into component of type: '{}' using locator.", componentType.getType().name());
      insert((InputComponentType) componentType, selector, (String) values[0]);
   }
}
