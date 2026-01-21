package io.cyborgcode.roa.framework.util;

import io.cyborgcode.utilities.config.ConfigSource;
import io.cyborgcode.utilities.config.PropertyConfig;
import io.cyborgcode.roa.framework.config.FrameworkConfig;
import io.cyborgcode.roa.framework.config.FrameworkConfigHolder;
import io.cyborgcode.roa.framework.log.LogQuest;
import io.cyborgcode.utilities.reflections.ReflectionUtil;
import io.qameta.allure.Allure;
import java.nio.charset.StandardCharsets;
import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigCache;
import org.apache.logging.log4j.ThreadContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.*;
import java.lang.annotation.*;
import java.lang.reflect.Method;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

import static io.cyborgcode.roa.framework.storage.StoreKeys.HTML;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AllureStepHelperTest {

    @Mock
    private ExtensionContext mockContext;

    @Mock
    private ExtensionContext.Store mockStore;

    @BeforeEach
    void resetEnvironmentInitializationFlag() throws Exception {
        Field field = AllureStepHelper.class.getDeclaredField("ENV_INITIALIZED");
        field.setAccessible(true);
        AtomicBoolean flag = (AtomicBoolean) field.get(null);
        flag.set(false);
        System.clearProperty("allure.results.directory");
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.METHOD})
    public @interface CustomTestAnnotation {
        String value();
    }

    @CustomTestAnnotation("SomeValue")
    public void customAnnotatedTestMethod() {
    }

    // Config with a method that lacks a key annotation
    public interface MissingKeyConfig extends PropertyConfig {
        String noKey();
    }

    @ConfigSource("noKeySource")
    public static class MissingKeyConfigImpl implements MissingKeyConfig {
        public String noKey() {
            return "valueShouldBeIgnored";
        }
    }

    // Config with a key returning whitespace
    public interface WhitespaceValueConfig extends PropertyConfig {
        @Config.Key("white.key")
        String getWhiteValue();
    }

    @ConfigSource("whitespaceSource")
    public static class WhitespaceValueConfigImpl implements WhitespaceValueConfig {
        public String getWhiteValue() {
            return "   ";
        }
    }

    // General dummy property config with typical keys
    @ConfigSource("dummy")
    public static class BasicPropertyConfig implements PropertyConfig {

        @Config.Key("browser")
        String browser() {
            return "chrome";
        }

        @Config.Key("env")
        String env() {
            return "staging";
        }

        @Config.Key("features")
        List<String> features() {
            return List.of("login", "checkout");
        }
    }

    // Simple config interface with a key
    public interface SimpleKeyedConfig extends PropertyConfig {
        @Config.Key("test.key")
        String testMethod();
    }

    @ConfigSource("testSource")
    public static class SimpleKeyedConfigImpl implements SimpleKeyedConfig {
        @Override
        public String testMethod() {
            return "value123";
        }
    }

    // Config interface and class returning an empty string
    public interface EmptyValueConfig {
        @Config.Key("empty.key")
        String testMethod();
    }

    public static class EmptyValueConfigImpl implements PropertyConfig, EmptyValueConfig {
        @Override
        public String testMethod() {
            return "";
        }
    }

    // Config that throws an exception on method call
    @ConfigSource(value = "testSource")
    public class ExceptionThrowingConfig implements PropertyConfig {

        @Config.Key("exception.key")
        public String getExceptionKey() throws Exception {
            throw new Exception("Simulating an exception during method invocation");
        }
    }

    //setDescription - START
    @Test
    @DisplayName("Should set Allure description using HTML content from context store")
    void shouldSetAllureHtmlDescriptionFromContext() {
        // Given
        List<String> htmlContent = List.of("<td>Some content</td>", "<td>Other content</td>");
        when(mockContext.getStore(ExtensionContext.Namespace.GLOBAL)).thenReturn(mockStore);
        when(mockStore.get(HTML)).thenReturn(htmlContent);

        // When
        try (MockedStatic<Allure> mockedAllure = mockStatic(Allure.class)) {
            AllureStepHelper.setDescription(mockContext);

            // Then
            String expectedHtml = "<div style='margin: 20px;'><td>Some content</td><td>Other content</td></div>";
            mockedAllure.verify(() -> Allure.descriptionHtml(eq(expectedHtml)), times(1));
        }
    }
    //setDescription - END

    //attachFilteredLogsToAllure - START
    @Test
    @DisplayName("Should attach filtered logs to Allure when test name is provided")
    void shouldAttachFilteredLogsToAllureWhenTestNameProvided() {
        // Given
        String testName = "testScenario";

        // When
        try (MockedStatic<Allure> mockedAllure = mockStatic(Allure.class)) {
            AllureStepHelper.attachFilteredLogsToAllure(testName);

            // Then
            mockedAllure.verify(() ->
                            Allure.addAttachment(anyString(), eq("text/plain"), anyString(), eq(".log")),
                    times(1)
            );
        }
    }

    @Test
    @DisplayName("Should attach message when test name is null")
    void shouldAttachMessageWhenTestNameIsNull() {
        // When
        try (MockedStatic<Allure> mockedAllure = mockStatic(Allure.class)) {
            AllureStepHelper.attachFilteredLogsToAllure(null);

            // Then
            mockedAllure.verify(() ->
                            Allure.addAttachment(
                                    eq("Filtered Logs"),
                                    eq("text/plain"),
                                    eq("Test name is not available."),
                                    eq(".log")
                            ),
                    times(1)
            );
        }
    }

    @Test
    @DisplayName("Should attach message when test name is empty")
    void shouldAttachMessageWhenTestNameIsEmpty() {
        // When
        try (MockedStatic<Allure> mockedAllure = mockStatic(Allure.class)) {
            AllureStepHelper.attachFilteredLogsToAllure("");

            // Then
            mockedAllure.verify(() ->
                            Allure.addAttachment(
                                    eq("Filtered Logs"),
                                    eq("text/plain"),
                                    eq("Test name is not available."),
                                    eq(".log")
                            ),
                    times(1)
            );
        }
    }

    @Test
    @DisplayName("Should attach fallback message when no logs are found for test")
    void shouldAttachFallbackMessageWhenNoLogsAreFound() {
        // Given
        String testName = "nonExistentScenario";
        System.clearProperty("logFileName");

        // When
        try (MockedStatic<Allure> mockedAllure = mockStatic(Allure.class)) {
            AllureStepHelper.attachFilteredLogsToAllure(testName);

            // Then
            mockedAllure.verify(() ->
                            Allure.addAttachment(
                                    eq("Filtered Logs for Test: nonExistentScenario"),
                                    eq("text/plain"),
                                    eq("No logs found for test: nonExistentScenario"),
                                    eq(".log")
                            ),
                    times(1)
            );
        }
    }

    @Test
    @DisplayName("Should attach only matching log lines when logs exist for the test")
    void shouldAttachOnlyMatchingLogLines() throws IOException {
        // Given
        String testName = "myScenario";
        String testIdentifier = "[scenario=" + testName + "]";
        String matchingLine1 = testIdentifier + " Log line 1";
        String matchingLine2 = testIdentifier + " Log line 2";

        Path tempLogFile = Files.createTempFile("logfile", ".log");
        Files.write(tempLogFile, List.of(
                "[scenario=someOtherScenario] Not related log",
                matchingLine1,
                matchingLine2
        ));
        System.setProperty("logFileName", tempLogFile.toAbsolutePath().toString());

        // When
        try (MockedStatic<Allure> mockedAllure = mockStatic(Allure.class)) {
            AllureStepHelper.attachFilteredLogsToAllure(testName);

            // Then
            String expectedAttachment = matchingLine1 + System.lineSeparator() + matchingLine2;

            mockedAllure.verify(() ->
                    Allure.addAttachment(
                            eq("Filtered Logs for Test: " + testName),
                            eq("text/plain"),
                            eq(expectedAttachment),
                            eq(".log")
                    )
            );
        } finally {
            Files.deleteIfExists(tempLogFile);
        }
    }
    //attachFilteredLogsToAllure - END


    //logTestOutcome - START
    @Test
    @DisplayName("Should log SUCCESS outcome without exception")
    void shouldLogSuccessOutcomeWithoutException() {
        // Given
        String testName = "testScenario";
        long durationInSeconds = 5;

        // When
        try (MockedStatic<LogQuest> mockedLogTest = mockStatic(LogQuest.class)) {
            AllureStepHelper.logTestOutcome(testName, "SUCCESS", durationInSeconds, null);

            // Then
            mockedLogTest.verify(() ->
                            LogQuest.info(anyString(), eq(testName), eq("SUCCESS"), eq(durationInSeconds)),
                    times(1)
            );
        }
    }

    @Test
    @DisplayName("Should log FAILED outcome and debug throwable when exception is provided")
    void shouldLogFailedOutcomeWithException() {
        // Given
        String testName = "testScenario";
        long durationInSeconds = 5;
        Throwable throwable = new RuntimeException("Test failed");

        // When
        try (MockedStatic<LogQuest> mockedLogTest = mockStatic(LogQuest.class)) {
            AllureStepHelper.logTestOutcome(testName, "FAILED", durationInSeconds, throwable);

            // Then
            mockedLogTest.verify(() ->
                            LogQuest.info(anyString(), eq(testName), eq("FAILED"), eq(durationInSeconds)),
                    times(1)
            );
            mockedLogTest.verify(() ->
                            LogQuest.debug(eq("Failure reason:"), eq(throwable)),
                    times(1)
            );
        }
    }
    //logTestOutcome - END

    //setUpTestMetadata - START
    @Test
    @DisplayName("Should set up test metadata and store rendered HTML when HTML list is already present")
    void shouldSetUpTestMetadata_WhenHtmlListIsAlreadyPresent() {
        // Given
        try (MockedStatic<ResourceLoader> mockedStatic = mockStatic(ResourceLoader.class)) {
            String htmlTemplate = "<html><body>{{testName}} {{className}} {{methodAnnotations}}</body></html>";
            mockedStatic.when(() -> ResourceLoader.loadResourceFile("allure/html/test-details.html"))
                    .thenReturn(htmlTemplate);

            Method mockMethod = mock(Method.class);
            when(mockMethod.getName()).thenReturn("testMethod");

            Annotation[] annotations = {};
            when(mockMethod.getAnnotations()).thenReturn(annotations);

            Class<?> testClass = AllureStepHelperTest.class;
            when(mockContext.getRequiredTestMethod()).thenReturn(mockMethod);
            when(mockContext.getRequiredTestClass()).thenReturn((Class) testClass);
            when(mockContext.getStore(ExtensionContext.Namespace.GLOBAL)).thenReturn(mockStore);
            List<String> htmlList = new ArrayList<>();
            when(mockStore.get(HTML, List.class)).thenReturn(htmlList);

            // When
            AllureStepHelper.setUpTestMetadata(mockContext);

            // Then
            verify(mockStore, times(1)).put(eq(HTML), eq(htmlList));
        }
    }

    @Test
    @DisplayName("Should initialize new HTML list if not present in store")
    void shouldInitializeHtmlList_WhenHtmlListIsNull() throws NoSuchMethodException {
        // Given
        when(mockContext.getStore(ExtensionContext.Namespace.GLOBAL)).thenReturn(mockStore);
        when(mockStore.get(HTML, List.class)).thenReturn(null);

        try (MockedStatic<ResourceLoader> mockedResourceLoader = mockStatic(ResourceLoader.class)) {
            String mockHtmlTemplate = "<html>{{testName}}</html>";
            mockedResourceLoader.when(() -> ResourceLoader.loadResourceFile("allure/html/test-details.html"))
                    .thenReturn(mockHtmlTemplate);

            Method mockMethod = AllureStepHelperTest.class.getDeclaredMethod("shouldInitializeHtmlList_WhenHtmlListIsNull");
            when(mockContext.getRequiredTestMethod()).thenReturn(mockMethod);
            when(mockContext.getRequiredTestClass()).thenReturn((Class) AllureStepHelperTest.class);

            // When
            AllureStepHelper.setUpTestMetadata(mockContext);

            // Then
            verify(mockStore).put(eq(HTML), anyList());
        }
    }

    @Test
    @DisplayName("Should capture and store method annotations in HTML template")
    void shouldRenderCustomAnnotationsIntoHtmlTemplate() throws NoSuchMethodException {
        // Given
        try (MockedStatic<ResourceLoader> mockedResourceLoader = mockStatic(ResourceLoader.class)) {
            String template = "<html>{{methodAnnotations}}</html>";
            mockedResourceLoader.when(() -> ResourceLoader.loadResourceFile("allure/html/test-details.html"))
                    .thenReturn(template);

            Method annotatedMethod = AllureStepHelperTest.class.getDeclaredMethod("customAnnotatedTestMethod");
            when(mockContext.getRequiredTestMethod()).thenReturn(annotatedMethod);
            when(mockContext.getRequiredTestClass()).thenReturn((Class) AllureStepHelperTest.class);
            when(mockContext.getStore(ExtensionContext.Namespace.GLOBAL)).thenReturn(mockStore);

            List<String> htmlList = new ArrayList<>();
            when(mockStore.get(HTML, List.class)).thenReturn(htmlList);

            // When
            AllureStepHelper.setUpTestMetadata(mockContext);

            // Then
            assertFalse(htmlList.isEmpty());
            assertTrue(htmlList.get(0).contains("CustomTestAnnotation"));
        }
    }
    //setUpTestMetadata - END

    //setupTestContext - START
    @Test
    @DisplayName("Should set up test context by storing test name in thread context")
    void shouldStoreTestNameInThreadContext_WhenTestClassAndMethodArePresent() throws NoSuchMethodException {
        // Given
        when(mockContext.getTestClass()).thenReturn(Optional.of(AllureStepHelperTest.class));
        when(mockContext.getTestMethod())
                .thenReturn(Optional.of(AllureStepHelperTest.class.getDeclaredMethod("shouldSetAllureHtmlDescriptionFromContext")));
        when(mockContext.getStore(ExtensionContext.Namespace.GLOBAL)).thenReturn(mockStore);

        // When
        try (MockedStatic<ThreadContext> mockedThreadContext = mockStatic(ThreadContext.class)) {
            AllureStepHelper.setupTestContext(mockContext);

            // Then
            mockedThreadContext.verify(() ->
                            ThreadContext.put(eq("testName"), eq("AllureStepHelperTest.shouldSetAllureHtmlDescriptionFromContext")),
                    times(1)
            );
        }
    }
    //setupTestContext - END

    //initializeTestEnvironment - START
    @Test
    @DisplayName("Should initialize test environment with mocked ReflectionUtil and ConfigCache")
    void shouldInitializeTestEnvironment_WhenReflectionAndConfigAreMocked() throws Exception {
        try (MockedStatic<ReflectionUtil> mockedReflectionUtil = mockStatic(ReflectionUtil.class);
             MockedStatic<ConfigCache> mockedConfigCache = mockStatic(ConfigCache.class)) {

            // Given
            List<Class<? extends PropertyConfig>> dummyConfigs = List.of(BasicPropertyConfig.class);
            mockedReflectionUtil.when(() ->
                    ReflectionUtil.findImplementationsOfInterface(any(), any())
            ).thenReturn(dummyConfigs);

            BasicPropertyConfig dummyConfig = new BasicPropertyConfig();
            mockedConfigCache.when(() -> ConfigCache.getOrCreate(BasicPropertyConfig.class))
                    .thenReturn(dummyConfig);

            FrameworkConfig dummyFrameworkConfig = mock(FrameworkConfig.class);
            lenient().when(dummyFrameworkConfig.projectPackages()).thenReturn(new String[]{"io.cyborgcode.roa"});
            mockedConfigCache.when(() -> ConfigCache.getOrCreate(FrameworkConfig.class))
                    .thenReturn(dummyFrameworkConfig);

            // When / Then
            AllureStepHelper.initializeTestEnvironment();

            // Then
            mockedConfigCache.verify(() -> ConfigCache.getOrCreate(BasicPropertyConfig.class), atLeastOnce());
            mockedConfigCache.verify(() -> ConfigCache.getOrCreate(FrameworkConfig.class));
            Field field = AllureStepHelper.class.getDeclaredField("ENV_INITIALIZED");
            field.setAccessible(true);
            AtomicBoolean flag = (AtomicBoolean) field.get(null);
            assertTrue(flag.get());
        }
    }

    @Test
    @DisplayName("Should use allure results directory from system property when writing environment properties")
    void shouldWriteEnvironmentProperties_UsesSystemPropertyDirectory() throws Exception {
        // Given
        String customResultsDir = "build/allure-results";
        System.setProperty("allure.results.directory", customResultsDir);

        Map<String, List<String>> propertiesMap = Map.of(
                "key1", List.of("value1"),
                "key2", List.of("value2")
        );

        Path resultsDir = Path.of(customResultsDir);
        Path environmentFile = resultsDir.resolve("environment.properties");
        BufferedWriter writer = mock(BufferedWriter.class);

        try (MockedStatic<Files> mockedFiles = mockStatic(Files.class)) {
            mockedFiles.when(() -> Files.createDirectories(resultsDir)).thenReturn(resultsDir);
            mockedFiles.when(() -> Files.newBufferedWriter(environmentFile, StandardCharsets.UTF_8)).thenReturn(writer);

            // When
            Method method = AllureStepHelper.class.getDeclaredMethod("writeEnvironmentProperties", Map.class);
            method.setAccessible(true);
            method.invoke(null, propertiesMap);

            // Then
            mockedFiles.verify(() -> Files.createDirectories(resultsDir));
            mockedFiles.verify(() -> Files.newBufferedWriter(environmentFile, StandardCharsets.UTF_8));
        }
    }
    //initializeTestEnvironment - END

    //collectConfigurationProperties - START
    @Test
    @DisplayName("Should collect valid configuration properties with key and value")
    void test_collectConfigurationProperties_withValidConfig() throws Exception {
        // Given
        try (
                MockedStatic<ReflectionUtil> mockedReflectionUtil = mockStatic(ReflectionUtil.class);
                MockedStatic<ConfigCache> mockedConfigCache = mockStatic(ConfigCache.class);
                MockedStatic<FrameworkConfigHolder> mockedFrameworkConfigHolder = mockStatic(FrameworkConfigHolder.class)
        ) {
            mockedReflectionUtil.when(() ->
                    ReflectionUtil.findImplementationsOfInterface(PropertyConfig.class, "io.cyborgcode.roa")
            ).thenReturn(List.of(SimpleKeyedConfigImpl.class));


            SimpleKeyedConfigImpl simpleKeyedConfigImpl = new SimpleKeyedConfigImpl();
            mockedConfigCache.when(() -> ConfigCache.getOrCreate(SimpleKeyedConfigImpl.class)).thenReturn(simpleKeyedConfigImpl);

            FrameworkConfig mockConfig = mock(FrameworkConfig.class);
            when(mockConfig.projectPackages()).thenReturn(new String[]{"io.cyborgcode.roa"});
            mockedFrameworkConfigHolder.when(FrameworkConfigHolder::getFrameworkConfig).thenReturn(mockConfig);

            // When
            Method method = AllureStepHelper.class.getDeclaredMethod("collectConfigurationProperties");
            method.setAccessible(true);
            Map<String, List<String>> result = (Map<String, List<String>>) method.invoke(null);

            // Then
            assertNotNull(result, "Result should not be null");
            assertTrue(result.containsKey("test.key"), "Expected result to contain 'test.key'");
            assertEquals("value123 (Source: testSource)", result.get("test.key").get(0), "Unexpected value for 'test.key'");
        }
    }

    @Test
    @DisplayName("Should skip properties with empty values")
    void test_collectConfigurationProperties_skipsEmptyValues() throws Exception {
        // Given
        try (
                MockedStatic<ReflectionUtil> mockedReflectionUtil = mockStatic(ReflectionUtil.class);
                MockedStatic<ConfigCache> mockedConfigCache = mockStatic(ConfigCache.class);
                MockedStatic<FrameworkConfigHolder> mockedFrameworkConfigHolder = mockStatic(FrameworkConfigHolder.class)
        ) {
            FrameworkConfig mockConfig = mock(FrameworkConfig.class);
            when(mockConfig.projectPackages()).thenReturn(new String[]{"io.cyborgcode.roa"});
            mockedFrameworkConfigHolder.when(FrameworkConfigHolder::getFrameworkConfig).thenReturn(mockConfig);

            mockedReflectionUtil.when(() ->
                    ReflectionUtil.findImplementationsOfInterface(eq(PropertyConfig.class), anyString())
            ).thenReturn(List.of(EmptyValueConfigImpl.class));

            EmptyValueConfigImpl emptyConfig = new EmptyValueConfigImpl();
            mockedConfigCache.when(() -> ConfigCache.getOrCreate(EmptyValueConfigImpl.class)).thenReturn(emptyConfig);

            // When
            Method method = AllureStepHelper.class.getDeclaredMethod("collectConfigurationProperties");
            method.setAccessible(true);
            Map<String, List<String>> result = (Map<String, List<String>>) method.invoke(null);

            // Then
            assertNotNull(result, "Result should not be null");
            assertTrue(result.isEmpty() || !result.containsKey("empty.key"),
                    "Result should not contain 'empty.key' or should be empty");
        }
    }

    @Test
    @DisplayName("Should return empty result when reflection throws exception")
    void test_collectConfigurationProperties_handlesReflectionException() throws Exception {
        // Given
        try (
                MockedStatic<ReflectionUtil> mockedReflectionUtil = mockStatic(ReflectionUtil.class);
                MockedStatic<ConfigCache> mockedConfigCache = mockStatic(ConfigCache.class);
                MockedStatic<FrameworkConfigHolder> mockedFrameworkConfigHolder = mockStatic(FrameworkConfigHolder.class)
        ) {
            FrameworkConfig mockConfig = mock(FrameworkConfig.class);
            when(mockConfig.projectPackages()).thenReturn(new String[]{"io.cyborgcode.roa"});
            mockedFrameworkConfigHolder.when(FrameworkConfigHolder::getFrameworkConfig).thenReturn(mockConfig);

            mockedReflectionUtil.when(() ->
                    ReflectionUtil.findImplementationsOfInterface(eq(PropertyConfig.class), anyString())
            ).thenReturn(List.of(ExceptionThrowingConfig.class));

            ExceptionThrowingConfig dummy = new ExceptionThrowingConfig();
            mockedConfigCache.when(() -> ConfigCache.getOrCreate(ExceptionThrowingConfig.class)).thenReturn(dummy);

            // When
            Method method = AllureStepHelper.class.getDeclaredMethod("collectConfigurationProperties");
            method.setAccessible(true);
            Map<String, List<String>> result = (Map<String, List<String>>) method.invoke(null);

            // Then
            assertNotNull(result, "Result should not be null");
            assertTrue(result.isEmpty(), "Expected result to be empty due to exception in config method");
        }
    }

    @Test
    @DisplayName("Should skip methods without @Config.Key annotation")
    void test_collectConfigurationProperties_skipsMethodWithoutKeyAnnotation() throws Exception {
        // Given
        try (
                MockedStatic<ReflectionUtil> mockedReflectionUtil = mockStatic(ReflectionUtil.class);
                MockedStatic<ConfigCache> mockedConfigCache = mockStatic(ConfigCache.class);
                MockedStatic<FrameworkConfigHolder> mockedFrameworkConfigHolder = mockStatic(FrameworkConfigHolder.class)
        ) {
            FrameworkConfig mockConfig = mock(FrameworkConfig.class);
            when(mockConfig.projectPackages()).thenReturn(new String[]{"io.cyborgcode.roa"});
            mockedFrameworkConfigHolder.when(FrameworkConfigHolder::getFrameworkConfig).thenReturn(mockConfig);

            mockedReflectionUtil.when(() ->
                    ReflectionUtil.findImplementationsOfInterface(PropertyConfig.class, "io.cyborgcode.roa")
            ).thenReturn(List.of(MissingKeyConfigImpl.class));

            MissingKeyConfigImpl dummy = new MissingKeyConfigImpl();
            mockedConfigCache.when(() -> ConfigCache.getOrCreate(MissingKeyConfigImpl.class)).thenReturn(dummy);

            // When
            Method method = AllureStepHelper.class.getDeclaredMethod("collectConfigurationProperties");
            method.setAccessible(true);
            Map<String, List<String>> result = (Map<String, List<String>>) method.invoke(null);

            // Then
            assertNotNull(result, "Result should not be null");
            assertTrue(result.isEmpty(), "Expected result to be empty because no method has @Config.Key");
        }
    }

    @Test
    @DisplayName("Should skip properties with whitespace-only values")
    void test_collectConfigurationProperties_skipsWhitespaceOnlyValues() throws Exception {
        // Given
        try (
                MockedStatic<ReflectionUtil> mockedReflectionUtil = mockStatic(ReflectionUtil.class);
                MockedStatic<ConfigCache> mockedConfigCache = mockStatic(ConfigCache.class);
                MockedStatic<FrameworkConfigHolder> mockedFrameworkConfigHolder = mockStatic(FrameworkConfigHolder.class)
        ) {
            FrameworkConfig mockConfig = mock(FrameworkConfig.class);
            when(mockConfig.projectPackages()).thenReturn(new String[]{"io.cyborgcode.roa"});
            mockedFrameworkConfigHolder.when(FrameworkConfigHolder::getFrameworkConfig).thenReturn(mockConfig);

            mockedReflectionUtil.when(() ->
                    ReflectionUtil.findImplementationsOfInterface(PropertyConfig.class, "io.cyborgcode.roa")
            ).thenReturn(List.of(WhitespaceValueConfigImpl.class));

            WhitespaceValueConfigImpl dummy = new WhitespaceValueConfigImpl();
            mockedConfigCache.when(() -> ConfigCache.getOrCreate(WhitespaceValueConfigImpl.class)).thenReturn(dummy);

            // When
            Method method = AllureStepHelper.class.getDeclaredMethod("collectConfigurationProperties");
            method.setAccessible(true);
            Map<String, List<String>> result = (Map<String, List<String>>) method.invoke(null);

            // Then
            assertNotNull(result, "Result should not be null");
            assertTrue(result.isEmpty(), "Expected result to be empty because value is only whitespace");
        }
    }
    //collectConfigurationProperties - END

    //writeEnvironmentProperties - START
    @Test
    @DisplayName("Should write key-value pairs to the environment file")
    void testWriteEnvironmentProperties_writeKeyValues() throws Exception {
        // Given
        Map<String, List<String>> propertiesMap = new LinkedHashMap<>();
        propertiesMap.put("key1", List.of("value1", "value2"));
        propertiesMap.put("key2", List.of("value3"));

        Path resultsDir = Path.of("target/allure-results");
        Path environmentFile = resultsDir.resolve("environment.properties");
        BufferedWriter writer = mock(BufferedWriter.class);

        try (MockedStatic<Files> mockedFiles = mockStatic(Files.class)) {
            mockedFiles.when(() -> Files.createDirectories(resultsDir)).thenReturn(resultsDir);
            mockedFiles.when(() -> Files.newBufferedWriter(environmentFile, StandardCharsets.UTF_8)).thenReturn(writer);

            Method method = AllureStepHelper.class.getDeclaredMethod("writeEnvironmentProperties", Map.class);
            method.setAccessible(true);
            method.invoke(null, propertiesMap);

            // Then
            mockedFiles.verify(() -> Files.createDirectories(resultsDir));
            mockedFiles.verify(() -> Files.newBufferedWriter(environmentFile, StandardCharsets.UTF_8));

            InOrder inOrder = inOrder(writer);
            inOrder.verify(writer).write("key1=value1, value2");
            inOrder.verify(writer).write(System.lineSeparator());
            inOrder.verify(writer).write("key2=value3");
            inOrder.verify(writer).write(System.lineSeparator());
        }
    }

    @Test
    @DisplayName("Should handle invocation exceptions when collecting properties")
    void shouldHandleInvocationExceptions() throws Exception {
        try (
                MockedStatic<ReflectionUtil> mockedReflectionUtil = mockStatic(ReflectionUtil.class);
                MockedStatic<ConfigCache> mockedConfigCache = mockStatic(ConfigCache.class);
                MockedStatic<FrameworkConfigHolder> mockedFrameworkConfigHolder = mockStatic(FrameworkConfigHolder.class);
                MockedStatic<LogQuest> mockedLogTest = mockStatic(LogQuest.class)
        ) {
            // 1. Setup framework config
            FrameworkConfig mockConfig = mock(FrameworkConfig.class);
            when(mockConfig.projectPackages()).thenReturn(new String[]{"io.cyborgcode.roa"});
            mockedFrameworkConfigHolder.when(FrameworkConfigHolder::getFrameworkConfig).thenReturn(mockConfig);

            // 2. Create minimal test interface
            interface SingleMethodConfig extends PropertyConfig {
                @Config.Key("test.key")
                String willThrow() throws Exception;
            }

            // 3. Create mock that throws
            SingleMethodConfig mockInstance = mock(SingleMethodConfig.class);
            when(mockInstance.willThrow()).thenThrow(new Exception("Test exception"));

            // 4. Setup reflection to return only our test interface
            mockedReflectionUtil.when(() ->
                            ReflectionUtil.findImplementationsOfInterface(any(), any()))
                    .thenReturn(List.of(SingleMethodConfig.class));

            // 5. Mock ConfigCache
            mockedConfigCache.when(() -> ConfigCache.getOrCreate(SingleMethodConfig.class))
                    .thenReturn(mockInstance);

            // When
            Method method = AllureStepHelper.class.getDeclaredMethod("collectConfigurationProperties");
            method.setAccessible(true);
            Map<String, List<String>> result = (Map<String, List<String>>) method.invoke(null);

            // Then verify results
            assertNotNull(result);
            assertTrue(result.isEmpty());

            mockedLogTest.verify(() ->
                            LogQuest.error(eq("Error processing willThrow"), any(Exception.class)),
                    atLeastOnce()
            );
        }
    }
}
