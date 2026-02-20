package io.cyborgcode.roa.ui.playwright.components.checkbox;

import com.microsoft.playwright.Locator;
import io.cyborgcode.roa.ui.components.checkbox.CheckboxCore;
import io.cyborgcode.roa.ui.playwright.base.PwBy;
import java.util.List;

/**
 * Playwright-specific checkbox component interface.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface Checkbox extends CheckboxCore<Locator> {

   void select(PwBy... checkBoxLocator);

   void deSelect(PwBy... checkBoxLocator);

   boolean areSelected(PwBy... checkBoxLocator);

   boolean areEnabled(PwBy... checkBoxLocator);

   List<String> getSelected(PwBy containerLocator);

   List<String> getAll(PwBy containerLocator);

}
