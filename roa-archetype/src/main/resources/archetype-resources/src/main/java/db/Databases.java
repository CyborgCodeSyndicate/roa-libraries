package ${package}.db;

import io.cyborgcode.roa.db.config.DbType;

import java.sql.Driver;
import java.sql.SQLException;

public enum Databases implements DbType<Databases> {

#if ( $dbType.equalsIgnoreCase("H2") || $dbType == ""  || $dbType == "/" )
    // Extend this enum with additional database types if needed.
    H2(new org.h2.Driver(), "jdbc:h2");
#end
#if ( $dbType.equalsIgnoreCase("POSTGRES") )
    // Extend this enum with additional database types if needed.
    POSTGRES(new org.postgresql.Driver(), "jdbc:postgresql");
#end
#if ( $dbType.equalsIgnoreCase("MYSQL") )
    // Extend this enum with additional database types if needed.
    MYSQL(new com.mysql.cj.jdbc.Driver(), "jdbc:mysql");
#end
#if ( $dbType.equalsIgnoreCase("ORACLE") )
    // Extend this enum with additional database types if needed.
    ORACLE(new oracle.jdbc.OracleDriver(), "jdbc:oracle:thin");
#end
#if ( $dbType.equalsIgnoreCase("SQLSERVER") )
    // Extend this enum with additional database types if needed.
    SQLSERVER(new com.microsoft.sqlserver.jdbc.SQLServerDriver(), "jdbc:sqlserver");
#end
#if ( $dbType.equalsIgnoreCase("MARIADB") )
    // Extend this enum with additional database types if needed.
    MARIADB(new org.mariadb.jdbc.Driver(), "jdbc:mariadb");
#end

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
