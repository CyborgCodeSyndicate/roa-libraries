package io.cyborgcode.roa.ui.selenium;

import io.cyborgcode.roa.ui.components.base.ComponentType;
import io.cyborgcode.roa.ui.components.loader.LoaderComponentType;
import io.cyborgcode.roa.ui.config.UiConfig;
import io.cyborgcode.roa.ui.config.UiConfigHolder;
import io.cyborgcode.roa.ui.service.fluent.mock.MockLoaderComponentType;
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

class LoaderUiElementTest {

   static class DummyLoaderElement implements LoaderUiElement {
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
         when(mockConfig.loaderDefaultType()).thenReturn("DUMMY");
         when(mockConfig.projectPackage()).thenReturn("com.theairebellion.zeus");

         configHolderMock.when(UiConfigHolder::getUiConfig).thenReturn(mockConfig);

         // Mock ReflectionUtil.findEnumImplementationsOfInterface
         reflectionUtilMock.when(() ->
               ReflectionUtil.findEnumImplementationsOfInterface(
                     eq(LoaderComponentType.class),
                     anyString(),
                     anyString()
               )
         ).thenReturn(MockLoaderComponentType.DUMMY);

         // Create and test the dummy element
         LoaderUiElement element = new DummyLoaderElement();
         ComponentType result = element.componentType();

         assertNotNull(result);
         assertInstanceOf(LoaderComponentType.class, result);
         assertEquals(MockLoaderComponentType.DUMMY, result);
         assertEquals(MockLoaderComponentType.DUMMY, result.getType());
      }
   }
}