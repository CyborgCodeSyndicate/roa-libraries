package io.cyborgcode.roa.framework.extension.mock;

import io.cyborgcode.roa.framework.parameters.DataForge;
import io.cyborgcode.roa.framework.parameters.Late;

public class MockDataForge implements DataForge<MockEnum> {

   private final Late<Object> late;

   public MockDataForge(Late<Object> late) {
      this.late = late;
   }

   @Override
   public Late<Object> dataCreator() {
      return late;
   }

   @Override
   public MockEnum enumImpl() {
      return MockEnum.VALUE;
   }
}
