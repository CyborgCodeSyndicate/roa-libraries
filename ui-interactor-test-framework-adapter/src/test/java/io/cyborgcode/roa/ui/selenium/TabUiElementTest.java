package io.cyborgcode.roa.ui.selenium;

import io.cyborgcode.roa.ui.components.base.ComponentType;
import io.cyborgcode.roa.ui.components.tab.TabComponentType;
import io.cyborgcode.roa.ui.config.UiConfig;
import io.cyborgcode.roa.ui.config.UiConfigHolder;
import io.cyborgcode.roa.ui.service.fluent.mock.MockTabComponentType;
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

class TabUiElementTest {

   static class DummyTabElement implements TabUiElement {
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
         when(mockConfig.tabDefaultType()).thenReturn("DUMMY");
         when(mockConfig.projectPackages()).thenReturn(new String[]{"io.cyborgcode.roa"});

         configHolderMock.when(UiConfigHolder::getUiConfig).thenReturn(mockConfig);

         // Mock ReflectionUtil.findEnumImplementationsOfInterface
         reflectionUtilMock.when(() ->
               ReflectionUtil.findEnumImplementationsOfInterface(
                     eq(TabComponentType.class),
                     anyString(),
                     anyString()
               )
         ).thenReturn(MockTabComponentType.DUMMY);

         // Create and test the dummy element
         TabUiElement element = new DummyTabElement();
         ComponentType result = element.componentType();

         assertNotNull(result);
         assertInstanceOf(TabComponentType.class, result);
         assertEquals(MockTabComponentType.DUMMY, result);
         assertEquals(MockTabComponentType.DUMMY, result.getType());
      }
   }
}