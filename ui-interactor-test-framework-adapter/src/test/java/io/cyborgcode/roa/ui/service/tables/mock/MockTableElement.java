package io.cyborgcode.roa.ui.service.tables.mock;

import io.cyborgcode.roa.ui.selenium.smart.SmartWebDriver;
import io.cyborgcode.roa.ui.service.tables.DefaultTableTypes;
import io.cyborgcode.roa.ui.service.tables.TableElement;
import java.util.function.Consumer;

public class MockTableElement implements TableElement<DefaultTableTypes> {

   @Override
   public <T> Class<T> rowsRepresentationClass() {
      return (Class<T>) MockRowClass.class;
   }

   @Override
   public DefaultTableTypes enumImpl() {
      return DefaultTableTypes.DEFAULT;
   }

   @Override
   public Consumer<SmartWebDriver> before() {
      return driver -> {
      };
   }

   @Override
   public Consumer<SmartWebDriver> after() {
      return driver -> {
      };
   }
}