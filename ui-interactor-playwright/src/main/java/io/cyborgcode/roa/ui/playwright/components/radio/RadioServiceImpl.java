package io.cyborgcode.roa.ui.playwright.components.radio;

import io.cyborgcode.roa.ui.components.base.ComponentType;
import io.cyborgcode.roa.ui.components.radio.RadioComponentType;
import io.cyborgcode.roa.ui.components.radio.RadioServiceImplCore;
import io.cyborgcode.roa.ui.log.LogUi;
import io.cyborgcode.roa.ui.playwright.base.PwBy;
import io.cyborgcode.roa.ui.playwright.components.factory.ComponentFactory;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import java.util.List;

/**
 * Provides service-level operations for interacting with radio button components.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public class RadioServiceImpl extends RadioServiceImplCore<Locator, Radio, Page, PwBy>
      implements RadioService {

   public RadioServiceImpl(Page page) {
      super(page);
   }

   @Override
   protected Radio createComponent(final RadioComponentType componentType) {
      return ComponentFactory.getRadioComponent(componentType, driver);
   }

   @Override
   public void select(final RadioComponentType componentType, final PwBy radioButtonLocator) {
      LogUi.step("Selecting radio by locator");
      radioComponent(componentType).select(radioButtonLocator);
   }

   @Override
   public boolean isEnabled(final RadioComponentType componentType, final PwBy radioButtonLocator) {
      return radioComponent(componentType).isEnabled(radioButtonLocator);
   }

   @Override
   public boolean isSelected(final RadioComponentType componentType, final PwBy radioButtonLocator) {
      return radioComponent(componentType).isSelected(radioButtonLocator);
   }

   @Override
   public boolean isVisible(final RadioComponentType componentType, final PwBy radioButtonLocator) {
      return radioComponent(componentType).isVisible(radioButtonLocator);
   }

   @Override
   public String getSelected(final RadioComponentType componentType, final PwBy containerLocator) {
      return radioComponent(componentType).getSelected(containerLocator);
   }

   @Override
   public List<String> getAll(final RadioComponentType componentType, final PwBy containerLocator) {
      return radioComponent(componentType).getAll(containerLocator);
   }

   @Override
   public void insertion(ComponentType componentType, PwBy selector, Object... values) {
   }
}
