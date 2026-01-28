package io.cyborgcode.roa.ui.service.fluent.mock;

import io.cyborgcode.roa.ui.components.alert.AlertComponentType;
import io.cyborgcode.roa.ui.components.alert.AlertService;
import io.cyborgcode.roa.ui.selenium.smart.SmartWebElement;
import org.openqa.selenium.By;

public class MockAlertService implements AlertService {

   public static final String VALUE_CONTAINER = "valueContainer";
   public static final String VALUE_LOCATOR = "valueLocator";
   public static final boolean VISIBLE_RESULT = true;

   public AlertComponentType lastComponentType;
   public SmartWebElement lastContainer;
   public By lastLocator;

   public MockAlertService() {
      // Set DEFAULT_TYPE for testing
      lastComponentType = MockAlertComponentType.DUMMY;
   }

   public void reset() {
      lastComponentType = MockAlertComponentType.DUMMY;
      lastContainer = null;
      lastLocator = null;
   }

   @Override
   public String getValue(AlertComponentType componentType, SmartWebElement container) {
      lastComponentType = componentType;
      lastContainer = container;
      return VALUE_CONTAINER;
   }

   @Override
   public String getValue(AlertComponentType componentType, By containerLocator) {
      lastComponentType = componentType;
      lastLocator = containerLocator;
      return VALUE_LOCATOR;
   }

   @Override
   public boolean isVisible(AlertComponentType componentType, SmartWebElement container) {
      lastComponentType = componentType;
      lastContainer = container;
      return VISIBLE_RESULT;
   }

   @Override
   public boolean isVisible(AlertComponentType componentType, By containerLocator) {
      lastComponentType = componentType;
      lastLocator = containerLocator;
      return VISIBLE_RESULT;
   }
}