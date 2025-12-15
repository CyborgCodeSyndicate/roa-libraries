package ${package}.db;

import io.cyborgcode.roa.db.annotations.DB;
import io.cyborgcode.roa.db.validator.DbAssertionTarget;
import io.cyborgcode.roa.db.query.QueryResponse;
import io.cyborgcode.roa.db.storage.StorageKeysDb;
import io.cyborgcode.roa.framework.annotation.Regression;
import io.cyborgcode.roa.framework.base.BaseQuest;
import io.cyborgcode.roa.framework.quest.Quest;
import io.cyborgcode.roa.validator.core.Assertion;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Test;

import ${package}.db.queries.ExampleDbQueries;

import static ${package}.common.base.Rings.RING_OF_CUSTOM;
import static ${package}.common.base.Rings.RING_OF_DB;
import static io.cyborgcode.roa.db.validator.DbAssertionTarget.QUERY_RESULT;
import static io.cyborgcode.roa.validator.core.AssertionTypes.IS;

/**
 * Simple database query example.
 * 
 * Configure your database in Databases.java and config.properties first.
 */
@DB
public class GettingStartedDbTestBasic extends BaseQuest {

    @Test
    @Regression
    @Description("Basic DB flow")
    void basicDbQuery(Quest quest) {

        // Execute a query and optionally validate results
        // Replace ExampleDbQueries.SIMPLE_QUERY with your own
        
        quest.use(RING_OF_DB)
                .query(ExampleDbQueries.SIMPLE_QUERY)
                // .validate(...) add validation when your DB is set up
                .complete();
    }

    @Test
    @Regression
    void customFlowDemonstration(Quest quest) {

        /**
         * Example:
         *
         * <p>TODO: implement your custom flow here</p>
         */
//        quest.use(RING_OF_CUSTOM)
//                // .performExampleFlow(order) add custom flow here
//                .complete();
    }
}
