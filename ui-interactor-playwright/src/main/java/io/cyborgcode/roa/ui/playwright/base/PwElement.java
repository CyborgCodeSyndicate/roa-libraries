package io.cyborgcode.roa.ui.playwright.base;

import com.microsoft.playwright.Locator;
import io.cyborgcode.roa.ui.components.base.BaseUiElement;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Delegate;

@RequiredArgsConstructor(staticName = "of")
public final class PwElement implements BaseUiElement, Locator {

   /**
    * Lombok will delegate all Locator methods EXCEPT the ones listed in LocatorFluentReturns,
    * because we override those to keep the chain returning PwElement.
    */
   @NonNull
   @Delegate(excludes = LocatorFluentReturns.class)
   private final Locator locator;

   /** Excludes the fluent Locator-returning methods from @Delegate so we can override them. */
   private interface LocatorFluentReturns {
      Locator locator(String selector);
      Locator locator(String selector, Locator.LocatorOptions options);
      Locator first();
      Locator last();
      Locator nth(int index);
      Locator filter(Locator.FilterOptions options);

      // Add the ones you actually use (expand over time):
      Locator getByText(String text);
      Locator getByText(String text, Locator.GetByTextOptions options);
      Locator getByRole(com.microsoft.playwright.options.AriaRole role);
      Locator getByRole(com.microsoft.playwright.options.AriaRole role, Locator.GetByRoleOptions options);
      Locator getByTestId(String testId);
   }

   // ---- Fluent overrides (covariant return types: PwElement is-a Locator) ----

   @Override
   public PwElement locator(String selector) {
      return PwElement.of(locator.locator(selector));
   }

   @Override
   public PwElement locator(String selector, LocatorOptions options) {
      return PwElement.of(locator.locator(selector, options));
   }

   @Override
   public PwElement first() {
      return PwElement.of(locator.first());
   }

   @Override
   public PwElement last() {
      return PwElement.of(locator.last());
   }

   @Override
   public PwElement nth(int index) {
      return PwElement.of(locator.nth(index));
   }

   @Override
   public PwElement filter(FilterOptions options) {
      return PwElement.of(locator.filter(options));
   }

   @Override
   public PwElement getByText(String text) {
      return PwElement.of(locator.getByText(text));
   }

   @Override
   public PwElement getByText(String text, GetByTextOptions options) {
      return PwElement.of(locator.getByText(text, options));
   }

   @Override
   public PwElement getByRole(com.microsoft.playwright.options.AriaRole role) {
      return PwElement.of(locator.getByRole(role));
   }

   @Override
   public PwElement getByRole(com.microsoft.playwright.options.AriaRole role, GetByRoleOptions options) {
      return PwElement.of(locator.getByRole(role, options));
   }

   @Override
   public PwElement getByTestId(String testId) {
      return PwElement.of(locator.getByTestId(testId));
   }

   // ---- List helpers (because Locator.all() must stay List<Locator>) ----

   /** Use this in your framework whenever you want List<PwElement>. */
   public List<PwElement> allElements() {
      return locator.all().stream().map(PwElement::of).toList();
   }

   /** Optional: unwrap for low-level Playwright calls if ever needed. */
   public Locator unwrap() {
      return locator;
   }
}