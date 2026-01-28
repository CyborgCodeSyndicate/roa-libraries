package ${package}.db.queries;

import io.cyborgcode.roa.db.query.DbQuery;

// TODO: Replace SIMPLE_QUERY with your real SQL queries and add more entries as needed.
public enum ExampleDbQueries implements DbQuery<ExampleDbQueries> {

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
