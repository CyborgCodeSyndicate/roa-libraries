package io.cyborgcode.roa.ui.playwright.components.loader;

import io.cyborgcode.roa.ui.playwright.components.base.AbstractComponentService;
import io.cyborgcode.roa.ui.playwright.components.factory.ComponentFactory;
import io.cyborgcode.roa.ui.playwright.log.LogUi;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

/**
 * Provides service-level operations for interacting with loader/spinner components.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public class LoaderServiceImpl extends AbstractComponentService<LoaderComponentType, Loader>
      implements LoaderService {

   public LoaderServiceImpl(Page page) {
      super(page);
   }

   @Override
   protected Loader createComponent(final LoaderComponentType componentType) {
      return ComponentFactory.getLoaderComponent(componentType, page);
   }

   @Override
   public void waitUntilLoaded(final LoaderComponentType ct, final Locator c) {
      LogUi.step("Waiting for loader to finish");
      comp(ct).waitUntilLoaded(c);
   }

   @Override
   public void waitUntilLoaded(final LoaderComponentType ct, final Locator c, final int s) {
      LogUi.step("Waiting for loader to finish ({} seconds)", s);
      comp(ct).waitUntilLoaded(c, s);
   }

   @Override
   public void waitUntilLoaded(final LoaderComponentType ct, final String l) {
      LogUi.step("Waiting for loader: '{}' to finish", l);
      comp(ct).waitUntilLoaded(l);
   }

   @Override
   public void waitUntilLoaded(final LoaderComponentType ct, final String l, final int s) {
      LogUi.step("Waiting for loader: '{}' to finish ({} seconds)", l, s);
      comp(ct).waitUntilLoaded(l, s);
   }

   @Override
   public void waitUntilLoadedBySelector(final LoaderComponentType ct, final String sel) {
      LogUi.step("Waiting for loader by selector to finish");
      comp(ct).waitUntilLoadedBySelector(sel);
   }

   @Override
   public void waitUntilLoadedBySelector(final LoaderComponentType ct, final String sel, final int s) {
      LogUi.step("Waiting for loader by selector to finish ({} seconds)", s);
      comp(ct).waitUntilLoadedBySelector(sel, s);
   }

   @Override
   public boolean isLoading(final LoaderComponentType ct, final Locator c) {
      return comp(ct).isLoading(c);
   }

   @Override
   public boolean isLoading(final LoaderComponentType ct, final String l) {
      return comp(ct).isLoading(l);
   }

   @Override
   public boolean isLoadingBySelector(final LoaderComponentType ct, final String s) {
      return comp(ct).isLoadingBySelector(s);
   }

   private Loader comp(final LoaderComponentType componentType) {
      return getOrCreateComponent(componentType);
   }
}
