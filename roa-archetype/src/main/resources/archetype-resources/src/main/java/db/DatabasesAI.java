package ${package}.db;

import io.cyborgcode.roa.db.config.DbType;
import java.sql.Driver;
import java.sql.SQLException;

public enum DatabasesAI implements DbType<DatabasesAI> {

    ;

    private final Driver driver;
    private final String protocol;

    DatabasesAI(Driver driver, String protocol) {
        this.driver = driver;
        this.protocol = protocol;
    }

    @Override
    public Driver driver() {
        if (this.driver == null) {
            throw new IllegalStateException("Driver not initialized for " + this.name());
        }
        return driver;
    }

    @Override
    public String protocol() {
        return protocol;
    }

    @Override
    public DatabasesAI enumImpl() {
        return this;
    }

    @FunctionalInterface
    private interface DriverSupplier {
        Driver get() throws SQLException;
    }

    private static Driver createDriver(String dbType, DriverSupplier supplier) {
        try {
            return supplier.get();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to initialize " + dbType + " driver", e);
        }
    }
}
