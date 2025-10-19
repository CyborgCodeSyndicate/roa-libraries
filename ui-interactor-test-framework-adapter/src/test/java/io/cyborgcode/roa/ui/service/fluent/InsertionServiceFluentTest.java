package io.cyborgcode.roa.ui.service.fluent;

import io.cyborgcode.roa.framework.storage.Storage;
import io.cyborgcode.roa.ui.insertion.InsertionService;
import io.cyborgcode.roa.ui.service.fluent.InsertionServiceFluent;
import io.cyborgcode.roa.ui.service.fluent.UiServiceFluent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("InsertionServiceFluent Tests")
class InsertionServiceFluentTest {

   private InsertionService insertionService;
   private UiServiceFluent<?> uiServiceFluent;
   private Storage storage;

   private InsertionServiceFluent<UiServiceFluent<?>> sut; // system under test

   @BeforeEach
   void setUp() {
      insertionService = mock(InsertionService.class);
      uiServiceFluent = mock(UiServiceFluent.class);
      storage = mock(Storage.class);
      sut = new InsertionServiceFluent<>(insertionService, uiServiceFluent, storage);
   }

   @Test
   @DisplayName("Should insert data and return fluent interface")
   void insertDataTest() {
      // Given
      String data = "TestData";

      // When
      UiServiceFluent<?> result = sut.insertData(data);

      // Then
      verify(insertionService).insertData(data);
      assertThat(result).isSameAs(uiServiceFluent);
   }
}
