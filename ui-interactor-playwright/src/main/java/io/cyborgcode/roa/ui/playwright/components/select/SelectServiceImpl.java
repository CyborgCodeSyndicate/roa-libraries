package io.cyborgcode.roa.ui.playwright.components.select;

import io.cyborgcode.roa.ui.playwright.components.base.AbstractComponentService;
import io.cyborgcode.roa.ui.playwright.components.base.ComponentType;
import io.cyborgcode.roa.ui.playwright.components.factory.ComponentFactory;
import io.cyborgcode.roa.ui.playwright.log.LogUi;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

/**
 * Provides service-level operations for interacting with select (dropdown) components.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public class SelectServiceImpl extends AbstractComponentService<SelectComponentType, Select>
      implements SelectService {

   public SelectServiceImpl(Page page) {
      super(page);
   }

   @Override
   protected Select createComponent(final SelectComponentType componentType) {
      return ComponentFactory.getSelectComponent(componentType, page);
   }

   @Override
   public void select(final SelectComponentType ct, final Locator c, final String o) {
      LogUi.step("Selecting option: '{}'", o);
      comp(ct).select(c, o);
   }

   @Override
   public void select(final SelectComponentType ct, final Locator c, final String l, final String o) {
      LogUi.step("Selecting option: '{}' in select: '{}'", o, l);
      comp(ct).select(c, l, o);
   }

   @Override
   public void select(final SelectComponentType ct, final String l, final String o) {
      LogUi.step("Selecting option: '{}' in select: '{}'", o, l);
      comp(ct).select(l, o);
   }

   @Override
   public void selectBySelector(final SelectComponentType ct, final String s, final String o) {
      LogUi.step("Selecting option: '{}' by selector", o);
      comp(ct).selectBySelector(s, o);
   }

   @Override
   public String getSelectedValue(final SelectComponentType ct, final Locator c) {
      return comp(ct).getSelectedValue(c);
   }

   @Override
   public String getSelectedValue(final SelectComponentType ct, final Locator c, final String l) {
      return comp(ct).getSelectedValue(c, l);
   }

   @Override
   public String getSelectedValue(final SelectComponentType ct, final String l) {
      return comp(ct).getSelectedValue(l);
   }

   @Override
   public String getSelectedValueBySelector(final SelectComponentType ct, final String s) {
      return comp(ct).getSelectedValueBySelector(s);
   }

   @Override
   public boolean isEnabled(final SelectComponentType ct, final Locator c, final String l) {
      return comp(ct).isEnabled(c, l);
   }

   @Override
   public boolean isEnabled(final SelectComponentType ct, final Locator c) {
      return comp(ct).isEnabled(c);
   }

   @Override
   public boolean isEnabled(final SelectComponentType ct, final String l) {
      return comp(ct).isEnabled(l);
   }

   @Override
   public boolean isEnabledBySelector(final SelectComponentType ct, final String s) {
      return comp(ct).isEnabledBySelector(s);
   }

   @Override
   public void insertion(ComponentType componentType, String selector, Object... values) {
   }

   private Select comp(final SelectComponentType componentType) {
      return getOrCreateComponent(componentType);
   }
}
