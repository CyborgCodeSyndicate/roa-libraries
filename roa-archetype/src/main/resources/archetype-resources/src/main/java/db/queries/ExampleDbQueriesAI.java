package ${package}.db.queries;

import io.cyborgcode.roa.db.query.DbQuery;

public enum ExampleDbQueriesAI implements DbQuery<ExampleDbQueriesAI> {

    // TODO: Add your queries here
    EXAMPLE_QUERY("SELECT 1");

    private final String query;

    ExampleDbQueriesAI(String query) {
        this.query = query;
    }

    @Override
    public String query() {
        return query;
    }

    @Override
    public ExampleDbQueriesAI enumImpl() {
        return this;
    }
}
