package io.cyborgcode.roa.ui.playwright.service.fluent;

import com.microsoft.playwright.Page;
import io.cyborgcode.roa.ui.playwright.base.PwBy;
import io.cyborgcode.roa.ui.playwright.base.PwElement;
import io.cyborgcode.roa.ui.service.fluent.SuperUiServiceFluentCore;
import lombok.experimental.Delegate;

/**
 * A specialized Playwright UI service fluent class that extends {@link SuperUiServiceFluentCore}
 * and delegates its functionalities to an existing instance of {@link UiServiceFluent}.
 *
 * <p>The generic type {@code T} represents a concrete implementation of {@link UiServiceFluent},
 * ensuring fluent interface compatibility and enabling seamless method chaining.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public class SuperUiServiceFluent<T extends UiServiceFluent<T>>
      extends SuperUiServiceFluentCore<PwBy, Page, PwElement, T> {

   @Delegate
   private final UiServiceFluent<T> delegate;

   public SuperUiServiceFluent(T uiServiceFluent) {
      super(uiServiceFluent);
      this.delegate = uiServiceFluent;
   }
}
