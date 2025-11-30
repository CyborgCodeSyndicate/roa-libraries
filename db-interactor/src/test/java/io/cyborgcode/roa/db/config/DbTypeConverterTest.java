package io.cyborgcode.roa.db.config;

import io.cyborgcode.utilities.reflections.ReflectionUtil;
import java.sql.Driver;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("DbTypeConverter Tests")
class DbTypeConverterTest {

    @Mock
    private DbConfig mockDbConfig;

    private final DbTypeConverter converter = new DbTypeConverter();


    private enum DummyDbType implements DbType {
        ALPHA;

        @Override
        public Driver driver() {
            return null;
        }

        @Override
        public String protocol() {
            return name().toLowerCase();
        }

        @Override
        public Enum<?> enumImpl() {
            return this;
        }
    }

    @Test
    @DisplayName("convert() returns the matching enum constant")
    void convertReturnsEnum() {
        try (MockedStatic<DbConfigHolder> dbConfigHolder = mockStatic(DbConfigHolder.class);
             MockedStatic<ReflectionUtil> refl = mockStatic(ReflectionUtil.class)) {

            dbConfigHolder.when(DbConfigHolder::getDbConfig).thenReturn(mockDbConfig);
            when(mockDbConfig.projectPackages()).thenReturn(new String[]{"any.pkg"});

            refl.when(() ->
                    ReflectionUtil.findEnumImplementationsOfInterface(
                            any(), any(String.class), any(String[].class))
            ).thenReturn(DummyDbType.ALPHA);

            DbType result = converter.convert(null, "ALPHA");

            assertSame(DummyDbType.ALPHA, result);
        }
    }

    @Test
    @DisplayName("convert() throws exception when no enums found")
    void convertThrowsWhenNoEnums() {
        try (MockedStatic<DbConfigHolder> dbConfigHolder = mockStatic(DbConfigHolder.class);
             MockedStatic<ReflectionUtil> refl = mockStatic(ReflectionUtil.class)) {

            dbConfigHolder.when(DbConfigHolder::getDbConfig).thenReturn(mockDbConfig);
            when(mockDbConfig.projectPackages()).thenReturn(new String[]{"none.pkg"});

            refl.when(() ->
                    ReflectionUtil.findEnumImplementationsOfInterface(
                            any(), any(String.class), any(String[].class))
            ).thenThrow(new IllegalStateException("No enum implementing DbType found"));

            assertThrows(IllegalStateException.class,
                    () -> converter.convert(null, "ANY"));
        }
    }

    @Test
    @DisplayName("convert() falls back to first package when multiple enums found")
    void convertFallsBackToFirstPackage() {
        try (var dbConfigHolder = mockStatic(DbConfigHolder.class);
             var refl = mockStatic(ReflectionUtil.class)) {

            dbConfigHolder.when(DbConfigHolder::getDbConfig).thenReturn(mockDbConfig);
            when(mockDbConfig.projectPackages()).thenReturn(new String[]{"first.pkg", "second.pkg"});

            // First call with all packages throws "more than one enum" exception
            refl.when(() ->
                    ReflectionUtil.findEnumImplementationsOfInterface(
                            any(), any(String.class), any(String[].class))
            ).thenThrow(new IllegalStateException("Found more than one enum implementing DbType"));

            // Second call with only first package succeeds
            refl.when(() ->
                    ReflectionUtil.findEnumImplementationsOfInterface(
                            any(), any(String.class), any(String.class))
            ).thenReturn(DummyDbType.ALPHA);

            DbType result = converter.convert(null, "ALPHA");

            assertSame(DummyDbType.ALPHA, result);
        }
    }

    @Test
    @DisplayName("convert() throws exception on bad enum name")
    void convertThrowsOnInvalidName() {
        try (MockedStatic<DbConfigHolder> dbConfigHolder = mockStatic(DbConfigHolder.class);
             MockedStatic<ReflectionUtil> refl = mockStatic(ReflectionUtil.class)) {

            dbConfigHolder.when(DbConfigHolder::getDbConfig).thenReturn(mockDbConfig);
            when(mockDbConfig.projectPackages()).thenReturn(new String[]{"any.pkg"});

            refl.when(() ->
                    ReflectionUtil.findEnumImplementationsOfInterface(
                            any(), any(String.class), any(String[].class))
            ).thenThrow(new IllegalArgumentException("No constant GAMMA"));

            assertThrows(IllegalArgumentException.class,
                    () -> converter.convert(null, "GAMMA"));
        }
    }

    @Test
    @DisplayName("convert() throws when multiple enums found even after fallback")
    void convertThrowsWhenFallbackStillFindsMultiple() {
        try (var dbConfigHolder = mockStatic(DbConfigHolder.class);
             var refl = mockStatic(ReflectionUtil.class)) {

            dbConfigHolder.when(DbConfigHolder::getDbConfig).thenReturn(mockDbConfig);
            when(mockDbConfig.projectPackages()).thenReturn(new String[]{"first.pkg"});

            // Both calls throw "more than one enum" - even the fallback
            refl.when(() ->
                    ReflectionUtil.findEnumImplementationsOfInterface(
                            any(), any(String.class), any())
            ).thenThrow(new IllegalStateException("Found more than one enum implementing DbType"));

            IllegalStateException ex = assertThrows(
                    IllegalStateException.class,
                    () -> converter.convert(null, "ALPHA")
            );
            assertTrue(ex.getMessage().contains("more than one enum"));
        }
    }
}
