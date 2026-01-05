package ${package}.db.queries;

import io.cyborgcode.roa.db.query.DbQuery;

public enum DbQueriesAI implements DbQuery<DbQueriesAI> {

    ;

    private final String query;

    DbQueriesAI(String query) {
        this.query = query;
    }

    @Override
    public String query() {
        return query;
    }

    @Override
    public DbQueriesAI enumImpl() {
        return this;
    }
}
