package io.cyborgcode.roa.ui.playwright.components.loader;

import io.cyborgcode.roa.ui.components.loader.LoaderComponentType;
import io.cyborgcode.roa.ui.components.loader.LoaderServiceImplCore;
import io.cyborgcode.roa.ui.log.LogUi;
import io.cyborgcode.roa.ui.playwright.base.PwBy;
import io.cyborgcode.roa.ui.playwright.components.factory.ComponentFactory;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

/**
 * Provides service-level operations for interacting with loader/spinner components.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public class LoaderServiceImpl extends LoaderServiceImplCore<Locator, Loader, Page>
      implements LoaderService {

   public LoaderServiceImpl(Page page) {
      super(page);
   }

   @Override
   protected Loader createComponent(final LoaderComponentType componentType) {
      return ComponentFactory.getLoaderComponent(componentType, driver);
   }

   @Override
   public boolean isVisible(final LoaderComponentType componentType, final PwBy loaderLocator) {
      return loaderComponent(componentType).isVisible(loaderLocator);
   }

   @Override
   public void waitToBeShown(final LoaderComponentType componentType, final PwBy loaderLocator, final int secondsShown) {
      LogUi.step("Waiting for loader to be shown ({} seconds)", secondsShown);
      loaderComponent(componentType).waitToBeShown(loaderLocator, secondsShown);
   }

   @Override
   public void waitToBeRemoved(final LoaderComponentType componentType, final PwBy loaderLocator, final int secondsRemoved) {
      LogUi.step("Waiting for loader to be removed ({} seconds)", secondsRemoved);
      loaderComponent(componentType).waitToBeRemoved(loaderLocator, secondsRemoved);
   }
}
