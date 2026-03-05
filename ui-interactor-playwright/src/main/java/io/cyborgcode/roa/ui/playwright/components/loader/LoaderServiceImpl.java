package io.cyborgcode.roa.ui.playwright.components.loader;

import io.cyborgcode.roa.ui.components.loader.LoaderComponentType;
import io.cyborgcode.roa.ui.components.loader.LoaderServiceImplCore;
import io.cyborgcode.roa.ui.playwright.base.PwBy;
import io.cyborgcode.roa.ui.playwright.base.PwElement;
import io.cyborgcode.roa.ui.playwright.components.factory.ComponentFactory;
import com.microsoft.playwright.Page;

/**
 * Provides service-level operations for interacting with loader/spinner components.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public class LoaderServiceImpl extends LoaderServiceImplCore<PwElement, Loader, Page, PwBy>
      implements LoaderService {

   public LoaderServiceImpl(Page page) {
      super(page);
   }

   @Override
   protected Loader createComponent(final LoaderComponentType componentType) {
      return ComponentFactory.getLoaderComponent(componentType, driver);
   }

}
