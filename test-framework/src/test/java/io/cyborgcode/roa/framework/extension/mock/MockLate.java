package io.cyborgcode.roa.framework.extension.mock;

import io.cyborgcode.roa.framework.parameters.Late;

public class MockLate implements Late<Object> {

   @Override
   public Object create() {
      return "createdValue";
   }
}
