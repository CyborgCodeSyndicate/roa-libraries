package ${package}.db;

import io.cyborgcode.roa.db.annotations.DB;
import io.cyborgcode.roa.db.validator.DbAssertionTarget;
import io.cyborgcode.roa.db.query.QueryResponse;
import io.cyborgcode.roa.db.storage.StorageKeysDb;
import io.cyborgcode.roa.framework.annotation.Regression;
import io.cyborgcode.roa.framework.base.BaseQuest;
import io.cyborgcode.roa.framework.quest.Quest;
import io.cyborgcode.roa.validator.core.Assertion;
import org.junit.jupiter.api.Test;

import ${package}.db.queries.ExampleDbQueries;

import static ${package}.common.base.Rings.RING_OF_DB;
import static io.cyborgcode.roa.db.validator.DbAssertionTarget.QUERY_RESULT;
import static io.cyborgcode.roa.validator.core.AssertionTypes.IS;

/**
 * Getting started DB test (basic template).
 *
 * <p>Minimal example showing a query and a single assertion.
 * Replace the query and expected values with your application's needs.</p>
 */
@DB
public class GettingStartedDbTestBasic extends BaseQuest {

    @Test
    @Regression
    @Description("Basic DB flow")
    void basicDbQuery(Quest quest) {
        quest.use(RING_OF_DB)
                .query(ExampleDbQueries.SIMPLE_QUERY)
                .validate(retrieve(StorageKeysDb.DB, ExampleDbQueries.SIMPLE_QUERY, QueryResponse.class),
                        Assertion.builder()
                                .target(QUERY_RESULT)
                                .expected(true)
                                .type(IS)
                                .build()
                )
                .complete();
    }
}
