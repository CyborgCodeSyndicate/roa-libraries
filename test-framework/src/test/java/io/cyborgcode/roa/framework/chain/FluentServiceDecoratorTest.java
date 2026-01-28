package io.cyborgcode.roa.framework.chain;

import io.cyborgcode.roa.framework.quest.Quest;
import io.cyborgcode.roa.framework.quest.SuperQuest;
import io.cyborgcode.roa.validator.core.AssertionResult;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("FluentServiceDecorator Tests")
class FluentServiceDecoratorTest {

    @Mock
    private FluentService mockFluentService;

    @Mock
    private SuperQuest mockQuest;

    @Mock
    private Quest originalQuest;

    private FluentServiceDecorator decorator;

    @BeforeEach
    void setUp() {
        decorator = new FluentServiceDecorator(mockFluentService);
    }

    @Test
    @DisplayName("setQuest should delegate to decorated service")
    void testSetQuest() {
        // When
        decorator.setQuest(mockQuest);

        // Then
        verify(mockFluentService).setQuest(mockQuest);
    }

    @Test
    @DisplayName("validation should delegate to decorated service")
    void testValidation() {
        // Given
        List<AssertionResult<Object>> results = List.of();

        // When
        decorator.validation(results);

        // Then
        verify(mockFluentService).validation(results);
    }

    @Test
    @DisplayName("postQuestSetupInitialization should delegate to decorated service")
    void testPostQuestSetupInitialization() {
        // When
        decorator.postQuestSetupInitialization();

        // Then
        verify(mockFluentService).postQuestSetupInitialization();
    }

    @Test
    @DisplayName("drop should delegate to decorated service")
    void testDrop() {
        // Given
        when(mockFluentService.drop()).thenReturn(originalQuest);

        // When
        Quest result = decorator.drop();

        // Then
        verify(mockFluentService).drop();
        assertSame(originalQuest, result);
    }

    @Test
    @DisplayName("validate(Consumer) should delegate to decorated service")
    void testValidateWithConsumer() {
        // Given
        @SuppressWarnings("unchecked")
        Consumer<SoftAssertions> consumer = mock(Consumer.class);
        // Return the decorator itself from validate to allow chaining
        doReturn(decorator).when(mockFluentService).validate(consumer);

        // When
        FluentChain result = decorator.validate(consumer);

        // Then
        verify(mockFluentService).validate(consumer);
        assertSame(decorator, result, "Should return decorator for chaining");
    }

    @Test
    @DisplayName("validate(Runnable) should delegate to decorated service")
    void testValidateWithRunnable() {
        // Given
        Runnable runnable = mock(Runnable.class);
        // Return the decorator itself from validate to allow chaining
        doReturn(decorator).when(mockFluentService).validate(runnable);

        // When
        FluentChain result = decorator.validate(runnable);

        // Then
        verify(mockFluentService).validate(runnable);
        assertSame(decorator, result, "Should return decorator for chaining");
    }

    @Test
    @DisplayName("complete should delegate to decorated service")
    void testComplete() {
        // When
        decorator.complete();

        // Then
        verify(mockFluentService).complete();
    }

    @Test
    @DisplayName("Decorator should work as FluentChain")
    void testAsFluentChain() {
        // Given
        FluentChain chain = decorator;
        when(mockFluentService.drop()).thenReturn(originalQuest);

        // When
        Quest result = chain.drop();

        // Then
        verify(mockFluentService).drop();
        assertSame(originalQuest, result);
    }

    @Test
    @DisplayName("Method chaining through decorator should work")
    void testMethodChaining() {
        // Given
        Runnable runnable = mock(Runnable.class);
        doReturn(decorator).when(mockFluentService).validate(runnable);

        // When - Chain methods
        decorator.validate(runnable).complete();

        // Then
        verify(mockFluentService).validate(runnable);
        verify(mockFluentService).complete();
    }
}