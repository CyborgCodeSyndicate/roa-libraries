package io.cyborgcode.roa.framework.base;

import io.cyborgcode.roa.framework.base.mock.MockEnum;
import io.cyborgcode.roa.framework.log.LogQuest;
import io.cyborgcode.roa.framework.quest.QuestHolder;
import io.cyborgcode.roa.framework.quest.SuperQuest;
import io.cyborgcode.roa.framework.storage.DataExtractor;
import io.cyborgcode.roa.framework.storage.Storage;
import java.util.stream.Stream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("BaseQuest Unit Tests")
class BaseQuestUnitTest {

   private static final String DEFAULT_VALUE = "defaultValue";

   @Mock
   private SuperQuest superQuest;

   @Mock
   private Storage storage;

   @Mock
   private Storage subStorage;

   private BaseQuest baseQuest;

   private MockedStatic<QuestHolder> questHolderMock;
   private MockedStatic<LogQuest> logTestMock;

   @BeforeEach
   void setUpBaseQuest() {
      lenient().when(superQuest.getStorage()).thenReturn(storage);
      questHolderMock = mockStatic(QuestHolder.class);
      questHolderMock.when(QuestHolder::get).thenReturn(superQuest);
      logTestMock = mockStatic(LogQuest.class);
      baseQuest = new BaseQuest();
   }

   @AfterEach
   void tearDownBaseQuest() {
      questHolderMock.close();
      logTestMock.close();
      System.clearProperty("dummy.prop");
   }

   @ParameterizedTest
   @MethodSource("provideBasicRetrieveScenarios")
   @DisplayName("retrieve(Enum, Class) returns correct value from storage")
   <T> void testRetrieve_ShouldReturnCorrectValueFromStorage(MockEnum key, Class<T> clazz, T expected) {
      // Given
      when(storage.get(key, clazz)).thenReturn(expected);

      // When
      T result = baseQuest.retrieve(key, clazz);

      // Then
      assertEquals(expected, result);
      verify(storage).get(key, clazz);
      logTestMock.verify(() -> LogQuest.extended(anyString(), any(), any()));
   }

   @Test
   @DisplayName("retrieve(Enum, Enum, Class) returns correct value from sub-storage")
   void testRetrieve_ShouldReturnCorrectValueFromSubStorage() {
      // Given
      when(storage.sub(MockEnum.KEY2)).thenReturn(subStorage);
      when(subStorage.get(MockEnum.KEY1, Integer.class)).thenReturn(42);

      // When
      Integer result = baseQuest.retrieve(MockEnum.KEY2, MockEnum.KEY1, Integer.class);

      // Then
      assertEquals(42, result);
      verify(storage).sub(MockEnum.KEY2);
      verify(subStorage).get(MockEnum.KEY1, Integer.class);
      logTestMock.verify(() -> LogQuest.extended(anyString(), any(), any()));
   }

   @Test
   @DisplayName("retrieve(DataExtractor, Class) returns correct value")
   void testRetrieve_ShouldReturnCorrectBooleanValueFromStorage() {
      // Given
      DataExtractor<Boolean> extractor = mock(DataExtractor.class);
      doReturn(MockEnum.KEY1).when(extractor).getKey();
      when(storage.get(extractor, Boolean.class)).thenReturn(true);

      // Given
      Boolean result = baseQuest.retrieve(extractor, Boolean.class);

      // Then
      assertTrue(result);
      verify(storage).get(extractor, Boolean.class);
      logTestMock.verify(() -> LogQuest.extended(anyString(), any(), any()));
   }

   @Test
   @DisplayName("retrieve(DataExtractor, int, Class) returns indexed data")
   void testRetrieve_ShouldReturnCorrectDoubleValueFromStorage() {
      // Given
      DataExtractor<Double> extractor = mock(DataExtractor.class);
      doReturn(MockEnum.KEY1).when(extractor).getKey();
      when(storage.get(extractor, Double.class, 1)).thenReturn(9.99);

      // When
      Double result = baseQuest.retrieve(extractor, 1, Double.class);

      // Then
      assertEquals(9.99, result);
      verify(storage).get(extractor, Double.class, 1);
      logTestMock.verify(() -> LogQuest.extended(anyString(), any(), any()));
   }

   @Test
   @DisplayName("DefaultStorage.retrieve(Enum, Class) returns correct value")
   void testRetrieve_ShouldReturnCorrectValueFromDefaultStorage() {
      // Given
      when(storage.sub()).thenReturn(subStorage);
      when(subStorage.get(MockEnum.KEY1, String.class)).thenReturn(DEFAULT_VALUE);

      // When
      String result = BaseQuest.DefaultStorage.retrieve(MockEnum.KEY1, String.class);

      // Then
      assertEquals(DEFAULT_VALUE, result);
      verify(storage).sub();
      verify(subStorage).get(MockEnum.KEY1, String.class);
   }

   @Test
   @DisplayName("DefaultStorage.retrieve(DataExtractor, Class) returns correct value")
   void testRetrieve_ShouldReturnCorrectDataExtractorValueFromStorage() {
      // Given
      DataExtractor<Integer> extractor = mock(DataExtractor.class);
      when(storage.sub()).thenReturn(subStorage);
      when(subStorage.get(extractor, Integer.class)).thenReturn(123);

      // When
      Integer result = BaseQuest.DefaultStorage.retrieve(extractor, Integer.class);

      // Then
      assertEquals(123, result);
      verify(storage).sub();
      verify(subStorage).get(extractor, Integer.class);
   }

   @Test
   @DisplayName("retrieve returns null if storage returns null")
   void testRetrieve_ShouldReturnNullWhenStorageReturnsNull() {
      // Given
      when(storage.get(MockEnum.KEY1, String.class)).thenReturn(null);

      // When
      String result = baseQuest.retrieve(MockEnum.KEY1, String.class);

      // Then
      assertNull(result);
   }

   @Test
   @DisplayName("retrieve throws exception if QuestHolder.get() is null")
   void testRetrieve_ShouldThrowExceptionIfQuestHolderIsNull() {
      // Given
      questHolderMock.close();
      questHolderMock = mockStatic(QuestHolder.class);

      // When
      questHolderMock.when(QuestHolder::get).thenReturn(null);

      // Then
      assertThrows(NullPointerException.class, () -> new BaseQuest().retrieve(MockEnum.KEY1, String.class));
   }

   @Test
   @DisplayName("hookData logs and retrieves hook data correctly")
   void testHookData_ShouldReturnCorrectHookData() {
      // Given
      String input = "hookKey";
      Class<Integer> clazz = Integer.class;

      // When
      when(storage.getHookData(input, clazz)).thenReturn(99);
      Integer result = baseQuest.hookData(input, clazz);

      // Then
      assertEquals(99, result);
      verify(storage).getHookData(input, clazz);
      logTestMock.verify(() -> LogQuest.extended(anyString(), eq(input), eq(clazz.getName())));
   }

   private static Stream<Arguments> provideBasicRetrieveScenarios() {
      return Stream.of(
            Arguments.of(MockEnum.KEY1, String.class, "value"),
            Arguments.of(MockEnum.KEY2, Integer.class, 10),
            Arguments.of(MockEnum.KEY1, Boolean.class, true)
      );
   }
}
