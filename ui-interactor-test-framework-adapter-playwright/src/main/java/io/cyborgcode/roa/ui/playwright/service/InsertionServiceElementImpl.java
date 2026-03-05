package io.cyborgcode.roa.ui.playwright.service;

import com.microsoft.playwright.Page;
import io.cyborgcode.roa.ui.insertion.InsertionServiceRegistry;
import io.cyborgcode.roa.ui.playwright.base.PwBy;
import io.cyborgcode.roa.ui.service.InsertionServiceElementImplCore;

/**
 * Playwright-specific implementation of the {@link InsertionServiceElementImplCore}.
 *
 * <p>This class binds the core insertion service to Playwright types:
 * {@link Page} as the driver and {@link PwBy} as the locator.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public class InsertionServiceElementImpl extends InsertionServiceElementImplCore<Page, PwBy> {

   /**
    * Constructs an {@code InsertionServiceElementImpl} instance.
    *
    * @param serviceRegistry The registry managing available insertion services.
    * @param page            The Playwright Page instance for element interaction.
    */
   public InsertionServiceElementImpl(final InsertionServiceRegistry<PwBy> serviceRegistry,
                                      final Page page) {
      super(serviceRegistry, page);
   }

}
