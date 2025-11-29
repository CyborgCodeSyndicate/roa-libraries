package ${package}.api;

import io.cyborgcode.roa.api.annotations.API;
import io.cyborgcode.roa.api.annotations.AuthenticateViaApi;
import io.cyborgcode.roa.framework.annotation.Craft;
import io.cyborgcode.roa.framework.annotation.Journey;
import io.cyborgcode.roa.framework.annotation.JourneyData;
import io.cyborgcode.roa.framework.annotation.Regression;
import io.cyborgcode.roa.framework.base.BaseQuestSequential;
import io.cyborgcode.roa.framework.parameters.Late;
import io.cyborgcode.roa.framework.quest.Quest;
import io.cyborgcode.roa.validator.core.Assertion;
import org.junit.jupiter.api.Test;
import io.qameta.allure.Description;

import ${package}.api.ExampleEndpoints;
import ${package}.api.authentication.ExampleCredentials;
import ${package}.api.authentication.ExampleAuthenticationClient;
import ${package}.api.dto.request.ExampleRequestDto;
import ${package}.common.data.creator.DataCreator;
import ${package}.common.preconditions.Preconditions;

import static ${package}.common.base.Rings.RING_OF_API;
import static io.cyborgcode.roa.api.validator.RestAssertionTarget.STATUS;
import static io.cyborgcode.roa.validator.core.AssertionTypes.IS;
import static org.apache.http.HttpStatus.SC_CREATED;

/**
 * Getting started API test (advanced template).
 *
 * <p>Shows a slightly richer flow with crafted data and two requests.
 * Replace the DTO, endpoints, auth, and assertions with your real API interactions.</p>
 */
@API
@DisplayName("Getting started API test class")
public class GettingStartedApiTestAdvanced extends BaseQuestSequential {

    @Test
    @Regression
    @Description("Creates two example payloads using crafted models")
    @AuthenticateViaApi(credentials = ExampleCredentials.class, type = ExampleAuthenticationClient.class)
    @Journey(value = Preconditions.Data.EXAMPLE_PRECONDITION, order = 1)
    void createsTwoUsersUsingCraftAndLate(Quest quest,
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
}
