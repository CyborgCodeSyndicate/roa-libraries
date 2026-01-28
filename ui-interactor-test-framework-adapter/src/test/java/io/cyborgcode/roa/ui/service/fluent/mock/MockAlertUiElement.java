package io.cyborgcode.roa.ui.service.fluent.mock;

import io.cyborgcode.roa.ui.components.base.ComponentType;
import io.cyborgcode.roa.ui.selenium.AlertUiElement;
import io.cyborgcode.roa.ui.selenium.smart.SmartWebDriver;
import java.util.function.Consumer;
import org.openqa.selenium.By;


public class MockAlertUiElement implements AlertUiElement {

   private final By locator;
   private final Object enumKey;

   public MockAlertUiElement(By locator, Object enumKey) {
      this.locator = locator;
      this.enumKey = enumKey;
   }

   @Override
   public By locator() {
      return locator;
   }

   @Override
   public Consumer<SmartWebDriver> before() {
      return w -> {
      };
   }

   @Override
   public Consumer<SmartWebDriver> after() {
      return w -> {
      };
   }

   @Override
   public Enum<?> enumImpl() {
      return MockAlertComponentType.DUMMY;
   }

   @Override
   public <T extends ComponentType> T componentType() {
      return (T) MockAlertComponentType.DUMMY;
   }
}
