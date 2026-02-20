package io.cyborgcode.roa.ui.playwright.components.input;

import com.microsoft.playwright.Locator;
import io.cyborgcode.roa.ui.components.input.InputCore;
import io.cyborgcode.roa.ui.playwright.base.PwBy;

/**
 * Playwright-specific input component interface.
 *
 * <p>Binds the core generic {@link InputCore}
 * to Playwright's {@link Locator} type. All method contracts are inherited from core.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface Input extends InputCore<Locator> {

   void insert(PwBy inputFieldContainerLocator, String value);

   void clear(PwBy inputFieldContainerLocator);

   String getValue(PwBy inputFieldContainerLocator);

   boolean isEnabled(PwBy inputFieldContainerLocator);

   String getErrorMessage(PwBy inputFieldContainerLocator);

}
