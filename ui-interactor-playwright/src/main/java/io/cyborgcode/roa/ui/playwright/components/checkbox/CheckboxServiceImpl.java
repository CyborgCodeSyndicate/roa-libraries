package io.cyborgcode.roa.ui.playwright.components.checkbox;

import io.cyborgcode.roa.ui.components.base.ComponentType;
import io.cyborgcode.roa.ui.components.checkbox.CheckboxComponentType;
import io.cyborgcode.roa.ui.components.checkbox.CheckboxServiceImplCore;
import io.cyborgcode.roa.ui.log.LogUi;
import io.cyborgcode.roa.ui.playwright.base.PwBy;
import io.cyborgcode.roa.ui.playwright.components.factory.ComponentFactory;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import java.util.List;

/**
 * Provides service-level operations for interacting with checkbox components.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public class CheckboxServiceImpl extends CheckboxServiceImplCore<Locator, Checkbox, Page, PwBy>
      implements CheckboxService {

   public CheckboxServiceImpl(Page page) {
      super(page);
   }

   @Override
   protected Checkbox createComponent(final CheckboxComponentType componentType) {
      return ComponentFactory.getCheckBoxComponent(componentType, driver);
   }

   public void select(final CheckboxComponentType componentType, final PwBy... checkBoxLocator) {
      LogUi.step("Selecting checkbox by locator");
      checkboxComponent(componentType).select(checkBoxLocator);
   }

   public void deSelect(final CheckboxComponentType componentType, final PwBy... checkBoxLocator) {
      LogUi.step("Deselecting checkbox by locator");
      checkboxComponent(componentType).deSelect(checkBoxLocator);
   }

   public boolean areSelected(final CheckboxComponentType componentType, final PwBy... checkBoxLocator) {
      return checkboxComponent(componentType).areSelected(checkBoxLocator);
   }

   public boolean areEnabled(final CheckboxComponentType componentType, final PwBy... checkBoxLocator) {
      return checkboxComponent(componentType).areEnabled(checkBoxLocator);
   }

   public List<String> getSelected(final CheckboxComponentType componentType, final PwBy containerLocator) {
      return checkboxComponent(componentType).getSelected(containerLocator);
   }

   public List<String> getAll(final CheckboxComponentType componentType, final PwBy containerLocator) {
      return checkboxComponent(componentType).getAll(containerLocator);
   }
   
}
