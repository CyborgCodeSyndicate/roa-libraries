package io.cyborgcode.roa.ui.playwright.components.select;

import com.microsoft.playwright.Locator;
import io.cyborgcode.roa.ui.components.select.SelectCore;
import io.cyborgcode.roa.ui.playwright.base.PwBy;
import io.cyborgcode.roa.ui.util.strategy.Strategy;
import java.util.List;

/**
 * Playwright-specific select component interface.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface Select extends SelectCore<Locator> {

   void selectOptions(PwBy containerLocator, String... values);

   List<String> selectOptions(PwBy containerLocator, Strategy strategy);

   List<String> getAvailableOptions(PwBy containerLocator);

   List<String> getSelectedOptions(PwBy containerLocator);

   boolean isOptionVisible(PwBy containerLocator, String value);

   boolean isOptionEnabled(PwBy containerLocator, String value);

}
