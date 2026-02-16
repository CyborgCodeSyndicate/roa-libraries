package io.cyborgcode.roa.ui.playwright.components.tab;

import io.cyborgcode.roa.ui.playwright.components.base.AbstractComponentService;
import io.cyborgcode.roa.ui.playwright.components.factory.ComponentFactory;
import io.cyborgcode.roa.ui.playwright.log.LogUi;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

/**
 * Provides service-level operations for interacting with tab components.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public class TabServiceImpl extends AbstractComponentService<TabComponentType, Tab>
      implements TabService {

   public TabServiceImpl(Page page) {
      super(page);
   }

   @Override
   protected Tab createComponent(final TabComponentType componentType) {
      return ComponentFactory.getTabComponent(componentType, page);
   }

   @Override
   public void select(final TabComponentType ct, final Locator c, final String l) {
      LogUi.step("Selecting tab: '{}'", l);
      comp(ct).select(c, l);
   }

   @Override
   public void select(final TabComponentType ct, final Locator c) {
      LogUi.step("Selecting tab in container");
      comp(ct).select(c);
   }

   @Override
   public void select(final TabComponentType ct, final String l) {
      LogUi.step("Selecting tab: '{}'", l);
      comp(ct).select(l);
   }

   @Override
   public void selectBySelector(final TabComponentType ct, final String s) {
      LogUi.step("Selecting tab by selector");
      comp(ct).selectBySelector(s);
   }

   @Override
   public boolean isSelected(final TabComponentType ct, final Locator c, final String l) {
      return comp(ct).isSelected(c, l);
   }

   @Override
   public boolean isSelected(final TabComponentType ct, final Locator c) {
      return comp(ct).isSelected(c);
   }

   @Override
   public boolean isSelected(final TabComponentType ct, final String l) {
      return comp(ct).isSelected(l);
   }

   @Override
   public boolean isSelectedBySelector(final TabComponentType ct, final String s) {
      return comp(ct).isSelectedBySelector(s);
   }

   @Override
   public boolean isEnabled(final TabComponentType ct, final Locator c, final String l) {
      return comp(ct).isEnabled(c, l);
   }

   @Override
   public boolean isEnabled(final TabComponentType ct, final Locator c) {
      return comp(ct).isEnabled(c);
   }

   @Override
   public boolean isEnabled(final TabComponentType ct, final String l) {
      return comp(ct).isEnabled(l);
   }

   @Override
   public boolean isEnabledBySelector(final TabComponentType ct, final String s) {
      return comp(ct).isEnabledBySelector(s);
   }

   private Tab comp(final TabComponentType componentType) {
      return getOrCreateComponent(componentType);
   }
}
