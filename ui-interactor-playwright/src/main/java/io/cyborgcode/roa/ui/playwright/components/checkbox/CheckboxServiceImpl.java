package io.cyborgcode.roa.ui.playwright.components.checkbox;

import io.cyborgcode.roa.ui.playwright.components.base.AbstractComponentService;
import io.cyborgcode.roa.ui.playwright.components.base.ComponentType;
import io.cyborgcode.roa.ui.playwright.components.factory.ComponentFactory;
import io.cyborgcode.roa.ui.playwright.log.LogUi;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

/**
 * Provides service-level operations for interacting with checkbox components.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public class CheckboxServiceImpl extends AbstractComponentService<CheckboxComponentType, Checkbox>
      implements CheckboxService {

   public CheckboxServiceImpl(Page page) {
      super(page);
   }

   @Override
   protected Checkbox createComponent(final CheckboxComponentType componentType) {
      return ComponentFactory.getCheckBoxComponent(componentType, page);
   }

   @Override
   public void check(final CheckboxComponentType ct, final Locator c, final String l) {
      LogUi.step("Checking checkbox: '{}'", l);
      comp(ct).check(c, l);
   }

   @Override
   public void check(final CheckboxComponentType ct, final Locator c) {
      LogUi.step("Checking checkbox in container");
      comp(ct).check(c);
   }

   @Override
   public void check(final CheckboxComponentType ct, final String l) {
      LogUi.step("Checking checkbox: '{}'", l);
      comp(ct).check(l);
   }

   @Override
   public void checkBySelector(final CheckboxComponentType ct, final String s) {
      LogUi.step("Checking checkbox by selector");
      comp(ct).checkBySelector(s);
   }

   @Override
   public void uncheck(final CheckboxComponentType ct, final Locator c, final String l) {
      LogUi.step("Unchecking checkbox: '{}'", l);
      comp(ct).uncheck(c, l);
   }

   @Override
   public void uncheck(final CheckboxComponentType ct, final Locator c) {
      LogUi.step("Unchecking checkbox in container");
      comp(ct).uncheck(c);
   }

   @Override
   public void uncheck(final CheckboxComponentType ct, final String l) {
      LogUi.step("Unchecking checkbox: '{}'", l);
      comp(ct).uncheck(l);
   }

   @Override
   public void uncheckBySelector(final CheckboxComponentType ct, final String s) {
      LogUi.step("Unchecking checkbox by selector");
      comp(ct).uncheckBySelector(s);
   }

   @Override
   public boolean isChecked(final CheckboxComponentType ct, final Locator c, final String l) {
      return comp(ct).isChecked(c, l);
   }

   @Override
   public boolean isChecked(final CheckboxComponentType ct, final Locator c) {
      return comp(ct).isChecked(c);
   }

   @Override
   public boolean isChecked(final CheckboxComponentType ct, final String l) {
      return comp(ct).isChecked(l);
   }

   @Override
   public boolean isCheckedBySelector(final CheckboxComponentType ct, final String s) {
      return comp(ct).isCheckedBySelector(s);
   }

   @Override
   public boolean isEnabled(final CheckboxComponentType ct, final Locator c, final String l) {
      return comp(ct).isEnabled(c, l);
   }

   @Override
   public boolean isEnabled(final CheckboxComponentType ct, final Locator c) {
      return comp(ct).isEnabled(c);
   }

   @Override
   public boolean isEnabled(final CheckboxComponentType ct, final String l) {
      return comp(ct).isEnabled(l);
   }

   @Override
   public boolean isEnabledBySelector(final CheckboxComponentType ct, final String s) {
      return comp(ct).isEnabledBySelector(s);
   }

   @Override
   public void insertion(ComponentType componentType, String selector, Object... values) {
   }

   private Checkbox comp(final CheckboxComponentType componentType) {
      return getOrCreateComponent(componentType);
   }
}
