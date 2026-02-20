package io.cyborgcode.roa.ui.playwright.components.alert;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.cyborgcode.roa.ui.components.alert.AlertComponentType;
import io.cyborgcode.roa.ui.components.alert.AlertServiceImplCore;
import io.cyborgcode.roa.ui.playwright.base.PwBy;
import io.cyborgcode.roa.ui.playwright.components.factory.ComponentFactory;

/**
 * Provides service-level operations for interacting with alert components.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public class AlertServiceImpl extends AlertServiceImplCore<Locator, Alert, Page>
      implements AlertService {

   public AlertServiceImpl(Page page) {
      super(page);
   }

   @Override
   protected Alert createComponent(final AlertComponentType alertComponentType) {
      return ComponentFactory.getAlertComponent(alertComponentType, driver);
   }

   public String getValue(final AlertComponentType alertComponentType, final PwBy containerLocator) {
      return alertComponent(alertComponentType).getValue(containerLocator);
   }

   public boolean isVisible(final AlertComponentType alertComponentType, final PwBy containerLocator) {
      return alertComponent(alertComponentType).isVisible(containerLocator);
   }

}
