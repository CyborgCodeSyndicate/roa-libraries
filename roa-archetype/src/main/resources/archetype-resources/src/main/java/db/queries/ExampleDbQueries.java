package ${package}.db.queries;

import io.cyborgcode.roa.db.query.DbQuery;

/**
 * SQL query definitions.
 * <p>
 * Use {paramName} for parameters, then .withParam("paramName", value) in tests.
 * </p>
 */
public enum ExampleDbQueries implements DbQuery<ExampleDbQueries> {

    /**
     * Simple example query to demonstrate usage.
     */
    SIMPLE_QUERY("SELECT * FROM example WHERE id = {id}");

    private final String query;

    ExampleDbQueries(String query) {
        this.query = query;
    }

    @Override
    public String query() {
        return query;
    }

    @Override
    public ExampleDbQueries enumImpl() {
        return this;
    }
}
