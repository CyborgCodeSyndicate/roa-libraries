package io.cyborgcode.roa.framework.extension.mock;

import io.cyborgcode.roa.framework.annotation.Ripper;

public class MockRipper {

   @Ripper(targets = {"MOCK_TARGET"})
   public void testMethod() {
   }
}