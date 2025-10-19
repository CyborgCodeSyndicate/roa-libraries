package io.cyborgcode.roa.framework.extension.mock;

import io.cyborgcode.roa.framework.annotation.TestStaticData;

public class MockTestClass {

   @TestStaticData(MockDataProvider.class)
   public void mockTestMethod() {
   }
}
