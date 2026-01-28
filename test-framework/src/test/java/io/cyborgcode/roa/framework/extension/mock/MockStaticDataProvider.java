package io.cyborgcode.roa.framework.extension.mock;

import io.cyborgcode.roa.framework.data.StaticDataProvider;
import java.util.Map;

public class MockStaticDataProvider implements StaticDataProvider {

   public Map<String, Object> staticTestData() {
      return Map.of("dummyKey", "dummyValue");
   }
}
