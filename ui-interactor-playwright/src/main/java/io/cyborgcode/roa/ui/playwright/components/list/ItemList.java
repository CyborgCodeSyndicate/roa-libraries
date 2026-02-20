package io.cyborgcode.roa.ui.playwright.components.list;

import com.microsoft.playwright.Locator;
import io.cyborgcode.roa.ui.components.list.ItemListCore;
import io.cyborgcode.roa.ui.playwright.base.PwBy;
import io.cyborgcode.roa.ui.util.strategy.Strategy;
import java.util.List;

/**
 * Playwright-specific list component interface.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface ItemList extends ItemListCore<Locator> {

   void select(PwBy containerLocator, String... itemText);

   String select(PwBy containerLocator, Strategy strategy);

   void select(PwBy... itemListLocator);

   void deSelect(PwBy containerLocator, String... itemText);

   String deSelect(PwBy containerLocator, Strategy strategy);

   void deSelect(PwBy... itemListLocator);

   boolean areSelected(PwBy containerLocator, String... itemText);

   boolean areSelected(PwBy... itemListLocator);

   boolean areEnabled(PwBy containerLocator, String... itemText);

   boolean areEnabled(PwBy... itemLocator);

   boolean areVisible(PwBy containerLocator, String... itemText);

   boolean areVisible(PwBy... itemLocator);

   List<String> getSelected(PwBy containerLocator);

   List<String> getAll(PwBy containerLocator);

}
