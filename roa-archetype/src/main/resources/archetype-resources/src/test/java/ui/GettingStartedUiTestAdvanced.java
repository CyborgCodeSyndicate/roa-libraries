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

#set( $ui = $uiComponents.toUpperCase() )
#if( $ui.contains("BUTTON") && $ui.contains("INPUT") && $ui.contains("SELECT") )
import ${package}.ui.elements.ButtonFields;
import ${package}.ui.elements.InputFields;
import ${package}.ui.elements.SelectFields;
#end
#if( $ui.contains("TABLE") )
import ${package}.ui.elements.TableExample;
#end
import ${package}.ui.authentication.ExampleCredentials;
import ${package}.ui.authentication.ExampleAppUiLogin;
import ${package}.ui.interceptor.RequestsInterceptor;
import ${package}.common.data.creator.DataCreator;
import ${package}.common.preconditions.Preconditions;
import static ${package}.common.base.Rings.RING_OF_CUSTOM;

import java.util.List;

import static io.cyborgcode.roa.ui.config.UiConfigHolder.getUiConfig;
import static ${package}.common.base.Rings .*;

/**
 * UI test demonstrating authentication, data injection, and element interactions.
 *
 * <p>This class shows how to:
 * <ul>
 *   <li>Authenticate automatically using {@code @AuthenticateViaUi}</li>
 *   <li>Chain multiple UI interactions fluently</li>
 * </ul>
 */
@UI
@DisplayName("Getting started UI test class")
public class GettingStartedUiTestAdvanced extends BaseQuest {

    @Test
    @Regression
    @Description("Using automatic UI authentication through @AuthenticateViaUi and combining journeys/preconditions" +
            " and crafted data")
    @AuthenticateViaUi(credentials = ExampleCredentials.class, type = ExampleAppUiLogin.class)
    @Journey("" // Add preconditions: value = Preconditions.Data.EXAMPLE_PRECONDITION
    )
    void exampleUITest(Quest quest) {

        // TODO: implement your test here
#if( $ui.contains("BUTTON") && $ui.contains("INPUT") && $ui.contains("SELECT") )
        // Login is handled automatically by @AuthenticateViaUi
        quest.use(RING_OF_UI)
                .getNavigation().navigate(getUiConfig().baseUrl())
                .getButtonField().click(ButtonFields.LOGIN_BUTTON)
                .getInputField().insert(InputFields.USERNAME, "example_username")
                .getInputField().insert(InputFields.PASSWORD, "example_password")
                .getSelectField().selectOption(SelectFields.GENERIC_SELECT, "example_select")
                .complete();
#end
#if( $ui.contains("TABLE") )
        // Validate table example:
            // .getTable().readTable(TableExample.EXAMPLE_TABLE_MODEL)
            // .getTable().validate(...).complete();
#end
    }

    @Test
    @Regression
    void customFlowDemonstration(Quest quest) {

        /**
         * Example of integrated custom flow execution.
         */
//        quest.use(RING_OF_CUSTOM)
//                // .exampleFlow()
//                .complete();
    }
}
