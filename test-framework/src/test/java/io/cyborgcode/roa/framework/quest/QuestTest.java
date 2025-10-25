package io.cyborgcode.roa.framework.quest;

import io.cyborgcode.roa.framework.assertion.CustomSoftAssertion;
import io.cyborgcode.roa.framework.chain.FluentService;
import io.cyborgcode.roa.framework.log.LogQuest;
import io.cyborgcode.roa.framework.quest.mock.MockFluentService;
import io.cyborgcode.roa.framework.quest.mock.TestableQuest;
import io.cyborgcode.roa.framework.storage.Storage;
import io.cyborgcode.utilities.reflections.exceptions.ReflectionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.mockito.InjectMocks;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SuppressWarnings("all")
@ExtendWith(MockitoExtension.class)
class QuestTest {

    @InjectMocks
    private TestableQuest quest;

    private MockFluentService mockFluentService;

    @BeforeEach
    void setUp() {
        quest = new TestableQuest();
        mockFluentService = new MockFluentService();
        quest.exposeRegisterRing(MockFluentService.class, mockFluentService);
    }

    @Nested
    @DisplayName("use method tests")
    class UseMethodTests {
        @Test
        @DisplayName("Should successfully enter existing ring")
        void testUseSuccess() {
            try (MockedStatic<LogQuest> logMock = mockStatic(LogQuest.class)) {
                // When
                MockFluentService result = quest.use(MockFluentService.class);

                // Then
                assertSame(mockFluentService, result);
                logMock.verify(() -> LogQuest.info(
                        "The quest has used the ring: 'MockRing'"
                ));
            }
        }

        @Test
        @DisplayName("Should throw exception when ring not initialized")
        void testUseNoRing() {
            // Given
            quest.exposeRemoveRing(MockFluentService.class);

            // When/Then
            IllegalArgumentException exception = assertThrows(
                    IllegalArgumentException.class,
                    () -> quest.use(MockFluentService.class)
            );
            assertEquals(
                    "Ring not initialized: " + MockFluentService.class.getName(),
                    exception.getMessage()
            );
        }

        @Test
        @DisplayName("Should handle ring without TestService annotation")
        void testUseRingWithoutAnnotation() {
            // Given
            class NoAnnotationService extends FluentService {
            }
            quest.exposeRegisterRing(NoAnnotationService.class, new NoAnnotationService());

            // When/Then
            try (MockedStatic<LogQuest> logMock = mockStatic(LogQuest.class)) {
                NoAnnotationService result = quest.use(NoAnnotationService.class);

                assertNotNull(result);
                logMock.verify(() -> LogQuest.info(
                        "The quest has used the ring: '" +
                                NoAnnotationService.class.getName() + "'"
                ));
            }
        }
    }

    @Nested
    @DisplayName("artifact method tests")
    class ArtifactMethodTests {
        @Test
        @DisplayName("Should successfully retrieve artifact")
        void testArtifactSuccess() {
            // When
            String value = quest.exposeArtifact(MockFluentService.class, String.class);

            // Then
            assertEquals("mockValue", value);
        }

        @ParameterizedTest
        @NullSource
        @DisplayName("Should throw exception for null parameters")
        void testArtifactNullParameters(Class<?> nullParam) {
            // When/Then
            IllegalArgumentException exception = assertThrows(
                    IllegalArgumentException.class,
                    () -> quest.exposeArtifact(
                            nullParam == null ? null : MockFluentService.class,
                            nullParam == null ? String.class : null
                    )
            );
            assertEquals(
                    "Parameters ringType and artifactType must not be null.",
                    exception.getMessage()
            );
        }

        @Test
        @DisplayName("Should throw exception when ring is not found")
        void testArtifactRingNotFound() {
            // Given
            quest.exposeRemoveRing(MockFluentService.class);

            // When/Then
            IllegalArgumentException exception = assertThrows(
                    IllegalArgumentException.class,
                    () -> quest.exposeArtifact(MockFluentService.class, String.class)
            );
            assertEquals(
                    "Ring not initialized: " + MockFluentService.class.getName(),
                    exception.getMessage()
            );
        }

        @Test
        @DisplayName("Should throw IllegalArgumentException when ringType is null")
        void testArtifactNullRingType() {
            // When/Then
            IllegalArgumentException exception = assertThrows(
                    IllegalArgumentException.class,
                    () -> quest.exposeArtifact(null, String.class)
            );

            assertEquals(
                    "Parameters ringType and artifactType must not be null.",
                    exception.getMessage()
            );
        }

