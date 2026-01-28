package io.cyborgcode.roa.framework.extension.mock;

import io.cyborgcode.roa.framework.annotation.StaticTestData;

public class MockTestClass {

   @StaticTestData(MockStaticDataProvider.class)
   public void mockTestMethod() {
   }
}
