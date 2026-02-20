package io.cyborgcode.roa.ui.playwright.components.checkbox;

import com.microsoft.playwright.Locator;
import io.cyborgcode.roa.ui.components.checkbox.CheckboxComponentType;
import io.cyborgcode.roa.ui.components.checkbox.CheckboxServiceCore;
import io.cyborgcode.roa.ui.playwright.base.PwBy;
import java.util.List;

/**
 * Playwright-specific checkbox service interface.
 *
 * <p>Binds the core generic {@link CheckboxServiceCore}
 * to Playwright's {@link Locator} type. All method contracts are inherited from core.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public interface CheckboxService extends CheckboxServiceCore<Locator, PwBy> {

   default void select(PwBy... checkBoxLocator) {
      select(DEFAULT_TYPE, checkBoxLocator);
   }

   void select(CheckboxComponentType componentType, PwBy... checkBoxLocator);

   default void deSelect(PwBy... checkBoxLocator) {
      deSelect(DEFAULT_TYPE, checkBoxLocator);
   }

   void deSelect(CheckboxComponentType componentType, PwBy... checkBoxLocator);

   default boolean areSelected(PwBy... checkBoxLocator) {
      return areSelected(DEFAULT_TYPE, checkBoxLocator);
   }

   boolean areSelected(CheckboxComponentType componentType, PwBy... checkBoxLocator);

   default boolean areEnabled(PwBy... checkBoxLocator) {
      return areEnabled(DEFAULT_TYPE, checkBoxLocator);
   }

   boolean areEnabled(CheckboxComponentType componentType, PwBy... checkBoxLocator);

   default List<String> getSelected(PwBy containerLocator) {
      return getSelected(DEFAULT_TYPE, containerLocator);
   }

   List<String> getSelected(CheckboxComponentType componentType, PwBy containerLocator);

   default List<String> getAll(PwBy containerLocator) {
      return getAll(DEFAULT_TYPE, containerLocator);
   }

   List<String> getAll(CheckboxComponentType componentType, PwBy containerLocator);

}
