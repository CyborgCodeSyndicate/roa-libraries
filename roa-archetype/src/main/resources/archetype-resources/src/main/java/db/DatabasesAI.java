package ${package}.db;

import io.cyborgcode.roa.db.config.DbType;
import java.sql.Driver;

public enum DatabasesAI implements DbType<DatabasesAI> {

    // TODO: Add your database connections here
    EXAMPLE_DB(null, "jdbc:example");

    private final Driver driver;
    private final String protocol;

    DatabasesAI(Driver driver, String protocol) {
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
    public DatabasesAI enumImpl() {
        return this;
    }
}
