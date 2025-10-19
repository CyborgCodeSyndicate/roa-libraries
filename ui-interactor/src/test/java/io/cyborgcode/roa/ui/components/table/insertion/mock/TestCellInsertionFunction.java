package io.cyborgcode.roa.ui.components.table.insertion.mock;

import io.cyborgcode.roa.ui.components.table.insertion.CellInsertionFunction;
import io.cyborgcode.roa.ui.selenium.smart.SmartWebElement;

public class TestCellInsertionFunction implements CellInsertionFunction {

   public SmartWebElement capturedElement;
   public String[] capturedValues;

   @Override
   public void cellInsertionFunction(SmartWebElement cellElement, String... values) {
      capturedElement = cellElement;
      capturedValues = values;
   }
}