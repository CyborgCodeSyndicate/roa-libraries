package io.cyborgcode.roa.ui.playwright.base;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import java.util.Objects;

public final class PwBy {
   private final String selector;

   private PwBy(String selector) {
      this.selector = Objects.requireNonNull(selector, "selector");
   }

   public static PwBy css(String css) {
      // Playwright accepts CSS selectors directly
      return new PwBy(css);
   }

   public static PwBy playwright(String selector) {
      // For any Playwright selector engine: css=, text=, role=, xpath=, etc.
      return new PwBy(selector);
   }

   public String selector() {
      return selector;
   }

   public Locator on(Page page) {
      return page.locator(selector);
   }

   public Locator within(Locator root) {
      return root.locator(selector);
   }

   @Override
   public String toString() {
      return selector;
   }
}