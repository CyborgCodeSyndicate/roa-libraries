package io.cyborgcode.roa.ui.playwright.components.modal;

import io.cyborgcode.roa.ui.playwright.components.base.AbstractComponentService;
import io.cyborgcode.roa.ui.playwright.components.factory.ComponentFactory;
import io.cyborgcode.roa.ui.playwright.log.LogUi;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

/**
 * Provides service-level operations for interacting with modal components.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public class ModalServiceImpl extends AbstractComponentService<ModalComponentType, Modal>
      implements ModalService {

   public ModalServiceImpl(Page page) {
      super(page);
   }

   @Override
   protected Modal createComponent(final ModalComponentType componentType) {
      return ComponentFactory.getModalComponent(componentType, page);
   }

   @Override
   public boolean isOpen(final ModalComponentType ct, final Locator c) {
      return comp(ct).isOpen(c);
   }

   @Override
   public boolean isOpen(final ModalComponentType ct, final String l) {
      return comp(ct).isOpen(l);
   }

   @Override
   public boolean isOpenBySelector(final ModalComponentType ct, final String s) {
      return comp(ct).isOpenBySelector(s);
   }

   @Override
   public void close(final ModalComponentType ct, final Locator c) {
      LogUi.step("Closing modal");
      comp(ct).close(c);
   }

   @Override
   public void close(final ModalComponentType ct, final String l) {
      LogUi.step("Closing modal: '{}'", l);
      comp(ct).close(l);
   }

   @Override
   public void closeBySelector(final ModalComponentType ct, final String s) {
      LogUi.step("Closing modal by selector");
      comp(ct).closeBySelector(s);
   }

   @Override
   public String getTitle(final ModalComponentType ct, final Locator c) {
      return comp(ct).getTitle(c);
   }

   @Override
   public String getTitle(final ModalComponentType ct, final String l) {
      return comp(ct).getTitle(l);
   }

   @Override
   public String getTitleBySelector(final ModalComponentType ct, final String s) {
      return comp(ct).getTitleBySelector(s);
   }

   @Override
   public String getContent(final ModalComponentType ct, final Locator c) {
      return comp(ct).getContent(c);
   }

   @Override
   public String getContent(final ModalComponentType ct, final String l) {
      return comp(ct).getContent(l);
   }

   @Override
   public String getContentBySelector(final ModalComponentType ct, final String s) {
      return comp(ct).getContentBySelector(s);
   }

   @Override
   public void confirm(final ModalComponentType ct, final Locator c) {
      LogUi.step("Confirming modal");
      comp(ct).confirm(c);
   }

   @Override
   public void confirm(final ModalComponentType ct, final String l) {
      LogUi.step("Confirming modal: '{}'", l);
      comp(ct).confirm(l);
   }

   @Override
   public void confirmBySelector(final ModalComponentType ct, final String s) {
      LogUi.step("Confirming modal by selector");
      comp(ct).confirmBySelector(s);
   }

   private Modal comp(final ModalComponentType componentType) {
      return getOrCreateComponent(componentType);
   }
}
