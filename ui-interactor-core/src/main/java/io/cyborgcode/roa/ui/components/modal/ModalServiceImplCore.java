package io.cyborgcode.roa.ui.components.modal;

import io.cyborgcode.roa.ui.components.base.AbstractComponentServiceCore;
import io.cyborgcode.roa.ui.components.base.BaseUiElement;
import io.cyborgcode.roa.ui.components.modal.ModalComponentType;
import io.cyborgcode.roa.ui.log.LogUi;

/**
 * Abstract base implementation for modal service operations.
 *
 * @param <E> The element/container type.
 * @param <C> The modal component interface type.
 * @param <D> The driver or page type.
 * @author Cyborg Code Syndicate 💍👨💻
 */
public abstract class ModalServiceImplCore<E extends BaseUiElement, C extends ModalCore<E, L>, D, L>
      extends AbstractComponentServiceCore<ModalComponentType, C, D>
      implements ModalServiceCore<E, L> {

   protected ModalServiceImplCore(final D driver) {
      super(driver);
   }

   protected C modalComponent(final ModalComponentType componentType) {
      return getOrCreateComponent(componentType);
   }

   public boolean isOpened(final ModalComponentType componentType) {
      return modalComponent(componentType).isOpened();
   }

   public void clickButton(final ModalComponentType componentType, final E container, final String buttonText) {
      LogUi.step("Clicking button: '{}' in modal", buttonText);
      modalComponent(componentType).clickButton(container, buttonText);
   }

   public void clickButton(final ModalComponentType componentType, final String buttonText) {
      LogUi.step("Clicking button: '{}' in modal", buttonText);
      modalComponent(componentType).clickButton(buttonText);
   }

   public String getTitle(final ModalComponentType componentType) {
      return modalComponent(componentType).getTitle();
   }

   public String getBodyText(final ModalComponentType componentType) {
      return modalComponent(componentType).getBodyText();
   }

   public String getContentTitle(final ModalComponentType componentType) {
      return modalComponent(componentType).getContentTitle();
   }

   public void close(final ModalComponentType componentType) {
      LogUi.step("Closing modal");
      modalComponent(componentType).close();
   }

   public void clickButton(final ModalComponentType componentType, final L buttonLocator) {
      LogUi.step("Clicking button in modal by locator");
      modalComponent(componentType).clickButton(buttonLocator);
   }
}
