package ${package}.ui;

import io.cyborgcode.roa.ui.annotations.AuthenticateViaUi;
import io.cyborgcode.roa.framework.annotation.Journey;
import io.cyborgcode.roa.framework.annotation.Regression;
import io.cyborgcode.roa.framework.base.BaseQuest;
import io.cyborgcode.roa.framework.quest.Quest;
import io.cyborgcode.roa.framework.annotation.Ripper;
import io.cyborgcode.roa.ui.annotations.InterceptRequests;
import io.cyborgcode.roa.ui.annotations.UI;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ${package}.ui.interceptor.RequestsInterceptor;
import ${package}.common.data.cleaner.DataCleaner;
import ${package}.ui.authentication.ExampleCredentials;
import ${package}.ui.authentication.ExampleAppUiLogin;
import ${package}.common.preconditions.Preconditions;
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
import static io.cyborgcode.roa.ui.config.UiConfigHolder.getUiConfig;
import static ${package}.common.base.Rings.RING_OF_UI;
import static ${package}.common.base.Rings.RING_OF_CUSTOM;

// TODO: EXAMPLE TEST — delete this class after reviewing.
// This test is only an example of using ROA UI capabilities
@UI
@DisplayName("EXAMPLE - Getting started UI test (delete me)")
public class GettingStartedUiTestAdvanced extends BaseQuest {

    @Test
    @Regression
    @AuthenticateViaUi(credentials = ExampleCredentials.class, type = ExampleAppUiLogin.class)
    @InterceptRequests(requestUrlSubStrings = {RequestsInterceptor.Data.EXAMPLE_INTERCEPT})
    @Journey(value = Preconditions.Data.EXAMPLE_PRECONDITION, order = 1)
    @Ripper(targets = {DataCleaner.Data.EXAMPLE_CLEANUP})
    void exampleUITest(Quest quest) {

        quest.use(RING_OF_UI)
                .browser().navigate(getUiConfig().baseUrl())
#if( $ui.contains("BUTTON") && $ui.contains("INPUT") && $ui.contains("SELECT") )
                .button().click(ButtonFields.LOGIN_BUTTON)
                .input().insert(InputFields.USERNAME, "example_username")
                .input().insert(InputFields.PASSWORD, "example_password")
                .select().selectOption(SelectFields.GENERIC_SELECT, "example_select")
#end
#if( $ui.contains("TABLE") )
        // Validate table example:
            // .table().readTable(Tables.EXAMPLE_TABLE_MODEL)
            // .table().validate(Tables.EXAMPLE_TABLE_MODEL,
            // Assertion.builder().type(TABLE_NOT_EMPTY).expected(true).soft(true).build())
#end
                .getInterceptor().validateResponseHaveStatus(
                        RequestsInterceptor.EXAMPLE_INTERCEPT.getEndpointSubString(), 2, true)
                .complete();
    }

    @Test
    @Regression
    void customFlowDemonstration(Quest quest) {
        // Example:
        // quest.use(RING_OF_CUSTOM)
        //    .exampleFlow()
        //    .drop()
        //    .use(RING_OF_UI)
        //    .browser().navigate(getUiConfig().baseUrl())
        //    .complete();
    }
}
