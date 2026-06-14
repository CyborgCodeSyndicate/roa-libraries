package io.cyborgcode.roa.framework.extension;

import io.cyborgcode.roa.framework.allure.CustomAllureListener;
import io.cyborgcode.roa.framework.allure.StepType;
import io.cyborgcode.roa.framework.quest.SuperQuest;
import io.cyborgcode.roa.framework.storage.Storage;
import io.cyborgcode.roa.framework.storage.StorageKeysTest;
import io.cyborgcode.roa.framework.util.AllureStepHelper;
import io.cyborgcode.roa.framework.util.ResourceLoader;
import io.cyborgcode.roa.framework.util.TestContextManager;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import org.apache.logging.log4j.ThreadContext;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import static io.cyborgcode.roa.framework.storage.StoreKeys.HTML;
import static io.cyborgcode.roa.framework.storage.StoreKeys.START_TIME;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EpilogueTest {

    @Mock
    private ExtensionContext mockContext;

    @Mock
    private ExtensionContext.Store mockStore;

    @Mock
    private SuperQuest mockSuperQuest;

    @Mock
    private Storage subStorage;

    private Epilogue epilogue;

    private MockedStatic<CustomAllureListener> customAllureMock;
    private MockedStatic<ResourceLoader> resourceLoaderMock;
    private MockedStatic<TestContextManager> testContextManagerMock;

    @BeforeEach
    void setUp() throws NoSuchMethodException {
        epilogue = spy(new Epilogue());

        // Initialize static mocks *within* each test setup
        customAllureMock = mockStatic(CustomAllureListener.class);
        resourceLoaderMock = mockStatic(ResourceLoader.class);
        testContextManagerMock = mockStatic(TestContextManager.class);

        testContextManagerMock.when(() -> TestContextManager.getSuperQuest(mockContext))
              .thenReturn(mockSuperQuest);

        Storage dummyStorage = mock(Storage.class);
        when(mockSuperQuest.getStorage()).thenReturn(dummyStorage);
        when(dummyStorage.sub(eq(StorageKeysTest.ARGUMENTS))).thenReturn(subStorage);
        when(subStorage.getData()).thenReturn(new HashMap<>());

        resourceLoaderMock.when(() -> ResourceLoader.loadResourceFile(anyString()))
                .thenReturn("<html><body>{{testName}} {{className}}</body></html>");

        when(mockContext.getStore(ExtensionContext.Namespace.GLOBAL)).thenReturn(mockStore);
        when(mockStore.get(eq(START_TIME), eq(Long.class))).thenReturn(123L);
        when(mockContext.getExecutionException()).thenReturn(Optional.empty());

        Method mockMethod = EpilogueTest.class.getDeclaredMethod("shouldStopStep_WhenActiveStepIsNotTearDown");
        lenient().when(mockContext.getRequiredTestMethod()).thenReturn(mockMethod);
        lenient().when(mockContext.getRequiredTestClass()).thenReturn((Class) EpilogueTest.class);
        lenient().when(mockContext.getDisplayName()).thenReturn("Epilogue test");
    }

    @AfterEach
    void tearDown() {
        if (customAllureMock != null) {
            customAllureMock.close();
        }
        if (resourceLoaderMock != null) {
            resourceLoaderMock.close();
        }
        if (testContextManagerMock != null) {
            testContextManagerMock.close();
        }

        ThreadContext.clearAll();
    }

    @Test
    @DisplayName("Should stop step when active step is NOT TEAR_DOWN")
    void shouldStopStep_WhenActiveStepIsNotTearDown() {
        customAllureMock.when(CustomAllureListener::getActiveStepName)
              .thenReturn("Some Other Step");

        customAllureMock.when(() -> CustomAllureListener.isStepActive("Some Other Step"))
              .thenReturn(false, true);

        List<String> htmlContent = new ArrayList<>(List.of("<td>Some content</td>", "<td>Other content</td>"));
        when(mockStore.get(eq(HTML), eq(List.class))).thenReturn(htmlContent);

        epilogue.afterTestExecution(mockContext);

        customAllureMock.verify(CustomAllureListener::stopStep, times(2));
        customAllureMock.verify(() -> CustomAllureListener.startStep(StepType.TEAR_DOWN), times(1));
    }

    @Test
    @DisplayName("Should NOT stop step when active step IS TEAR_DOWN")
    void shouldNotStopStep_WhenActiveStepIsTearDown() {
        customAllureMock.when(CustomAllureListener::getActiveStepName)
              .thenReturn(StepType.TEAR_DOWN.getDisplayName());

        customAllureMock.when(() -> CustomAllureListener.isStepActive(StepType.TEAR_DOWN.getDisplayName()))
              .thenReturn(true);

        List<String> htmlContent = new ArrayList<>(List.of("<td>Some content</td>", "<td>Other content</td>"));
        when(mockStore.get(eq(HTML), eq(List.class))).thenReturn(htmlContent);

        epilogue.afterTestExecution(mockContext);

        customAllureMock.verify(CustomAllureListener::stopStep, times(1));
    }

    @Test
    @DisplayName("Should create new list when html list don't exist")
    void shouldCreateNewList_WhenHtmlListDontExist() {
        try (MockedStatic<AllureStepHelper> allureStepHelperMockedStatic = mockStatic(AllureStepHelper.class)) {
            customAllureMock.when(CustomAllureListener::getActiveStepName)
                  .thenReturn(StepType.TEAR_DOWN.getDisplayName());

            customAllureMock.when(() -> CustomAllureListener.isStepActive(StepType.TEAR_DOWN.getDisplayName()))
                  .thenReturn(true);

            when(mockStore.get(eq(HTML), eq(List.class))).thenReturn(null);
            when(mockContext.getExecutionException()).thenReturn(Optional.of(new RuntimeException("Test failure")));

            Epilogue spyEpilogue = spy(new Epilogue());

            spyEpilogue.afterTestExecution(mockContext);

            ArgumentCaptor<List> captor = ArgumentCaptor.forClass(List.class);
            verify(mockStore, atLeastOnce()).put(eq(HTML), captor.capture());

            List capturedHtmlList = captor.getValue();

            assertNotNull(capturedHtmlList);
            assertFalse(capturedHtmlList.isEmpty());

            allureStepHelperMockedStatic.verify(() ->
                  AllureStepHelper.logTestOutcome(
                        nullable(String.class),
                        eq("FAILED"),
                        anyLong(),
                        any(Throwable.class)
                  )
            );
        }
    }
}