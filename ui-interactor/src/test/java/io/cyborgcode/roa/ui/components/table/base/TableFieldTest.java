package io.cyborgcode.roa.ui.components.table.base;

import io.cyborgcode.roa.ui.components.table.base.mock.TableFieldTestRow;
import io.cyborgcode.roa.ui.testutil.BaseUnitUITest;
import java.lang.reflect.InvocationTargetException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("TableField Tests")
class TableFieldTest extends BaseUnitUITest {

   @Test
   @DisplayName("of() and invoke() should correctly use setter method reference")
   public void testInvoke() throws IllegalAccessException, InvocationTargetException {
      // Given
      TableField<TableFieldTestRow> field = TableField.of(TableFieldTestRow::setValue);
      var mockInstance = new TableFieldTestRow();
      var testValue = "test";

      // When
      field.invoke(mockInstance, testValue);

      // Then
      assertEquals(testValue, mockInstance.getValue());
   }
}