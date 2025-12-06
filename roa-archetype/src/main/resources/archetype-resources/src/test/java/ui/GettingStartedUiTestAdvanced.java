package ${package}.ui;

import io.cyborgcode.roa.ui.annotations.AuthenticateViaUi;
import io.cyborgcode.roa.framework.annotation.Craft;
import io.cyborgcode.roa.framework.annotation.Journey;
import io.cyborgcode.roa.framework.annotation.JourneyData;
import io.cyborgcode.roa.framework.annotation.Regression;
import io.cyborgcode.roa.framework.base.BaseQuest;
import io.cyborgcode.roa.framework.quest.Quest;
import io.cyborgcode.roa.ui.annotations.UI;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.qameta.allure.Description;

import ${package}.common.data.extractor.DataExtractorFunctions;
import ${package}.ui.elements.ButtonFields;
import ${package}.ui.elements.InputFields;
import ${package}.ui.elements.SelectFields;
import ${package}.ui.authentication.ExampleCredentials;
import ${package}.ui.authentication.ExampleAppUiLogin;
import ${package}.ui.interceptor.RequestsInterceptor;
import ${package}.ui.model.ExampleTableModel;
import ${package}.common.data.creator.DataCreator;
import ${package}.common.preconditions.Preconditions;
import static ${package}.common.base.Rings.RING_OF_CUSTOM;

import java.util.List;

import static io.cyborgcode.roa.ui.config.UiConfigHolder.getUiConfig;
import static ${package}.common.base.Rings .*;

/**
 * Getting started UI test (advanced template).
 *
 * <p>Includes examples for raw values, crafted data, auth, preconditions and response extractor.
 * Replace element locators, data models, and flows with your application specifics.</p>
 */
@UI
@DisplayName("Getting started UI test class")
public class GettingStartedUiTestAdvanced extends BaseQuest {

    @Test
    @Regression
    @Description("Basic UI flow")
    void createExampleUsingRawValues(Quest quest) {
        quest.use(RING_OF_UI)
                .browser().navigate(getUiConfig().baseUrl())
                .input().insert(InputFields.GENERIC_INPUT, "example")
                .button().click(ButtonFields.GENERIC_BUTTON)
                .select().selectOption(SelectFields.GENERIC_SELECT, "Option 1")
                .complete();
    }

    @Test
    @Regression
    @Description("Using crafted data models from DataCreator")
    void createExampleUsingCraft(Quest quest,
                                 @Craft(model = DataCreator.Data.EXAMPLE_TABLE_MODEL) ExampleTableModel model) {

        quest.use(RING_OF_UI)
                .browser().navigate(getUiConfig().baseUrl())
                .input().insert(InputFields.GENERIC_INPUT, model.getExampleText())
                .button().click(ButtonFields.GENERIC_BUTTON)
                .select().selectOption(SelectFields.GENERIC_SELECT, model.getExampleSelection())
                .complete();
    }

    @Test
    @Regression
    @Description("Using automatic UI authentication through @AuthenticateViaUi")
    @AuthenticateViaUi(credentials = ExampleCredentials.class, type = ExampleAppUiLogin.class)
    void createExampleUsingAuthenticationAndExtractResponse(Quest quest,
                                                            @Craft(model = DataCreator.Data.EXAMPLE_TABLE_MODEL) ExampleTableModel model) {

        quest.use(RING_OF_UI)
                .input().insert(InputFields.GENERIC_INPUT, model.getExampleText())
                .select().selectOption(SelectFields.GENERIC_SELECT, model.getExampleSelection())
                .validate(() -> Assertions.assertEquals(List.of("$197.54"),
                        retrieve(DataExtractorFunctions
                                        .responseBodyExtraction(RequestsInterceptor.EXAMPLE_INTERCEPT.getEndpointSubString(),
                                                "$[0].changes[?(@.key=='totalPrice')].value", "for(;;);"),
                                List.class)))
                .complete();
    }

    @Test
    @Regression
    @Description("Combining journeys/preconditions with UI testing.")
    @Journey(
            value = Preconditions.Data.EXAMPLE_PRECONDITION,
            journeyData = {@JourneyData(DataCreator.Data.EXAMPLE_TABLE_MODEL)},
            order = 1
    )
    void createExampleUsingPreconditions(Quest quest) {
        quest.use(RING_OF_UI)
                .validate(() -> System.out.println("User precondition executed"))
                .complete();
    }

    @Test
    @Regression
    void customFlowDemonstration(Quest quest) {

        quest.use(RING_OF_CUSTOM)
                // .performExampleFlow(order) add custom flow here
                .complete();
    }
}
