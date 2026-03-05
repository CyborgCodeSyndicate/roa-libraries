package io.cyborgcode.roa.ui.playwright.components.base;

import com.microsoft.playwright.Page;
import io.cyborgcode.roa.ui.components.base.BaseComponentCore;

public class BaseComponent extends BaseComponentCore<Page> {
   /**
    * Constructs a new {@code BaseComponent} with the specified driver.
    *
    * @param driver The driver/page instance used for UI interactions.
    */
   protected BaseComponent(Page driver) {
      super(driver);
   }
}
