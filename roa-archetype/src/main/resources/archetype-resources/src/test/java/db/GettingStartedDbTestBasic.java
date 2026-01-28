package ${package}.db;

import io.cyborgcode.roa.db.annotations.DB;
import io.cyborgcode.roa.framework.annotation.Regression;
import io.cyborgcode.roa.framework.base.BaseQuest;
import io.cyborgcode.roa.framework.quest.Quest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import ${package}.db.queries.ExampleDbQueries;
import io.cyborgcode.roa.validator.core.Assertion;
import io.cyborgcode.roa.db.query.QueryResponse;
import io.cyborgcode.roa.db.storage.StorageKeysDb;

import static ${package}.common.base.Rings.RING_OF_DB;
import static ${package}.common.base.Rings.RING_OF_CUSTOM;
import static io.cyborgcode.roa.db.validator.DbAssertionTarget.QUERY_RESULT;
import static io.cyborgcode.roa.validator.core.AssertionTypes.CONTAINS_ALL;

// TODO: EXAMPLE TEST — delete this class after reviewing.
// This test is only an example of using ROA API capabilities
@DB
@DisplayName("EXAMPLE - Getting started DB test (delete me))
public class GettingStartedDbTestBasic extends BaseQuest {

    @Test
    @Regression
    void basicDbQuery(Quest quest) {
        
        quest.use(RING_OF_DB)
                .query(ExampleDbQueries.SIMPLE_QUERY)
                // Validation example:
                // .validate(
                //     retrieve(StorageKeysDb.DB, ExampleDbQueries.SIMPLE_QUERY, QueryResponse.class),
                //     Assertion.builder()
                //         .target(QUERY_RESULT)
                //         .key("$[?(@.id == 1)].columnName")
                //         .type(CONTAINS_ALL)
                //         .expected(List.of("expected value"))
                //         .build()
                // )
                .complete();
    }

    @Test
    @Regression
    void customFlowDemonstration(Quest quest) {
        // Example:
        // quest.use(RING_OF_CUSTOM)
        //    .exampleFlow()
        //    .drop()
        //    .use(RING_OF_DB)
        //    .query(ExampleDbQueries.SIMPLE_QUERY.withParam("id", 1))
        //    .complete();
    }
}
