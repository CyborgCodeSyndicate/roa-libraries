package io.cyborgcode.roa.ui.playwright.components.radio;

import com.microsoft.playwright.Locator;
import io.cyborgcode.roa.ui.components.radio.RadioComponentType;
import io.cyborgcode.roa.ui.components.radio.RadioServiceCore;
import io.cyborgcode.roa.ui.playwright.base.PwBy;
import java.util.List;

/**
 * Playwright-specific radio service interface.
 *
 * <p>Binds the core generic {@link RadioServiceCore}
 * to Playwright's {@link Locator} type. All method contracts are inherited from core.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface RadioService extends RadioServiceCore<Locator, PwBy> {

   default void select(PwBy radioButtonLocator) {
      select(DEFAULT_TYPE, radioButtonLocator);
   }

   void select(RadioComponentType componentType, PwBy radioButtonLocator);

   default boolean isEnabled(PwBy radioButtonLocator) {
      return isEnabled(DEFAULT_TYPE, radioButtonLocator);
   }

   boolean isEnabled(RadioComponentType componentType, PwBy radioButtonLocator);

   default boolean isSelected(PwBy radioButtonLocator) {
      return isSelected(DEFAULT_TYPE, radioButtonLocator);
   }

   boolean isSelected(RadioComponentType componentType, PwBy radioButtonLocator);

   default boolean isVisible(PwBy radioButtonLocator) {
      return isVisible(DEFAULT_TYPE, radioButtonLocator);
   }

   boolean isVisible(RadioComponentType componentType, PwBy radioButtonLocator);

   default String getSelected(PwBy containerLocator) {
      return getSelected(DEFAULT_TYPE, containerLocator);
   }

   String getSelected(RadioComponentType componentType, PwBy containerLocator);

   default List<String> getAll(PwBy containerLocator) {
      return getAll(DEFAULT_TYPE, containerLocator);
   }

   List<String> getAll(RadioComponentType componentType, PwBy containerLocator);
}
