package io.cyborgcode.roa.framework.log;

import io.cyborgcode.utilities.logging.LogCore;
import io.cyborgcode.utilities.logging.LogCyborg;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class LogQuestTest {
    // Constants for test data
    static final String INFO_MESSAGE = "info message";
    static final String WARN_MESSAGE = "warn message";
    static final String ERROR_MESSAGE = "error message";
    static final String DEBUG_MESSAGE = "debug message";
    static final String TRACE_MESSAGE = "trace message";
    static final String STEP_MESSAGE = "step message";
    static final String VALIDATION_MESSAGE = "validation message";
    static final String EXTENDED_MESSAGE = "extended message";

    static final String EXTENDED_LOGGING = "extended.logging";
    static final String TRUE = "true";

    static final String ARG_1 = "arg1";
    static final String ARG_2 = "arg2";
    static final String ARG_3 = "arg3";
    static final String ARG_4 = "arg4";
    static final String ARG_5 = "arg5";
    static final String ARG_6 = "arg6";
    static final String ARG_7 = "arg7";
    static final String ARG_8 = "arg8";

    @Mock
    private Logger mockLogger;
    @Mock
    private Marker mockMarker;

    @BeforeEach
    void setUp() throws Exception {
        clearLogQuestInstance();
        clearExtendedLogging();
    }

    @AfterEach
    void tearDown() throws Exception {
        clearLogQuestInstance();
        clearExtendedLogging();
        System.clearProperty(EXTENDED_LOGGING);
    }

    @Test
    @DisplayName("Test info log method")
    void testInfoLog() {
        try (MockedStatic<LogCyborg> mockedLog = Mockito.mockStatic(LogCyborg.class)) {
            // Given: Mock logger and marker are setup
            setupLoggerMocks(mockedLog, mockLogger, mockMarker);
            Object[] args = {ARG_1, 100};

            // When: info log is called
            LogQuest.info(INFO_MESSAGE, args);

            // Then: verify logger was called with expected parameters
            verify(mockLogger, times(1)).info(mockMarker, INFO_MESSAGE, args);
        }
    }

    @Test
    @DisplayName("Test warn log method")
    void testWarnLog() {
        try (MockedStatic<LogCyborg> mockedLog = Mockito.mockStatic(LogCyborg.class)) {
            // Given: Mock logger and marker are setup
            setupLoggerMocks(mockedLog, mockLogger, mockMarker);
            Object[] args = {ARG_2, 200};

            // When: warn log is called
            LogQuest.warn(WARN_MESSAGE, args);

            // Then: verify logger was called with expected parameters
            verify(mockLogger, times(1)).warn(mockMarker, WARN_MESSAGE, args);
        }
    }

    @Test
    @DisplayName("Test error log method")
    void testErrorLog() {
        try (MockedStatic<LogCyborg> mockedLog = Mockito.mockStatic(LogCyborg.class)) {
            // Given: Mock logger and marker are setup
            setupLoggerMocks(mockedLog, mockLogger, mockMarker);
            Object[] args = {ARG_3, 300};

            // When: error log is called
            LogQuest.error(ERROR_MESSAGE, args);

            // Then: verify logger was called with expected parameters
            verify(mockLogger, times(1)).error(mockMarker, ERROR_MESSAGE, args);
        }
    }

    @Test
    @DisplayName("Test debug log method")
    void testDebugLog() {
        try (MockedStatic<LogCyborg> mockedLog = Mockito.mockStatic(LogCyborg.class)) {
            // Given: Mock logger and marker are setup
            setupLoggerMocks(mockedLog, mockLogger, mockMarker);
            Object[] args = {ARG_4, 400};

            // When: debug log is called
            LogQuest.debug(DEBUG_MESSAGE, args);

            // Then: verify logger was called with expected parameters
            verify(mockLogger, times(1)).debug(mockMarker, DEBUG_MESSAGE, args);
        }
    }

    @Test
    @DisplayName("Test trace log method")
    void testTraceLog() {
        try (MockedStatic<LogCyborg> mockedLog = Mockito.mockStatic(LogCyborg.class)) {
            // Given: Mock logger and marker are setup
            setupLoggerMocks(mockedLog, mockLogger, mockMarker);
            Object[] args = {ARG_5, 500};

            // When: trace log is called
            LogQuest.trace(TRACE_MESSAGE, args);

            // Then: verify logger was called with expected parameters
            verify(mockLogger, times(1)).trace(mockMarker, TRACE_MESSAGE, args);
        }
    }

    @Test
    @DisplayName("Test step log method")
    void testStepLog() {
        try (MockedStatic<LogCyborg> mockedLog = Mockito.mockStatic(LogCyborg.class)) {
            // Given: Mock logger and marker are setup, and STEP level exists
            setupLoggerMocks(mockedLog, mockLogger, mockMarker);
            Object[] args = {ARG_6, 600};
            Level stepLevel = Level.forName("STEP", 350);

            // When: step log is called
            LogQuest.step(STEP_MESSAGE, args);

            // Then: verify logger was called with STEP level and expected parameters
            verify(mockLogger, times(1)).log(stepLevel, mockMarker, STEP_MESSAGE, args);
        }
    }

    @Test
    @DisplayName("Test validation log method")
    void testValidationLog() {
        try (MockedStatic<LogCyborg> mockedLog = Mockito.mockStatic(LogCyborg.class)) {
            // Given: Mock logger and marker are setup, and VALIDATION level exists
            setupLoggerMocks(mockedLog, mockLogger, mockMarker);
            Object[] args = {ARG_7, 700};
            Level validationLevel = Level.forName("VALIDATION", 350);

            // When: validation log is called
            LogQuest.validation(VALIDATION_MESSAGE, args);

            // Then: verify logger was called with VALIDATION level and expected parameters
            verify(mockLogger, times(1)).log(validationLevel, mockMarker, VALIDATION_MESSAGE, args);
        }
    }

    @Test
    @DisplayName("Test extended log method when disabled")
    void testExtendedLogDisabled() {
        try (MockedStatic<LogCyborg> mockedLog = Mockito.mockStatic(LogCyborg.class)) {
            // Given: Mock logger and marker are setup, and extended logging is disabled
            setupLoggerMocks(mockedLog, mockLogger, mockMarker);
            System.clearProperty(EXTENDED_LOGGING);
            Object[] args = {ARG_8, 800};

            // When: extended log is called
            LogQuest.extended(EXTENDED_MESSAGE, args);

            // Then: verify no logging occurred
            verify(mockLogger, times(0)).log(
                    any(Level.class),
                    eq(mockMarker),
                    any(String.class),
                    any(Object[].class)
            );
        }
    }

    @Test
    @DisplayName("Test extended log method when enabled")
    void testExtendedLogEnabled() {
        try (MockedStatic<LogCyborg> mockedLog = Mockito.mockStatic(LogCyborg.class)) {
            // Given: Mock logger and marker are setup, extended logging is enabled, and EXTENDED level exists
            setupLoggerMocks(mockedLog, mockLogger, mockMarker);
            System.setProperty(EXTENDED_LOGGING, TRUE);
            Object[] args = {ARG_8, 800};
            Level extendedLevel = Level.forName("EXTENDED", 450);

            // When: extended log is called
            LogQuest.extended(EXTENDED_MESSAGE, args);

            // Then: verify logger was called with EXTENDED level and expected parameters
            verify(mockLogger, times(1)).log(extendedLevel, mockMarker, EXTENDED_MESSAGE, args);
        }
    }

    // Reflection-based methods to reset static state
    private void clearLogQuestInstance() throws Exception {
        Field instanceField = LogQuest.class.getDeclaredField("instance");
        instanceField.setAccessible(true);
        instanceField.set(null, null);
    }

    private void clearExtendedLogging() throws Exception {
        Field extendedField = LogCore.class.getDeclaredField("extendedLogging");
        extendedField.setAccessible(true);
        extendedField.set(null, null);
    }

    private void setupLoggerMocks(MockedStatic<LogCyborg> mockedLog, Logger mockLogger, Marker mockMarker) {
        mockedLog.when(() -> LogCyborg.getLogger("ROA.TEST")).thenReturn(mockLogger);
        mockedLog.when(() -> LogCyborg.registerMarker("QUEST")).thenReturn(mockMarker);
    }
}