        @Test
        @DisplayName("Should throw IllegalArgumentException when artifactType is null")
        void testArtifactNullArtifactType() {
            // When/Then
            IllegalArgumentException exception = assertThrows(
                    IllegalArgumentException.class,
                    () -> quest.exposeArtifact(MockFluentService.class, null)
            );

            assertEquals(
                    "Parameters ringType and artifactType must not be null.",
                    exception.getMessage()
            );
        }

        @Test
        @DisplayName("Should throw IllegalArgumentException when no matching ring is found for cast")
        void testArtifactNoMatchingRing() {
            // Given
            quest.exposeRemoveRing(MockFluentService.class);

            // Create a new service class to test
            class UnregisteredService extends FluentService {
                public String testField = "testValue";
            }

            // When/Then
            IllegalArgumentException exception = assertThrows(
                    IllegalArgumentException.class,
                    () -> quest.exposeArtifact(UnregisteredService.class, String.class)
            );

            assertEquals(
                    "Ring not initialized: " + UnregisteredService.class.getName(),
                    exception.getMessage()
            );
        }

        @Test
        @DisplayName("Should retrieve the most specific String field in inheritance hierarchy")
        void testFieldRetrievalOrder() {
            // Given
            class BaseMockService extends FluentService {
                public String baseField = "baseValue";
            }

            class MidMockService extends BaseMockService {
                public String midField = "midValue";
            }

            class SubMockService extends MidMockService {
                public String subField = "subValue";
            }

            // Register the subclass
            SubMockService subService = new SubMockService();
            quest.exposeRegisterRing(SubMockService.class, subService);

            // When
            String retrievedField = quest.exposeArtifact(SubMockService.class, String.class);

            // Then
            assertEquals("subValue", retrievedField, "Should retrieve the most specific String field");
        }

        @Test
        @DisplayName("Should verify field type matching behavior")
        void testFieldTypeMatching() {
            // Given
            class ComplexService extends FluentService {
                public Number numberField = 42;
                public Integer integerField = 100;
                public String stringField = "testValue";
            }

            ComplexService service = new ComplexService();
            quest.exposeRegisterRing(ComplexService.class, service);

            // When/Then - verify Number retrieval
            Number numberValue = quest.exposeArtifact(ComplexService.class, Number.class);
            assertEquals(42, numberValue, "Should retrieve first Number field");

            // When/Then - verify Integer retrieval
            Integer integerValue = quest.exposeArtifact(ComplexService.class, Integer.class);
            assertEquals(Integer.valueOf(100), integerValue, "Should retrieve Integer field");
        }

        @Test
        @DisplayName("Should handle multiple fields of same type")
        void testMultipleFieldsOfSameType() {
            // Given
            class MultiFieldService extends FluentService {
                public String firstStringField = "first";
                public String secondStringField = "second";
            }

            MultiFieldService service = new MultiFieldService();
            quest.exposeRegisterRing(MultiFieldService.class, service);

            // When
            String retrievedField = quest.exposeArtifact(MultiFieldService.class, String.class);

            // Then
            assertEquals("first", retrievedField, "Should retrieve first String field in declaration order");
        }
    }

    @Nested
    @DisplayName("cast method tests")
    class CastMethodTests {
        @Test
        @DisplayName("Should successfully cast to ring")
        void testCastSuccess() {
            // When
            MockFluentService result = quest.exposeCast(MockFluentService.class);

            // Then
            assertSame(mockFluentService, result);
        }

        @Test
        @DisplayName("Should throw exception when ring not initialized")
        void testCastNoRing() {
            // Given
            quest.exposeRemoveRing(MockFluentService.class);

            // When/Then
            IllegalArgumentException exception = assertThrows(
                    IllegalArgumentException.class,
                    () -> quest.exposeCast(MockFluentService.class)
            );
            assertEquals(
                    "Ring not initialized: " + MockFluentService.class.getName(),
                    exception.getMessage()
            );
        }
    }

    @Nested
    @DisplayName("Complete method tests")
    class CompleteMethodTests {
        @Test
        @DisplayName("Should complete quest and clear quest holder")
        void testComplete() throws Exception {
            // Given
            CustomSoftAssertion softSpy = spy(new CustomSoftAssertion());
            Field softField = Quest.class.getDeclaredField("softAssertions");
            softField.setAccessible(true);
            softField.set(quest, softSpy);

            // When/Then
            try (MockedStatic<LogQuest> logMock = mockStatic(LogQuest.class);
                 MockedStatic<QuestHolder> holderMock = mockStatic(QuestHolder.class)) {

                quest.complete();

                // Verify interactions
                logMock.verify(() -> LogQuest.info("The quest has reached his end"));
                holderMock.verify(QuestHolder::clear);
                verify(softSpy, times(1)).assertAll();
            }
        }
    }

