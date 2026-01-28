package io.cyborgcode.roa.ui.selenium;

import io.cyborgcode.roa.ui.components.base.ComponentType;
import io.cyborgcode.roa.ui.components.input.InputComponentType;
import io.cyborgcode.roa.ui.config.UiConfig;
import io.cyborgcode.roa.ui.config.UiConfigHolder;
import io.cyborgcode.roa.ui.service.fluent.mock.MockInputComponentType;
import io.cyborgcode.utilities.reflections.ReflectionUtil;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.openqa.selenium.By;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

class InputUiElementTest {

   static class DummyInputElement implements InputUiElement {
      @Override
      public By locator() {
         return By.id("dummy-id");
      }

      @Override
      public Enum<?> enumImpl() {
         return null;
      }
   }

   @Test
   void testComponentTypeReturnsDefaultType() {
      try (
            MockedStatic<UiConfigHolder> configHolderMock = mockStatic(UiConfigHolder.class);
            MockedStatic<ReflectionUtil> reflectionUtilMock = mockStatic(ReflectionUtil.class)
      ) {
         // Mock UiConfigHolder.getUiConfig()
         UiConfig mockConfig = mock(UiConfig.class);
         when(mockConfig.inputDefaultType()).thenReturn("DUMMY");
         when(mockConfig.projectPackages()).thenReturn(new String[]{"io.cyborgcode.roa"});

         configHolderMock.when(UiConfigHolder::getUiConfig).thenReturn(mockConfig);

         // Mock ReflectionUtil.findEnumImplementationsOfInterface
         reflectionUtilMock.when(() ->
               ReflectionUtil.findEnumImplementationsOfInterface(
                     eq(InputComponentType.class),
                     anyString(),
                     anyString()
               )
         ).thenReturn(MockInputComponentType.DUMMY);

         // Create and test the dummy element
         InputUiElement element = new DummyInputElement();
         ComponentType result = element.componentType();

         assertNotNull(result);
         assertInstanceOf(InputComponentType.class, result);
         assertEquals(MockInputComponentType.DUMMY, result);
         assertEquals(MockInputComponentType.DUMMY, result.getType());
      }
   }
}