package ${package}.api;

import io.cyborgcode.roa.api.annotations.API;
import io.cyborgcode.roa.api.annotations.AuthenticateViaApi;
import io.cyborgcode.roa.framework.annotation.Craft;
import io.cyborgcode.roa.framework.annotation.Journey;
import io.cyborgcode.roa.framework.annotation.Regression;
import io.cyborgcode.roa.framework.annotation.Ripper;
import io.cyborgcode.roa.framework.base.BaseQuest;
import io.cyborgcode.roa.framework.parameters.Late;
import io.cyborgcode.roa.framework.quest.Quest;
import io.cyborgcode.roa.validator.core.Assertion;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import ${package}.common.data.cleaner.DataCleaner;
import ${package}.api.authentication.ExampleCredentials;
import ${package}.api.authentication.ExampleAuthenticationClient;
import ${package}.api.dto.request.ExampleRequestDto;
import ${package}.common.data.creator.DataCreator;
import ${package}.common.preconditions.Preconditions;

import static ${package}.common.base.Rings.RING_OF_API;
import static ${package}.common.base.Rings.RING_OF_CUSTOM;
import static io.cyborgcode.roa.api.validator.RestAssertionTarget.STATUS;
import static io.cyborgcode.roa.validator.core.AssertionTypes.IS;
import static org.apache.http.HttpStatus.SC_CREATED;

// TODO: EXAMPLE TEST — delete this class after reviewing.
// This test is only an example of using ROA API capabilities
@API
@DisplayName("EXAMPLE - Getting started API test (delete me)")
public class GettingStartedApiTestAdvanced extends BaseQuest {

    @Test
    @Regression
    @AuthenticateViaApi(credentials = ExampleCredentials.class, type = ExampleAuthenticationClient.class)
    @Journey(value = Preconditions.Data.EXAMPLE_PRECONDITION, order = 1)
    @Ripper(targets = {DataCleaner.Data.EXAMPLE_CLEANUP})
    void exampleAPITest(Quest quest,
                        @Craft(model = DataCreator.Data.EXAMPLE_MODEL) ExampleRequestDto firstPayload,
                        @Craft(model = DataCreator.Data.EXAMPLE_MODEL) Late<ExampleRequestDto> secondPayload) {

        quest.use(RING_OF_API)
                .requestAndValidate(
                        ExampleEndpoints.EXAMPLE_POST,
                        firstPayload,
                        Assertion.builder().target(STATUS).type(IS).expected(SC_CREATED).build()
                )
                .requestAndValidate(
                        ExampleEndpoints.EXAMPLE_POST,
                        secondPayload.create(),
                        Assertion.builder().target(STATUS).type(IS).expected(SC_CREATED).build()
                )
                .complete();
    }

    @Test
    @Regression
    void customFlowDemonstration(Quest quest) {
        // Example:
        // quest.use(RING_OF_CUSTOM)
        //    .exampleFlow()
        //    .drop()
        //    .use(RING_OF_API)
        //    .requestAndValidate(
        //       ExampleEndpoints.EXAMPLE_POST,
        //       Assertion.builder().target(STATUS).type(IS).expected(SC_CREATED).build()
        //    )
        //    .complete();
    }
}
