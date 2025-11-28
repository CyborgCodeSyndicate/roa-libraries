package ${package}.db;

import io.cyborgcode.roa.db.annotations.DB;
import io.cyborgcode.roa.db.annotations.DbHook;
import io.cyborgcode.roa.db.annotations.DbHooks;
import io.cyborgcode.roa.framework.annotation.Regression;
import io.cyborgcode.roa.framework.base.BaseQuest;
import io.cyborgcode.roa.framework.quest.Quest;
import io.cyborgcode.roa.validator.core.Assertion;
import org.junit.jupiter.api.Test;

import io.qameta.allure.Description;

import ${package}.api_module.api.dto.request.ExampleRequestDto;
import ${package}.db_module.db.extractors.DbResponsesJsonPaths;
import ${package}.db_module.db.hooks.DbHookFlows;
import ${package}.db_module.db.queries.ExampleDbQueries;

import java.util.List;

import static ${package}.common.base.Rings.RING_OF_DB;
import static io.cyborgcode.roa.db.validator.DbAssertionTarget.QUERY_RESULT;
import static io.cyborgcode.roa.framework.hooks.HookExecution.BEFORE;
import static io.cyborgcode.roa.validator.core.AssertionTypes.CONTAINS_ALL;
import static io.cyborgcode.roa.validator.core.AssertionTypes.IS;

/**
 * Getting started DB test (advanced template).
 *
 * <p>Demonstrates hooks and chained validations with a single example model.
 * Replace the query, hooks, and validations with your real DB logic.</p>
 */
@DB
@DbHooks({
      @DbHook(when = BEFORE, type = DbHookFlows.Data.EXAMPLE_INITIALIZE)
})
public class GettingStartedDbTest extends BaseQuest {

   @Test
   @Regression
   @Description("Database usage with a hook and simple validation")
   void createOrderDatabaseValidation(Quest quest) {

      ExampleRequestDto example = ExampleRequestDto.builder()
            .name("Example Name")
            .job("Example Role")
            .build();

      quest
            .use(RING_OF_DB)
            .validate(() -> System.out.println("Replace with your custom flow, example: " + example))
            .drop()
            .use(RING_OF_DB)
            .query(ExampleDbQueries.SIMPLE_QUERY)
            .validate(
                  Assertion.builder()
                        .target(QUERY_RESULT)
                        .key(DbResponsesJsonPaths.PRODUCT_BY_ID.getJsonPath(1))
                        .type(CONTAINS_ALL)
                        .expected(List.of(example.getName()))
                        .soft(true)
                        .build(),
                  Assertion.builder()
                        .target(QUERY_RESULT)
                        .key(DbResponsesJsonPaths.LOCATION_BY_ID.getJsonPath(1))
                        .type(IS)
                        .expected("REPLACE_WITH_LOCATION")
                        .soft(true)
                        .build()
            )
            .complete();
   }
}
