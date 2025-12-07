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
 * Getting started UI test.
 *
 * <p>Includes examples for raw values, crafted data, auth, preconditions and response extractor.
 * Replace element locators, data models, and flows with your application specifics.</p>
 */
@UI
@DisplayName("Getting started UI test class")
public class GettingStartedUiTestAdvanced extends BaseQuest {

    @Test
    @Regression
    @Description("Using automatic UI authentication through @AuthenticateViaUi and combining journeys/preconditions" +
            " and crafted data")
    @AuthenticateViaUi(credentials = ExampleCredentials.class, type = ExampleAppUiLogin.class)
    @Journey("" //implement your journey here
            /*value = Preconditions.Data.EXAMPLE_PRECONDITION,
            journeyData = {@JourneyData(DataCreator.Data.EXAMPLE_TABLE_MODEL)},
            order = 1*/
    )
    void exampleUITest(Quest quest //implement your data creator here
                                    /*,@Craft(model = DataCreator.Data.EXAMPLE_TABLE_MODEL) ExampleTableModel model*/) {

        /**
         * Example:
         *
         * <p>TODO: implement your UI test here</p>
         */
//        quest.use(RING_OF_UI)
//                .browser().navigate(getUiConfig().baseUrl())
//                .input().insert(InputFields.GENERIC_INPUT, "example")
//                .input().insert(InputFields.GENERIC_INPUT, model.getExampleText())
//                .select().selectOption(SelectFields.GENERIC_SELECT, model.getExampleSelection())
//                .validate(() -> Assertions.assertEquals(List.of(""),
//                        retrieve(DataExtractorFunctions
//                                        .responseBodyExtraction(RequestsInterceptor.EXAMPLE_INTERCEPT.getEndpointSubString(),
//                                                "$[0].example[?(@.key=='example')].value", ""),
//                                List.class)))
//                .complete();
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
