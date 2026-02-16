package io.cyborgcode.roa.ui.playwright.components.radio;

import io.cyborgcode.roa.ui.playwright.components.base.AbstractComponentService;
import io.cyborgcode.roa.ui.playwright.components.base.ComponentType;
import io.cyborgcode.roa.ui.playwright.components.factory.ComponentFactory;
import io.cyborgcode.roa.ui.playwright.log.LogUi;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

/**
 * Provides service-level operations for interacting with radio button components.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public class RadioServiceImpl extends AbstractComponentService<RadioComponentType, Radio>
      implements RadioService {

   public RadioServiceImpl(Page page) {
      super(page);
   }

   @Override
   protected Radio createComponent(final RadioComponentType componentType) {
      return ComponentFactory.getRadioComponent(componentType, page);
   }

   @Override
   public void select(final RadioComponentType ct, final Locator c, final String l) {
      LogUi.step("Selecting radio: '{}'", l);
      comp(ct).select(c, l);
   }

   @Override
   public void select(final RadioComponentType ct, final Locator c) {
      LogUi.step("Selecting radio in container");
      comp(ct).select(c);
   }

   @Override
   public void select(final RadioComponentType ct, final String l) {
      LogUi.step("Selecting radio: '{}'", l);
      comp(ct).select(l);
   }

   @Override
   public void selectBySelector(final RadioComponentType ct, final String s) {
      LogUi.step("Selecting radio by selector");
      comp(ct).selectBySelector(s);
   }

   @Override
   public boolean isSelected(final RadioComponentType ct, final Locator c, final String l) {
      return comp(ct).isSelected(c, l);
   }

   @Override
   public boolean isSelected(final RadioComponentType ct, final Locator c) {
      return comp(ct).isSelected(c);
   }

   @Override
   public boolean isSelected(final RadioComponentType ct, final String l) {
      return comp(ct).isSelected(l);
   }

   @Override
   public boolean isSelectedBySelector(final RadioComponentType ct, final String s) {
      return comp(ct).isSelectedBySelector(s);
   }

   @Override
   public boolean isEnabled(final RadioComponentType ct, final Locator c, final String l) {
      return comp(ct).isEnabled(c, l);
   }

   @Override
   public boolean isEnabled(final RadioComponentType ct, final Locator c) {
      return comp(ct).isEnabled(c);
   }

   @Override
   public boolean isEnabled(final RadioComponentType ct, final String l) {
      return comp(ct).isEnabled(l);
   }

   @Override
   public boolean isEnabledBySelector(final RadioComponentType ct, final String s) {
      return comp(ct).isEnabledBySelector(s);
   }

   @Override
   public void insertion(ComponentType componentType, String selector, Object... values) {
   }

   private Radio comp(final RadioComponentType componentType) {
      return getOrCreateComponent(componentType);
   }
}
