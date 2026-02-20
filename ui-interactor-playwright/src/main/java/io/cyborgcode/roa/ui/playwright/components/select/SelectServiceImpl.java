package io.cyborgcode.roa.ui.playwright.components.select;

import io.cyborgcode.roa.ui.components.base.ComponentType;
import io.cyborgcode.roa.ui.components.select.SelectComponentType;
import io.cyborgcode.roa.ui.components.select.SelectServiceImplCore;
import io.cyborgcode.roa.ui.log.LogUi;
import io.cyborgcode.roa.ui.util.strategy.Strategy;
import io.cyborgcode.roa.ui.playwright.base.PwBy;
import io.cyborgcode.roa.ui.playwright.components.factory.ComponentFactory;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import java.util.Arrays;
import java.util.List;

/**
 * Provides service-level operations for interacting with select (dropdown) components.
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public class SelectServiceImpl extends SelectServiceImplCore<Locator, Select, Page, PwBy>
      implements SelectService {

   public SelectServiceImpl(Page page) {
      super(page);
   }

   @Override
   protected Select createComponent(final SelectComponentType componentType) {
      return ComponentFactory.getSelectComponent(componentType, driver);
   }

   @Override
   public void selectOptions(final SelectComponentType componentType, final PwBy containerLocator, final String... values) {
      LogUi.step("Selecting options");
      selectComponent(componentType).selectOptions(containerLocator, values);
   }

   @Override
   public void selectOptions(final SelectComponentType componentType, final PwBy containerLocator, final Strategy strategy) {
      LogUi.step("Selecting options");
      selectComponent(componentType).selectOptions(containerLocator, strategy);
   }

   @Override
   public List<String> getAvailableOptions(final SelectComponentType componentType, final PwBy containerLocator) {
      return selectComponent(componentType).getAvailableOptions(containerLocator);
   }

   @Override
   public List<String> getSelectedOptions(final SelectComponentType componentType, final PwBy containerLocator) {
      return selectComponent(componentType).getSelectedOptions(containerLocator);
   }

   @Override
   public boolean isOptionVisible(final SelectComponentType componentType, final PwBy containerLocator, final String value) {
      return selectComponent(componentType).isOptionVisible(containerLocator, value);
   }

   @Override
   public boolean isOptionEnabled(final SelectComponentType componentType, final PwBy containerLocator, final String value) {
      return selectComponent(componentType).isOptionEnabled(containerLocator, value);
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