    @Nested
    @DisplayName("Utility method tests")
    class UtilityMethodTests {
        @Test
        @DisplayName("Should return storage")
        void testGetStorage() {
            // When
            Storage storage = quest.getStorage();

            // Then
            assertNotNull(storage);
        }

        @Test
        @DisplayName("Should return soft assertions")
        void testGetSoftAssertions() {
            // When
            CustomSoftAssertion softAssertions = quest.getSoftAssertions();

            // Then
            assertNotNull(softAssertions);
        }
    }

    @Nested
    @DisplayName("Ring management tests")
    class RingManagementTests {
        @Test
        @DisplayName("Should remove ring")
        void testRemoveRing() {
            // Given
            quest.exposeRemoveRing(MockFluentService.class);

            // When/Then
            IllegalArgumentException exception = assertThrows(
                    IllegalArgumentException.class,
                    () -> quest.use(MockFluentService.class)
            );
            assertEquals(
                    "Ring not initialized: " + MockFluentService.class.getName(),
                    exception.getMessage()
            );
        }
    }

    @Test
    @DisplayName("Should throw ReflectionException when no matching field type is found")
    void testArtifactNoMatchingFieldType() {
        // Given
        class NoMatchingFieldService extends FluentService {
            // Intentionally left without a String field
            private int someIntField = 42;
        }

        quest.exposeRegisterRing(NoMatchingFieldService.class, new NoMatchingFieldService());

        // When/Then
        ReflectionException exception = assertThrows(
                ReflectionException.class,
                () -> quest.exposeArtifact(NoMatchingFieldService.class, String.class)
        );

        // Use contains instead of exact match to handle anonymous class naming
        assertTrue(
                exception.getMessage().contains("No fields of type 'java.lang.String' found in class") &&
                        exception.getMessage().contains("NoMatchingFieldService"),
                "Unexpected exception message: " + exception.getMessage()
        );
    }

    @Test
    @DisplayName("Should throw ReflectionException when field value does not match expected type")
    void testArtifactFieldValueTypeMismatch() {
        // Given
        class TypeMismatchService extends FluentService {
            private Integer intField = 42;
        }

        quest.exposeRegisterRing(TypeMismatchService.class, new TypeMismatchService());

        // When/Then
        ReflectionException exception = assertThrows(
                ReflectionException.class,
                () -> quest.exposeArtifact(TypeMismatchService.class, String.class)
        );

        // Verify exception message
        assertTrue(
                exception.getMessage().contains("No fields of type 'java.lang.String' found in class") &&
                        exception.getMessage().contains("TypeMismatchService"),
                "Unexpected exception message: " + exception.getMessage()
        );
    }

    @Test
    @DisplayName("Should throw IllegalStateException when ring is null")
    void testArtifactNullRing() {
        // Given
        class NullRingService extends FluentService {}

        TestableQuest spyQuest = spy(quest);
        spyQuest.exposeRegisterRing(NullRingService.class, null);

        // When/Then
        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
                () -> spyQuest.exposeArtifact(NullRingService.class, String.class)
        );

        assertEquals(
                "Could not retrieve an instance of the specified ringType: " + NullRingService.class.getName(),
                exception.getMessage()
        );
    }

    @Test
    @DisplayName("Should handle empty field list in artifact retrieval")
    void testArtifactEmptyFieldList() {
        // Given
        class EmptyService extends FluentService {}
        quest.exposeRegisterRing(EmptyService.class, new EmptyService());

        // When/Then
        assertThrows(ReflectionException.class,
                () -> quest.exposeArtifact(EmptyService.class, String.class));
    }

    @Test
    @DisplayName("Should log warning when multiple artifacts found")
    void testArtifactMultipleFoundWarning() {
        try (MockedStatic<LogQuest> logMock = mockStatic(LogQuest.class)) {
            // Given
            class MultiFieldService extends FluentService {
                public String field1 = "first";
                public String field2 = "second";
            }
            quest.exposeRegisterRing(MultiFieldService.class, new MultiFieldService());

            // When
            quest.exposeArtifact(MultiFieldService.class, String.class);

            // Then
            logMock.verify(() -> LogQuest.warn(
                    anyString(), any(), any(), any()
            ));
        }
    }

    @Test
    @DisplayName("Should maintain separate rings per Quest instance")
    void testInstanceIsolation() {
        TestableQuest quest1 = new TestableQuest();
        TestableQuest quest2 = new TestableQuest();

        FluentService service1 = new MockFluentService();
        FluentService service2 = new MockFluentService();

        quest1.exposeRegisterRing(MockFluentService.class, service1);
        quest2.exposeRegisterRing(MockFluentService.class, service2);

        assertSame(service1, quest1.use(MockFluentService.class));
        assertSame(service2, quest2.use(MockFluentService.class));
    }
}