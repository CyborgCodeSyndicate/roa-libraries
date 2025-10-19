package io.cyborgcode.roa.framework.extension.mock;

import io.cyborgcode.roa.framework.config.FrameworkConfig;

public class MockConfig implements FrameworkConfig {

   @Override
   public String projectPackage() {
      return "dummy.package";
   }

   @Override
   public String defaultStorage() {
      return "dummyStorage";
   }

   @Override
   public String testEnv() {
      return "dummyTestEnv";
   }
}
