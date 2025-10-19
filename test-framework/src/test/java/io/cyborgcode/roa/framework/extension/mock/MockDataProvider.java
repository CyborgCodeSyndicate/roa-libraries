package io.cyborgcode.roa.framework.extension.mock;

import io.cyborgcode.roa.framework.data.DataProvider;
import java.util.Map;

public class MockDataProvider implements DataProvider {

   public Map<String, Object> testStaticData() {
      return Map.of("dummyKey", "dummyValue");
   }
}
