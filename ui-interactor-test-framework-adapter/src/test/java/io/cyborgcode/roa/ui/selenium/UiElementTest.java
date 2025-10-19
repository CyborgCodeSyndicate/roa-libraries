package io.cyborgcode.roa.ui.selenium;

import io.cyborgcode.roa.ui.components.base.ComponentType;
import io.cyborgcode.roa.ui.selenium.smart.SmartWebDriver;
import io.cyborgcode.roa.ui.selenium.UiElement;
import java.util.function.Consumer;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyNoInteractions;

/**
 * Unit tests for the UiElement interface default methods.
 */
class UiElementTest {

   // Dummy implementation of UiElement to test default methods
   static class DummyUiElement implements UiElement {
      @Override
      public By locator() {
         return By.id("dummy");
      }

      @Override
      public <T extends ComponentType> T componentType() {
         return null; // Not relevant here
      }

      @Override
      public Enum<?> enumImpl() {
         return null; // Not relevant here
      }
   }

   @Test
   void before_defaultConsumerDoesNothing() {
      UiElement element = new DummyUiElement();
      Consumer<SmartWebDriver> beforeConsumer = element.before();

      assertNotNull(beforeConsumer);

      SmartWebDriver mockDriver = mock(SmartWebDriver.class);

      // Execute the consumer and verify no interactions happen on mockDriver
      beforeConsumer.accept(mockDriver);

      // Since default implementation is empty, verify no interactions
      verifyNoInteractions(mockDriver);
   }

   @Test
   void after_defaultConsumerDoesNothing() {
      UiElement element = new DummyUiElement();
      Consumer<SmartWebDriver> afterConsumer = element.after();

      assertNotNull(afterConsumer);

      SmartWebDriver mockDriver = mock(SmartWebDriver.class);

      // Execute the consumer and verify no interactions happen on mockDriver
      afterConsumer.accept(mockDriver);

      // Since default implementation is empty, verify no interactions
      verifyNoInteractions(mockDriver);
   }
}