package io.cyborgcode.roa.ui.components.loader;

import io.cyborgcode.roa.ui.components.base.AbstractComponentServiceCore;
import io.cyborgcode.roa.ui.log.LogUi;

/**
 * Abstract base implementation for loader/spinner service operations.
 *
 * @param <E> The element/container type.
 * @param <C> The loader component interface type.
 * @param <D> The driver or page type.
 * @author Cyborg Code Syndicate 💍👨💻
 */
public abstract class LoaderServiceImplCore<E, C extends LoaderCore<E>, D>
      extends AbstractComponentServiceCore<LoaderComponentType, C, D>
      implements LoaderServiceCore<E> {

   protected LoaderServiceImplCore(final D driver) {
      super(driver);
   }

   protected C loaderComponent(final LoaderComponentType componentType) {
      return getOrCreateComponent(componentType);
   }

   public boolean isVisible(final LoaderComponentType componentType, final E container) {
      return loaderComponent(componentType).isVisible(container);
   }

   public void waitToBeShown(final LoaderComponentType componentType, final E container, final int secondsShown) {
      LogUi.step("Waiting for loader to be shown ({} seconds)", secondsShown);
      loaderComponent(componentType).waitToBeShown(container, secondsShown);
   }

   public void waitToBeShown(final LoaderComponentType componentType, final int secondsShown) {
      LogUi.step("Waiting for loader to be shown ({} seconds)", secondsShown);
      loaderComponent(componentType).waitToBeShown(secondsShown);
   }

   public void waitToBeRemoved(final LoaderComponentType componentType, final E container, final int secondsRemoved) {
      LogUi.step("Waiting for loader to be removed ({} seconds)", secondsRemoved);
      loaderComponent(componentType).waitToBeRemoved(container, secondsRemoved);
   }

   public void waitToBeRemoved(final LoaderComponentType componentType, final int secondsRemoved) {
      LogUi.step("Waiting for loader to be removed ({} seconds)", secondsRemoved);
      loaderComponent(componentType).waitToBeRemoved(secondsRemoved);
   }
}
