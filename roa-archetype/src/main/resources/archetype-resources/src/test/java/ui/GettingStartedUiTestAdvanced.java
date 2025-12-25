package ${package}.ui;

import io.cyborgcode.roa.ui.annotations.AuthenticateViaUi;
import io.cyborgcode.roa.framework.annotation.Journey;
import io.cyborgcode.roa.framework.annotation.Regression;
import io.cyborgcode.roa.example.project.common.data.cleaner.DataCleaner;
import io.cyborgcode.ui.complex.test.framework.ui.interceptor.RequestsInterceptor;
import io.cyborgcode.roa.framework.base.BaseQuest;
import io.cyborgcode.roa.framework.quest.Quest;
import io.cyborgcode.roa.framework.annotation.Ripper;
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
import ${package}.ui.elements.Tables;
import static io.cyborgcode.roa.ui.validator.TableAssertionTypes.TABLE_NOT_EMPTY;
#end
import static ${package}.common.base.Rings .*;
import static io.cyborgcode.roa.ui.config.UiConfigHolder.getUiConfig;

import java.util.List;

/**
 * UI test demonstrating authentication, deletion (cleanup), precondition method execution,
 * element interactions and requests interceptor.
 */
@UI
@DisplayName("Getting started UI test class")
public class GettingStartedUiTestAdvanced extends BaseQuest {

    @Test
    @Regression
    @Description("Using automatic UI authentication through @AuthenticateViaUi, combining journeys/preconditions," +
            " data deletion and request interceptor with validation")
    @AuthenticateViaUi(credentials = ExampleCredentials.class, type = ExampleAppUiLogin.class)
    @InterceptRequests(requestUrlSubStrings = {RequestsInterceptor.Data.EXAMPLE_INTERCEPT})
    @Journey(value = Preconditions.Data.EXAMPLE_PRECONDITION, option = 1)
    @Ripper(targets = {DataCleaner.Data.EXAMPLE_CLEANUP})
    void exampleUITest(Quest quest) {

        quest.use(RING_OF_UI)
#if( $ui.contains("BUTTON") && $ui.contains("INPUT") && $ui.contains("SELECT") )
                .getNavigation().navigate(getUiConfig().baseUrl())
                .getButtonField().click(ButtonFields.LOGIN_BUTTON)
                .getInputField().insert(InputFields.USERNAME, "example_username")
                .getInputField().insert(InputFields.PASSWORD, "example_password")
                .getSelectField().selectOption(SelectFields.GENERIC_SELECT, "example_select")
#end
#if( $ui.contains("TABLE") )
        // Validate table example:
            // .getTable().readTable(Tables.EXAMPLE_TABLE_MODEL)
            // .getTable().validate(Tables.EXAMPLE_TABLE_MODEL,
            // Assertion.builder().type(TABLE_NOT_EMPTY).expected(true).soft(true).build())
#end
                .interceptor().validateResponseHaveStatus(
                        RequestsInterceptor.EXAMPLE_INTERCEPT.getEndpointSubString(), 2, true)
                .complete();
    }

    @Test
    @Regression
    void customFlowDemonstration(Quest quest) {

//        quest.use(RING_OF_CUSTOM)
//                // .exampleFlow()
//                .complete();
    }
}
