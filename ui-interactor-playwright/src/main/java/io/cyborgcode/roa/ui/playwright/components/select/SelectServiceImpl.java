package io.cyborgcode.roa.ui.playwright.components.select;

import io.cyborgcode.roa.ui.components.base.ComponentType;
import io.cyborgcode.roa.ui.components.select.SelectComponentType;
import io.cyborgcode.roa.ui.components.select.SelectServiceImplCore;
import io.cyborgcode.roa.ui.log.LogUi;
import io.cyborgcode.roa.ui.playwright.base.PwBy;
import io.cyborgcode.roa.ui.playwright.base.PwElement;
import io.cyborgcode.roa.ui.playwright.components.factory.ComponentFactory;
import com.microsoft.playwright.Page;
import java.util.Arrays;

/**
 * Provides service-level operations for interacting with select (dropdown) components.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public class SelectServiceImpl extends SelectServiceImplCore<PwElement, Select, Page, PwBy>
      implements SelectService {

   public SelectServiceImpl(Page page) {
      super(page);
   }

   @Override
   protected Select createComponent(final SelectComponentType componentType) {
      return ComponentFactory.getSelectComponent(componentType, driver);
   }

   @Override
   public void insertion(ComponentType componentType, PwBy selector, Object... values) {
      if (!(componentType instanceof SelectComponentType selectType)) {
         throw new IllegalArgumentException("Component type needs to be from: SelectComponentType.");
      }
      LogUi.step(String.format("Inserting values %s for select component %s using locator %s",
            Arrays.toString(values), componentType, selector));
      String[] stringValues = Arrays.stream(values)
            .map(String::valueOf)
            .toArray(String[]::new);
      selectOptions(selectType, selector, stringValues);
   }
}
