package ${package}.ui;

import io.cyborgcode.roa.ui.annotations.AuthenticateViaUi;
import io.cyborgcode.roa.framework.annotation.Journey;
import io.cyborgcode.roa.framework.annotation.Regression;
import io.cyborgcode.roa.framework.base.BaseQuest;
import io.cyborgcode.roa.framework.quest.Quest;
import io.cyborgcode.roa.ui.annotations.UI;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ${package}.ui.authentication.ExampleCredentials;
import ${package}.ui.authentication.ExampleAppUiLogin;
import ${package}.common.preconditions.Preconditions;

import io.qameta.allure.Description;

#set( $ui = $uiComponents.toUpperCase() )
#if( $ui.contains("BUTTON") && $ui.contains("INPUT") && $ui.contains("SELECT") )
import ${package}.ui.elements.ButtonFields;
import ${package}.ui.elements.InputFields;
import ${package}.ui.elements.SelectFields;
#end
#if( $ui.contains("TABLE") )
import ${package}.ui.elements.TableExample;
import static io.cyborgcode.roa.ui.validator.TableAssertionTypes.TABLE_NOT_EMPTY;
#end
import static ${package}.common.base.Rings .*;
import static io.cyborgcode.roa.ui.config.UiConfigHolder.getUiConfig;

import java.util.List;

/**
 * UI test demonstrating authentication, data injection, and element interactions.
 */
@UI
@DisplayName("Getting started UI test class")
public class GettingStartedUiTestAdvanced extends BaseQuest {

    @Test
    @Regression
    @Description("Using automatic UI authentication through @AuthenticateViaUi and combining journeys/preconditions" +
            " and crafted data")
    @AuthenticateViaUi(credentials = ExampleCredentials.class, type = ExampleAppUiLogin.class)
    /*
     * The @Journey annotation is optional and should be used only when a test flow
     * requires one or more preconditions.
     */
    @Journey(value = Preconditions.Data.EXAMPLE_PRECONDITION, option = 1)
    void exampleUITest(Quest quest) {

        // TODO: implement your test here
        quest.use(RING_OF_UI)
#if( $ui.contains("BUTTON") && $ui.contains("INPUT") && $ui.contains("SELECT") )
        // Login is handled automatically by @AuthenticateViaUi
                .getNavigation().navigate(getUiConfig().baseUrl())
                .getButtonField().click(ButtonFields.LOGIN_BUTTON)
                .getInputField().insert(InputFields.USERNAME, "example_username")
                .getInputField().insert(InputFields.PASSWORD, "example_password")
                .getSelectField().selectOption(SelectFields.GENERIC_SELECT, "example_select")
#end
#if( $ui.contains("TABLE") )
        // Validate table example:
            // .getTable().readTable(TableExample.EXAMPLE_TABLE_MODEL)
            // .getTable().validate(TableExample.EXAMPLE_TABLE_MODEL,
            // Assertion.builder().type(TABLE_NOT_EMPTY).expected(true).soft(true).build())
#end
                .complete();
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
