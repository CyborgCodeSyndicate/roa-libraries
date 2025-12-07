package ${package}.db;

import io.cyborgcode.roa.db.config.DbType;

import java.sql.Driver;
import java.sql.SQLException;

/**
 *
 * TODO: implement your database enum here by adding your database types to the enum
 *
 */
public enum Databases implements DbType<Databases> {

#if ( $dbType.equalsIgnoreCase("H2") || $dbType == ""  || $dbType == "/" )
    H2(new org.h2.Driver(), "jdbc:h2") // H2 driver constructor does not throw SQLException, so no wrapper needed
#end

#if ( $dbType.equalsIgnoreCase("POSTGRES") )
    POSTGRES(createDriver("PostgreSQL", () -> new org.postgresql.Driver()), "jdbc:postgresql")
#end

#if ( $dbType.equalsIgnoreCase("MYSQL") )
    MYSQL(createDriver("MySQL", com.mysql.cj.jdbc.Driver::new), "jdbc:mysql")
#end

#if ( $dbType.equalsIgnoreCase("ORACLE") )
    ORACLE(createDriver("Oracle", () -> new oracle.jdbc.OracleDriver()), "jdbc:oracle:thin")
#end

#if ( $dbType.equalsIgnoreCase("SQLSERVER") )
    SQLSERVER(createDriver("SQL Server", () -> new com.microsoft.sqlserver.jdbc.SQLServerDriver()), "jdbc:sqlserver")
#end

#if ( $dbType.equalsIgnoreCase("MARIADB") )
    MARIADB(createDriver("MariaDB", () -> new org.mariadb.jdbc.Driver()), "jdbc:mariadb")
#end
    ;

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

    private final Driver driver;
    private final String protocol;

    Databases(Driver driver, String protocol) {
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
    public Databases enumImpl() {
        return this;
    }
}
