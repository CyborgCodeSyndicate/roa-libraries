package io.cyborgcode.roa.ui.config;

import io.cyborgcode.roa.ui.testutil.BaseUnitUITest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;

class UiConfigHolderTest extends BaseUnitUITest {

   @Test
   void testGetUiConfigSubsequentCalls() {
      UiConfig firstConfig = UiConfigHolder.getUiConfig();
      UiConfig secondConfig = UiConfigHolder.getUiConfig();

      // Verify same instance is returned
      assertSame(firstConfig, secondConfig,
            "Subsequent calls should return the same config instance");
   }
}