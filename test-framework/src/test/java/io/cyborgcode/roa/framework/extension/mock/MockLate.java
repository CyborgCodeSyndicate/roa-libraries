package io.cyborgcode.roa.framework.extension.mock;

import io.cyborgcode.roa.framework.parameters.Late;

public class MockLate implements Late<Object> {

   @Override
   public Object join() {
      return "joinedValue";
   }
}
