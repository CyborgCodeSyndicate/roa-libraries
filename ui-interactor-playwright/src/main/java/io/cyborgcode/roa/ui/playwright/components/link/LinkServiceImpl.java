package io.cyborgcode.roa.ui.playwright.components.link;

import io.cyborgcode.roa.ui.playwright.components.base.AbstractComponentService;
import io.cyborgcode.roa.ui.playwright.components.base.ComponentType;
import io.cyborgcode.roa.ui.playwright.components.factory.ComponentFactory;
import io.cyborgcode.roa.ui.playwright.log.LogUi;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

/**
 * Provides service-level operations for interacting with link components.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public class LinkServiceImpl extends AbstractComponentService<LinkComponentType, Link>
      implements LinkService {

   public LinkServiceImpl(Page page) {
      super(page);
   }

   @Override
   protected Link createComponent(final LinkComponentType componentType) {
      return ComponentFactory.getLinkComponent(componentType, page);
   }

   @Override
   public void click(final LinkComponentType ct, final Locator c, final String l) {
      LogUi.step("Clicking link: '{}'", l);
      comp(ct).click(c, l);
   }

   @Override
   public void click(final LinkComponentType ct, final Locator c) {
      LogUi.step("Clicking link in container");
      comp(ct).click(c);
   }

   @Override
   public void click(final LinkComponentType ct, final String l) {
      LogUi.step("Clicking link: '{}'", l);
      comp(ct).click(l);
   }

   @Override
   public void clickBySelector(final LinkComponentType ct, final String s) {
      LogUi.step("Clicking link by selector");
      comp(ct).clickBySelector(s);
   }

   @Override
   public String getHref(final LinkComponentType ct, final Locator c, final String l) {
      return comp(ct).getHref(c, l);
   }

   @Override
   public String getHref(final LinkComponentType ct, final Locator c) {
      return comp(ct).getHref(c);
   }

   @Override
   public String getHref(final LinkComponentType ct, final String l) {
      return comp(ct).getHref(l);
   }

   @Override
   public String getHrefBySelector(final LinkComponentType ct, final String s) {
      return comp(ct).getHrefBySelector(s);
   }

   @Override
   public boolean isVisible(final LinkComponentType ct, final Locator c, final String l) {
      return comp(ct).isVisible(c, l);
   }

   @Override
   public boolean isVisible(final LinkComponentType ct, final Locator c) {
      return comp(ct).isVisible(c);
   }

   @Override
   public boolean isVisible(final LinkComponentType ct, final String l) {
      return comp(ct).isVisible(l);
   }

   @Override
   public boolean isVisibleBySelector(final LinkComponentType ct, final String s) {
      return comp(ct).isVisibleBySelector(s);
   }

   @Override
   public boolean isEnabled(final LinkComponentType ct, final Locator c, final String l) {
      return comp(ct).isEnabled(c, l);
   }

   @Override
   public boolean isEnabled(final LinkComponentType ct, final Locator c) {
      return comp(ct).isEnabled(c);
   }

   @Override
   public boolean isEnabled(final LinkComponentType ct, final String l) {
      return comp(ct).isEnabled(l);
   }

   @Override
   public boolean isEnabledBySelector(final LinkComponentType ct, final String s) {
      return comp(ct).isEnabledBySelector(s);
   }

   @Override
   public void tableInsertion(Locator cellElement, ComponentType componentType, String... values) {
   }

   private Link comp(final LinkComponentType componentType) {
      return getOrCreateComponent(componentType);
   }
}
