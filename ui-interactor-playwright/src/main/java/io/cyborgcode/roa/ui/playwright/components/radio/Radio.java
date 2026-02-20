package io.cyborgcode.roa.ui.playwright.components.radio;

import com.microsoft.playwright.Locator;
import io.cyborgcode.roa.ui.components.radio.RadioCore;
import io.cyborgcode.roa.ui.playwright.base.PwBy;
import io.cyborgcode.roa.ui.util.strategy.Strategy;
import java.util.List;

/**
 * Playwright-specific radio component interface.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface Radio extends RadioCore<Locator> {

   void select(PwBy radioButtonLocator);

   boolean isEnabled(PwBy radioButtonLocator);

   boolean isSelected(PwBy radioButtonLocator);

   boolean isVisible(PwBy radioButtonLocator);

   String getSelected(PwBy containerLocator);

   List<String> getAll(PwBy containerLocator);

}
