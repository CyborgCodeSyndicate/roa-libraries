package io.cyborgcode.roa.ui.playwright.components.alert;

import io.cyborgcode.roa.ui.playwright.components.base.AbstractComponentService;
import io.cyborgcode.roa.ui.playwright.components.factory.ComponentFactory;
import io.cyborgcode.roa.ui.playwright.log.LogUi;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

/**
 * Provides service-level operations for interacting with alert components.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public class AlertServiceImpl extends AbstractComponentService<AlertComponentType, Alert>
      implements AlertService {

   public AlertServiceImpl(Page page) {
      super(page);
   }

   @Override
   protected Alert createComponent(final AlertComponentType componentType) {
      return ComponentFactory.getAlertComponent(componentType, page);
   }

   @Override
   public String getMessage(final AlertComponentType ct, final Locator c) {
      return comp(ct).getMessage(c);
   }

   @Override
   public String getMessage(final AlertComponentType ct, final String l) {
      return comp(ct).getMessage(l);
   }

   @Override
   public String getMessageBySelector(final AlertComponentType ct, final String s) {
      return comp(ct).getMessageBySelector(s);
   }

   @Override
   public boolean isVisible(final AlertComponentType ct, final Locator c) {
      return comp(ct).isVisible(c);
   }

   @Override
   public boolean isVisible(final AlertComponentType ct, final String l) {
      return comp(ct).isVisible(l);
   }

   @Override
   public boolean isVisibleBySelector(final AlertComponentType ct, final String s) {
      return comp(ct).isVisibleBySelector(s);
   }

   @Override
   public void dismiss(final AlertComponentType ct, final Locator c) {
      LogUi.step("Dismissing alert");
      comp(ct).dismiss(c);
   }

   @Override
   public void dismiss(final AlertComponentType ct, final String l) {
      LogUi.step("Dismissing alert: '{}'", l);
      comp(ct).dismiss(l);
   }

   @Override
   public void dismissBySelector(final AlertComponentType ct, final String s) {
      LogUi.step("Dismissing alert by selector");
      comp(ct).dismissBySelector(s);
   }

   @Override
   public String getType(final AlertComponentType ct, final Locator c) {
      return comp(ct).getType(c);
   }

   @Override
   public String getType(final AlertComponentType ct, final String l) {
      return comp(ct).getType(l);
   }

   @Override
   public String getTypeBySelector(final AlertComponentType ct, final String s) {
      return comp(ct).getTypeBySelector(s);
   }

   private Alert comp(final AlertComponentType componentType) {
      return getOrCreateComponent(componentType);
   }
}
