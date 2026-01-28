package io.cyborgcode.roa.framework.extension;

import io.cyborgcode.roa.framework.config.FrameworkConfigHolder;
import io.cyborgcode.roa.framework.decorators.DecoratorsFactory;
import io.cyborgcode.roa.framework.extension.mock.MockConfig;
import io.cyborgcode.roa.framework.extension.mock.MockNoRipper;
import io.cyborgcode.roa.framework.extension.mock.MockRipper;
import io.cyborgcode.roa.framework.log.LogQuest;
import io.cyborgcode.roa.framework.parameters.DataRipper;
import io.cyborgcode.roa.framework.quest.Quest;
import io.cyborgcode.roa.framework.quest.SuperQuest;
import io.cyborgcode.roa.framework.storage.Storage;
import io.cyborgcode.utilities.reflections.ReflectionUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.mockito.MockedStatic;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.lang.reflect.Method;
import java.util.Optional;
import java.util.function.Consumer;

import static io.cyborgcode.roa.framework.storage.StorageKeysTest.ARGUMENTS;
import static io.cyborgcode.roa.framework.storage.StoreKeys.QUEST;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@DisplayName("RipperMan Extension Tests")
class RipperManTest {

    private static final String TEST_METHOD = "testMethod";
    private static final String MOCK_TARGET = "MOCK_TARGET";

    @Nested
    @DisplayName("RipperMan execution tests")
    class RipperManExecutionTests {

        @Test
        @DisplayName("Should do nothing when no Ripper annotation is present")
        void afterTestExecution_NoRipperAnnotation_DoesNothing() throws Exception {
            // Given
            RipperMan ripperMan = new RipperMan();
            ExtensionContext context = mock(ExtensionContext.class);
            Method method = MockNoRipper.class.getMethod(TEST_METHOD);

            // When
            when(context.getTestMethod()).thenReturn(Optional.of(method));

            // Then
            assertDoesNotThrow(() -> ripperMan.afterTestExecution(context));
            verify(context).getTestMethod();
            verifyNoMoreInteractions(context);
        }

        @Test
        @DisplayName("Should throw exception when Quest is not found in global store")
        void afterTestExecution_QuestNull_ThrowsException() throws Exception {
            // Given
            RipperMan ripperMan = new RipperMan();
            ExtensionContext context = mock(ExtensionContext.class);
            Method method = MockRipper.class.getMethod(TEST_METHOD);

            // When
            when(context.getTestMethod()).thenReturn(Optional.of(method));
            when(context.getRoot()).thenReturn(context);

            ExtensionContext.Store store = mock(ExtensionContext.Store.class);
            when(context.getStore(ExtensionContext.Namespace.GLOBAL)).thenReturn(store);
            when(store.get(QUEST)).thenReturn(null);

            ApplicationContext appCtx = mock(ApplicationContext.class);

            try (MockedStatic<SpringExtension> springExt = mockStatic(SpringExtension.class)) {
                springExt.when(() -> SpringExtension.getApplicationContext(context)).thenReturn(appCtx);
                DecoratorsFactory decoratorsFactory = mock(DecoratorsFactory.class);
                when(appCtx.getBean(DecoratorsFactory.class)).thenReturn(decoratorsFactory);
                when(decoratorsFactory.decorate(null, SuperQuest.class)).thenReturn(null);

                // Then
                assertThrows(IllegalStateException.class, () -> ripperMan.afterTestExecution(context));
            }
        }

        @Test
        @DisplayName("Should process ripper logic successfully with valid inputs")
        void afterTestExecution_ProcessesRipperLogic() throws Exception {
            // Given
            RipperMan ripperMan = new RipperMan();
            ExtensionContext context = mock(ExtensionContext.class);
            Method method = MockRipper.class.getMethod(TEST_METHOD);

            // When
            when(context.getTestMethod()).thenReturn(Optional.of(method));
            when(context.getRoot()).thenReturn(context);

            ExtensionContext.Store store = mock(ExtensionContext.Store.class);
            when(context.getStore(ExtensionContext.Namespace.GLOBAL)).thenReturn(store);

            Quest quest = mock(Quest.class);
            when(store.get(QUEST)).thenReturn(quest);

            ApplicationContext appCtx = mock(ApplicationContext.class);
            try (MockedStatic<SpringExtension> springExt = mockStatic(SpringExtension.class)) {
                springExt.when(() -> SpringExtension.getApplicationContext(context)).thenReturn(appCtx);

                DecoratorsFactory decoratorsFactory = mock(DecoratorsFactory.class);
                when(appCtx.getBean(DecoratorsFactory.class)).thenReturn(decoratorsFactory);

                SuperQuest superQuest = mock(SuperQuest.class);
                when(decoratorsFactory.decorate(quest, SuperQuest.class)).thenReturn(superQuest);

                Storage storage = mock(Storage.class);
                when(superQuest.getStorage()).thenReturn(storage);

                Storage subStorage = mock(Storage.class);
                when(storage.sub(ARGUMENTS)).thenReturn(subStorage);
                doNothing().when(subStorage).createLateArguments();

                MockConfig mockConfig = new MockConfig();
                try (MockedStatic<FrameworkConfigHolder> configMock = mockStatic(FrameworkConfigHolder.class)) {
                    configMock.when(FrameworkConfigHolder::getFrameworkConfig).thenReturn(mockConfig);

                    @SuppressWarnings("unchecked")
                    Consumer<SuperQuest> consumer = mock(Consumer.class);

                    // ✅ Use real implementation instead of mocking DataRipper
                    DataRipper dataRipper = new FakeDataRipper(consumer);

                    try (MockedStatic<ReflectionUtil> reflectionMock = mockStatic(ReflectionUtil.class)) {
                        reflectionMock.when(() ->
                                                ReflectionUtil.findEnumImplementationsOfInterface(
                                                    eq(DataRipper.class),
                                                    eq(MOCK_TARGET),
                                                    eq(mockConfig.projectPackages())
                                                )
                        ).thenReturn(dataRipper);

                        try (MockedStatic<LogQuest> logTestMock = mockStatic(LogQuest.class)) {
                            ripperMan.afterTestExecution(context);

                            // Then
                            verify(subStorage).createLateArguments();
                            verify(consumer).accept(superQuest);

                            logTestMock.verify(() -> LogQuest.info(
                                eq("DataRipper processed target: '{}'."), eq(MOCK_TARGET)));
                        }
                    }
                }
            }
        }
    }

    // ✅ Helper test implementation instead of mocking DataRipper
    static class FakeDataRipper implements DataRipper {
        private final Consumer<SuperQuest> consumer;

        public FakeDataRipper(Consumer<SuperQuest> consumer) {
            this.consumer = consumer;
        }

        @Override
        public Consumer<SuperQuest> eliminate() {
            return consumer;
        }


        @Override
        public Enum<?> enumImpl() {
            return null;
        }


    }
}