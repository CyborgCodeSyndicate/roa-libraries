package io.cyborgcode.roa.ui.playwright.components.toggle;

import io.cyborgcode.roa.ui.playwright.components.base.AbstractComponentService;
import io.cyborgcode.roa.ui.playwright.components.factory.ComponentFactory;
import io.cyborgcode.roa.ui.playwright.log.LogUi;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

/**
 * Provides service-level operations for interacting with toggle components.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public class ToggleServiceImpl extends AbstractComponentService<ToggleComponentType, Toggle>
      implements ToggleService {

   public ToggleServiceImpl(Page page) {
      super(page);
   }

   @Override
   protected Toggle createComponent(final ToggleComponentType componentType) {
      return ComponentFactory.getToggleComponent(componentType, page);
   }

   @Override
   public void toggle(final ToggleComponentType ct, final Locator c, final String l) {
      LogUi.step("Toggling: '{}'", l);
      comp(ct).toggle(c, l);
   }

   @Override
   public void toggle(final ToggleComponentType ct, final Locator c) {
      LogUi.step("Toggling in container");
      comp(ct).toggle(c);
   }

   @Override
   public void toggle(final ToggleComponentType ct, final String l) {
      LogUi.step("Toggling: '{}'", l);
      comp(ct).toggle(l);
   }

   @Override
   public void toggleBySelector(final ToggleComponentType ct, final String s) {
      LogUi.step("Toggling by selector");
      comp(ct).toggleBySelector(s);
   }

   @Override
   public boolean isOn(final ToggleComponentType ct, final Locator c, final String l) {
      return comp(ct).isOn(c, l);
   }

   @Override
   public boolean isOn(final ToggleComponentType ct, final Locator c) {
      return comp(ct).isOn(c);
   }

   @Override
   public boolean isOn(final ToggleComponentType ct, final String l) {
      return comp(ct).isOn(l);
   }

   @Override
   public boolean isOnBySelector(final ToggleComponentType ct, final String s) {
      return comp(ct).isOnBySelector(s);
   }

   @Override
   public boolean isEnabled(final ToggleComponentType ct, final Locator c, final String l) {
      return comp(ct).isEnabled(c, l);
   }

   @Override
   public boolean isEnabled(final ToggleComponentType ct, final Locator c) {
      return comp(ct).isEnabled(c);
   }

   @Override
   public boolean isEnabled(final ToggleComponentType ct, final String l) {
      return comp(ct).isEnabled(l);
   }

   @Override
   public boolean isEnabledBySelector(final ToggleComponentType ct, final String s) {
      return comp(ct).isEnabledBySelector(s);
   }

   private Toggle comp(final ToggleComponentType componentType) {
      return getOrCreateComponent(componentType);
   }
}
