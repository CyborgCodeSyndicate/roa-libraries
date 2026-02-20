package io.cyborgcode.roa.ui.playwright.components.select;

import com.microsoft.playwright.Locator;
import io.cyborgcode.roa.ui.components.select.SelectComponentType;
import io.cyborgcode.roa.ui.components.select.SelectServiceCore;
import io.cyborgcode.roa.ui.util.strategy.Strategy;
import io.cyborgcode.roa.ui.playwright.base.PwBy;
import java.util.List;

/**
 * Playwright-specific select service interface.
 *
 * <p>Binds the core generic {@link SelectServiceCore}
 * to Playwright's {@link Locator} type. All method contracts are inherited from core.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface SelectService extends SelectServiceCore<Locator, PwBy> {

   default void selectOptions(PwBy containerLocator, String... values) {
      selectOptions(DEFAULT_TYPE, containerLocator, values);
   }

   void selectOptions(SelectComponentType componentType, PwBy containerLocator, String... values);

   default void selectOptions(PwBy containerLocator, Strategy strategy) {
      selectOptions(DEFAULT_TYPE, containerLocator, strategy);
   }

   void selectOptions(SelectComponentType componentType, PwBy containerLocator, Strategy strategy);

   default List<String> getAvailableOptions(PwBy containerLocator) {
      return getAvailableOptions(DEFAULT_TYPE, containerLocator);
   }

   List<String> getAvailableOptions(SelectComponentType componentType, PwBy containerLocator);

   default List<String> getSelectedOptions(PwBy containerLocator) {
      return getSelectedOptions(DEFAULT_TYPE, containerLocator);
   }

   List<String> getSelectedOptions(SelectComponentType componentType, PwBy containerLocator);

   default boolean isOptionVisible(PwBy containerLocator, String value) {
      return isOptionVisible(DEFAULT_TYPE, containerLocator, value);
   }

   boolean isOptionVisible(SelectComponentType componentType, PwBy containerLocator, String value);

   default boolean isOptionEnabled(PwBy containerLocator, String value) {
      return isOptionEnabled(DEFAULT_TYPE, containerLocator, value);
   }

   boolean isOptionEnabled(SelectComponentType componentType, PwBy containerLocator, String value);
}
