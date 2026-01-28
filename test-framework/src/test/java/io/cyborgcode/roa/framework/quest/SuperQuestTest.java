package io.cyborgcode.roa.framework.quest;

import io.cyborgcode.roa.framework.assertion.CustomSoftAssertion;
import io.cyborgcode.roa.framework.quest.mock.MockFluentService;
import io.cyborgcode.roa.framework.storage.Storage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SuperQuestTest {

    private Quest quest;
    private SuperQuest superQuest;
    private MockFluentService mockFluentService;

    @BeforeEach
    void setUp() {
        quest = new Quest();
        superQuest = new SuperQuest(quest);
        mockFluentService = new MockFluentService();
    }

    @Nested
    @DisplayName("Artifact retrieval tests")
    class ArtifactTests {
        @Test
        @DisplayName("Should retrieve artifact from ring")
        void testArtifact() {
            // Given
            quest.registerRing(MockFluentService.class, mockFluentService);

            // When
            String value = superQuest.artifact(MockFluentService.class, String.class);

            // Then
            assertEquals("mockValue", value, "Should retrieve the correct artifact value");
        }
    }

    @Nested
    @DisplayName("Ring management tests")
    class RingManagementTests {
        @Test
        @DisplayName("Should cast to registered ring")
        void testRegisterRingAndCast() {
            // Given
            // Looking at the original test, we need to manually share the rings map
            quest.registerRing(MockFluentService.class, mockFluentService);
            try {
                // Get the rings map from the quest
                Field ringsField = Quest.class.getDeclaredField("rings");
                ringsField.setAccessible(true);
                Map<?, ?> ringsMap = (Map<?, ?>) ringsField.get(quest);

                // Set it on the superQuest
                ringsField.set(superQuest, ringsMap);

                // When
                MockFluentService casted = superQuest.cast(MockFluentService.class);

                // Then
                assertSame(mockFluentService, casted, "Cast should return the same instance");
            } catch (NoSuchFieldException | IllegalAccessException e) {
                fail("Failed to access rings field: " + e.getMessage());
            }
        }

        @Test
        @DisplayName("Should register and remove ring correctly")
        void testRemoveRing() {
            // Given
            superQuest.registerRing(MockFluentService.class, mockFluentService);

            // When
            superQuest.removeRing(MockFluentService.class);

            // Then
            IllegalArgumentException exception = assertThrows(
                    IllegalArgumentException.class,
                    () -> superQuest.cast(MockFluentService.class),
                    "Should throw exception when attempting to cast to removed ring"
            );

            assertTrue(exception.getMessage().contains("Ring not initialized"),
                    "Exception message should indicate ring not initialized");
        }

        @Test
        @DisplayName("Should maintain separate state for different SuperQuest instances")
        void testInstanceIsolation() {
            Quest quest1 = new Quest();
            Quest quest2 = new Quest();
            SuperQuest sq1 = new SuperQuest(quest1);
            SuperQuest sq2 = new SuperQuest(quest2);

            sq1.registerRing(MockFluentService.class, new MockFluentService());

            assertThrows(IllegalArgumentException.class,
                    () -> sq2.cast(MockFluentService.class));
        }
    }

    @Nested
    @DisplayName("Utility method tests")
    class UtilityMethodTests {
        @Test
        @DisplayName("Should access original Quest's storage")
        void testGetStorage() {
            // When
            Storage storageFromQuest = quest.getStorage();
            Storage storageFromSuperQuest = superQuest.getStorage();

            // Then
            assertSame(storageFromQuest, storageFromSuperQuest,
                    "Storage from SuperQuest should be the same as from Quest");
        }

        @Test
        @DisplayName("Should access original Quest's soft assertions")
        void testGetSoftAssertions() {
            // When
            CustomSoftAssertion assertionsFromQuest = quest.getSoftAssertions();
            CustomSoftAssertion assertionsFromSuperQuest = superQuest.getSoftAssertions();

            // Then
            assertSame(assertionsFromQuest, assertionsFromSuperQuest,
                    "SoftAssertions from SuperQuest should be the same as from Quest");
        }
    }

    @Test
    @DisplayName("Should get the original Quest instance")
    void testGetOriginal() {
        // When/Then
        assertSame(quest, superQuest.getOriginal(),
                "getOriginal should return the Quest instance provided in constructor");
    }

    @Nested
    @DisplayName("Delegate method tests")
    class DelegateMethodTests {
        @Spy
        private Quest spyQuest;

        @BeforeEach
        void setUpSpy() {
            superQuest = new SuperQuest(spyQuest);
        }

        @Test
        @DisplayName("Should delegate complete method to original Quest")
        void testCompleteDelegate() {
            // When
            superQuest.complete();

            // Then
            verify(spyQuest).complete();
        }

        @Test
        @DisplayName("Should delegate enters method to original Quest")
        void testEntersDelegate() {
            // Given
            spyQuest.registerRing(MockFluentService.class, mockFluentService);

            // When
            superQuest.use(MockFluentService.class);

            // Then
            verify(spyQuest).use(MockFluentService.class);
        }
    }
}