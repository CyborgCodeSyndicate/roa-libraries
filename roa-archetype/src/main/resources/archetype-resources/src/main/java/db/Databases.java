package ${package}.db;

import io.cyborgcode.roa.db.config.DbType;

import java.sql.Driver;

public enum Databases implements DbType<Databases> {

#if($dbType.equalsIgnoreCase("H2")||$dbType ==""||$dbType =="/")

    H2(new org.h2.Driver(), "jdbc:h2")
            #end

#if($dbType.equalsIgnoreCase("POSTGRES"))

    POSTGRES(new org.postgresql.Driver(), "jdbc:postgresql")
            #end

#if($dbType.equalsIgnoreCase("MYSQL"))

    MYSQL(new com.mysql.cj.jdbc.Driver(), "jdbc:mysql")
            #end

#if($dbType.equalsIgnoreCase("ORACLE"))

    ORACLE(new oracle.jdbc.OracleDriver(), "jdbc:oracle:thin")
            #end

#if($dbType.equalsIgnoreCase("SQLSERVER"))

    SQLSERVER(new com.microsoft.sqlserver.jdbc.SQLServerDriver(), "jdbc:sqlserver")
            #end

#if($dbType.equalsIgnoreCase("MARIADB"))

    MARIADB(new org.mariadb.jdbc.Driver(), "jdbc:mariadb")
            #end;

    private final Driver driver;
    private final String protocol;

    Databases(Driver driver, String protocol) {
        this.driver = driver;
        this.protocol = protocol;
    }

    @Override
    public Driver driver() {
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
