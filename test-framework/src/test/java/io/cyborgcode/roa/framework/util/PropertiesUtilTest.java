package io.cyborgcode.roa.framework.util;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Properties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("PropertiesUtil tests")
@ExtendWith(MockitoExtension.class)
class PropertiesUtilTest {

   @BeforeEach
   void clearSystemProperties() {
      System.clearProperty("test.key");
      System.clearProperty("existing.key");
   }

   @Test
   @DisplayName("Should add properties to System if system.properties file exists and contains properties")
   void shouldAddSystemPropertiesWhenFileExists() {
      // Given
      Resource mockResource = Mockito.mock(Resource.class);
      Mockito.when(mockResource.exists()).thenReturn(true);

      Properties mockProps = new Properties();
      mockProps.setProperty("test.key", "test.value");

      try (
         MockedStatic<PropertiesUtil> utilMock = Mockito.mockStatic(PropertiesUtil.class, Mockito.CALLS_REAL_METHODS);
         MockedStatic<PropertiesLoaderUtils> propsLoaderMock = Mockito.mockStatic(PropertiesLoaderUtils.class)
      ) {
         // Given
         utilMock.when(PropertiesUtil::getResource).thenReturn(mockResource);
         propsLoaderMock.when(() -> PropertiesLoaderUtils.loadProperties(mockResource)).thenReturn(mockProps);

         // When
         PropertiesUtil.addSystemProperties();

         // Then
         assertEquals("test.value", System.getProperty("test.key"));
      }
   }


   @Test
   @DisplayName("Should apply system.properties respecting Surefire overwrite rules")
   void shouldRespectSurefireOverwriteRules() {
      // Save original value to avoid polluting other tests
      String original = System.getProperty("existing.key");

      // Given
      System.setProperty("existing.key", "do.not.overwrite");

      Resource mockResource = Mockito.mock(Resource.class);
      Mockito.when(mockResource.exists()).thenReturn(true);

      Properties mockProps = new Properties();
      mockProps.setProperty("existing.key", "overwrite.this");

      // Detect environment exactly the same way as production code
      boolean surefire = System.getProperties()
         .stringPropertyNames()
         .stream()
         .anyMatch(k -> k.startsWith("surefire."));

      try (
         MockedStatic<PropertiesUtil> utilMock =
            Mockito.mockStatic(PropertiesUtil.class, Mockito.CALLS_REAL_METHODS);
         MockedStatic<PropertiesLoaderUtils> propsLoaderMock =
            Mockito.mockStatic(PropertiesLoaderUtils.class)
      ) {
         // When
         utilMock.when(PropertiesUtil::getResource).thenReturn(mockResource);
         propsLoaderMock.when(() -> PropertiesLoaderUtils.loadProperties(mockResource)).thenReturn(mockProps);

         PropertiesUtil.addSystemProperties();

         // Then
         String expected = surefire ? "do.not.overwrite" : "overwrite.this";
         assertEquals(expected, System.getProperty("existing.key"),
            "Expected behavior differs in Surefire vs non-Surefire environment");
      } finally {
         // Restore original value
         if (original == null) {
            System.clearProperty("existing.key");
         } else {
            System.setProperty("existing.key", original);
         }
      }
   }

   @Test
   @DisplayName("Should skip loading if system.properties file does not exist")
   void shouldDoNothingIfFileDoesNotExist() {
      // Given
      Resource mockResource = Mockito.mock(Resource.class);
      Mockito.when(mockResource.exists()).thenReturn(false);

      try (
         MockedStatic<PropertiesUtil> utilMock = Mockito.mockStatic(PropertiesUtil.class, Mockito.CALLS_REAL_METHODS)
      ) {
         utilMock.when(PropertiesUtil::getResource).thenReturn(mockResource);

         // When
         // Then
         assertDoesNotThrow(PropertiesUtil::addSystemProperties);
      }
   }

   @Test
   @DisplayName("Should wrap IOException in UncheckedIOException when loading system.properties fails")
   void shouldWrapIOExceptionIntoUncheckedIOException() {
      // Given
      Resource mockResource = Mockito.mock(Resource.class);
      Mockito.when(mockResource.exists()).thenReturn(true);

      try (
         MockedStatic<PropertiesUtil> utilMock = Mockito.mockStatic(PropertiesUtil.class, Mockito.CALLS_REAL_METHODS);
         MockedStatic<PropertiesLoaderUtils> propsLoaderMock = Mockito.mockStatic(PropertiesLoaderUtils.class)
      ) {
         utilMock.when(PropertiesUtil::getResource).thenReturn(mockResource);
         propsLoaderMock.when(() -> PropertiesLoaderUtils.loadProperties(mockResource))
            .thenThrow(new IOException("Broken file"));

         // When
         // Then
         UncheckedIOException thrown = assertThrows(UncheckedIOException.class, PropertiesUtil::addSystemProperties);
         assertTrue(thrown.getMessage().contains("system.properties"));
      }
   }
}
