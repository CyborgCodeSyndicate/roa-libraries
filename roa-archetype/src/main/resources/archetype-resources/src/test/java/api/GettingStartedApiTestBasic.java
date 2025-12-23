package ${package}.api;

import io.cyborgcode.roa.api.annotations.API;
import io.cyborgcode.roa.api.annotations.AuthenticateViaApi;
import io.cyborgcode.roa.framework.annotation.Regression;
import io.cyborgcode.roa.framework.base.BaseQuest;
import io.cyborgcode.roa.framework.quest.Quest;
import io.cyborgcode.roa.validator.core.Assertion;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Test;

import ${package}.api.authentication.ExampleCredentials;
import ${package}.api.authentication.ExampleAuthenticationClient;
import static ${package}.common.base.Rings.RING_OF_CUSTOM;

import static ${package}.common.base.Rings.RING_OF_API;
import static io.cyborgcode.roa.api.validator.RestAssertionTarget.STATUS;
import static io.cyborgcode.roa.validator.core.AssertionTypes.IS;
import static org.apache.http.HttpStatus.SC_OK;

/**
 * Demonstrates a basic API test flow, chaining a request with validation.
 */
@API
public class GettingStartedApiTestBasic extends BaseQuest {

    @Test
    @Regression
    @AuthenticateViaApi(credentials = ExampleCredentials.class, type = ExampleAuthenticationClient.class)
    @Description("Basic API test flow")
    void exampleAPITest(Quest quest) {

        quest.use(RING_OF_API)
                .requestAndValidate(
                        ExampleEndpoints.EXAMPLE_GET,
                        Assertion.builder().target(STATUS).type(IS).expected(SC_OK).build()
                )
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
