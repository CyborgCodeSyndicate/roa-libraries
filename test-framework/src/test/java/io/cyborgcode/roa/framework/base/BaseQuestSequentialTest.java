package io.cyborgcode.roa.framework.base;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@DisplayName("BaseQuestSequential Tests")
class BaseQuestSequentialTest {

   @Spy
   @InjectMocks
   private BaseQuestSequential baseQuestSequential;

   @Mock
   private Services services;

   @Test
   @DisplayName("beforeAll() should call overridable beforeAll(Services)")
   void testBeforeAll_ShouldCallOverridableBeforeAll() {
      // When
      baseQuestSequential.beforeAll();

      // Then
      verify(baseQuestSequential).beforeAll(services);
   }

   @Test
   @DisplayName("afterAll() should call overridable afterAll(Services)")
   void testAfterAll_ShouldCallOverridableAfterAll() {
      // When
      baseQuestSequential.afterAll();

      // Then
      verify(baseQuestSequential).afterAll(services);
   }
}