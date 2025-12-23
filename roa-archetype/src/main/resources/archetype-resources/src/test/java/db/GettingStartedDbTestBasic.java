package ${package}.db;

import io.cyborgcode.roa.db.annotations.DB;
import io.cyborgcode.roa.db.validator.DbAssertionTarget;
import io.cyborgcode.roa.db.query.QueryResponse;
import io.cyborgcode.roa.db.storage.StorageKeysDb;
import io.cyborgcode.roa.framework.annotation.Regression;
import io.cyborgcode.roa.framework.base.BaseQuest;
import io.cyborgcode.roa.framework.quest.Quest;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Test;

import static ${package}.common.base.Rings.RING_OF_CUSTOM;
import static ${package}.common.base.Rings.RING_OF_DB;

/**
 * DB test demonstrating query execution and validation.
 */
@DB
@DisplayName("Getting started DB test class")
public class GettingStartedDbTestBasic extends BaseQuest {

    @Test
    @Regression
    @Description("Basic DB test flow")
    void basicDbQuery(Quest quest) {
        
        quest.use(RING_OF_DB)
                .query(ExampleDbQueries.SIMPLE_QUERY)
                // .validate(...)
                .complete();
    }

    @Test
    @Regression
    void customFlowDemonstration(Quest quest) {

//        quest.use(RING_OF_CUSTOM)
//                // .performExampleFlow(order) add custom flow here
//                .complete();
    }
}
