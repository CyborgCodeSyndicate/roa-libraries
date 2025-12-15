package ${package}.db;

import io.cyborgcode.roa.db.annotations.DB;
import io.cyborgcode.roa.db.annotations.DbHook;
import io.cyborgcode.roa.db.annotations.DbHooks;
import io.cyborgcode.roa.db.query.QueryResponse;
import io.cyborgcode.roa.db.storage.StorageKeysDb;
import io.cyborgcode.roa.framework.annotation.Regression;
import io.cyborgcode.roa.framework.base.BaseQuest;
import io.cyborgcode.roa.framework.quest.Quest;
import io.cyborgcode.roa.validator.core.Assertion;
import org.junit.jupiter.api.Test;

import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;

import ${package}.db.queries.ExampleDbQueries;

import java.util.List;

import static ${package}.common.base.Rings.RING_OF_DB;
import static ${package}.common.base.Rings.RING_OF_CUSTOM;
import static io.cyborgcode.roa.db.validator.DbAssertionTarget.QUERY_RESULT;
import static io.cyborgcode.roa.framework.hooks.HookExecution.BEFORE;
import static io.cyborgcode.roa.validator.core.AssertionTypes.CONTAINS_ALL;
import static io.cyborgcode.roa.validator.core.AssertionTypes.IS;

/**
 * Database test showing query execution and validation.
 * 
 * Configure your database in Databases.java and config.properties first.
 */
@DB
@DisplayName("Getting started DB test class")
public class GettingStartedDbTestAdvanced extends BaseQuest {

    @Test
    @Regression
    @Description("Database usage with a hook and simple validation")
    void exampleDBTest(Quest quest) {

        // Execute a query with parameters, then validate the results
        // Requires configured DB connection (see Databases.java and config.properties)
        
        quest
                .use(RING_OF_DB)
                .validate(() -> System.out.println("Setup or logging here"))
                .query(ExampleDbQueries.SIMPLE_QUERY.withParam("id", 1))
                // Validation example - uncomment when your DB is ready:
                // .validate(retrieve(StorageKeysDb.DB, ExampleDbQueries.SIMPLE_QUERY, QueryResponse.class),
                //         Assertion.builder()
                //                 .target(QUERY_RESULT)
                //                 .key("$[?(@.id == 1)].columnName")
                //                 .type(CONTAINS_ALL)
                //                 .expected(List.of("expected value"))
                //                 .build()
                // )
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
